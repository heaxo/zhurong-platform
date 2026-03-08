package com.ao.platform.custom.httpclient;

import com.ao.platform.base.api.ApiResponse;
import com.ao.platform.custom.base.BaseApiClient;
import com.ao.platform.custom.dto.AcceptOrderRequestDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class CcsApiClient extends BaseApiClient {

    @Value("${third-api.ccs.accept-order-url}")
    private String acceptOrderUrl;

    public CcsApiClient(WebClient.Builder builder,
                        @Value("${third-api.ccs.base-url}") String baseUrl) {
        super(builder, baseUrl);
    }

    public ApiResponse requestAcceptOrder(AcceptOrderRequestDTO dto) {
        return post(acceptOrderUrl, dto, ApiResponse.class);
    }
}