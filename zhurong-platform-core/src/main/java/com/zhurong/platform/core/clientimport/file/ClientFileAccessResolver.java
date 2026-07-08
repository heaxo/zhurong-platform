package com.zhurong.platform.core.clientimport.file;

import com.zhurong.platform.base.exception.BusinessException;
import com.zhurong.platform.core.clientimport.configuration.ConditionalOnClientCommunicationEnabled;
import com.zhurong.platform.core.clientimport.configuration.ImportFileProperties;
import com.zhurong.platform.core.clientimport.enums.ClientAccessType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@ConditionalOnClientCommunicationEnabled
@RequiredArgsConstructor
public class ClientFileAccessResolver {

    private final ImportFileProperties properties;

    public ClientAccess resolve(String fileId, String storedRelativePath) {
        if (!StringUtils.hasText(properties.getWindowsShareRoot())) {
            throw new BusinessException("IMPORT_FILE_SHARE_ROOT_EMPTY: 需要配置zhurong.import-file.windows-share-root");
        }
        // core只负责把文件保存到storage-root，再按共享目录根路径拼出客户端可访问的UNC路径。
        String root = trimShareRoot(properties.getWindowsShareRoot());
        return new ClientAccess(ClientAccessType.SHARE, root + "\\" + storedRelativePath.replace('/', '\\'));
    }

    private String trimShareRoot(String value) {
        return value.replaceAll("[\\\\/]+$", "");
    }

    public record ClientAccess(ClientAccessType type, String path) {
    }
}
