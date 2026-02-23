package com.ao.platform.auth.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * VO
 */
@Data
public class SysMenuVO implements Serializable {

    private String id;
    private String tenantId;
    private Boolean deleted;
    private String createBy;
    private LocalDateTime createTime;
    private String updateBy;
    private LocalDateTime updateTime;
    private Integer version;
    private String pid;
    private String name;
    private String type;
    private String path;
    private String activePath;
    private String redirect;
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
    private SysMenuMetaVO meta;
    private List<SysMenuVO> children;
    public String getRedirect(){
        return this.activePath;
    }
}
