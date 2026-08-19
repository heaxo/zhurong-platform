package com.zhurong.platform.custom.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhurong.platform.custom.clientimport.handler.ClientCommandExecutionStore;
import com.zhurong.platform.custom.clientimport.handler.ClientCommandResult;
import com.zhurong.platform.custom.entity.ClientCommandExecution;
import com.zhurong.platform.custom.mapper.ClientCommandExecutionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.function.Function;

/**
 * 按 commandId 保存本机 HTTP 代理结果。MQ 回执失败导致重投时，
 * 直接返回原结果，避免重复执行创建、导入等有副作用的请求。
 */
@Service
@DS("lantek")
@RequiredArgsConstructor
public class ClientCommandExecutionService implements ClientCommandExecutionStore {

    private final ClientCommandExecutionMapper mapper;
    private final ObjectMapper objectMapper;

    @Override
    public ClientCommandResult executeOnce(
            String commandId,
            String commandType,
            Function<Boolean, ClientCommandResult> action
    ) {
        ClientCommandExecution existing = find(commandId);
        if (existing != null && ("SUCCESS".equals(existing.getStatus())
                || "FAILED".equals(existing.getStatus()))) {
            return toResult(existing);
        }

        boolean recovering = existing != null && "RUNNING".equals(existing.getStatus());
        ClientCommandExecution record = existing == null ? new ClientCommandExecution() : existing;
        record.setCommandId(commandId);
        record.setCommandType(commandType);
        record.setStatus("RUNNING");
        if (existing == null) {
            mapper.insert(record);
        } else {
            mapper.updateById(record);
        }

        ClientCommandResult result;
        try {
            result = action.apply(recovering);
        } catch (Exception exception) {
            result = ClientCommandResult.failed(safeMessage(exception));
        }
        record.setStatus(result.isSuccess() ? "SUCCESS" : "FAILED");
        record.setMessage(result.getMessage());
        record.setResultJson(writeJson(result.getData()));
        mapper.updateById(record);
        return result;
    }

    private ClientCommandExecution find(String commandId) {
        return mapper.selectOne(Wrappers.lambdaQuery(ClientCommandExecution.class)
                .eq(ClientCommandExecution::getCommandId, commandId));
    }

    private ClientCommandResult toResult(ClientCommandExecution record) {
        Object data = null;
        if (StringUtils.hasText(record.getResultJson())) {
            try {
                data = objectMapper.readValue(record.getResultJson(), Object.class);
            } catch (JsonProcessingException exception) {
                throw new IllegalStateException("读取客户端命令幂等结果失败", exception);
            }
        }
        return new ClientCommandResult(
                "SUCCESS".equals(record.getStatus()),
                record.getMessage(),
                data
        );
    }

    private String writeJson(Object value) {
        if (value == null) {
            return null;
        }
        try {
            return objectMapper.writeValueAsString(value);
        } catch (JsonProcessingException exception) {
            throw new IllegalStateException("保存客户端命令幂等结果失败", exception);
        }
    }

    private static String safeMessage(Exception exception) {
        return StringUtils.hasText(exception.getMessage())
                ? exception.getMessage()
                : exception.getClass().getSimpleName();
    }
}
