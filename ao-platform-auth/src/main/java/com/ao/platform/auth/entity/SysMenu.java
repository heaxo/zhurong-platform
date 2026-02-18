package com.ao.platform.auth.entity;

import com.ao.platform.base.model.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 *
 *
 * @author heao
 * @since 2026-02-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_menu")
public class SysMenu extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     *
     */
    private Long tenantId;


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

}
