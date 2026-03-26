package com.zhurong.platform.security.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TokenUser {

    private Long id;

    private String username;

    private Long tenantId;

    private List<String> roles;
}