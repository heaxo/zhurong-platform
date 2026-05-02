package com.zhurong.platform.custom.controller;

/*
 * @Author zhurong
 * @Description StaticResourcesController
 * @Date 2026/5/1 14:10
 **/

import com.zhurong.platform.custom.dto.StaticResourcesDTO;
import com.zhurong.platform.custom.web.BaseController;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URLDecoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.time.Duration;
import java.util.Base64;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
@Slf4j
public class BaseStaticResourcesController extends BaseController {

    private static final Duration CACHE_TIMEOUT = Duration.ofMinutes(3);

    /**
     * 下载文件会存到：当前 jar 目录/staticResources/后缀名/文件名
     */
    private static final String PRODUCT_NAME = "staticResources";

    /**
     * 父类默认静态资源根目录。
     */
    private final Path staticResourceRootFolder = Paths.get("C:\\Lantek\\LSystemDB")
            .toAbsolutePath()
            .normalize();

    /**
     * 当前 jar 运行目录。
     * 例如：/opt/app/app.jar -> /opt/app
     */
    private final Path appRunDir = getAppRunDir();

    private final Map<String, CacheItem<String>> base64Cache = new ConcurrentHashMap<>();
    private final Map<String, CacheItem<FileCacheData>> fileCache = new ConcurrentHashMap<>();

