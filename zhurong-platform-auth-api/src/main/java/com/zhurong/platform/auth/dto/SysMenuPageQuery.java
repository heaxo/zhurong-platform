package com.zhurong.platform.auth.dto;

import com.zhurong.platform.base.model.BasePageQuery;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * 分页查询对象
 */
@Getter
@Setter
public class SysMenuPageQuery extends BasePageQuery {


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
    private Long pid;


    /**
     *
     */
    private String name;


    /**
     *
     */
    private String type;


    /**
     *
     */
    private String path;


    /**
     *
     */
    private String activePath;


    /**
     *
     */
    private String component;


    /**
     *
     */
    private String authCode;


    /**
     *
     */
    private Integer status;


    /**
     *
     */
    private Integer sortOrder;


    /**
     *
     */
    private String metaTitle;


    /**
     *
     */
    private String metaIcon;


    /**
     *
     */
    private String metaActiveIcon;


    /**
     *
     */
    private String metaBadgeType;


    /**
     *
     */
    private String metaBadge;


    /**
     *
     */
    private String metaBadgeVariants;


    /**
     *
     */
    private String metaIframeSrc;


    /**
     *
     */
    private String metaLink;


    /**
     *
     */
    private Boolean metaKeepAlive;


    /**
     *
     */
    private Boolean metaAffixTab;


    /**
     *
     */
    private Boolean metaHideInMenu;


    /**
     *
     */
    private Boolean metaHideChildrenInMenu;


    /**
     *
     */
    private Boolean metaHideInBreadcrumb;


    /**
     *
     */
    private Boolean metaHideInTab;


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
