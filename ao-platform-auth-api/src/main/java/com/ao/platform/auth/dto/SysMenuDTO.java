package com.ao.platform.auth.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO
 */
@Data
public class SysMenuDTO implements Serializable {

    private Long id;
    private Long tenantId;
    private Boolean deleted;
    private Long createBy;
    private LocalDateTime createTime;
    private Long updateBy;
    private LocalDateTime updateTime;
    private Integer version;
    private Long pid;
    private String name;
    private String type;
    private String path;
    private String activePath;
    private String component;
    private String authCode;
    private Integer status;
    private Integer sortOrder;
    private String metaTitle;
    private String metaIcon;
    private String metaActiveIcon;
    private String metaBadgeType;
    private String metaBadge;
    private String metaBadgeVariants;
    private String metaIframeSrc;
    private String metaLink;
    private Boolean metaKeepAlive;
    private Boolean metaAffixTab;
    private Boolean metaHideInMenu;
    private Boolean metaHideChildrenInMenu;
    private Boolean metaHideInBreadcrumb;
    private Boolean metaHideInTab;
    private String remark;

}
