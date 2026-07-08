package com.zhurong.platform.core.clientimport.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhurong.platform.base.exception.BusinessException;
import com.zhurong.platform.core.clientimport.business.ClientImportBusinessRegistry;
import com.zhurong.platform.core.clientimport.configuration.ConditionalOnClientCommunicationEnabled;
import com.zhurong.platform.core.clientimport.entity.ClientDispatchTask;
import com.zhurong.platform.core.clientimport.enums.DispatchStatus;
import com.zhurong.platform.core.clientimport.mq.ClientDispatchPublishService;
import com.zhurong.platform.core.clientimport.mq.ClientImportTaskMessage;
import com.zhurong.platform.core.clientimport.mq.ClientImportTaskStatusMessage;
import com.zhurong.platform.core.clientimport.service.ClientDispatchTaskService;
import com.zhurong.platform.core.clientimport.service.ClientImportTaskRuntimeService;
import com.zhurong.platform.core.clientimport.service.ClientRegistryService;
import com.zhurong.platform.core.clientimport.service.EntityAuditHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@ConditionalOnClientCommunicationEnabled
@RequiredArgsConstructor
public class ClientImportTaskRuntimeServiceImpl implements ClientImportTaskRuntimeService {

    private static final int MAX_IMPORT_RETRY_COUNT = 3;

    private final ObjectMapper objectMapper;
    private final ClientRegistryService clientRegistryService;
    private final ClientDispatchTaskService dispatchTaskService;
    private final ClientDispatchPublishService dispatchPublishService;
    private final ClientImportBusinessRegistry businessRegistry;

    @Override
    public ClientImportTaskMessage getPendingData(String taskId) {
        ClientDispatchTask task = getRequiredTask(taskId);
        return buildPendingTaskMessage(task);
    }

    @Override
    public boolean handleStatus(ClientImportTaskStatusMessage statusMessage) {
        clientRegistryService.heartbeat(statusMessage);
        if (statusMessage.getTaskId() == null || statusMessage.getTaskId().startsWith("PING-")) {
            log.info("收到客户端状态 clientId={}, status={}, message={}",
                    statusMessage.getClientId(), statusMessage.getStatus(), statusMessage.getMessage());
            return true;
        }

        ClientDispatchTask task = dispatchTaskService.getByTaskId(statusMessage.getTaskId());
        if (task == null) {
            return false;
        }

        markImportedRows(task, statusMessage.getImportedRecordIds());
        updateRunningRows(task, statusMessage);
        boolean shouldRetry = updateTaskStatus(task, statusMessage);
        if (shouldRetry) {
            // 重试只重新发送taskId通知；客户端再次通过Feign读取SQL Server中的当前未导入数据。
            dispatchPublishService.publish(task.getTaskId());
        }
        return true;
    }

