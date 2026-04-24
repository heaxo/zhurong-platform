package com.zhurong.platform.auth.convert;

import com.zhurong.platform.auth.dto.SysMenuDTO;
import com.zhurong.platform.auth.dto.SysMenuPageQuery;
import com.zhurong.platform.auth.entity.SysMenu;
import com.zhurong.platform.auth.vo.SysMenuVO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-04-24T19:07:24+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.10 (Eclipse Adoptium)"
)
@Component
public class SysMenuConvertImpl implements SysMenuConvert {

    @Override
    public SysMenuVO toVO(SysMenu entity) {
        if ( entity == null ) {
            return null;
        }

        SysMenuVO sysMenuVO = new SysMenuVO();

        if ( entity.getId() != null ) {
            sysMenuVO.setId( String.valueOf( entity.getId() ) );
        }
        if ( entity.getTenantId() != null ) {
            sysMenuVO.setTenantId( String.valueOf( entity.getTenantId() ) );
        }
        sysMenuVO.setDeleted( entity.getDeleted() );
        if ( entity.getCreateBy() != null ) {
            sysMenuVO.setCreateBy( String.valueOf( entity.getCreateBy() ) );
        }
        sysMenuVO.setCreateTime( entity.getCreateTime() );
        if ( entity.getUpdateBy() != null ) {
            sysMenuVO.setUpdateBy( String.valueOf( entity.getUpdateBy() ) );
        }
        sysMenuVO.setUpdateTime( entity.getUpdateTime() );
        sysMenuVO.setVersion( entity.getVersion() );
        if ( entity.getPid() != null ) {
            sysMenuVO.setPid( String.valueOf( entity.getPid() ) );
        }
        sysMenuVO.setName( entity.getName() );
        sysMenuVO.setType( entity.getType() );
        sysMenuVO.setPath( entity.getPath() );
        sysMenuVO.setActivePath( entity.getActivePath() );
        sysMenuVO.setComponent( entity.getComponent() );
        sysMenuVO.setAuthCode( entity.getAuthCode() );
        sysMenuVO.setStatus( entity.getStatus() );
        sysMenuVO.setSortOrder( entity.getSortOrder() );
        sysMenuVO.setMetaTitle( entity.getMetaTitle() );
        sysMenuVO.setMetaIcon( entity.getMetaIcon() );
        sysMenuVO.setMetaActiveIcon( entity.getMetaActiveIcon() );
        sysMenuVO.setMetaBadgeType( entity.getMetaBadgeType() );
        sysMenuVO.setMetaBadge( entity.getMetaBadge() );
        sysMenuVO.setMetaBadgeVariants( entity.getMetaBadgeVariants() );
        sysMenuVO.setMetaIframeSrc( entity.getMetaIframeSrc() );
        sysMenuVO.setMetaLink( entity.getMetaLink() );
        sysMenuVO.setMetaKeepAlive( entity.getMetaKeepAlive() );
        sysMenuVO.setMetaAffixTab( entity.getMetaAffixTab() );
        sysMenuVO.setMetaHideInMenu( entity.getMetaHideInMenu() );
        sysMenuVO.setMetaHideChildrenInMenu( entity.getMetaHideChildrenInMenu() );
        sysMenuVO.setMetaHideInBreadcrumb( entity.getMetaHideInBreadcrumb() );
        sysMenuVO.setMetaHideInTab( entity.getMetaHideInTab() );
        sysMenuVO.setRemark( entity.getRemark() );

        return sysMenuVO;
    }

    @Override
    public List<SysMenuVO> toVO(List<SysMenu> entitys) {
        if ( entitys == null ) {
            return null;
        }

        List<SysMenuVO> list = new ArrayList<SysMenuVO>( entitys.size() );
        for ( SysMenu sysMenu : entitys ) {
            list.add( toVO( sysMenu ) );
        }

        return list;
    }

    @Override
    public List<SysMenuVO> toVOList(List<SysMenu> list) {
        if ( list == null ) {
            return null;
        }

        List<SysMenuVO> list1 = new ArrayList<SysMenuVO>( list.size() );
        for ( SysMenu sysMenu : list ) {
            list1.add( toVO( sysMenu ) );
        }

        return list1;
    }

