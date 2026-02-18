package com.ao.platform.auth.convert;

import com.ao.platform.auth.dto.SysUserRoleDTO;
import com.ao.platform.auth.dto.SysUserRolePageQuery;
import com.ao.platform.auth.entity.SysUserRole;
import com.ao.platform.auth.vo.SysUserRoleVO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-18T23:35:29+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.10 (Eclipse Adoptium)"
)
@Component
public class SysUserRoleConvertImpl implements SysUserRoleConvert {

    @Override
    public SysUserRoleVO toVO(SysUserRole entity) {
        if ( entity == null ) {
            return null;
        }

        SysUserRoleVO sysUserRoleVO = new SysUserRoleVO();

        sysUserRoleVO.setId( entity.getId() );
        sysUserRoleVO.setTenantId( entity.getTenantId() );
        sysUserRoleVO.setDeleted( entity.getDeleted() );
        sysUserRoleVO.setCreateBy( entity.getCreateBy() );
        sysUserRoleVO.setCreateTime( entity.getCreateTime() );
        sysUserRoleVO.setUpdateBy( entity.getUpdateBy() );
        sysUserRoleVO.setUpdateTime( entity.getUpdateTime() );
        sysUserRoleVO.setVersion( entity.getVersion() );
        sysUserRoleVO.setUserId( entity.getUserId() );
        sysUserRoleVO.setRoleId( entity.getRoleId() );

        return sysUserRoleVO;
    }

    @Override
    public List<SysUserRoleVO> toVOList(List<SysUserRole> list) {
        if ( list == null ) {
            return null;
        }

        List<SysUserRoleVO> list1 = new ArrayList<SysUserRoleVO>( list.size() );
        for ( SysUserRole sysUserRole : list ) {
            list1.add( toVO( sysUserRole ) );
        }

        return list1;
    }

    @Override
    public SysUserRole toEntity(SysUserRoleDTO dto) {
        if ( dto == null ) {
            return null;
        }

        SysUserRole sysUserRole = new SysUserRole();

        sysUserRole.setId( dto.getId() );
        sysUserRole.setDeleted( dto.getDeleted() );
        sysUserRole.setVersion( dto.getVersion() );
        sysUserRole.setCreateBy( dto.getCreateBy() );
        sysUserRole.setCreateTime( dto.getCreateTime() );
        sysUserRole.setUpdateBy( dto.getUpdateBy() );
        sysUserRole.setUpdateTime( dto.getUpdateTime() );
        sysUserRole.setTenantId( dto.getTenantId() );
        sysUserRole.setUserId( dto.getUserId() );
        sysUserRole.setRoleId( dto.getRoleId() );

        return sysUserRole;
    }

    @Override
    public SysUserRole toEntity(SysUserRolePageQuery dto) {
        if ( dto == null ) {
            return null;
        }

        SysUserRole sysUserRole = new SysUserRole();

        sysUserRole.setVersion( dto.getVersion() );
        sysUserRole.setCreateBy( dto.getCreateBy() );
        sysUserRole.setUpdateBy( dto.getUpdateBy() );
        sysUserRole.setUserId( dto.getUserId() );
        sysUserRole.setRoleId( dto.getRoleId() );

        return sysUserRole;
    }

    @Override
    public void updateFromDTO(SysUserRoleDTO dto, SysUserRole entity) {
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
        if ( dto.getUserId() != null ) {
            entity.setUserId( dto.getUserId() );
        }
        if ( dto.getRoleId() != null ) {
            entity.setRoleId( dto.getRoleId() );
        }
    }
}
