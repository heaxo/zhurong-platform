package com.zhurong.platform.custom.clientimport.configuration;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@EnableConfigurationProperties(ClientCommunicationProperties.class)
public class ClientCommunicationConfiguration {
}