    @Override
    public SysMenu toEntity(SysMenuDTO dto) {
        if ( dto == null ) {
            return null;
        }

        SysMenu sysMenu = new SysMenu();

        sysMenu.setId( dto.getId() );
        sysMenu.setDeleted( dto.getDeleted() );
        sysMenu.setVersion( dto.getVersion() );
        sysMenu.setCreateBy( dto.getCreateBy() );
        sysMenu.setCreateTime( dto.getCreateTime() );
        sysMenu.setUpdateBy( dto.getUpdateBy() );
        sysMenu.setUpdateTime( dto.getUpdateTime() );
        sysMenu.setTenantId( dto.getTenantId() );
        sysMenu.setPid( dto.getPid() );
        sysMenu.setName( dto.getName() );
        sysMenu.setType( dto.getType() );
        sysMenu.setPath( dto.getPath() );
        sysMenu.setActivePath( dto.getActivePath() );
        sysMenu.setComponent( dto.getComponent() );
        sysMenu.setAuthCode( dto.getAuthCode() );
        sysMenu.setStatus( dto.getStatus() );
        sysMenu.setSortOrder( dto.getSortOrder() );
        sysMenu.setMetaTitle( dto.getMetaTitle() );
        sysMenu.setMetaIcon( dto.getMetaIcon() );
        sysMenu.setMetaActiveIcon( dto.getMetaActiveIcon() );
        sysMenu.setMetaBadgeType( dto.getMetaBadgeType() );
        sysMenu.setMetaBadge( dto.getMetaBadge() );
        sysMenu.setMetaBadgeVariants( dto.getMetaBadgeVariants() );
        sysMenu.setMetaIframeSrc( dto.getMetaIframeSrc() );
        sysMenu.setMetaLink( dto.getMetaLink() );
        sysMenu.setMetaKeepAlive( dto.getMetaKeepAlive() );
        sysMenu.setMetaAffixTab( dto.getMetaAffixTab() );
        sysMenu.setMetaHideInMenu( dto.getMetaHideInMenu() );
        sysMenu.setMetaHideChildrenInMenu( dto.getMetaHideChildrenInMenu() );
        sysMenu.setMetaHideInBreadcrumb( dto.getMetaHideInBreadcrumb() );
        sysMenu.setMetaHideInTab( dto.getMetaHideInTab() );
        sysMenu.setRemark( dto.getRemark() );

        return sysMenu;
    }

    @Override
    public SysMenu toEntity(SysMenuPageQuery dto) {
        if ( dto == null ) {
            return null;
        }

        SysMenu sysMenu = new SysMenu();

        sysMenu.setVersion( dto.getVersion() );
        sysMenu.setCreateBy( dto.getCreateBy() );
        sysMenu.setUpdateBy( dto.getUpdateBy() );
        sysMenu.setPid( dto.getPid() );
        sysMenu.setName( dto.getName() );
        sysMenu.setType( dto.getType() );
        sysMenu.setPath( dto.getPath() );
        sysMenu.setActivePath( dto.getActivePath() );
        sysMenu.setComponent( dto.getComponent() );
        sysMenu.setAuthCode( dto.getAuthCode() );
        sysMenu.setStatus( dto.getStatus() );
        sysMenu.setSortOrder( dto.getSortOrder() );
        sysMenu.setMetaTitle( dto.getMetaTitle() );
        sysMenu.setMetaIcon( dto.getMetaIcon() );
        sysMenu.setMetaActiveIcon( dto.getMetaActiveIcon() );
        sysMenu.setMetaBadgeType( dto.getMetaBadgeType() );
        sysMenu.setMetaBadge( dto.getMetaBadge() );
        sysMenu.setMetaBadgeVariants( dto.getMetaBadgeVariants() );
        sysMenu.setMetaIframeSrc( dto.getMetaIframeSrc() );
        sysMenu.setMetaLink( dto.getMetaLink() );
        sysMenu.setMetaKeepAlive( dto.getMetaKeepAlive() );
        sysMenu.setMetaAffixTab( dto.getMetaAffixTab() );
        sysMenu.setMetaHideInMenu( dto.getMetaHideInMenu() );
        sysMenu.setMetaHideChildrenInMenu( dto.getMetaHideChildrenInMenu() );
        sysMenu.setMetaHideInBreadcrumb( dto.getMetaHideInBreadcrumb() );
        sysMenu.setMetaHideInTab( dto.getMetaHideInTab() );
        sysMenu.setRemark( dto.getRemark() );

        return sysMenu;
    }

