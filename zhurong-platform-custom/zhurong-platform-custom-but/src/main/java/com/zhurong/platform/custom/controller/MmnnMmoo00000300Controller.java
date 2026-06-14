package com.zhurong.platform.custom.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhurong.platform.base.api.ApiResponse;
import com.zhurong.platform.base.api.PageResponse;
import com.zhurong.platform.base.constant.NestConstant;
import com.zhurong.platform.base.model.PageFactory;
import com.zhurong.platform.core.lantek.dto.MmnnMmoo00000300PageQuery;
import com.zhurong.platform.core.lantek.vo.MmnnMmoo00000300VO;
import com.zhurong.platform.custom.convert.MmnnMmoo00000300Convert;
import com.zhurong.platform.custom.entity.MmnnMmoo00000300;
import com.zhurong.platform.custom.mapper.ZhurongButNestingPartsSplitRecordsMapper;
import com.zhurong.platform.custom.service.IMmnnMmoo00000300Service;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 控制器实现
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/mmnnMmoo00000300")
public class MmnnMmoo00000300Controller extends com.zhurong.platform.custom.web.BaseController {

    private final MmnnMmoo00000300Convert convert;
    private final IMmnnMmoo00000300Service service;
    private final ZhurongButNestingPartsSplitRecordsMapper zhurongButNestingPartsSplitRecordsMapper;

    @GetMapping
    public ApiResponse
            <PageResponse
                    <MmnnMmoo00000300VO>> page(MmnnMmoo00000300PageQuery pageQuery, Boolean detachableOrder) {

        /*LambdaQueryWrapper<MmnnMmoo00000300> wrapper =
                Wrappers.lambdaQuery(convert.toEntity(pageQuery));

        wrapper.orderByDesc(MmnnMmoo00000300::getRecID);

        if (CollectionUtils.isNotEmpty(pageQuery.getOrdRefs())) {
            wrapper.in(MmnnMmoo00000300::getOrdRef, pageQuery.getOrdRefs());
        }
        if (CollectionUtils.isNotEmpty(pageQuery.getMnoRefs())) {
            wrapper.in(MmnnMmoo00000300::getMnORef, pageQuery.getMnoRefs());
        }
        if (pageQuery.getQueryRelease() != null && pageQuery.getQueryRelease()) {
            wrapper.eq(MmnnMmoo00000300::getMState, NestConstant.MState.NOT_STARTED);
        }

        if (detachableOrder != null && detachableOrder){
            wrapper.gt(MmnnMmoo00000300::getMinQuan,0)
                    .apply("RQ > MinQuan");
        }

        Page<MmnnMmoo00000300> page = service.page(
                PageFactory.build(pageQuery),
                wrapper
        );*/
        Page<MmnnMmoo00000300> build = PageFactory.build(pageQuery);
        build.setCountId(
                "com.zhurong.platform.custom.mapper.ZhurongButNestingPartsSplitRecordsMapper.selectCustomPageCount"
        );
        Page<MmnnMmoo00000300> page = zhurongButNestingPartsSplitRecordsMapper.selectCustomPage(build,
                pageQuery, detachableOrder, NestConstant.MState.NOT_STARTED);

        List
                <MmnnMmoo00000300VO> voList = page.getRecords()
                .stream()
                .map(convert::toVO)
                .toList();

        PageResponse
                <MmnnMmoo00000300VO> response = new PageResponse<>(
                voList,
                page.getTotal(),
                page.getCurrent(),
                page.getSize()
        );

        return ApiResponse.success(response);
    }


}
