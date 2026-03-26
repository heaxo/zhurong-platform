package com.zhurong.platform.auth.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO
 */
@Data
public class SysRoleDTO implements Serializable {

    private Long id;
    private Long tenantId;
    private Boolean deleted;
    private Long createBy;
    private LocalDateTime createTime;
    private Long updateBy;
    private LocalDateTime updateTime;
    private Integer version;
    private String name;
    private String code;
    private Integer status;
    private Integer dataScope;
    private String remark;
    private List<String> permissions;

}
