package com.zhurong.platform.custom.drawing;

import java.io.IOException;

public interface DrawingDownloadStrategy {

    boolean supports(String drawingPath);

    DrawingDownloadSource open(String drawingPath) throws IOException;
}
