package com.zhurong.platform.custom.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhurong.platform.custom.dto.U8StockTransferOrderRequest;
import com.zhurong.platform.custom.properties.ScjinggongProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;

@Service
@RequiredArgsConstructor
public class U8StockTransferOrderService {

    private final ScjinggongProperties scjinggongProperties;
    private final RestClient.Builder restClientBuilder;
    private final ObjectMapper objectMapper;

    /**
     * 调拨单新增并审核
     */
    public boolean add(U8StockTransferOrderRequest request) {
        String url = scjinggongProperties
                .getFeedback()
                .getU8StockTransferOrder();

        try {
            // 实体转 JSON
            String json = objectMapper.writeValueAsString(request);

            // x-www-form-urlencoded
            // data={...json...}
            MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
            formData.add("data", json);

            U8Response response = restClientBuilder
                    .build()
                    .post()
                    .uri(url)
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .body(formData)
                    .retrieve()
                    .body(U8Response.class);

            if (response == null) {
                throw new RuntimeException("U8调拨单接口返回为空");
            }

            if (!"0".equals(response.errcode())) {
                throw new RuntimeException(
                        "U8调拨单新增审核失败，errcode=%s，errmsg=%s"
                                .formatted(response.errcode(), response.errmsg())
                );
            }

            return true;

        } catch (JsonProcessingException e) {
            throw new RuntimeException("U8调拨单请求参数JSON序列化失败", e);

        } catch (RestClientResponseException e) {
            throw new RuntimeException(
                    "调用U8调拨单接口失败，HTTP状态码=%s，响应=%s"
                            .formatted(
                                    e.getStatusCode(),
                                    e.getResponseBodyAsString()
                            ),
                    e
            );

        } catch (RestClientException e) {
            throw new RuntimeException("调用U8调拨单接口异常：" + e.getMessage(), e);
        }
    }

    private record U8Response(
            String errcode,
            String errmsg,
            String id
    ) {
    }
}