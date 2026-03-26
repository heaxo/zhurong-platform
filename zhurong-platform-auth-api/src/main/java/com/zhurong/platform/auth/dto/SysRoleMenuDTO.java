package com.zhurong.platform.auth.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO
 */
@Data
public class SysRoleMenuDTO implements Serializable {

    private Long id;
    private Long tenantId;
    private Boolean deleted;
    private Long createBy;
    private LocalDateTime createTime;
    private Long updateBy;
    private LocalDateTime updateTime;
    private Integer version;
    private Long roleId;
    private Long menuId;

}
