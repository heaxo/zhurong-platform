package com.ao.platform.auth.dto;

import com.ao.platform.base.api.BasePageQuery;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * 分页查询对象
 */
@Getter
@Setter
public class SysRolePageQuery extends BasePageQuery {


    /**
     *
     */
    private Long createBy;


    /**
     *
     */
    private Long updateBy;


    /**
     *
     */
    private Integer version;


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


    /**
     * 创建时间开始
     */
    private LocalDateTime beginCreateTime;

    /**
     * 创建时间结束
     */
    private LocalDateTime endCreateTime;
}
