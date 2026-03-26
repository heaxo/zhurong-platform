package com.zhurong.platform.auth.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * VO
 */
@Data
public class SysUserVO implements Serializable {

    private String id;
    private String tenantId;
    private Boolean deleted;
    private String createBy;
    private LocalDateTime createTime;
    private String updateBy;
    private LocalDateTime updateTime;
    private Integer version;
    private String username;
    private String realName;
    private String deptId;
    private Integer status;
    private LocalDateTime lastLoginTime;
    private String lastLoginIp;
    private String remark;
    private List<String> roles;

}
