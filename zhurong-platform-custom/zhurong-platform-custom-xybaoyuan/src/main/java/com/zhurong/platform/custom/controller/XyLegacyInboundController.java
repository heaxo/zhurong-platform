package com.zhurong.platform.custom.controller;

import com.zhurong.platform.base.api.ApiResponse;
import com.zhurong.platform.custom.dto.XyInboundRequests;
import com.zhurong.platform.custom.service.XyInboundService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/** 保留金蝶现有 0111 URL，内部统一进入 Zhurong 客户服务。 */
@RestController
@RequiredArgsConstructor
public class XyLegacyInboundController {
    private final XyInboundService inboundService;

    @PostMapping("/0111/BasePart/Creates")
    public ApiResponse<Boolean> createBaseParts(@Valid @RequestBody XyInboundRequests.BaseParts request) {
        return ApiResponse.success(inboundService.receiveBaseParts(request));
    }

    @PostMapping("/0111/ManufacturingOrder/Creates")
    public ApiResponse<Boolean> createManufacturingOrders(
            @Valid @RequestBody XyInboundRequests.ManufacturingOrders request) {
        return ApiResponse.success(inboundService.receiveManufacturingOrders(request));
    }
}
