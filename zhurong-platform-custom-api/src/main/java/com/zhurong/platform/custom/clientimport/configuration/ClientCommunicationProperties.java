package com.zhurong.platform.custom.clientimport.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "zhurong.client-communication")
public class ClientCommunicationProperties {

    private boolean enabled = false;

    /**
     * 当前客户端唯一编号。core按该值生成专属队列和routingKey，禁止多个客户端共用同一个clientId。
     */
    private String clientId;

    private String userName;

    private String version = "1.0.0";

    private long heartbeatSeconds = 30;
}
