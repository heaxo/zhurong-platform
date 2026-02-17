package com.ao.platform.auth.controller;

import com.ao.platform.auth.service.ISysRoleMenuService;
import com.ao.platform.auth.dto.SysRoleMenuDTO;
import com.ao.platform.auth.vo.SysRoleMenuVO;
import com.ao.platform.auth.entity.SysRoleMenu;
import com.ao.platform.auth.api.ISysRoleMenuApi;

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
public class SysRoleMenuController implements ISysRoleMenuApi {

    private final ISysRoleMenuService service;

    @Override
    public ApiResponse<PageResponse<SysRoleMenuVO>> page(long current, long size) {

        Page<SysRoleMenu> page = service.page(new Page<>(current, size));

        List<SysRoleMenuVO> voList = page.getRecords()
                .stream()
                .map(service::convertToVO)
                .toList();

        PageResponse<SysRoleMenuVO> response = new PageResponse<>(
                voList,
                page.getTotal(),
                page.getCurrent(),
                page.getSize()
        );

        return ApiResponse.success(response);
    }

    @Override
    public ApiResponse<SysRoleMenuVO> getById(Serializable id) {
        return ApiResponse.success(service.getVOById(id));
    }

    @Override
    public ApiResponse<Void> save(@Valid SysRoleMenuDTO dto) {
        service.saveFromDTO(dto);
        return ApiResponse.success();
    }

    @Override
    public ApiResponse<Void> update(Serializable id, @Valid SysRoleMenuDTO dto) {
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
