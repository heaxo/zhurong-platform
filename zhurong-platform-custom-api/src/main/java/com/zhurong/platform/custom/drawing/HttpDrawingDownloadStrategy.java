package com.zhurong.platform.custom.drawing;

import com.zhurong.platform.base.exception.BusinessException;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

@Component
@Order(10)
public class HttpDrawingDownloadStrategy implements DrawingDownloadStrategy {

    private static final Duration CONNECT_TIMEOUT = Duration.ofSeconds(15);
    private static final Duration READ_TIMEOUT = Duration.ofMinutes(2);

    private final HttpClient httpClient = HttpClient.newBuilder()
            .connectTimeout(CONNECT_TIMEOUT)
            .followRedirects(HttpClient.Redirect.NORMAL)
            .build();

    @Override
    public boolean supports(String drawingPath) {
        return StringUtils.hasText(drawingPath)
                && (drawingPath.regionMatches(true, 0, "http://", 0, 7)
                || drawingPath.regionMatches(true, 0, "https://", 0, 8));
    }

    @Override
    public DrawingDownloadSource open(String drawingPath) throws IOException {
        URI uri;
        try {
            uri = URI.create(drawingPath.trim());
        } catch (IllegalArgumentException ex) {
            throw new BusinessException("DRAWING_HTTP_URI_INVALID: invalid HTTP drawing URI");
        }

        HttpRequest request = HttpRequest.newBuilder(uri)
                .timeout(READ_TIMEOUT)
                .GET()
                .build();
        try {
            HttpResponse<InputStream> response = httpClient.send(request, HttpResponse.BodyHandlers.ofInputStream());
            int statusCode = response.statusCode();
            if (statusCode < 200 || statusCode >= 300) {
                response.body().close();
                throw new BusinessException("DRAWING_HTTP_DOWNLOAD_FAILED: HTTP status=" + statusCode);
            }

            String contentType = response.headers()
                    .firstValue(HttpHeaders.CONTENT_TYPE)
                    .orElse("application/octet-stream");
            String fileName = response.headers()
                    .firstValue(HttpHeaders.CONTENT_DISPOSITION)
                    .map(HttpDrawingDownloadStrategy::fileNameFromContentDisposition)
                    .filter(StringUtils::hasText)
                    .orElseGet(() -> fileNameFromUri(response.uri()));
            return new DrawingDownloadSource(fileName, contentType, response.body());
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            throw new BusinessException("DRAWING_HTTP_DOWNLOAD_INTERRUPTED: HTTP drawing download interrupted");
        }
    }

    private static String fileNameFromUri(URI uri) {
        String path = uri.getPath();
        if (!StringUtils.hasText(path) || path.endsWith("/")) {
            return "download.bin";
        }
        int index = path.lastIndexOf('/');
        return index >= 0 ? path.substring(index + 1) : path;
    }

    private static String fileNameFromContentDisposition(String contentDisposition) {
        String[] parts = contentDisposition.split(";");
        for (String part : parts) {
            String value = part.trim();
            if (value.regionMatches(true, 0, "filename=", 0, 9)) {
                String fileName = value.substring(9).trim();
                if (fileName.startsWith("\"") && fileName.endsWith("\"") && fileName.length() > 1) {
                    fileName = fileName.substring(1, fileName.length() - 1);
                }
                return fileName;
            }
        }
        return "";
    }
}
