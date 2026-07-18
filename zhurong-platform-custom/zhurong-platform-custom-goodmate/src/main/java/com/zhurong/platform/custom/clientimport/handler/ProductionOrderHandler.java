package com.zhurong.platform.custom.clientimport.handler;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zhurong.platform.base.clientimport.dto.ProductionOrderRequest;
import com.zhurong.platform.base.lantek.expert.lstx.ExpertProductXmlItem;
import com.zhurong.platform.base.lantek.expert.lstx.LstxImportTool;
import com.zhurong.platform.core.clientimport.mq.ClientImportBusinessTypes;
import com.zhurong.platform.core.clientimport.mq.ClientImportTaskPayloadItem;
import com.zhurong.platform.custom.clientimport.configuration.ConditionalOnClientCommunicationEnabled;
import com.zhurong.platform.custom.entity.MmnnMmoo00000300;
import com.zhurong.platform.custom.properties.LantekConfigProperties;
import com.zhurong.platform.custom.service.IMmnnMmoo00000300Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.nio.file.Path;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
@ConditionalOnClientCommunicationEnabled
public class ProductionOrderHandler implements ClientImportHandler<ProductionOrderRequest> {

    private static final ReentrantLock LSTX_IMPORT_LOCK = new ReentrantLock(true);

    private final LantekConfigProperties lantekConfigProperties;
    private final IMmnnMmoo00000300Service mmnnMmoo00000300Service;

    @Override
    public String businessType() {
        return ClientImportBusinessTypes.PRODUCTION_ORDER;
    }

    @Override
    public Class<ProductionOrderRequest> payloadType() {
        return ProductionOrderRequest.class;
    }

    @Override
    public ClientImportResult execute(ClientImportContext<ProductionOrderRequest> context) {
        List<ProductionOrderRequest> dataList = context.getDataList();
        List<Long> recordIds = context.getRecordIds();
        log.info("goodmate收到生产订单批量导入任务，taskId={}, requestId={}, count={}, recordIds={}",
                context.getTask().getTaskId(),
                context.getTask().getRequestId(),
                dataList.size(),
                recordIds);

        String lantekInstall = lantekConfigProperties.getInstall();
        if (!StringUtils.hasText(lantekInstall)) {
            return ClientImportResult.failed("Lantek安装目录未配置，无法执行生产订单LSTX导入。", List.of());
        }
        if (dataList.isEmpty()) {
            return ClientImportResult.success("当前批次没有待导入生产订单数据。", List.of());
        }

        LSTX_IMPORT_LOCK.lock();
        try {
            /*
             * LSTX导入器不会校验生产订单是否已存在。同一个mnORef重复调用会重复写入数据。
             * 所以必须把“导入前反查已存在订单”和“调用导入器”放在同一个锁内，避免并发线程同时导入同一批订单。
             * 注意：LSTX无法直接指定MnORef，当前业务约定把request.mnORef写入LSTX的OrdRef字段。
             */
            validateBatchMnORefs(dataList);
            List<String> mnORefs = dataList.stream()
                    .map(ProductionOrderRequest::getMnORef)
                    .filter(StringUtils::hasText)
                    .distinct()
                    .toList();
            Set<String> existingMnORefs = findImportedMnORefs(mnORefs);
            List<ClientImportTaskPayloadItem<ProductionOrderRequest>> pendingItems = context.getItems().stream()
                    .filter(item -> !existingMnORefs.contains(item.getData().getMnORef()))
                    .toList();

            if (!pendingItems.isEmpty()) {
                List<ProductionOrderRequest> pendingData = pendingItems.stream()
                        .map(ClientImportTaskPayloadItem::getData)
                        .toList();
                LstxImportTool.ImportType importType = resolveImportType(pendingData);
                List<ExpertProductXmlItem> products = LstxImportTool.toProductionOrderProductXmlItems(pendingData);
                LstxImportTool.ImportExecutionResult result = LstxImportTool.exportAndExecute(
                        products,
                        Path.of(lantekInstall),
                        importType
                );
                LstxImportTool.ExecResult execResult = result.execResult();
                if (!execResult.success()) {
                    log.warn("goodmate生产订单LSTX导入失败，xmlPath={}, exitCode={}, stdout={}, stderr={}",
                            result.xmlPath(), execResult.exitCode(), execResult.stdout(), execResult.stderr());
                    return ClientImportResult.failed(
                            "生产订单LSTX导入失败，exitCode=" + execResult.exitCode(),
                            recordIdsForMnORefs(context, existingMnORefs)
                    );
                }
                log.info("goodmate生产订单LSTX导入完成，xmlPath={}, durationMs={}",
                        result.xmlPath(), execResult.durationMs());
            }

            Set<String> importedMnORefs = findImportedMnORefs(mnORefs);
            List<Long> importedRecordIds = recordIdsForMnORefs(context, importedMnORefs);
            if (importedRecordIds.size() == recordIds.size()) {
                return ClientImportResult.successAll("生产订单LSTX导入完成：" + dataList.size(), context);
            }
            return ClientImportResult.success(
                    "生产订单LSTX部分导入完成：" + importedRecordIds.size() + "/" + dataList.size(),
                    importedRecordIds
            );
        } catch (Exception e) {
            log.error("goodmate生产订单LSTX导入异常", e);
            return ClientImportResult.failed("生产订单LSTX导入异常：" + e.getMessage(), List.of());
        } finally {
            LSTX_IMPORT_LOCK.unlock();
        }
    }

    private void validateBatchMnORefs(List<ProductionOrderRequest> dataList) {
        List<String> mnORefs = dataList.stream()
                .map(ProductionOrderRequest::getMnORef)
                .filter(StringUtils::hasText)
                .toList();
        if (mnORefs.size() != dataList.size()) {
            throw new IllegalArgumentException("生产订单mnORef不能为空。");
        }
        Set<String> uniqueMnORefs = Set.copyOf(mnORefs);
        if (uniqueMnORefs.size() != mnORefs.size()) {
            throw new IllegalArgumentException("同一批生产订单中存在重复mnORef，禁止通过LSTX重复导入：" + mnORefs);
        }
    }

    private Set<String> findImportedMnORefs(Collection<String> mnORefs) {
        if (mnORefs.isEmpty()) {
            return Set.of();
        }
        return mmnnMmoo00000300Service.list(Wrappers.lambdaQuery(MmnnMmoo00000300.class)
                        .in(MmnnMmoo00000300::getOrdRef, mnORefs))
                .stream()
                .map(MmnnMmoo00000300::getOrdRef)
                .filter(StringUtils::hasText)
                .collect(Collectors.toSet());
    }

    private List<Long> recordIdsForMnORefs(
            ClientImportContext<ProductionOrderRequest> context,
            Set<String> importedMnORefs
    ) {
        return context.getItems().stream()
                .filter(item -> importedMnORefs.contains(item.getData().getMnORef()))
                .map(ClientImportTaskPayloadItem::getRecordId)
                .toList();
    }

    private LstxImportTool.ImportType resolveImportType(List<ProductionOrderRequest> dataList) {
        List<LstxImportTool.ImportType> importTypes = dataList.stream()
                .map(ProductionOrderRequest::getImage)
                .map(LstxImportTool::importTypeFromDrawingPath)
                .filter(Objects::nonNull)
                .distinct()
                .toList();
        if (importTypes.size() != 1) {
            throw new IllegalArgumentException("同一批生产订单LSTX导入必须使用同一种图纸类型：" + importTypes);
        }
        return importTypes.getFirst();
    }
}
