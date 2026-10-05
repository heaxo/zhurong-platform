package com.zhurong.platform.custom.drawing;

import com.zhurong.platform.base.exception.BusinessException;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.net.ftp.FTPSClient;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

@Component
@Order(20)
public class FtpDrawingDownloadStrategy implements DrawingDownloadStrategy {

    private static final int DEFAULT_FTP_PORT = 21;
    private static final int DEFAULT_FTPS_PORT = 990;
    private static final int CONNECT_TIMEOUT_MILLIS = (int) Duration.ofSeconds(15).toMillis();
    private static final int READ_TIMEOUT_MILLIS = (int) Duration.ofMinutes(2).toMillis();

    @Override
    public boolean supports(String drawingPath) {
        return StringUtils.hasText(drawingPath)
                && (drawingPath.regionMatches(true, 0, "ftp://", 0, 6)
                || drawingPath.regionMatches(true, 0, "ftps://", 0, 7));
    }

    @Override
    public DrawingDownloadSource open(String drawingPath) throws IOException {
        URI uri;
        try {
            uri = URI.create(drawingPath.trim());
        } catch (IllegalArgumentException ex) {
            throw new BusinessException("DRAWING_FTP_URI_INVALID: invalid FTP drawing URI");
        }

        if (!StringUtils.hasText(uri.getHost())) {
            throw new BusinessException("DRAWING_FTP_HOST_EMPTY: FTP drawing URI host is empty");
        }

        String rawPath = uri.getRawPath();
        if (!StringUtils.hasText(rawPath) || rawPath.endsWith("/")) {
            throw new BusinessException("DRAWING_FTP_PATH_EMPTY: FTP drawing URI file path is empty");
        }
        String remotePath = URLDecoder.decode(rawPath, StandardCharsets.UTF_8);

        boolean ftps = "ftps".equalsIgnoreCase(uri.getScheme());
        FTPClient client = ftps ? new FTPSClient(true) : new FTPClient();
        client.setDefaultTimeout(CONNECT_TIMEOUT_MILLIS);
        client.setConnectTimeout(CONNECT_TIMEOUT_MILLIS);
        client.setDataTimeout(Duration.ofMillis(READ_TIMEOUT_MILLIS));

        boolean opened = false;
        try {
            int port = uri.getPort() > 0 ? uri.getPort() : (ftps ? DEFAULT_FTPS_PORT : DEFAULT_FTP_PORT);
            client.connect(uri.getHost(), port);
            client.setSoTimeout(READ_TIMEOUT_MILLIS);

            if (!FTPReply.isPositiveCompletion(client.getReplyCode())) {
                throw new BusinessException("DRAWING_FTP_CONNECT_FAILED: FTP server refused connection");
            }

            FtpCredentials credentials = credentials(uri.getUserInfo());
            if (!client.login(credentials.username(), credentials.password())) {
                throw new BusinessException("DRAWING_FTP_LOGIN_FAILED: FTP login failed");
            }

            client.enterLocalPassiveMode();
            client.setFileType(FTP.BINARY_FILE_TYPE);

            InputStream inputStream = client.retrieveFileStream(remotePath);
            if (inputStream == null) {
                throw new BusinessException("DRAWING_FTP_FILE_NOT_FOUND: FTP drawing file not found");
            }

            opened = true;
            return new DrawingDownloadSource(
                    fileNameFromPath(remotePath),
                    "application/octet-stream",
                    new FtpResourceInputStream(inputStream, client)
            );
        } finally {
            if (!opened) {
                closeClient(client);
            }
        }
    }

    private static FtpCredentials credentials(String userInfo) {
        if (!StringUtils.hasText(userInfo)) {
            return new FtpCredentials("anonymous", "");
        }
        String[] parts = userInfo.split(":", 2);
        String username = URLDecoder.decode(parts[0], StandardCharsets.UTF_8);
        String password = parts.length > 1 ? URLDecoder.decode(parts[1], StandardCharsets.UTF_8) : "";
        return new FtpCredentials(StringUtils.hasText(username) ? username : "anonymous", password);
    }

    private static String fileNameFromPath(String remotePath) {
        int index = remotePath.lastIndexOf('/');
        return index >= 0 ? remotePath.substring(index + 1) : remotePath;
    }

    private static void closeClient(FTPClient client) {
        if (!client.isConnected()) {
            return;
        }
        try {
            client.logout();
        } catch (IOException ignored) {
        }
        try {
            client.disconnect();
        } catch (IOException ignored) {
        }
    }

    private record FtpCredentials(String username, String password) {
    }

    private static final class FtpResourceInputStream extends FilterInputStream {

        private final FTPClient client;

        private FtpResourceInputStream(InputStream inputStream, FTPClient client) {
            super(inputStream);
            this.client = client;
        }

        @Override
        public void close() throws IOException {
            IOException failure = null;
            try {
                super.close();
                if (!client.completePendingCommand()) {
                    failure = new IOException("FTP pending command failed");
                }
            } catch (IOException ex) {
                failure = ex;
            } finally {
                closeClient(client);
            }
            if (failure != null) {
                throw failure;
            }
        }
    }
}
