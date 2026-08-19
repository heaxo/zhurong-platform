package com.zhurong.platform.custom.controller;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zhurong.platform.base.api.ApiResponse;
import com.zhurong.platform.base.constant.NestConstant;
import com.zhurong.platform.core.lantek.dto.DisNestNest00000100DTO;
import com.zhurong.platform.custom.entity.DisNestNest00000100;
import com.zhurong.platform.custom.service.IDisNestNest00000100Service;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 控制器实现
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/disNestNest00000100")
public class DisNestNest00000100Controller extends com.zhurong.platform.custom.web.BaseController {

    private final IDisNestNest00000100Service disNestNest00000100Service;

    @PutMapping("batch_locking")
    public ApiResponse<Boolean> batchLocking(@RequestBody DisNestNest00000100DTO dto){
        if (CollectionUtils.isEmpty(dto.getRecIds())){
            return ApiResponse.fail("套料ID不能为空");
        }
        boolean update = disNestNest00000100Service.update(Wrappers.lambdaUpdate(DisNestNest00000100.class)
                .set(DisNestNest00000100::getMState, NestConstant.MState.IN_WORKSHOP)
                .in(DisNestNest00000100::getRecID, dto.getRecIds()));
        return ApiResponse.success(update);
    }

}
