package com.zhurong.platform.auth.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class CreateUserRequest {

    @NotBlank
    @Size(max = 100)
    private String username;

    @NotBlank
    @Size(max = 255)
    private String password;

    @NotBlank
    @Size(max = 100)
    private String realName;

    @Size(max = 128)
    private String clientId;

    private Long deptId;

    @Min(0)
    @Max(1)
    private Integer status = 1;

    @Size(max = 500)
    private String remark;

    private List<Long> roleIds;
}
