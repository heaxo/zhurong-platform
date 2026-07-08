package com.zhurong.platform.custom.clientimport.mq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhurong.platform.core.clientimport.mq.ClientImportMqConstants;
import com.zhurong.platform.core.clientimport.mq.ClientImportTaskMessage;
import com.zhurong.platform.core.clientimport.mq.ClientImportTaskStatusMessage;
import com.zhurong.platform.custom.clientimport.configuration.ClientCommunicationProperties;
import com.zhurong.platform.custom.clientimport.configuration.ConditionalOnClientCommunicationEnabled;
import com.zhurong.platform.custom.clientimport.feign.ClientImportCoreFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@Component
@ConditionalOnClientCommunicationEnabled
@RequiredArgsConstructor
public class ClientStatusReporter {

    // 状态回传使用独立status exchange，不复用已有套料状态MQ拓扑。
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;
    private final ClientCommunicationProperties properties;
    private final ClientImportCoreFeignClient coreFeignClient;

    public void report(ClientImportTaskMessage task, String status, String message, List<Long> importedRecordIds) {
        ClientImportTaskStatusMessage statusMessage = baseStatus(status, message);
        statusMessage.setTaskId(task.getTaskId());
        statusMessage.setRequestId(task.getRequestId());
        statusMessage.setBusinessType(task.getBusinessType());
        statusMessage.setImportedRecordIds(importedRecordIds);
        // 导入任务状态通过Feign回写core，避免MQ成为业务状态的长期存储来源。
        coreFeignClient.updateStatus(task.getTaskId(), statusMessage).unwrap();
    }

    public void heartbeat() {
        send(baseStatus("HEARTBEAT", "客户端心跳"));
    }

    public void pong(ClientImportTaskMessage task) {
        report(task, "PONG", "PONG", null);
    }

    private ClientImportTaskStatusMessage baseStatus(String status, String message) {
        ClientImportTaskStatusMessage statusMessage = new ClientImportTaskStatusMessage();
        statusMessage.setClientId(properties.getClientId());
        statusMessage.setUserName(properties.getUserName());
        statusMessage.setVersion(properties.getVersion());
        statusMessage.setStatus(status);
        statusMessage.setMessage(message);
        statusMessage.setEventTime(Instant.now());
        return statusMessage;
    }

    private void send(ClientImportTaskStatusMessage statusMessage) {
        try {
            Message amqpMessage = MessageBuilder.withBody(objectMapper.writeValueAsBytes(statusMessage))
                    .setContentType(MessageProperties.CONTENT_TYPE_JSON)
                    .setDeliveryMode(MessageDeliveryMode.PERSISTENT)
                    .setHeader("clientId", statusMessage.getClientId())
                    .setHeader("status", statusMessage.getStatus())
                    .build();
            rabbitTemplate.send(ClientImportMqConstants.STATUS_EXCHANGE, ClientImportMqConstants.STATUS_ROUTING_KEY, amqpMessage);
        } catch (JsonProcessingException ex) {
            throw new IllegalStateException("客户端状态消息序列化失败", ex);
        }
    }
}
