package com.ao.platform.auth.service.impl;

import com.ao.platform.auth.convert.SysRoleConvert;
import com.ao.platform.auth.dto.SysRoleDTO;
import com.ao.platform.auth.entity.SysRole;
import com.ao.platform.auth.entity.SysRoleMenu;
import com.ao.platform.auth.mapper.SysRoleMapper;
import com.ao.platform.auth.service.ISysRoleMenuService;
import com.ao.platform.auth.service.ISysRoleService;
import com.ao.platform.auth.vo.SysRoleVO;
import com.ao.platform.base.exception.BusinessException;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 服务实现类
 */
@Service
@RequiredArgsConstructor
public class SysRoleServiceImpl
        extends ServiceImpl<SysRoleMapper, SysRole>
        implements ISysRoleService {

    private final SysRoleConvert convert;
    private final ISysRoleMenuService sysRoleMenuService;


    @Override
    public SysRoleVO getVOById(Long id) {
        SysRole entity = this.getById(id);
        return convert.toVO(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long saveFromDTO(SysRoleDTO dto) {
        SysRole entity = convert.toEntity(dto);
        if (entity.getTenantId() == null){
            entity.setTenantId(0L);
        }
        entity.setId(IdWorker.getId());
        List<SysRoleMenu> sysRoleMenus = dto.getPermissions().stream().map(menuId -> new SysRoleMenu()
                        .setTenantId(0L)
                        .setMenuId(Long.parseLong(menuId))
                        .setRoleId(entity.getId()))
                .toList();
        boolean save = this.save(entity);
        if (!save){
            return null;
        }
        boolean batch = sysRoleMenuService.saveBatch(sysRoleMenus);
        if (!batch){
            throw new BusinessException("角色菜单保存异常");
        }
        return entity.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateFromDTO(Long id, SysRoleDTO dto) {
        List<String> permissions = dto.getPermissions();
        SysRole entity = this.getById(id);
        if (entity == null) {
            throw new BusinessException("角色不存在");
        }

        // 更新角色基本信息
        convert.updateFromDTO(dto, entity);
        boolean updated = this.updateById(entity);
        if (!updated) {
            return false;
        }

        // 获取现有角色的菜单权限，并进行更新
        List<SysRoleMenu> sysRoleMenus = permissions.stream().distinct()
                .map(menuId -> new SysRoleMenu()
                        .setTenantId(0L)  // 设置租户 ID
                        .setMenuId(Long.parseLong(menuId))  // 转换 menuId 为 Long 类型
                        .setRoleId(entity.getId()))  // 关联当前角色
                .collect(Collectors.toList());

        // 删除旧的角色菜单数据，防止冗余
        sysRoleMenuService.remove(Wrappers.lambdaUpdate(SysRoleMenu.class).eq(SysRoleMenu::getRoleId,id));

        // 批量插入新的角色菜单数据
        boolean batchSaved = sysRoleMenuService.saveBatch(sysRoleMenus);
        if (!batchSaved) {
            throw new BusinessException("角色菜单保存异常");
        }

        return true;
    }
}
