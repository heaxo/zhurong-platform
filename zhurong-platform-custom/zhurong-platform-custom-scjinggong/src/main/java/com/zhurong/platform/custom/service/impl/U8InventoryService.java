package com.zhurong.platform.custom.service.impl;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhurong.platform.custom.dto.U8InventoryRequest;
import com.zhurong.platform.custom.dto.U8InventoryResponse;
import com.zhurong.platform.custom.properties.ScjinggongProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.util.List;

@Service
@RequiredArgsConstructor
public class U8InventoryService {

    private final ScjinggongProperties scjinggongProperties;
    private final RestClient.Builder restClientBuilder;
    private final ObjectMapper objectMapper;

    /**
     * 库存查询
     */
    public List<U8InventoryResponse> query(U8InventoryRequest request) {
        String url = scjinggongProperties
                .getU8().getU8InventoryUrl();

        try {
            // 实体转 JSON
            String json = objectMapper.writeValueAsString(request);

            // x-www-form-urlencoded
            // _data={...json...}
            MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
            formData.add("_data", json);

            /**
             * <?xml version="1.0" encoding="utf-8"?>
             * <string xmlns="http://tempuri.org/">{"errcode":"200","errmsg":"","data":[{"itemid":1150,"AutoID":11,"cWhCode":"101","cInvCode":"A01010001","iQuantity":8.000000,"iSoType":0,"isodid":"","enumname":"无","csocode":null,"isoseq":null,"cdemandmemo":""}]}</string>
             * errcode									执行状态  0：执行成功  100：内部错误  200：程序异常
             * errmsg									错误消息，执行成功时为空字符串
             * data									查询结果数据
             * 	itemid
             * 	AutoID
             * 	cWhCode								仓库编码
             * 	cInvCode							物料编码
             * 	iQuantity							在库刷领
             * 	iSoType								需求跟踪方式-编码
             * 	isodid								来源销售订单id
             * 	enumname							需求跟踪方式-名称
             * 	csocode								需求跟踪订单号
             * 	isoseq								需求跟踪行号
             * 	cdemandmemo							需求分类代码
             */
            String responseBody = restClientBuilder
                    .build()
                    .post()
                    .uri(url)
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .body(formData)
                    .retrieve()
                    .body(String.class);

            if (responseBody == null || responseBody.isBlank()) {
                throw new RuntimeException("U8库存查询接口返回为空");
            }

            U8Response response = objectMapper.readValue(extractResponseJson(responseBody), U8Response.class);

            if (response == null) {
                throw new RuntimeException("U8库存查询接口返回为空");
            }

            if (!"0".equals(response.errcode())) {
                throw new RuntimeException(
                        "U8库存查询失败，errcode=%s，errmsg=%s"
                                .formatted(response.errcode(), response.errmsg())
                );
            }

            return response.data() == null ? List.of() : response.data();

        } catch (JsonProcessingException e) {
            throw new RuntimeException("U8库存查询接口JSON处理失败", e);

        } catch (RestClientResponseException e) {
            throw new RuntimeException(
                    "调用U8库存查询接口失败，HTTP状态码=%s，响应=%s"
                            .formatted(
                                    e.getStatusCode(),
                                    e.getResponseBodyAsString()
                            ),
                    e
            );

        } catch (RestClientException e) {
            throw new RuntimeException("调用U8库存查询接口异常：" + e.getMessage(), e);
        }
    }

    private String extractResponseJson(String responseBody) {
        String trimmed = responseBody.trim();
        if (!trimmed.startsWith("<")) {
            return trimmed;
        }

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            factory.setFeature("http://xml.org/sax/features/external-general-entities", false);
            factory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
            factory.setXIncludeAware(false);
            factory.setExpandEntityReferences(false);

            Document document = factory.newDocumentBuilder()
                    .parse(new InputSource(new StringReader(trimmed)));
            return document.getDocumentElement().getTextContent();
        } catch (Exception e) {
            throw new RuntimeException("解析U8库存查询接口XML响应失败", e);
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private record U8Response(
            String errcode,
            String errmsg,
            List<U8InventoryResponse> data
    ) {
    }
}
