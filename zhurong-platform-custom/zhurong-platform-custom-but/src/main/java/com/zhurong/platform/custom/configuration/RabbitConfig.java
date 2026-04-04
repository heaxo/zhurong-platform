package com.zhurong.platform.custom.configuration;

import com.zhurong.platform.core.lantek.constants.NestMqConstants;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.RetryInterceptorBuilder;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.retry.RejectAndDontRequeueRecoverer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.interceptor.RetryOperationsInterceptor;

import java.util.HashMap;
import java.util.Map;

@EnableRabbit
@Configuration
public class RabbitConfig {
    public static final String DLX = "zhurong.nest.dlx";
    public static final String DLQ = "zhurong.nest.state.dlq";
    public static final String DLQ_ROUTING_KEY = "dlq";
    /**
     * Exchange
     */
    @Bean
    public TopicExchange nestExchange() {
        return new TopicExchange(NestMqConstants.EXCHANGE, true, false);
    }

    /**
     * Dead Letter Exchange
     */
    @Bean
    public DirectExchange deadLetterExchange() {
        return new DirectExchange(DLX, true, false);
    }

    /**
     * 业务队列
     */
    @Bean
    public Queue nestQueue() {

        Map<String, Object> args = new HashMap<>();

        args.put("x-dead-letter-exchange", DLX);
        args.put("x-dead-letter-routing-key", DLQ_ROUTING_KEY);

        return new Queue(NestMqConstants.Queue.CUSTOM_STATE, true, false, false, args);
    }

    /**
     * DLQ
     */
    @Bean
    public Queue deadLetterQueue() {
        return new Queue(DLQ, true);
    }

    /**
     * 业务绑定
     */
    @Bean
    public Binding nestBinding() {
        return BindingBuilder.bind(nestQueue())
                .to(nestExchange())
                .with(NestMqConstants.RoutingKey.STATE_CHANGED);
    }

    /**
     * DLQ绑定
     */
    @Bean
    public Binding dlqBinding() {
        return BindingBuilder.bind(deadLetterQueue())
                .to(deadLetterExchange())
                .with(DLQ_ROUTING_KEY);
    }

    /**
     * JSON converter
     */
    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    /**
     * 重试拦截器
     */
    @Bean
    public RetryOperationsInterceptor rabbitRetryInterceptor() {

        return RetryInterceptorBuilder.stateless()
                .maxAttempts(3)
                .backOffOptions(1000, 2.0, 10000)
                .recoverer(new RejectAndDontRequeueRecoverer())
                .build();
    }

    /**
     * Listener Container
     */
    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(
            ConnectionFactory connectionFactory,
            Jackson2JsonMessageConverter converter,
            RetryOperationsInterceptor rabbitRetryInterceptor
    ) {

        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();

        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(converter);

        factory.setConcurrentConsumers(3);
        factory.setMaxConcurrentConsumers(10);
        factory.setPrefetchCount(10);

        factory.setDefaultRequeueRejected(false);

        factory.setAdviceChain(rabbitRetryInterceptor);

        return factory;
    }

}