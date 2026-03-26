package com.zhurong.platform.auth.vo;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class SysMenuMetaVO {

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

    public static SysMenuMetaVO from(SysMenuVO menuVO) {
        return SysMenuMetaVO.builder()
                .title(menuVO.getMetaTitle())
                .icon(menuVO.getMetaIcon())
                .activeIcon(menuVO.getMetaActiveIcon())
                .badgeType(menuVO.getMetaBadgeType())
                .badge(menuVO.getMetaBadge())
                .badgeVariants(menuVO.getMetaBadgeVariants())
                .iframeSrc(menuVO.getMetaIframeSrc())
                .link(menuVO.getMetaLink())
                .keepAlive(menuVO.getMetaKeepAlive())
                .affixTab(menuVO.getMetaAffixTab())
                .hideInMenu(menuVO.getMetaHideInMenu())
                .hideChildrenInMenu(menuVO.getMetaHideChildrenInMenu())
                .hideInBreadcrumb(menuVO.getMetaHideInBreadcrumb())
                .hideInTab(menuVO.getMetaHideInTab())
                .build();
    }
}