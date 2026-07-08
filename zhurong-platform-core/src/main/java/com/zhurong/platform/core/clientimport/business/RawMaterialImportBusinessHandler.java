package com.zhurong.platform.core.clientimport.business;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zhurong.platform.core.clientimport.configuration.ConditionalOnClientCommunicationEnabled;
import com.zhurong.platform.core.clientimport.dto.ClientImportRequest;
import com.zhurong.platform.core.clientimport.dto.RawMaterialRequest;
import com.zhurong.platform.core.clientimport.entity.RawMaterial;
import com.zhurong.platform.core.clientimport.enums.DispatchStatus;
import com.zhurong.platform.core.clientimport.file.StoredImportFile;
import com.zhurong.platform.core.clientimport.mq.ClientImportBusinessTypes;
import com.zhurong.platform.core.clientimport.mq.ClientImportTaskPayloadItem;
import com.zhurong.platform.core.clientimport.service.RawMaterialService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Set;

@Component
@ConditionalOnClientCommunicationEnabled
@RequiredArgsConstructor
public class RawMaterialImportBusinessHandler
        implements ClientImportBusinessHandler<RawMaterialRequest, RawMaterial> {

    private final RawMaterialService service;
    private final ClientImportBusinessSupport support;

    @Override
    public String businessType() {
        return ClientImportBusinessTypes.RAW_MATERIAL;
    }

    @Override
    public String businessKeyName() {
        return "prdRef";
    }

    @Override
    public String businessKey(RawMaterialRequest data) {
        return data.getPrdRef();
    }

    @Override
    public String materialRef(RawMaterialRequest data) {
        return data.getMatRef();
    }

    @Override
    public String image(RawMaterialRequest data) {
        return data.getImage();
    }

    @Override
    public List<RawMaterial> findExistingByBusinessKeys(Set<String> businessKeys) {
        if (CollectionUtils.isEmpty(businessKeys)) {
            return List.of();
        }
        return service.list(Wrappers.lambdaQuery(RawMaterial.class)
                .in(RawMaterial::getPrdRef, businessKeys));
    }

    @Override
    public String recordBusinessKey(RawMaterial record) {
        return record.getPrdRef();
    }

    @Override
    public void save(
            String requestId,
            ClientImportRequest<List<RawMaterialRequest>> request,
            RawMaterialRequest data,
            int requestItemIndex,
            String taskId,
            String targetClientId,
            StoredImportFile storedFile) {

        RawMaterial entity = new RawMaterial();
        support.fillCommonFields(entity, requestId, requestItemIndex, taskId, targetClientId,
                data.getImage(), data.getExtensions(), storedFile);
        entity.setPrdRef(data.getPrdRef());
        entity.setPrdName(data.getPrdName());
        entity.setMatRef(data.getMatRef());
        entity.setThickness(data.getThickness());
        entity.setLength(data.getLength());
        entity.setWidth(data.getWidth());
        entity.setQuantity(data.getQuantity());
        entity.setUdata1(data.getUdata1());
        entity.setUdata2(data.getUdata2());
        entity.setUdata3(data.getUdata3());
        entity.setIsRemnant(StringUtils.hasText(data.getImage()));
        service.save(entity);
    }

    @Override
    public void updateNotImportedRows(String requestId, DispatchStatus status, String message) {
        service.update(Wrappers.lambdaUpdate(RawMaterial.class)
                .set(RawMaterial::getDispatchStatus, status.name())
                .set(RawMaterial::getDispatchMessage, message)
                .eq(RawMaterial::getRequestId, requestId)
                .eq(RawMaterial::getImported, false));
    }

    @Override
    public void markImportedRows(String requestId, List<Long> importedRecordIds, String message) {
        if (CollectionUtils.isEmpty(importedRecordIds)) {
            return;
        }
        service.update(Wrappers.lambdaUpdate(RawMaterial.class)
                .set(RawMaterial::getImported, true)
                .set(RawMaterial::getDispatchStatus, DispatchStatus.IMPORTED.name())
                .set(RawMaterial::getDispatchMessage, message)
                .eq(RawMaterial::getRequestId, requestId)
                .in(RawMaterial::getId, importedRecordIds));
    }

    @Override
    public long countNotImported(String requestId) {
        return service.count(Wrappers.lambdaQuery(RawMaterial.class)
                .eq(RawMaterial::getRequestId, requestId)
                .eq(RawMaterial::getImported, false));
    }

    @Override
    public List<ClientImportTaskPayloadItem<RawMaterialRequest>> listPendingPayload(String requestId) {
        return service.list(Wrappers.lambdaQuery(RawMaterial.class)
                        .eq(RawMaterial::getRequestId, requestId)
                        .eq(RawMaterial::getImported, false)
                        .orderByAsc(RawMaterial::getRequestItemIndex))
                .stream()
                .map(this::toPayloadItem)
                .toList();
    }

    private ClientImportTaskPayloadItem<RawMaterialRequest> toPayloadItem(RawMaterial entity) {
        RawMaterialRequest data = new RawMaterialRequest();
        data.setPrdRef(entity.getPrdRef());
        data.setPrdName(entity.getPrdName());
        data.setMatRef(entity.getMatRef());
        data.setThickness(entity.getThickness());
        data.setLength(entity.getLength());
        data.setWidth(entity.getWidth());
        data.setQuantity(entity.getQuantity());
        data.setUdata1(entity.getUdata1());
        data.setUdata2(entity.getUdata2());
        data.setUdata3(entity.getUdata3());
        data.setImage(entity.getClientImagePath());
        data.setExtensions(support.readExtensions(entity.getExtensionsJson()));
        return support.payloadItem(entity.getId(), entity.getRequestItemIndex(), data);
    }
}