    private static final HttpClient HTTP_CLIENT = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(15))
            .followRedirects(HttpClient.Redirect.NORMAL)
            .build();

    /**
     * 子类可以重写该方法，自定义静态资源根目录。
     */
    protected Path getStaticResourceRootFolder() {
        return staticResourceRootFolder;
    }

    @GetMapping
    public ResponseEntity<?> get(StaticResourcesDTO req, HttpServletResponse response) {
        long startTime = System.currentTimeMillis();
        Path localPath = null;

        if (req == null) {
            log.warn("[staticResources] 请求参数为空，返回默认图片");
            return ResponseEntity.ok(new ApiResult<>(defaultBase64Image(), 0));
        }

        if (!StringUtils.hasText(req.getSource())) {
            log.warn("[staticResources] source为空，返回默认图片，req={}", req);
            return ResponseEntity.ok(new ApiResult<>(defaultBase64Image(), 0));
        }

        String image = req.getSource();

        log.info(
                "[staticResources] 收到请求，source={}, resultType={}, disabledCache={}",
                safeLogText(image),
                req.getResultType(),
                req.getDisabledCache()
        );

        /**
         * http / https 直接返回 URL，前端自行加载。
         */
        if (startsWithIgnoreCase(image, "http://") || startsWithIgnoreCase(image, "https://")) {
            log.info(
                    "[staticResources] HTTP/HTTPS资源直接返回，source={}, cost={}ms",
                    safeLogText(image),
                    System.currentTimeMillis() - startTime
            );
            return ResponseEntity.ok(new ApiResult<>(image, 0));
        }

        response.setHeader("Access-Control-Allow-Origin", "*");

        try {
            String cacheKey = "img:" + image;
            String cacheBase64Key = "img:base64:" + image;

            boolean returnBase64 = StringUtils.hasText(req.getResultType())
                    && "base64".equalsIgnoreCase(req.getResultType());

            boolean disabledCache = Boolean.TRUE.equals(req.getDisabledCache());

            log.info(
                    "[staticResources] 请求解析完成，returnBase64={}, disabledCache={}, cacheKey={}",
                    returnBase64,
                    disabledCache,
                    safeLogText(cacheKey)
            );

            if (returnBase64 && !disabledCache) {
                String cachedDataUri = getCache(base64Cache, cacheBase64Key);

                if (cachedDataUri != null) {
                    log.info(
                            "[staticResources] 命中base64缓存，source={}, dataLength={}, cost={}ms",
                            safeLogText(image),
                            cachedDataUri.length(),
                            System.currentTimeMillis() - startTime
                    );
                    return ResponseEntity.ok(new ApiResult<>(cachedDataUri, 0));
                }

                log.info("[staticResources] 未命中base64缓存，source={}", safeLogText(image));
            } else if (!disabledCache) {
                FileCacheData cachedFile = getCache(fileCache, cacheKey);

                if (cachedFile != null) {
                    log.info(
                            "[staticResources] 命中文件缓存，source={}, bytes={}, mime={}, cost={}ms",
                            safeLogText(image),
                            cachedFile.bytes() == null ? 0 : cachedFile.bytes().length,
                            cachedFile.mime(),
                            System.currentTimeMillis() - startTime
                    );
                    return buildFileResponse(cachedFile.bytes(), cachedFile.mime());
                }

                log.info("[staticResources] 未命中文件缓存，source={}", safeLogText(image));
            } else {
                log.info("[staticResources] 本次请求禁用缓存，source={}", safeLogText(image));
            }

            if (isLocalRelativePath(image)) {
                Path rootFolder = getStaticResourceRootFolder();

                localPath = rootFolder.resolve(image).normalize();

                // 防止 ../ 路径穿越
                Path root = rootFolder.toAbsolutePath().normalize();
                Path target = localPath.toAbsolutePath().normalize();

                log.info(
                        "[staticResources] 本地相对路径解析完成，source={}, rootFolder={}, localPath={}, absoluteTarget={}",
                        safeLogText(image),
                        rootFolder.toAbsolutePath().normalize(),
                        localPath,
                        target
                );

                if (!target.startsWith(root)) {
                    log.warn(
                            "[staticResources] 检测到非法路径访问，source={}, root={}, target={}",
                            safeLogText(image),
                            root,
                            target
                    );
                    return ResponseEntity.ok(new ApiResult<>(defaultBase64Image(), 0));
                }
            } else {
                log.info(
                        "[staticResources] source不是本地相对路径，准备下载到本地，source={}",
                        safeLogText(image)
                );

                localPath = downloadFileToLocal(image);

                log.info(
                        "[staticResources] 文件下载完成，source={}, localPath={}",
                        safeLogText(image),
                        localPath == null ? null : localPath.toAbsolutePath().normalize()
                );
            }

            if (localPath == null) {
                log.warn("[staticResources] localPath为空，source={}", safeLogText(image));
                return ResponseEntity.ok(new ApiResult<>(defaultBase64Image(), 0));
            }

            Path absoluteLocalPath = localPath.toAbsolutePath().normalize();

            if (!Files.exists(localPath)) {
                log.warn(
                        "[staticResources] 文件不存在，source={}, localPath={}",
                        safeLogText(image),
                        absoluteLocalPath
                );
                return ResponseEntity.ok(new ApiResult<>(defaultBase64Image(), 0));
            }

            if (!Files.isRegularFile(localPath)) {
                log.warn(
                        "[staticResources] 目标路径不是普通文件，source={}, localPath={}",
                        safeLogText(image),
                        absoluteLocalPath
                );
                return ResponseEntity.ok(new ApiResult<>(defaultBase64Image(), 0));
            }

            byte[] bytes = Files.readAllBytes(localPath);
            String mime = getMimeFromFileName(localPath);

            log.info(
                    "[staticResources] 文件读取成功，source={}, localPath={}, bytes={}, mime={}",
                    safeLogText(image),
                    absoluteLocalPath,
                    bytes.length,
                    mime
            );

            if (returnBase64) {
                String dataUri = buildDataUri(bytes, mime);
                setCache(base64Cache, cacheBase64Key, dataUri, CACHE_TIMEOUT);

                log.info(
                        "[staticResources] 返回base64结果，source={}, dataLength={}, cost={}ms",
                        safeLogText(image),
                        dataUri.length(),
                        System.currentTimeMillis() - startTime
                );

                return ResponseEntity.ok(new ApiResult<>(dataUri, 0));
            } else {
                setCache(fileCache, cacheKey, new FileCacheData(bytes, mime), CACHE_TIMEOUT);

                log.info(
                        "[staticResources] 返回文件流，source={}, localPath={}, bytes={}, mime={}, cost={}ms",
                        safeLogText(image),
                        absoluteLocalPath,
                        bytes.length,
                        mime,
                        System.currentTimeMillis() - startTime
                );

                return buildFileResponse(bytes, mime);
            }

        } catch (Exception e) {
            log.error(
                    "[staticResources] 处理静态资源异常，source={}, localPath={}, cost={}ms",
                    safeLogText(image),
                    localPath == null ? null : localPath.toAbsolutePath().normalize(),
                    System.currentTimeMillis() - startTime,
                    e
            );

            return ResponseEntity.ok(new ApiResult<>(defaultBase64Image(), 0));
        }
    }
    private static String safeLogText(String value) {
        if (!StringUtils.hasText(value)) {
            return "";
        }

        String text = value
                .replace("\r", "_")
                .replace("\n", "_")
                .replace("\t", "_");

        int maxLength = 300;

        if (text.length() <= maxLength) {
            return text;
        }

        return text.substring(0, maxLength) + "...(length=" + text.length() + ")";
    }
    private Path downloadFileToLocal(String target) throws Exception {
        return downloadFileToLocal(target, null);
    }

    private Path downloadFileToLocal(String target, String destinationFolderPath) throws Exception {
        if (!StringUtils.hasText(target)) {
            throw new IllegalArgumentException("文件路径不能为空");
        }

        String fileName = getTargetFileName(target);
        String suffix = getFileExtensionWithoutDot(fileName);

        String childFolder = StringUtils.hasText(suffix) ? suffix : "UNKNOWN";

        Path productRootPath = appRunDir.resolve(PRODUCT_NAME).normalize();
        Files.createDirectories(productRootPath);

        Path drawRootPath;
        if (StringUtils.hasText(destinationFolderPath)) {
            drawRootPath = Paths.get(destinationFolderPath).resolve(childFolder).normalize();
        } else {
            drawRootPath = productRootPath.resolve(childFolder).normalize();
        }

        Files.createDirectories(drawRootPath);

        Path destinationPath = drawRootPath.resolve(fileName).normalize();

        PathProtocol protocol = getPathProtocol(target);

        switch (protocol) {
            case LOCAL:
            case UNC: {
                Path sourcePath = Paths.get(target).toAbsolutePath().normalize();

                if (!Files.exists(sourcePath)) {
                    throw new NoSuchFileException("文件不存在：" + target);
                }

                Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
                return destinationPath;
            }

            case HTTP:
            case HTTPS: {
                downloadHttpFile(target, destinationPath);
                return destinationPath;
            }

            case FTP: {
                downloadFtpFile(target, destinationPath);
                return destinationPath;
            }

            case UNKNOWN:
            default:
                throw new IllegalStateException("未知的文件类型，无法下载");
        }
    }

    private void downloadHttpFile(String target, Path destinationPath) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(target))
                .timeout(Duration.ofMinutes(2))
                .GET()
                .build();

        HttpResponse<byte[]> response = HTTP_CLIENT.send(
                request,
                HttpResponse.BodyHandlers.ofByteArray()
        );

        int statusCode = response.statusCode();

        if (statusCode < 200 || statusCode >= 300) {
            throw new IllegalStateException("文件下载异常，HTTP状态码：" + statusCode + "，URL：" + target);
        }

        Files.write(
                destinationPath,
                response.body(),
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING
        );
    }

    private void downloadFtpFile(String target, Path destinationPath) throws Exception {
        /*URI uri = URI.create(target);

        String host = uri.getHost();
        int port = uri.getPort() > 0 ? uri.getPort() : 21;

        String username = "anonymous";
        String password = "";

        *//**
         * 支持格式：
         * ftp://user:password@host/path/file.png
         *//*
        if (StringUtils.hasText(uri.getUserInfo())) {
            String[] arr = uri.getUserInfo().split(":", 2);
            username = URLDecoder.decode(arr[0], StandardCharsets.UTF_8);

            if (arr.length > 1) {
                password = URLDecoder.decode(arr[1], StandardCharsets.UTF_8);
            }
        }

        String rawPath = uri.getRawPath();
        if (!StringUtils.hasText(rawPath)) {
            throw new IllegalArgumentException("FTP文件路径不能为空：" + target);
        }

        String remotePath = URLDecoder.decode(rawPath, StandardCharsets.UTF_8);

        FTPClient ftpClient = new FTPClient();

        try {
            ftpClient.connect(host, port);

            if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
                throw new IllegalStateException("FTP连接失败：" + target);
            }

            boolean loginSuccess = ftpClient.login(username, password);
            if (!loginSuccess) {
                throw new IllegalStateException("FTP登录失败：" + target);
            }

            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

            try (OutputStream outputStream = Files.newOutputStream(
                    destinationPath,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING
            )) {
                boolean success = ftpClient.retrieveFile(remotePath, outputStream);

                if (!success) {
                    throw new NoSuchFileException("FTP文件下载异常或文件不存在：" + target);
                }
            }

        } finally {
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.logout();
                } catch (Exception ignored) {
                }

                try {
                    ftpClient.disconnect();
                } catch (Exception ignored) {
                }
            }
        }*/
    }

    private ResponseEntity<byte[]> buildFileResponse(byte[] bytes, String mime) {
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(mime))
                .body(bytes);
    }

    private static String buildDataUri(byte[] bytes, String contentType) {
        String base64 = Base64.getEncoder().encodeToString(bytes);
        return "data:" + contentType + ";base64," + base64;
    }

    private static String getMimeFromFileName(Path path) {
        try {
            String mime = Files.probeContentType(path);
            return StringUtils.hasText(mime) ? mime : "application/octet-stream";
        } catch (IOException e) {
            return "application/octet-stream";
        }
    }

    private static boolean isLocalRelativePath(String image) {
        if (!StringUtils.hasText(image)) {
            return false;
        }

        String lower = image.toLowerCase(Locale.ROOT);

        if (lower.startsWith("http://")
                || lower.startsWith("https://")
                || lower.startsWith("ftp://")) {
            return false;
        }

        // Windows UNC：\\server\share\a.png 或 //server/share/a.png
        if (image.startsWith("\\\\") || image.startsWith("//")) {
            return false;
        }

        // Windows 绝对路径：C:\a\b.png 或 C:/a/b.png
        if (image.matches("^[a-zA-Z]:[\\\\/].*")) {
            return false;
        }

        if (image.startsWith("/") || image.startsWith("\\")) {
            return false;
        }

        if (image.contains("://")) {
            return false;
        }

        return true;
    }

    private static PathProtocol getPathProtocol(String target) {
        if (!StringUtils.hasText(target)) {
            return PathProtocol.UNKNOWN;
        }

        String lower = target.toLowerCase(Locale.ROOT);

        if (lower.startsWith("http://")) {
            return PathProtocol.HTTP;
        }

        if (lower.startsWith("https://")) {
            return PathProtocol.HTTPS;
        }

        if (lower.startsWith("ftp://")) {
            return PathProtocol.FTP;
        }

        if (target.startsWith("\\\\") || target.startsWith("//")) {
            return PathProtocol.UNC;
        }

        if (target.matches("^[a-zA-Z]:[\\\\/].*")) {
            return PathProtocol.LOCAL;
        }

        Path path = Paths.get(target);
        if (path.isAbsolute()) {
            return PathProtocol.LOCAL;
        }

        return PathProtocol.UNKNOWN;
    }

    private static String getTargetFileName(String target) {
        try {
            PathProtocol protocol = getPathProtocol(target);

            if (protocol == PathProtocol.HTTP
                    || protocol == PathProtocol.HTTPS
                    || protocol == PathProtocol.FTP) {
                URI uri = URI.create(target);
                String path = uri.getPath();

                if (StringUtils.hasText(path)) {
                    String normalized = path.replace("\\", "/");
                    int index = normalized.lastIndexOf("/");
                    String fileName = index >= 0 ? normalized.substring(index + 1) : normalized;

                    if (StringUtils.hasText(fileName)) {
                        return sanitizeFileName(fileName);
                    }
                }
            } else {
                String normalized = target.replace("\\", "/");
                int index = normalized.lastIndexOf("/");
                String fileName = index >= 0 ? normalized.substring(index + 1) : normalized;

                if (StringUtils.hasText(fileName)) {
                    return sanitizeFileName(fileName);
                }
            }
        } catch (Exception ignored) {
        }

        return UUID.randomUUID() + ".dat";
    }

    private static String sanitizeFileName(String fileName) {
        String decoded = URLDecoder.decode(fileName, StandardCharsets.UTF_8);

        decoded = decoded.replace("\\", "_")
                .replace("/", "_")
                .replace(":", "_")
                .replace("*", "_")
                .replace("?", "_")
                .replace("\"", "_")
                .replace("<", "_")
                .replace(">", "_")
                .replace("|", "_");

        return StringUtils.hasText(decoded) ? decoded : UUID.randomUUID() + ".dat";
    }

    private static String getFileExtensionWithoutDot(String fileName) {
        if (!StringUtils.hasText(fileName)) {
            return "";
        }

        int index = fileName.lastIndexOf(".");
        if (index < 0 || index == fileName.length() - 1) {
            return "";
        }

        return fileName.substring(index + 1);
    }

    private static <T> T getCache(Map<String, CacheItem<T>> cache, String key) {
        CacheItem<T> item = cache.get(key);
        if (item == null) {
            return null;
        }

        if (System.currentTimeMillis() > item.expireAt()) {
            cache.remove(key);
            return null;
        }

        return item.value();
    }

    private static <T> void setCache(
            Map<String, CacheItem<T>> cache,
            String key,
            T value,
            Duration timeout
    ) {
        long expireAt = System.currentTimeMillis() + timeout.toMillis();
        cache.put(key, new CacheItem<>(value, expireAt));
    }

    private static boolean startsWithIgnoreCase(String value, String prefix) {
        return value != null && value.regionMatches(true, 0, prefix, 0, prefix.length());
    }

    private static Path getAppRunDir() {
        File dir = new ApplicationHome(BaseStaticResourcesController.class).getDir();

        if (dir != null) {
            return dir.toPath().toAbsolutePath().normalize();
        }

        return Paths.get("").toAbsolutePath().normalize();
    }

    private static String defaultBase64Image() {
        return "data:image/bmp;base64,Qk3mCAAAAAAAAHYAAAAoAAAARgAAADwAAAABAAQAAAAAAHAIAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAgAAAgAAAAICAAIAAAACAAIAAgIAAAICAgADAwMAAAAD/AAD/AAAA//8A/wAAAP8A/wD//wAA////AP//////////////////////////////////////////////AP//////////////////////////////////////////////AP//////////////////////////////////////////////AP//////////////////////////////////////////////AP//////////////////////mf//////////////////////AP//////////////////////mZ//////////////////////AP//////////////////////mfn/////////////////////AP/////////////////////5n/+f////////////////////AP/////////////////////5n//5////////////////////AP/////////////////////5n///n///////////////////AP////////////////////+Z////+f//////////////////AP////////////////////+Z/////5//////////////////AP////////////////////mf//////n/////////////////AP////////////////////mf//////+f////////////////AP////////////////////mf///////5////////////////AP///////////////////5n/////////n///////////////AP///////////////////5n/////////+f//////////////AP///////////////////5n//////////5//////////////AP//////////////////+Z////////////n/////////////AP//////////////////+Z////////////+f////////////AP//////////////////mf/////////////5////////////AP//////////////////mf//////////////n///////////AP//////////////////mf//////////////+f//////////AP/////////////////5n////////////////5//////////AP/////////////////5n/////////////////n/////////AP/////////////////5n/////////////////+f////////AP////////////////+Z///////////////////5////////AP////////////////+Z////////////////////n///////AP////////////////mf////////////////////+f//////AP////////////////mf/////////////////////5//////AP////////////////mf//////////////////////n/////AP///////////////5n///////////////////////+f////AP///////////////5n////////////////////////5////AP//+f///////////5n/////////////////////////n///AP//mZ//////////+Z//////////////////////////////AP//mZn/////////+Z//////////////////////////////AP//+Zmf////////mf//////////////////////////////AP///5mf////////mf//////////////////////////////AP///5mZ////////mf//////////////////////////////AP////mZn//////5n///////////////////////////////AP////+Zn//////5n///////////////////////////////AP////+Zmf/////5n///////////////////////////////AP/////5mZ////+Z////////////////////////////////AP//////mZ////+Z////////////////////////////////AP//////mZn///mf////////////////////////////////AP//////+Zmf//mf////////////////////////////////AP///////5mf//mf////////////////////////////////AP///////5mZ/5n/////////////////////////////////AP////////mZn5n/////////////////////////////////AP////////+Zn5n/////////////////////////////////AP////////+ZmZ//////////////////////////////////AP/////////5mZ//////////////////////////////////AP//////////mZ//////////////////////////////////AP//////////+f//////////////////////////////////AP//////////////////////////////////////////////AP//////////////////////////////////////////////AP//////////////////////////////////////////////AP//////////////////////////////////////////////AP//////////////////////////////////////////////AP//////////////////////////////////////////////AA==";
    }

    public record ApiResult<T>(T Data, Integer Code) {
    }

    private record FileCacheData(byte[] bytes, String mime) {
    }

    private record CacheItem<T>(T value, long expireAt) {
    }

    private enum PathProtocol {
        LOCAL,
        UNC,
        HTTP,
        HTTPS,
        FTP,
        UNKNOWN
    }
}