package com.zhurong.platform.custom.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhurong.platform.base.clientimport.dto.RawMaterialRequest;
import com.zhurong.platform.base.lantek.expert.lst.PlateAndRemnantLstTool;
import com.zhurong.platform.base.lantek.expert.lstx.ExpertProductXmlExporter;
import com.zhurong.platform.base.lantek.expert.lstx.ExpertProductXmlItem;
import com.zhurong.platform.base.lantek.expert.procesos.*;
import com.zhurong.platform.custom.entity.*;
import com.zhurong.platform.custom.properties.XyBaoyuanProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class XyImportTaskService {
    public static final String ORDER = "MANUFACTURING_ORDER";
    public static final String STEEL_PLATE = "STEEL_PLATE";
    private static final ReentrantLock IMPORT_LOCK = new ReentrantLock(true);

    private final XyDataService dataService;
    private final ObjectMapper objectMapper;
    private final XyBaoyuanProperties properties;
    private final IMmnnMmoo00000300Service manufacturingOrderService;
    private final IPprrPprr00000100Service partService;

    public XyImportTask importSynchronously(String businessType, List<Long> ids) {
        if (ids == null || ids.isEmpty()) throw new IllegalArgumentException("请选择要导入的数据");
        List<Long> distinctIds = ids.stream().filter(Objects::nonNull).distinct().toList();
        validateRecords(businessType, distinctIds);
        try {
            XyImportTask task = new XyImportTask();
            task.setBusinessType(businessType);
            task.setStatus("PENDING");
            task.setAttempts(0);
            task.setRecordIdsJson(objectMapper.writeValueAsString(distinctIds));
            dataService.insertTask(task);
            attachTask(task, distinctIds);
            execute(task.getId());
            return dataService.taskById(task.getId());
        } catch (Exception exception) {
            if (exception instanceof RuntimeException runtimeException) throw runtimeException;
            throw new IllegalStateException("创建导入任务失败", exception);
        }
    }

    private void execute(Long id) {
        XyImportTask task = requireTask(id);
        if (Set.of("RUNNING", "SUCCESS").contains(task.getStatus())) return;
        IMPORT_LOCK.lock();
        try {
            task = requireTask(id);
            if (Set.of("RUNNING", "SUCCESS").contains(task.getStatus())) return;
            task.setStatus("RUNNING");
            task.setAttempts(Optional.ofNullable(task.getAttempts()).orElse(0) + 1);
            task.setExecutionTime(LocalDateTime.now());
            dataService.updateTask(task);
            List<Long> recordIds = objectMapper.readValue(task.getRecordIdsJson(), new TypeReference<>() {});
            if (ORDER.equals(task.getBusinessType())) importOrders(recordIds);
            else if (STEEL_PLATE.equals(task.getBusinessType())) importSteelPlates(recordIds);
            else throw new IllegalArgumentException("不支持的导入业务: " + task.getBusinessType());
            task.setStatus("SUCCESS");
            task.setMessage("导入完成");
        } catch (Exception exception) {
            log.error("象屿宝元导入任务执行失败, taskId={}", id, exception);
            task.setStatus("FAILED");
            task.setMessage(limit(exception.getMessage(), 1000));
        } finally {
            task.setExecutionTime(LocalDateTime.now());
            dataService.updateTask(task);
            IMPORT_LOCK.unlock();
        }
    }

    private void importOrders(List<Long> ids) throws Exception {
        String install = requireInstall();
        List<XyManufacturingOrder> orders = dataService.ordersByIds(ids);
        Map<String, XyBasePart> parts;
        // Base-part IDs differ from order IDs; resolve by querying the page-independent service data.
        Set<String> importedKeys = findImportedOrderKeys(orders);
        List<XyManufacturingOrder> pending = orders.stream()
                .filter(order -> !importedKeys.contains(orderKey(order.getProductionOrderErpInternalCode(), order.getJobRef())))
                .toList();
        if (!pending.isEmpty()) {
            List<String> refs = pending.stream().map(XyManufacturingOrder::getPrdRef).distinct().toList();
            parts = dataService.findBasePartsByRefs(refs).stream()
                    .collect(Collectors.toMap(XyBasePart::getPrdRef, Function.identity(), (left, right) -> left));
            Map<String, List<XyManufacturingOrder>> byJob = pending.stream().collect(Collectors.groupingBy(XyManufacturingOrder::getJobRef));
            for (Map.Entry<String, List<XyManufacturingOrder>> entry : byJob.entrySet()) {
                requireText(entry.getKey(), "生产订单未选择作业");
                List<ExpertProductXmlItem> products = new ArrayList<>();
                for (XyManufacturingOrder order : entry.getValue()) {
                    XyBasePart part = parts.get(order.getPrdRef());
                    if (part == null) throw new IllegalArgumentException("未维护基础零件: " + order.getPrdRef());
                    validateOrder(order, part);
                    products.add(ExpertProductXmlItem.create()
                            .reference(part.getDrawingCode())
                            .name(order.getPrdName())
                            .material(order.getMatRef())
                            .machine(order.getWrkRef())
                            .thickness(order.getThickness())
                            .quantity(order.getQuantity())
                            .ordRef(order.getProductionOrderNumber())
                            .cusRef(combineErpIdentity(order.getProductionOrderErpInternalCode(), order.getCusRef()))
                            .userData1(part.getUdata1()).userData2(part.getUdata2()).userData3(part.getUdata3()));
                }
                Path directory = Path.of(install).resolve("AutoImport").resolve("xybaoyuan");
                Files.createDirectories(directory);
                Path lstx = directory.resolve("order-" + UUID.randomUUID() + ".lstx");
                new ExpertProductXmlExporter().export(products, lstx);
                AutomationInstructionBuilder.ExecResult result = new AutomationInstructionBuilder(
                        AutomationInstructionBuilder.AutomationVersion.V45, install)
                        .addInstruction(new OpenExpert(true))
                        .addInstruction(new OpenJob(entry.getKey()))
                        .addInstruction(new ImportPartsFromDatabase(false, lstx.toAbsolutePath().toString()))
                        .execute();
                if (!result.success()) throw new IllegalStateException("LSTX自动化导入失败: " + result.stderr());
            }
        }
        Set<String> verified = findImportedOrderKeys(orders);
        LocalDateTime now = LocalDateTime.now();
        for (XyManufacturingOrder order : orders) {
            if (!verified.contains(orderKey(order.getProductionOrderErpInternalCode(), order.getJobRef()))) {
                throw new IllegalStateException("Lantek未找到导入结果: " + order.getProductionOrderErpInternalCode());
            }
            order.setReadState(true);
            order.setReadTime(now);
            order.setSendState(true);
            order.setSendTime(now);
            dataService.updateOrder(order);
        }
    }

    private void importSteelPlates(List<Long> ids) throws Exception {
        String install = requireInstall();
        List<XySteelPlate> plates = dataService.steelPlatesByIds(ids);
        List<RawMaterialRequest> requests = plates.stream().map(this::toRawMaterial).toList();
        String lstPath = PlateAndRemnantLstTool.exportRawMaterials(requests);
        AutomationInstructionBuilder.ExecResult result = new AutomationInstructionBuilder(
                AutomationInstructionBuilder.AutomationVersion.V45, install)
                .addInstruction(new CreateAndUpdateBoard(lstPath)).execute();
        if (!result.success()) throw new IllegalStateException("钢板自动化导入失败: " + result.stderr());
        Set<String> imported = partService.list(Wrappers.lambdaQuery(PprrPprr00000100.class)
                        .in(PprrPprr00000100::getPrdRef, plates.stream().map(XySteelPlate::getPrdRef).toList()))
                .stream().map(PprrPprr00000100::getPrdRef).collect(Collectors.toSet());
        LocalDateTime now = LocalDateTime.now();
        for (XySteelPlate plate : plates) {
            if (!imported.contains(plate.getPrdRef())) throw new IllegalStateException("Lantek未找到钢板: " + plate.getPrdRef());
            PprrPprr00000100 material = partService.getOne(Wrappers.lambdaQuery(PprrPprr00000100.class)
                    .eq(PprrPprr00000100::getPrdRef, plate.getPrdRef()));
            material.setDIS_UData1_Sht(plate.getStockName());
            material.setDIS_UData2_Sht(plate.getErpMaterialId() == null ? null : String.valueOf(plate.getErpMaterialId()));
            material.setDIS_UData3_Sht(plate.getLotNumber());
            partService.updateById(material);
            plate.setReadState(true);
            plate.setReadTime(now);
            plate.setSendState(true);
            plate.setSendTime(now);
            dataService.updateSteelPlate(plate);
        }
    }

    private Set<String> findImportedOrderKeys(List<XyManufacturingOrder> orders) {
        List<String> orderNumbers = orders.stream().map(XyManufacturingOrder::getProductionOrderNumber)
                .filter(StringUtils::hasText).distinct().toList();
        if (orderNumbers.isEmpty()) return Set.of();
        return manufacturingOrderService.list(Wrappers.lambdaQuery(MmnnMmoo00000300.class)
                        .in(MmnnMmoo00000300::getOrdRef, orderNumbers))
                .stream().map(item -> importedOrderKey(item.getCusRef(), item.getDIS_JobRef())).collect(Collectors.toSet());
    }

    private void validateRecords(String businessType, List<Long> ids) {
        int count;
        if (ORDER.equals(businessType)) count = dataService.ordersByIds(ids).size();
        else if (STEEL_PLATE.equals(businessType)) count = dataService.steelPlatesByIds(ids).size();
        else throw new IllegalArgumentException("不支持的导入业务: " + businessType);
        if (count != ids.size()) throw new IllegalArgumentException("部分待导入记录不存在或已删除");
    }

    private void attachTask(XyImportTask task, List<Long> ids) {
        LocalDateTime now = LocalDateTime.now();
        if (ORDER.equals(task.getBusinessType())) {
            dataService.ordersByIds(ids).forEach(item -> {
                item.setLastTaskId(task.getId()); item.setReadState(true); item.setReadTime(now); dataService.updateOrder(item);
            });
        } else {
            dataService.steelPlatesByIds(ids).forEach(item -> {
                item.setLastTaskId(task.getId()); item.setReadState(true); item.setReadTime(now); dataService.updateSteelPlate(item);
            });
        }
    }

    private RawMaterialRequest toRawMaterial(XySteelPlate plate) {
        requireText(plate.getPrdRef(), "钢板编号不能为空");
        RawMaterialRequest request = new RawMaterialRequest();
        request.setPrdRef(plate.getPrdRef()); request.setPrdName(plate.getPrdName()); request.setMatRef(plate.getMatRef());
        request.setThickness(floatValue(plate.getThickness())); request.setLength(floatValue(plate.getLength()));
        request.setWidth(floatValue(plate.getWidth())); request.setQuantity(Math.max(1, (int) Math.round(plate.getQuantity())));
        request.setUdata1(plate.getStockName()); request.setUdata2(plate.getErpMaterialId() == null ? null : String.valueOf(plate.getErpMaterialId()));
        request.setUdata3(plate.getLotNumber());
        return request;
    }

    private static void validateOrder(XyManufacturingOrder order, XyBasePart part) {
        requireText(order.getProductionOrderNumber(), "生产订单号不能为空");
        requireText(order.getProductionOrderErpInternalCode(), "生产订单ERP内码不能为空");
        requireText(order.getCusRef(), "计划跟踪号不能为空");
        requireText(order.getWrkRef(), "生产订单未选择设备");
        requireText(part.getDrawingCode(), "基础零件图号不能为空");
        requireText(part.getUdata3(), "基础零件ERP物料内码不能为空: " + part.getPrdRef());
        if (order.getQuantity() == null || order.getQuantity() <= 0) throw new IllegalArgumentException("生产订单数量必须大于0");
    }

    public static String combineErpIdentity(String erpInternalCode, String planNumber) {
        requireText(erpInternalCode, "ERP内码不能为空");
        requireText(planNumber, "计划跟踪号不能为空");
        return erpInternalCode.trim() + "-" + planNumber.trim();
    }

    public static ErpIdentity splitErpIdentity(String composite) {
        if (!StringUtils.hasText(composite)) return new ErpIdentity("", "");
        int separator = composite.indexOf('-');
        if (separator < 0) return new ErpIdentity(composite, "");
        return new ErpIdentity(composite.substring(0, separator), composite.substring(separator + 1));
    }

    public record ErpIdentity(String erpInternalCode, String planNumber) {}

    private XyImportTask requireTask(Long id) {
        XyImportTask task = dataService.taskById(id);
        if (task == null) throw new IllegalArgumentException("导入任务不存在: " + id);
        return task;
    }

    private String requireInstall() {
        String install = properties.getLantek().getInstall();
        requireText(install, "未配置Lantek安装目录");
        return install;
    }

    static String importedOrderKey(String compositeCusRef, String jobRef) {
        return orderKey(splitErpIdentity(compositeCusRef).erpInternalCode(), jobRef);
    }

    private static String orderKey(String erpInternalCode, String jobRef) {
        return String.valueOf(erpInternalCode) + "\u0000" + String.valueOf(jobRef);
    }
    private static Float floatValue(Double value) { return value == null ? null : value.floatValue(); }
    private static void requireText(String value, String message) { if (!StringUtils.hasText(value)) throw new IllegalArgumentException(message); }
    private static String limit(String value, int max) { return value == null ? "未知错误" : value.substring(0, Math.min(value.length(), max)); }
}
