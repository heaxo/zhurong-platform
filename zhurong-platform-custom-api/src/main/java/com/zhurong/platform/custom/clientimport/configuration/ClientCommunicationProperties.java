package com.zhurong.platform.custom.clientimport.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.StringUtils;

import com.zhurong.platform.core.clientimport.mq.ClientIds;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Getter
@Setter
@ConfigurationProperties(prefix = "zhurong.client-communication")
public class ClientCommunicationProperties {

    private boolean enabled = false;

    /**
     * 当前客户端唯一编号。core按该值生成专属队列和routingKey，禁止多个客户端共用同一个clientId。
     */
    private String clientId;

    /**
     * client-id 未显式配置时，使用当前 Windows 主机名作为客户端唯一标识。
     * 客户定制模块可以开启此项，原有手工 client-id 的项目不受影响。
     */
    private boolean clientIdFromHostname = false;

    private String userName;

    private String version = "1.0.0";

    private long heartbeatSeconds = 30;

    /** 客户端收到透明代理命令后访问的本机 custom 地址；为空时使用 127.0.0.1 和 server.port。 */
    private String localBaseUrl;

    private int proxyConnectTimeoutSeconds = 10;

    private long proxyMaxResponseBytes = 50L * 1024L * 1024L;

    public String getClientId() {
        if (StringUtils.hasText(clientId)) {
            return ClientIds.normalize(clientId);
        }
        if (!clientIdFromHostname) {
            return null;
        }
        try {
            return ClientIds.normalize(InetAddress.getLocalHost().getHostName());
        } catch (UnknownHostException ex) {
            throw new IllegalStateException("无法读取当前客户端主机名", ex);
        }
    }
}
