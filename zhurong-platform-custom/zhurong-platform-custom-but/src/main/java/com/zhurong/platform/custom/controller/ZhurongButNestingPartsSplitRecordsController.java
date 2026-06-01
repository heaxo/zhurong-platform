package com.zhurong.platform.custom.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhurong.platform.base.api.ApiResponse;
import com.zhurong.platform.base.api.PageResponse;
import com.zhurong.platform.base.model.PageFactory;
import com.zhurong.platform.custom.convert.ZhurongButNestingPartsSplitRecordsConvert;
import com.zhurong.platform.custom.dto.ZhurongButNestingPartsSplitRecordsDTO;
import com.zhurong.platform.custom.dto.ZhurongButNestingPartsSplitRecordsPageQuery;
import com.zhurong.platform.custom.entity.ZhurongButNestingPartsSplitRecords;
import com.zhurong.platform.custom.service.IZhurongButNestingPartsSplitRecordsService;
import com.zhurong.platform.custom.vo.ZhurongButNestingPartsSplitRecordsVO;
import com.zhurong.platform.custom.web.BaseController;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 控制器实现
 */
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/zhurongButNestingPartsSplitRecords")
public class ZhurongButNestingPartsSplitRecordsController extends BaseController {

    private final ZhurongButNestingPartsSplitRecordsConvert convert;
    private final IZhurongButNestingPartsSplitRecordsService service;

    @GetMapping("page")
    public ApiResponse
            <PageResponse
                    <ZhurongButNestingPartsSplitRecordsVO>> page(ZhurongButNestingPartsSplitRecordsPageQuery pageQuery) {

        LambdaQueryWrapper<ZhurongButNestingPartsSplitRecords> wrapper =
                Wrappers.lambdaQuery(convert.toEntity(pageQuery));

        wrapper.orderByAsc(ZhurongButNestingPartsSplitRecords::getCreatedAt);

        Page<ZhurongButNestingPartsSplitRecords> page = service.page(
                PageFactory.build(pageQuery),
                wrapper
        );

        List
                <ZhurongButNestingPartsSplitRecordsVO> voList = page.getRecords()
                .stream()
                .map(convert::toVO)
                .toList();

        PageResponse
                <ZhurongButNestingPartsSplitRecordsVO> response = new PageResponse<>(
                voList,
                page.getTotal(),
                page.getCurrent(),
                page.getSize()
        );

        return ApiResponse.success(response);
    }

    @PostMapping("splitRecordsOverwrite")
    public ApiResponse<Boolean> splitRecordsOverwrite(@RequestBody @Valid ZhurongButNestingPartsSplitRecordsDTO dto) {

        if (CollectionUtils.isEmpty(dto.getRecords())){
            try {
                boolean result = service.removeSplitRecords(dto.getNstRef());
                return ApiResponse.success(result);
            } catch (Exception e) {
                return ApiResponse.fail(e.getMessage());
            }
        }

        try {
            boolean result = service.splitRecordsOverwrite(dto.getRecords());
            return ApiResponse.success(result);
        } catch (Exception e) {
            return ApiResponse.fail(e.getMessage());
        }
    }
}
