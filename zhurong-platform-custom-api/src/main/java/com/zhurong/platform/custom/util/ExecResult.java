package com.zhurong.platform.custom.util;

public record ExecResult(
        int exitCode,
        String stdout,
        String stderr,
        boolean success,
        long durationMs
) {}