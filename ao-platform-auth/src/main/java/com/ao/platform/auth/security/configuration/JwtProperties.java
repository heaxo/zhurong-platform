package com.ao.platform.auth.security.configuration;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "security.jwt")
public class JwtProperties {

    private String secret;
}
