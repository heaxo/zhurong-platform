package com.ao.platform.auth.service.impl;

import com.ao.platform.auth.convert.SysUserConvert;
import com.ao.platform.auth.dto.LoginRequest;
import com.ao.platform.auth.entity.SysRole;
import com.ao.platform.auth.entity.SysUser;
import com.ao.platform.auth.entity.SysUserRole;
import com.ao.platform.auth.mapper.SysUserMapper;
import com.ao.platform.auth.service.ISysAuthService;
import com.ao.platform.auth.service.ISysRoleService;
import com.ao.platform.auth.service.ISysUserRoleService;
import com.ao.platform.auth.vo.LoginResponse;
import com.ao.platform.base.exception.BusinessException;
import com.ao.platform.base.model.BaseEntity;
import com.ao.platform.security.jwt.JwtProvider;
import com.ao.platform.security.model.TokenUser;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SysAuthServiceImpl implements ISysAuthService {

    private final ISysUserRoleService sysUserRoleService;
    private final ISysRoleService sysRoleService;
    private final SysUserMapper userMapper;
    private final SysUserConvert userConvert;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    public LoginResponse login(LoginRequest request) {

        SysUser user = userMapper.selectOne(
                Wrappers.lambdaQuery(SysUser.class)
                        .eq(SysUser::getUsername, request.getUsername())
        );

        if (user == null) {
            throw new BusinessException("用户不存在");
        }

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BusinessException("密码错误");
        }

        List<SysUserRole> sysUserRoles = sysUserRoleService.list(Wrappers.lambdaQuery(SysUserRole.class)
                .eq(SysUserRole::getUserId, user.getId()));

        List<Long> roleIds = sysUserRoles.stream().map(SysUserRole::getRoleId).toList();
        List<SysRole> roles = sysRoleService.list(Wrappers.lambdaQuery(SysRole.class).in(BaseEntity::getId, roleIds));
        List<String> codes = roles.stream().map(SysRole::getCode).toList();
        TokenUser tokenUser = userConvert.toTokenUser(user);
        String token = jwtProvider.generateToken(tokenUser, codes);

        return LoginResponse.builder()
                .id(user.getId().toString())
                .username(user.getUsername())
                .realName(user.getRealName())
                .roles(codes)
                .accessToken(token)
                .build();
    }

    public List<String> codes(String username) {
        SysUser user = userMapper.selectOne(
                Wrappers.lambdaQuery(SysUser.class).eq(SysUser::getUsername, username)
        );

        if (user == null) {
            throw new BusinessException("用户不存在");
        }

        List<SysUserRole> sysUserRoles = sysUserRoleService.list(Wrappers.lambdaQuery(SysUserRole.class)
                .eq(SysUserRole::getUserId, user.getId()));

        List<Long> roleIds = sysUserRoles.stream().map(SysUserRole::getRoleId).toList();
        List<SysRole> roles = sysRoleService.list(Wrappers.lambdaQuery(SysRole.class).in(BaseEntity::getId, roleIds));
        return roles.stream().map(SysRole::getCode).toList();
    }

}
