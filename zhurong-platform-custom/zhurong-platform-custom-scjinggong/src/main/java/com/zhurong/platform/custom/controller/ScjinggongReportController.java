package com.zhurong.platform.custom.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhurong.platform.base.api.ApiResponse;
import com.zhurong.platform.base.api.PageResponse;
import com.zhurong.platform.custom.dto.ReportQuery;
import com.zhurong.platform.custom.service.ScjinggongReportService;
import com.zhurong.platform.custom.vo.CuttingProgramReportRow;
import com.zhurong.platform.custom.vo.PartReportRow;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * 报表中心的唯一 Controller。增加报表时增加 page/export 路由并委托 Service；
 * 菜单只指向前端 report-center/index.vue，不需要为每份报表创建菜单。
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/scjinggongReports")
public class ScjinggongReportController {
    private final ScjinggongReportService service;
    private final ObjectMapper objectMapper;

    @GetMapping("/cutting-programs/page")
    public ApiResponse<PageResponse<CuttingProgramReportRow>> cuttingPrograms(ReportQuery query) {
        return ApiResponse.success(service.cuttingPrograms(query));
    }

    @GetMapping("/parts/page")
    public ApiResponse<PageResponse<PartReportRow>> parts(ReportQuery query) {
        return ApiResponse.success(service.parts(query));
    }

    @GetMapping("/cutting-programs/export")
    public void exportCuttingPrograms(ReportQuery query, HttpServletResponse response) throws IOException {
        try {
            service.exportCuttingPrograms(query, response);
        } catch (IllegalArgumentException exception) {
            writeExportError(response, exception);
        }
    }

    @GetMapping("/parts/export")
    public void exportParts(ReportQuery query, HttpServletResponse response) throws IOException {
        try {
            service.exportParts(query, response);
        } catch (IllegalArgumentException exception) {
            writeExportError(response, exception);
        }
    }

    /**
     * 导出超量等业务校验发生在写文件前。按项目的 ApiResponse 契约返回 JSON，
     * 前端虽然以 Blob 接收，也能读取 message 并提示用户。
     */
    private void writeExportError(HttpServletResponse response, IllegalArgumentException exception) throws IOException {
        if (response.isCommitted()) throw exception;
        response.reset();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        objectMapper.writeValue(response.getOutputStream(), ApiResponse.fail(exception.getMessage()));
    }
}
