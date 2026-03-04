package com.ao.platform.core.lantek.controller;

import com.ao.platform.base.api.ApiResponse;
import com.ao.platform.base.api.PageResponse;
import com.ao.platform.base.model.PageFactory;
import com.ao.platform.core.lantek.api.IDisPpttWwcc00000800Api;
import com.ao.platform.core.lantek.convert.DisPpttWwcc00000800Convert;
import com.ao.platform.core.lantek.dto.DisPpttWwcc00000800DTO;
import com.ao.platform.core.lantek.dto.DisPpttWwcc00000800PageQuery;
import com.ao.platform.core.lantek.entity.DisPpttWwcc00000800;
import com.ao.platform.core.lantek.service.IDisPpttWwcc00000800Service;
import com.ao.platform.core.lantek.vo.DisPpttWwcc00000800VO;
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
public class DisPpttWwcc00000800Controller extends BaseController implements IDisPpttWwcc00000800Api {

    private final DisPpttWwcc00000800Convert convert;
    private final IDisPpttWwcc00000800Service service;

    @Override
    public ApiResponse
            <PageResponse
                    <DisPpttWwcc00000800VO>> page(DisPpttWwcc00000800PageQuery pageQuery) {

        LambdaQueryWrapper<DisPpttWwcc00000800> wrapper =
                Wrappers.lambdaQuery(convert.toEntity(pageQuery));

        Page<DisPpttWwcc00000800> page = service.page(
                PageFactory.build(pageQuery),
                wrapper
        );

        List
                <DisPpttWwcc00000800VO> voList = page.getRecords()
                .stream()
                .map(convert::toVO)
                .toList();

        PageResponse
                <DisPpttWwcc00000800VO> response = new PageResponse<>(
                voList,
                page.getTotal(),
                page.getCurrent(),
                page.getSize()
        );

        return ApiResponse.success(response);
    }

    @Override
    public ApiResponse
            <DisPpttWwcc00000800VO> getById(Long id) {
        return ApiResponse.success(service.getVOById(id));
    }

    @Override
    public ApiResponse
            <Long> save(@Valid DisPpttWwcc00000800DTO dto) {
        Long id = service.saveFromDTO(dto);
        return ApiResponse.success(id);
    }

    @Override
    public ApiResponse
            <Boolean> update(Long id, @Valid DisPpttWwcc00000800DTO dto) {
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
