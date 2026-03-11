package com.ao.platform.custom.base;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
public abstract class BaseApiClient {

    protected final WebClient webClient;
    protected final ObjectMapper objectMapper;

    protected BaseApiClient(WebClient.Builder builder, ObjectMapper objectMapper, String baseUrl) {
        this.webClient = builder
                .baseUrl(baseUrl)
                .build();
        this.objectMapper = objectMapper;
    }

    protected String toJson(Object obj) {
        if (obj == null) {
            return "null";
        }
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            log.warn("JSON serialize error: {}", obj, e);
            return String.valueOf(obj);
        }
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

        log.info("HTTP POST {} body={}", uri, toJson(body));

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