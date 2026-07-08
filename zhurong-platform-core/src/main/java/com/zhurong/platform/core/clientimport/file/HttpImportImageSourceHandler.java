package com.zhurong.platform.core.clientimport.file;

import com.zhurong.platform.base.exception.BusinessException;
import com.zhurong.platform.core.clientimport.configuration.ConditionalOnClientCommunicationEnabled;
import com.zhurong.platform.core.clientimport.enums.ImageSourceType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

@Component
@ConditionalOnClientCommunicationEnabled
@RequiredArgsConstructor
public class HttpImportImageSourceHandler implements ImportImageSourceHandler {

    private final StoredFileWriter fileWriter;

    @Override
    public boolean supports(String image) {
        return StringUtils.hasText(image)
                && (image.regionMatches(true, 0, "http://", 0, 7)
                || image.regionMatches(true, 0, "https://", 0, 8));
    }

    @Override
    public StoredImportFile store(String requestId, String image, ImportFileStorageContext context) {
        URI uri = URI.create(image.trim());
        validateUri(uri, context);

        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(context.getProperties().getConnectTimeoutSeconds()))
                .followRedirects(HttpClient.Redirect.NEVER)
                .build();

        URI current = uri;
        for (int redirect = 0; redirect <= context.getProperties().getMaxRedirects(); redirect++) {
            HttpRequest request = HttpRequest.newBuilder(current)
                    .timeout(Duration.ofSeconds(context.getProperties().getReadTimeoutSeconds()))
                    .GET()
                    .build();
            try {
                HttpResponse<InputStream> response = client.send(request, HttpResponse.BodyHandlers.ofInputStream());
                int statusCode = response.statusCode();
                if (statusCode >= 300 && statusCode < 400) {
                    String location = response.headers().firstValue(HttpHeaders.LOCATION).orElseThrow(
                            () -> new BusinessException("HTTP_IMAGE_REDIRECT_WITHOUT_LOCATION: HTTP重定向缺少Location"));
                    current = current.resolve(location);
                    validateUri(current, context);
                    continue;
                }
                if (statusCode < 200 || statusCode >= 300) {
                    throw new BusinessException("HTTP_IMAGE_DOWNLOAD_FAILED: HTTP下载失败，状态码=" + statusCode);
                }
                String contentType = response.headers().firstValue(HttpHeaders.CONTENT_TYPE).orElse("application/octet-stream");
                return fileWriter.write(
                        requestId,
                        image,
                        ImageSourceType.HTTP,
                        fileNameFromUri(current),
                        contentType,
                        response.body()
                );
            } catch (IOException ex) {
                throw new BusinessException("HTTP_IMAGE_DOWNLOAD_FAILED: HTTP下载异常：" + ex.getMessage());
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                throw new BusinessException("HTTP_IMAGE_DOWNLOAD_INTERRUPTED: HTTP下载被中断");
            }
        }

        throw new BusinessException("HTTP_IMAGE_TOO_MANY_REDIRECTS: HTTP重定向次数超过限制");
    }

    private void validateUri(URI uri, ImportFileStorageContext context) {
        if (uri.getUserInfo() != null) {
            throw new BusinessException("HTTP_IMAGE_USERINFO_NOT_ALLOWED: HTTP地址不允许包含用户名密码");
        }
        if (!StringUtils.hasText(uri.getHost())) {
            throw new BusinessException("HTTP_IMAGE_HOST_EMPTY: HTTP地址缺少Host");
        }
        if (!context.getProperties().isAllowPrivateNetwork()) {
            try {
                for (InetAddress address : InetAddress.getAllByName(uri.getHost())) {
                    if (address.isAnyLocalAddress()
                            || address.isLoopbackAddress()
                            || address.isLinkLocalAddress()
                            || address.isSiteLocalAddress()
                            || address.isMulticastAddress()) {
                        throw new BusinessException("HTTP_IMAGE_PRIVATE_ADDRESS_NOT_ALLOWED: HTTP地址不允许指向本机或内网地址");
                    }
                }
            } catch (IOException ex) {
                throw new BusinessException("HTTP_IMAGE_HOST_RESOLVE_FAILED: HTTP地址Host解析失败：" + uri.getHost());
            }
        }
    }

    private String fileNameFromUri(URI uri) {
        String path = uri.getPath();
        if (!StringUtils.hasText(path) || path.endsWith("/")) {
            return "download.bin";
        }
        int index = path.lastIndexOf('/');
        return index >= 0 ? path.substring(index + 1) : path;
    }
}
