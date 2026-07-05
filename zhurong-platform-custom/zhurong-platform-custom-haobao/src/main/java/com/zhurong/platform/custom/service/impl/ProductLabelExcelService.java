package com.zhurong.platform.custom.service.impl;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.zhurong.platform.custom.vo.LabelDataVO;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * 产品标签 Excel 生成服务。
 *
 * 支持：
 * 1. Excel 97-2003：.xls
 * 2. Excel 2007+：.xlsx
 *
 * 输出结构：
 * A1:C6    第 1 张标签
 * A7:C12   第 2 张标签
 * A13:C18  第 3 张标签
 *
 * 注意：
 * 传入的 LabelDataVO 已由 nestService.labelData() 完成业务合并。
 * 本服务不会进行任何订单、品号、套料数据的二次分组或合并，
 * 只负责按 quantity 展开标签。
 */
@Service
public class ProductLabelExcelService {

    /**
     * 自动查找模板，优先使用 xlsx。
     *
     * 项目中只需要放入其中一个文件。
     */
    private static final List<String> TEMPLATE_LOCATIONS = List.of(
            "classpath:templates/label/product-label-template.xlsx",
            "classpath:templates/label/product-label-template.xls"
    );

    /**
     * 优先查找这个工作表。
     *
     * 如果没有找到，则使用模板中的第一个工作表。
     */
    private static final String TEMPLATE_SHEET_NAME =
            "LABEL_TEMPLATE";

    private static final String OUTPUT_SHEET_NAME =
            "产品标签";

    /**
     * 每张标签占 6 行。
     */
    private static final int LABEL_ROW_COUNT = 6;

    /**
     * 标签使用 A:C 列。
     *
     * POI 的行列下标从 0 开始：
     * A = 0
     * C = 2
     */
    private static final int FIRST_COLUMN_INDEX = 0;
    private static final int LAST_COLUMN_INDEX = 2;

    /**
     * 二维码所在列：C。
     */
    private static final int QR_COLUMN_INDEX = 2;

    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy/M/d");

    private final ResourceLoader resourceLoader;

    public ProductLabelExcelService(
            ResourceLoader resourceLoader
    ) {
        this.resourceLoader = resourceLoader;
    }

