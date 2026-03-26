package com.zhurong.platform.base.util;

import java.nio.file.Path;
import java.nio.file.Paths;

public final class PathResolver {

    private PathResolver() {}

    // ========================
    // 判断路径类型
    // ========================

    /** 是否 Linux 绝对路径 */
    public static boolean isLinuxAbsolute(String path) {
        return path != null && path.startsWith("/");
    }

    /** 是否 Windows 本地绝对路径（C:\xxx） */
    public static boolean isWindowsAbsolute(String path) {
        return path != null && path.matches("^[a-zA-Z]:\\\\.*");
    }

    /** 是否 UNC 路径（\\server\share） */
    public static boolean isUNCPath(String path) {
        return path != null && path.startsWith("\\\\");
    }

    /** 是否绝对路径（统一判断） */
    public static boolean isAbsolute(String path) {
        return isLinuxAbsolute(path) || isWindowsAbsolute(path) || isUNCPath(path);
    }

    /** 是否相对路径 */
    public static boolean isRelative(String path) {
        return !isAbsolute(path);
    }

    // ========================
    // 路径拼接（核心）
    // ========================

    /**
     * 解析最终路径：
     * - relative 是绝对路径 → 直接返回
     * - relative 是相对路径 → 拼接 base
     */
    public static String resolve(String basePath, String relativePath) {

        if (relativePath == null || relativePath.isBlank()) {
            return normalize(basePath);
        }

        // 已经是绝对路径
        if (isAbsolute(relativePath)) {
            return normalize(relativePath);
        }

        // basePath 是 UNC 或 Windows
        if (isUNCPath(basePath) || isWindowsAbsolute(basePath)) {
            return normalize(joinWindowsPath(basePath, relativePath));
        }

        // Linux / 其他 → 用 Path API
        Path path = Paths.get(basePath, relativePath).toAbsolutePath();
        return normalize(path.toString());
    }

    // ========================
    // 内部方法
    // ========================

    /** Windows / UNC 拼接（不能用 Paths.get） */
    private static String joinWindowsPath(String base, String child) {
        String b = trimEndSeparator(base);
        String c = trimStartSeparator(child);
        return b + "\\" + c;
    }

    /** 去掉末尾分隔符 */
    private static String trimEndSeparator(String path) {
        return path.replaceAll("[/\\\\]+$", "");
    }

    /** 去掉开头分隔符 */
    private static String trimStartSeparator(String path) {
        return path.replaceAll("^[/\\\\]+", "");
    }

    public static String normalize(String path) {
        if (path == null) {
            return null;
        }

        // UNC 路径 → 保持 Windows 风格
        if (isUNCPath(path)) {
            return path.replace("/", "\\");
        }

        // Windows 本地路径
        if (isWindowsAbsolute(path)) {
            return path.replace("/", "\\");
        }

        // Linux → 用 /
        return path.replace("\\", "/");
    }
}