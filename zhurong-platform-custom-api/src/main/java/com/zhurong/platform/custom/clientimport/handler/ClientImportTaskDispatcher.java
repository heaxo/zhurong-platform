package com.zhurong.platform.custom.clientimport.handler;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhurong.platform.core.clientimport.mq.ClientImportBusinessTypes;
import com.zhurong.platform.core.clientimport.mq.ClientImportTaskMessage;
import com.zhurong.platform.core.clientimport.mq.ClientImportTaskPayloadItem;
import com.zhurong.platform.custom.clientimport.configuration.ConditionalOnClientCommunicationEnabled;
import com.zhurong.platform.custom.clientimport.feign.ClientImportCoreFeignClient;
import com.zhurong.platform.custom.clientimport.mq.ClientStatusReporter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Component
@ConditionalOnClientCommunicationEnabled
public class ClientImportTaskDispatcher {

    private final ObjectMapper objectMapper;
    private final ClientImportCoreFeignClient coreFeignClient;
    private final ClientStatusReporter statusReporter;
    private final Map<String, ClientImportHandler<?>> handlerMap;

    public ClientImportTaskDispatcher(
            ObjectMapper objectMapper,
            ClientImportCoreFeignClient coreFeignClient,
            ClientStatusReporter statusReporter,
            List<ClientImportHandler<?>> handlers) {

        this.objectMapper = objectMapper;
        this.coreFeignClient = coreFeignClient;
        this.statusReporter = statusReporter;
        // 自动收集所有ClientImportHandler，后续新增业务类型只需要新增处理器Bean并返回唯一businessType。
        this.handlerMap = handlers.stream()
                .collect(Collectors.toMap(ClientImportHandler::businessType, Function.identity()));
    }

    public void dispatch(ClientImportTaskMessage task) {
        if (ClientImportBusinessTypes.PING.equals(task.getBusinessType())) {
            // PING只用于验证定向队列通信，不进入业务导入流程。
            statusReporter.pong(task);
            return;
        }

        ClientImportHandler<?> handler = handlerMap.get(task.getBusinessType());
        if (handler == null) {
            statusReporter.report(task, "FAILED", "未找到业务处理器：" + task.getBusinessType(), null);
            return;
        }

        try {
            // MQ只通知taskId，业务数据必须从core按当前SQL Server状态实时读取。
            ClientImportTaskMessage pendingTask = coreFeignClient.getPendingData(task.getTaskId()).unwrap();
            statusReporter.report(pendingTask, "RECEIVED", "客户端已接收任务", null);
            statusReporter.report(pendingTask, "RUNNING", "客户端开始处理任务", null);

            ClientImportResult result = executeTyped(handler, pendingTask);
            statusReporter.report(pendingTask, result.isSuccess() ? "SUCCESS" : "FAILED",
                    result.getMessage(), result.getImportedRecordIds());
        } catch (Exception ex) {
            log.warn("客户端任务处理失败 taskId={}, businessType={}, message={}",
                    task.getTaskId(), task.getBusinessType(), ex.getMessage());
            statusReporter.report(task, "FAILED", ex.getMessage(), null);
        }
    }

    private <T> ClientImportResult executeTyped(ClientImportHandler<T> handler, ClientImportTaskMessage task) throws Exception {
        JavaType itemType = objectMapper.getTypeFactory()
                .constructParametricType(ClientImportTaskPayloadItem.class, handler.payloadType());
        JavaType listType = objectMapper.getTypeFactory()
                .constructCollectionType(List.class, itemType);
        List<ClientImportTaskPayloadItem<T>> items = objectMapper.readerFor(listType)
                .without(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .readValue(task.getPayload());
        if (items.isEmpty()) {
            return ClientImportResult.success("当前批次没有待导入数据", List.of());
        }
        return handler.execute(new ClientImportContext<>(task, items));
    }
}
