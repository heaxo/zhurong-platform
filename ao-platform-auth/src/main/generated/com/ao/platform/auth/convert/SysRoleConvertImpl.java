package com.ao.platform.auth.convert;

import com.ao.platform.auth.dto.SysRoleDTO;
import com.ao.platform.auth.dto.SysRolePageQuery;
import com.ao.platform.auth.entity.SysRole;
import com.ao.platform.auth.vo.SysRoleVO;
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
public class SysRoleConvertImpl implements SysRoleConvert {

    @Override
    public SysRoleVO toVO(SysRole entity) {
        if ( entity == null ) {
            return null;
        }

        SysRoleVO sysRoleVO = new SysRoleVO();

        sysRoleVO.setId( entity.getId() );
        sysRoleVO.setTenantId( entity.getTenantId() );
        sysRoleVO.setDeleted( entity.getDeleted() );
        sysRoleVO.setCreateBy( entity.getCreateBy() );
        sysRoleVO.setCreateTime( entity.getCreateTime() );
        sysRoleVO.setUpdateBy( entity.getUpdateBy() );
        sysRoleVO.setUpdateTime( entity.getUpdateTime() );
        sysRoleVO.setVersion( entity.getVersion() );
        sysRoleVO.setName( entity.getName() );
        sysRoleVO.setCode( entity.getCode() );
        sysRoleVO.setStatus( entity.getStatus() );
        sysRoleVO.setDataScope( entity.getDataScope() );
        sysRoleVO.setRemark( entity.getRemark() );

        return sysRoleVO;
    }

    @Override
    public List<SysRoleVO> toVOList(List<SysRole> list) {
        if ( list == null ) {
            return null;
        }

        List<SysRoleVO> list1 = new ArrayList<SysRoleVO>( list.size() );
        for ( SysRole sysRole : list ) {
            list1.add( toVO( sysRole ) );
        }

        return list1;
    }

    @Override
    public SysRole toEntity(SysRoleDTO dto) {
        if ( dto == null ) {
            return null;
        }

        SysRole sysRole = new SysRole();

        sysRole.setId( dto.getId() );
        sysRole.setDeleted( dto.getDeleted() );
        sysRole.setVersion( dto.getVersion() );
        sysRole.setCreateBy( dto.getCreateBy() );
        sysRole.setCreateTime( dto.getCreateTime() );
        sysRole.setUpdateBy( dto.getUpdateBy() );
        sysRole.setUpdateTime( dto.getUpdateTime() );
        sysRole.setTenantId( dto.getTenantId() );
        sysRole.setName( dto.getName() );
        sysRole.setCode( dto.getCode() );
        sysRole.setStatus( dto.getStatus() );
        sysRole.setDataScope( dto.getDataScope() );
        sysRole.setRemark( dto.getRemark() );

        return sysRole;
    }

    @Override
    public SysRole toEntity(SysRolePageQuery dto) {
        if ( dto == null ) {
            return null;
        }

        SysRole sysRole = new SysRole();

        sysRole.setVersion( dto.getVersion() );
        sysRole.setCreateBy( dto.getCreateBy() );
        sysRole.setUpdateBy( dto.getUpdateBy() );
        sysRole.setName( dto.getName() );
        sysRole.setCode( dto.getCode() );
        sysRole.setStatus( dto.getStatus() );
        sysRole.setDataScope( dto.getDataScope() );
        sysRole.setRemark( dto.getRemark() );

        return sysRole;
    }

    @Override
    public void updateFromDTO(SysRoleDTO dto, SysRole entity) {
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
        if ( dto.getName() != null ) {
            entity.setName( dto.getName() );
        }
        if ( dto.getCode() != null ) {
            entity.setCode( dto.getCode() );
        }
        if ( dto.getStatus() != null ) {
            entity.setStatus( dto.getStatus() );
        }
        if ( dto.getDataScope() != null ) {
            entity.setDataScope( dto.getDataScope() );
        }
        if ( dto.getRemark() != null ) {
            entity.setRemark( dto.getRemark() );
        }
    }
}
