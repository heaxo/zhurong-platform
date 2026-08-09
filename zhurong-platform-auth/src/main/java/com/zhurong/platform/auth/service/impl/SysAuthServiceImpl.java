package com.zhurong.platform.auth.service.impl;

import com.zhurong.platform.auth.convert.SysUserConvert;
import com.zhurong.platform.auth.dto.LoginRequest;
import com.zhurong.platform.auth.dto.RegisterRequest;
import com.zhurong.platform.auth.entity.SysRole;
import com.zhurong.platform.auth.entity.SysUser;
import com.zhurong.platform.auth.entity.SysUserRole;
import com.zhurong.platform.auth.mapper.SysUserMapper;
import com.zhurong.platform.auth.service.ISysAuthService;
import com.zhurong.platform.auth.service.ISysRoleService;
import com.zhurong.platform.auth.service.ISysUserRoleService;
import com.zhurong.platform.auth.vo.LoginResponse;
import com.zhurong.platform.base.exception.BusinessException;
import com.zhurong.platform.base.model.BaseEntity;
import com.zhurong.platform.security.jwt.JwtProvider;
import com.zhurong.platform.security.model.TokenUser;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean register(RegisterRequest request) {
        long count = userMapper.selectCount(
                Wrappers.lambdaQuery(SysUser.class)
                        .eq(SysUser::getTenantId, 0L)
                        .eq(SysUser::getUsername, request.getUsername())
        );

        if (count > 0) {
            throw new BusinessException("用户名已存在");
        }

        SysUser user = new SysUser()
                .setTenantId(0L)
                .setUsername(request.getUsername())
                .setPassword(passwordEncoder.encode(request.getPassword()))
                .setRealName(resolveRealName(request))
                .setStatus(1);

        return userMapper.insert(user) > 0;
    }

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
        if (roleIds.isEmpty()) {
            TokenUser tokenUser = userConvert.toTokenUser(user);
            String token = jwtProvider.generateToken(tokenUser, Collections.emptyList());
            return LoginResponse.builder()
                    .id(user.getId().toString())
                    .username(user.getUsername())
                    .realName(user.getRealName())
                    .roles(Collections.emptyList())
                    .accessToken(token)
                    .build();
        }
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
        if (roleIds.isEmpty()) {
            return Collections.emptyList();
        }
        List<SysRole> roles = sysRoleService.list(Wrappers.lambdaQuery(SysRole.class).in(BaseEntity::getId, roleIds));
        return roles.stream().map(SysRole::getCode).toList();
    }

    private String resolveRealName(RegisterRequest request) {
        if (request.getRealName() == null || request.getRealName().isBlank()) {
            return request.getUsername();
        }
        return request.getRealName();
    }

}
