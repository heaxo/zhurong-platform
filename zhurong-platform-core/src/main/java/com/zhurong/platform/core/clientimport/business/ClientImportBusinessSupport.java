package com.zhurong.platform.core.clientimport.business;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhurong.platform.base.exception.BusinessException;
import com.zhurong.platform.core.clientimport.configuration.ConditionalOnClientCommunicationEnabled;
import com.zhurong.platform.core.clientimport.entity.ClientImportRecordBase;
import com.zhurong.platform.core.clientimport.enums.DispatchStatus;
import com.zhurong.platform.core.clientimport.file.ImageSourceSanitizer;
import com.zhurong.platform.core.clientimport.file.StoredImportFile;
import com.zhurong.platform.core.clientimport.mq.ClientImportTaskPayloadItem;
import com.zhurong.platform.core.clientimport.service.EntityAuditHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Map;

@Component
@ConditionalOnClientCommunicationEnabled
@RequiredArgsConstructor
public class ClientImportBusinessSupport {

    private final ObjectMapper objectMapper;

    public void fillCommonFields(
            ClientImportRecordBase entity,
            String requestId,
            int requestItemIndex,
            String taskId,
            String targetClientId,
            String originalImage,
            Map<String, Object> extensions,
            StoredImportFile storedFile) {

        entity.setRequestId(requestId);
        entity.setRequestItemIndex(requestItemIndex);
        entity.setTaskId(taskId);
        entity.setTargetClientId(targetClientId);
        entity.setOriginalImage(ImageSourceSanitizer.mask(originalImage));
        entity.setExtensionsJson(writeJson(extensions));
        entity.setImported(false);
        entity.setDispatchStatus(DispatchStatus.PENDING_DISPATCH.name());
        entity.setDispatchMessage("数据已保存，客户端任务等待派发");
        if (storedFile != null) {
            entity.setStoredFileId(storedFile.getFileId());
            entity.setStoredRelativePath(storedFile.getStoredRelativePath());
            entity.setClientImagePath(storedFile.getClientAccessPath());
        }
        EntityAuditHelper.prepareInsert(entity);
    }

    public Map<String, Object> readExtensions(String extensionsJson) {
        if (!StringUtils.hasText(extensionsJson)) {
            return null;
        }
        try {
            return objectMapper.readValue(extensionsJson, new TypeReference<>() {
            });
        } catch (JsonProcessingException ex) {
            throw new BusinessException("JSON_DESERIALIZE_FAILED: 扩展字段反序列化失败：" + ex.getMessage());
        }
    }

    public <T> ClientImportTaskPayloadItem<T> payloadItem(Long recordId, Integer requestItemIndex, T data) {
        ClientImportTaskPayloadItem<T> item = new ClientImportTaskPayloadItem<>();
        item.setRecordId(recordId);
        item.setRequestItemIndex(requestItemIndex);
        item.setData(data);
        return item;
    }

    private String writeJson(Object value) {
        if (value == null) {
            return null;
        }
        try {
            return objectMapper.writeValueAsString(value);
        } catch (JsonProcessingException ex) {
            throw new BusinessException("JSON_SERIALIZE_FAILED: JSON序列化失败：" + ex.getMessage());
        }
    }
}
