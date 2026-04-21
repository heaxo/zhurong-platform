package com.zhurong.platform.custom.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "but-config")
public class ButConfigProperties {
    /**
     * 同步套料报工状态最大天数（默认30天内）
     */
    private Integer syncNestingReportingMaximumDays = 30;
}