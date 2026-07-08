package com.zhurong.platform.core.clientimport.file;

import com.zhurong.platform.core.clientimport.configuration.ImportFileProperties;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ImportFileStorageContext {

    private final ImportFileProperties properties;
}
