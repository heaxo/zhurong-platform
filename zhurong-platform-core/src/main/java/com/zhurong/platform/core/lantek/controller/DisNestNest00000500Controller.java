package com.zhurong.platform.core.lantek.controller;

import com.zhurong.platform.base.api.ApiResponse;
import com.zhurong.platform.base.api.PageResponse;
import com.zhurong.platform.base.model.PageFactory;
import com.zhurong.platform.core.lantek.api.IDisNestNest00000500Api;
import com.zhurong.platform.core.lantek.convert.DisNestNest00000500Convert;
import com.zhurong.platform.core.lantek.dto.DisNestNest00000500DTO;
import com.zhurong.platform.core.lantek.dto.DisNestNest00000500PageQuery;
import com.zhurong.platform.core.lantek.entity.DisNestNest00000500;
import com.zhurong.platform.core.lantek.service.IDisNestNest00000500Service;
import com.zhurong.platform.core.lantek.vo.DisNestNest00000500VO;
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
public class DisNestNest00000500Controller extends BaseController implements IDisNestNest00000500Api {

    private final DisNestNest00000500Convert convert;
    private final IDisNestNest00000500Service service;

    @Override
    public ApiResponse
            <PageResponse
                    <DisNestNest00000500VO>> page(DisNestNest00000500PageQuery pageQuery) {

        LambdaQueryWrapper<DisNestNest00000500> wrapper =
                Wrappers.lambdaQuery(convert.toEntity(pageQuery));

        wrapper.orderByAsc(DisNestNest00000500::getRecID);

        Page<DisNestNest00000500> page = service.page(
                PageFactory.build(pageQuery),
                wrapper
        );

        List
                <DisNestNest00000500VO> voList = page.getRecords()
                .stream()
                .map(convert::toVO)
                .toList();

        PageResponse
                <DisNestNest00000500VO> response = new PageResponse<>(
                voList,
                page.getTotal(),
                page.getCurrent(),
                page.getSize()
        );

        return ApiResponse.success(response);
    }

    @Override
    public ApiResponse
            <DisNestNest00000500VO> getById(Long id) {
        return ApiResponse.success(service.getVOById(id));
    }

    @Override
    public ApiResponse
            <Long> save(@Valid DisNestNest00000500DTO dto) {
        Long id = service.saveFromDTO(dto);
        return ApiResponse.success(id);
    }

    @Override
    public ApiResponse
            <Boolean> update(Long id, @Valid DisNestNest00000500DTO dto) {
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
