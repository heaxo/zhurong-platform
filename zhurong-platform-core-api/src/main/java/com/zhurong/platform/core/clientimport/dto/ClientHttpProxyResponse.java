package com.zhurong.platform.core.clientimport.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/** 客户端本机 custom HTTP 接口的原始响应。 */
@Data
public class ClientHttpProxyResponse implements Serializable {

    private Integer statusCode;

    private String contentType;

    private Map<String, List<String>> headers = new LinkedHashMap<>();

    private String bodyBase64;
}
