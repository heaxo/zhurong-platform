package com.zhurong.platform.custom.controller;

import com.zhurong.platform.base.api.ApiResponse;
import com.zhurong.platform.custom.dto.ZhurongScjinggongOrderDTO;
import com.zhurong.platform.custom.service.IZhurongScjinggongOrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 控制器实现
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/disNestNest00000100")
public class DisNestNest00000100Controller extends com.zhurong.platform.custom.web.BaseController {

    private final IZhurongScjinggongOrderService orderService;

    @PostMapping("/feedback")
    public ApiResponse<Boolean> feedback(@Valid @RequestBody ZhurongScjinggongOrderDTO request) {
        try {
            orderService.feedback(request);
            return ApiResponse.success(true);
        } catch (Exception e) {
            return ApiResponse.fail(e.getMessage());
        }
    }

    @PostMapping("/withdraw")
    public ApiResponse<Boolean> withdraw(@Valid @RequestBody ZhurongScjinggongOrderDTO request) {
        try {
            orderService.withdraw(request);
            return ApiResponse.success(true);
        } catch (Exception e) {
            return ApiResponse.fail(e.getMessage());
        }
    }
}
