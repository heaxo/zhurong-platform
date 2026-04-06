package com.zhurong.platform.custom.configuration;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class InternalFeignInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {

        template.header("X-Internal-Token", "zhurong-platform-internal");

    }
}