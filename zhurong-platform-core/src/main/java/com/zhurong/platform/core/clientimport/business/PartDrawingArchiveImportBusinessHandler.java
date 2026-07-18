package com.zhurong.platform.core.clientimport.business;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zhurong.platform.core.clientimport.configuration.ConditionalOnClientCommunicationEnabled;
import com.zhurong.platform.core.clientimport.dto.ClientImportRequest;
import com.zhurong.platform.base.clientimport.dto.PartDrawingArchiveRequest;
import com.zhurong.platform.core.clientimport.entity.PartDrawingArchive;
import com.zhurong.platform.core.clientimport.enums.DispatchStatus;
import com.zhurong.platform.core.clientimport.file.StoredImportFile;
import com.zhurong.platform.core.clientimport.mq.ClientImportBusinessTypes;
import com.zhurong.platform.core.clientimport.mq.ClientImportTaskPayloadItem;
import com.zhurong.platform.core.clientimport.service.PartDrawingArchiveService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;

@Component
@ConditionalOnClientCommunicationEnabled
@RequiredArgsConstructor
public class PartDrawingArchiveImportBusinessHandler
        implements ClientImportBusinessHandler<PartDrawingArchiveRequest, PartDrawingArchive> {

    private final PartDrawingArchiveService service;
    private final ClientImportBusinessSupport support;

    @Override
    public String businessType() {
        return ClientImportBusinessTypes.PART_DRAWING_ARCHIVE;
    }

    @Override
    public String businessKeyName() {
        return "prdRef";
    }

    @Override
    public String businessKey(PartDrawingArchiveRequest data) {
        return data.getPrdRef();
    }

    @Override
    public String materialRef(PartDrawingArchiveRequest data) {
        return data.getMatRef();
    }

    @Override
    public String machineRef(PartDrawingArchiveRequest data) {
        return data.getWrkRef();
    }

    @Override
    public String image(PartDrawingArchiveRequest data) {
        return data.getImage();
    }

    @Override
    public List<PartDrawingArchive> findExistingByBusinessKeys(Set<String> businessKeys) {
        if (CollectionUtils.isEmpty(businessKeys)) {
            return List.of();
        }
        return service.list(Wrappers.lambdaQuery(PartDrawingArchive.class)
                .in(PartDrawingArchive::getPrdRef, businessKeys));
    }

    @Override
    public String recordBusinessKey(PartDrawingArchive record) {
        return record.getPrdRef();
    }

    @Override
    public void save(
            String requestId,
            ClientImportRequest<List<PartDrawingArchiveRequest>> request,
            PartDrawingArchiveRequest data,
            int requestItemIndex,
            String taskId,
            String targetClientId,
            StoredImportFile storedFile) {

        PartDrawingArchive entity = new PartDrawingArchive();
        support.fillCommonFields(entity, requestId, requestItemIndex, taskId, targetClientId,
                data.getImage(), data.getExtensions(), storedFile);
        entity.setPrdRef(data.getPrdRef());
        entity.setPrdName(data.getPrdName());
        entity.setMatRef(data.getMatRef());
        entity.setThickness(data.getThickness());
        entity.setWrkRef(data.getWrkRef());
        entity.setUdata1(data.getUdata1());
        entity.setUdata2(data.getUdata2());
        entity.setUdata3(data.getUdata3());
        entity.setUdata4(data.getUdata4());
        entity.setUdata5(data.getUdata5());
        entity.setUdata6(data.getUdata6());
        entity.setUdata7(data.getUdata7());
        entity.setUdata8(data.getUdata8());
        service.save(entity);
    }

    @Override
    public void updateNotImportedRows(String requestId, DispatchStatus status, String message) {
        service.update(Wrappers.lambdaUpdate(PartDrawingArchive.class)
                .set(PartDrawingArchive::getDispatchStatus, status.name())
                .set(PartDrawingArchive::getDispatchMessage, message)
                .eq(PartDrawingArchive::getRequestId, requestId)
                .eq(PartDrawingArchive::getImported, false));
    }

    @Override
    public void markImportedRows(String requestId, List<Long> importedRecordIds, String message) {
        if (CollectionUtils.isEmpty(importedRecordIds)) {
            return;
        }
        service.update(Wrappers.lambdaUpdate(PartDrawingArchive.class)
                .set(PartDrawingArchive::getImported, true)
                .set(PartDrawingArchive::getDispatchStatus, DispatchStatus.IMPORTED.name())
                .set(PartDrawingArchive::getDispatchMessage, message)
                .eq(PartDrawingArchive::getRequestId, requestId)
                .in(PartDrawingArchive::getId, importedRecordIds));
    }

    @Override
    public long countNotImported(String requestId) {
        return service.count(Wrappers.lambdaQuery(PartDrawingArchive.class)
                .eq(PartDrawingArchive::getRequestId, requestId)
                .eq(PartDrawingArchive::getImported, false));
    }

    @Override
    public List<ClientImportTaskPayloadItem<PartDrawingArchiveRequest>> listPendingPayload(String requestId) {
        return service.list(Wrappers.lambdaQuery(PartDrawingArchive.class)
                        .eq(PartDrawingArchive::getRequestId, requestId)
                        .eq(PartDrawingArchive::getImported, false)
                        .orderByAsc(PartDrawingArchive::getRequestItemIndex))
                .stream()
                .map(this::toPayloadItem)
                .toList();
    }

    private ClientImportTaskPayloadItem<PartDrawingArchiveRequest> toPayloadItem(PartDrawingArchive entity) {
        PartDrawingArchiveRequest data = new PartDrawingArchiveRequest();
        data.setPrdRef(entity.getPrdRef());
        data.setPrdName(entity.getPrdName());
        data.setMatRef(entity.getMatRef());
        data.setThickness(entity.getThickness());
        data.setWrkRef(entity.getWrkRef());
        data.setImage(entity.getClientImagePath());
        data.setUdata1(entity.getUdata1());
        data.setUdata2(entity.getUdata2());
        data.setUdata3(entity.getUdata3());
        data.setUdata4(entity.getUdata4());
        data.setUdata5(entity.getUdata5());
        data.setUdata6(entity.getUdata6());
        data.setUdata7(entity.getUdata7());
        data.setUdata8(entity.getUdata8());
        data.setExtensions(support.readExtensions(entity.getExtensionsJson()));
        return support.payloadItem(entity.getId(), entity.getRequestItemIndex(), data);
    }
}
