package com.zhurong.platform.core.clientimport.mq;

import com.zhurong.platform.core.clientimport.configuration.ConditionalOnClientCommunicationEnabled;
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
public class ClientImportRabbitStartupVerifier implements SmartInitializingSingleton {

    private final ConnectionFactory connectionFactory;
    private final RabbitProperties rabbitProperties;

    @Override
    public void afterSingletonsInstantiated() {
        /*
         * RabbitMQ连接默认懒创建。启用客户端通信后，core启动阶段必须先确认MQ可用，
         * 避免接口已接收数据但首次派发时才发现MQ地址或端口配置错误。
         */
        try (Connection ignored = connectionFactory.createConnection()) {
            log.info("客户端导入RabbitMQ启动连接检查通过 {}", rabbitConnectionInfo());
        } catch (Exception ex) {
            throw new IllegalStateException("客户端导入RabbitMQ连接失败，请检查spring.rabbitmq.host/port/username/password/virtual-host，当前配置 "
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
