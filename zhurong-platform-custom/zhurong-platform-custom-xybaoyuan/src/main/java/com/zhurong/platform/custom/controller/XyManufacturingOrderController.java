package com.zhurong.platform.custom.controller;

import com.zhurong.platform.base.api.ApiResponse;
import com.zhurong.platform.base.api.PageResponse;
import com.zhurong.platform.custom.dto.XyRequests;
import com.zhurong.platform.custom.dto.XyInboundRequests;
import com.zhurong.platform.custom.entity.XyImportTask;
import com.zhurong.platform.custom.entity.XyManufacturingOrder;
import com.zhurong.platform.custom.service.XyDataService;
import com.zhurong.platform.custom.service.XyImportTaskService;
import com.zhurong.platform.custom.service.XyInboundService;
import com.zhurong.platform.custom.service.XyJobService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/xybaoyuan/manufacturing-orders")
public class XyManufacturingOrderController {
    private final XyDataService dataService;
    private final XyImportTaskService importTaskService;
    private final XyJobService jobService;
    private final XyInboundService inboundService;

    @GetMapping
    public ApiResponse<PageResponse<XyManufacturingOrder>> page(@ModelAttribute XyRequests.ManufacturingOrderPage query) {
        return ApiResponse.success(dataService.pageManufacturingOrders(query));
    }

    @PostMapping("/creates")
    public ApiResponse<Boolean> creates(@Valid @RequestBody XyInboundRequests.ManufacturingOrders request) {
        return ApiResponse.success(inboundService.receiveManufacturingOrders(request));
    }

    @GetMapping("/job-exists")
    public ApiResponse<Boolean> jobExists(@RequestParam String jobName) { return ApiResponse.success(jobService.exists(jobName)); }

    @PostMapping("/jobs")
    public ApiResponse<String> createJob(@Valid @RequestBody XyRequests.JobCreate request) {
        return ApiResponse.success(jobService.create(request.getJobName(), request.getJobPath()));
    }

    @PutMapping("/batch")
    public ApiResponse<Void> batchUpdate(@RequestBody List<XyRequests.OrderUpdate> updates) {
        dataService.batchUpdateOrders(updates); return ApiResponse.success();
    }

    @PostMapping("/import")
    public ApiResponse<XyImportTask> importNow(@Valid @RequestBody XyRequests.Ids request) {
        return ApiResponse.success(importTaskService.importSynchronously(XyImportTaskService.ORDER, request.getIds()));
    }

    @GetMapping("/export")
    public ResponseEntity<byte[]> export(@ModelAttribute XyRequests.ManufacturingOrderPage query) {
        query.setSize(100_000L);
        StringBuilder csv = new StringBuilder("\uFEFF生产订单号,ERP内码,车间,零件编号,零件名称,图号,材质,厚度,数量,计划跟踪号,设备,作业\r\n");
        dataService.pageManufacturingOrders(query).items().forEach(item -> csv.append(XyBasePartController.row(
                item.getProductionOrderNumber(), item.getProductionOrderErpInternalCode(), item.getProductionWorkshopName(),
                item.getPrdRef(), item.getPrdName(), item.getDrawingCode(), item.getMatRef(), item.getThickness(), item.getQuantity(),
                item.getCusRef(), item.getWrkRef(), item.getJobName())));
        return XyBasePartController.csv("xybaoyuan-manufacturing-orders.csv", csv);
    }
}
