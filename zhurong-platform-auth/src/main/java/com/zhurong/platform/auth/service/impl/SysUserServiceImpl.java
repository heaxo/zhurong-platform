package com.zhurong.platform.auth.service.impl;

import com.zhurong.platform.auth.convert.SysUserConvert;
import com.zhurong.platform.auth.dto.SysUserDTO;
import com.zhurong.platform.auth.entity.SysRole;
import com.zhurong.platform.auth.entity.SysUser;
import com.zhurong.platform.auth.entity.SysUserRole;
import com.zhurong.platform.auth.mapper.SysUserMapper;
import com.zhurong.platform.auth.service.ISysRoleService;
import com.zhurong.platform.auth.service.ISysUserRoleService;
import com.zhurong.platform.auth.service.ISysUserService;
import com.zhurong.platform.auth.vo.SysUserVO;
import com.zhurong.platform.base.exception.BusinessException;
import com.zhurong.platform.base.model.BaseEntity;
import com.zhurong.platform.security.model.JwtUserDetails;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * 服务实现类
 */
@Service
@RequiredArgsConstructor
public class SysUserServiceImpl
        extends ServiceImpl<SysUserMapper, SysUser>
        implements ISysUserService {

    private final ISysUserRoleService sysUserRoleService;
    private final ISysRoleService sysRoleService;
    private final SysUserConvert convert;
    private final PasswordEncoder passwordEncoder;

    @Override
    public SysUserVO getVOById(Long id) {
        SysUser entity = this.getById(id);
        SysUserVO vo = convert.toVO(entity);
        List<SysUserRole> sysUserRoles = sysUserRoleService.list(Wrappers.lambdaQuery(SysUserRole.class).eq(SysUserRole::getUserId, id));
        List<Long> roleIds = sysUserRoles.stream().map(SysUserRole::getRoleId).toList();
        if (roleIds.isEmpty()) {
            vo.setRoles(List.of());
            return vo;
        }
        List<SysRole> roles = sysRoleService.list(Wrappers.lambdaQuery(SysRole.class).in(BaseEntity::getId, roleIds));
        vo.setRoles(roles.stream().map(SysRole::getCode).toList());
        return vo;
    }

    @Override
    public Long saveFromDTO(SysUserDTO dto) {
        dto.setClientId(normalizeClientId(dto.getClientId()));
        SysUser entity = convert.toEntity(dto);
        if (entity.getTenantId() == null){
            entity.setTenantId(0L);
        }
        this.save(entity);
        return entity.getId();
    }

    @Override
    public Boolean updateFromDTO(Long id, SysUserDTO dto) {
        SysUser entity = this.getById(id);
        dto.setClientId(normalizeClientId(dto.getClientId()));
        convert.updateFromDTO(dto, entity);
        return this.updateById(entity);
    }

    @Override
    public Boolean updateClientId(Long id, String clientId) {
        SysUser entity = this.getById(id);
        if (entity == null) {
            throw new BusinessException("用户不存在");
        }
        entity.setClientId(normalizeClientId(clientId));
        return this.updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean createUser(SysUserDTO request) {

        Long tenantId = getCurrentTenantId();
        String username = request.getUsername().trim();
        List<Long> roleIds = request.getRoleIds() == null
                ? List.of()
                : request.getRoleIds().stream().filter(java.util.Objects::nonNull).distinct().toList();

        // 校验租户内用户名唯一
        long count = count(
                Wrappers.lambdaQuery(SysUser.class)
                        .eq(SysUser::getTenantId, tenantId)
                        .eq(SysUser::getUsername, username)
        );

        if (count > 0) {
            throw new BusinessException("用户名已存在");
        }

        if (!roleIds.isEmpty()) {
            long existingRoleCount = sysRoleService.count(Wrappers.lambdaQuery(SysRole.class)
                    .eq(SysRole::getTenantId, tenantId)
                    .in(BaseEntity::getId, roleIds));
            if (existingRoleCount != roleIds.size()) {
                throw new BusinessException("部分角色不存在或不属于当前租户");
            }
        }

        // 构建用户
        SysUser user = new SysUser()
                .setTenantId(tenantId)
                .setUsername(username)
                .setPassword(passwordEncoder.encode(request.getPassword()))
                .setRealName(request.getRealName().trim())
                .setClientId(normalizeClientId(request.getClientId()))
                .setDeptId(request.getDeptId())
                .setStatus(request.getStatus() == null ? 1 : request.getStatus())
                .setRemark(request.getRemark());

        boolean save = save(user);

        if (!save) {
            throw new BusinessException("用户创建失败");
        }

        // 绑定角色
        if (!roleIds.isEmpty()) {
            List<SysUserRole> userRoles = roleIds
                    .stream()
                    .map(roleId -> new SysUserRole()
                            .setTenantId(tenantId)
                            .setUserId(user.getId())
                            .setRoleId(roleId)
                    ).toList();
            if (!sysUserRoleService.saveBatch(userRoles)) {
                throw new BusinessException("用户角色绑定失败");
            }
        }
        return true;
    }

    private static String normalizeClientId(String clientId) {
        return clientId == null || clientId.isBlank()
                ? null
                : clientId.trim().toUpperCase(java.util.Locale.ROOT);
    }

    private Long getCurrentTenantId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return 0L;
        }
        Object principal = authentication.getPrincipal();
        if (principal == null) {
            return 0L;
        }
        JwtUserDetails user = (JwtUserDetails) principal;
        return Optional.ofNullable(user.getTenantId()).orElse(0L);
    }

}
