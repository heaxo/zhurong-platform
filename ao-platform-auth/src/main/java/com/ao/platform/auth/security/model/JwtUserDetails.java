package com.ao.platform.auth.security.model;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class JwtUserDetails {

    private Long userId;
    private String username;
    private String realName;
    private Long tenantId;
    private String tenantCode;
    private List<String> roles;
    private List<String> permissions;
    private Boolean superAdmin;
    private Integer tokenVersion;
    private Long tenant;
}
