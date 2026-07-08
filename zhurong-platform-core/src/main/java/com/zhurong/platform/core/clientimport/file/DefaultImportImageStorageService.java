package com.zhurong.platform.core.clientimport.file;

import com.zhurong.platform.base.exception.BusinessException;
import com.zhurong.platform.core.clientimport.configuration.ConditionalOnClientCommunicationEnabled;
import com.zhurong.platform.core.clientimport.configuration.ImportFileProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@ConditionalOnClientCommunicationEnabled
@RequiredArgsConstructor
public class DefaultImportImageStorageService implements ImportImageStorageService {

    /*
     * 文件来源处理器按supports顺序匹配。
     * 新增来源类型时只需要新增ImportImageSourceHandler实现，不要把HTTP/FTP/共享/Base64逻辑堆到一个类里。
     */
    private final List<ImportImageSourceHandler> handlers;
    private final ImportFileProperties properties;

    @Override
    public StoredImportFile resolveAndStore(String requestId, Integer requestItemIndex, String image) {
        if (!StringUtils.hasText(image)) {
            return null;
        }

        ImportFileStorageContext context = ImportFileStorageContext.builder()
                .properties(properties)
                .build();

        StoredImportFile storedImportFile = handlers.stream()
                .filter(handler -> handler.supports(image))
                .findFirst()
                .orElseThrow(() -> new BusinessException("UNSUPPORTED_IMAGE_SOURCE: image仅支持HTTP、FTP、Windows共享路径或严格Base64"))
                .store(requestId, image, context);

        return storedImportFile;
    }
}
