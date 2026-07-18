package com.zhurong.platform.core.clientimport.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.zhurong.platform.base.exception.BusinessException;
import com.zhurong.platform.core.clientimport.business.ClientImportBusinessHandler;
import com.zhurong.platform.core.clientimport.business.ClientImportBusinessRegistry;
import com.zhurong.platform.core.clientimport.configuration.ConditionalOnClientCommunicationEnabled;
import com.zhurong.platform.core.clientimport.dto.ClientImportRequest;
import com.zhurong.platform.base.clientimport.dto.PartDrawingArchiveRequest;
import com.zhurong.platform.core.clientimport.dto.ProductionOrderRequest;
import com.zhurong.platform.core.clientimport.dto.RawMaterialRequest;
import com.zhurong.platform.core.clientimport.entity.ClientDispatchTask;
import com.zhurong.platform.core.clientimport.entity.ClientImportRecordBase;
import com.zhurong.platform.core.clientimport.enums.DispatchStatus;
import com.zhurong.platform.core.clientimport.file.ImageSourceSanitizer;
import com.zhurong.platform.core.clientimport.file.ImportImageStorageService;
import com.zhurong.platform.core.clientimport.file.StoredImportFile;
import com.zhurong.platform.core.clientimport.mq.ClientDispatchPublishService;
import com.zhurong.platform.core.clientimport.mq.ClientImportBusinessTypes;
import com.zhurong.platform.core.clientimport.mq.ClientImportMqConstants;
import com.zhurong.platform.core.clientimport.mq.ClientImportTaskMessage;
import com.zhurong.platform.core.clientimport.service.ClientDispatchTaskService;
import com.zhurong.platform.core.clientimport.service.ClientImportApplicationService;
import com.zhurong.platform.core.clientimport.service.EntityAuditHelper;
import com.zhurong.platform.core.clientimport.target.ClientTargetResolveContext;
import com.zhurong.platform.core.clientimport.target.ClientTargetResolver;
import com.zhurong.platform.core.clientimport.validation.ImportReferenceValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
@ConditionalOnClientCommunicationEnabled
@RequiredArgsConstructor
public class ClientImportApplicationServiceImpl implements ClientImportApplicationService {

    private static final String DEFAULT_SCHEMA_VERSION = "1.0";

    /*
     * requestId由core生成内部批次号，专门用于绑定本次新入库数据和派发任务。
     * 调用方传入的requestId只作为外部追踪号记录日志，不能作为幂等依据；
     * 三类业务分别用prdRef、mnORef、prdRef保证不会重复保存和重复导入。
     */
    private final TransactionTemplate transactionTemplate;
    private final ObjectMapper objectMapper;
    private final ImportImageStorageService imageStorageService;
    private final ClientTargetResolver targetResolver;
    private final ImportReferenceValidator referenceValidator;
    private final ClientDispatchPublishService dispatchPublishService;
    private final ClientDispatchTaskService dispatchTaskService;
    private final ClientImportBusinessRegistry businessRegistry;

    @Override
    public boolean importPartDrawing(ClientImportRequest<List<PartDrawingArchiveRequest>> request) {
        String batchRequestId = newBatchRequestId();
        logIncomingRequest(request, batchRequestId, ClientImportBusinessTypes.PART_DRAWING_ARCHIVE);
        BatchPersistResult result = persistBatch(
                batchRequestId,
                request,
                businessRegistry.get(ClientImportBusinessTypes.PART_DRAWING_ARCHIVE)
        );
        publishAfterCommit(result);
        return true;
    }

    @Override
    public boolean importProductionOrder(ClientImportRequest<List<ProductionOrderRequest>> request) {
        String batchRequestId = newBatchRequestId();
        logIncomingRequest(request, batchRequestId, ClientImportBusinessTypes.PRODUCTION_ORDER);
        BatchPersistResult result = persistBatch(
                batchRequestId,
                request,
                businessRegistry.get(ClientImportBusinessTypes.PRODUCTION_ORDER)
        );
        publishAfterCommit(result);
        return true;
    }

