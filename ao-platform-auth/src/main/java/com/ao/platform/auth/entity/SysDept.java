package com.ao.platform.auth.entity;

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
@TableName("sys_dept")
public class SysDept implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     *
     */
    private Object id;

    /**
     *
     */
    private Object pid;

    /**
     *
     */
    private String name;

    /**
     *
     */
    private Integer status;

    /**
     *
     */
    private LocalDateTime createTime;

    /**
     *
     */
    private String remark;

}
