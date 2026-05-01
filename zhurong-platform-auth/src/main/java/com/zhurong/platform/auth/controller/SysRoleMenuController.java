package com.zhurong.platform.auth.controller;

import com.zhurong.platform.auth.api.ISysRoleMenuApi;
import com.zhurong.platform.auth.convert.SysRoleMenuConvert;
import com.zhurong.platform.auth.dto.SysRoleMenuDTO;
import com.zhurong.platform.auth.dto.SysRoleMenuPageQuery;
import com.zhurong.platform.auth.entity.SysRoleMenu;
import com.zhurong.platform.auth.service.ISysRoleMenuService;
import com.zhurong.platform.auth.vo.SysRoleMenuVO;
import com.zhurong.platform.auth.web.BaseController;
import com.zhurong.platform.base.api.ApiResponse;
import com.zhurong.platform.base.api.PageResponse;
import com.zhurong.platform.base.model.BaseEntity;
import com.zhurong.platform.base.model.PageFactory;
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
public class SysRoleMenuController extends BaseController implements ISysRoleMenuApi {

    private final SysRoleMenuConvert convert;
    private final ISysRoleMenuService service;

    @Override
    public ApiResponse
            <PageResponse
                    <SysRoleMenuVO>> page(SysRoleMenuPageQuery pageQuery) {

        LambdaQueryWrapper<SysRoleMenu> wrapper =
                Wrappers.lambdaQuery(convert.toEntity(pageQuery));
        wrapper.orderByAsc(BaseEntity::getCreateTime);
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
