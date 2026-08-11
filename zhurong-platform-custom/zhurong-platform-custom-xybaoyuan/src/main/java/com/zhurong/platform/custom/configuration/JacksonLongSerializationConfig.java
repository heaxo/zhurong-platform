package com.zhurong.platform.custom.configuration;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Serializes Java Long values as JSON strings so browser clients never lose
 * precision when an identifier is greater than Number.MAX_SAFE_INTEGER.
 */
@Configuration
public class JacksonLongSerializationConfig {

    @Bean
    public Module longToStringJacksonModule() {
        SimpleModule module = new SimpleModule("xybaoyuan-long-to-string");
        module.addSerializer(Long.class, ToStringSerializer.instance);
        module.addSerializer(Long.TYPE, ToStringSerializer.instance);
        return module;
    }
}
