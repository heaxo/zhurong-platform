package com.ao.platform.auth.web;

import com.ao.platform.base.exception.BusinessException;
import com.ao.platform.security.model.JwtUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Controller 基类
 * <p>
 * 提供：
 * 1. 当前登录用户获取
 * 2. 当前租户获取
 * 3. 当前用户ID获取
 * 4. 当前用户名获取
 * <p>
 * 企业级多租户基础设施
 */
public abstract class BaseController {

    /**
     * 获取当前 Authentication
     */
    protected Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 获取当前登录用户（未登录抛异常）
     */
    protected JwtUserDetails getCurrentUser() {

        Authentication authentication = getAuthentication();

        if (authentication == null || authentication.getPrincipal() == null) {
            throw new BusinessException("未登录或登录已失效");
        }

        if (!(authentication.getPrincipal() instanceof JwtUserDetails user)) {
            throw new BusinessException("非法用户上下文");
        }

        return user;
    }

    /**
     * 获取当前用户ID
     */
    protected Long getCurrentUserId() {
        return getCurrentUser().getUserId();
    }

    /**
     * 获取当前用户名
     */
    protected String getCurrentUsername() {
        return getCurrentUser().getUsername();
    }

    /**
     * 获取当前租户ID
     */
    protected Long getCurrentTenantId() {
        return getCurrentUser().getTenantId();
    }

    /**
     * 是否为超级管理员（可扩展）
     */
    protected boolean isSuperAdmin() {
        return getCurrentUser().getRoles().contains("SUPER_ADMIN");
    }
}
