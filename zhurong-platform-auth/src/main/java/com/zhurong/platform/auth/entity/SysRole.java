package com.zhurong.platform.auth.entity;

import com.zhurong.platform.base.model.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 *
 *
 * @author me
 * @since 2026-02-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_role")
public class SysRole extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     *
     */
    private Long tenantId;


    /**
     *
     */
    private String name;


    /**
     *
     */
    private String code;


    /**
     *
     */
    private Integer status;


    /**
     *
     */
    private Integer dataScope;


    /**
     *
     */
    private String remark;

}
