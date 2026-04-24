package com.zhurong.platform.auth.convert;

import com.zhurong.platform.auth.dto.CreateUserRequest;
import com.zhurong.platform.auth.dto.SysUserDTO;
import com.zhurong.platform.auth.dto.SysUserPageQuery;
import com.zhurong.platform.auth.entity.SysUser;
import com.zhurong.platform.auth.vo.SysUserVO;
import com.zhurong.platform.security.model.TokenUser;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-04-24T19:07:23+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.10 (Eclipse Adoptium)"
)
@Component
public class SysUserConvertImpl implements SysUserConvert {

    @Override
    public SysUserVO toVO(SysUser entity) {
        if ( entity == null ) {
            return null;
        }

        SysUserVO sysUserVO = new SysUserVO();

        if ( entity.getId() != null ) {
            sysUserVO.setId( String.valueOf( entity.getId() ) );
        }
        if ( entity.getTenantId() != null ) {
            sysUserVO.setTenantId( String.valueOf( entity.getTenantId() ) );
        }
        sysUserVO.setDeleted( entity.getDeleted() );
        if ( entity.getCreateBy() != null ) {
            sysUserVO.setCreateBy( String.valueOf( entity.getCreateBy() ) );
        }
        sysUserVO.setCreateTime( entity.getCreateTime() );
        if ( entity.getUpdateBy() != null ) {
            sysUserVO.setUpdateBy( String.valueOf( entity.getUpdateBy() ) );
        }
        sysUserVO.setUpdateTime( entity.getUpdateTime() );
        sysUserVO.setVersion( entity.getVersion() );
        sysUserVO.setUsername( entity.getUsername() );
        sysUserVO.setRealName( entity.getRealName() );
        if ( entity.getDeptId() != null ) {
            sysUserVO.setDeptId( String.valueOf( entity.getDeptId() ) );
        }
        sysUserVO.setStatus( entity.getStatus() );
        sysUserVO.setLastLoginTime( entity.getLastLoginTime() );
        sysUserVO.setLastLoginIp( entity.getLastLoginIp() );
        sysUserVO.setRemark( entity.getRemark() );

        return sysUserVO;
    }

    @Override
    public List<SysUserVO> toVOList(List<SysUser> list) {
        if ( list == null ) {
            return null;
        }

        List<SysUserVO> list1 = new ArrayList<SysUserVO>( list.size() );
        for ( SysUser sysUser : list ) {
            list1.add( toVO( sysUser ) );
        }

        return list1;
    }

    @Override
    public SysUser toEntity(SysUserDTO dto) {
        if ( dto == null ) {
            return null;
        }

        SysUser sysUser = new SysUser();

        sysUser.setId( dto.getId() );
        sysUser.setDeleted( dto.getDeleted() );
        sysUser.setVersion( dto.getVersion() );
        sysUser.setCreateBy( dto.getCreateBy() );
        sysUser.setCreateTime( dto.getCreateTime() );
        sysUser.setUpdateBy( dto.getUpdateBy() );
        sysUser.setUpdateTime( dto.getUpdateTime() );
        sysUser.setTenantId( dto.getTenantId() );
        sysUser.setUsername( dto.getUsername() );
        sysUser.setPassword( dto.getPassword() );
        sysUser.setRealName( dto.getRealName() );
        sysUser.setDeptId( dto.getDeptId() );
        sysUser.setStatus( dto.getStatus() );
        sysUser.setLastLoginTime( dto.getLastLoginTime() );
        sysUser.setLastLoginIp( dto.getLastLoginIp() );
        sysUser.setRemark( dto.getRemark() );

        return sysUser;
    }

