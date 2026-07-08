package com.zhurong.platform.core.clientimport.file;

import com.zhurong.platform.base.exception.BusinessException;
import com.zhurong.platform.core.clientimport.configuration.ImportFileProperties;
import com.zhurong.platform.core.clientimport.configuration.ConditionalOnClientCommunicationEnabled;
import com.zhurong.platform.core.clientimport.enums.ImageSourceType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.Optional;

@Component
@ConditionalOnClientCommunicationEnabled
@RequiredArgsConstructor
public class WindowsShareImportImageSourceHandler implements ImportImageSourceHandler {

    private final StoredFileWriter fileWriter;

    @Override
    public boolean supports(String image) {
        return StringUtils.hasText(image)
                && (image.startsWith("\\\\") || image.startsWith("//"));
    }

    @Override
    public StoredImportFile store(String requestId, String image, ImportFileStorageContext context) {
        // 这里的Windows共享路径是外部调用方提供的源文件位置，不是core归档后暴露给客户端的最终共享目录。
        String source = normalizeSharePath(image.trim());
        if (source.contains("..")) {
            throw new BusinessException("WINDOWS_SHARE_PATH_TRAVERSAL: Windows共享路径不允许包含..");
        }
        Path sourcePath = resolveSourcePath(source, context.getProperties());
        if (!Files.isRegularFile(sourcePath)) {
            throw new BusinessException("WINDOWS_SHARE_FILE_NOT_FOUND: Windows共享源文件不存在");
        }
        try {
            long fileSize = Files.size(sourcePath);
            if (fileSize > context.getProperties().getMaxFileSize()) {
                throw new BusinessException("IMPORT_FILE_TOO_LARGE: Windows共享源文件超过最大限制");
            }
            return fileWriter.write(
                    requestId,
                    image,
                    ImageSourceType.WINDOWS_SHARE,
                    sourcePath.getFileName().toString(),
                    Files.probeContentType(sourcePath),
                    Files.newInputStream(sourcePath)
            );
        } catch (IOException ex) {
            throw new BusinessException("WINDOWS_SHARE_READ_FAILED: Windows共享源文件读取失败：" + ex.getMessage());
        }
    }

    private Path resolveSourcePath(String source, ImportFileProperties properties) {
        Optional<ImportFileProperties.WindowsShareSourceMapping> mapping = properties.getWindowsShareSourceMappings()
                .stream()
                .filter(item -> StringUtils.hasText(item.getShareRoot()) && StringUtils.hasText(item.getMountedRoot()))
                .filter(item -> isSameOrChildShare(source, normalizeShareRoot(item.getShareRoot())))
                .max(Comparator.comparingInt(item -> normalizeShareRoot(item.getShareRoot()).length()));

        if (mapping.isEmpty()) {
            // Windows本机运行core时，可以直接尝试读取UNC路径；Linux容器通常需要配置映射。
            return Path.of(source).normalize();
        }

        String shareRoot = normalizeShareRoot(mapping.get().getShareRoot());
        String relative = source.substring(shareRoot.length());
        relative = trimLeadingSeparators(relative);
        Path mountedRoot = Path.of(mapping.get().getMountedRoot()).toAbsolutePath().normalize();
        Path resolved = mountedRoot.resolve(relative.replace('\\', '/')).normalize();
        if (!resolved.startsWith(mountedRoot)) {
            throw new BusinessException("WINDOWS_SHARE_PATH_TRAVERSAL: Windows共享映射路径越界");
        }
        return resolved;
    }

    private String normalizeSharePath(String image) {
        if (image.startsWith("//")) {
            return "\\\\" + image.substring(2).replace('/', '\\');
        }
        return image;
    }

    private String normalizeShareRoot(String shareRoot) {
        String normalized = normalizeSharePath(shareRoot.trim());
        while (normalized.endsWith("\\") && normalized.length() > 2) {
            normalized = normalized.substring(0, normalized.length() - 1);
        }
        return normalized;
    }

    private boolean isSameOrChildShare(String source, String shareRoot) {
        return source.equalsIgnoreCase(shareRoot)
                || source.regionMatches(true, 0, shareRoot + "\\", 0, shareRoot.length() + 1);
    }

    private String trimLeadingSeparators(String value) {
        String result = value;
        while (result.startsWith("\\") || result.startsWith("/")) {
            result = result.substring(1);
        }
        return result;
    }
}
