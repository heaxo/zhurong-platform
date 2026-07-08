package com.zhurong.platform.core.clientimport.file;

import org.springframework.util.StringUtils;

import java.net.URI;

public final class ImageSourceSanitizer {

    private ImageSourceSanitizer() {
    }

    public static String mask(String image) {
        if (!StringUtils.hasText(image)) {
            return image;
        }
        String value = image.trim();
        if (value.regionMatches(true, 0, "data:", 0, 5)) {
            int comma = value.indexOf(',');
            String prefix = comma >= 0 ? value.substring(0, Math.min(comma, 120)) : "data:*";
            return prefix + ",<base64 omitted>";
        }
        if (isLikelyPureBase64(value)) {
            return "<base64 omitted length=" + value.length() + ">";
        }
        if (value.regionMatches(true, 0, "ftp://", 0, 6)
                || value.regionMatches(true, 0, "ftps://", 0, 7)
                || value.regionMatches(true, 0, "http://", 0, 7)
                || value.regionMatches(true, 0, "https://", 0, 8)) {
            try {
                URI uri = URI.create(value);
                String userInfo = uri.getRawUserInfo();
                if (!StringUtils.hasText(userInfo)) {
                    return value;
                }
                return value.replace(userInfo + "@", "***@");
            } catch (IllegalArgumentException ex) {
                return "<invalid uri>";
            }
        }
        return value;
    }

    private static boolean isLikelyPureBase64(String value) {
        return value.length() >= 128
                && value.length() % 4 == 0
                && value.matches("^[A-Za-z0-9+/]+={0,2}$");
    }
}