    /**
     * 生成标签 Excel。
     *
     * @param sourceData       nestService.labelData() 返回的数据
     * @param expandByQuantity 是否按照 quantity 展开
     * @return Excel 文件内容、文件名和 Content-Type
     */
    public GeneratedLabelExcel generate(
            List<LabelDataVO> sourceData,
            boolean expandByQuantity
    ) {
        validateSourceData(sourceData);

        /*
         * 这里只做数量展开，不做分组或合并。
         */
        List<ResolvedLabel> labels = expandLabels(
                sourceData,
                expandByQuantity
        );

        if (labels.isEmpty()) {
            throw new IllegalArgumentException(
                    "没有可导出的标签，请检查数量字段"
            );
        }

        Resource templateResource =
                resolveTemplateResource();

        try (
                InputStream resourceInputStream =
                        templateResource.getInputStream();

                BufferedInputStream inputStream =
                        new BufferedInputStream(
                                resourceInputStream
                        );

                /*
                 * 自动识别实际文件格式：
                 *
                 * OLE2/BIFF8 -> HSSFWorkbook -> .xls
                 * OOXML      -> XSSFWorkbook -> .xlsx
                 */
                Workbook workbook =
                        WorkbookFactory.create(inputStream);

                ByteArrayOutputStream outputStream =
                        new ByteArrayOutputStream()
        ) {
            validateLabelCapacity(
                    workbook,
                    labels.size()
            );

            Sheet sheet =
                    resolveTemplateSheet(workbook);

            ensureTemplateCells(sheet);

            int sheetIndex =
                    workbook.getSheetIndex(sheet);

            workbook.setSheetName(
                    sheetIndex,
                    OUTPUT_SHEET_NAME
            );

            /*
             * 必须先记录模板 A1:C6 的合并区域。
             * 后续复制标签时，根据这些信息创建新的合并区域。
             */
            List<CellRangeAddress> templateMergedRegions =
                    findTemplateMergedRegions(sheet);

            /*
             * 清除模板中已有的手工分页符，
             * 避免和新生成的分页符冲突。
             */
            clearRowBreaks(sheet);

            /*
             * 第一张标签直接使用模板 A1:C6。
             * 第二张开始复制模板到后续位置。
             */
            for (
                    int labelIndex = 1;
                    labelIndex < labels.size();
                    labelIndex++
            ) {
                int targetStartRow =
                        labelIndex * LABEL_ROW_COUNT;

                copyTemplateBlock(
                        sheet,
                        targetStartRow,
                        templateMergedRegions
                );
            }

            Drawing<?> drawing =
                    sheet.createDrawingPatriarch();

            /*
             * 同一个二维码内容只生成一次 PNG，
             * 减少重复生成二维码的耗时。
             */
            Map<String, byte[]> qrCodeCache =
                    new HashMap<>();

            for (
                    int labelIndex = 0;
                    labelIndex < labels.size();
                    labelIndex++
            ) {
                int startRow =
                        labelIndex * LABEL_ROW_COUNT;

                fillLabel(
                        workbook,
                        sheet,
                        drawing,
                        startRow,
                        labels.get(labelIndex),
                        qrCodeCache
                );

                /*
                 * 最后一张标签后面不插入分页符。
                 *
                 * 第 6 行后分页：POI rowIndex = 5
                 * 第 12 行后分页：POI rowIndex = 11
                 */
                if (labelIndex < labels.size() - 1) {
                    sheet.setRowBreak(
                            startRow
                                    + LABEL_ROW_COUNT
                                    - 1
                    );
                }
            }

            configurePrint(
                    workbook,
                    sheet,
                    labels.size()
            );

            workbook.setActiveSheet(sheetIndex);
            sheet.setSelected(true);

            workbook.write(outputStream);

            boolean xlsFormat =
                    workbook instanceof HSSFWorkbook;

            String fileName = xlsFormat
                    ? "产品标签.xls"
                    : "产品标签.xlsx";

            String contentType = xlsFormat
                    ? "application/vnd.ms-excel"
                    : "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

            return new GeneratedLabelExcel(
                    outputStream.toByteArray(),
                    fileName,
                    contentType,
                    labels.size()
            );
        } catch (Exception exception) {
            throw new IllegalStateException(
                    "生成标签 Excel 失败，模板："
                            + templateResource.getDescription(),
                    exception
            );
        }
    }

    private void validateSourceData(
            List<LabelDataVO> sourceData
    ) {
        if (sourceData == null || sourceData.isEmpty()) {
            throw new IllegalArgumentException(
                    "标签数据不能为空"
            );
        }
    }
    /**
     * 确保模板 A1:C6 中所有单元格真实存在。
     *
     * Excel 中看得到空白格，不代表 POI 可以读取到 Cell 对象。
     */
    private void ensureTemplateCells(
            Sheet sheet
    ) {
        for (
                int rowIndex = 0;
                rowIndex < LABEL_ROW_COUNT;
                rowIndex++
        ) {
            Row row = sheet.getRow(rowIndex);

            if (row == null) {
                row = sheet.createRow(rowIndex);
            }

            for (
                    int columnIndex = FIRST_COLUMN_INDEX;
                    columnIndex <= LAST_COLUMN_INDEX;
                    columnIndex++
            ) {
                row.getCell(
                        columnIndex,
                        Row.MissingCellPolicy
                                .CREATE_NULL_AS_BLANK
                );
            }
        }
    }
    /**
     * 查找模板。
     *
     * 优先顺序：
     * 1. product-label-template.xlsx
     * 2. product-label-template.xls
     */
    private Resource resolveTemplateResource() {
        for (String location : TEMPLATE_LOCATIONS) {
            Resource resource =
                    resourceLoader.getResource(location);

            if (resource.exists() &&
                    resource.isReadable()) {
                return resource;
            }
        }

        throw new IllegalStateException(
                "未找到标签模板，请放入以下任意一个文件："
                        + String.join(
                        " 或 ",
                        TEMPLATE_LOCATIONS
                )
        );
    }

