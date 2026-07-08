package com.zhurong.platform.core.clientimport.mq;

import com.zhurong.platform.core.clientimport.configuration.ConditionalOnClientCommunicationEnabled;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@EnableRabbit
@Configuration
@ConditionalOnClientCommunicationEnabled
public class ClientImportRabbitConfiguration {

    /*
     * 本功能只声明独立命名的Exchange/Queue/Binding。
     * 不使用@Primary，也不覆盖项目已有RabbitTemplate、MessageConverter和监听容器工厂。
     */
    @Bean("zhurongClientCommandExchange")
    public DirectExchange zhurongClientCommandExchange() {
        return new DirectExchange(ClientImportMqConstants.COMMAND_EXCHANGE, true, false);
    }

    @Bean("zhurongClientStatusExchange")
    public DirectExchange zhurongClientStatusExchange() {
        return new DirectExchange(ClientImportMqConstants.STATUS_EXCHANGE, true, false);
    }

    @Bean("zhurongClientCommandDeadLetterExchange")
    public DirectExchange zhurongClientCommandDeadLetterExchange() {
        return new DirectExchange(ClientImportMqConstants.COMMAND_DLX, true, false);
    }

    @Bean("zhurongClientStatusQueue")
    public Queue zhurongClientStatusQueue() {
        return new Queue(ClientImportMqConstants.STATUS_QUEUE, true, false, false,
                Map.of("x-dead-letter-exchange", ClientImportMqConstants.COMMAND_DLX));
    }

    @Bean("zhurongClientStatusBinding")
    public Binding zhurongClientStatusBinding(
            @Qualifier("zhurongClientStatusQueue") Queue zhurongClientStatusQueue,
            @Qualifier("zhurongClientStatusExchange") DirectExchange zhurongClientStatusExchange) {

        return BindingBuilder.bind(zhurongClientStatusQueue)
                .to(zhurongClientStatusExchange)
                .with(ClientImportMqConstants.STATUS_ROUTING_KEY);
    }
}
