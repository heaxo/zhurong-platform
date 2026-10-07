package com.zhurong.platform.custom.drawing;

import com.zhurong.platform.base.exception.BusinessException;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class DrawingDownloadTool {

    private static final String STORAGE_DIRECTORY = "drawings";

    private final List<DrawingDownloadStrategy> strategies;
    private final Path runRoot;
    private final Path storageRoot;

    @Autowired
    public DrawingDownloadTool(List<DrawingDownloadStrategy> strategies) {
        this(strategies, defaultRunRoot());
    }

    DrawingDownloadTool(List<DrawingDownloadStrategy> strategies, Path runRoot) {
        this.strategies = new ArrayList<>(strategies);
        AnnotationAwareOrderComparator.sort(this.strategies);
        this.runRoot = runRoot.toAbsolutePath().normalize();
        this.storageRoot = this.runRoot.resolve(STORAGE_DIRECTORY).normalize();
    }

    public String download(String drawingPath) {
        return downloadWithResult(drawingPath).storedAbsolutePath().toAbsolutePath().toString();
    }

    public DrawingDownloadResult downloadWithResult(String drawingPath) {
        if (!StringUtils.hasText(drawingPath)) {
            throw new BusinessException("DRAWING_PATH_EMPTY: drawing path is empty");
        }

        DrawingDownloadStrategy strategy = strategies.stream()
                .filter(item -> item.supports(drawingPath))
                .findFirst()
                .orElseThrow(() -> new BusinessException("DRAWING_PROTOCOL_UNSUPPORTED: unsupported drawing path protocol"));

        try (DrawingDownloadSource source = strategy.open(drawingPath.trim())) {
            String fileName = safeFileName(source.originalFileName(), UUID.randomUUID() + ".bin");
            String extension = extensionSegment(fileName);
            Path directory = storageRoot.resolve(extension).normalize();
            Path target = nextAvailableTarget(directory, fileName).normalize();

            if (!target.startsWith(storageRoot)) {
                throw new BusinessException("DRAWING_PATH_TRAVERSAL: drawing storage path is invalid");
            }

            Files.createDirectories(directory);
            long fileSize = copy(source.inputStream(), target);
            String relativePath = runRoot.relativize(target).toString().replace('\\', '/');
            return new DrawingDownloadResult(
                    target,
                    relativePath,
                    fileName,
                    source.contentType(),
                    fileSize
            );
        } catch (BusinessException ex) {
            throw ex;
        } catch (IOException ex) {
            throw new BusinessException("DRAWING_DOWNLOAD_FAILED: " + ex.getMessage());
        }
    }

    private long copy(InputStream inputStream, Path target) throws IOException {
        return Files.copy(inputStream, target, StandardCopyOption.REPLACE_EXISTING);
    }

    private Path nextAvailableTarget(Path directory, String fileName) {
        Path target = directory.resolve(fileName);
        if (!Files.exists(target)) {
            return target;
        }

        int extensionIndex = fileName.lastIndexOf('.');
        String name = extensionIndex > 0 ? fileName.substring(0, extensionIndex) : fileName;
        String extension = extensionIndex > 0 ? fileName.substring(extensionIndex) : "";
        return directory.resolve(name + "_" + UUID.randomUUID() + extension);
    }

    static String safeFileName(String fileName, String fallback) {
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

    static String safePathSegment(String value, String fallback) {
        String candidate = StringUtils.hasText(value) ? value : fallback;
        candidate = candidate.replaceAll("[\\\\/:*?\"<>|\\s]+", "_");
        candidate = candidate.replace("..", "_");
        candidate = candidate.trim();
        if (!StringUtils.hasText(candidate)) {
            return fallback;
        }
        return candidate.length() > 120 ? candidate.substring(0, 120) : candidate;
    }

    private String extensionSegment(String fileName) {
        int extensionIndex = fileName.lastIndexOf('.');
        if (extensionIndex < 0 || extensionIndex == fileName.length() - 1) {
            return "bin";
        }
        return safePathSegment(fileName.substring(extensionIndex + 1).toLowerCase(), "bin");
    }

    private static Path defaultRunRoot() {
        File dir = new ApplicationHome(DrawingDownloadTool.class).getDir();
        if (dir != null) {
            return dir.toPath().toAbsolutePath().normalize();
        }
        return Path.of("").toAbsolutePath().normalize();
    }
}
