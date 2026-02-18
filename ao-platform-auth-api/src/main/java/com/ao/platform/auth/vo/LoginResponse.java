package com.ao.platform.auth.vo;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class LoginResponse {

    private Long id;

    private String username;

    private String realName;

    private List<String> roles;

    private String accessToken;
}
