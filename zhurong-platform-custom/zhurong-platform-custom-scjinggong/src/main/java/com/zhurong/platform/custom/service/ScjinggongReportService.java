package com.zhurong.platform.custom.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhurong.platform.base.api.PageResponse;
import com.zhurong.platform.custom.dto.ReportQuery;
import com.zhurong.platform.custom.mapper.ScjinggongReportMapper;
import com.zhurong.platform.custom.vo.CuttingProgramReportRow;
import com.zhurong.platform.custom.vo.PartReportRow;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
import java.util.List;
import java.util.function.BiConsumer;

/**
 * 报表中心：所有 SQL 在 mapper XML；这里仅负责分页边界和分批写 Excel。
 * 新增报表：增加 mapper 查询、一个 page 方法和一个 export 方法，参照现有方法即可。
 */
@Service
@DS("lantek")
@RequiredArgsConstructor
public class ScjinggongReportService {
    private static final int EXPORT_BATCH_SIZE = 500;
    private static final int MAX_EXPORT_ROWS = 20_000; // 图片会保存在工作簿中，限制单次导出内存。
    private static final long MAX_IMAGE_BYTES = 5L * 1024 * 1024;
    private final ScjinggongReportMapper mapper;

    public PageResponse<CuttingProgramReportRow> cuttingPrograms(ReportQuery query) {
        Page<CuttingProgramReportRow> page = mapper.cuttingPrograms(pageOf(query), query);
        return new PageResponse<>(page.getRecords(), page.getTotal(), page.getCurrent(), page.getSize());
    }

    public PageResponse<PartReportRow> parts(ReportQuery query) {
        Page<PartReportRow> page = mapper.parts(pageOf(query), query);
        for (PartReportRow row : page.getRecords()) {
            byte[] image = loadImage(row.getImagePath());
            if (image != null) {
                String mime = imageMime(image);
                if (mime != null) row.setImageDataUri("data:" + mime + ";base64," + Base64.getEncoder().encodeToString(image));
            }
        }
        return new PageResponse<>(page.getRecords(), page.getTotal(), page.getCurrent(), page.getSize());
    }

    public void exportCuttingPrograms(ReportQuery query, HttpServletResponse response) throws IOException {
        this.<CuttingProgramReportRow>export(query, response, "切割程序清单",
                new String[]{"程序号", "钢板尺寸", "材质", "数量", "钢板总重量", "整板利用率", "去除余料利用率"},
                (page, q) -> mapper.cuttingPrograms(page, q), (row, item) -> {
                    cell(row, 0, item.getCnc());
                    cell(row, 1, item.getPlateSize());
                    cell(row, 2, item.getMaterial());
                    cell(row, 3, item.getQuantity());
                    cell(row, 4, item.getTotalWeight());
                    cell(row, 5, item.getUtilization());
                    cell(row, 6, item.getUtilizationWithoutRemnant());
                });
    }

    public void exportParts(ReportQuery query, HttpServletResponse response) throws IOException {
        this.<PartReportRow>export(query, response, "零件清单",
                new String[]{"零件序号", "物料编码", "简图", "数量", "客户件号", "批次号", "材质", "厚度", "工序"},
                (page, q) -> mapper.parts(page, q), (row, item) -> {
                    cell(row, 0, item.getPartSequence());
                    cell(row, 1, item.getProductCode());
                    picture(row, 2, loadImage(item.getImagePath()));
                    cell(row, 3, item.getQuantity());
                    cell(row, 4, item.getCustomerPartNo());
                    cell(row, 5, item.getBatchNo());
                    cell(row, 6, item.getMaterial());
                    cell(row, 7, item.getThickness());
                    cell(row, 8, item.getProcess());
                });
    }

    private static <T> Page<T> pageOf(ReportQuery query) {
        long current = query.getCurrent() == null ? 1 : query.getCurrent();
        long size = query.getPageSize() == null ? 20 : query.getPageSize();
        return new Page<>(Math.max(1, current), Math.max(1, Math.min(500, size)));
    }

