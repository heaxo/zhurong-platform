package com.zhurong.platform.core.clientimport.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhurong.platform.base.exception.BusinessException;
import com.zhurong.platform.core.clientimport.configuration.ConditionalOnClientCommunicationEnabled;
import com.zhurong.platform.core.clientimport.dto.ClientCommandRequest;
import com.zhurong.platform.core.clientimport.dto.ClientCommandResponse;
import com.zhurong.platform.core.clientimport.dto.ClientCommandResultReport;
import com.zhurong.platform.core.clientimport.entity.ClientCommandTask;
import com.zhurong.platform.core.clientimport.mapper.ClientCommandTaskMapper;
import com.zhurong.platform.core.clientimport.mq.ClientDispatchPublishService;
import com.zhurong.platform.core.clientimport.mq.ClientIds;
import com.zhurong.platform.core.clientimport.mq.ClientImportTaskMessage;
import com.zhurong.platform.core.clientimport.mq.ClientMessageKinds;
import com.zhurong.platform.core.clientimport.service.ClientCommandService;
import com.zhurong.platform.core.clientimport.service.ClientRegistryService;
import com.zhurong.platform.core.clientimport.service.EntityAuditHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 通用客户端命令同步执行器。
 *
 * <p>命令先持久化再发布；等待线程使用内存 Future 降低延迟，数据库记录保证服务重启后仍能审计结果。
 * core 只保存和转发 JSON，不解释任何客户字段。</p>
 */