    @Override
    public void updateFromDTO(SysMenuDTO dto, SysMenu entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getId() != null ) {
            entity.setId( dto.getId() );
        }
        if ( dto.getDeleted() != null ) {
            entity.setDeleted( dto.getDeleted() );
        }
        if ( dto.getVersion() != null ) {
            entity.setVersion( dto.getVersion() );
        }
        if ( dto.getCreateBy() != null ) {
            entity.setCreateBy( dto.getCreateBy() );
        }
        if ( dto.getCreateTime() != null ) {
            entity.setCreateTime( dto.getCreateTime() );
        }
        if ( dto.getUpdateBy() != null ) {
            entity.setUpdateBy( dto.getUpdateBy() );
        }
        if ( dto.getUpdateTime() != null ) {
            entity.setUpdateTime( dto.getUpdateTime() );
        }
        if ( dto.getTenantId() != null ) {
            entity.setTenantId( dto.getTenantId() );
        }
        if ( dto.getPid() != null ) {
            entity.setPid( dto.getPid() );
        }
        if ( dto.getName() != null ) {
            entity.setName( dto.getName() );
        }
        if ( dto.getType() != null ) {
            entity.setType( dto.getType() );
        }
        if ( dto.getPath() != null ) {
            entity.setPath( dto.getPath() );
        }
        if ( dto.getActivePath() != null ) {
            entity.setActivePath( dto.getActivePath() );
        }
        if ( dto.getComponent() != null ) {
            entity.setComponent( dto.getComponent() );
        }
        if ( dto.getAuthCode() != null ) {
            entity.setAuthCode( dto.getAuthCode() );
        }
        if ( dto.getStatus() != null ) {
            entity.setStatus( dto.getStatus() );
        }
        if ( dto.getSortOrder() != null ) {
            entity.setSortOrder( dto.getSortOrder() );
        }
        if ( dto.getMetaTitle() != null ) {
            entity.setMetaTitle( dto.getMetaTitle() );
        }
        if ( dto.getMetaIcon() != null ) {
            entity.setMetaIcon( dto.getMetaIcon() );
        }
        if ( dto.getMetaActiveIcon() != null ) {
            entity.setMetaActiveIcon( dto.getMetaActiveIcon() );
        }
        if ( dto.getMetaBadgeType() != null ) {
            entity.setMetaBadgeType( dto.getMetaBadgeType() );
        }
        if ( dto.getMetaBadge() != null ) {
            entity.setMetaBadge( dto.getMetaBadge() );
        }
        if ( dto.getMetaBadgeVariants() != null ) {
            entity.setMetaBadgeVariants( dto.getMetaBadgeVariants() );
        }
        if ( dto.getMetaIframeSrc() != null ) {
            entity.setMetaIframeSrc( dto.getMetaIframeSrc() );
        }
        if ( dto.getMetaLink() != null ) {
            entity.setMetaLink( dto.getMetaLink() );
        }
        if ( dto.getMetaKeepAlive() != null ) {
            entity.setMetaKeepAlive( dto.getMetaKeepAlive() );
        }
        if ( dto.getMetaAffixTab() != null ) {
            entity.setMetaAffixTab( dto.getMetaAffixTab() );
        }
        if ( dto.getMetaHideInMenu() != null ) {
            entity.setMetaHideInMenu( dto.getMetaHideInMenu() );
        }
        if ( dto.getMetaHideChildrenInMenu() != null ) {
            entity.setMetaHideChildrenInMenu( dto.getMetaHideChildrenInMenu() );
        }
        if ( dto.getMetaHideInBreadcrumb() != null ) {
            entity.setMetaHideInBreadcrumb( dto.getMetaHideInBreadcrumb() );
        }
        if ( dto.getMetaHideInTab() != null ) {
            entity.setMetaHideInTab( dto.getMetaHideInTab() );
        }
        if ( dto.getRemark() != null ) {
            entity.setRemark( dto.getRemark() );
        }
    }
}
