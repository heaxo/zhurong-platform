package com.zhurong.platform.core.clientimport.business;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zhurong.platform.base.clientimport.dto.ProductionOrderRequest;
import com.zhurong.platform.core.clientimport.configuration.ConditionalOnClientCommunicationEnabled;
import com.zhurong.platform.core.clientimport.dto.ClientImportRequest;
import com.zhurong.platform.core.clientimport.entity.ProductionOrder;
import com.zhurong.platform.core.clientimport.enums.DispatchStatus;
import com.zhurong.platform.core.clientimport.file.StoredImportFile;
import com.zhurong.platform.core.clientimport.mq.ClientImportBusinessTypes;
import com.zhurong.platform.core.clientimport.mq.ClientImportTaskPayloadItem;
import com.zhurong.platform.core.clientimport.service.ProductionOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;

@Component
@ConditionalOnClientCommunicationEnabled
@RequiredArgsConstructor
public class ProductionOrderImportBusinessHandler
        implements ClientImportBusinessHandler<ProductionOrderRequest, ProductionOrder> {

    private final ProductionOrderService service;
    private final ClientImportBusinessSupport support;

    @Override
    public String businessType() {
        return ClientImportBusinessTypes.PRODUCTION_ORDER;
    }

    @Override
    public String businessKeyName() {
        return "mnORef";
    }

    @Override
    public String businessKey(ProductionOrderRequest data) {
        return data.getMnORef();
    }

    @Override
    public String materialRef(ProductionOrderRequest data) {
        return data.getMatRef();
    }

    @Override
    public String machineRef(ProductionOrderRequest data) {
        return data.getWrkRef();
    }

    @Override
    public String image(ProductionOrderRequest data) {
        return data.getImage();
    }

    @Override
    public List<ProductionOrder> findExistingByBusinessKeys(Set<String> businessKeys) {
        if (CollectionUtils.isEmpty(businessKeys)) {
            return List.of();
        }
        return service.list(Wrappers.lambdaQuery(ProductionOrder.class)
                .in(ProductionOrder::getMnORef, businessKeys));
    }

    @Override
    public String recordBusinessKey(ProductionOrder record) {
        return record.getMnORef();
    }

    @Override
    public void save(
            String requestId,
            ClientImportRequest<List<ProductionOrderRequest>> request,
            ProductionOrderRequest data,
            int requestItemIndex,
            String taskId,
            String targetClientId,
            StoredImportFile storedFile) {

        ProductionOrder entity = new ProductionOrder();
        support.fillCommonFields(entity, requestId, requestItemIndex, taskId, targetClientId,
                data.getImage(), data.getExtensions(), storedFile);
        entity.setPrdRef(data.getPrdRef());
        entity.setPrdName(data.getPrdName());
        entity.setMatRef(data.getMatRef());
        entity.setThickness(data.getThickness());
        entity.setWrkRef(data.getWrkRef());
        entity.setMnORef(data.getMnORef());
        entity.setOrdRef(data.getOrdRef());
        entity.setCusRef(data.getCusRef());
        entity.setQuantity(data.getQuantity());
        service.save(entity);
    }

    @Override
    public void updateNotImportedRows(String requestId, DispatchStatus status, String message) {
        service.update(Wrappers.lambdaUpdate(ProductionOrder.class)
                .set(ProductionOrder::getDispatchStatus, status.name())
                .set(ProductionOrder::getDispatchMessage, message)
                .eq(ProductionOrder::getRequestId, requestId)
                .eq(ProductionOrder::getImported, false));
    }

    @Override
    public void markImportedRows(String requestId, List<Long> importedRecordIds, String message) {
        if (CollectionUtils.isEmpty(importedRecordIds)) {
            return;
        }
        service.update(Wrappers.lambdaUpdate(ProductionOrder.class)
                .set(ProductionOrder::getImported, true)
                .set(ProductionOrder::getDispatchStatus, DispatchStatus.IMPORTED.name())
                .set(ProductionOrder::getDispatchMessage, message)
                .eq(ProductionOrder::getRequestId, requestId)
                .in(ProductionOrder::getId, importedRecordIds));
    }

    @Override
    public long countNotImported(String requestId) {
        return service.count(Wrappers.lambdaQuery(ProductionOrder.class)
                .eq(ProductionOrder::getRequestId, requestId)
                .eq(ProductionOrder::getImported, false));
    }

    @Override
    public List<ClientImportTaskPayloadItem<ProductionOrderRequest>> listPendingPayload(String requestId) {
        return service.list(Wrappers.lambdaQuery(ProductionOrder.class)
                        .eq(ProductionOrder::getRequestId, requestId)
                        .eq(ProductionOrder::getImported, false)
                        .orderByAsc(ProductionOrder::getRequestItemIndex))
                .stream()
                .map(this::toPayloadItem)
                .toList();
    }

    private ClientImportTaskPayloadItem<ProductionOrderRequest> toPayloadItem(ProductionOrder entity) {
        ProductionOrderRequest data = new ProductionOrderRequest();
        data.setPrdRef(entity.getPrdRef());
        data.setPrdName(entity.getPrdName());
        data.setMatRef(entity.getMatRef());
        data.setThickness(entity.getThickness());
        data.setWrkRef(entity.getWrkRef());
        data.setImage(entity.getClientImagePath());
        data.setMnORef(entity.getMnORef());
        data.setOrdRef(entity.getOrdRef());
        data.setCusRef(entity.getCusRef());
        data.setQuantity(entity.getQuantity());
        data.setExtensions(support.readExtensions(entity.getExtensionsJson()));
        return support.payloadItem(entity.getId(), entity.getRequestItemIndex(), data);
    }
}
