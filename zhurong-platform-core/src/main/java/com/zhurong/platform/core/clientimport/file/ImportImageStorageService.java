package com.zhurong.platform.core.clientimport.file;

public interface ImportImageStorageService {

    StoredImportFile resolveAndStore(String requestId, Integer requestItemIndex, String image);
}
