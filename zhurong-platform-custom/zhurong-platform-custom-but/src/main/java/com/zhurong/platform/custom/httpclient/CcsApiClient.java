package com.zhurong.platform.custom.httpclient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhurong.platform.base.api.ApiResponse;
import com.zhurong.platform.custom.web.BaseApiClient;
import com.zhurong.platform.custom.dto.OrderNestingRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@Slf4j
public class CcsApiClient extends BaseApiClient {

    private String acceptOrderUrl;

    public CcsApiClient(WebClient.Builder builder,
                        ObjectMapper objectMapper) {
        super(builder, objectMapper, "");
    }

    public ApiResponse requestAcceptOrder(OrderNestingRequest dto) {
        return post(acceptOrderUrl, dto, ApiResponse.class);
    }
}