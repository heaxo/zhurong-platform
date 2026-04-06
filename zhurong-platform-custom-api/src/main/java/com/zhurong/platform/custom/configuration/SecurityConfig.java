package com.zhurong.platform.custom.configuration;

import com.zhurong.platform.security.filter.ServletJwtAuthenticationFilter;
import com.zhurong.platform.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtProvider jwtProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                //必须放行error，不然gateway响应的异常是403（500返回403）
                                "/error",
                                "/actuator/**").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(
                        new ServletJwtAuthenticationFilter(jwtProvider),
                        UsernamePasswordAuthenticationFilter.class
                )
                .formLogin(form -> form.disable())
                .httpBasic(basic -> basic.disable())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );

        return http.build();
    }
}