package com.zhurong.platform.custom.clientimport.handler;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zhurong.platform.base.clientimport.dto.ProductionOrderRequest;
import com.zhurong.platform.base.lantek.expert.lstx.ExpertProductXmlExporter;
import com.zhurong.platform.base.lantek.expert.lstx.ExpertProductXmlItem;
import com.zhurong.platform.base.lantek.expert.procesos.AutomationInstructionBuilder;
import com.zhurong.platform.base.lantek.expert.procesos.ImportPartsFromDatabase;
import com.zhurong.platform.base.lantek.expert.procesos.OpenExpert;
import com.zhurong.platform.base.lantek.expert.procesos.OpenJob;
import com.zhurong.platform.core.clientimport.mq.ClientImportBusinessTypes;
import com.zhurong.platform.core.clientimport.mq.ClientImportTaskPayloadItem;
import com.zhurong.platform.custom.entity.MmnnMmoo00000300;
import com.zhurong.platform.custom.properties.XyBaoyuanProperties;
import com.zhurong.platform.custom.service.IMmnnMmoo00000300Service;
import com.zhurong.platform.custom.service.XyImportTaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

/** 在目标 Windows 客户端执行象屿宝元生产订单 LSTX 导入。 */
@Slf4j
@Component
@RequiredArgsConstructor
public class XyProductionOrderHandler implements ClientImportHandler<ProductionOrderRequest> {

    private static final String JOB_REF = "jobRef";

