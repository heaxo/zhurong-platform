package com.zhurong.platform.custom.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;

import java.time.Duration;

@Component
@RequiredArgsConstructor
@Slf4j
public class JsonHttpClient {

    private static final Duration DEFAULT_CONNECT_TIMEOUT = Duration.ofSeconds(30);
    private static final Duration DEFAULT_READ_TIMEOUT = Duration.ofMinutes(5);

    private final ObjectMapper objectMapper;

    public String postJson(String url, Object payload) {
        return postJson(url, payload, DEFAULT_CONNECT_TIMEOUT, DEFAULT_READ_TIMEOUT);
    }

    public <T> T postJson(String url, Object payload, Class<T> responseType) {
        return postJson(url, payload, responseType, DEFAULT_CONNECT_TIMEOUT, DEFAULT_READ_TIMEOUT);
    }

    public <T> T postJson(String url, Object payload, Class<T> responseType,
                          Duration connectTimeout, Duration readTimeout) {
        String body = postJson(url, payload, connectTimeout, readTimeout);
        return readJson(body, responseType);
    }

    public <T> T postJson(String url, Object payload, TypeReference<T> responseType) {
        return postJson(url, payload, responseType, DEFAULT_CONNECT_TIMEOUT, DEFAULT_READ_TIMEOUT);
    }

    public <T> T postJson(String url, Object payload, TypeReference<T> responseType,
                          Duration connectTimeout, Duration readTimeout) {
        String body = postJson(url, payload, connectTimeout, readTimeout);
        return readJson(body, responseType);
    }

    public <T> T postJson(String url, Object payload, JavaType responseType) {
        return postJson(url, payload, responseType, DEFAULT_CONNECT_TIMEOUT, DEFAULT_READ_TIMEOUT);
    }

    public <T> T postJson(String url, Object payload, JavaType responseType,
                          Duration connectTimeout, Duration readTimeout) {
        String body = postJson(url, payload, connectTimeout, readTimeout);
        return readJson(body, responseType);
    }

    public String postJson(String url, Object payload, Duration connectTimeout, Duration readTimeout) {
        if (!StringUtils.hasText(url)) {
            throw new IllegalArgumentException("HTTP URL must not be blank");
        }

        String requestBody = writeJson(payload);
        log.debug("HTTP POST url={}, payload={}", url, requestBody);

        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(connectTimeout);
        requestFactory.setReadTimeout(readTimeout);

        try {
            String body = RestClient.builder()
                    .requestFactory(requestFactory)
                    .build()
                    .post()
                    .uri(url)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(requestBody)
                    .retrieve()
                    .body(String.class);

            log.debug("HTTP POST response url={}, body={}", url, body);
            return body;
        } catch (RestClientResponseException exception) {
            throw new IllegalStateException(
                    "HTTP request failed, status=%s, body=%s".formatted(
                            exception.getStatusCode(),
                            exception.getResponseBodyAsString()
                    ),
                    exception
            );
        } catch (RestClientException exception) {
            throw new IllegalStateException("HTTP request failed: " + exception.getMessage(), exception);
        }
    }

    private String writeJson(Object payload) {
        if (payload instanceof String json) {
            return json;
        }

        try {
            return objectMapper.copy()
                    .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                    .writeValueAsString(payload);
        } catch (JsonProcessingException exception) {
            throw new IllegalStateException("HTTP request JSON serialize failed, payload=" + payload, exception);
        }
    }

    private <T> T readJson(String body, Class<T> responseType) {
        if (responseType == null) {
            throw new IllegalArgumentException("HTTP response type must not be null");
        }
        if (body == null) {
            return null;
        }

        try {
            return objectMapper.readValue(body, responseType);
        } catch (JsonProcessingException exception) {
            throw new IllegalStateException("HTTP response JSON deserialize failed, body=" + body, exception);
        }
    }

    private <T> T readJson(String body, TypeReference<T> responseType) {
        if (responseType == null) {
            throw new IllegalArgumentException("HTTP response type must not be null");
        }
        if (body == null) {
            return null;
        }

        try {
            return objectMapper.readValue(body, responseType);
        } catch (JsonProcessingException exception) {
            throw new IllegalStateException("HTTP response JSON deserialize failed, body=" + body, exception);
        }
    }

    private <T> T readJson(String body, JavaType responseType) {
        if (responseType == null) {
            throw new IllegalArgumentException("HTTP response type must not be null");
        }
        if (body == null) {
            return null;
        }

        try {
            return objectMapper.readValue(body, responseType);
        } catch (JsonProcessingException exception) {
            throw new IllegalStateException("HTTP response JSON deserialize failed, body=" + body, exception);
        }
    }

}