    /**
     * 优先查找 LABEL_TEMPLATE，
     * 找不到时使用模板中的第一个工作表。
     */
    private Sheet resolveTemplateSheet(
            Workbook workbook
    ) {
        Sheet sheet =
                workbook.getSheet(
                        TEMPLATE_SHEET_NAME
                );

        if (sheet != null) {
            return sheet;
        }

        if (workbook.getNumberOfSheets() == 0) {
            throw new IllegalStateException(
                    "标签模板中没有工作表"
            );
        }

        return workbook.getSheetAt(0);
    }

    /**
     * 检查当前 Excel 格式的最大行数。
     *
     * .xls 最大行数明显小于 .xlsx。
     */
    private void validateLabelCapacity(
            Workbook workbook,
            int labelCount
    ) {
        SpreadsheetVersion spreadsheetVersion =
                workbook instanceof HSSFWorkbook
                        ? SpreadsheetVersion.EXCEL97
                        : SpreadsheetVersion.EXCEL2007;

        long requiredRows =
                (long) labelCount * LABEL_ROW_COUNT;

        int maxRows =
                spreadsheetVersion.getMaxRows();

        if (requiredRows > maxRows) {
            long maxLabelCount =
                    maxRows / LABEL_ROW_COUNT;

            throw new IllegalArgumentException(
                    "当前 Excel 格式最多支持约 "
                            + maxLabelCount
                            + " 张标签，本次需要生成 "
                            + labelCount
                            + " 张标签，请分批导出"
            );
        }
    }

    /**
     * 只按照 quantity 展开，不做任何合并。
     *
     * expandByQuantity = true：
     * quantity=5 -> 生成 5 张标签，每张数量显示 1。
     *
     * expandByQuantity = false：
     * quantity=5 -> 生成 1 张标签，数量显示 5。
     */
    private List<ResolvedLabel> expandLabels(
            List<LabelDataVO> sourceData,
            boolean expandByQuantity
    ) {
        List<ResolvedLabel> result =
                new ArrayList<>();

        for (LabelDataVO item : sourceData) {
            if (item == null) {
                continue;
            }

            int sourceQuantity =
                    item.getQuantity() == null
                            ? 0
                            : Math.max(
                            0,
                            item.getQuantity()
                    );

            int copyCount =
                    expandByQuantity
                            ? sourceQuantity
                            : 1;

            for (
                    int copyIndex = 0;
                    copyIndex < copyCount;
                    copyIndex++
            ) {
                int displayQuantity =
                        expandByQuantity
                                ? 1
                                : sourceQuantity;

                result.add(
                        new ResolvedLabel(
                                item.getOrdRef(),
                                item.getPrdRef(),
                                item.getPrdName(),
                                item.getColor(),
                                displayQuantity,
                                item.getDate(),
                                item.getQrCodeContent()
                        )
                );
            }
        }

        return result;
    }

    /**
     * 获取模板 A1:C6 中的合并区域。
     */
    private List<CellRangeAddress>
    findTemplateMergedRegions(
            Sheet sheet
    ) {
        List<CellRangeAddress> result =
                new ArrayList<>();

        for (
                int index = 0;
                index < sheet.getNumMergedRegions();
                index++
        ) {
            CellRangeAddress region =
                    sheet.getMergedRegion(index);

            boolean insideTemplate =
                    region.getFirstRow() >= 0
                            && region.getLastRow()
                            < LABEL_ROW_COUNT
                            && region.getFirstColumn()
                            >= FIRST_COLUMN_INDEX
                            && region.getLastColumn()
                            <= LAST_COLUMN_INDEX;

            if (!insideTemplate) {
                continue;
            }

            result.add(
                    new CellRangeAddress(
                            region.getFirstRow(),
                            region.getLastRow(),
                            region.getFirstColumn(),
                            region.getLastColumn()
                    )
            );
        }

        return result;
    }

