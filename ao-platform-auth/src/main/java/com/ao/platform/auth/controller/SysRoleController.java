package com.ao.platform.auth.controller;

import com.ao.platform.auth.service.ISysRoleService;
import com.ao.platform.auth.dto.SysRoleDTO;
import com.ao.platform.auth.vo.SysRoleVO;
import com.ao.platform.auth.entity.SysRole;
import com.ao.platform.auth.api.ISysRoleApi;

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
public class SysRoleController implements ISysRoleApi {

    private final ISysRoleService service;

    @Override
    public ApiResponse<PageResponse<SysRoleVO>> page(long current, long size) {

        Page<SysRole> page = service.page(new Page<>(current, size));

        List<SysRoleVO> voList = page.getRecords()
                .stream()
                .map(service::convertToVO)
                .toList();

        PageResponse<SysRoleVO> response = new PageResponse<>(
                voList,
                page.getTotal(),
                page.getCurrent(),
                page.getSize()
        );

        return ApiResponse.success(response);
    }

    @Override
    public ApiResponse<SysRoleVO> getById(Serializable id) {
        return ApiResponse.success(service.getVOById(id));
    }

    @Override
    public ApiResponse<Void> save(@Valid SysRoleDTO dto) {
        service.saveFromDTO(dto);
        return ApiResponse.success();
    }

    @Override
    public ApiResponse<Void> update(Serializable id, @Valid SysRoleDTO dto) {
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
