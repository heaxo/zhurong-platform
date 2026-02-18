package com.ao.platform.auth.controller;

import com.ao.platform.auth.api.ISysUserRoleApi;
import com.ao.platform.auth.convert.SysUserRoleConvert;
import com.ao.platform.auth.dto.SysUserRoleDTO;
import com.ao.platform.auth.dto.SysUserRolePageQuery;
import com.ao.platform.auth.entity.SysUserRole;
import com.ao.platform.auth.service.ISysUserRoleService;
import com.ao.platform.auth.vo.SysUserRoleVO;
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
public class SysUserRoleController implements ISysUserRoleApi {

    private final SysUserRoleConvert convert;
    private final ISysUserRoleService service;

    @Override
    public ApiResponse
            <PageResponse
                    <SysUserRoleVO>> page(SysUserRolePageQuery pageQuery) {

        LambdaQueryWrapper<SysUserRole> wrapper =
                Wrappers.lambdaQuery(convert.toEntity(pageQuery));

        Page<SysUserRole> page = service.page(
                PageFactory.build(pageQuery),
                wrapper
        );

        List
                <SysUserRoleVO> voList = page.getRecords()
                .stream()
                .map(convert::toVO)
                .toList();

        PageResponse
                <SysUserRoleVO> response = new PageResponse<>(
                voList,
                page.getTotal(),
                page.getCurrent(),
                page.getSize()
        );

        return ApiResponse.success(response);
    }

    @Override
    public ApiResponse
            <SysUserRoleVO> getById(Serializable id) {
        return ApiResponse.success(service.getVOById(id));
    }

    @Override
    public ApiResponse
            <Long> save(@Valid SysUserRoleDTO dto) {
        Long id = service.saveFromDTO(dto);
        return ApiResponse.success(id);
    }

    @Override
    public ApiResponse
            <Boolean> update(Serializable id, @Valid SysUserRoleDTO dto) {
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
