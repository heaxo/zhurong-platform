package com.ao.platform.auth.convert;

import com.ao.platform.auth.dto.SysTenantDTO;
import com.ao.platform.auth.dto.SysTenantPageQuery;
import com.ao.platform.auth.entity.SysTenant;
import com.ao.platform.auth.vo.SysTenantVO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-22T14:38:38+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.10 (Eclipse Adoptium)"
)
@Component
public class SysTenantConvertImpl implements SysTenantConvert {

    @Override
    public SysTenantVO toVO(SysTenant entity) {
        if ( entity == null ) {
            return null;
        }

        SysTenantVO sysTenantVO = new SysTenantVO();

        sysTenantVO.setId( entity.getId() );
        sysTenantVO.setName( entity.getName() );
        sysTenantVO.setStatus( entity.getStatus() );
        sysTenantVO.setExpireTime( entity.getExpireTime() );
        sysTenantVO.setCreateTime( entity.getCreateTime() );
        sysTenantVO.setUpdateTime( entity.getUpdateTime() );
        sysTenantVO.setVersion( entity.getVersion() );

        return sysTenantVO;
    }

    @Override
    public List<SysTenantVO> toVOList(List<SysTenant> list) {
        if ( list == null ) {
            return null;
        }

        List<SysTenantVO> list1 = new ArrayList<SysTenantVO>( list.size() );
        for ( SysTenant sysTenant : list ) {
            list1.add( toVO( sysTenant ) );
        }

        return list1;
    }

    @Override
    public SysTenant toEntity(SysTenantDTO dto) {
        if ( dto == null ) {
            return null;
        }

        SysTenant sysTenant = new SysTenant();

        sysTenant.setId( dto.getId() );
        sysTenant.setVersion( dto.getVersion() );
        sysTenant.setCreateTime( dto.getCreateTime() );
        sysTenant.setUpdateTime( dto.getUpdateTime() );
        sysTenant.setName( dto.getName() );
        sysTenant.setStatus( dto.getStatus() );
        sysTenant.setExpireTime( dto.getExpireTime() );

        return sysTenant;
    }

    @Override
    public SysTenant toEntity(SysTenantPageQuery dto) {
        if ( dto == null ) {
            return null;
        }

        SysTenant sysTenant = new SysTenant();

        sysTenant.setVersion( dto.getVersion() );
        sysTenant.setName( dto.getName() );
        sysTenant.setStatus( dto.getStatus() );
        sysTenant.setExpireTime( dto.getExpireTime() );

        return sysTenant;
    }

    @Override
    public void updateFromDTO(SysTenantDTO dto, SysTenant entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getId() != null ) {
            entity.setId( dto.getId() );
        }
        if ( dto.getVersion() != null ) {
            entity.setVersion( dto.getVersion() );
        }
        if ( dto.getCreateTime() != null ) {
            entity.setCreateTime( dto.getCreateTime() );
        }
        if ( dto.getUpdateTime() != null ) {
            entity.setUpdateTime( dto.getUpdateTime() );
        }
        if ( dto.getName() != null ) {
            entity.setName( dto.getName() );
        }
        if ( dto.getStatus() != null ) {
            entity.setStatus( dto.getStatus() );
        }
        if ( dto.getExpireTime() != null ) {
            entity.setExpireTime( dto.getExpireTime() );
        }
    }
}
