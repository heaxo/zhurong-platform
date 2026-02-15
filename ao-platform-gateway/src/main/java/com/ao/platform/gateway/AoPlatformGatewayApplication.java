package com.ao.platform.gateway;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class AoPlatformGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(AoPlatformGatewayApplication.class, args);
    }
    @Value("${test.value}")
    private String value;

    @PostConstruct
    public void printValue() {
        log.info("Nacos value = " + value);
    }
}
