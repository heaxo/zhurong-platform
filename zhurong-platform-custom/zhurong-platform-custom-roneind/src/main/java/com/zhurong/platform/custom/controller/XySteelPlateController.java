package com.zhurong.platform.custom.controller;

import com.zhurong.platform.base.api.ApiResponse;
import com.zhurong.platform.base.api.PageResponse;
import com.zhurong.platform.custom.dto.RoneindRequests;
import com.zhurong.platform.custom.entity.RoneindImportTask;
import com.zhurong.platform.custom.entity.RoneindSteelPlate;
import com.zhurong.platform.custom.service.RoneindDataService;
import com.zhurong.platform.custom.service.RoneindErpSteelPlateService;
import com.zhurong.platform.custom.service.RoneindImportTaskService;
import com.zhurong.platform.custom.web.BaseController;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/roneind/steel-plates")
public class RoneindSteelPlateController extends BaseController {
    private final RoneindDataService dataService;
    private final RoneindErpSteelPlateService erpService;
    private final RoneindImportTaskService importTaskService;

    @GetMapping
    public ApiResponse<PageResponse<RoneindSteelPlate>> page(@ModelAttribute RoneindRequests.SteelPlatePage query) {
        return ApiResponse.success(dataService.pageSteelPlates(query));
    }

    @PostMapping("/sync-erp")
    public ApiResponse<Integer> syncErp(@Valid @RequestBody RoneindRequests.SteelPlateSync query) {
        try{
            return ApiResponse.success(erpService.synchronize(query.getPrdRef(), query.getLotNumber()));
        } catch (Exception e) {
            return ApiResponse.fail(e.getMessage());
        }
    }

    @PostMapping("/import")
    public ApiResponse<RoneindImportTask> importNow(@Valid @RequestBody RoneindRequests.Ids request) {
        return ApiResponse.success(importTaskService.importSynchronously(
                RoneindImportTaskService.STEEL_PLATE,
                request.getIds()
        ));
    }

    @DeleteMapping
    public ApiResponse<Void> delete(@Valid @RequestBody RoneindRequests.Ids request) {
        dataService.deleteSteelPlates(request.getIds()); return ApiResponse.success();
    }

    @GetMapping("/export")
    public ResponseEntity<byte[]> export(@ModelAttribute RoneindRequests.SteelPlatePage query) {
        query.setSize(100_000L);
        StringBuilder csv = new StringBuilder("\uFEFF钢板编号,钢板名称,规格,材质,仓库,仓库编码,吨数,数量,厚度,宽度,长度\r\n");
        dataService.pageSteelPlates(query).items().forEach(item -> csv.append(RoneindBasePartController.row(
                item.getPrdRef(), item.getPrdName(), item.getSpecification(), item.getMatRef(), item.getStockName(),
                item.getStockNumber(), item.getTons(), item.getQuantity(), item.getThickness(), item.getWidth(), item.getLength())));
        return RoneindBasePartController.csv("roneind-steel-plates.csv", csv);
    }
}
