package com.ao.platform.auth.controller;

import com.ao.platform.auth.service.ISysUserRoleService;
import com.ao.platform.auth.dto.SysUserRoleDTO;
import com.ao.platform.auth.vo.SysUserRoleVO;
import com.ao.platform.auth.entity.SysUserRole;
import com.ao.platform.auth.api.ISysUserRoleApi;

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
public class SysUserRoleController implements ISysUserRoleApi {

    private final ISysUserRoleService service;

    @Override
    public ApiResponse<PageResponse<SysUserRoleVO>> page(long current, long size) {

        Page<SysUserRole> page = service.page(new Page<>(current, size));

        List<SysUserRoleVO> voList = page.getRecords()
                .stream()
                .map(service::convertToVO)
                .toList();

        PageResponse<SysUserRoleVO> response = new PageResponse<>(
                voList,
                page.getTotal(),
                page.getCurrent(),
                page.getSize()
        );

        return ApiResponse.success(response);
    }

    @Override
    public ApiResponse<SysUserRoleVO> getById(Serializable id) {
        return ApiResponse.success(service.getVOById(id));
    }

    @Override
    public ApiResponse<Void> save(@Valid SysUserRoleDTO dto) {
        service.saveFromDTO(dto);
        return ApiResponse.success();
    }

    @Override
    public ApiResponse<Void> update(Serializable id, @Valid SysUserRoleDTO dto) {
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
