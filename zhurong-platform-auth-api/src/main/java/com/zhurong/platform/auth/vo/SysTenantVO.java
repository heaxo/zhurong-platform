package com.zhurong.platform.auth.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * VO
 */
@Data
public class SysTenantVO implements Serializable {

    private String id;
    private String name;
    private Integer status;
    private LocalDateTime expireTime;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Integer version;

}
