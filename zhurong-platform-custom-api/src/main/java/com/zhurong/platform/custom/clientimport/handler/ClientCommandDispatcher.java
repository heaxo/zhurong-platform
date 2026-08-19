package com.zhurong.platform.custom.clientimport.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhurong.platform.core.clientimport.mq.ClientImportTaskMessage;
import com.zhurong.platform.custom.clientimport.configuration.ConditionalOnClientCommunicationEnabled;
import com.zhurong.platform.custom.clientimport.mq.ClientStatusReporter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 通用命令分发器。公共层只做类型匹配和 JSON 转换，不包含任何客户业务分支。
 */
@Slf4j
@Component
@ConditionalOnClientCommunicationEnabled
public class ClientCommandDispatcher {

    private final ObjectMapper objectMapper;
    private final ClientStatusReporter statusReporter;
    private final Map<String, ClientCommandHandler<?>> handlers;

    public ClientCommandDispatcher(
            ObjectMapper objectMapper,
            ClientStatusReporter statusReporter,
            List<ClientCommandHandler<?>> handlers
    ) {
        this.objectMapper = objectMapper;
        this.statusReporter = statusReporter;
        this.handlers = handlers.stream().collect(Collectors.toUnmodifiableMap(
                ClientCommandHandler::commandType,
                Function.identity()
        ));
    }

    public void dispatch(ClientImportTaskMessage command) {
        ClientCommandHandler<?> handler = handlers.get(command.getBusinessType());
        if (handler == null) {
            statusReporter.reportCommand(command, ClientCommandResult.failed(
                    "未找到客户端命令处理器: " + command.getBusinessType()
            ));
            return;
        }
        ClientCommandResult result;
        try {
            result = executeTyped(handler, command);
        } catch (Exception ex) {
            log.error("客户端命令执行失败, commandId={}, commandType={}",
                    command.getTaskId(), command.getBusinessType(), ex);
            result = ClientCommandResult.failed(safeMessage(ex));
        }
        // 回执失败必须交给 RabbitMQ 重新投递；具体客户处理器负责按 commandId 幂等返回原结果。
        statusReporter.reportCommand(command, result);
    }

    private <T> ClientCommandResult executeTyped(
            ClientCommandHandler<T> handler,
            ClientImportTaskMessage command
    ) {
        T payload = objectMapper.convertValue(command.getPayload(), handler.payloadType());
        return handler.execute(command.getTaskId(), payload);
    }

    private static String safeMessage(Exception ex) {
        return ex.getMessage() == null || ex.getMessage().isBlank()
                ? ex.getClass().getSimpleName()
                : ex.getMessage();
    }
}
