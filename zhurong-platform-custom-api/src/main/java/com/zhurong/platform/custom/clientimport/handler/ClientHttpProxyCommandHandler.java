package com.zhurong.platform.custom.clientimport.handler;

import com.zhurong.platform.core.clientimport.dto.ClientHttpProxyRequest;
import com.zhurong.platform.core.clientimport.dto.ClientHttpProxyResponse;
import com.zhurong.platform.core.clientimport.mq.ClientCommandTypes;
import com.zhurong.platform.custom.clientimport.configuration.ClientCommunicationProperties;
import com.zhurong.platform.custom.clientimport.configuration.ConditionalOnClientCommunicationEnabled;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 在 Windows 客户端把 core 代理命令转成对本机 custom HTTP 服务的调用。
 */
@Component
@ConditionalOnClientCommunicationEnabled
public class ClientHttpProxyCommandHandler implements ClientCommandHandler<ClientHttpProxyRequest> {

    private static final Set<String> HEADER_DENY_LIST = Set.of(
            "connection", "keep-alive", "proxy-authenticate", "proxy-authorization",
            "te", "trailer", "transfer-encoding", "upgrade", "host", "content-length",
            "content-type", "accept-encoding", "x-internal-token", "x-client-proxy-command-id"
    );
    private static final int MAX_MEMORY_RESULTS = 1000;

    private final ClientCommunicationProperties properties;
    private final Environment environment;
    private final ClientCommandExecutionStore executionStore;
    private final HttpClient httpClient;
    private final Map<String, ClientCommandResult> memoryResults = new ConcurrentHashMap<>();

    public ClientHttpProxyCommandHandler(
            ClientCommunicationProperties properties,
            Environment environment,
            ObjectProvider<ClientCommandExecutionStore> executionStoreProvider
    ) {
        this.properties = properties;
        this.environment = environment;
        this.executionStore = executionStoreProvider.getIfAvailable();
        this.httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(Math.max(1, properties.getProxyConnectTimeoutSeconds())))
                .followRedirects(HttpClient.Redirect.NEVER)
                .build();
    }

    @Override
    public String commandType() {
        return ClientCommandTypes.HTTP_PROXY;
    }

    @Override
    public Class<ClientHttpProxyRequest> payloadType() {
        return ClientHttpProxyRequest.class;
    }

    @Override
    public ClientCommandResult execute(String commandId, ClientHttpProxyRequest payload) {
        if (executionStore != null) {
            return executionStore.executeOnce(
                    commandId,
                    commandType(),
                    recovering -> executeLocal(commandId, payload)
            );
        }
        ClientCommandResult cached = memoryResults.get(commandId);
        if (cached != null) {
            return cached;
        }
        ClientCommandResult result = executeLocal(commandId, payload);
        if (memoryResults.size() >= MAX_MEMORY_RESULTS) {
            memoryResults.clear();
        }
        memoryResults.put(commandId, result);
        return result;
    }

    private ClientCommandResult executeLocal(String commandId, ClientHttpProxyRequest payload) {
        validate(payload);
        try {
            byte[] requestBody = StringUtils.hasText(payload.getBodyBase64())
                    ? Base64.getDecoder().decode(payload.getBodyBase64())
                    : new byte[0];
            HttpRequest.Builder builder = HttpRequest.newBuilder(buildUri(payload))
                    .timeout(Duration.ofSeconds(Math.min(600, Math.max(1, payload.getTimeoutSeconds()))))
                    .header("X-Client-Proxy-Command-Id", commandId);
            if (StringUtils.hasText(payload.getContentType())) {
                builder.header("Content-Type", payload.getContentType());
            }
            if (payload.getHeaders() != null) {
                payload.getHeaders().forEach((name, values) -> addHeaders(builder, name, values));
            }
            HttpRequest.BodyPublisher bodyPublisher = requestBody.length == 0
                    ? HttpRequest.BodyPublishers.noBody()
                    : HttpRequest.BodyPublishers.ofByteArray(requestBody);
            builder.method(payload.getMethod().trim().toUpperCase(Locale.ROOT), bodyPublisher);

            HttpResponse<InputStream> localResponse = httpClient.send(
                    builder.build(),
                    HttpResponse.BodyHandlers.ofInputStream()
            );
            long maxResponseBytes = Math.min(
                    Math.max(1L, properties.getProxyMaxResponseBytes()),
                    Integer.MAX_VALUE - 1L
            );
            byte[] responseBody;
            try (InputStream responseStream = localResponse.body()) {
                responseBody = responseStream.readNBytes((int) maxResponseBytes + 1);
            }
            if (responseBody.length > maxResponseBytes) {
                return ClientCommandResult.failed("客户端本机HTTP响应超过允许上限");
            }

            ClientHttpProxyResponse response = new ClientHttpProxyResponse();
            response.setStatusCode(localResponse.statusCode());
            response.setContentType(localResponse.headers().firstValue("Content-Type").orElse(null));
            response.setHeaders(new LinkedHashMap<>(localResponse.headers().map()));
            response.setBodyBase64(responseBody.length == 0
                    ? null
                    : Base64.getEncoder().encodeToString(responseBody));
            // 本机接口返回 4xx/5xx 仍是一次成功的代理传输，由 core 原样返回给浏览器。
            return ClientCommandResult.success("客户端本机HTTP请求完成", response);
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
            return ClientCommandResult.failed("客户端本机HTTP请求被中断");
        } catch (Exception exception) {
            return ClientCommandResult.failed("客户端本机HTTP请求失败: " + safeMessage(exception));
        }
    }

    private URI buildUri(ClientHttpProxyRequest payload) {
        String baseUrl = properties.getLocalBaseUrl();
        if (!StringUtils.hasText(baseUrl)) {
            String port = environment.getProperty("server.port", "8080");
            baseUrl = "http://127.0.0.1:" + port;
        }
        String path = payload.getPath().startsWith("/") ? payload.getPath() : "/" + payload.getPath();
        String query = payload.getQueryString();
        return URI.create(baseUrl.trim().replaceAll("/+$", "")
                + path
                + (StringUtils.hasText(query) ? "?" + query.replaceFirst("^\\?", "") : ""));
    }

    private static void validate(ClientHttpProxyRequest payload) {
        if (payload == null || !StringUtils.hasText(payload.getMethod())) {
            throw new IllegalArgumentException("客户端代理HTTP方法不能为空");
        }
        if (!StringUtils.hasText(payload.getPath()) || !payload.getPath().startsWith("/")) {
            throw new IllegalArgumentException("客户端代理路径不合法");
        }
        if (payload.getTimeoutSeconds() == null || payload.getTimeoutSeconds() <= 0) {
            throw new IllegalArgumentException("客户端代理超时时间不合法");
        }
    }

    private static void addHeaders(HttpRequest.Builder builder, String name, List<String> values) {
        if (!StringUtils.hasText(name) || HEADER_DENY_LIST.contains(name.toLowerCase(Locale.ROOT))
                || values == null) {
            return;
        }
        values.stream().filter(StringUtils::hasText).forEach(value -> builder.header(name, value));
    }

    private static String safeMessage(Exception exception) {
        return StringUtils.hasText(exception.getMessage())
                ? exception.getMessage()
                : exception.getClass().getSimpleName();
    }
}
