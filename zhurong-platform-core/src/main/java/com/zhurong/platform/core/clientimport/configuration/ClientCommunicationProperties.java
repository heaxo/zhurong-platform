package com.zhurong.platform.core.clientimport.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "zhurong.client-communication")
public class ClientCommunicationProperties {

    // 总开关关闭时，本功能相关Controller、Service和RabbitMQ拓扑都不会创建。
    private boolean enabled = false;

    // 仅在请求未传targetClientId且没有自定义解析规则时使用，禁止随机选择客户端。
    private String defaultClientId;

    // MQ发布失败后的后台补偿间隔；小于等于0时补偿任务会直接跳过。
    private long publishRetryDelayMillis = 60000L;

    /** 超过该时长未收到心跳时，客户端按离线处理。 */
    private long clientOnlineTimeoutSeconds = 90L;

    /** 前端经 core 透明调用客户端本机 HTTP 接口的最长等待时间。 */
    private int proxyTimeoutSeconds = 600;

    /** 单次代理请求体上限，避免任意大请求占满 core 与 RabbitMQ 内存。 */
    private long proxyMaxRequestBytes = 20L * 1024L * 1024L;
}
