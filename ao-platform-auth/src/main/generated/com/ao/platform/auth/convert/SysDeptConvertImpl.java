package com.ao.platform.auth.convert;

import com.ao.platform.auth.dto.SysDeptDTO;
import com.ao.platform.auth.dto.SysDeptPageQuery;
import com.ao.platform.auth.entity.SysDept;
import com.ao.platform.auth.vo.SysDeptVO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-22T14:38:39+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.10 (Eclipse Adoptium)"
)
@Component
public class SysDeptConvertImpl implements SysDeptConvert {

    @Override
    public SysDeptVO toVO(SysDept entity) {
        if ( entity == null ) {
            return null;
        }

        SysDeptVO sysDeptVO = new SysDeptVO();

        sysDeptVO.setId( entity.getId() );
        sysDeptVO.setTenantId( entity.getTenantId() );
        sysDeptVO.setDeleted( entity.getDeleted() );
        sysDeptVO.setCreateBy( entity.getCreateBy() );
        sysDeptVO.setCreateTime( entity.getCreateTime() );
        sysDeptVO.setUpdateBy( entity.getUpdateBy() );
        sysDeptVO.setUpdateTime( entity.getUpdateTime() );
        sysDeptVO.setVersion( entity.getVersion() );
        sysDeptVO.setPid( entity.getPid() );
        sysDeptVO.setName( entity.getName() );
        sysDeptVO.setStatus( entity.getStatus() );
        sysDeptVO.setSortOrder( entity.getSortOrder() );
        sysDeptVO.setRemark( entity.getRemark() );

        return sysDeptVO;
    }

    @Override
    public List<SysDeptVO> toVOList(List<SysDept> list) {
        if ( list == null ) {
            return null;
        }

        List<SysDeptVO> list1 = new ArrayList<SysDeptVO>( list.size() );
        for ( SysDept sysDept : list ) {
            list1.add( toVO( sysDept ) );
        }

        return list1;
    }

    @Override
    public SysDept toEntity(SysDeptDTO dto) {
        if ( dto == null ) {
            return null;
        }

        SysDept sysDept = new SysDept();

        sysDept.setId( dto.getId() );
        sysDept.setDeleted( dto.getDeleted() );
        sysDept.setVersion( dto.getVersion() );
        sysDept.setCreateBy( dto.getCreateBy() );
        sysDept.setCreateTime( dto.getCreateTime() );
        sysDept.setUpdateBy( dto.getUpdateBy() );
        sysDept.setUpdateTime( dto.getUpdateTime() );
        sysDept.setTenantId( dto.getTenantId() );
        sysDept.setPid( dto.getPid() );
        sysDept.setName( dto.getName() );
        sysDept.setStatus( dto.getStatus() );
        sysDept.setSortOrder( dto.getSortOrder() );
        sysDept.setRemark( dto.getRemark() );

        return sysDept;
    }

    @Override
    public SysDept toEntity(SysDeptPageQuery dto) {
        if ( dto == null ) {
            return null;
        }

        SysDept sysDept = new SysDept();

        sysDept.setVersion( dto.getVersion() );
        sysDept.setCreateBy( dto.getCreateBy() );
        sysDept.setUpdateBy( dto.getUpdateBy() );
        sysDept.setPid( dto.getPid() );
        sysDept.setName( dto.getName() );
        sysDept.setStatus( dto.getStatus() );
        sysDept.setSortOrder( dto.getSortOrder() );
        sysDept.setRemark( dto.getRemark() );

        return sysDept;
    }

    @Override
    public void updateFromDTO(SysDeptDTO dto, SysDept entity) {
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
        if ( dto.getStatus() != null ) {
            entity.setStatus( dto.getStatus() );
        }
        if ( dto.getSortOrder() != null ) {
            entity.setSortOrder( dto.getSortOrder() );
        }
        if ( dto.getRemark() != null ) {
            entity.setRemark( dto.getRemark() );
        }
    }
}
