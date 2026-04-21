package com.zhurong.platform.custom.controller;

import com.zhurong.platform.base.api.ApiResponse;
import com.zhurong.platform.custom.erp.service.IViPmPrjreportsLantekService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 控制器实现
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/disNestNest00000100")
public class DisNestNest00000100Controller extends com.zhurong.platform.custom.web.BaseController {

    private final IViPmPrjreportsLantekService viPmPrjreportsLantekService;

    @PostMapping("sync_reported_status")
    public ApiResponse<Boolean> syncReportedStatus(){
        try{
            boolean succeed = viPmPrjreportsLantekService.syncReportNestingProgram();
            return ApiResponse.success(succeed);
        } catch (Exception e) {
            return ApiResponse.fail(e.getMessage());
        }
    }

}
