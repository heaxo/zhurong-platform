package com.zhurong.platform.auth.controller;

import com.zhurong.platform.auth.api.ISysMenuApi;
import com.zhurong.platform.auth.convert.SysMenuConvert;
import com.zhurong.platform.auth.dto.SysMenuDTO;
import com.zhurong.platform.auth.dto.SysMenuPageQuery;
import com.zhurong.platform.auth.entity.SysMenu;
import com.zhurong.platform.auth.entity.SysRoleMenu;
import com.zhurong.platform.auth.entity.SysUserRole;
import com.zhurong.platform.auth.service.ISysMenuService;
import com.zhurong.platform.auth.service.ISysRoleMenuService;
import com.zhurong.platform.auth.service.ISysUserRoleService;
import com.zhurong.platform.auth.vo.SysMenuMetaVO;
import com.zhurong.platform.auth.vo.SysMenuVO;
import com.zhurong.platform.auth.web.BaseController;
import com.zhurong.platform.base.api.ApiResponse;
import com.zhurong.platform.base.api.PageResponse;
import com.zhurong.platform.base.model.BaseEntity;
import com.zhurong.platform.base.model.PageFactory;
import com.zhurong.platform.base.util.TreeBuilder;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;

/**
 * 控制器实现
 */
@RestController
@RequiredArgsConstructor
public class SysMenuController extends BaseController implements ISysMenuApi {

    private final SysMenuConvert convert;
    private final ISysMenuService service;
    private final ISysUserRoleService sysUserRoleService;
    private final ISysRoleMenuService sysRoleMenuService;

    @Override
    public ApiResponse
            <PageResponse
                    <SysMenuVO>> page(SysMenuPageQuery pageQuery) {

        LambdaQueryWrapper<SysMenu> wrapper =
                Wrappers.lambdaQuery(convert.toEntity(pageQuery));

        Page<SysMenu> page = service.page(
                PageFactory.build(pageQuery),
                wrapper
        );

        List<SysMenuVO> voList = page.getRecords()
                .stream()
                .map(convert::toVO)
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
    public ApiResponse<Boolean> nameExists(SysMenuDTO dto) {
        long count = service.count(Wrappers.lambdaQuery(SysMenu.class)
                .ne(BaseEntity::getId, dto.getId())
                .eq(SysMenu::getName, dto.getName()));
        return ApiResponse.success(count > 0);
    }

    @Override
    public ApiResponse<Boolean> pathExists(SysMenuDTO dto) {
        long count = service.count(Wrappers.lambdaQuery(SysMenu.class)
                .ne(BaseEntity::getId, dto.getId())
                .eq(SysMenu::getPath, dto.getPath()));
        return ApiResponse.success(count > 0);
    }

    @Override
    public ApiResponse
            <SysMenuVO> getById(Long id) {
        return ApiResponse.success(service.getVOById(id));
    }

    @Override
    public ApiResponse
            <Long> save(@Valid SysMenuDTO dto) {
        Long id = service.saveFromDTO(dto);
        return ApiResponse.success(id);
    }

    @Override
    public ApiResponse
            <Boolean> update(Long id, @Valid SysMenuDTO dto) {
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

    @Override
    public ApiResponse<List<SysMenuVO>> all() {
        List<SysUserRole> sysUserRoles = sysUserRoleService.list(Wrappers.lambdaQuery(SysUserRole.class)
                .eq(SysUserRole::getUserId, getCurrentUserId()));
        List<Long> roleIds = sysUserRoles.stream().map(SysUserRole::getRoleId).toList();
        List<SysRoleMenu> roleMenus = sysRoleMenuService.list(Wrappers.lambdaQuery(SysRoleMenu.class)
                .in(SysRoleMenu::getRoleId, roleIds));
        List<Long> menuIds = roleMenus.stream().map(SysRoleMenu::getMenuId).distinct().toList();
        List<SysMenu> menus = service.list(Wrappers.lambdaQuery(SysMenu.class).in(BaseEntity::getId, menuIds));
        List<SysMenuVO> vos = convert.toVO(menus);
        vos.forEach(vo -> {
            vo.setMeta(SysMenuMetaVO.from(vo));
        });
        List<SysMenuVO> tree = TreeBuilder.buildTree(
                vos,
                SysMenuVO::getId,
                SysMenuVO::getPid,
                pid -> pid == null || "-1".equals(pid) || pid.isEmpty(),
                Comparator.comparing(SysMenuVO::getSortOrder),
                SysMenuVO::setChildren
        );
        return ApiResponse.success(tree);
    }

    @GetMapping("tree-list")
    public ApiResponse<List<SysMenuVO>> treeList() {
        List<SysMenu> list = service.list();

        List<SysMenuVO> vos = list
                .stream()
                .map(convert::toVO)
                .toList();
        vos.forEach(vo -> {
            vo.setMeta(SysMenuMetaVO.from(vo));
        });
        List<SysMenuVO> tree = TreeBuilder.buildTree(
                vos,
                SysMenuVO::getId,
                SysMenuVO::getPid,
                pid -> pid == null || "-1".equals(pid) || pid.isEmpty(),
                Comparator.comparing(SysMenuVO::getSortOrder),
                SysMenuVO::setChildren
        );
        return ApiResponse.success(tree);
    }
}