    private final XyBaoyuanProperties properties;
    private final IMmnnMmoo00000300Service manufacturingOrderService;
    private final XyLantekExecutionLock executionLock;

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
        try {
            return executionLock.execute(() -> executeLocked(context));
        } catch (Exception ex) {
            log.error("象屿宝元生产订单客户端导入异常, taskId={}", context.getTask().getTaskId(), ex);
            return ClientImportResult.failed("生产订单客户端导入失败: " + safeMessage(ex), List.of());
        }
    }

    private ClientImportResult executeLocked(ClientImportContext<ProductionOrderRequest> context) throws Exception {
        String install = requireInstall();
        List<ProductionOrderRequest> data = context.getDataList();
        if (data.isEmpty()) {
            return ClientImportResult.success("当前批次没有待导入生产订单", List.of());
        }

        validate(data);
        Set<String> importedBefore = findImportedKeys(data);
        Map<String, List<ClientImportTaskPayloadItem<ProductionOrderRequest>>> pendingByJob = context.getItems()
                .stream()
                .filter(item -> !importedBefore.contains(key(item.getData())))
                .collect(Collectors.groupingBy(
                        item -> jobRef(item.getData()),
                        LinkedHashMap::new,
                        Collectors.toList()
                ));

        for (Map.Entry<String, List<ClientImportTaskPayloadItem<ProductionOrderRequest>>> entry
                : pendingByJob.entrySet()) {
            List<ExpertProductXmlItem> products = entry.getValue().stream()
                    .map(ClientImportTaskPayloadItem::getData)
                    .map(this::toProduct)
                    .toList();
            Path directory = Path.of(install).resolve("AutoImport").resolve("xybaoyuan");
            Files.createDirectories(directory);
            Path lstx = directory.resolve("order-" + UUID.randomUUID() + ".lstx");
            new ExpertProductXmlExporter().export(products, lstx);

            AutomationInstructionBuilder.ExecResult result = new AutomationInstructionBuilder(
                    AutomationInstructionBuilder.AutomationVersion.V45,
                    install
            ).withPrcEncoding(AutomationInstructionBuilder.PrcEncoding.ANSI)
                    .addInstruction(new OpenExpert(true))
                    .addInstruction(new OpenJob(entry.getKey()))
                    .addInstruction(new ImportPartsFromDatabase(false, lstx.toAbsolutePath().toString()))
                    .execute();
            if (!result.success()) {
                throw new IllegalStateException(automationError("LSTX自动化导入失败", result));
            }
        }

        Set<String> imported = findImportedKeys(data);
        List<Long> importedIds = context.getItems().stream()
                .filter(item -> imported.contains(key(item.getData())))
                .map(ClientImportTaskPayloadItem::getRecordId)
                .toList();
        if (importedIds.size() != context.getItems().size()) {
            return ClientImportResult.failed(
                    "生产订单部分导入失败: " + importedIds.size() + "/" + context.getItems().size(),
                    importedIds
            );
        }
        return ClientImportResult.successAll("生产订单导入完成: " + data.size(), context);
    }

    private void validate(List<ProductionOrderRequest> data) {
        Set<String> mnORefs = data.stream().map(ProductionOrderRequest::getMnORef).collect(Collectors.toSet());
        if (mnORefs.contains(null) || mnORefs.contains("") || mnORefs.size() != data.size()) {
            throw new IllegalArgumentException("生产订单ERP内码不能为空且同批次不能重复");
        }
        data.forEach(item -> {
            requireText(item.getPrdRef(), "LSTX零件图号不能为空");
            requireText(item.getOrdRef(), "生产订单号不能为空");
            requireText(item.getCusRef(), "ERP内码和计划跟踪号组合值不能为空");
            requireText(item.getWrkRef(), "生产订单设备不能为空");
            jobRef(item);
        });
    }

    private ExpertProductXmlItem toProduct(ProductionOrderRequest request) {
        return ExpertProductXmlItem.create()
                .reference(request.getPrdRef())
                .name(request.getPrdName())
                .material(request.getMatRef())
                .machine(request.getWrkRef())
                .thickness(request.getThickness())
                .quantity(request.getQuantity())
                .ordRef(request.getOrdRef())
                .cusRef(request.getCusRef())
                .userData1(request.getUdata1())
                .userData2(request.getUdata2())
                .userData3(request.getUdata3());
    }

    private Set<String> findImportedKeys(Collection<ProductionOrderRequest> data) {
        List<String> orderNumbers = data.stream()
                .map(ProductionOrderRequest::getOrdRef)
                .filter(StringUtils::hasText)
                .distinct()
                .toList();
        if (orderNumbers.isEmpty()) {
            return Set.of();
        }
        return manufacturingOrderService.list(Wrappers.lambdaQuery(MmnnMmoo00000300.class)
                        .in(MmnnMmoo00000300::getOrdRef, orderNumbers))
                .stream()
                .map(item -> XyImportTaskService.importedOrderKey(item.getCusRef(), item.getDIS_JobRef()))
                .collect(Collectors.toSet());
    }

    private static String key(ProductionOrderRequest request) {
        return XyImportTaskService.importedOrderKey(request.getCusRef(), jobRef(request));
    }

    private static String jobRef(ProductionOrderRequest request) {
        Object value = request.getExtensions() == null ? null : request.getExtensions().get(JOB_REF);
        String result = value == null ? null : String.valueOf(value);
        requireText(result, "生产订单作业不能为空");
        return result.trim();
    }

    private String requireInstall() {
        String install = properties.getLantek().getInstall();
        requireText(install, "未配置Lantek安装目录");
        return install.trim();
    }

    private static String automationError(
            String prefix,
            AutomationInstructionBuilder.ExecResult result
    ) {
        String detail = StringUtils.hasText(result.stderr()) ? result.stderr().trim() : result.stdout().trim();
        return StringUtils.hasText(detail) ? prefix + ": " + detail : prefix + "，退出码: " + result.exitCode();
    }

    private static void requireText(String value, String message) {
        if (!StringUtils.hasText(value)) {
            throw new IllegalArgumentException(message);
        }
    }

    private static String safeMessage(Exception ex) {
        return StringUtils.hasText(ex.getMessage()) ? ex.getMessage() : ex.getClass().getSimpleName();
    }
}