@Service
@ConditionalOnClientCommunicationEnabled
@RequiredArgsConstructor
public class ClientCommandServiceImpl
        extends ServiceImpl<ClientCommandTaskMapper, ClientCommandTask>
        implements ClientCommandService {

    private final ObjectMapper objectMapper;
    private final ClientRegistryService clientRegistryService;
    private final ClientDispatchPublishService publishService;
    private final Map<String, CompletableFuture<ClientCommandResponse>> waiters = new ConcurrentHashMap<>();

    @Override
    public ClientCommandResponse execute(ClientCommandRequest request) {
        String targetClientId = ClientIds.normalize(request.getTargetClientId());
        if (!StringUtils.hasText(targetClientId)) {
            throw new BusinessException("未绑定目标客户端");
        }
        String commandType = request.getCommandType().trim();
        clientRegistryService.requireOnline(targetClientId);

        String commandId = "CMD-" + IdWorker.getIdStr();
        ClientCommandTask entity = new ClientCommandTask();
        entity.setCommandId(commandId);
        entity.setCommandType(commandType);
        entity.setTargetClientId(targetClientId);
        entity.setStatus("PENDING");
        entity.setPayloadJson(writeJson(request.getPayload()));
        EntityAuditHelper.prepareInsert(entity);
        save(entity);

        CompletableFuture<ClientCommandResponse> waiter = new CompletableFuture<>();
        waiters.put(commandId, waiter);
        try {
            // 先落库再发布，防止客户端极速回执 SUCCESS 后又被发布线程覆盖回 PUBLISHED。
            entity.setStatus("PUBLISHED");
            entity.setPublishTime(EntityAuditHelper.now());
            EntityAuditHelper.prepareUpdate(entity);
            updateById(entity);
            publishService.publish(toMessage(entity, request.getPayload()));

            int timeoutSeconds = request.getTimeoutSeconds() == null ? 120 : request.getTimeoutSeconds();
            return waiter.get(timeoutSeconds, TimeUnit.SECONDS);
        } catch (TimeoutException ex) {
            return handleTimeout(entity);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            markFailed(entity, "等待客户端执行结果被中断");
            throw new BusinessException("等待客户端执行结果被中断");
        } catch (BusinessException ex) {
            markFailed(entity, ex.getMessage());
            throw ex;
        } catch (Exception ex) {
            markFailed(entity, ex.getMessage());
            throw new BusinessException("客户端命令执行失败: " + safeMessage(ex));
        } finally {
            waiters.remove(commandId);
        }
    }

    @Override
    public boolean complete(String commandId, ClientCommandResultReport report) {
        ClientCommandTask entity = getOne(Wrappers.lambdaQuery(ClientCommandTask.class)
                .eq(ClientCommandTask::getCommandId, commandId), false);
        if (entity == null) {
            throw new BusinessException("客户端命令不存在: " + commandId);
        }
        String reportingClientId = ClientIds.normalize(report.getClientId());
        if (!entity.getTargetClientId().equals(reportingClientId)) {
            throw new BusinessException("客户端命令回执来源不匹配");
        }
        if (!entity.getCommandType().equals(report.getCommandType())) {
            throw new BusinessException("客户端命令回执类型不匹配");
        }

        String status = report.isSuccess() ? "SUCCESS" : "FAILED";
        entity.setStatus(status);
        entity.setResultMessage(report.getMessage());
        entity.setResultJson(writeJson(report.getData()));
        entity.setFinishTime(EntityAuditHelper.now());
        EntityAuditHelper.prepareUpdate(entity);
        updateById(entity);

        ClientCommandResponse response = response(entity, status, report.getMessage(), report.getData());
        CompletableFuture<ClientCommandResponse> waiter = waiters.get(commandId);
        if (waiter != null) {
            waiter.complete(response);
        }
        return true;
    }

    private ClientImportTaskMessage toMessage(ClientCommandTask entity, JsonNode payload) {
        ClientImportTaskMessage message = new ClientImportTaskMessage();
        message.setMessageKind(ClientMessageKinds.COMMAND);
        message.setTaskId(entity.getCommandId());
        message.setRequestId(entity.getCommandId());
        message.setTargetClientId(entity.getTargetClientId());
        message.setBusinessType(entity.getCommandType());
        message.setSchemaVersion("1.0");
        message.setCreateTime(Instant.now());
        message.setPayload(payload);
        return message;
    }

    private ClientCommandResponse response(
            ClientCommandTask entity,
            String status,
            String message,
            JsonNode data
    ) {
        ClientCommandResponse response = new ClientCommandResponse();
        response.setCommandId(entity.getCommandId());
        response.setTargetClientId(entity.getTargetClientId());
        response.setCommandType(entity.getCommandType());
        response.setStatus(status);
        response.setMessage(message);
        response.setData(data);
        return response;
    }

    private ClientCommandResponse handleTimeout(ClientCommandTask original) {
        ClientCommandTask latest = getOne(Wrappers.lambdaQuery(ClientCommandTask.class)
                .eq(ClientCommandTask::getCommandId, original.getCommandId()), false);
        if (latest != null && ("SUCCESS".equals(latest.getStatus()) || "FAILED".equals(latest.getStatus()))) {
            return response(latest, latest.getStatus(), latest.getResultMessage(), readJson(latest.getResultJson()));
        }

        update(Wrappers.lambdaUpdate(ClientCommandTask.class)
                .set(ClientCommandTask::getStatus, "TIMEOUT")
                .set(ClientCommandTask::getResultMessage, "等待客户端执行结果超时")
                .set(ClientCommandTask::getFinishTime, EntityAuditHelper.now())
                .eq(ClientCommandTask::getCommandId, original.getCommandId())
                .notIn(ClientCommandTask::getStatus, "SUCCESS", "FAILED"));
        return response(original, "TIMEOUT", "等待客户端执行结果超时", null);
    }

    private void markFailed(ClientCommandTask entity, String message) {
        update(Wrappers.lambdaUpdate(ClientCommandTask.class)
                .set(ClientCommandTask::getStatus, "FAILED")
                .set(ClientCommandTask::getResultMessage, message)
                .set(ClientCommandTask::getFinishTime, EntityAuditHelper.now())
                .eq(ClientCommandTask::getCommandId, entity.getCommandId())
                .notIn(ClientCommandTask::getStatus, "SUCCESS", "FAILED"));
    }

    private String writeJson(Object value) {
        if (value == null) {
            return null;
        }
        try {
            return objectMapper.writeValueAsString(value);
        } catch (JsonProcessingException ex) {
            throw new BusinessException("客户端命令JSON序列化失败");
        }
    }

    private JsonNode readJson(String value) {
        if (!StringUtils.hasText(value)) {
            return null;
        }
        try {
            return objectMapper.readTree(value);
        } catch (JsonProcessingException ex) {
            throw new BusinessException("客户端命令JSON反序列化失败");
        }
    }

    private static String safeMessage(Exception ex) {
        return StringUtils.hasText(ex.getMessage()) ? ex.getMessage() : ex.getClass().getSimpleName();
    }
}
