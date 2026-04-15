package com.zhurong.platform.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "lantek-config")
public class LantekConfigProperties {

    /**
     * 安装路径
     */
    private String install = "C:\\Lantek";
}