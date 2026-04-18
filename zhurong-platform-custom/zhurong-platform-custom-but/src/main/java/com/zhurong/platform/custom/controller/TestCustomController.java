package com.zhurong.platform.custom.controller;

import com.zhurong.platform.base.api.ApiResponse;
import com.zhurong.platform.core.lantek.dto.WwhhIivv00000100DTO;
import com.zhurong.platform.core.lantek.dto.WwhhWwhh00000100DTO;
import com.zhurong.platform.custom.feign.WwhhIivv00000100FeignClient;
import com.zhurong.platform.custom.feign.WwhhWwhh00000100FeignClient;
import com.zhurong.platform.custom.service.IPprrPprr00000100Service;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/custom")
@Hidden
public class TestCustomController {
    private final IPprrPprr00000100Service pprrPprr00000100Service;
    private final WwhhWwhh00000100FeignClient wwhhWwhh00000100FeignClient;
    private final WwhhIivv00000100FeignClient wwhhIivv00000100FeignClient;

    @GetMapping("/ping")
    public String ping() {
        return "pong-from-custom";
    }

    @GetMapping("/bind_warehouse")
    public String bindWarehouse(@RequestParam String whsName,@RequestParam String locName,@RequestParam String prdRef) {
        WwhhWwhh00000100DTO wwhhWwhh00000100DTO = new WwhhWwhh00000100DTO();
        wwhhWwhh00000100DTO.setWrhRef(whsName);
        wwhhWwhh00000100DTO.setLocRef(locName);
        ApiResponse<Boolean> apiResponse1 = wwhhWwhh00000100FeignClient.createWarehouseAndLocation(wwhhWwhh00000100DTO);

        if (apiResponse1.data() == null || !apiResponse1.data()){
            return apiResponse1.message();
        }
        //钢板绑定库存
        WwhhIivv00000100DTO wwhhIivv00000100DTO = new WwhhIivv00000100DTO();
        wwhhIivv00000100DTO.setWrhRef(whsName);
        wwhhIivv00000100DTO.setLocDefault(locName);
        wwhhIivv00000100DTO.setPrdRef(prdRef);
        wwhhIivv00000100DTO.setAllocatedQ(66D);
        ApiResponse<Boolean> apiResponse2 = wwhhIivv00000100FeignClient.sheetMetalBinding(wwhhIivv00000100DTO);

        if (apiResponse2.data() == null || !apiResponse2.data()){
            return apiResponse2.message();
        }
        return "TRUE";
    }

    @GetMapping("/update_bind_warehouse_location")
    public String updateBindWarehouseLocation(@RequestParam String whsName,@RequestParam String locName,@RequestParam String prdRef) {
        WwhhWwhh00000100DTO wwhhWwhh00000100DTO = new WwhhWwhh00000100DTO();
        wwhhWwhh00000100DTO.setWrhRef(whsName);
        wwhhWwhh00000100DTO.setLocRef(locName);
        ApiResponse<Boolean> apiResponse1 = wwhhWwhh00000100FeignClient.createWarehouseAndLocation(wwhhWwhh00000100DTO);

        if (apiResponse1.data() == null || !apiResponse1.data()){
            return apiResponse1.message();
        }
        WwhhIivv00000100DTO wwhhIivv00000100DTO = new WwhhIivv00000100DTO();
        wwhhIivv00000100DTO.setWrhRef(whsName);
        wwhhIivv00000100DTO.setLocDefault(locName);
        wwhhIivv00000100DTO.setPrdRef(prdRef);
        ApiResponse<Boolean> apiResponse2 = wwhhIivv00000100FeignClient.sheetMetalBindingUpdate(wwhhIivv00000100DTO);

        if (apiResponse2.data() == null || !apiResponse2.data()){
            return apiResponse2.message();
        }
        return "TRUE";
    }
}
