package com.zhurong.platform.core.clientimport.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhurong.platform.base.exception.BusinessException;
import com.zhurong.platform.core.clientimport.configuration.ConditionalOnClientCommunicationEnabled;
import com.zhurong.platform.core.clientimport.dto.PartDrawingArchiveRequest;
import com.zhurong.platform.core.clientimport.dto.ProductionOrderRequest;
import com.zhurong.platform.core.clientimport.dto.RawMaterialRequest;
import com.zhurong.platform.core.clientimport.entity.ClientDispatchTask;
import com.zhurong.platform.core.clientimport.entity.PartDrawingArchive;
import com.zhurong.platform.core.clientimport.entity.ProductionOrder;
import com.zhurong.platform.core.clientimport.entity.RawMaterial;
import com.zhurong.platform.core.clientimport.enums.DispatchStatus;
import com.zhurong.platform.core.clientimport.mq.ClientDispatchPublishService;
import com.zhurong.platform.core.clientimport.mq.ClientImportBusinessTypes;
import com.zhurong.platform.core.clientimport.mq.ClientImportTaskMessage;
import com.zhurong.platform.core.clientimport.mq.ClientImportTaskPayloadItem;
import com.zhurong.platform.core.clientimport.mq.ClientImportTaskStatusMessage;
import com.zhurong.platform.core.clientimport.service.ClientDispatchTaskService;
import com.zhurong.platform.core.clientimport.service.ClientImportTaskRuntimeService;
import com.zhurong.platform.core.clientimport.service.ClientRegistryService;
import com.zhurong.platform.core.clientimport.service.EntityAuditHelper;
import com.zhurong.platform.core.clientimport.service.PartDrawingArchiveService;
import com.zhurong.platform.core.clientimport.service.ProductionOrderService;
import com.zhurong.platform.core.clientimport.service.RawMaterialService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@ConditionalOnClientCommunicationEnabled
@RequiredArgsConstructor
public class ClientImportTaskRuntimeServiceImpl implements ClientImportTaskRuntimeService {

    private static final int MAX_IMPORT_RETRY_COUNT = 3;

    private final ObjectMapper objectMapper;
    private final ClientRegistryService clientRegistryService;
    private final ClientDispatchTaskService dispatchTaskService;
    private final ClientDispatchPublishService dispatchPublishService;
    private final PartDrawingArchiveService partDrawingArchiveService;
    private final ProductionOrderService productionOrderService;
    private final RawMaterialService rawMaterialService;

    @Override
    public ClientImportTaskMessage getPendingData(String taskId) {
        ClientDispatchTask task = getRequiredTask(taskId);
        return buildPendingTaskMessage(task);
    }

    @Override
    public boolean handleStatus(ClientImportTaskStatusMessage statusMessage) {
        clientRegistryService.heartbeat(statusMessage);
        if (statusMessage.getTaskId() == null || statusMessage.getTaskId().startsWith("PING-")) {
            log.info("收到客户端状态 clientId={}, status={}, message={}",
                    statusMessage.getClientId(), statusMessage.getStatus(), statusMessage.getMessage());
            return true;
        }

        ClientDispatchTask task = dispatchTaskService.getByTaskId(statusMessage.getTaskId());
        if (task == null) {
            return false;
        }

        markImportedRows(task, statusMessage.getImportedRecordIds());
        updateRunningRows(task, statusMessage);
        boolean shouldRetry = updateTaskStatus(task, statusMessage);
        if (shouldRetry) {
            // 重试只重新发送taskId通知；客户端再次通过Feign读取SQL Server中的当前未导入数据。
            dispatchPublishService.publish(task.getTaskId());
        }
        return true;
    }

