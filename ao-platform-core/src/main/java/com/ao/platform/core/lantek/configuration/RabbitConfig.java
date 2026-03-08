package com.ao.platform.core.lantek.configuration;

import com.ao.platform.core.lantek.constants.NestMqConstants;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    public TopicExchange nestExchange() {
        return new TopicExchange(NestMqConstants.EXCHANGE);
    }

    @Bean
    public Queue nestQueue() {
        return new Queue(NestMqConstants.Queue.CUSTOM_STATE);
    }

    @Bean
    public Binding binding(Queue nestQueue, TopicExchange nestExchange) {
        return BindingBuilder
                .bind(nestQueue)
                .to(nestExchange)
                .with(NestMqConstants.RoutingKey.STATE_CHANGED);
    }

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}