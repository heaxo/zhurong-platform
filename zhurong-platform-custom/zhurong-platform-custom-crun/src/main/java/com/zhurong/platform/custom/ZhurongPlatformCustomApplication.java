package com.zhurong.platform.custom;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@Slf4j
@SpringBootApplication
@EnableFeignClients
public class ZhurongPlatformCustomApplication {

    @Value("${test.value}")
    private String value;

    public static void main(String[] args) {
        SpringApplication.run(ZhurongPlatformCustomApplication.class, args);
    }

    @PostConstruct
    public void printValue() {
        log.info("Nacos value = " + value);
    }
}