    /**
     * 将模板 A1:C6 复制到目标位置。
     *
     * 保留：
     * 1. 行高
     * 2. 单元格样式
     * 3. 边框
     * 4. 字体
     * 5. 填充
     * 6. 水平、垂直对齐
     * 7. 合并区域
     */
    private void copyTemplateBlock(
            Sheet sheet,
            int targetStartRow,
            List<CellRangeAddress> templateMergedRegions
    ) {
        for (
                int rowOffset = 0;
                rowOffset < LABEL_ROW_COUNT;
                rowOffset++
        ) {
            Row sourceRow = sheet.getRow(rowOffset);

            if (sourceRow == null) {
                throw new IllegalStateException(
                        "模板缺少第 "
                                + (rowOffset + 1)
                                + " 行"
                );
            }

            int targetRowIndex =
                    targetStartRow + rowOffset;

            Row targetRow =
                    sheet.getRow(targetRowIndex);

            if (targetRow == null) {
                targetRow =
                        sheet.createRow(targetRowIndex);
            }

            targetRow.setHeight(
                    sourceRow.getHeight()
            );

            targetRow.setZeroHeight(
                    sourceRow.getZeroHeight()
            );

            if (sourceRow.getRowStyle() != null) {
                targetRow.setRowStyle(
                        sourceRow.getRowStyle()
                );
            }

            for (
                    int columnIndex = FIRST_COLUMN_INDEX;
                    columnIndex <= LAST_COLUMN_INDEX;
                    columnIndex++
            ) {
                /*
                 * 空白单元格也创建出来，
                 * 避免 B1、B2 等模板占位单元格不存在。
                 */
                Cell sourceCell =
                        sourceRow.getCell(
                                columnIndex,
                                Row.MissingCellPolicy
                                        .CREATE_NULL_AS_BLANK
                        );

                Cell targetCell =
                        targetRow.getCell(
                                columnIndex,
                                Row.MissingCellPolicy
                                        .CREATE_NULL_AS_BLANK
                        );

                targetCell.setCellStyle(
                        sourceCell.getCellStyle()
                );

                copyCellValue(
                        sourceCell,
                        targetCell
                );
            }
        }

        for (
                CellRangeAddress sourceRegion :
                templateMergedRegions
        ) {
            CellRangeAddress targetRegion =
                    new CellRangeAddress(
                            sourceRegion.getFirstRow()
                                    + targetStartRow,
                            sourceRegion.getLastRow()
                                    + targetStartRow,
                            sourceRegion.getFirstColumn(),
                            sourceRegion.getLastColumn()
                    );

            if (!hasMergedRegion(
                    sheet,
                    targetRegion
            )) {
                sheet.addMergedRegion(
                        targetRegion
                );
            }
        }
    }

    private void copyCellValue(
            Cell sourceCell,
            Cell targetCell
    ) {
        CellType cellType =
                sourceCell.getCellType();

        switch (cellType) {
            case STRING ->
                    targetCell.setCellValue(
                            sourceCell
                                    .getStringCellValue()
                    );

            case NUMERIC ->
                    targetCell.setCellValue(
                            sourceCell
                                    .getNumericCellValue()
                    );

            case BOOLEAN ->
                    targetCell.setCellValue(
                            sourceCell
                                    .getBooleanCellValue()
                    );

            case FORMULA ->
                    targetCell.setCellFormula(
                            sourceCell
                                    .getCellFormula()
                    );

            case ERROR ->
                    targetCell.setCellErrorValue(
                            sourceCell
                                    .getErrorCellValue()
                    );

            case BLANK ->
                    targetCell.setBlank();

            default ->
                    targetCell.setCellValue(
                            sourceCell.toString()
                    );
        }
    }