    @Override
    public boolean importRawMaterial(ClientImportRequest<List<RawMaterialRequest>> request) {
        String batchRequestId = newBatchRequestId();
        logIncomingRequest(request, batchRequestId, ClientImportBusinessTypes.RAW_MATERIAL);
        BatchPersistResult result = persistBatch(
                batchRequestId,
                request,
                businessRegistry.get(ClientImportBusinessTypes.RAW_MATERIAL)
        );
        publishAfterCommit(result);
        return true;
    }

    @Override
    public boolean retryPublish(String taskId) {
        ClientDispatchTask task = dispatchTaskService.getByTaskId(taskId);
        if (task == null) {
            throw new BusinessException("CLIENT_DISPATCH_TASK_NOT_FOUND: 派发任务不存在");
        }
        publishTask(task.getTaskId(), task.getRequestId(), task.getBusinessType());
        return true;
    }

    @Override
    public boolean ping(String targetClientId) {
        ClientImportTaskMessage message = new ClientImportTaskMessage();
        String taskId = "PING-" + IdWorker.getIdStr();
        message.setTaskId(taskId);
        message.setRequestId(taskId);
        message.setTargetClientId(targetClientId);
        message.setBusinessType(ClientImportBusinessTypes.PING);
        message.setSchemaVersion(DEFAULT_SCHEMA_VERSION);
        message.setCreateTime(Instant.now());
        message.setPayload(objectMapper.createObjectNode().put("command", "PING"));

        dispatchPublishService.publish(message);
        return true;
    }

    private String newBatchRequestId() {
        return "BATCH-" + IdWorker.getIdStr();
    }

    private <T, R extends ClientImportRecordBase> BatchPersistResult persistBatch(
            String batchRequestId,
            ClientImportRequest<List<T>> request,
            ClientImportBusinessHandler<T, R> businessHandler) {

        validateBatch(request);

        BatchPersistResult result = transactionTemplate.execute(status -> {
            List<IndexedItem<T>> indexedItems = indexItems(request.getData(),
                    businessHandler.businessKeyName(), businessHandler::businessKey);
            Set<String> businessKeys = indexedItems.stream()
                    .map(IndexedItem::businessKey)
                    .collect(Collectors.toSet());
            Set<String> existingKeys = businessHandler.findExistingByBusinessKeys(businessKeys).stream()
                    .map(businessHandler::recordBusinessKey)
                    .filter(StringUtils::hasText)
                    .map(this::normalizeBusinessKey)
                    .collect(Collectors.toSet());
            List<IndexedItem<T>> newItems = indexedItems.stream()
                    .filter(item -> !existingKeys.contains(item.businessKey()))
                    .toList();

            if (newItems.isEmpty()) {
                log.info("客户端导入数据均已存在，跳过保存和派发 businessType={}, requestId={}, keyCount={}",
                        businessHandler.businessType(), batchRequestId, businessKeys.size());
                throw new BusinessException("CLIENT_IMPORT_DATA_ALREADY_EXISTS: 客户端导入数据已存在，未保存和派发，"
                        + "businessType=" + businessHandler.businessType() + ", keys=" + businessKeys);
            }

            if (!existingKeys.isEmpty()) {
                log.info("客户端导入跳过已存在数据 businessType={}, requestId={}, existingKeys={}",
                        businessHandler.businessType(), batchRequestId, existingKeys);
            }

            String targetClientId = targetResolver.resolve(ClientTargetResolveContext.builder()
                    .requestId(batchRequestId)
                    .requestedTargetClientId(request.getTargetClientId())
                    .businessType(businessHandler.businessType())
                    .businessData(request.getData())
                    .build());

            String taskId = "TASK-" + IdWorker.getIdStr();
            for (IndexedItem<T> indexedItem : newItems) {
                persistItem(batchRequestId, request, businessHandler, targetClientId, taskId, indexedItem);
            }

            ClientImportTaskMessage message = buildTaskMessage(batchRequestId, businessHandler.businessType(), targetClientId, taskId);
            ClientDispatchTask task = buildDispatchTask(batchRequestId, businessHandler.businessType(), targetClientId, taskId, message);
            dispatchTaskService.save(task);

            return BatchPersistResult.toPublish(taskId, batchRequestId, businessHandler.businessType());
        });

        if (result == null) {
            throw new BusinessException("CLIENT_IMPORT_SAVE_FAILED: 客户端导入数据保存失败");
        }
        return result;
    }

