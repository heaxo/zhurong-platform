package com.ao.platform.auth.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.TableName;

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
@TableName("sys_menu")
public class SysMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     *
     */
    private Integer id;

    /**
     *
     */
    private Integer pid;

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
    private Integer metaOrder;

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
    private LocalDateTime createTime;

}
