package com.zhurong.platform.core.clientimport.file;

import com.zhurong.platform.base.exception.BusinessException;
import com.zhurong.platform.core.clientimport.configuration.ConditionalOnClientCommunicationEnabled;
import com.zhurong.platform.core.clientimport.configuration.ImportFileProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
@ConditionalOnClientCommunicationEnabled
@RequiredArgsConstructor
public class ImportFileStorageInitializer implements InitializingBean {

    private final ImportFileProperties properties;

    @Override
    public void afterPropertiesSet() {
        if (!StringUtils.hasText(properties.getStorageRoot())) {
            throw new BusinessException("IMPORT_FILE_STORAGE_ROOT_EMPTY: 请配置zhurong.import-file.storage-root");
        }

        Path root = Path.of(properties.getStorageRoot()).toAbsolutePath().normalize();
        try {
            if (Files.notExists(root)) {
                if (!properties.isAutoCreateStorageRoot()) {
                    throw new BusinessException("IMPORT_FILE_STORAGE_ROOT_NOT_EXISTS: 文件归档目录不存在");
                }
                Files.createDirectories(root);
            }
        } catch (IOException ex) {
            throw new BusinessException("IMPORT_FILE_STORAGE_ROOT_CREATE_FAILED: 文件归档目录创建失败：" + ex.getMessage());
        }

        if (!Files.isDirectory(root) || !Files.isWritable(root)) {
            throw new BusinessException("IMPORT_FILE_STORAGE_ROOT_NOT_WRITABLE: 文件归档目录不可写");
        }
        // Windows开发环境可配置D:/Zhurong/import-files，Docker/WSL环境可配置/app/data/import-files，代码不写死任一路径。
    }
}
