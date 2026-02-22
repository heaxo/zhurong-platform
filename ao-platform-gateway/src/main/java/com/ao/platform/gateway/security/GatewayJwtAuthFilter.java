package com.ao.platform.gateway.security;

import com.ao.platform.security.jwt.JwtProvider;
import com.ao.platform.security.model.TokenUser;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GatewayJwtAuthFilter implements WebFilter {

    private static final String PREFIX = "Bearer ";

    private final JwtProvider jwtProvider;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {

        ServerHttpRequest request = exchange.getRequest();
        String authHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

        if (authHeader == null || !authHeader.startsWith(PREFIX)) {
            return chain.filter(exchange);
        }

        String token = authHeader.substring(PREFIX.length());

        try {

            TokenUser tokenUser = jwtProvider.parseToken(token);

            Long userId = tokenUser.getId();
            String username = tokenUser.getUsername();
            Long tenantId = tokenUser.getTenantId();
            List<String> roles = tokenUser.getRoles();

            // 构造 Spring Security 认证对象（为将来扩展预留）
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(username, null, List.of());

            // 删除客户端伪造 Header 并注入系统 Header
            ServerHttpRequest mutatedRequest = request.mutate()
                    .headers(headers -> {
                        headers.remove("X-User-Id");
                        headers.remove("X-Username");
                        headers.remove("X-Tenant-Id");
                        headers.remove("X-Roles");

                        headers.add("X-User-Id", String.valueOf(userId));
                        headers.add("X-Username", username);
                        headers.add("X-Tenant-Id", String.valueOf(tenantId));
                        headers.add("X-Roles", String.join(",", roles));
                    })
                    .build();

            return chain.filter(exchange.mutate().request(mutatedRequest).build())
                    .contextWrite(ReactiveSecurityContextHolder.withSecurityContext(
                            Mono.just(new SecurityContextImpl(authentication))
                    ));

        } catch (ExpiredJwtException e) {
            return unauthorized(exchange, "Token expired");
        } catch (JwtException e) {
            return unauthorized(exchange, "Invalid token");
        }
    }

    private Mono<Void> unauthorized(ServerWebExchange exchange, String message) {
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();
    }
}