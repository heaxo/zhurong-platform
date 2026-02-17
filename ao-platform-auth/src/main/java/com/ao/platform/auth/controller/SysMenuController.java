package com.ao.platform.auth.controller;

import com.ao.platform.auth.service.ISysMenuService;
import com.ao.platform.auth.dto.SysMenuDTO;
import com.ao.platform.auth.vo.SysMenuVO;
import com.ao.platform.auth.entity.SysMenu;
import com.ao.platform.auth.api.ISysMenuApi;

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
public class SysMenuController implements ISysMenuApi {

    private final ISysMenuService service;

    @Override
    public ApiResponse<PageResponse<SysMenuVO>> page(long current, long size) {

        Page<SysMenu> page = service.page(new Page<>(current, size));

        List<SysMenuVO> voList = page.getRecords()
                .stream()
                .map(service::convertToVO)
                .toList();

        PageResponse<SysMenuVO> response = new PageResponse<>(
                voList,
                page.getTotal(),
                page.getCurrent(),
                page.getSize()
        );

        return ApiResponse.success(response);
    }

    @Override
    public ApiResponse<SysMenuVO> getById(Serializable id) {
        return ApiResponse.success(service.getVOById(id));
    }

    @Override
    public ApiResponse<Void> save(@Valid SysMenuDTO dto) {
        service.saveFromDTO(dto);
        return ApiResponse.success();
    }

    @Override
    public ApiResponse<Void> update(Serializable id, @Valid SysMenuDTO dto) {
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
