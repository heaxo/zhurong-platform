package com.ao.platform.auth.controller;

import com.ao.platform.auth.api.ISysUserApi;
import com.ao.platform.auth.convert.SysUserConvert;
import com.ao.platform.auth.dto.CreateUserRequest;
import com.ao.platform.auth.dto.SysUserDTO;
import com.ao.platform.auth.dto.SysUserPageQuery;
import com.ao.platform.auth.entity.SysUser;
import com.ao.platform.auth.service.ISysUserService;
import com.ao.platform.auth.vo.SysUserVO;
import com.ao.platform.base.api.ApiResponse;
import com.ao.platform.base.api.PageResponse;
import com.ao.platform.base.model.PageFactory;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.List;

/**
 * 控制器实现
 */
@RestController
@RequiredArgsConstructor
public class SysUserController implements ISysUserApi {

    private final SysUserConvert convert;
    private final ISysUserService service;

    @Override
    public ApiResponse
            <PageResponse
                    <SysUserVO>> page(SysUserPageQuery pageQuery) {

        LambdaQueryWrapper<SysUser> wrapper =
                Wrappers.lambdaQuery(convert.toEntity(pageQuery));

        Page<SysUser> page = service.page(
                PageFactory.build(pageQuery),
                wrapper
        );

        List
                <SysUserVO> voList = page.getRecords()
                .stream()
                .map(convert::toVO)
                .toList();

        PageResponse
                <SysUserVO> response = new PageResponse<>(
                voList,
                page.getTotal(),
                page.getCurrent(),
                page.getSize()
        );

        return ApiResponse.success(response);
    }

    @Override
    public ApiResponse
            <SysUserVO> getById(Serializable id) {
        return ApiResponse.success(service.getVOById(id));
    }

    @Override
    public ApiResponse
            <Long> save(@Valid SysUserDTO dto) {
        Long id = service.saveFromDTO(dto);
        return ApiResponse.success(id);
    }

    @Override
    public ApiResponse
            <Boolean> update(Serializable id, @Valid SysUserDTO dto) {
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

    @PostMapping("create")
    public ApiResponse<Boolean> create(@Valid @RequestBody CreateUserRequest request) {
        SysUserDTO sysUserDTO = convert.toDTO(request);
        boolean create = service.createUser(sysUserDTO);
        return ApiResponse.success(create);
    }
}
