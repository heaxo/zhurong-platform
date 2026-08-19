package com.zhurong.platform.core.clientimport.controller;

import com.zhurong.platform.base.api.ApiResponse;
import com.zhurong.platform.base.exception.BusinessException;
import com.zhurong.platform.core.clientimport.configuration.ClientCommunicationProperties;
import com.zhurong.platform.core.clientimport.configuration.ConditionalOnClientCommunicationEnabled;
import com.zhurong.platform.core.clientimport.dto.ClientHttpProxyRequest;
import com.zhurong.platform.core.clientimport.dto.ClientHttpProxyResponse;
import com.zhurong.platform.core.clientimport.service.ClientHttpProxyService;
import com.zhurong.platform.core.web.BaseController;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Base64;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/**
 * 前端统一访问的客户端透明代理入口，示例：/client-proxy/xybaoyuan/manufacturing-orders。
 */
@RestController
@RequestMapping("/client-proxy")
@RequiredArgsConstructor
@ConditionalOnClientCommunicationEnabled
public class ClientHttpProxyController extends BaseController {

    private static final Set<String> REQUEST_HEADER_DENY_LIST = Set.of(
            "connection", "keep-alive", "proxy-authenticate", "proxy-authorization",
            "te", "trailer", "transfer-encoding", "upgrade", "host", "content-length",
            "content-type", "accept-encoding", "x-internal-token", "x-client-proxy-command-id"
    );
    private static final Set<String> RESPONSE_HEADER_DENY_LIST = Set.of(
            "connection", "keep-alive", "proxy-authenticate", "proxy-authorization",
            "te", "trailer", "transfer-encoding", "upgrade", "content-length", "content-encoding"
    );

    private final ClientHttpProxyService proxyService;
    private final ClientCommunicationProperties properties;

    @GetMapping("/available")
    public ApiResponse<Boolean> available() {
        return ApiResponse.success(proxyService.available(getCurrentUserId()));
    }

    @RequestMapping(
            value = "/**",
            method = {
                    RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
                    RequestMethod.DELETE, RequestMethod.PATCH
            }
    )
    public ResponseEntity<byte[]> proxy(HttpServletRequest servletRequest) throws IOException {
        ClientHttpProxyRequest request = toProxyRequest(servletRequest);
        ClientHttpProxyResponse response = proxyService.execute(getCurrentUserId(), request);

        HttpHeaders headers = new HttpHeaders();
        if (response.getHeaders() != null) {
            response.getHeaders().forEach((name, values) -> {
                if (StringUtils.hasText(name)
                        && !RESPONSE_HEADER_DENY_LIST.contains(name.toLowerCase(Locale.ROOT))) {
                    headers.put(name, values);
                }
            });
        }
        if (StringUtils.hasText(response.getContentType())) {
            headers.set(HttpHeaders.CONTENT_TYPE, response.getContentType());
        }
        byte[] body = StringUtils.hasText(response.getBodyBase64())
                ? Base64.getDecoder().decode(response.getBodyBase64())
                : new byte[0];
        return new ResponseEntity<>(body, headers, HttpStatusCode.valueOf(response.getStatusCode()));
    }

    private ClientHttpProxyRequest toProxyRequest(HttpServletRequest request) throws IOException {
        String requestUri = request.getRequestURI();
        int prefixIndex = requestUri.indexOf("/client-proxy");
        String targetPath = prefixIndex < 0
                ? null
                : requestUri.substring(prefixIndex + "/client-proxy".length());
        if (!StringUtils.hasText(targetPath) || "/".equals(targetPath)) {
            throw new BusinessException("客户端代理路径不能为空");
        }

        long declaredLength = request.getContentLengthLong();
        long maxBytes = Math.min(
                Math.max(1L, properties.getProxyMaxRequestBytes()),
                Integer.MAX_VALUE - 1L
        );
        if (declaredLength > maxBytes) {
            throw new BusinessException("客户端代理请求体超过允许上限");
        }
        // 即使请求没有 Content-Length（例如 chunked），也只读取上限多一个字节，
        // 避免代理入口先把超大请求完整装入内存后才拒绝。
        byte[] body = request.getInputStream().readNBytes((int) maxBytes + 1);
        if (body.length > maxBytes) {
            throw new BusinessException("客户端代理请求体超过允许上限");
        }

        ClientHttpProxyRequest result = new ClientHttpProxyRequest();
        result.setMethod(request.getMethod());
        result.setPath(targetPath);
        result.setQueryString(request.getQueryString());
        result.setContentType(request.getContentType());
        result.setBodyBase64(body.length == 0 ? null : Base64.getEncoder().encodeToString(body));

        Enumeration<String> names = request.getHeaderNames();
        if (names != null) {
            for (String name : Collections.list(names)) {
                if (REQUEST_HEADER_DENY_LIST.contains(name.toLowerCase(Locale.ROOT))) {
                    continue;
                }
                Enumeration<String> values = request.getHeaders(name);
                result.getHeaders().put(name, values == null ? List.of() : Collections.list(values));
            }
        }
        return result;
    }
}
