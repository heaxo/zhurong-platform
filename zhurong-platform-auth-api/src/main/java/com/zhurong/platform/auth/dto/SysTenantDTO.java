package com.zhurong.platform.auth.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO
 */
@Data
public class SysTenantDTO implements Serializable {

    private Long id;
    private String name;
    private Integer status;
    private LocalDateTime expireTime;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Integer version;

}
