package com.zhurong.platform.security.filter;

import com.zhurong.platform.security.constant.SecurityConstants;
import com.zhurong.platform.security.jwt.JwtAuthenticationToken;
import com.zhurong.platform.security.jwt.JwtProvider;
import com.zhurong.platform.security.model.JwtUserDetails;
import com.zhurong.platform.security.model.TokenUser;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

/*
 * @Author a.he@lantek.com
 * @Description ServletJwtAuthenticationFilter
 * @Date 2026/2/22 15:07
 **/
@RequiredArgsConstructor
public class ServletJwtAuthenticationFilter extends OncePerRequestFilter {

    private static final String HEADER = "Authorization";
    private static final String PREFIX = "Bearer ";

    private final JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getRequestURI();

        if (isWhiteList(path)) {
            filterChain.doFilter(request, response);
            return;
        }

        String header = request.getHeader(HEADER);

        String internalToken = request.getHeader("X-Internal-Token");

        if ("zhurong-platform-internal".equals(internalToken)) {

            // 系统内部调用认证：
            // 当请求携带合法的内部调用 Token 时，认为该请求来自系统内部服务（如 MQ 消费者或 Feign 调用）。
            // 当前仅完成认证（Authentication），暂未对接口进行细粒度权限控制。
            // 后续计划为该身份授予特定 API 权限（如 ROLE_API），并结合接口权限进行授权校验。
            // 目前部分接口仍处于未启用权限校验的状态，因此即使未授权也可能访问成功。
            Authentication auth =
                    new UsernamePasswordAuthenticationToken(
                            "api",
                            null,
                            List.of(new SimpleGrantedAuthority("ROLE_API"))
                    );

            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        if (header == null || !header.startsWith(PREFIX)) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = header.substring(PREFIX.length());

        try {

            TokenUser tokenUser = jwtProvider.parseToken(token);

            Long userId = tokenUser.getId();
            String username = tokenUser.getUsername();
            Long tenant = tokenUser.getTenantId();
            List<String> roles = tokenUser.getRoles();

            // ====================== 权限构造说明 ======================
            //
            // 这里将 JWT 中的角色列表转换为 Spring Security 的 GrantedAuthority。
            // 当前只存储角色（ROLE_XXX），用于基于角色的权限控制：
            //
            //     @PreAuthorize("hasRole('ADMIN')")
            //
            // 注意：
            // 这里只处理“角色级权限”。
            // 如果后续需要支持“接口级权限（role_api）”，
            //    必须在 JWT 中增加 permissions 字段，例如：
            //        "permissions": ["sys:user:add", "sys:user:delete"]
            //
            //    然后在这里同时将 permissions 转换为 SimpleGrantedAuthority：
            //        authorities.addAll(
            //            permissions.stream()
            //                .map(SimpleGrantedAuthority::new)
            //                .toList()
            //        );
            //
            // Gateway 层通常只做认证，不做细粒度接口权限控制。
            //    接口级权限建议在 Core/Auth 服务中使用 @PreAuthorize 进行判断。
            //
            // ==========================================================
            List<SimpleGrantedAuthority> authorities = roles.stream()
                    .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                    .toList();

            JwtUserDetails userDetails = JwtUserDetails.builder()
                    .userId(userId)
                    .username(username)
                    .tenant(tenant)
                    .roles(roles)
                    .build();

            JwtAuthenticationToken authentication =
                    new JwtAuthenticationToken(userDetails, authorities);

            SecurityContextHolder.getContext().setAuthentication(authentication);

        } catch (Exception e) {
            SecurityContextHolder.clearContext();
        }

        filterChain.doFilter(request, response);
    }

    private boolean isWhiteList(String path) {
        return List.of(SecurityConstants.WHITE_LIST).stream().anyMatch(path::startsWith);
    }
}
