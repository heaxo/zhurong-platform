package com.zhurong.platform.core.clientimport.file;

import org.springframework.util.StringUtils;

public final class SafeFileNames {

    private SafeFileNames() {
    }

    public static String sanitizeFileName(String fileName, String fallback) {
        String candidate = StringUtils.hasText(fileName) ? fileName : fallback;
        candidate = candidate.replace('\\', '/');
        int lastSlash = candidate.lastIndexOf('/');
        if (lastSlash >= 0) {
            candidate = candidate.substring(lastSlash + 1);
        }
        candidate = candidate.replaceAll("[\\r\\n\\t]", "");
        candidate = candidate.replaceAll("[\\\\/:*?\"<>|]", "_");
        candidate = candidate.replace("..", "_");
        candidate = candidate.trim();
        if (!StringUtils.hasText(candidate)) {
            return fallback;
        }
        return candidate.length() > 180 ? candidate.substring(0, 180) : candidate;
    }

    public static String sanitizePathSegment(String value, String fallback) {
        String candidate = StringUtils.hasText(value) ? value : fallback;
        candidate = candidate.replaceAll("[\\\\/:*?\"<>|\\s]+", "_");
        candidate = candidate.replace("..", "_");
        candidate = candidate.trim();
        if (!StringUtils.hasText(candidate)) {
            return fallback;
        }
        return candidate.length() > 120 ? candidate.substring(0, 120) : candidate;
    }
}
