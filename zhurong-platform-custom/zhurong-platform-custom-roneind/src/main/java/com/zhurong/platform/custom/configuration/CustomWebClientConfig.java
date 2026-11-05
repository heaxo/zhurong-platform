package com.zhurong.platform.custom.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(WebClientConfig.class)
public class CustomWebClientConfig {


}