    @Override
    public SysUser toEntity(SysUserPageQuery dto) {
        if ( dto == null ) {
            return null;
        }

        SysUser sysUser = new SysUser();

        sysUser.setVersion( dto.getVersion() );
        sysUser.setCreateBy( dto.getCreateBy() );
        sysUser.setUpdateBy( dto.getUpdateBy() );
        sysUser.setUsername( dto.getUsername() );
        sysUser.setPassword( dto.getPassword() );
        sysUser.setRealName( dto.getRealName() );
        sysUser.setDeptId( dto.getDeptId() );
        sysUser.setStatus( dto.getStatus() );
        sysUser.setLastLoginTime( dto.getLastLoginTime() );
        sysUser.setLastLoginIp( dto.getLastLoginIp() );
        sysUser.setRemark( dto.getRemark() );

        return sysUser;
    }

    @Override
    public SysUserDTO toDTO(CreateUserRequest request) {
        if ( request == null ) {
            return null;
        }

        SysUserDTO sysUserDTO = new SysUserDTO();

        sysUserDTO.setUsername( request.getUsername() );
        sysUserDTO.setPassword( request.getPassword() );
        sysUserDTO.setRealName( request.getRealName() );
        sysUserDTO.setDeptId( request.getDeptId() );
        sysUserDTO.setStatus( request.getStatus() );
        sysUserDTO.setRemark( request.getRemark() );
        List<Long> list = request.getRoleIds();
        if ( list != null ) {
            sysUserDTO.setRoleIds( new ArrayList<Long>( list ) );
        }

        return sysUserDTO;
    }

    @Override
    public SysUserDTO toDTO(SysUser entity) {
        if ( entity == null ) {
            return null;
        }

        SysUserDTO sysUserDTO = new SysUserDTO();

        sysUserDTO.setId( entity.getId() );
        sysUserDTO.setTenantId( entity.getTenantId() );
        sysUserDTO.setDeleted( entity.getDeleted() );
        sysUserDTO.setCreateBy( entity.getCreateBy() );
        sysUserDTO.setCreateTime( entity.getCreateTime() );
        sysUserDTO.setUpdateBy( entity.getUpdateBy() );
        sysUserDTO.setUpdateTime( entity.getUpdateTime() );
        sysUserDTO.setVersion( entity.getVersion() );
        sysUserDTO.setUsername( entity.getUsername() );
        sysUserDTO.setPassword( entity.getPassword() );
        sysUserDTO.setRealName( entity.getRealName() );
        sysUserDTO.setDeptId( entity.getDeptId() );
        sysUserDTO.setStatus( entity.getStatus() );
        sysUserDTO.setLastLoginTime( entity.getLastLoginTime() );
        sysUserDTO.setLastLoginIp( entity.getLastLoginIp() );
        sysUserDTO.setRemark( entity.getRemark() );

        return sysUserDTO;
    }

    @Override
    public TokenUser toTokenUser(SysUser entity) {
        if ( entity == null ) {
            return null;
        }

        TokenUser.TokenUserBuilder tokenUser = TokenUser.builder();

        tokenUser.id( entity.getId() );
        tokenUser.username( entity.getUsername() );
        tokenUser.tenantId( entity.getTenantId() );

        return tokenUser.build();
    }

    @Override
    public void updateFromDTO(SysUserDTO dto, SysUser entity) {
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
        if ( dto.getUsername() != null ) {
            entity.setUsername( dto.getUsername() );
        }
        if ( dto.getPassword() != null ) {
            entity.setPassword( dto.getPassword() );
        }
        if ( dto.getRealName() != null ) {
            entity.setRealName( dto.getRealName() );
        }
        if ( dto.getDeptId() != null ) {
            entity.setDeptId( dto.getDeptId() );
        }
        if ( dto.getStatus() != null ) {
            entity.setStatus( dto.getStatus() );
        }
        if ( dto.getLastLoginTime() != null ) {
            entity.setLastLoginTime( dto.getLastLoginTime() );
        }
        if ( dto.getLastLoginIp() != null ) {
            entity.setLastLoginIp( dto.getLastLoginIp() );
        }
        if ( dto.getRemark() != null ) {
            entity.setRemark( dto.getRemark() );
        }
    }
}
