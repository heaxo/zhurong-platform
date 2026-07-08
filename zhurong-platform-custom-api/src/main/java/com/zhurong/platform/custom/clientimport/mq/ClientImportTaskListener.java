package com.zhurong.platform.custom.clientimport.mq;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhurong.platform.core.clientimport.mq.ClientImportTaskMessage;
import com.zhurong.platform.custom.clientimport.configuration.ConditionalOnClientCommunicationEnabled;
import com.zhurong.platform.custom.clientimport.handler.ClientImportTaskDispatcher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@ConditionalOnClientCommunicationEnabled
@RequiredArgsConstructor
public class ClientImportTaskListener {

    private final ObjectMapper objectMapper;
    private final ClientImportTaskDispatcher dispatcher;

    @RabbitListener(queues = "#{@clientImportCommandQueue.name}")
    public void handle(Message message) {
        try {
            // 监听队列来自clientImportCommandQueue Bean，启动时由client-id计算，禁止多个客户端共享同一业务队列。
            ClientImportTaskMessage task = objectMapper.readValue(message.getBody(), ClientImportTaskMessage.class);
            log.info("收到客户端导入任务 taskId={}, businessType={}, targetClientId={}",
                    task.getTaskId(), task.getBusinessType(), task.getTargetClientId());
            dispatcher.dispatch(task);
        } catch (Exception ex) {
            log.warn("客户端导入任务消息解析或分发失败 message={}", ex.getMessage());
            throw new IllegalStateException("客户端导入任务处理失败", ex);
        }
    }
}
