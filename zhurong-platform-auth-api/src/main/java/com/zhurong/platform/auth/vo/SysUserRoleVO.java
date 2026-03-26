package com.zhurong.platform.auth.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * VO
 */
@Data
public class SysUserRoleVO implements Serializable {

    private String id;
    private String tenantId;
    private Boolean deleted;
    private String createBy;
    private LocalDateTime createTime;
    private String updateBy;
    private LocalDateTime updateTime;
    private Integer version;
    private String userId;
    private String roleId;

}
