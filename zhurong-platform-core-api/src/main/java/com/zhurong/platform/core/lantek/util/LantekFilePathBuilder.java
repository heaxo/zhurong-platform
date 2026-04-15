package com.zhurong.platform.core.lantek.util;

import java.nio.file.Path;
import java.nio.file.Paths;

public final class LantekFilePathBuilder {

    private LantekFilePathBuilder() {
    }

    public static String combine(String rootDir, String relativePath) {
        if (rootDir == null || rootDir.isBlank() || relativePath == null || relativePath.isBlank()) {
            return null;
        }
        Path path = Paths.get(rootDir, relativePath);
        return path.normalize().toString();
    }
}