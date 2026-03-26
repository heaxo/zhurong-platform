package com.zhurong.platform.auth.dto;

import lombok.Data;

@Data
public class SysMenuMetaDTO {
    String title;
    String icon;
    String activeIcon;
    String badgeType;
    String badge;
    String badgeVariants;
    String iframeSrc;
    String link;
    Boolean keepAlive;
    Boolean affixTab;
    Boolean hideInMenu;
    Boolean hideChildrenInMenu;
    Boolean hideInBreadcrumb;
    Boolean hideInTab;

    public SysMenuDTO to(SysMenuDTO dto) {
        dto.setMetaTitle(this.title);
        dto.setMetaIcon(this.icon);
        dto.setMetaActiveIcon(this.activeIcon);
        dto.setMetaBadgeType(this.badgeType);
        dto.setMetaBadge(this.badge);
        dto.setMetaBadgeVariants(this.badgeVariants);
        dto.setMetaIframeSrc(this.iframeSrc);
        dto.setMetaLink(this.link);
        dto.setMetaKeepAlive(this.keepAlive);
        dto.setMetaAffixTab(this.affixTab);
        dto.setMetaHideInMenu(this.hideInMenu);
        dto.setMetaHideChildrenInMenu(this.hideChildrenInMenu);
        dto.setMetaHideInBreadcrumb(this.hideInBreadcrumb);
        dto.setMetaHideInTab(this.hideInTab);
        return dto;
    }
}