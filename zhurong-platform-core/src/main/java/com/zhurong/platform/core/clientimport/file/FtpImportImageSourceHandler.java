package com.zhurong.platform.core.clientimport.file;

import com.zhurong.platform.base.exception.BusinessException;
import com.zhurong.platform.core.clientimport.configuration.ConditionalOnClientCommunicationEnabled;
import com.zhurong.platform.core.clientimport.enums.ImageSourceType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URI;
import java.net.URLConnection;

@Component
@ConditionalOnClientCommunicationEnabled
@RequiredArgsConstructor
public class FtpImportImageSourceHandler implements ImportImageSourceHandler {

    private final StoredFileWriter fileWriter;

    @Override
    public boolean supports(String image) {
        return StringUtils.hasText(image)
                && (image.regionMatches(true, 0, "ftp://", 0, 6)
                || image.regionMatches(true, 0, "ftps://", 0, 7));
    }

    @Override
    public StoredImportFile store(String requestId, String image, ImportFileStorageContext context) {
        try {
            URI uri = URI.create(image.trim());
            validateUri(uri, context);
            URLConnection connection = uri.toURL().openConnection();
            connection.setConnectTimeout(context.getProperties().getConnectTimeoutSeconds() * 1000);
            connection.setReadTimeout(context.getProperties().getReadTimeoutSeconds() * 1000);
            return fileWriter.write(
                    requestId,
                    image,
                    ImageSourceType.FTP,
                    fileNameFromUri(uri),
                    "application/octet-stream",
                    connection.getInputStream()
            );
        } catch (IllegalArgumentException ex) {
            throw new BusinessException("FTP_IMAGE_URI_INVALID: FTP地址格式非法");
        } catch (IOException ex) {
            throw new BusinessException("FTP_IMAGE_DOWNLOAD_FAILED: FTP下载失败：" + ex.getMessage());
        }
    }

    private void validateUri(URI uri, ImportFileStorageContext context) {
        if (!StringUtils.hasText(uri.getHost())) {
            throw new BusinessException("FTP_IMAGE_HOST_EMPTY: FTP地址缺少Host");
        }
        if (context.getProperties().isAllowPrivateNetwork()) {
            return;
        }
        try {
            for (InetAddress address : InetAddress.getAllByName(uri.getHost())) {
                if (address.isAnyLocalAddress()
                        || address.isLoopbackAddress()
                        || address.isLinkLocalAddress()
                        || address.isSiteLocalAddress()
                        || address.isMulticastAddress()) {
                    throw new BusinessException("FTP_IMAGE_PRIVATE_ADDRESS_NOT_ALLOWED: FTP地址不允许指向本机或内网地址");
                }
            }
        } catch (IOException ex) {
            throw new BusinessException("FTP_IMAGE_HOST_RESOLVE_FAILED: FTP地址Host解析失败：" + uri.getHost());
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
