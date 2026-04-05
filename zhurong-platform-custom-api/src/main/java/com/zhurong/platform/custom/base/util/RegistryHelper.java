package com.zhurong.platform.custom.base.util;

import com.sun.jna.platform.win32.Advapi32Util;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.platform.win32.WinReg;

import java.util.Objects;
import java.util.Optional;

public final class RegistryHelper {

    private static final String REGISTRY_PATH = "SOFTWARE\\WOW6432Node\\Lantek";
    private static final String VALUE_NAME = "MainDir";

    private RegistryHelper() {
    }

    /**
     * 从 HKEY_LOCAL_MACHINE 读取 Lantek 安装目录
     * 等价于 C# RegistryView.Registry64 + WOW6432Node
     *
     * @return 安装目录，不存在返回 null
     */
    public static String getInstallDir() {
        return readStringValue(
                WinReg.HKEY_LOCAL_MACHINE,
                REGISTRY_PATH,
                VALUE_NAME,
                true // 优先 64 位视图
        ).orElse("C:\\Lantek2");
    }

    /**
     * 通用注册表读取（生产级封装）
     */
    public static Optional<String> readStringValue(
            WinReg.HKEY root,
            String path,
            String valueName,
            boolean prefer64
    ) {
        Objects.requireNonNull(root);
        Objects.requireNonNull(path);
        Objects.requireNonNull(valueName);

        // 访问顺序策略：
        // 1. 指定优先视图（64 or 32）
        // 2. fallback 到另一视图

        if (prefer64) {
            Optional<String> val = read(root, path, valueName, true);
            if (val.isPresent()) return val;

            return read(root, path, valueName, false);
        } else {
            Optional<String> val = read(root, path, valueName, false);
            if (val.isPresent()) return val;

            return read(root, path, valueName, true);
        }
    }

    /**
     * 单次读取（指定视图）
     */
    private static Optional<String> read(
            WinReg.HKEY root,
            String path,
            String valueName,
            boolean is64
    ) {
        try {
            int flags = is64
                    ? WinNT.KEY_READ | WinNT.KEY_WOW64_64KEY
                    : WinNT.KEY_READ | WinNT.KEY_WOW64_32KEY;

            if (!Advapi32Util.registryKeyExists(root, path, flags)) {
                return Optional.empty();
            }

            if (!Advapi32Util.registryValueExists(root, path, valueName, flags)) {
                return Optional.empty();
            }

            String value = Advapi32Util.registryGetStringValue(
                    root,
                    path,
                    valueName,
                    flags
            );

            if (value == null || value.isBlank()) {
                return Optional.empty();
            }

            return Optional.of(value);

        } catch (Throwable ex) {
            return Optional.empty();
        }
    }
}