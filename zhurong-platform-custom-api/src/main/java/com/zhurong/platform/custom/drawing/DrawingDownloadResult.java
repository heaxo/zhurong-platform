package com.zhurong.platform.custom.drawing;

import java.nio.file.Path;

public record DrawingDownloadResult(
        Path storedAbsolutePath,
        String storedRelativePath,
        String originalFileName,
        String contentType,
        long fileSize
) {
}
