package com.zhurong.platform.custom.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zhurong.platform.custom.dto.XyInboundRequests;
import com.zhurong.platform.custom.entity.PprrPprr00000100;
import com.zhurong.platform.custom.entity.XyBasePart;
import com.zhurong.platform.custom.entity.XyManufacturingOrder;
import com.zhurong.platform.custom.mapper.XyBasePartMapper;
import com.zhurong.platform.custom.mapper.XyManufacturingOrderMapper;
import com.zhurong.platform.custom.properties.XyBaoyuanProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.AtomicMoveNotSupportedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
@DS("lantek")
@RequiredArgsConstructor
public class XyInboundService {
    private static final int QUERY_BATCH_SIZE = 1_000;
    private static final int ROUTE_MAX_LENGTH = 40;
    private static final String[] BASE_PART_HEADERS = {
            "零件图号", "零件名称", "ERP零件编号（DIS_UData2_Prt）", "材质", "厚度",
            "工序次数（DIS_UData1_Prt）", "加工时长（DIS_UData4_Prt）", "ERP物料内码（DIS_UData3_Prt）",
            "图纸路径", "图纸目录", "图纸后缀"
    };
    private static final ReentrantLock BASE_PART_LOCK = new ReentrantLock(true);
    private static final ReentrantLock ORDER_LOCK = new ReentrantLock(true);

    private final XyBasePartMapper basePartMapper;
    private final XyManufacturingOrderMapper manufacturingOrderMapper;
    private final IPprrPprr00000100Service partService;
    private final XyBaoyuanProperties properties;

