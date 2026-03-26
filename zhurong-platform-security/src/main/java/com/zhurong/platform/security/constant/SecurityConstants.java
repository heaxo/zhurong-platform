package com.zhurong.platform.security.constant;

/*
 * @Author a.he@lantek.com
 * @Description SecurityConstants
 * @Date 2026/2/22 13:35
 **/
public class SecurityConstants {
    public static final String[] WHITE_LIST = {
            "/auth/login",
            "/api/auth/auth/login",
            "/actuator/**",
            "/error"
    };
}
