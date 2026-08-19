package com.zhurong.platform.auth.entity;

import com.zhurong.platform.base.model.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *
 *
 * @author me
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
     * 与该登录账号绑定的 Windows 客户端主机标识，用于定向投递本机 Lantek 任务。
     */
    @TableField("client_id")
    private String clientId;


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
