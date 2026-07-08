package com.zhurong.platform.custom.clientimport.mq;

import com.zhurong.platform.custom.clientimport.configuration.ConditionalOnClientCommunicationEnabled;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@ConditionalOnClientCommunicationEnabled
@RequiredArgsConstructor
public class ClientRabbitStartupVerifier implements SmartInitializingSingleton {

    private final ConnectionFactory connectionFactory;
    private final RabbitProperties rabbitProperties;

    @Override
    public void afterSingletonsInstantiated() {
        /*
         * RabbitTemplate默认懒连接，首次发送心跳时才会暴露连接失败。
         * 客户端通信启用后，启动阶段必须主动验证RabbitMQ可连接；运行中断开则交给客户端重连和后续重试处理。
         */
        try (Connection ignored = connectionFactory.createConnection()) {
            log.info("客户端通信RabbitMQ启动连接检查通过 {}", rabbitConnectionInfo());
        } catch (Exception ex) {
            throw new IllegalStateException("客户端通信RabbitMQ连接失败，请检查spring.rabbitmq.host/port/username/password/virtual-host，当前配置 "
                    + rabbitConnectionInfo(), ex);
        }
    }

    private String rabbitConnectionInfo() {
        return "addresses=" + rabbitProperties.getAddresses()
                + ", host=" + rabbitProperties.getHost()
                + ", port=" + rabbitProperties.getPort()
                + ", username=" + rabbitProperties.getUsername()
                + ", virtualHost=" + rabbitProperties.getVirtualHost();
    }
}
