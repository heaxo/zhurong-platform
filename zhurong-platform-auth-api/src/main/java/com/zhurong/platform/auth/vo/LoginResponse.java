package com.zhurong.platform.auth.vo;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class LoginResponse {

    private String id;

    private String username;

    private String realName;

    private List<String> roles;

    private String accessToken;
}
