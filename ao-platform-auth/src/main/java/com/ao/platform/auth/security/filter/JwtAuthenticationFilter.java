package com.ao.platform.auth.security.filter;

import com.ao.platform.auth.configuration.SecurityConfig;
import com.ao.platform.auth.security.jwt.JwtAuthenticationToken;
import com.ao.platform.auth.security.jwt.JwtProvider;
import com.ao.platform.auth.security.model.JwtUserDetails;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

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

        if (header == null || !header.startsWith(PREFIX)) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = header.substring(PREFIX.length());

        try {

            Claims claims = jwtProvider.parseToken(token);

            Long userId = claims.get("id", Long.class);
            String username = claims.get("username", String.class);
            Long tenant = claims.get("tenant", Long.class);
            List<String> roles = claims.get("roles", List.class);

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
        return List.of(SecurityConfig.WHITE_LIST).stream().anyMatch(path::startsWith);
    }
}