    private <T> void export(ReportQuery query, HttpServletResponse response, String name,
                            String[] titles, ReportLoader<T> loader, BiConsumer<Row, T> writer) throws IOException {
        Page<T> first = loader.load(new Page<>(1, 1), query);
        if (first.getTotal() > MAX_EXPORT_ROWS) {
            throw new IllegalArgumentException("当前筛选结果超过 " + MAX_EXPORT_ROWS + " 行，请缩小查询范围后导出");
        }
        SXSSFWorkbook workbook = new SXSSFWorkbook(100);
        workbook.setCompressTempFiles(true);
        try {
            Sheet sheet = workbook.createSheet(name);
            sheet.createFreezePane(0, 1);
            for (int i = 0; i < titles.length; i++) sheet.setColumnWidth(i, i == 2 && titles.length == 9 ? 25 * 256 : 20 * 256);
            Row header = sheet.createRow(0);
            CellStyle headerStyle = workbook.createCellStyle();
            Font font = workbook.createFont();
            font.setBold(true);
            headerStyle.setFont(font);
            for (int i = 0; i < titles.length; i++) {
                Cell cell = header.createCell(i);
                cell.setCellValue(titles[i]);
                cell.setCellStyle(headerStyle);
            }
            int rowNumber = 1;
            long total = first.getTotal();
            for (long pageNumber = 1; (pageNumber - 1) * EXPORT_BATCH_SIZE < total; pageNumber++) {
                Page<T> batch = new Page<>(pageNumber, EXPORT_BATCH_SIZE, false);
                List<T> records = loader.load(batch, query).getRecords();
                if (records.isEmpty()) break;
                for (T item : records) writer.accept(sheet.createRow(rowNumber++), item);
            }
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment; filename*=UTF-8''" +
                    URLEncoder.encode(name + ".xlsx", StandardCharsets.UTF_8).replace("+", "%20"));
            workbook.write(response.getOutputStream());
        } finally {
            workbook.dispose();
            workbook.close();
        }
    }

    private static void cell(Row row, int index, Object value) {
        if (value == null) return;
        // Excel 会把以公式前缀开头的文本当作公式；报表数据始终按纯文本写入。
        String text = value.toString();
        if (!text.isEmpty() && "=+-@".indexOf(text.charAt(0)) >= 0) text = "'" + text;
        row.createCell(index).setCellValue(text);
    }

    private static void picture(Row row, int index, byte[] bytes) {
        if (bytes == null || bytes.length < 4) return;
        int type;
        if ((bytes[0] & 0xff) == 0x89 && bytes[1] == 'P') type = Workbook.PICTURE_TYPE_PNG;
        else if ((bytes[0] & 0xff) == 0xff && (bytes[1] & 0xff) == 0xd8) type = Workbook.PICTURE_TYPE_JPEG;
        else {
            cell(row, index, "图片格式不支持");
            return;
        }
        Sheet sheet = row.getSheet();
        int pictureId = sheet.getWorkbook().addPicture(bytes, type);
        ClientAnchor anchor = sheet.getWorkbook().getCreationHelper().createClientAnchor();
        anchor.setCol1(index);
        anchor.setCol2(index + 1);
        anchor.setRow1(row.getRowNum());
        anchor.setRow2(row.getRowNum() + 1);
        sheet.createDrawingPatriarch().createPicture(anchor, pictureId);
        row.setHeightInPoints(60);
    }

    /** 文件路径来自 Lantek 的 Image 字段；路径失效或图片过大时只留空简图，不影响数据行。 */
    private static byte[] loadImage(String imagePath) {
        if (imagePath == null || imagePath.isBlank()) return null;
        try {
            Path path = Path.of(imagePath).toAbsolutePath().normalize();
            if (!Files.isRegularFile(path) || Files.size(path) > MAX_IMAGE_BYTES) return null;
            byte[] bytes = Files.readAllBytes(path);
            String mime = imageMime(bytes);
            if ("image/png".equals(mime) || "image/jpeg".equals(mime)) return bytes;
            // Excel 图片使用 PNG/JPEG；把浏览器可见的 GIF/BMP 统一转成 PNG。
            if ("image/gif".equals(mime) || "image/bmp".equals(mime)) {
                var image = ImageIO.read(new ByteArrayInputStream(bytes));
                if (image == null) return null;
                ByteArrayOutputStream output = new ByteArrayOutputStream();
                ImageIO.write(image, "png", output);
                return output.toByteArray();
            }
            return null;
        } catch (Exception ignored) {
            return null;
        }
    }

    private static String imageMime(byte[] bytes) {
        if (bytes.length < 4) return null;
        if ((bytes[0] & 0xff) == 0x89 && bytes[1] == 'P') return "image/png";
        if ((bytes[0] & 0xff) == 0xff && (bytes[1] & 0xff) == 0xd8) return "image/jpeg";
        if (bytes[0] == 'G' && bytes[1] == 'I') return "image/gif";
        if (bytes[0] == 'B' && bytes[1] == 'M') return "image/bmp";
        return null;
    }

    @FunctionalInterface
    private interface ReportLoader<T> {
        Page<T> load(Page<T> page, ReportQuery query);
    }
}
