package com.zhurong.platform.core.clientimport.mq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhurong.platform.base.exception.BusinessException;
import com.zhurong.platform.core.clientimport.configuration.ConditionalOnClientCommunicationEnabled;
import com.zhurong.platform.core.clientimport.entity.ClientDispatchTask;
import com.zhurong.platform.core.clientimport.enums.DispatchStatus;
import com.zhurong.platform.core.clientimport.service.ClientDispatchTaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@ConditionalOnClientCommunicationEnabled
@RequiredArgsConstructor
public class ClientDispatchPublishServiceImpl implements ClientDispatchPublishService {

    /*
     * 这里复用Spring Boot自动装配的RabbitTemplate，不声明新的RabbitTemplate、
     * MessageConverter或ListenerContainerFactory，避免改变core已有RabbitMQ行为。
     */
    private final RabbitTemplate rabbitTemplate;
    private final AmqpAdmin amqpAdmin;
    private final ObjectMapper objectMapper;
    private final ClientDispatchTaskService dispatchTaskService;

    @Override
    public void publish(String taskId) {
        ClientDispatchTask task = dispatchTaskService.getByTaskId(taskId);
        if (task == null) {
            throw new BusinessException("CLIENT_DISPATCH_TASK_NOT_FOUND: 派发任务不存在");
        }
        try {
            // RequestSnapshot只保存轻量任务通知；业务数据由客户端收到通知后通过Feign按taskId实时查询SQL Server。
            ClientImportTaskMessage message = objectMapper.readValue(task.getRequestSnapshot(), ClientImportTaskMessage.class);
            publish(message);
            dispatchTaskService.updateStatus(taskId, DispatchStatus.PUBLISHED, null);
        } catch (Exception ex) {
            dispatchTaskService.updateStatus(taskId, DispatchStatus.PUBLISH_FAILED, ex.getMessage());
            throw new BusinessException("CLIENT_DISPATCH_PUBLISH_FAILED: 客户端任务发布失败：" + ex.getMessage());
        }
    }

    @Override
    public void publish(ClientImportTaskMessage message) {
        String routingKey = ClientImportMqConstants.commandRoutingKey(message.getTargetClientId());
        try {
            ensureTargetCommandQueue(message.getTargetClientId());
            byte[] body = objectMapper.writeValueAsBytes(message);
            Message amqpMessage = MessageBuilder.withBody(body)
                    .setContentType(MessageProperties.CONTENT_TYPE_JSON)
                    .setDeliveryMode(MessageDeliveryMode.PERSISTENT)
                    .setCorrelationId(message.getTaskId())
                    .setHeader("taskId", message.getTaskId())
                    .setHeader("requestId", message.getRequestId())
                    .setHeader("targetClientId", message.getTargetClientId())
                    .setHeader("businessType", message.getBusinessType())
                    .build();
            // DirectExchange + client.{clientId}确保消息只进入目标客户端专属队列。
            rabbitTemplate.send(ClientImportMqConstants.COMMAND_EXCHANGE, routingKey, amqpMessage);
        } catch (JsonProcessingException ex) {
            throw new BusinessException("CLIENT_DISPATCH_MESSAGE_SERIALIZE_FAILED: 任务消息序列化失败");
        }
    }

    private void ensureTargetCommandQueue(String targetClientId) {
        String queueName = ClientImportMqConstants.commandQueue(targetClientId);
        String deadLetterQueueName = queueName + ".dlq";
        String routingKey = ClientImportMqConstants.commandRoutingKey(targetClientId);
        String deadLetterRoutingKey = routingKey + ".dlq";

        DirectExchange commandExchange = new DirectExchange(ClientImportMqConstants.COMMAND_EXCHANGE, true, false);
        DirectExchange deadLetterExchange = new DirectExchange(ClientImportMqConstants.COMMAND_DLX, true, false);
        Queue commandQueue = new Queue(queueName, true, false, false,
                Map.of(
                        "x-dead-letter-exchange", ClientImportMqConstants.COMMAND_DLX,
                        "x-dead-letter-routing-key", deadLetterRoutingKey
                ));
        Queue deadLetterQueue = new Queue(deadLetterQueueName, true);

        amqpAdmin.declareExchange(commandExchange);
        amqpAdmin.declareExchange(deadLetterExchange);
        amqpAdmin.declareQueue(commandQueue);
        amqpAdmin.declareQueue(deadLetterQueue);
        amqpAdmin.declareBinding(BindingBuilder.bind(commandQueue)
                .to(commandExchange)
                .with(routingKey));
        amqpAdmin.declareBinding(BindingBuilder.bind(deadLetterQueue)
                .to(deadLetterExchange)
                .with(deadLetterRoutingKey));
    }
}
