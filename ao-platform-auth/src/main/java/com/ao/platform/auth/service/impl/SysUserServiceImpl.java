package com.ao.platform.auth.service.impl;

import com.ao.platform.auth.convert.SysUserConvert;
import com.ao.platform.auth.dto.SysUserDTO;
import com.ao.platform.auth.entity.SysUser;
import com.ao.platform.auth.entity.SysUserRole;
import com.ao.platform.auth.mapper.SysUserMapper;
import com.ao.platform.auth.security.model.JwtUserDetails;
import com.ao.platform.auth.service.ISysUserRoleService;
import com.ao.platform.auth.service.ISysUserService;
import com.ao.platform.auth.vo.SysUserVO;
import com.ao.platform.base.exception.BusinessException;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * 服务实现类
 */
@Service
@RequiredArgsConstructor
public class SysUserServiceImpl
        extends ServiceImpl<SysUserMapper, SysUser>
        implements ISysUserService {

    private final ISysUserRoleService sysUserRoleService;
    private final SysUserConvert convert;
    private final PasswordEncoder passwordEncoder;

    @Override
    public SysUserVO getVOById(Serializable id) {
        SysUser entity = this.getById(id);
        return convert.toVO(entity);
    }

    @Override
    public Long saveFromDTO(SysUserDTO dto) {
        SysUser entity = convert.toEntity(dto);
        this.save(entity);
        return entity.getId();
    }

    @Override
    public Boolean updateFromDTO(Serializable id, SysUserDTO dto) {
        SysUser entity = this.getById(id);
        convert.updateFromDTO(dto, entity);
        return this.updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean createUser(SysUserDTO request) {

        Long tenantId = getCurrentTenantId();

        // 校验租户内用户名唯一
        long count = count(
                Wrappers.lambdaQuery(SysUser.class)
                        .eq(SysUser::getTenantId, tenantId)
                        .eq(SysUser::getUsername, request.getUsername())
        );

        if (count > 0) {
            throw new BusinessException("用户名已存在");
        }

        // 构建用户
        SysUser user = new SysUser()
                .setTenantId(tenantId)
                .setUsername(request.getUsername())
                .setPassword(passwordEncoder.encode(request.getPassword()))
                .setRealName(request.getRealName())
                .setDeptId(request.getDeptId())
                .setStatus(request.getStatus())
                .setRemark(request.getRemark());

        boolean save = save(user);

        if (!save) {
            throw new BusinessException("用户创建失败");
        }

        // 绑定角色
        if (request.getRoleIds() != null && !request.getRoleIds().isEmpty()) {
            List<SysUserRole> userRoles = request.getRoleIds()
                    .stream()
                    .map(roleId -> new SysUserRole()
                            .setTenantId(tenantId)
                            .setUserId(user.getId())
                            .setRoleId(roleId)
                    ).toList();
            return save && sysUserRoleService.saveBatch(userRoles);
        }
        return false;
    }

    private Long getCurrentTenantId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null){
            return 0L;
        }
        Object principal = authentication.getPrincipal();
        if (principal == null){
            return 0L;
        }
        JwtUserDetails user = (JwtUserDetails) principal;
        return user.getTenantId();
    }

}
