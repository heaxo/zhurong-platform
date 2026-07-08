package com.zhurong.platform.core.clientimport.file;

import com.zhurong.platform.core.clientimport.enums.ClientAccessType;
import com.zhurong.platform.core.clientimport.enums.ImageSourceType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StoredImportFile {

    private final String fileId;

    private final ImageSourceType sourceType;

    private final String originalImage;

    private final String originalFileName;

    private final String storedRelativePath;

    private final ClientAccessType clientAccessType;

    private final String clientAccessPath;

    private final String contentType;

    private final long fileSize;

    private final String sha256;
}
