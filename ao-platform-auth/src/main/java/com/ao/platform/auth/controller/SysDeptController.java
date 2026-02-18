package com.ao.platform.auth.controller;

import com.ao.platform.auth.api.ISysDeptApi;
import com.ao.platform.auth.convert.SysDeptConvert;
import com.ao.platform.auth.dto.SysDeptDTO;
import com.ao.platform.auth.dto.SysDeptPageQuery;
import com.ao.platform.auth.entity.SysDept;
import com.ao.platform.auth.service.ISysDeptService;
import com.ao.platform.auth.vo.SysDeptVO;
import com.ao.platform.base.api.ApiResponse;
import com.ao.platform.base.api.PageResponse;
import com.ao.platform.base.model.PageFactory;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.List;

/**
 * 控制器实现
 */
@RestController
@RequiredArgsConstructor
public class SysDeptController implements ISysDeptApi {

    private final SysDeptConvert convert;
    private final ISysDeptService service;

    @Override
    public ApiResponse
            <PageResponse
                    <SysDeptVO>> page(SysDeptPageQuery pageQuery) {

        LambdaQueryWrapper<SysDept> wrapper =
                Wrappers.lambdaQuery(convert.toEntity(pageQuery));

        Page<SysDept> page = service.page(
                PageFactory.build(pageQuery),
                wrapper
        );

        List
                <SysDeptVO> voList = page.getRecords()
                .stream()
                .map(convert::toVO)
                .toList();

        PageResponse
                <SysDeptVO> response = new PageResponse<>(
                voList,
                page.getTotal(),
                page.getCurrent(),
                page.getSize()
        );

        return ApiResponse.success(response);
    }

    @Override
    public ApiResponse
            <SysDeptVO> getById(Serializable id) {
        return ApiResponse.success(service.getVOById(id));
    }

    @Override
    public ApiResponse
            <Long> save(@Valid SysDeptDTO dto) {
        Long id = service.saveFromDTO(dto);
        return ApiResponse.success(id);
    }

    @Override
    public ApiResponse
            <Boolean> update(Serializable id, @Valid SysDeptDTO dto) {
        boolean update = service.updateFromDTO(id, dto);
        return ApiResponse.success(update);
    }

    @Override
    public ApiResponse
            <Boolean> remove(Serializable id) {
        boolean remove = service.removeById(id);
        return ApiResponse.success(remove);
    }

    @Override
    public ApiResponse
            <Boolean> batchRemove(List
                                          <Serializable> ids) {
        boolean remove = service.removeByIds(ids);
        return ApiResponse.success(remove);
    }
}
