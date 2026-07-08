package com.zhurong.platform.custom;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.util.StringUtils;

@Slf4j
@SpringBootApplication
@EnableFeignClients(basePackages = "com.zhurong.platform.custom")
@EnableScheduling
@MapperScan(basePackages = {
        "com.zhurong.platform.custom.mapper",
})
@ConfigurationPropertiesScan
public class ZhurongPlatformCustomApplication {

    @Value("${test.value:}")
    private String value;

    public static void main(String[] args) {
        SpringApplication.run(ZhurongPlatformCustomApplication.class, args);
    }

    @PostConstruct
    public void printValue() {
        if (StringUtils.hasText(value)) {
            log.info("Nacos value = {}", value);
        }
    }
}
