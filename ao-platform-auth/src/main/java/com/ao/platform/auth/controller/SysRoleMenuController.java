package com.ao.platform.auth.controller;

import com.ao.platform.auth.api.ISysRoleMenuApi;
import com.ao.platform.auth.convert.SysRoleMenuConvert;
import com.ao.platform.auth.dto.SysRoleMenuDTO;
import com.ao.platform.auth.dto.SysRoleMenuPageQuery;
import com.ao.platform.auth.entity.SysRoleMenu;
import com.ao.platform.auth.service.ISysRoleMenuService;
import com.ao.platform.auth.vo.SysRoleMenuVO;
import com.ao.platform.auth.web.BaseController;
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
public class SysRoleMenuController extends BaseController implements ISysRoleMenuApi {

    private final SysRoleMenuConvert convert;
    private final ISysRoleMenuService service;

    @Override
    public ApiResponse
            <PageResponse
                    <SysRoleMenuVO>> page(SysRoleMenuPageQuery pageQuery) {

        LambdaQueryWrapper<SysRoleMenu> wrapper =
                Wrappers.lambdaQuery(convert.toEntity(pageQuery));

        Page<SysRoleMenu> page = service.page(
                PageFactory.build(pageQuery),
                wrapper
        );

        List
                <SysRoleMenuVO> voList = page.getRecords()
                .stream()
                .map(convert::toVO)
                .toList();

        PageResponse
                <SysRoleMenuVO> response = new PageResponse<>(
                voList,
                page.getTotal(),
                page.getCurrent(),
                page.getSize()
        );

        return ApiResponse.success(response);
    }

    @Override
    public ApiResponse
            <SysRoleMenuVO> getById(Long id) {
        return ApiResponse.success(service.getVOById(id));
    }

    @Override
    public ApiResponse
            <Long> save(@Valid SysRoleMenuDTO dto) {
        Long id = service.saveFromDTO(dto);
        return ApiResponse.success(id);
    }

    @Override
    public ApiResponse
            <Boolean> update(Long id, @Valid SysRoleMenuDTO dto) {
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
            <Boolean> batchRemove(List<Long> ids) {
        boolean remove = service.removeByIds(ids);
        return ApiResponse.success(remove);
    }
}