    @Transactional(rollbackFor = Exception.class)
    public boolean receiveBaseParts(XyInboundRequests.BaseParts request) {
        BASE_PART_LOCK.lock();
        try {
            List<XyInboundRequests.BasePart> items = request.getParts();
            assertUnique(items, XyInboundRequests.BasePart::getPrdName, "零件图号不能重复");
            assertUnique(items, XyInboundRequests.BasePart::getPrdRef, "零件编码不能重复");

            Path outputDirectory = requireOutputDirectory();
            String drawingRoot = requireText(properties.getInbound().getDrawingRoot(), "未配置零件图纸目录");
            String drawingExtension = normalizedExtension(properties.getInbound().getDrawingExtension());

            List<String> drawingCodes = items.stream().map(XyInboundRequests.BasePart::getPrdName)
                    .map(String::trim).toList();
            Set<String> lantekRefs = findLantekPartRefs(drawingCodes);
            Set<String> storedDrawingCodes = findStoredDrawingCodes(drawingCodes);

            List<XyInboundRequests.BasePart> newItems = items.stream()
                    .filter(item -> !lantekRefs.contains(normalize(item.getPrdName())))
                    .filter(item -> !storedDrawingCodes.contains(normalize(item.getPrdName())))
                    .toList();
            for (XyInboundRequests.BasePart item : newItems) {
                if (basePartMapper.insert(toEntity(item)) != 1) {
                    throw new IllegalStateException("零件基础信息保存失败: " + item.getPrdName());
                }
            }

            // 已落库但尚未进入 Lantek 的零件也重新写入文件，使金蝶重试可以恢复中断的后续导入。
            List<XyInboundRequests.BasePart> excelItems = items.stream()
                    .filter(item -> !lantekRefs.contains(normalize(item.getPrdName())))
                    .toList();
            appendBasePartWorkbook(outputDirectory, drawingRoot, drawingExtension, excelItems);
            log.info("象屿宝元接收基础零件完成, received={}, inserted={}, excelCandidates={}",
                    items.size(), newItems.size(), excelItems.size());
            return true;
        } finally {
            BASE_PART_LOCK.unlock();
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean receiveManufacturingOrders(XyInboundRequests.ManufacturingOrders request) {
        ORDER_LOCK.lock();
        try {
            List<XyInboundRequests.ManufacturingOrder> items = request.getProductionOrders();
            assertUnique(items, XyInboundRequests.ManufacturingOrder::getProductionOrderErpInternalCode, "生产订单ERP内码不能重复");

            List<String> erpCodes = items.stream()
                    .map(XyInboundRequests.ManufacturingOrder::getProductionOrderErpInternalCode).map(String::trim).toList();
            if (manufacturingOrderErpCodeExists(erpCodes)) {
                log.info("象屿宝元生产订单批次已存在，按0111幂等规则忽略整批, size={}", items.size());
                return true;
            }

            for (XyInboundRequests.ManufacturingOrder item : items) {
                XyManufacturingOrder entity = toEntity(item);
                if (manufacturingOrderMapper.insert(entity) != 1) {
                    throw new IllegalStateException("生产订单保存失败: " + item.getProductionOrderNumber());
                }
            }
            log.info("象屿宝元接收生产订单完成, inserted={}", items.size());
            return true;
        } finally {
            ORDER_LOCK.unlock();
        }
    }

    private Set<String> findLantekPartRefs(List<String> drawingCodes) {
        Set<String> result = new HashSet<>();
        forEachBatch(drawingCodes, batch -> partService.list(Wrappers.lambdaQuery(PprrPprr00000100.class)
                .in(PprrPprr00000100::getPrdRef, batch)).stream()
                .map(PprrPprr00000100::getPrdRef).filter(Objects::nonNull)
                .map(XyInboundService::normalize).forEach(result::add));
        return result;
    }

    private Set<String> findStoredDrawingCodes(List<String> drawingCodes) {
        Set<String> result = new HashSet<>();
        forEachBatch(drawingCodes, batch -> basePartMapper.selectList(Wrappers.lambdaQuery(XyBasePart.class)
                .in(XyBasePart::getPrdName, batch)).stream()
                .map(XyBasePart::getPrdName).filter(Objects::nonNull)
                .map(XyInboundService::normalize).forEach(result::add));
        return result;
    }

    private boolean manufacturingOrderErpCodeExists(List<String> erpCodes) {
        for (int start = 0; start < erpCodes.size(); start += QUERY_BATCH_SIZE) {
            int end = Math.min(start + QUERY_BATCH_SIZE, erpCodes.size());
            Set<String> erpBatch = new HashSet<>(erpCodes.subList(start, end));
            Long count = manufacturingOrderMapper.selectCount(Wrappers.lambdaQuery(XyManufacturingOrder.class)
                    .in(XyManufacturingOrder::getProductionOrderErpInternalCode, erpBatch));
            if (count != null && count > 0) return true;
        }
        return false;
    }

    private XyBasePart toEntity(XyInboundRequests.BasePart item) {
        XyBasePart entity = new XyBasePart();
        entity.setPrdRef(item.getPrdRef().trim());
        entity.setPrdName(item.getPrdName().trim());
        entity.setDrawingCode(item.getDrawingCode().trim());
        entity.setMatRef(item.getMatRef().trim());
        entity.setThickness(item.getThickness());
        entity.setUdata1(trimToNull(item.getUdata1()));
        entity.setUdata2(trimToNull(item.getUdata2()));
        entity.setUdata3(item.getUdata3().trim());
        entity.setInvalidState(false);
        return entity;
    }

    private XyManufacturingOrder toEntity(XyInboundRequests.ManufacturingOrder item) {
        XyManufacturingOrder entity = new XyManufacturingOrder();
        entity.setProductionOrderNumber(item.getProductionOrderNumber().trim());
        entity.setProductionOrderLineId(trimToNull(item.getProductionOrderLineId()));
        entity.setProductionOrderErpInternalCode(item.getProductionOrderErpInternalCode().trim());
        entity.setPrdRef(item.getPrdRef().trim());
        entity.setPrdName(item.getPrdName().trim());
        entity.setQuantity(item.getQuantity());
        entity.setMatRef(item.getMatRef().trim());
        entity.setThickness(item.getThickness());
        entity.setDeliveryDate(item.getDeliveryDate());
        entity.setRouRef(truncateRoute(item.getRouRef()));
        entity.setCusRef(item.getCusRef().trim());
        entity.setCusName(trimToNull(item.getCusName()));
        entity.setUdata1(item.getUdata1().trim());
        entity.setUdata2(item.getUdata2().trim());
        entity.setWorkCenter(trimToNull(item.getWorkCenter()));
        entity.setProductionWorkshopCode(item.getProductionWorkshopCode().trim());
        entity.setProductionWorkshopName(item.getProductionWorkshopName().trim());
        entity.setReadState(false);
        entity.setSendState(false);
        entity.setInvalidState(false);
        return entity;
    }

    private Path requireOutputDirectory() {
        String configured = requireText(properties.getInbound().getBasePartExcelDirectory(), "下料件Excel保存路径未设置");
        Path directory = Path.of(configured).toAbsolutePath().normalize();
        if (!Files.isDirectory(directory)) throw new IllegalArgumentException("下料件Excel保存路径不存在，请检查");
        return directory;
    }

    private void appendBasePartWorkbook(Path directory, String drawingRoot, String extension,
                                        List<XyInboundRequests.BasePart> items) {
        if (items.isEmpty()) return;
        Path target = directory.resolve("零件基础信息" + LocalDate.now() + ".xlsx");
        Path temporary = null;
        try (Workbook workbook = openWorkbook(target)) {
            Sheet sheet = workbook.getSheet("Sheet1");
            if (sheet == null) sheet = workbook.createSheet("Sheet1");
            ensureHeader(sheet);
            Set<String> existingErpMaterialIds = new HashSet<>();
            for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                Row row = sheet.getRow(rowIndex);
                if (row != null) {
                    String value = cellText(row.getCell(7));
                    if (StringUtils.hasText(value)) existingErpMaterialIds.add(value.trim());
                }
            }
            int rowIndex = Math.max(1, sheet.getLastRowNum() + 1);
            for (XyInboundRequests.BasePart item : items) {
                if (!existingErpMaterialIds.add(item.getUdata3().trim())) continue;
                Row row = sheet.createRow(rowIndex);
                set(row, 0, item.getPrdName());
                set(row, 1, item.getDrawingCode());
                set(row, 2, item.getPrdRef());
                set(row, 3, item.getMatRef());
                row.createCell(4).setCellValue(item.getThickness());
                set(row, 5, item.getUdata1());
                set(row, 6, item.getUdata2());
                set(row, 7, item.getUdata3());
                row.createCell(8).setCellFormula("CONCATENATE(J" + (rowIndex + 1) + ",A" + (rowIndex + 1) + ",K" + (rowIndex + 1) + ")");
                set(row, 9, drawingRoot);
                set(row, 10, extension);
                rowIndex++;
            }
            workbook.getCreationHelper().createFormulaEvaluator().evaluateAll();
            workbook.setForceFormulaRecalculation(true);
            temporary = Files.createTempFile(directory, "xybaoyuan-base-parts-", ".xlsx.tmp");
            try (OutputStream output = Files.newOutputStream(temporary)) {
                workbook.write(output);
            }
            replaceAtomically(temporary, target);
            temporary = null;
        } catch (IOException exception) {
            throw new IllegalStateException("零件基础信息Excel写入失败", exception);
        } finally {
            if (temporary != null) {
                try { Files.deleteIfExists(temporary); }
                catch (IOException exception) { log.warn("临时Excel文件清理失败: {}", temporary, exception); }
            }
        }
    }

    private static Workbook openWorkbook(Path target) throws IOException {
        if (!Files.exists(target)) return new XSSFWorkbook();
        try (InputStream input = Files.newInputStream(target)) {
            return WorkbookFactory.create(input);
        }
    }

    private static void ensureHeader(Sheet sheet) {
        Row header = sheet.getRow(0);
        if (header == null) header = sheet.createRow(0);
        for (int i = 0; i < BASE_PART_HEADERS.length; i++) {
            Cell cell = header.getCell(i);
            if (cell == null) cell = header.createCell(i);
            if (!StringUtils.hasText(cell.getStringCellValue())) cell.setCellValue(BASE_PART_HEADERS[i]);
        }
    }

    private static void replaceAtomically(Path source, Path target) throws IOException {
        try {
            Files.move(source, target, StandardCopyOption.ATOMIC_MOVE, StandardCopyOption.REPLACE_EXISTING);
        } catch (AtomicMoveNotSupportedException exception) {
            Files.move(source, target, StandardCopyOption.REPLACE_EXISTING);
        }
    }

    public static String truncateRoute(String value) {
        if (value == null) return null;
        StringBuilder result = new StringBuilder();
        int length = 0;
        for (int index = 0; index < value.length(); index++) {
            char character = value.charAt(index);
            int occupied = character >= 0x4E00 && character <= 0x9FFF ? 2 : 1;
            if (length + occupied > ROUTE_MAX_LENGTH) break;
            result.append(character);
            length += occupied;
        }
        return result.toString();
    }

    private static <T> void assertUnique(List<T> items, Function<T, String> keyGetter, String message) {
        Set<String> keys = items.stream().map(keyGetter).map(XyInboundService::normalize).collect(Collectors.toSet());
        if (keys.size() != items.size()) throw new IllegalArgumentException(message);
    }

    private static void forEachBatch(List<String> values, java.util.function.Consumer<List<String>> consumer) {
        for (int start = 0; start < values.size(); start += QUERY_BATCH_SIZE) {
            consumer.accept(new ArrayList<>(values.subList(start, Math.min(start + QUERY_BATCH_SIZE, values.size()))));
        }
    }

    private static String normalizedExtension(String value) {
        String extension = StringUtils.hasText(value) ? value.trim() : ".dxf";
        return extension.startsWith(".") ? extension : "." + extension;
    }

    private static String requireText(String value, String message) {
        if (!StringUtils.hasText(value)) throw new IllegalArgumentException(message);
        return value.trim();
    }

    private static String trimToNull(String value) {
        return StringUtils.hasText(value) ? value.trim() : null;
    }

    private static String cellText(Cell cell) {
        if (cell == null) return "";
        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue();
            case NUMERIC -> String.valueOf(cell.getNumericCellValue());
            case FORMULA -> cell.getCellFormula();
            default -> "";
        };
    }

    private static void set(Row row, int column, String value) {
        row.createCell(column).setCellValue(value == null ? "" : value);
    }

    private static String normalize(String value) {
        return value == null ? "" : value.trim().toUpperCase(Locale.ROOT);
    }
}
