package com.zhurong.platform.custom.drawing;

import com.zhurong.platform.base.exception.BusinessException;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;

@Component
@Order(50)
public class LocalFileDrawingDownloadStrategy implements DrawingDownloadStrategy {

    @Override
    public boolean supports(String drawingPath) {
        if (!StringUtils.hasText(drawingPath)) {
            return false;
        }
        String value = drawingPath.trim();
        if (value.regionMatches(true, 0, "file:", 0, 5)) {
            return true;
        }
        if (value.contains("://") || value.startsWith("\\\\") || value.startsWith("//")) {
            return false;
        }
        try {
            return value.matches("^[a-zA-Z]:[\\\\/].*") || Path.of(value).isAbsolute();
        } catch (InvalidPathException ex) {
            return false;
        }
    }

    @Override
    public DrawingDownloadSource open(String drawingPath) throws IOException {
        Path sourcePath = sourcePath(drawingPath.trim());
        if (!Files.isRegularFile(sourcePath)) {
            throw new BusinessException("DRAWING_LOCAL_FILE_NOT_FOUND: local drawing file not found");
        }
        return new DrawingDownloadSource(
                sourcePath.getFileName().toString(),
                Files.probeContentType(sourcePath),
                Files.newInputStream(sourcePath)
        );
    }

    private Path sourcePath(String drawingPath) {
        if (drawingPath.regionMatches(true, 0, "file:", 0, 5)) {
            return Path.of(URI.create(drawingPath)).toAbsolutePath().normalize();
        }
        return Path.of(drawingPath).toAbsolutePath().normalize();
    }
}
