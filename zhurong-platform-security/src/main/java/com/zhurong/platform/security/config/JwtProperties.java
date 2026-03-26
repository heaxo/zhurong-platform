package com.zhurong.platform.security.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "security.jwt")
@Data
public class JwtProperties {

    /**
     * Base64 密钥
     */
    private String secret;

    /**
     * accessToken 过期时间（毫秒）
     */
    private long accessExpire = 7 * 24 * 60 * 60 * 1000L;
}