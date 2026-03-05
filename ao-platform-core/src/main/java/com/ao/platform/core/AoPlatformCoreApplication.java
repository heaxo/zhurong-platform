package com.ao.platform.core;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan(basePackages = "com.ao.platform.core")
@EnableScheduling
@Slf4j
public class AoPlatformCoreApplication {

    @Value("${test.value}")
    private String value;

    public static void main(String[] args) {
        SpringApplication.run(AoPlatformCoreApplication.class, args);
    }

    @PostConstruct
    public void printValue() {
        log.info("Nacos value = " + value);
    }
}
