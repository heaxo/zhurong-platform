package com.zhurong.platform.custom.controller;

import com.zhurong.platform.base.api.ApiResponse;
import com.zhurong.platform.core.lantek.dto.WwhhIivv00000100DTO;
import com.zhurong.platform.core.lantek.dto.WwhhWwhh00000100DTO;
import com.zhurong.platform.custom.feign.WwhhIivv00000100FeignClient;
import com.zhurong.platform.custom.feign.WwhhWwhh00000100FeignClient;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/inventory")
@Tag(name = "库存管理", description = "套料软件板材仓库和位置管理接口")
public class InventoryController {
    private final WwhhWwhh00000100FeignClient wwhhWwhh00000100FeignClient;
    private final WwhhIivv00000100FeignClient wwhhIivv00000100FeignClient;

    @Operation(
            summary = "更新板材仓库和位置",
            description = "更新套料软件中板材对应的仓库和默认位置。"
                    + "接口会先创建或更新仓库与位置关系，再更新指定板材的默认仓库和位置。"
    )
    @GetMapping("/updateWarehouseAndLocation")
    public ApiResponse<Boolean> updateWarehouseAndLocation(
            @Parameter(description = "仓库编码", required = true, example = "WH-01")
            @RequestParam String whsName,
            @Parameter(description = "位置编码", required = true, example = "LOC-01")
            @RequestParam String locName,
            @Parameter(description = "钢板编号", required = true, example = "PRD-10001")
            @RequestParam String prdRef) {
        WwhhWwhh00000100DTO wwhhWwhh00000100DTO = new WwhhWwhh00000100DTO();
        wwhhWwhh00000100DTO.setWrhRef(whsName);
        wwhhWwhh00000100DTO.setLocRef(locName);
        ApiResponse<Boolean> apiResponse1 = wwhhWwhh00000100FeignClient.createWarehouseAndLocation(wwhhWwhh00000100DTO);
        if (apiResponse1.data() == null || !apiResponse1.data()) {
            return ApiResponse.fail(apiResponse1.message());
        }
        WwhhIivv00000100DTO wwhhIivv00000100DTO = new WwhhIivv00000100DTO();
        wwhhIivv00000100DTO.setWrhRef(whsName);
        wwhhIivv00000100DTO.setLocDefault(locName);
        wwhhIivv00000100DTO.setPrdRef(prdRef);
        ApiResponse<Boolean> apiResponse2 = wwhhIivv00000100FeignClient.sheetMetalBindingUpdate(wwhhIivv00000100DTO);

        if (apiResponse2.data() == null || !apiResponse2.data()) {
            return ApiResponse.fail(apiResponse2.message());
        }
        return ApiResponse.success(true);
    }
}
