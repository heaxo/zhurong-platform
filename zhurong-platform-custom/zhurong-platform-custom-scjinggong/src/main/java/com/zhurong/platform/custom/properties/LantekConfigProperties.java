package com.zhurong.platform.custom.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "lantek-config")
public class LantekConfigProperties {
    private String install;
}