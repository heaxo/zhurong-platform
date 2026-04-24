package com.zhurong.platform.auth.convert;

import com.zhurong.platform.auth.dto.SysRoleMenuDTO;
import com.zhurong.platform.auth.dto.SysRoleMenuPageQuery;
import com.zhurong.platform.auth.entity.SysRoleMenu;
import com.zhurong.platform.auth.vo.SysRoleMenuVO;
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
public class SysRoleMenuConvertImpl implements SysRoleMenuConvert {

    @Override
    public SysRoleMenuVO toVO(SysRoleMenu entity) {
        if ( entity == null ) {
            return null;
        }

        SysRoleMenuVO sysRoleMenuVO = new SysRoleMenuVO();

        if ( entity.getId() != null ) {
            sysRoleMenuVO.setId( String.valueOf( entity.getId() ) );
        }
        if ( entity.getTenantId() != null ) {
            sysRoleMenuVO.setTenantId( String.valueOf( entity.getTenantId() ) );
        }
        sysRoleMenuVO.setDeleted( entity.getDeleted() );
        if ( entity.getCreateBy() != null ) {
            sysRoleMenuVO.setCreateBy( String.valueOf( entity.getCreateBy() ) );
        }
        sysRoleMenuVO.setCreateTime( entity.getCreateTime() );
        if ( entity.getUpdateBy() != null ) {
            sysRoleMenuVO.setUpdateBy( String.valueOf( entity.getUpdateBy() ) );
        }
        sysRoleMenuVO.setUpdateTime( entity.getUpdateTime() );
        sysRoleMenuVO.setVersion( entity.getVersion() );
        if ( entity.getRoleId() != null ) {
            sysRoleMenuVO.setRoleId( String.valueOf( entity.getRoleId() ) );
        }
        if ( entity.getMenuId() != null ) {
            sysRoleMenuVO.setMenuId( String.valueOf( entity.getMenuId() ) );
        }

        return sysRoleMenuVO;
    }

    @Override
    public List<SysRoleMenuVO> toVOList(List<SysRoleMenu> list) {
        if ( list == null ) {
            return null;
        }

        List<SysRoleMenuVO> list1 = new ArrayList<SysRoleMenuVO>( list.size() );
        for ( SysRoleMenu sysRoleMenu : list ) {
            list1.add( toVO( sysRoleMenu ) );
        }

        return list1;
    }

    @Override
    public SysRoleMenu toEntity(SysRoleMenuDTO dto) {
        if ( dto == null ) {
            return null;
        }

        SysRoleMenu sysRoleMenu = new SysRoleMenu();

        sysRoleMenu.setId( dto.getId() );
        sysRoleMenu.setDeleted( dto.getDeleted() );
        sysRoleMenu.setVersion( dto.getVersion() );
        sysRoleMenu.setCreateBy( dto.getCreateBy() );
        sysRoleMenu.setCreateTime( dto.getCreateTime() );
        sysRoleMenu.setUpdateBy( dto.getUpdateBy() );
        sysRoleMenu.setUpdateTime( dto.getUpdateTime() );
        sysRoleMenu.setTenantId( dto.getTenantId() );
        sysRoleMenu.setRoleId( dto.getRoleId() );
        sysRoleMenu.setMenuId( dto.getMenuId() );

        return sysRoleMenu;
    }

    @Override
    public SysRoleMenu toEntity(SysRoleMenuPageQuery dto) {
        if ( dto == null ) {
            return null;
        }

        SysRoleMenu sysRoleMenu = new SysRoleMenu();

        sysRoleMenu.setVersion( dto.getVersion() );
        sysRoleMenu.setCreateBy( dto.getCreateBy() );
        sysRoleMenu.setUpdateBy( dto.getUpdateBy() );
        sysRoleMenu.setRoleId( dto.getRoleId() );
        sysRoleMenu.setMenuId( dto.getMenuId() );

        return sysRoleMenu;
    }

    @Override
    public void updateFromDTO(SysRoleMenuDTO dto, SysRoleMenu entity) {
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
        if ( dto.getRoleId() != null ) {
            entity.setRoleId( dto.getRoleId() );
        }
        if ( dto.getMenuId() != null ) {
            entity.setMenuId( dto.getMenuId() );
        }
    }
}