    private <T> void persistItem(
            String batchRequestId,
            ClientImportRequest<List<T>> request,
            ClientImportBusinessHandler<T, ?> businessHandler,
            String targetClientId,
            String taskId,
            IndexedItem<T> indexedItem) {

        T item = indexedItem.value();
        validateReferences(businessHandler.businessType(), businessHandler.materialRef(item), businessHandler.machineRef(item));

        // image仍保持String入参，由core统一识别来源并归档；客户端只接收最终共享路径。
        StoredImportFile storedFile = imageStorageService.resolveAndStore(batchRequestId, indexedItem.index(), businessHandler.image(item));
        businessHandler.save(batchRequestId, request, item, indexedItem.index(), taskId, targetClientId, storedFile);
    }

    private ClientImportTaskMessage buildTaskMessage(
            String batchRequestId,
            String businessType,
            String targetClientId,
            String taskId) {

        ClientImportTaskMessage message = new ClientImportTaskMessage();
        message.setTaskId(taskId);
        message.setRequestId(batchRequestId);
        message.setTargetClientId(targetClientId);
        message.setBusinessType(businessType);
        message.setSchemaVersion(DEFAULT_SCHEMA_VERSION);
        message.setCreateTime(Instant.now());
        // MQ只承载任务通知，不携带业务数据；客户端收到后通过Feign按taskId读取SQL Server中的当前未导入数据。
        message.setPayload(null);
        return message;
    }

    private ClientDispatchTask buildDispatchTask(
            String batchRequestId,
            String businessType,
            String targetClientId,
            String taskId,
            ClientImportTaskMessage message) {

        ClientDispatchTask task = new ClientDispatchTask();
        task.setTaskId(taskId);
        task.setRequestId(batchRequestId);
        task.setBusinessType(businessType);
        task.setTargetClientId(targetClientId);
        task.setStatus(DispatchStatus.PENDING_DISPATCH.name());
        task.setRetryCount(0);
        task.setRoutingKey(ClientImportMqConstants.commandRoutingKey(targetClientId));
        task.setRequestSnapshot(writeJson(message));
        EntityAuditHelper.prepareInsert(task);
        return task;
    }

    private void publishAfterCommit(BatchPersistResult result) {
        publishTask(result.taskId(), result.requestId(), result.businessType());
    }

    private void publishTask(String taskId, String requestId, String businessType) {
        try {
            // 数据库事务提交后再发送MQ，避免消息先到客户端但业务记录尚未提交导致无法追踪或补偿。
            dispatchPublishService.publish(taskId);
            updateBatchStatus(businessType, requestId, DispatchStatus.PUBLISHED, "数据已保存并提交客户端处理");
        } catch (Exception ex) {
            dispatchTaskService.updateStatus(taskId, DispatchStatus.PUBLISH_FAILED, ex.getMessage());
            updateBatchStatus(businessType, requestId, DispatchStatus.PUBLISH_FAILED, "数据已保存，客户端任务等待重新派发");
        }
    }

    private void updateBatchStatus(String businessType, String requestId, DispatchStatus status, String message) {
        businessRegistry.get(businessType).updateNotImportedRows(requestId, status, message);
    }

