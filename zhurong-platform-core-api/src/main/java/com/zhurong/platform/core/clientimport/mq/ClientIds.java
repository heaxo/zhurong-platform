package com.zhurong.platform.core.clientimport.mq;

import org.springframework.util.StringUtils;

import java.util.Locale;

/**
 * 统一客户端标识格式，避免 Windows 主机名大小写造成两个不同的 MQ 队列。
 */
public final class ClientIds {

    private ClientIds() {
    }

    public static String normalize(String value) {
        return StringUtils.hasText(value) ? value.trim().toUpperCase(Locale.ROOT) : null;
    }
}
