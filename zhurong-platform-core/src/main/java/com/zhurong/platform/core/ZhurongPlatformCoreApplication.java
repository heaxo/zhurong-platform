package com.zhurong.platform.core;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan(basePackages = {
        "com.zhurong.platform.core.lantek.mapper",
        "com.zhurong.platform.core.clientimport.mapper"
})
@EnableScheduling
@Slf4j
public class ZhurongPlatformCoreApplication {

    @Value("${test.value}")
    private String value;

    public static void main(String[] args) {
        SpringApplication.run(ZhurongPlatformCoreApplication.class, args);
    }

    @PostConstruct
    public void printValue() {
        log.info("Nacos value = " + value);
    }
}
