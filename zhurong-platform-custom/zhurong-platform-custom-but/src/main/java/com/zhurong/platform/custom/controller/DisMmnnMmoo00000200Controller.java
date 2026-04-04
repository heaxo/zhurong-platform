package com.zhurong.platform.custom.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhurong.platform.base.api.ApiResponse;
import com.zhurong.platform.base.api.PageResponse;
import com.zhurong.platform.base.model.PageFactory;
import com.zhurong.platform.core.lantek.dto.DisMmnnMmoo00000200PageQuery;
import com.zhurong.platform.core.lantek.vo.DisMmnnMmoo00000200VO;
import com.zhurong.platform.custom.convert.DisMmnnMmoo00000200Convert;
import com.zhurong.platform.custom.entity.DisMmnnMmoo00000200;
import com.zhurong.platform.custom.service.IDisMmnnMmoo00000200Service;
import com.zhurong.platform.custom.web.BaseController;
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
@RequestMapping("/job")
public class DisMmnnMmoo00000200Controller extends BaseController {

    private final DisMmnnMmoo00000200Convert convert;
    private final IDisMmnnMmoo00000200Service service;

    @GetMapping
    public ApiResponse
            <PageResponse
                    <DisMmnnMmoo00000200VO>> page(DisMmnnMmoo00000200PageQuery pageQuery) {

        LambdaQueryWrapper<DisMmnnMmoo00000200> wrapper =
                Wrappers.lambdaQuery(convert.toEntity(pageQuery));

        wrapper.orderByAsc(DisMmnnMmoo00000200::getRecID);

        Page<DisMmnnMmoo00000200> page = service.page(
                PageFactory.build(pageQuery),
                wrapper
        );

        List
                <DisMmnnMmoo00000200VO> voList = page.getRecords()
                .stream()
                .map(convert::toVO)
                .toList();

        PageResponse
                <DisMmnnMmoo00000200VO> response = new PageResponse<>(
                voList,
                page.getTotal(),
                page.getCurrent(),
                page.getSize()
        );

        return ApiResponse.success(response);
    }

}
