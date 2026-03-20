package com.ao.platform.core.lantek.controller;

import com.ao.platform.base.api.ApiResponse;
import com.ao.platform.base.api.PageResponse;
import com.ao.platform.base.model.PageFactory;
import com.ao.platform.core.lantek.api.IDisNestNest00000400Api;
import com.ao.platform.core.lantek.convert.DisNestNest00000400Convert;
import com.ao.platform.core.lantek.dto.DisNestNest00000400DTO;
import com.ao.platform.core.lantek.dto.DisNestNest00000400PageQuery;
import com.ao.platform.core.lantek.entity.DisNestNest00000400;
import com.ao.platform.core.lantek.service.IDisNestNest00000400Service;
import com.ao.platform.core.lantek.vo.DisNestNest00000400VO;
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
public class DisNestNest00000400Controller extends BaseController implements IDisNestNest00000400Api {

    private final DisNestNest00000400Convert convert;
    private final IDisNestNest00000400Service service;

    @Override
    public ApiResponse
            <PageResponse
                    <DisNestNest00000400VO>> page(DisNestNest00000400PageQuery pageQuery) {

        LambdaQueryWrapper<DisNestNest00000400> wrapper =
                Wrappers.lambdaQuery(convert.toEntity(pageQuery));

        wrapper.orderByAsc(DisNestNest00000400::getRecID);

        Page<DisNestNest00000400> page = service.page(
                PageFactory.build(pageQuery),
                wrapper
        );

        List
                <DisNestNest00000400VO> voList = page.getRecords()
                .stream()
                .map(convert::toVO)
                .toList();

        PageResponse
                <DisNestNest00000400VO> response = new PageResponse<>(
                voList,
                page.getTotal(),
                page.getCurrent(),
                page.getSize()
        );

        return ApiResponse.success(response);
    }

    @Override
    public ApiResponse
            <DisNestNest00000400VO> getById(Long id) {
        return ApiResponse.success(service.getVOById(id));
    }

    @Override
    public ApiResponse
            <Long> save(@Valid DisNestNest00000400DTO dto) {
        Long id = service.saveFromDTO(dto);
        return ApiResponse.success(id);
    }

    @Override
    public ApiResponse
            <Boolean> update(Long id, @Valid DisNestNest00000400DTO dto) {
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