    private boolean updateTaskStatus(ClientDispatchTask task, ClientImportTaskStatusMessage statusMessage) {
        boolean terminal = DispatchStatus.SUCCESS.name().equals(statusMessage.getStatus())
                || DispatchStatus.FAILED.name().equals(statusMessage.getStatus())
                || DispatchStatus.TIMEOUT.name().equals(statusMessage.getStatus());

        boolean shouldRetry = false;
        String nextStatus = statusMessage.getStatus();
        String nextMessage = statusMessage.getMessage();
        if (terminal) {
            long remaining = countNotImported(task);
            if (remaining == 0) {
                nextStatus = DispatchStatus.SUCCESS.name();
                nextMessage = "客户端批量导入完成";
            } else {
                int retryCount = task.getRetryCount() == null ? 0 : task.getRetryCount();
                if (retryCount < MAX_IMPORT_RETRY_COUNT) {
                    int nextRetryCount = retryCount + 1;
                    task.setRetryCount(nextRetryCount);
                    nextStatus = DispatchStatus.PENDING_DISPATCH.name();
                    nextMessage = "客户端批量导入未完成，剩余未导入数量：" + remaining
                            + "，准备第" + nextRetryCount + "次重试";
                    updateRowsNotImported(task, DispatchStatus.PENDING_DISPATCH, nextMessage);
                    shouldRetry = true;
                } else {
                    nextStatus = DispatchStatus.FAILED.name();
                    nextMessage = "客户端批量导入未完成，已达到最大重试次数，剩余未导入数量：" + remaining;
                    markRemainingRowsFailed(task, nextMessage);
                }
            }
        }

        task.setStatus(nextStatus);
        task.setResponseSnapshot(writeJson(statusMessage));
        task.setErrorMessage(nextMessage);
        if (DispatchStatus.RECEIVED.name().equals(nextStatus)) {
            task.setReceiveTime(LocalDateTime.now());
        }
        if (DispatchStatus.SUCCESS.name().equals(nextStatus)
                || DispatchStatus.FAILED.name().equals(nextStatus)
                || DispatchStatus.TIMEOUT.name().equals(nextStatus)) {
            task.setFinishTime(LocalDateTime.now());
        }
        EntityAuditHelper.prepareUpdate(task);
        dispatchTaskService.updateById(task);
        return shouldRetry;
    }

    private void markImportedRows(ClientDispatchTask task, List<Long> importedRecordIds) {
        if (CollectionUtils.isEmpty(importedRecordIds)) {
            return;
        }
        String message = "客户端已确认导入";
        switch (task.getBusinessType()) {
            case ClientImportBusinessTypes.PART_DRAWING_ARCHIVE -> partDrawingArchiveService.update(Wrappers.lambdaUpdate(PartDrawingArchive.class)
                    .set(PartDrawingArchive::getImported, true)
                    .set(PartDrawingArchive::getDispatchStatus, DispatchStatus.IMPORTED.name())
                    .set(PartDrawingArchive::getDispatchMessage, message)
                    .eq(PartDrawingArchive::getRequestId, task.getRequestId())
                    .in(PartDrawingArchive::getId, importedRecordIds));
            case ClientImportBusinessTypes.PRODUCTION_ORDER -> productionOrderService.update(Wrappers.lambdaUpdate(ProductionOrder.class)
                    .set(ProductionOrder::getImported, true)
                    .set(ProductionOrder::getDispatchStatus, DispatchStatus.IMPORTED.name())
                    .set(ProductionOrder::getDispatchMessage, message)
                    .eq(ProductionOrder::getRequestId, task.getRequestId())
                    .in(ProductionOrder::getId, importedRecordIds));
            case ClientImportBusinessTypes.RAW_MATERIAL -> rawMaterialService.update(Wrappers.lambdaUpdate(RawMaterial.class)
                    .set(RawMaterial::getImported, true)
                    .set(RawMaterial::getDispatchStatus, DispatchStatus.IMPORTED.name())
                    .set(RawMaterial::getDispatchMessage, message)
                    .eq(RawMaterial::getRequestId, task.getRequestId())
                    .in(RawMaterial::getId, importedRecordIds));
            default -> {
            }
        }
    }

    private void updateRunningRows(ClientDispatchTask task, ClientImportTaskStatusMessage statusMessage) {
        if (!DispatchStatus.RECEIVED.name().equals(statusMessage.getStatus())
                && !DispatchStatus.RUNNING.name().equals(statusMessage.getStatus())) {
            return;
        }
        updateRowsNotImported(task, DispatchStatus.valueOf(statusMessage.getStatus()), statusMessage.getMessage());
    }

    private void markRemainingRowsFailed(ClientDispatchTask task, String message) {
        updateRowsNotImported(task, DispatchStatus.FAILED, message);
    }

    private void updateRowsNotImported(ClientDispatchTask task, DispatchStatus status, String message) {
        switch (task.getBusinessType()) {
            case ClientImportBusinessTypes.PART_DRAWING_ARCHIVE -> partDrawingArchiveService.update(Wrappers.lambdaUpdate(PartDrawingArchive.class)
                    .set(PartDrawingArchive::getDispatchStatus, status.name())
                    .set(PartDrawingArchive::getDispatchMessage, message)
                    .eq(PartDrawingArchive::getRequestId, task.getRequestId())
                    .eq(PartDrawingArchive::getImported, false));
            case ClientImportBusinessTypes.PRODUCTION_ORDER -> productionOrderService.update(Wrappers.lambdaUpdate(ProductionOrder.class)
                    .set(ProductionOrder::getDispatchStatus, status.name())
                    .set(ProductionOrder::getDispatchMessage, message)
                    .eq(ProductionOrder::getRequestId, task.getRequestId())
                    .eq(ProductionOrder::getImported, false));
            case ClientImportBusinessTypes.RAW_MATERIAL -> rawMaterialService.update(Wrappers.lambdaUpdate(RawMaterial.class)
                    .set(RawMaterial::getDispatchStatus, status.name())
                    .set(RawMaterial::getDispatchMessage, message)
                    .eq(RawMaterial::getRequestId, task.getRequestId())
                    .eq(RawMaterial::getImported, false));
            default -> {
            }
        }
    }

