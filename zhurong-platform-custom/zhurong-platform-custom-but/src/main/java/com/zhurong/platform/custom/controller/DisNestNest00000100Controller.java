package com.zhurong.platform.custom.controller;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zhurong.platform.base.api.ApiResponse;
import com.zhurong.platform.base.constant.NestConstant;
import com.zhurong.platform.core.lantek.dto.DisNestNest00000100DTO;
import com.zhurong.platform.custom.entity.DisNestNest00000100;
import com.zhurong.platform.custom.erp.service.IViPmPrjreportsLantekService;
import com.zhurong.platform.custom.service.IDisNestNest00000100Service;
import jakarta.servlet.ServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 控制器实现
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/disNestNest00000100")
public class DisNestNest00000100Controller extends com.zhurong.platform.custom.web.BaseController {

    private final IViPmPrjreportsLantekService viPmPrjreportsLantekService;
    private final IDisNestNest00000100Service disNestNest00000100Service;

    @PostMapping("sync_reported_status")
    public ApiResponse<Boolean> syncReportedStatus(){
        try{
            boolean succeed = viPmPrjreportsLantekService.syncReportNestingProgram();
            return ApiResponse.success(succeed);
        } catch (Exception e) {
            return ApiResponse.fail(e.getMessage());
        }
    }
    @PutMapping("batch_locking")
    public ApiResponse<Boolean> batchLocking(@RequestBody DisNestNest00000100DTO dto, ServletRequest servletRequest){
        if (CollectionUtils.isEmpty(dto.getRecIds())){
            return ApiResponse.fail("套料ID不能为空");
        }
        boolean update = disNestNest00000100Service.update(Wrappers.lambdaUpdate(DisNestNest00000100.class)
                .set(DisNestNest00000100::getMState, NestConstant.MState.IN_WORKSHOP)
                .in(DisNestNest00000100::getRecID, dto.getRecIds()));
        return ApiResponse.success(update);
    }

}
