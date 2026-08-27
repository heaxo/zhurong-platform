package com.zhurong.platform.custom.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zhurong.platform.base.api.ApiResponse;
import com.zhurong.platform.base.api.PageResponse;
import com.zhurong.platform.custom.dto.XyInboundRequests;
import com.zhurong.platform.custom.dto.XyRequests;
import com.zhurong.platform.custom.entity.MmnnMmoo00000300;
import com.zhurong.platform.custom.entity.XyImportTask;
import com.zhurong.platform.custom.entity.XyManufacturingOrder;
import com.zhurong.platform.custom.service.*;
import com.zhurong.platform.custom.web.BaseController;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/xybaoyuan/manufacturing-orders")
public class XyManufacturingOrderController extends BaseController {
    private final XyDataService dataService;
    private final XyImportTaskService importTaskService;
    private final XyJobService jobService;
    private final XyInboundService inboundService;
    private final IMmnnMmoo00000300Service mmnnMmoo00000300Service;

    @GetMapping
    public ApiResponse<PageResponse<XyManufacturingOrder>> page(@ModelAttribute XyRequests.ManufacturingOrderPage query) {
        return ApiResponse.success(dataService.pageManufacturingOrders(query));
    }

    @PostMapping("/creates")
    public com.zhurong.platform.custom.api.ApiResponse<Boolean> creates(@Valid @RequestBody XyInboundRequests.ManufacturingOrders request) {
        return com.zhurong.platform.custom.api.ApiResponse.success(inboundService.receiveManufacturingOrders(request));
    }

    @PostMapping("/delete")
    public com.zhurong.platform.custom.api.ApiResponse<Boolean> delete(@Valid @RequestBody XyInboundRequests.ManufacturingOrderDeletes request) {

        List<String> reqProductionOrderErpInternalCodes = request.getProductionOrderERPInternalCodes();
        if (reqProductionOrderErpInternalCodes.isEmpty())
        {
            return com.zhurong.platform.custom.api.ApiResponse.fail("生产订单ERP内码不能为空");
        }

        List<MmnnMmoo00000300> mmnnMmoo00000300s = new ArrayList();

        boolean single = reqProductionOrderErpInternalCodes.size() == 1;

        if (single)
        {
            mmnnMmoo00000300s = mmnnMmoo00000300Service.list(Wrappers.lambdaQuery(MmnnMmoo00000300.class)
                    .eq(MmnnMmoo00000300::getMnORef, reqProductionOrderErpInternalCodes.get(0)));
        }
        else
        {
            mmnnMmoo00000300s = mmnnMmoo00000300Service.list(Wrappers.lambdaQuery(MmnnMmoo00000300.class)
                    .in(MmnnMmoo00000300::getMnORef, reqProductionOrderErpInternalCodes));
        }

        boolean expertNotExists = mmnnMmoo00000300s.isEmpty();


        if (!expertNotExists)
        {
            return com.zhurong.platform.custom.api.ApiResponse.fail(String.format("该生产订单ERP内码%s，已导入至LantekExpert中，删除失败", reqProductionOrderErpInternalCodes
                    .stream()
                    .collect(Collectors.joining(","))));
        }

        boolean deleteAsync = false;
        if (single)
        {
            deleteAsync = dataService.deleteOrderByProductionOrderERPInternalCode(reqProductionOrderErpInternalCodes.get(0)) > 0;
        }
        else
        {
            deleteAsync = dataService.deleteOrderByProductionOrderERPInternalCodes(reqProductionOrderErpInternalCodes) == reqProductionOrderErpInternalCodes.size();
        }

        return deleteAsync ? com.zhurong.platform.custom.api.ApiResponse.success(true): com.zhurong.platform.custom.api.ApiResponse.fail("删除失败");
    }

    @GetMapping("/job-exists")
    public ApiResponse<Boolean> jobExists(
            @RequestParam String jobName,
            @RequestParam(required = false) String jobPath
    ) {
        return ApiResponse.success(jobPath == null
                ? jobService.exists(jobName)
                : jobService.exists(jobName, jobPath));
    }

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
        return ApiResponse.success(importTaskService.importSynchronously(
                XyImportTaskService.ORDER,
                request.getIds()
        ));
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
