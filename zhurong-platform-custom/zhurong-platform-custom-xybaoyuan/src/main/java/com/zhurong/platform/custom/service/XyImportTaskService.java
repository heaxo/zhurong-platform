package com.zhurong.platform.custom.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhurong.platform.base.clientimport.dto.ProductionOrderRequest;
import com.zhurong.platform.base.clientimport.dto.RawMaterialRequest;
import com.zhurong.platform.core.clientimport.mq.ClientImportBusinessTypes;
import com.zhurong.platform.core.clientimport.mq.ClientImportTaskMessage;
import com.zhurong.platform.core.clientimport.mq.ClientImportTaskPayloadItem;
import com.zhurong.platform.custom.clientimport.handler.ClientImportContext;
import com.zhurong.platform.custom.clientimport.handler.ClientImportResult;
import com.zhurong.platform.custom.clientimport.handler.XyProductionOrderHandler;
import com.zhurong.platform.custom.clientimport.handler.XyRawMaterialHandler;
import com.zhurong.platform.custom.entity.XyBasePart;
import com.zhurong.platform.custom.entity.XyImportTask;
import com.zhurong.platform.custom.entity.XyManufacturingOrder;
import com.zhurong.platform.custom.entity.XySteelPlate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Windows 客户端本机同步导入编排。
 *
 * <p>前端请求已经由 core 透明代理到当前客户端，本服务必须直接执行本机 LSTX/PRC，
 * 不能再次向 core 派发同一客户端的 MQ 任务，否则单消费者会等待自身而形成死锁。</p>
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class XyImportTaskService {

    public static final String ORDER = "MANUFACTURING_ORDER";
    public static final String STEEL_PLATE = "STEEL_PLATE";

    private final XyDataService dataService;
    private final ObjectMapper objectMapper;
    private final XyProductionOrderHandler productionOrderHandler;
    private final XyRawMaterialHandler rawMaterialHandler;

    public XyImportTask importSynchronously(String businessType, List<Long> ids) {
        List<Long> distinctIds = normalizeIds(ids);
        validateRecords(businessType, distinctIds);

        XyImportTask task = new XyImportTask();
        task.setBusinessType(businessType);
        task.setStatus("PENDING");
        task.setAttempts(0);
        try {
            task.setRecordIdsJson(objectMapper.writeValueAsString(distinctIds));
            dataService.insertTask(task);
            attachTask(task, distinctIds);
            execute(task, distinctIds);
        } catch (Exception exception) {
            log.error("象屿宝元客户端本机导入失败, taskId={}, businessType={}",
                    task.getId(), businessType, exception);
            task.setStatus("FAILED");
            task.setMessage(limit(safeMessage(exception), 1000));
            task.setExecutionTime(LocalDateTime.now());
            if (task.getId() != null) {
                dataService.updateTask(task);
            }
        }
        return task.getId() == null ? task : dataService.taskById(task.getId());
    }

    private void execute(XyImportTask task, List<Long> ids) {
        task.setStatus("RUNNING");
        task.setAttempts(1);
        task.setExecutionTime(LocalDateTime.now());
        dataService.updateTask(task);

        ClientImportResult result;
        if (ORDER.equals(task.getBusinessType())) {
            result = productionOrderHandler.execute(buildProductionOrderContext(task, ids));
        } else if (STEEL_PLATE.equals(task.getBusinessType())) {
            result = rawMaterialHandler.execute(buildRawMaterialContext(task, ids));
        } else {
            throw new IllegalArgumentException("不支持的导入业务: " + task.getBusinessType());
        }

        if (result.getImportedRecordIds() != null && !result.getImportedRecordIds().isEmpty()) {
            markBusinessRowsImported(task.getBusinessType(), result.getImportedRecordIds());
        }
        if (!result.isSuccess()) {
            throw new IllegalStateException(StringUtils.hasText(result.getMessage())
                    ? result.getMessage()
                    : "客户端本机导入失败");
        }

        task.setStatus("SUCCESS");
        task.setMessage(StringUtils.hasText(result.getMessage()) ? result.getMessage() : "导入完成");
        task.setExecutionTime(LocalDateTime.now());
        dataService.updateTask(task);
    }

    private ClientImportContext<ProductionOrderRequest> buildProductionOrderContext(
            XyImportTask task,
            List<Long> ids
    ) {
        List<XyManufacturingOrder> orders = dataService.ordersByIds(ids);
        validateOrderAssignments(orders);
        Map<String, XyBasePart> parts = dataService.findBasePartsByRefs(orders.stream()
                        .map(XyManufacturingOrder::getPrdRef)
                        .filter(StringUtils::hasText)
                        .collect(Collectors.toSet()))
                .stream()
                .collect(Collectors.toMap(XyBasePart::getPrdRef, Function.identity(), (left, right) -> left));

        List<ClientImportTaskPayloadItem<ProductionOrderRequest>> items = new ArrayList<>(orders.size());
        for (int index = 0; index < orders.size(); index++) {
            XyManufacturingOrder order = orders.get(index);
            XyBasePart part = parts.get(order.getPrdRef());
            if (part == null) {
                throw new IllegalArgumentException("未维护基础零件: " + order.getPrdRef());
            }
            validateOrder(order, part);
            ClientImportTaskPayloadItem<ProductionOrderRequest> item = new ClientImportTaskPayloadItem<>();
            item.setRecordId(order.getId());
            item.setRequestItemIndex(index);
            item.setData(toProductionOrder(order, part));
            items.add(item);
        }
        return new ClientImportContext<>(taskMessage(task, ClientImportBusinessTypes.PRODUCTION_ORDER), items);
    }

    private ClientImportContext<RawMaterialRequest> buildRawMaterialContext(
            XyImportTask task,
            List<Long> ids
    ) {
        List<XySteelPlate> plates = dataService.steelPlatesByIds(ids);
        List<ClientImportTaskPayloadItem<RawMaterialRequest>> items = new ArrayList<>(plates.size());
        for (int index = 0; index < plates.size(); index++) {
            XySteelPlate plate = plates.get(index);
            ClientImportTaskPayloadItem<RawMaterialRequest> item = new ClientImportTaskPayloadItem<>();
            item.setRecordId(plate.getId());
            item.setRequestItemIndex(index);
            item.setData(toRawMaterial(plate));
            items.add(item);
        }
        return new ClientImportContext<>(taskMessage(task, ClientImportBusinessTypes.RAW_MATERIAL), items);
    }

    private static ClientImportTaskMessage taskMessage(XyImportTask task, String businessType) {
        String taskId = String.valueOf(task.getId());
        ClientImportTaskMessage message = new ClientImportTaskMessage();
        message.setTaskId(taskId);
        message.setRequestId(taskId);
        message.setBusinessType(businessType);
        message.setSchemaVersion("1.0");
        message.setCreateTime(Instant.now());
        return message;
    }

    private ProductionOrderRequest toProductionOrder(XyManufacturingOrder order, XyBasePart part) {
        ProductionOrderRequest request = new ProductionOrderRequest();
        request.setPrdRef(part.getPrdName());
        request.setPrdName(order.getDrawingCode());
        request.setMatRef(order.getMatRef());
        request.setThickness(floatValue(order.getThickness()));
        request.setWrkRef(order.getWrkRef());
        request.setMnORef(order.getProductionOrderErpInternalCode());
        request.setOrdRef(order.getProductionOrderNumber());
        request.setCusRef(combineErpIdentity(order.getProductionOrderErpInternalCode(), order.getCusRef()));
        request.setQuantity(integerQuantity(order.getQuantity(), "生产订单数量必须为正整数"));
        request.setUdata1(part.getUdata1());
        request.setUdata2(part.getUdata2());
        request.setUdata3(part.getUdata3());
        Map<String, Object> extensions = new LinkedHashMap<>();
        extensions.put("jobRef", order.getJobRef());
        extensions.put("sourcePrdRef", order.getPrdRef());
        request.setExtensions(extensions);
        return request;
    }

    private RawMaterialRequest toRawMaterial(XySteelPlate plate) {
        requireText(plate.getPrdRef(), "钢板编号不能为空");
        RawMaterialRequest request = new RawMaterialRequest();
        request.setPrdRef(plate.getPrdRef());
        request.setPrdName(plate.getPrdName());
        request.setMatRef(plate.getMatRef());
        request.setThickness(floatValue(plate.getThickness()));
        request.setLength(floatValue(plate.getLength()));
        request.setWidth(floatValue(plate.getWidth()));
        request.setQuantity(integerQuantity(plate.getQuantity(), "钢板数量必须为正整数"));
        request.setUdata1(plate.getStockName());
        request.setUdata2(plate.getErpMaterialId() == null ? null : String.valueOf(plate.getErpMaterialId()));
        request.setUdata3(plate.getLotNumber());
        return request;
    }

    private void validateRecords(String businessType, List<Long> ids) {
        int count;
        if (ORDER.equals(businessType)) {
            List<XyManufacturingOrder> orders = dataService.ordersByIds(ids);
            count = orders.size();
            if (count == ids.size()) {
                validateOrderAssignments(orders);
            }
        } else if (STEEL_PLATE.equals(businessType)) {
            count = dataService.steelPlatesByIds(ids).size();
        } else {
            throw new IllegalArgumentException("不支持的导入业务: " + businessType);
        }
        if (count != ids.size()) {
            throw new IllegalArgumentException("部分待导入记录不存在或已删除");
        }
    }

    static void validateOrderAssignments(List<XyManufacturingOrder> orders) {
        List<String> missingJobs = orders.stream()
                .filter(order -> !StringUtils.hasText(order.getJobRef()))
                .map(XyImportTaskService::orderLabel)
                .toList();
        List<String> missingMachines = orders.stream()
                .filter(order -> !StringUtils.hasText(order.getWrkRef()))
                .map(XyImportTaskService::orderLabel)
                .toList();
        List<String> errors = new ArrayList<>();
        if (!missingJobs.isEmpty()) {
            errors.add("未设置作业: " + String.join("、", missingJobs));
        }
        if (!missingMachines.isEmpty()) {
            errors.add("未设置设备: " + String.join("、", missingMachines));
        }
        if (!errors.isEmpty()) {
            throw new IllegalArgumentException(String.join("；", errors));
        }
    }

    private void attachTask(XyImportTask task, List<Long> ids) {
        LocalDateTime now = LocalDateTime.now();
        if (ORDER.equals(task.getBusinessType())) {
            dataService.ordersByIds(ids).forEach(item -> {
                item.setLastTaskId(task.getId());
                item.setReadState(true);
                item.setReadTime(now);
                dataService.updateOrder(item);
            });
        } else {
            dataService.steelPlatesByIds(ids).forEach(item -> {
                item.setLastTaskId(task.getId());
                item.setReadState(true);
                item.setReadTime(now);
                dataService.updateSteelPlate(item);
            });
        }
    }

    private void markBusinessRowsImported(String businessType, List<Long> ids) {
        LocalDateTime now = LocalDateTime.now();
        if (ORDER.equals(businessType)) {
            dataService.ordersByIds(ids).forEach(item -> {
                item.setSendState(true);
                item.setSendTime(now);
                dataService.updateOrder(item);
            });
        } else {
            dataService.steelPlatesByIds(ids).forEach(item -> {
                item.setSendState(true);
                item.setSendTime(now);
                dataService.updateSteelPlate(item);
            });
        }
    }

    private static void validateOrder(XyManufacturingOrder order, XyBasePart part) {
        requireText(order.getProductionOrderNumber(), "生产订单号不能为空");
        requireText(order.getProductionOrderErpInternalCode(), "生产订单ERP内码不能为空");
        requireText(order.getCusRef(), "计划跟踪号不能为空");
        requireText(order.getJobRef(), "生产订单未选择作业");
        requireText(order.getWrkRef(), "生产订单未选择设备");
        requireText(part.getPrdName(), "基础零件图号不能为空");
        requireText(part.getUdata3(), "基础零件ERP物料内码不能为空: " + part.getPrdRef());
        integerQuantity(order.getQuantity(), "生产订单数量必须为正整数");
    }

    public static String combineErpIdentity(String erpInternalCode, String planNumber) {
        requireText(erpInternalCode, "ERP内码不能为空");
        requireText(planNumber, "计划跟踪号不能为空");
        return erpInternalCode.trim() + "-" + planNumber.trim();
    }

    public static ErpIdentity splitErpIdentity(String composite) {
        if (!StringUtils.hasText(composite)) {
            return new ErpIdentity("", "");
        }
        int separator = composite.indexOf('-');
        if (separator < 0) {
            return new ErpIdentity(composite, "");
        }
        return new ErpIdentity(composite.substring(0, separator), composite.substring(separator + 1));
    }

    public static String importedOrderKey(String compositeCusRef, String jobRef) {
        return orderKey(splitErpIdentity(compositeCusRef).erpInternalCode(), jobRef);
    }

    static String orderKey(String erpInternalCode, String jobRef) {
        return String.valueOf(erpInternalCode) + "\u0000" + String.valueOf(jobRef);
    }

    public record ErpIdentity(String erpInternalCode, String planNumber) {
    }

    private static List<Long> normalizeIds(List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            throw new IllegalArgumentException("请选择要导入的数据");
        }
        List<Long> result = ids.stream().filter(Objects::nonNull).distinct().toList();
        if (result.isEmpty()) {
            throw new IllegalArgumentException("请选择要导入的数据");
        }
        return result;
    }

    private static String orderLabel(XyManufacturingOrder order) {
        if (StringUtils.hasText(order.getProductionOrderNumber())) {
            return order.getProductionOrderNumber();
        }
        if (StringUtils.hasText(order.getProductionOrderErpInternalCode())) {
            return order.getProductionOrderErpInternalCode();
        }
        return String.valueOf(order.getId());
    }

    private static Integer integerQuantity(Double value, String message) {
        if (value == null || value <= 0 || value > Integer.MAX_VALUE || value != Math.rint(value)) {
            throw new IllegalArgumentException(message);
        }
        return Math.toIntExact(Math.round(value));
    }

    private static Float floatValue(Double value) {
        return value == null ? null : value.floatValue();
    }

    private static void requireText(String value, String message) {
        if (!StringUtils.hasText(value)) {
            throw new IllegalArgumentException(message);
        }
    }

    private static String safeMessage(Exception exception) {
        return StringUtils.hasText(exception.getMessage())
                ? exception.getMessage()
                : exception.getClass().getSimpleName();
    }

    private static String limit(String value, int max) {
        return value.substring(0, Math.min(value.length(), max));
    }
}
