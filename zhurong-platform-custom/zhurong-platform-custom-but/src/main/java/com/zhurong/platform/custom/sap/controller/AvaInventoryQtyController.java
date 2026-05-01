package com.zhurong.platform.custom.sap.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zhurong.platform.base.api.ApiResponse;
import com.zhurong.platform.custom.sap.convert.AvaInventoryQtyConvert;
import com.zhurong.platform.custom.sap.dto.AvaInventoryQtyDTO;
import com.zhurong.platform.custom.sap.entity.AvaInventoryQty;
import com.zhurong.platform.custom.sap.service.IAvaInventoryQtyService;
import com.zhurong.platform.custom.sap.vo.AvaInventoryQtyVO;
import com.zhurong.platform.custom.sbut.entity.SbutAvaInventoryQty;
import com.zhurong.platform.custom.sbut.service.ISbutAvaInventoryQtyService;
import com.zhurong.platform.custom.web.BaseController;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sap/ava/inventoyrQty")
@Hidden
public class AvaInventoryQtyController extends BaseController {

    private final AvaInventoryQtyConvert avaInventoryQtyConvert;
    private final IAvaInventoryQtyService avaInventoryQtyService;
    private final ISbutAvaInventoryQtyService sbutAvaInventoryQtyService;
    @Resource(name = "dbQueryExecutor")
    private Executor dbQueryExecutor;

    @GetMapping("/ping")
    public String ping() {
        return "pong-from-custom";
    }

    @GetMapping("list")
    public ApiResponse<List<AvaInventoryQtyVO>> list(AvaInventoryQtyDTO dto) {
        AvaInventoryQty avaInventoryQty = avaInventoryQtyConvert.toEntity(dto);
        SbutAvaInventoryQty sbutAvaInventoryQty = avaInventoryQtyConvert.toEntity(avaInventoryQty);

        try {
            CompletableFuture<List<AvaInventoryQty>> list1Future = CompletableFuture.supplyAsync(
                    () -> avaInventoryQtyService.list(Wrappers.lambdaQuery(avaInventoryQty)),
                    dbQueryExecutor
            );

            CompletableFuture<List<SbutAvaInventoryQty>> list2Future = CompletableFuture.supplyAsync(
                    () -> sbutAvaInventoryQtyService.list(Wrappers.lambdaQuery(sbutAvaInventoryQty)),
                    dbQueryExecutor
            );

            CompletableFuture.allOf(list1Future, list2Future).join();

            List<AvaInventoryQty> list1 = list1Future.join();
            List<SbutAvaInventoryQty> list2 = list2Future.join();

            List<AvaInventoryQtyVO> vo = new ArrayList<>();
            vo.addAll(avaInventoryQtyConvert.toVO(list1));
            vo.addAll(avaInventoryQtyConvert.toVOFromSbut(list2));

            return ApiResponse.success(vo);
        } catch (Exception e) {
            throw new RuntimeException("查询库存数量失败", e);
        }
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
