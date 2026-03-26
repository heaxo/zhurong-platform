package com.zhurong.platform.auth.controller;

import com.zhurong.platform.auth.api.ISysRoleApi;
import com.zhurong.platform.auth.convert.SysRoleConvert;
import com.zhurong.platform.auth.dto.SysRoleDTO;
import com.zhurong.platform.auth.dto.SysRolePageQuery;
import com.zhurong.platform.auth.entity.SysRole;
import com.zhurong.platform.auth.entity.SysRoleMenu;
import com.zhurong.platform.auth.service.ISysRoleMenuService;
import com.zhurong.platform.auth.service.ISysRoleService;
import com.zhurong.platform.auth.vo.SysRoleVO;
import com.zhurong.platform.auth.web.BaseController;
import com.zhurong.platform.base.api.ApiResponse;
import com.zhurong.platform.base.api.PageResponse;
import com.zhurong.platform.base.model.PageFactory;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 控制器实现
 */
@RestController
@RequiredArgsConstructor
public class SysRoleController extends BaseController implements ISysRoleApi {

    private final SysRoleConvert convert;
    private final ISysRoleService service;
    private final ISysRoleMenuService sysRoleMenuService;

    @Override
    public ApiResponse
            <PageResponse
                    <SysRoleVO>> page(SysRolePageQuery pageQuery) {

        LambdaQueryWrapper<SysRole> wrapper =
                Wrappers.lambdaQuery(convert.toEntity(pageQuery));

        Page<SysRole> page = service.page(
                PageFactory.build(pageQuery),
                wrapper
        );

        List
                <SysRoleVO> voList = page.getRecords()
                .stream()
                .map(convert::toVO)
                .toList();

        List<SysRoleMenu> sysRoleMenus = sysRoleMenuService.list();
        Map<Long, List<SysRoleMenu>> map = sysRoleMenus.stream().collect(Collectors.groupingBy(SysRoleMenu::getRoleId));
        voList.forEach(v -> {
            v.setPermissions(map.getOrDefault(Long.parseLong(v.getId()), new ArrayList<>())
                    .stream()
                    .map(SysRoleMenu::getMenuId)
                    .map(String::valueOf)
                    .toList());
        });

        PageResponse
                <SysRoleVO> response = new PageResponse<>(
                voList,
                page.getTotal(),
                page.getCurrent(),
                page.getSize()
        );

        return ApiResponse.success(response);
    }

    @Override
    public ApiResponse
            <SysRoleVO> getById(Long id) {
        return ApiResponse.success(service.getVOById(id));
    }

    @Override
    public ApiResponse
            <Long> save(@Valid SysRoleDTO dto) {
        Long id = service.saveFromDTO(dto);
        return ApiResponse.success(id);
    }

    @Override
    public ApiResponse
            <Boolean> update(Long id, @Valid SysRoleDTO dto) {
        boolean update = service.updateFromDTO(id, dto);
        return ApiResponse.success(update);
    }

    @Override
    public ApiResponse
            <Boolean> remove(Long id) {
        boolean remove1 = service.removeById(id);
        boolean remove2 = sysRoleMenuService.remove(Wrappers.lambdaUpdate(SysRoleMenu.class)
                .eq(SysRoleMenu::getRoleId, id));
        return ApiResponse.success(remove1 && remove2);
    }

    @Override
    public ApiResponse
            <Boolean> batchRemove(List<Long> ids) {
        boolean remove = service.removeByIds(ids);
        return ApiResponse.success(remove);
    }
}