    private void validateReferences(String businessType, String matRef, String wrkRef) {
        // 材质和机床校验是平台扩展点，默认空实现；客户项目可注册ImportReferenceValidator替换。
        if (StringUtils.hasText(matRef)) {
            referenceValidator.validateMaterial(businessType, matRef);
        }
        if (StringUtils.hasText(wrkRef)) {
            referenceValidator.validateMachine(businessType, wrkRef);
        }
    }

    private void validateBatch(ClientImportRequest<?> request) {
        if (!(request.getData() instanceof List<?> list) || CollectionUtils.isEmpty(list)) {
            throw new BusinessException("CLIENT_IMPORT_DATA_EMPTY: data必须是非空数组");
        }
    }

    private <T> List<IndexedItem<T>> indexItems(
            List<T> items,
            String businessKeyName,
            Function<T, String> businessKeyGetter) {

        Map<String, IndexedItem<T>> indexedItems = new LinkedHashMap<>();
        for (int index = 0; index < items.size(); index++) {
            T item = items.get(index);
            String businessKey = normalizeBusinessKey(businessKeyGetter.apply(item));
            if (!StringUtils.hasText(businessKey)) {
                throw new BusinessException("CLIENT_IMPORT_BUSINESS_KEY_EMPTY: " + businessKeyName + "不能为空");
            }
            IndexedItem<T> duplicate = indexedItems.putIfAbsent(businessKey, new IndexedItem<>(index, businessKey, item));
            if (duplicate != null) {
                throw new BusinessException("CLIENT_IMPORT_DUPLICATE_BUSINESS_KEY: 同一次请求中存在重复" + businessKeyName + "=" + businessKey);
            }
        }
        return new ArrayList<>(indexedItems.values());
    }

    private String normalizeBusinessKey(String value) {
        return value == null ? null : value.trim();
    }

    private void logIncomingRequest(ClientImportRequest<?> request, String batchRequestId, String businessType) {
        if (!log.isInfoEnabled()) {
            return;
        }
        try {
            JsonNode data = objectMapper.valueToTree(request.getData());
            JsonNode sanitizedData = sanitizeLogNode(data);
            log.info("客户端导入请求 businessType={}, batchRequestId={}, targetClientId={}, data={}",
                    businessType,
                    batchRequestId,
                    request.getTargetClientId(),
                    objectMapper.writeValueAsString(sanitizedData));
        } catch (Exception ex) {
            log.info("客户端导入请求 businessType={}, batchRequestId={}, targetClientId={}, dataLogFailed={}",
                    businessType,
                    batchRequestId,
                    request.getTargetClientId(),
                    ex.getMessage());
        }
    }

    private JsonNode sanitizeLogNode(JsonNode node) {
        if (node == null || node.isNull()) {
            return node;
        }
        JsonNode copy = node.deepCopy();
        sanitizeLogNodeInPlace(copy);
        return copy;
    }

    private void sanitizeLogNodeInPlace(JsonNode node) {
        if (node == null || node.isNull()) {
            return;
        }
        if (node.isObject()) {
            ObjectNode objectNode = (ObjectNode) node;
            Iterator<Map.Entry<String, JsonNode>> fields = objectNode.fields();
            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> entry = fields.next();
                if ("image".equalsIgnoreCase(entry.getKey()) && entry.getValue().isTextual()) {
                    objectNode.put(entry.getKey(), ImageSourceSanitizer.mask(entry.getValue().asText()));
                } else {
                    sanitizeLogNodeInPlace(entry.getValue());
                }
            }
            return;
        }
        if (node.isArray()) {
            node.forEach(this::sanitizeLogNodeInPlace);
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

    private record IndexedItem<T>(int index, String businessKey, T value) {
    }

    private record BatchPersistResult(String taskId, String requestId, String businessType) {

        static BatchPersistResult toPublish(String taskId, String requestId, String businessType) {
            return new BatchPersistResult(taskId, requestId, businessType);
        }
    }
}
