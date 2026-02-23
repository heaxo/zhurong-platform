package com.ao.platform.auth.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * VO
 */
@Data
public class SysRoleVO implements Serializable {

    private String id;
    private String tenantId;
    private Boolean deleted;
    private String createBy;
    private LocalDateTime createTime;
    private String updateBy;
    private LocalDateTime updateTime;
    private Integer version;
    private String name;
    private String code;
    private Integer status;
    private Integer dataScope;
    private String remark;

}
