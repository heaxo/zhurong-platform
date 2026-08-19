package com.zhurong.platform.custom;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableFeignClients
@EnableScheduling
@MapperScan(basePackages = {
        "com.zhurong.platform.custom.mapper",
})
@ConfigurationPropertiesScan
public class ZhurongPlatformCustomApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZhurongPlatformCustomApplication.class, args);
    }
}
