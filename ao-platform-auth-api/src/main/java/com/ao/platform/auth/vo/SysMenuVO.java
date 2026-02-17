package com.ao.platform.auth.vo;

import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
*  VO
*/
@Data
public class SysMenuVO implements Serializable {

    private Integer id;
    private Integer pid;
    private String name;
    private String type;
    private String path;
    private String activePath;
    private String component;
    private String authCode;
    private Integer status;
    private String metaTitle;
    private String metaIcon;
    private String metaActiveIcon;
    private Integer metaOrder;
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
    private LocalDateTime createTime;

}
