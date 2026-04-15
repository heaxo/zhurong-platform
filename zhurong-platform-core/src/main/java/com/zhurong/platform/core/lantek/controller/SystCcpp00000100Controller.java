package com.zhurong.platform.core.lantek.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhurong.platform.base.api.ApiResponse;
import com.zhurong.platform.base.api.PageResponse;
import com.zhurong.platform.base.model.PageFactory;
import com.zhurong.platform.core.lantek.api.ISystCcpp00000100Api;
import com.zhurong.platform.core.lantek.convert.SystCcpp00000100Convert;
import com.zhurong.platform.core.lantek.dto.SystCcpp00000100DTO;
import com.zhurong.platform.core.lantek.dto.SystCcpp00000100PageQuery;
import com.zhurong.platform.core.lantek.entity.SystCcpp00000100;
import com.zhurong.platform.core.lantek.service.ISystCcpp00000100Service;
import com.zhurong.platform.core.lantek.vo.SystCcpp00000100VO;
import com.zhurong.platform.core.web.BaseController;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 控制器实现
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/systCcpp00000100")
public class SystCcpp00000100Controller extends BaseController implements ISystCcpp00000100Api {

    private final SystCcpp00000100Convert convert;
    private final ISystCcpp00000100Service service;

    @Override
    public ApiResponse
            <PageResponse
                    <SystCcpp00000100VO>> page(SystCcpp00000100PageQuery pageQuery) {

        LambdaQueryWrapper<SystCcpp00000100> wrapper =
                Wrappers.lambdaQuery(convert.toEntity(pageQuery));

        wrapper.orderByAsc(SystCcpp00000100::getRecID);

        Page<SystCcpp00000100> page = service.page(
                PageFactory.build(pageQuery),
                wrapper
        );

        List
                <SystCcpp00000100VO> voList = page.getRecords()
                .stream()
                .map(convert::toVO)
                .toList();

        PageResponse
                <SystCcpp00000100VO> response = new PageResponse<>(
                voList,
                page.getTotal(),
                page.getCurrent(),
                page.getSize()
        );

        return ApiResponse.success(response);
    }

    @Override
    public ApiResponse
            <SystCcpp00000100VO> getById(Long id) {
        return ApiResponse.success(service.getVOById(id));
    }

    @Override
    public ApiResponse
            <Long> save(@Valid SystCcpp00000100DTO dto) {
        Long id = service.saveFromDTO(dto);
        return ApiResponse.success(id);
    }

    @Override
    public ApiResponse
            <Boolean> update(Long id, @Valid SystCcpp00000100DTO dto) {
        boolean update = service.updateFromDTO(id, dto);
        return ApiResponse.success(update);
    }

    @Override
    public ApiResponse
            <Boolean> remove(Long id) {
        boolean remove = service.removeById(id);
        return ApiResponse.success(remove);
    }

    @Override
    public ApiResponse
            <Boolean> batchRemove(List
                                          <Long> ids) {
        boolean remove = service.removeByIds(ids);
        return ApiResponse.success(remove);
    }
}
