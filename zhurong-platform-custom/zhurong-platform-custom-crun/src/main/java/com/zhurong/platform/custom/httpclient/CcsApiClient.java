package com.zhurong.platform.custom.httpclient;

import com.zhurong.platform.base.api.ApiResponse;
import com.zhurong.platform.custom.base.BaseApiClient;
import com.zhurong.platform.custom.dto.OrderNestingRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@Slf4j
public class CcsApiClient extends BaseApiClient {

    @Value("${third-api.ccs.accept-order-url}")
    private String acceptOrderUrl;

    public CcsApiClient(WebClient.Builder builder,
                        ObjectMapper objectMapper,
                        @Value("${third-api.ccs.base-url}") String baseUrl) {
        super(builder, objectMapper, baseUrl);
    }

    public ApiResponse requestAcceptOrder(OrderNestingRequest dto) {
        return post(acceptOrderUrl, dto, ApiResponse.class);
    }
}