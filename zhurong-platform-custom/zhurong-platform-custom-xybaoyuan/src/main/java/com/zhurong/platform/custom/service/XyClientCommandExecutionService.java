package com.zhurong.platform.custom.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhurong.platform.custom.clientimport.handler.ClientCommandResult;
import com.zhurong.platform.custom.clientimport.handler.ClientCommandExecutionStore;
import com.zhurong.platform.custom.entity.XyClientCommandExecution;
import com.zhurong.platform.custom.mapper.XyClientCommandExecutionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.function.Function;

/**
 * 保证 RabbitMQ 消息在回执失败后重新投递时，不会再次启动同一个本机自动化命令。
 */
@Service
@RequiredArgsConstructor
public class XyClientCommandExecutionService implements ClientCommandExecutionStore {

    private final XyClientCommandExecutionMapper mapper;
    private final ObjectMapper objectMapper;

    @Override
    public ClientCommandResult executeOnce(
            String commandId,
            String commandType,
            Function<Boolean, ClientCommandResult> action
    ) {
        XyClientCommandExecution existing = find(commandId);
        if (existing != null && ("SUCCESS".equals(existing.getStatus())
                || "FAILED".equals(existing.getStatus()))) {
            return toResult(existing);
        }

        boolean recoveringInterruptedExecution = existing != null && "RUNNING".equals(existing.getStatus());
        XyClientCommandExecution record = existing == null ? new XyClientCommandExecution() : existing;
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
            result = action.apply(recoveringInterruptedExecution);
        } catch (Exception ex) {
            result = ClientCommandResult.failed(safeMessage(ex));
        }
        record.setStatus(result.isSuccess() ? "SUCCESS" : "FAILED");
        record.setMessage(result.getMessage());
        record.setResultJson(writeJson(result.getData()));
        mapper.updateById(record);
        return result;
    }

    private XyClientCommandExecution find(String commandId) {
        return mapper.selectOne(Wrappers.lambdaQuery(XyClientCommandExecution.class)
                .eq(XyClientCommandExecution::getCommandId, commandId));
    }

    private ClientCommandResult toResult(XyClientCommandExecution record) {
        Object data = null;
        if (StringUtils.hasText(record.getResultJson())) {
            try {
                data = objectMapper.readValue(record.getResultJson(), Object.class);
            } catch (JsonProcessingException ex) {
                throw new IllegalStateException("读取客户端命令幂等结果失败", ex);
            }
        }
        return new ClientCommandResult("SUCCESS".equals(record.getStatus()), record.getMessage(), data);
    }

    private String writeJson(Object value) {
        if (value == null) {
            return null;
        }
        try {
            return objectMapper.writeValueAsString(value);
        } catch (JsonProcessingException ex) {
            throw new IllegalStateException("保存客户端命令幂等结果失败", ex);
        }
    }

    private static String safeMessage(Exception ex) {
        return StringUtils.hasText(ex.getMessage()) ? ex.getMessage() : ex.getClass().getSimpleName();
    }
}
