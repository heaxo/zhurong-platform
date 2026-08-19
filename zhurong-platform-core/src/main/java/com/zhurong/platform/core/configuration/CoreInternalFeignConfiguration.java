package com.zhurong.platform.core.configuration;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;

/** core 调用平台内部服务时使用的内部认证头。 */
public class CoreInternalFeignConfiguration {

    @Bean
    RequestInterceptor coreInternalRequestInterceptor() {
        return template -> template.header("X-Internal-Token", "zhurong-platform-internal");
    }
}
