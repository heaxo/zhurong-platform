package com.zhurong.platform.gateway;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class ZhurongPlatformGatewayApplication {

    @Value("${test.value}")
    private String value;

    public static void main(String[] args) {
        SpringApplication.run(ZhurongPlatformGatewayApplication.class, args);
    }

    @PostConstruct
    public void printValue() {
        log.info("Nacos value = " + value);
    }
}
