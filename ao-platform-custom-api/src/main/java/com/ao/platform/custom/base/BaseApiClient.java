package com.ao.platform.custom.base;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
public abstract class BaseApiClient {

    protected final WebClient webClient;

    protected BaseApiClient(WebClient.Builder builder, String baseUrl) {
        this.webClient = builder
                .baseUrl(baseUrl)
                .build();
    }

    protected <T> T get(String uri, Class<T> clazz) {

        log.debug("HTTP GET {}", uri);

        return webClient.get()
                .uri(uri)
                .retrieve()
                .onStatus(HttpStatusCode::isError, resp ->
                        resp.bodyToMono(String.class)
                                .map(msg -> new RuntimeException("HTTP error: " + msg))
                )
                .bodyToMono(clazz)
                .block();
    }

    protected <T> T post(String uri, Object body, Class<T> clazz) {

        log.debug("HTTP POST {}", uri);

        return webClient.post()
                .uri(uri)
                .bodyValue(body)
                .retrieve()
                .onStatus(HttpStatusCode::isError, resp ->
                        resp.bodyToMono(String.class)
                                .map(msg -> new RuntimeException("HTTP error: " + msg))
                )
                .bodyToMono(clazz)
                .block();
    }
}