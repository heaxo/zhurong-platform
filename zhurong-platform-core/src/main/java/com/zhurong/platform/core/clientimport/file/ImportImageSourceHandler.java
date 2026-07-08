package com.zhurong.platform.core.clientimport.file;

public interface ImportImageSourceHandler {

    boolean supports(String image);

    StoredImportFile store(String requestId, String image, ImportFileStorageContext context);
}
