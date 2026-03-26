package com.zhurong.platform.core.lantek.controller;

import com.zhurong.platform.base.api.ApiResponse;
import com.zhurong.platform.base.api.PageResponse;
import com.zhurong.platform.base.model.PageFactory;
import com.zhurong.platform.core.lantek.api.ISystOwnd00000100Api;
import com.zhurong.platform.core.lantek.convert.SystOwnd00000100Convert;
import com.zhurong.platform.core.lantek.dto.SystOwnd00000100DTO;
import com.zhurong.platform.core.lantek.dto.SystOwnd00000100PageQuery;
import com.zhurong.platform.core.lantek.entity.SystOwnd00000100;
import com.zhurong.platform.core.lantek.service.ISystOwnd00000100Service;
import com.zhurong.platform.core.lantek.vo.SystOwnd00000100VO;
import com.zhurong.platform.core.web.BaseController;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 控制器实现
 */
@RestController
@RequiredArgsConstructor
public class SystOwnd00000100Controller extends BaseController implements ISystOwnd00000100Api {

    private final SystOwnd00000100Convert convert;
    private final ISystOwnd00000100Service service;

    @Override
    public ApiResponse
            <PageResponse
                    <SystOwnd00000100VO>> page(SystOwnd00000100PageQuery pageQuery) {

        LambdaQueryWrapper<SystOwnd00000100> wrapper =
                Wrappers.lambdaQuery(convert.toEntity(pageQuery));

        wrapper.orderByAsc(SystOwnd00000100::getRecID);

        Page<SystOwnd00000100> page = service.page(
                PageFactory.build(pageQuery),
                wrapper
        );

        List
                <SystOwnd00000100VO> voList = page.getRecords()
                .stream()
                .map(convert::toVO)
                .toList();

        PageResponse
                <SystOwnd00000100VO> response = new PageResponse<>(
                voList,
                page.getTotal(),
                page.getCurrent(),
                page.getSize()
        );

        return ApiResponse.success(response);
    }

    @Override
    public ApiResponse
            <SystOwnd00000100VO> getById(Long id) {
        return ApiResponse.success(service.getVOById(id));
    }

    @Override
    public ApiResponse
            <Long> save(@Valid SystOwnd00000100DTO dto) {
        Long id = service.saveFromDTO(dto);
        return ApiResponse.success(id);
    }

    @Override
    public ApiResponse
            <Boolean> update(Long id, @Valid SystOwnd00000100DTO dto) {
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
