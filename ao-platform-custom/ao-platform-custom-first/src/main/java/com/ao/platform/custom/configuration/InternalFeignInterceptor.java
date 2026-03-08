package com.ao.platform.custom.configuration;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InternalFeignInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {

        template.header("X-Internal-Token", "ao-platform-internal");

    }
}