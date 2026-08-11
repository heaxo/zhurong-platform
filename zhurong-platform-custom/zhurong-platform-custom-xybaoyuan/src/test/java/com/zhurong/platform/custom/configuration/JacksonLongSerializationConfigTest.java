package com.zhurong.platform.custom.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class JacksonLongSerializationConfigTest {

    @Test
    void serializesLongIdentifiersWithoutJavascriptPrecisionLoss() throws Exception {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext()) {
            context.register(JacksonAutoConfiguration.class, JacksonLongSerializationConfig.class);
            context.refresh();
            ObjectMapper objectMapper = context.getBean(ObjectMapper.class);

            String json = objectMapper.writeValueAsString(Map.of("id", 2_087_042_649_941_786_625L));

            assertThat(json).isEqualTo("{\"id\":\"2087042649941786625\"}");
        }
    }
}