    private long countNotImported(ClientDispatchTask task) {
        return switch (task.getBusinessType()) {
            case ClientImportBusinessTypes.PART_DRAWING_ARCHIVE -> partDrawingArchiveService.count(Wrappers.lambdaQuery(PartDrawingArchive.class)
                    .eq(PartDrawingArchive::getRequestId, task.getRequestId())
                    .eq(PartDrawingArchive::getImported, false));
            case ClientImportBusinessTypes.PRODUCTION_ORDER -> productionOrderService.count(Wrappers.lambdaQuery(ProductionOrder.class)
                    .eq(ProductionOrder::getRequestId, task.getRequestId())
                    .eq(ProductionOrder::getImported, false));
            case ClientImportBusinessTypes.RAW_MATERIAL -> rawMaterialService.count(Wrappers.lambdaQuery(RawMaterial.class)
                    .eq(RawMaterial::getRequestId, task.getRequestId())
                    .eq(RawMaterial::getImported, false));
            default -> 0L;
        };
    }

    private ClientImportTaskMessage buildPendingTaskMessage(ClientDispatchTask task) {
        ClientImportTaskMessage message = new ClientImportTaskMessage();
        message.setTaskId(task.getTaskId());
        message.setRequestId(task.getRequestId());
        message.setTargetClientId(task.getTargetClientId());
        message.setBusinessType(task.getBusinessType());
        message.setSchemaVersion("1.0");
        message.setCreateTime(Instant.now());
        message.setPayload(objectMapper.valueToTree(buildPendingPayload(task)));
        return message;
    }

    private List<?> buildPendingPayload(ClientDispatchTask task) {
        return switch (task.getBusinessType()) {
            case ClientImportBusinessTypes.PART_DRAWING_ARCHIVE -> partDrawingArchiveService.list(Wrappers.lambdaQuery(PartDrawingArchive.class)
                            .eq(PartDrawingArchive::getRequestId, task.getRequestId())
                            .eq(PartDrawingArchive::getImported, false)
                            .orderByAsc(PartDrawingArchive::getRequestItemIndex))
                    .stream()
                    .map(this::toPayloadItem)
                    .toList();
            case ClientImportBusinessTypes.PRODUCTION_ORDER -> productionOrderService.list(Wrappers.lambdaQuery(ProductionOrder.class)
                            .eq(ProductionOrder::getRequestId, task.getRequestId())
                            .eq(ProductionOrder::getImported, false)
                            .orderByAsc(ProductionOrder::getRequestItemIndex))
                    .stream()
                    .map(this::toPayloadItem)
                    .toList();
            case ClientImportBusinessTypes.RAW_MATERIAL -> rawMaterialService.list(Wrappers.lambdaQuery(RawMaterial.class)
                            .eq(RawMaterial::getRequestId, task.getRequestId())
                            .eq(RawMaterial::getImported, false)
                            .orderByAsc(RawMaterial::getRequestItemIndex))
                    .stream()
                    .map(this::toPayloadItem)
                    .toList();
            default -> List.of();
        };
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
        data.setExtensions(readExtensions(entity.getExtensionsJson()));
        return toPayloadItem(entity.getId(), entity.getRequestItemIndex(), data);
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
        data.setExtensions(readExtensions(entity.getExtensionsJson()));
        return toPayloadItem(entity.getId(), entity.getRequestItemIndex(), data);
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
        data.setExtensions(readExtensions(entity.getExtensionsJson()));
        return toPayloadItem(entity.getId(), entity.getRequestItemIndex(), data);
    }

    private <T> ClientImportTaskPayloadItem<T> toPayloadItem(Long recordId, Integer requestItemIndex, T data) {
        ClientImportTaskPayloadItem<T> item = new ClientImportTaskPayloadItem<>();
        item.setRecordId(recordId);
        item.setRequestItemIndex(requestItemIndex);
        item.setData(data);
        return item;
    }

    private ClientDispatchTask getRequiredTask(String taskId) {
        ClientDispatchTask task = dispatchTaskService.getByTaskId(taskId);
        if (task == null) {
            throw new BusinessException("CLIENT_DISPATCH_TASK_NOT_FOUND: 派发任务不存在");
        }
        return task;
    }

    private Map<String, Object> readExtensions(String extensionsJson) {
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
