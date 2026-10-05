package com.zhurong.platform.custom.drawing;

import com.zhurong.platform.base.exception.BusinessException;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.ByteArrayInputStream;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@Order(30)
public class Base64DrawingDownloadStrategy implements DrawingDownloadStrategy {

    private static final Pattern DATA_URI = Pattern.compile("^data:([^;,]+)?;base64,(.+)$", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
    private static final Pattern PURE_BASE64 = Pattern.compile("^[A-Za-z0-9+/]+={0,2}$");

    @Override
    public boolean supports(String drawingPath) {
        if (!StringUtils.hasText(drawingPath)) {
            return false;
        }
        String value = drawingPath.trim();
        if (DATA_URI.matcher(value).matches()) {
            return true;
        }
        return isStrictPureBase64(value);
    }

    @Override
    public DrawingDownloadSource open(String drawingPath) {
        String value = drawingPath.trim();
        String contentType = "application/octet-stream";
        String base64 = value;

        Matcher matcher = DATA_URI.matcher(value);
        if (matcher.matches()) {
            contentType = StringUtils.hasText(matcher.group(1)) ? matcher.group(1) : contentType;
            base64 = matcher.group(2).replaceAll("\\s+", "");
        } else if (isStrictPureBase64(value)) {
            base64 = value.replaceAll("\\s+", "");
        } else {
            throw new BusinessException("DRAWING_BASE64_INVALID: invalid Base64 drawing content");
        }

        byte[] bytes;
        try {
            bytes = Base64.getDecoder().decode(base64);
        } catch (IllegalArgumentException ex) {
            throw new BusinessException("DRAWING_BASE64_DECODE_FAILED: Base64 drawing decode failed");
        }

        return new DrawingDownloadSource(
                "base64" + extension(contentType, bytes),
                contentType,
                new ByteArrayInputStream(bytes)
        );
    }

    private boolean isStrictPureBase64(String value) {
        String compact = value.replaceAll("\\s+", "");
        return compact.length() >= 64
                && compact.length() % 4 == 0
                && !compact.contains("\\")
                && !compact.contains(":")
                && PURE_BASE64.matcher(compact).matches();
    }

    private String extension(String contentType, byte[] bytes) {
        if ("image/png".equalsIgnoreCase(contentType)) {
            return ".png";
        }
        if ("image/jpeg".equalsIgnoreCase(contentType) || "image/jpg".equalsIgnoreCase(contentType)) {
            return ".jpg";
        }
        if ("application/pdf".equalsIgnoreCase(contentType)) {
            return ".pdf";
        }
        if ("image/vnd.dxf".equalsIgnoreCase(contentType)
                || "application/dxf".equalsIgnoreCase(contentType)
                || "application/x-dxf".equalsIgnoreCase(contentType)) {
            return ".dxf";
        }
        if ("image/vnd.dwg".equalsIgnoreCase(contentType)
                || "application/acad".equalsIgnoreCase(contentType)
                || "application/x-acad".equalsIgnoreCase(contentType)
                || "application/dwg".equalsIgnoreCase(contentType)
                || "application/x-dwg".equalsIgnoreCase(contentType)) {
            return ".dwg";
        }
        return extensionFromMagic(bytes);
    }

    private String extensionFromMagic(byte[] bytes) {
        if (bytes.length >= 8
                && bytes[0] == (byte) 0x89
                && bytes[1] == 'P'
                && bytes[2] == 'N'
                && bytes[3] == 'G') {
            return ".png";
        }
        if (bytes.length >= 3
                && bytes[0] == (byte) 0xFF
                && bytes[1] == (byte) 0xD8
                && bytes[2] == (byte) 0xFF) {
            return ".jpg";
        }
        if (bytes.length >= 4
                && bytes[0] == '%'
                && bytes[1] == 'P'
                && bytes[2] == 'D'
                && bytes[3] == 'F') {
            return ".pdf";
        }
        if (bytes.length >= 4
                && bytes[0] == 'A'
                && bytes[1] == 'C'
                && bytes[2] == '1'
                && bytes[3] == '0') {
            return ".dwg";
        }
        return ".bin";
    }
}
