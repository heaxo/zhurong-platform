package com.zhurong.platform.custom.sap.controller;

import com.zhurong.platform.base.api.ApiResponse;
import com.zhurong.platform.custom.sap.convert.AvaInventoryQtyConvert;
import com.zhurong.platform.custom.sap.dto.AvaInventoryQtyDTO;
import com.zhurong.platform.custom.sap.entity.AvaInventoryQty;
import com.zhurong.platform.custom.sap.service.IAvaInventoryQtyService;
import com.zhurong.platform.custom.sap.vo.AvaInventoryQtyVO;
import com.zhurong.platform.custom.web.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sap/ava/inventoyrQty")
public class AvaInventoryQtyController extends BaseController {

    private final AvaInventoryQtyConvert avaInventoryQtyConvert;
    private final IAvaInventoryQtyService avaInventoryQtyService;

    @GetMapping("/ping")
    public String ping() {
        return "pong-from-custom";
    }

    @GetMapping("list")
    public ApiResponse<List<AvaInventoryQtyVO>> list(){
        List<AvaInventoryQty> list = avaInventoryQtyService.list();
        List<AvaInventoryQtyVO> vo = avaInventoryQtyConvert.toVO(list);
        return ApiResponse.success(vo);
    }

    @PostMapping("importInventory")
    public ApiResponse<Boolean> importInventory(@RequestBody AvaInventoryQtyDTO dto){
        try{
            boolean succeed = avaInventoryQtyService.importInventory(dto);
            return ApiResponse.success(succeed);
        }catch (Exception e){
            return ApiResponse.fail(e.getMessage());
        }
    }
}
