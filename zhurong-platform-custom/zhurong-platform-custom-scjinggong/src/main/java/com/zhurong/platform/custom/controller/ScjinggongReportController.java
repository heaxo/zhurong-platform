package com.zhurong.platform.custom.controller;

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
        service.exportCuttingPrograms(query, response);
    }

    @GetMapping("/parts/export")
    public void exportParts(ReportQuery query, HttpServletResponse response) throws IOException {
        service.exportParts(query, response);
    }
}