    private boolean updateTaskStatus(ClientDispatchTask task, ClientImportTaskStatusMessage statusMessage) {
        boolean terminal = DispatchStatus.SUCCESS.name().equals(statusMessage.getStatus())
                || DispatchStatus.FAILED.name().equals(statusMessage.getStatus())
                || DispatchStatus.TIMEOUT.name().equals(statusMessage.getStatus());

        boolean shouldRetry = false;
        String nextStatus = statusMessage.getStatus();
        String nextMessage = statusMessage.getMessage();
        if (terminal) {
            long remaining = countNotImported(task);
            if (remaining == 0) {
                nextStatus = DispatchStatus.SUCCESS.name();
                nextMessage = "客户端批量导入完成";
            } else {
                int retryCount = task.getRetryCount() == null ? 0 : task.getRetryCount();
                if (retryCount < MAX_IMPORT_RETRY_COUNT) {
                    int nextRetryCount = retryCount + 1;
                    task.setRetryCount(nextRetryCount);
                    nextStatus = DispatchStatus.PENDING_DISPATCH.name();
                    nextMessage = "客户端批量导入未完成，剩余未导入数量：" + remaining
                            + "，准备第" + nextRetryCount + "次重试";
                    updateRowsNotImported(task, DispatchStatus.PENDING_DISPATCH, nextMessage);
                    shouldRetry = true;
                } else {
                    nextStatus = DispatchStatus.FAILED.name();
                    nextMessage = "客户端批量导入未完成，已达到最大重试次数，剩余未导入数量：" + remaining;
                    markRemainingRowsFailed(task, nextMessage);
                }
            }
        }

        task.setStatus(nextStatus);
        task.setResponseSnapshot(writeJson(statusMessage));
        task.setErrorMessage(nextMessage);
        if (DispatchStatus.RECEIVED.name().equals(nextStatus)) {
            task.setReceiveTime(LocalDateTime.now());
        }
        if (DispatchStatus.SUCCESS.name().equals(nextStatus)
                || DispatchStatus.FAILED.name().equals(nextStatus)
                || DispatchStatus.TIMEOUT.name().equals(nextStatus)) {
            task.setFinishTime(LocalDateTime.now());
        }
        EntityAuditHelper.prepareUpdate(task);
        dispatchTaskService.updateById(task);
        return shouldRetry;
    }

    private void markImportedRows(ClientDispatchTask task, List<Long> importedRecordIds) {
        if (CollectionUtils.isEmpty(importedRecordIds)) {
            return;
        }
        businessRegistry.get(task.getBusinessType())
                .markImportedRows(task.getRequestId(), importedRecordIds, "客户端已确认导入");
    }

    private void updateRunningRows(ClientDispatchTask task, ClientImportTaskStatusMessage statusMessage) {
        if (!DispatchStatus.RECEIVED.name().equals(statusMessage.getStatus())
                && !DispatchStatus.RUNNING.name().equals(statusMessage.getStatus())) {
            return;
        }
        updateRowsNotImported(task, DispatchStatus.valueOf(statusMessage.getStatus()), statusMessage.getMessage());
    }

    private void markRemainingRowsFailed(ClientDispatchTask task, String message) {
        updateRowsNotImported(task, DispatchStatus.FAILED, message);
    }

    private void updateRowsNotImported(ClientDispatchTask task, DispatchStatus status, String message) {
        businessRegistry.get(task.getBusinessType()).updateNotImportedRows(task.getRequestId(), status, message);
    }

    private long countNotImported(ClientDispatchTask task) {
        return businessRegistry.get(task.getBusinessType()).countNotImported(task.getRequestId());
    }

    private ClientImportTaskMessage buildPendingTaskMessage(ClientDispatchTask task) {
        ClientImportTaskMessage message = new ClientImportTaskMessage();
        message.setTaskId(task.getTaskId());
        message.setRequestId(task.getRequestId());
        message.setTargetClientId(task.getTargetClientId());
        message.setBusinessType(task.getBusinessType());
        message.setSchemaVersion("1.0");
        message.setCreateTime(Instant.now());
        message.setPayload(objectMapper.valueToTree(buildPendingPayload(task)));
        return message;
    }

    private List<?> buildPendingPayload(ClientDispatchTask task) {
        return businessRegistry.get(task.getBusinessType()).listPendingPayload(task.getRequestId());
    }

    private ClientDispatchTask getRequiredTask(String taskId) {
        ClientDispatchTask task = dispatchTaskService.getByTaskId(taskId);
        if (task == null) {
            throw new BusinessException("CLIENT_DISPATCH_TASK_NOT_FOUND: 派发任务不存在");
        }
        return task;
    }

    private String writeJson(Object value) {
        if (value == null) {
            return null;
        }
        try {
            return objectMapper.writeValueAsString(value);
        } catch (JsonProcessingException ex) {
            throw new BusinessException("JSON_SERIALIZE_FAILED: JSON序列化失败：" + ex.getMessage());
        }
    }
}
