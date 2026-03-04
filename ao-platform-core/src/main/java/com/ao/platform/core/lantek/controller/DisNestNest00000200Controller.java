package com.ao.platform.core.lantek.controller;

import com.ao.platform.base.api.ApiResponse;
import com.ao.platform.base.api.PageResponse;
import com.ao.platform.base.model.PageFactory;
import com.ao.platform.core.lantek.api.IDisNestNest00000200Api;
import com.ao.platform.core.lantek.convert.DisNestNest00000200Convert;
import com.ao.platform.core.lantek.dto.DisNestNest00000200DTO;
import com.ao.platform.core.lantek.dto.DisNestNest00000200PageQuery;
import com.ao.platform.core.lantek.entity.DisNestNest00000200;
import com.ao.platform.core.lantek.service.IDisNestNest00000200Service;
import com.ao.platform.core.lantek.vo.DisNestNest00000200VO;
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
public class DisNestNest00000200Controller extends BaseController implements IDisNestNest00000200Api {

    private final DisNestNest00000200Convert convert;
    private final IDisNestNest00000200Service service;

    @Override
    public ApiResponse
            <PageResponse
                    <DisNestNest00000200VO>> page(DisNestNest00000200PageQuery pageQuery) {

        LambdaQueryWrapper<DisNestNest00000200> wrapper =
                Wrappers.lambdaQuery(convert.toEntity(pageQuery));

        Page<DisNestNest00000200> page = service.page(
                PageFactory.build(pageQuery),
                wrapper
        );

        List
                <DisNestNest00000200VO> voList = page.getRecords()
                .stream()
                .map(convert::toVO)
                .toList();

        PageResponse
                <DisNestNest00000200VO> response = new PageResponse<>(
                voList,
                page.getTotal(),
                page.getCurrent(),
                page.getSize()
        );

        return ApiResponse.success(response);
    }

    @Override
    public ApiResponse
            <DisNestNest00000200VO> getById(Long id) {
        return ApiResponse.success(service.getVOById(id));
    }

    @Override
    public ApiResponse
            <Long> save(@Valid DisNestNest00000200DTO dto) {
        Long id = service.saveFromDTO(dto);
        return ApiResponse.success(id);
    }

    @Override
    public ApiResponse
            <Boolean> update(Long id, @Valid DisNestNest00000200DTO dto) {
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
