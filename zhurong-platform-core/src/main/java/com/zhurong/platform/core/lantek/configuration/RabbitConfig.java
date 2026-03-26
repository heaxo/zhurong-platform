package com.zhurong.platform.core.lantek.configuration;

import com.zhurong.platform.core.lantek.constants.NestMqConstants;
import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitConfig {

    @Bean
    public TopicExchange nestExchange() {
        return new TopicExchange(NestMqConstants.EXCHANGE);
    }

    @Bean
    public Queue nestQueue() {

        Map<String, Object> args = new HashMap<>();

        args.put("x-dead-letter-exchange", "zhurong.nest.dlx");
        args.put("x-dead-letter-routing-key", "dlq");

        return new Queue(
                NestMqConstants.Queue.CUSTOM_STATE,
                true,
                false,
                false,
                args
        );

    }

    @Bean
    public DirectExchange deadLetterExchange() {
        return new DirectExchange("zhurong.nest.dlx");
    }

    @Bean
    public Queue deadLetterQueue() {
        return new Queue("zhurong.nest.state.dlq");
    }

    @Bean
    public Binding dlqBinding() {
        return BindingBuilder
                .bind(deadLetterQueue())
                .to(deadLetterExchange())
                .with("dlq");
    }

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}