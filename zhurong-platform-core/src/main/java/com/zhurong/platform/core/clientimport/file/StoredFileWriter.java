package com.zhurong.platform.core.clientimport.file;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.zhurong.platform.base.exception.BusinessException;
import com.zhurong.platform.core.clientimport.configuration.ConditionalOnClientCommunicationEnabled;
import com.zhurong.platform.core.clientimport.configuration.ImportFileProperties;
import com.zhurong.platform.core.clientimport.enums.ImageSourceType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HexFormat;

@Component
@ConditionalOnClientCommunicationEnabled
@RequiredArgsConstructor
public class StoredFileWriter {

    /*
     * 物理文件统一写入storage-root下的日期/文件后缀目录。
     * 这里负责最终路径归一化和startsWith校验，防止外部文件名造成路径穿越。
     */
    private static final DateTimeFormatter DATE_PATH = DateTimeFormatter.BASIC_ISO_DATE;

    private final ImportFileProperties properties;
    private final ClientFileAccessResolver accessResolver;

    public StoredImportFile write(
            String requestId,
            String originalImage,
            ImageSourceType sourceType,
            String originalFileName,
            String contentType,
            InputStream inputStream) {

        String fileId = IdWorker.getIdStr();
        String datePath = LocalDate.now().format(DATE_PATH);
        String safeFileName = SafeFileNames.sanitizeFileName(originalFileName, fileId + ".bin");
        String extensionPath = extensionPathSegment(safeFileName);

        Path root = Path.of(properties.getStorageRoot()).toAbsolutePath().normalize();
        Path directory = root.resolve(datePath).resolve(extensionPath).normalize();
        Path target = nextAvailableTarget(directory, safeFileName, fileId).normalize();

        if (!target.startsWith(root)) {
            throw new BusinessException("IMPORT_FILE_PATH_TRAVERSAL: 文件保存路径非法");
        }

        try {
            Files.createDirectories(directory);
            FileDigest digest = copyWithLimit(inputStream, target, properties.getMaxFileSize());
            // 数据库只保存相对路径，避免Windows开发路径和Docker容器路径在不同环境下失效。
            String relativePath = root.relativize(target).toString().replace('\\', '/');
            ClientFileAccessResolver.ClientAccess clientAccess = accessResolver.resolve(fileId, relativePath);

            return StoredImportFile.builder()
                    .fileId(fileId)
                    .sourceType(sourceType)
                    .originalImage(ImageSourceSanitizer.mask(originalImage))
                    .originalFileName(safeFileName)
                    .storedRelativePath(relativePath)
                    .clientAccessType(clientAccess.type())
                    .clientAccessPath(clientAccess.path())
                    .contentType(contentType)
                    .fileSize(digest.size())
                    .sha256(digest.sha256())
                    .build();
        } catch (IOException ex) {
            throw new BusinessException("IMPORT_FILE_STORE_FAILED: 文件归档失败：" + ex.getMessage());
        }
    }

    private FileDigest copyWithLimit(InputStream inputStream, Path target, long maxFileSize) throws IOException {
        MessageDigest messageDigest = sha256();
        long total = 0;
        try (DigestInputStream digestInputStream = new DigestInputStream(inputStream, messageDigest);
             var outputStream = Files.newOutputStream(target, StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE)) {
            byte[] buffer = new byte[8192];
            int read;
            while ((read = digestInputStream.read(buffer)) != -1) {
                total += read;
                if (total > maxFileSize) {
                    Files.deleteIfExists(target);
                    throw new BusinessException("IMPORT_FILE_TOO_LARGE: 文件超过最大限制");
                }
                outputStream.write(buffer, 0, read);
            }
        }
        return new FileDigest(total, HexFormat.of().formatHex(messageDigest.digest()));
    }

    private Path nextAvailableTarget(Path directory, String safeFileName, String fileId) {
        Path target = directory.resolve(safeFileName);
        if (!Files.exists(target)) {
            return target;
        }

        int extensionIndex = safeFileName.lastIndexOf('.');
        String name = extensionIndex > 0 ? safeFileName.substring(0, extensionIndex) : safeFileName;
        String extension = extensionIndex > 0 ? safeFileName.substring(extensionIndex) : "";
        return directory.resolve(name + "_" + fileId + extension);
    }

    private String extensionPathSegment(String safeFileName) {
        int extensionIndex = safeFileName.lastIndexOf('.');
        if (extensionIndex < 0 || extensionIndex == safeFileName.length() - 1) {
            return "bin";
        }
        String extension = safeFileName.substring(extensionIndex + 1).toLowerCase();
        return SafeFileNames.sanitizePathSegment(extension, "bin");
    }

    private MessageDigest sha256() {
        try {
            return MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException ex) {
            throw new IllegalStateException("SHA-256 not available", ex);
        }
    }

    private record FileDigest(long size, String sha256) {
    }
}
