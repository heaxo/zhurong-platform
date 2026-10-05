package com.zhurong.platform.custom.drawing;

import com.zhurong.platform.base.exception.BusinessException;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
@Order(40)
public class WindowsShareDrawingDownloadStrategy implements DrawingDownloadStrategy {

    @Override
    public boolean supports(String drawingPath) {
        return StringUtils.hasText(drawingPath)
                && (drawingPath.startsWith("\\\\") || drawingPath.startsWith("//"));
    }

    @Override
    public DrawingDownloadSource open(String drawingPath) throws IOException {
        String source = normalizeSharePath(drawingPath.trim());
        if (source.contains("..")) {
            throw new BusinessException("DRAWING_WINDOWS_SHARE_PATH_TRAVERSAL: Windows share path must not contain ..");
        }

        Path sourcePath = Path.of(source).toAbsolutePath().normalize();
        if (!Files.isRegularFile(sourcePath)) {
            throw new BusinessException("DRAWING_WINDOWS_SHARE_FILE_NOT_FOUND: Windows share drawing file not found");
        }

        return new DrawingDownloadSource(
                sourcePath.getFileName().toString(),
                Files.probeContentType(sourcePath),
                Files.newInputStream(sourcePath)
        );
    }

    private String normalizeSharePath(String drawingPath) {
        if (drawingPath.startsWith("//")) {
            return "\\\\" + drawingPath.substring(2).replace('/', '\\');
        }
        return drawingPath;
    }
}
