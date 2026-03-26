package com.zhurong.platform.auth.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class CreateUserRequest {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String realName;

    private Long deptId;

    private Integer status = 1;

    private String remark;

    private List<Long> roleIds;
}
