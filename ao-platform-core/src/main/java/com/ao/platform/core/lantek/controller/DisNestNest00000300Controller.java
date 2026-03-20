package com.ao.platform.core.lantek.controller;

import com.ao.platform.base.api.ApiResponse;
import com.ao.platform.base.api.PageResponse;
import com.ao.platform.base.model.PageFactory;
import com.ao.platform.core.lantek.api.IDisNestNest00000300Api;
import com.ao.platform.core.lantek.convert.DisNestNest00000300Convert;
import com.ao.platform.core.lantek.dto.DisNestNest00000300DTO;
import com.ao.platform.core.lantek.dto.DisNestNest00000300PageQuery;
import com.ao.platform.core.lantek.entity.DisNestNest00000300;
import com.ao.platform.core.lantek.service.IDisNestNest00000300Service;
import com.ao.platform.core.lantek.vo.DisNestNest00000300VO;
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
public class DisNestNest00000300Controller extends BaseController implements IDisNestNest00000300Api {

    private final DisNestNest00000300Convert convert;
    private final IDisNestNest00000300Service service;

    @Override
    public ApiResponse
            <PageResponse
                    <DisNestNest00000300VO>> page(DisNestNest00000300PageQuery pageQuery) {

        LambdaQueryWrapper<DisNestNest00000300> wrapper =
                Wrappers.lambdaQuery(convert.toEntity(pageQuery));

        wrapper.orderByAsc(DisNestNest00000300::getRecID);

        Page<DisNestNest00000300> page = service.page(
                PageFactory.build(pageQuery),
                wrapper
        );

        List
                <DisNestNest00000300VO> voList = page.getRecords()
                .stream()
                .map(convert::toVO)
                .toList();

        PageResponse
                <DisNestNest00000300VO> response = new PageResponse<>(
                voList,
                page.getTotal(),
                page.getCurrent(),
                page.getSize()
        );

        return ApiResponse.success(response);
    }

    @Override
    public ApiResponse
            <DisNestNest00000300VO> getById(Long id) {
        return ApiResponse.success(service.getVOById(id));
    }

    @Override
    public ApiResponse
            <Long> save(@Valid DisNestNest00000300DTO dto) {
        Long id = service.saveFromDTO(dto);
        return ApiResponse.success(id);
    }

    @Override
    public ApiResponse
            <Boolean> update(Long id, @Valid DisNestNest00000300DTO dto) {
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
