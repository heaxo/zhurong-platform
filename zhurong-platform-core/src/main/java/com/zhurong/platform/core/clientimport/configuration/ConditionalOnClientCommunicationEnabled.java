package com.zhurong.platform.core.clientimport.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@ConditionalOnProperty(
        prefix = "zhurong.client-communication",
        name = "enabled",
        havingValue = "true",
        matchIfMissing = false
)
public @interface ConditionalOnClientCommunicationEnabled {
}
