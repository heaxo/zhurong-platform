package com.zhurong.platform.auth.entity;

import com.zhurong.platform.base.model.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *
 *
 * @author heao
 * @since 2026-02-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_user")
public class SysUser extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     *
     */
    private Long tenantId;


    /**
     *
     */
    private String username;


    /**
     *
     */
    private String password;


    /**
     *
     */
    private String realName;


    /**
     *
     */
    private Long deptId;


    /**
     *
     */
    private Integer status;


    /**
     *
     */
    private LocalDateTime lastLoginTime;


    /**
     *
     */
    private String lastLoginIp;


    /**
     *
     */
    private String remark;

}
