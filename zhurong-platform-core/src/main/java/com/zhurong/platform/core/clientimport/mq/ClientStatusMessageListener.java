package com.zhurong.platform.core.clientimport.mq;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhurong.platform.core.clientimport.configuration.ConditionalOnClientCommunicationEnabled;
import com.zhurong.platform.core.clientimport.service.ClientImportTaskRuntimeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@ConditionalOnClientCommunicationEnabled
@RequiredArgsConstructor
public class ClientStatusMessageListener {

    private final ObjectMapper objectMapper;
    private final ClientImportTaskRuntimeService taskRuntimeService;

    @RabbitListener(queues = ClientImportMqConstants.STATUS_QUEUE)
    public void handle(Message message) {
        try {
            ClientImportTaskStatusMessage statusMessage =
                    objectMapper.readValue(message.getBody(), ClientImportTaskStatusMessage.class);
            taskRuntimeService.handleStatus(statusMessage);
        } catch (Exception ex) {
            // 当前导入任务状态通过Feign回写；status MQ主要承载心跳，失败只记录日志，避免心跳消息重试风暴。
            log.warn("客户端状态消息处理失败 message={}", ex.getMessage());
        }
    }
}
