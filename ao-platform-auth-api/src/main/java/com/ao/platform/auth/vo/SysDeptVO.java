package com.ao.platform.auth.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * VO
 */
@Data
public class SysDeptVO implements Serializable {

    private String id;
    private String tenantId;
    private Boolean deleted;
    private String createBy;
    private LocalDateTime createTime;
    private String updateBy;
    private LocalDateTime updateTime;
    private Integer version;
    private String pid;
    private String name;
    private Integer status;
    private Integer sortOrder;
    private String remark;
    private List<SysDeptVO> children;

}
