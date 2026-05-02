package com.zhurong.platform.security.constant;

/*
 * @Author zhurong
 * @Description SecurityConstants
 * @Date 2026/2/22 13:35
 **/
public class SecurityConstants {
    public static final String[] WHITE_LIST = {
            "/auth/login",
            "/api/auth/auth/login",
            "/staticResources",
            "/staticResources/**",
            "/custom/staticResources",
            "/custom/staticResources/**",
            "/api/custom/staticResources",
            "/api/custom/staticResources/**",
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/actuator/**",
            "/error"
    };
}
