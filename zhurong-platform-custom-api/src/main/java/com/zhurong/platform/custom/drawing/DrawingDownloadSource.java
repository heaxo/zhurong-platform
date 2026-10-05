package com.zhurong.platform.custom.drawing;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public record DrawingDownloadSource(
        String originalFileName,
        String contentType,
        InputStream inputStream
) implements AutoCloseable {

    public DrawingDownloadSource {
        inputStream = Objects.requireNonNull(inputStream, "inputStream");
    }

    @Override
    public void close() throws IOException {
        inputStream.close();
    }
}