    private boolean hasMergedRegion(
            Sheet sheet,
            CellRangeAddress expected
    ) {
        for (
                int index = 0;
                index < sheet.getNumMergedRegions();
                index++
        ) {
            CellRangeAddress current =
                    sheet.getMergedRegion(index);

            boolean same =
                    current.getFirstRow()
                            == expected.getFirstRow()
                            && current.getLastRow()
                            == expected.getLastRow()
                            && current.getFirstColumn()
                            == expected.getFirstColumn()
                            && current.getLastColumn()
                            == expected.getLastColumn();

            if (same) {
                return true;
            }
        }

        return false;
    }

    /**
     * 填充一张标签。
     */
    private void fillLabel(
            Workbook workbook,
            Sheet sheet,
            Drawing<?> drawing,
            int startRow,
            ResolvedLabel data,
            Map<String, byte[]> qrCodeCache
    ) throws Exception {
        /*
         * B1：订单
         */
        setCellValue(
                sheet,
                startRow,
                1,
                data.ordRef()
        );

        /*
         * B2：品号
         */
        setCellValue(
                sheet,
                startRow + 1,
                1,
                data.prdRef()
        );

        /*
         * B3：品名
         */
        setCellValue(
                sheet,
                startRow + 2,
                1,
                data.prdName()
        );

        /*
         * B4：颜色
         */
        setCellValue(
                sheet,
                startRow + 3,
                1,
                data.color()
        );

        /*
         * B5：数量
         */
        setCellValue(
                sheet,
                startRow + 4,
                1,
                String.valueOf(
                        data.quantity()
                )
        );

        /*
         * B6：日期
         */
        setCellValue(
                sheet,
                startRow + 5,
                1,
                data.date() == null
                        ? ""
                        : data.date().format(
                        DATE_FORMATTER
                )
        );

        /*
         * 清除 C5:C6 左上角中的：
         * 1. #VALUE!
         * 2. IMAGE 公式
         * 3. 模板示例文字
         */
        setCellValue(
                sheet,
                startRow + 4,
                QR_COLUMN_INDEX,
                ""
        );

        insertQrCode(
                workbook,
                drawing,
                startRow,
                data.qrCodeContent(),
                qrCodeCache
        );
    }

    private void setCellValue(
            Sheet sheet,
            int rowIndex,
            int columnIndex,
            String value
    ) {
        Row row = sheet.getRow(rowIndex);

        if (row == null) {
            row = sheet.createRow(rowIndex);
        }

        /*
         * Excel 中的空白单元格可能没有真实存储，
         * 因此不存在时直接创建。
         */
        Cell cell = row.getCell(
                columnIndex,
                Row.MissingCellPolicy.CREATE_NULL_AS_BLANK
        );

        cell.setCellValue(
                value == null ? "" : value
        );
    }

    /**
     * 在 C5:C6 区域插入二维码。
     *
     * CreationHelper 会根据当前工作簿格式创建：
     * 1. HSSFClientAnchor
     * 2. XSSFClientAnchor
     */
    private void insertQrCode(
            Workbook workbook,
            Drawing<?> drawing,
            int startRow,
            String content,
            Map<String, byte[]> qrCodeCache
    ) throws Exception {
        if (!StringUtils.hasText(content)) {
            return;
        }

        byte[] qrCodeBytes =
                qrCodeCache.computeIfAbsent(
                        content,
                        key -> {
                            try {
                                return createQrCodePng(key);
                            } catch (Exception exception) {
                                throw new QrCodeGenerateRuntimeException(
                                        exception
                                );
                            }
                        }
                );

        int pictureIndex =
                workbook.addPicture(
                        qrCodeBytes,
                        Workbook.PICTURE_TYPE_PNG
                );

        CreationHelper creationHelper =
                workbook.getCreationHelper();

        ClientAnchor anchor =
                creationHelper.createClientAnchor();

        /*
         * 二维码覆盖 C5:C6。
         *
         * 第 1 张：
         * C5:C6
         *
         * 第 2 张：
         * C11:C12
         */
        anchor.setCol1(QR_COLUMN_INDEX);
        anchor.setRow1(startRow + 4);

        anchor.setCol2(QR_COLUMN_INDEX + 1);
        anchor.setRow2(startRow + 6);

        anchor.setAnchorType(
                ClientAnchor.AnchorType
                        .MOVE_AND_RESIZE
        );

        drawing.createPicture(
                anchor,
                pictureIndex
        );
    }

