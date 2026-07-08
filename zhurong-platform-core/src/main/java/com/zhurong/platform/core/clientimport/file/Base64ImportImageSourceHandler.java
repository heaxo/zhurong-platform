package com.zhurong.platform.core.clientimport.file;

import com.zhurong.platform.base.exception.BusinessException;
import com.zhurong.platform.core.clientimport.configuration.ConditionalOnClientCommunicationEnabled;
import com.zhurong.platform.core.clientimport.enums.ImageSourceType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.ByteArrayInputStream;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@ConditionalOnClientCommunicationEnabled
@RequiredArgsConstructor
public class Base64ImportImageSourceHandler implements ImportImageSourceHandler {

    private static final Pattern DATA_URI = Pattern.compile("^data:([^;,]+)?;base64,(.+)$", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
    private static final Pattern PURE_BASE64 = Pattern.compile("^[A-Za-z0-9+/]+={0,2}$");

    private final StoredFileWriter fileWriter;

    @Override
    public boolean supports(String image) {
        if (!StringUtils.hasText(image)) {
            return false;
        }
        String value = image.trim();
        if (DATA_URI.matcher(value).matches()) {
            return true;
        }
        return isStrictPureBase64(value);
    }

    @Override
    public StoredImportFile store(String requestId, String image, ImportFileStorageContext context) {
        String value = image.trim();
        String contentType = "application/octet-stream";
        String base64 = value;
        Matcher matcher = DATA_URI.matcher(value);
        if (matcher.matches()) {
            contentType = StringUtils.hasText(matcher.group(1)) ? matcher.group(1) : contentType;
            base64 = matcher.group(2).replaceAll("\\s+", "");
        } else if (!isStrictPureBase64(value)) {
            throw new BusinessException("BASE64_IMAGE_INVALID: Base64内容格式非法");
        } else {
            base64 = value.replaceAll("\\s+", "");
        }

        long estimatedSize = base64.length() * 3L / 4L;
        if (estimatedSize > context.getProperties().getMaxFileSize()) {
            throw new BusinessException("IMPORT_FILE_TOO_LARGE: Base64文件超过最大限制");
        }

        try {
            byte[] bytes = Base64.getDecoder().decode(base64);
            if (bytes.length > context.getProperties().getMaxFileSize()) {
                throw new BusinessException("IMPORT_FILE_TOO_LARGE: Base64文件超过最大限制");
            }
            return fileWriter.write(
                    requestId,
                    image,
                    ImageSourceType.BASE64,
                    "base64" + extension(contentType),
                    contentType,
                    new ByteArrayInputStream(bytes)
            );
        } catch (IllegalArgumentException ex) {
            throw new BusinessException("BASE64_IMAGE_INVALID: Base64内容解码失败");
        }
    }

    private boolean isStrictPureBase64(String value) {
        String compact = value.replaceAll("\\s+", "");
        return compact.length() >= 64
                && compact.length() % 4 == 0
                && !compact.contains("\\")
                && !compact.contains(":")
                && PURE_BASE64.matcher(compact).matches();
    }

    private String extension(String contentType) {
        if ("image/png".equalsIgnoreCase(contentType)) {
            return ".png";
        }
        if ("image/jpeg".equalsIgnoreCase(contentType)) {
            return ".jpg";
        }
        if ("image/vnd.dxf".equalsIgnoreCase(contentType) || "application/dxf".equalsIgnoreCase(contentType)) {
            return ".dxf";
        }
        return ".bin";
    }
}
