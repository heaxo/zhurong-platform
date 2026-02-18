package com.ao.platform.auth.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * VO
 */
@Data
public class SysRoleVO implements Serializable {

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

}
