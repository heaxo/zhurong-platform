package com.zhurong.platform.custom.clientimport.mq;

import com.zhurong.platform.core.clientimport.mq.ClientImportMqConstants;
import com.zhurong.platform.custom.clientimport.configuration.ClientCommunicationProperties;
import com.zhurong.platform.custom.clientimport.configuration.ConditionalOnClientCommunicationEnabled;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.util.Map;

@EnableRabbit
@Configuration
@ConditionalOnClientCommunicationEnabled
@RequiredArgsConstructor
public class ClientRabbitConfiguration {

    private final ClientCommunicationProperties properties;

    @Bean("clientImportCommandExchange")
    public DirectExchange clientImportCommandExchange() {
        return new DirectExchange(ClientImportMqConstants.COMMAND_EXCHANGE, true, false);
    }

    @Bean("clientImportStatusExchange")
    public DirectExchange clientImportStatusExchange() {
        return new DirectExchange(ClientImportMqConstants.STATUS_EXCHANGE, true, false);
    }

    @Bean("clientImportCommandDeadLetterExchange")
    public DirectExchange clientImportCommandDeadLetterExchange() {
        return new DirectExchange(ClientImportMqConstants.COMMAND_DLX, true, false);
    }

    @Bean("clientImportCommandQueue")
    public Queue clientImportCommandQueue() {
        String clientId = requireClientId();
        return new Queue(ClientImportMqConstants.commandQueue(clientId), true, false, false,
                Map.of(
                        "x-dead-letter-exchange", ClientImportMqConstants.COMMAND_DLX,
                        "x-dead-letter-routing-key", ClientImportMqConstants.commandRoutingKey(clientId) + ".dlq"
                ));
    }

    @Bean("clientImportCommandDeadLetterQueue")
    public Queue clientImportCommandDeadLetterQueue() {
        return new Queue(ClientImportMqConstants.commandQueue(requireClientId()) + ".dlq", true);
    }

    @Bean("clientImportCommandBinding")
    public Binding clientImportCommandBinding(
            @Qualifier("clientImportCommandQueue") Queue queue,
            @Qualifier("clientImportCommandExchange") DirectExchange exchange) {

        // RabbitMQ专属队列避免随机消费：CLIENT-01只绑定client.CLIENT-01，不与其他客户端共享同一业务队列。
        return BindingBuilder.bind(queue)
                .to(exchange)
                .with(ClientImportMqConstants.commandRoutingKey(requireClientId()));
    }

    @Bean("clientImportCommandDeadLetterBinding")
    public Binding clientImportCommandDeadLetterBinding(
            @Qualifier("clientImportCommandDeadLetterQueue") Queue queue,
            @Qualifier("clientImportCommandDeadLetterExchange") DirectExchange exchange) {

        return BindingBuilder.bind(queue)
                .to(exchange)
                .with(ClientImportMqConstants.commandRoutingKey(requireClientId()) + ".dlq");
    }

    private String requireClientId() {
        if (!StringUtils.hasText(properties.getClientId())) {
            throw new IllegalStateException("zhurong.client-communication.client-id不能为空");
        }
        return properties.getClientId().trim();
    }
}