    private byte[] createQrCodePng(
            String content
    ) throws Exception {
        Map<EncodeHintType, Object> hints =
                new EnumMap<>(
                        EncodeHintType.class
                );

        hints.put(
                EncodeHintType.CHARACTER_SET,
                "UTF-8"
        );

        hints.put(
                EncodeHintType.ERROR_CORRECTION,
                ErrorCorrectionLevel.M
        );

        /*
         * 保留一个模块的二维码静区。
         */
        hints.put(
                EncodeHintType.MARGIN,
                1
        );

        BitMatrix matrix =
                new QRCodeWriter().encode(
                        content,
                        BarcodeFormat.QR_CODE,
                        600,
                        600,
                        hints
                );

        try (
                ByteArrayOutputStream outputStream =
                        new ByteArrayOutputStream()
        ) {
            MatrixToImageWriter.writeToStream(
                    matrix,
                    "PNG",
                    outputStream
            );

            return outputStream.toByteArray();
        }
    }

    private void clearRowBreaks(
            Sheet sheet
    ) {
        int[] rowBreaks =
                sheet.getRowBreaks();

        for (int rowBreak : rowBreaks) {
            sheet.removeRowBreak(rowBreak);
        }
    }

    /**
     * 配置打印区域和分页。
     *
     * 不修改模板中的：
     * 1. 纸张规格
     * 2. 横向/纵向
     * 3. 页边距
     *
     * 这些设置继续使用客户模板中的原值。
     */
    private void configurePrint(
            Workbook workbook,
            Sheet sheet,
            int labelCount
    ) {
        int sheetIndex =
                workbook.getSheetIndex(sheet);

        int lastRowIndex =
                labelCount * LABEL_ROW_COUNT - 1;

        /*
         * 打印区域仅包含 A:C，
         * 排除右侧备注内容。
         */
        workbook.setPrintArea(
                sheetIndex,
                FIRST_COLUMN_INDEX,
                LAST_COLUMN_INDEX,
                0,
                lastRowIndex
        );

        sheet.setPrintGridlines(false);
        sheet.setDisplayGridlines(false);

        /*
         * 禁止把所有标签自动缩放到一页。
         */
        sheet.setAutobreaks(false);
        sheet.setFitToPage(false);

        PrintSetup printSetup =
                sheet.getPrintSetup();

        printSetup.setScale((short) 100);
        printSetup.setFitWidth((short) 0);
        printSetup.setFitHeight((short) 0);
    }

    /**
     * 导出结果。
     *
     * Controller 根据这里返回的文件名和 Content-Type
     * 输出正确格式。
     */
    public record GeneratedLabelExcel(
            byte[] content,
            String fileName,
            String contentType,
            int labelCount
    ) {
    }

    /**
     * 仅承载按数量展开后的单张标签。
     *
     * 没有任何分组、合并逻辑。
     */
    private record ResolvedLabel(
            String ordRef,
            String prdRef,
            String prdName,
            String color,
            int quantity,
            LocalDateTime date,
            String qrCodeContent
    ) {
    }

    private static class QrCodeGenerateRuntimeException
            extends RuntimeException {

        private QrCodeGenerateRuntimeException(
                Throwable cause
        ) {
            super(cause);
        }
    }
}