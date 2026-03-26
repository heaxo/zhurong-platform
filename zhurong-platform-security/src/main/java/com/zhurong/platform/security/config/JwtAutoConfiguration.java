package com.zhurong.platform.security.config;

import com.zhurong.platform.security.jwt.JwtProvider;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(JwtProperties.class)
public class JwtAutoConfiguration {

    @Bean
    public JwtProvider jwtProvider(JwtProperties properties) {
        return new JwtProvider(properties);
    }
}