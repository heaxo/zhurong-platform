package com.zhurong.platform.custom.controller;

import com.zhurong.platform.base.api.ApiResponse;
import com.zhurong.platform.base.api.PageResponse;
import com.zhurong.platform.custom.dto.XyRequests;
import com.zhurong.platform.custom.service.XyNestFeedbackService;
import com.zhurong.platform.core.lantek.dto.DisNestNest00000100PageQuery;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/xybaoyuan/nests")
public class XyNestController {
    private final XyNestFeedbackService feedbackService;

    @PostMapping("/page")
    public ApiResponse<PageResponse<Map<String, Object>>> page(@RequestBody DisNestNest00000100PageQuery query) {
        return ApiResponse.success(feedbackService.page(query));
    }

    @PostMapping("/feedback")
    public ApiResponse<Void> feedback(@Valid @RequestBody XyRequests.Feedback request) {
        try{
            feedbackService.send(request);
            return ApiResponse.success();
        } catch (Exception e) {
            return ApiResponse.fail(e.getMessage());
        }
    }

    @PostMapping("/feedback/withdraw")
    public ApiResponse<Void> withdraw(@Valid @RequestBody XyRequests.Ids request) {
        try{
            feedbackService.withdraw(request.getIds());
            return ApiResponse.success();
        }catch (Exception e){
            return ApiResponse.fail(e.getMessage());
        }
    }
}
