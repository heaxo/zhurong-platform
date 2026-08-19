package com.zhurong.platform.custom.controller;

import com.zhurong.platform.base.api.ApiResponse;
import com.zhurong.platform.base.api.PageResponse;
import com.zhurong.platform.custom.dto.XyRequests;
import com.zhurong.platform.custom.entity.XyImportTask;
import com.zhurong.platform.custom.entity.XySteelPlate;
import com.zhurong.platform.custom.service.XyDataService;
import com.zhurong.platform.custom.service.XyErpSteelPlateService;
import com.zhurong.platform.custom.service.XyImportTaskService;
import com.zhurong.platform.custom.web.BaseController;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/xybaoyuan/steel-plates")
public class XySteelPlateController extends BaseController {
    private final XyDataService dataService;
    private final XyErpSteelPlateService erpService;
    private final XyImportTaskService importTaskService;

    @GetMapping
    public ApiResponse<PageResponse<XySteelPlate>> page(@ModelAttribute XyRequests.SteelPlatePage query) {
        return ApiResponse.success(dataService.pageSteelPlates(query));
    }

    @PostMapping("/sync-erp")
    public ApiResponse<Integer> syncErp(@Valid @RequestBody XyRequests.SteelPlateSync query) {
        return ApiResponse.success(erpService.synchronize(query.getPrdRef(), query.getLotNumber()));
    }

    @PostMapping("/import")
    public ApiResponse<XyImportTask> importNow(@Valid @RequestBody XyRequests.Ids request) {
        return ApiResponse.success(importTaskService.importSynchronously(
                XyImportTaskService.STEEL_PLATE,
                request.getIds()
        ));
    }

    @DeleteMapping
    public ApiResponse<Void> delete(@Valid @RequestBody XyRequests.Ids request) {
        dataService.deleteSteelPlates(request.getIds()); return ApiResponse.success();
    }

    @GetMapping("/export")
    public ResponseEntity<byte[]> export(@ModelAttribute XyRequests.SteelPlatePage query) {
        query.setSize(100_000L);
        StringBuilder csv = new StringBuilder("\uFEFF钢板编号,钢板名称,规格,材质,仓库,仓库编码,吨数,数量,厚度,宽度,长度\r\n");
        dataService.pageSteelPlates(query).items().forEach(item -> csv.append(XyBasePartController.row(
                item.getPrdRef(), item.getPrdName(), item.getSpecification(), item.getMatRef(), item.getStockName(),
                item.getStockNumber(), item.getTons(), item.getQuantity(), item.getThickness(), item.getWidth(), item.getLength())));
        return XyBasePartController.csv("xybaoyuan-steel-plates.csv", csv);
    }
}
