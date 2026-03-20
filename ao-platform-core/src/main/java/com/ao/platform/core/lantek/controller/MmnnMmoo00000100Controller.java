package com.ao.platform.core.lantek.controller;

import com.ao.platform.base.api.ApiResponse;
import com.ao.platform.base.api.PageResponse;
import com.ao.platform.base.model.PageFactory;
import com.ao.platform.core.lantek.api.IMmnnMmoo00000100Api;
import com.ao.platform.core.lantek.convert.MmnnMmoo00000100Convert;
import com.ao.platform.core.lantek.dto.MmnnMmoo00000100DTO;
import com.ao.platform.core.lantek.dto.MmnnMmoo00000100PageQuery;
import com.ao.platform.core.lantek.entity.MmnnMmoo00000100;
import com.ao.platform.core.lantek.service.IMmnnMmoo00000100Service;
import com.ao.platform.core.lantek.vo.MmnnMmoo00000100VO;
import com.ao.platform.core.web.BaseController;
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
public class MmnnMmoo00000100Controller extends BaseController implements IMmnnMmoo00000100Api {

    private final MmnnMmoo00000100Convert convert;
    private final IMmnnMmoo00000100Service service;

    @Override
    public ApiResponse
            <PageResponse
                    <MmnnMmoo00000100VO>> page(MmnnMmoo00000100PageQuery pageQuery) {

        LambdaQueryWrapper<MmnnMmoo00000100> wrapper =
                Wrappers.lambdaQuery(convert.toEntity(pageQuery));

        wrapper.orderByAsc(MmnnMmoo00000100::getRecID);

        Page<MmnnMmoo00000100> page = service.page(
                PageFactory.build(pageQuery),
                wrapper
        );

        List
                <MmnnMmoo00000100VO> voList = page.getRecords()
                .stream()
                .map(convert::toVO)
                .toList();

        PageResponse
                <MmnnMmoo00000100VO> response = new PageResponse<>(
                voList,
                page.getTotal(),
                page.getCurrent(),
                page.getSize()
        );

        return ApiResponse.success(response);
    }

    @Override
    public ApiResponse
            <MmnnMmoo00000100VO> getById(Long id) {
        return ApiResponse.success(service.getVOById(id));
    }

    @Override
    public ApiResponse
            <Long> save(@Valid MmnnMmoo00000100DTO dto) {
        Long id = service.saveFromDTO(dto);
        return ApiResponse.success(id);
    }

    @Override
    public ApiResponse
            <Boolean> update(Long id, @Valid MmnnMmoo00000100DTO dto) {
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
