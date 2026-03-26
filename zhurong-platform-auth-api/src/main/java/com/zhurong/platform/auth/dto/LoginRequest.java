package com.zhurong.platform.auth.dto;

import lombok.Data;

@Data
public class LoginRequest {

    private String selectAccount; // 多租户入口

    private String username;

    private String password;

    private Boolean captcha;
}
