package com.zhurong.platform.core.clientimport.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(
        prefix = "zhurong.client-communication",
        name = "enabled",
        havingValue = "true",
        matchIfMissing = false
)
@EnableConfigurationProperties({
        ClientCommunicationProperties.class,
        ImportFileProperties.class
})
public class ClientImportFeatureConfiguration {
}
