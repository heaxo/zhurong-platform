package com.zhurong.platform.core.clientimport.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * core 转发到目标客户端的透明 HTTP 请求。请求体采用 Base64，兼容 JSON、表单和二进制载荷。
 */
@Data
public class ClientHttpProxyRequest implements Serializable {

    private String method;

    private String path;

    private String queryString;

    private Map<String, List<String>> headers = new LinkedHashMap<>();

    private String contentType;

    private String bodyBase64;

    private Integer timeoutSeconds;
}
