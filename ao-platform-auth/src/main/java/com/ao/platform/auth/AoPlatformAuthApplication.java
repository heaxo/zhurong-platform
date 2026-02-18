package com.ao.platform.auth;

import com.ao.platform.auth.security.jwt.JwtSecretGenerator;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@Slf4j
@SpringBootApplication
@MapperScan("com.ao.platform.auth.mapper")
@ConfigurationPropertiesScan
public class AoPlatformAuthApplication {

    @Value("${test.value}")
    private String value;

    public static void main(String[] args) {
        SpringApplication.run(AoPlatformAuthApplication.class, args);
    }

    @PostConstruct
    public void printValue() {

        log.info("Nacos value = " + value);

        String secret = JwtSecretGenerator.generateStrongSecret();
        log.info("secret = {}", secret);
    }
}
