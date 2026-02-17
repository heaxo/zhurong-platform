package com.ao.platform.auth.controller;

import com.ao.platform.auth.service.ISysDeptService;
import com.ao.platform.auth.dto.SysDeptDTO;
import com.ao.platform.auth.vo.SysDeptVO;
import com.ao.platform.auth.entity.SysDept;
import com.ao.platform.auth.api.ISysDeptApi;

import com.ao.platform.base.api.ApiResponse;
import com.ao.platform.base.api.PageResponse;

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

    private final ISysDeptService service;

    @Override
    public ApiResponse<PageResponse<SysDeptVO>> page(long current, long size) {

        Page<SysDept> page = service.page(new Page<>(current, size));

        List<SysDeptVO> voList = page.getRecords()
                .stream()
                .map(service::convertToVO)
                .toList();

        PageResponse<SysDeptVO> response = new PageResponse<>(
                voList,
                page.getTotal(),
                page.getCurrent(),
                page.getSize()
        );

        return ApiResponse.success(response);
    }

    @Override
    public ApiResponse<SysDeptVO> getById(Serializable id) {
        return ApiResponse.success(service.getVOById(id));
    }

    @Override
    public ApiResponse<Void> save(@Valid SysDeptDTO dto) {
        service.saveFromDTO(dto);
        return ApiResponse.success();
    }

    @Override
    public ApiResponse<Void> update(Serializable id, @Valid SysDeptDTO dto) {
        service.updateFromDTO(id, dto);
        return ApiResponse.success();
    }

    @Override
    public ApiResponse<Void> remove(Serializable id) {
        service.removeById(id);
        return ApiResponse.success();
    }

    @Override
    public ApiResponse<Void> batchRemove(List<Serializable> ids) {
        service.removeByIds(ids);
        return ApiResponse.success();
    }
}
