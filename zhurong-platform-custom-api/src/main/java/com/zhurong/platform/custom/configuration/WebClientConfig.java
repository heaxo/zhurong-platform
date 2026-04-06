package com.zhurong.platform.custom.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;

public class WebClientConfig {

    @Bean
    public WebClient.Builder webClientBuilder() {

        HttpClient httpClient = HttpClient.create()
                .compress(true)
                .responseTimeout(Duration.ofSeconds(10));

        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient));
    }
}