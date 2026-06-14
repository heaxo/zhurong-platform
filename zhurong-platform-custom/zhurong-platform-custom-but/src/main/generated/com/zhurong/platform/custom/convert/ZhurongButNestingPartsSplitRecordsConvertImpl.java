package com.zhurong.platform.custom.convert;

import com.zhurong.platform.custom.dto.ZhurongButNestingPartsSplitRecordsCreateDTO;
import com.zhurong.platform.custom.dto.ZhurongButNestingPartsSplitRecordsDTO;
import com.zhurong.platform.custom.dto.ZhurongButNestingPartsSplitRecordsPageQuery;
import com.zhurong.platform.custom.entity.ZhurongButNestingPartsSplitRecords;
import com.zhurong.platform.custom.vo.ZhurongButNestingPartsSplitRecordsVO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-06-14T12:06:56+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.10 (Eclipse Adoptium)"
)
@Component
public class ZhurongButNestingPartsSplitRecordsConvertImpl implements ZhurongButNestingPartsSplitRecordsConvert {

    @Override
    public ZhurongButNestingPartsSplitRecordsVO toVO(ZhurongButNestingPartsSplitRecords entity) {
        if ( entity == null ) {
            return null;
        }

        ZhurongButNestingPartsSplitRecordsVO zhurongButNestingPartsSplitRecordsVO = new ZhurongButNestingPartsSplitRecordsVO();

        if ( entity.getId() != null ) {
            zhurongButNestingPartsSplitRecordsVO.setId( String.valueOf( entity.getId() ) );
        }
        zhurongButNestingPartsSplitRecordsVO.setCreatedAt( entity.getCreatedAt() );
        zhurongButNestingPartsSplitRecordsVO.setUpdatedAt( entity.getUpdatedAt() );
        if ( entity.getCreatedBy() != null ) {
            zhurongButNestingPartsSplitRecordsVO.setCreatedBy( String.valueOf( entity.getCreatedBy() ) );
        }
        if ( entity.getUpdatedBy() != null ) {
            zhurongButNestingPartsSplitRecordsVO.setUpdatedBy( String.valueOf( entity.getUpdatedBy() ) );
        }
        zhurongButNestingPartsSplitRecordsVO.setVersion( entity.getVersion() );
        zhurongButNestingPartsSplitRecordsVO.setIsDeleted( entity.getIsDeleted() );
        zhurongButNestingPartsSplitRecordsVO.setIsRead( entity.getIsRead() );
        zhurongButNestingPartsSplitRecordsVO.setIsReviewed( entity.getIsReviewed() );
        zhurongButNestingPartsSplitRecordsVO.setNstRef( entity.getNstRef() );
        zhurongButNestingPartsSplitRecordsVO.setMnoRef( entity.getMnoRef() );
        zhurongButNestingPartsSplitRecordsVO.setOrgMnoRef( entity.getOrgMnoRef() );
        zhurongButNestingPartsSplitRecordsVO.setOprId( entity.getOprId() );
        zhurongButNestingPartsSplitRecordsVO.setQuantity( entity.getQuantity() );
        zhurongButNestingPartsSplitRecordsVO.setRemark( entity.getRemark() );
        zhurongButNestingPartsSplitRecordsVO.setOrdRef( entity.getOrdRef() );
        zhurongButNestingPartsSplitRecordsVO.setRecId( entity.getRecId() );
        zhurongButNestingPartsSplitRecordsVO.setPrdRef( entity.getPrdRef() );

        return zhurongButNestingPartsSplitRecordsVO;
    }

    @Override
    public List<ZhurongButNestingPartsSplitRecordsVO> toVOList(List<ZhurongButNestingPartsSplitRecords> list) {
        if ( list == null ) {
            return null;
        }

        List<ZhurongButNestingPartsSplitRecordsVO> list1 = new ArrayList<ZhurongButNestingPartsSplitRecordsVO>( list.size() );
        for ( ZhurongButNestingPartsSplitRecords zhurongButNestingPartsSplitRecords : list ) {
            list1.add( toVO( zhurongButNestingPartsSplitRecords ) );
        }

        return list1;
    }

    @Override
    public ZhurongButNestingPartsSplitRecords toEntity(ZhurongButNestingPartsSplitRecordsDTO dto) {
        if ( dto == null ) {
            return null;
        }

        ZhurongButNestingPartsSplitRecords zhurongButNestingPartsSplitRecords = new ZhurongButNestingPartsSplitRecords();

        zhurongButNestingPartsSplitRecords.setId( dto.getId() );
        zhurongButNestingPartsSplitRecords.setIsDeleted( dto.getIsDeleted() );
        zhurongButNestingPartsSplitRecords.setVersion( dto.getVersion() );
        zhurongButNestingPartsSplitRecords.setCreatedBy( dto.getCreatedBy() );
        zhurongButNestingPartsSplitRecords.setCreatedAt( dto.getCreatedAt() );
        zhurongButNestingPartsSplitRecords.setUpdatedBy( dto.getUpdatedBy() );
        zhurongButNestingPartsSplitRecords.setUpdatedAt( dto.getUpdatedAt() );
        zhurongButNestingPartsSplitRecords.setIsRead( dto.getIsRead() );
        zhurongButNestingPartsSplitRecords.setIsReviewed( dto.getIsReviewed() );
        zhurongButNestingPartsSplitRecords.setNstRef( dto.getNstRef() );
        zhurongButNestingPartsSplitRecords.setMnoRef( dto.getMnoRef() );
        zhurongButNestingPartsSplitRecords.setOrgMnoRef( dto.getOrgMnoRef() );
        zhurongButNestingPartsSplitRecords.setOprId( dto.getOprId() );
        zhurongButNestingPartsSplitRecords.setQuantity( dto.getQuantity() );
        zhurongButNestingPartsSplitRecords.setRemark( dto.getRemark() );
        zhurongButNestingPartsSplitRecords.setOrdRef( dto.getOrdRef() );
        zhurongButNestingPartsSplitRecords.setRecId( dto.getRecId() );
        zhurongButNestingPartsSplitRecords.setPrdRef( dto.getPrdRef() );

        return zhurongButNestingPartsSplitRecords;
    }

    @Override
    public List<ZhurongButNestingPartsSplitRecords> toEntity(List<ZhurongButNestingPartsSplitRecordsCreateDTO> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<ZhurongButNestingPartsSplitRecords> list = new ArrayList<ZhurongButNestingPartsSplitRecords>( dtos.size() );
        for ( ZhurongButNestingPartsSplitRecordsCreateDTO zhurongButNestingPartsSplitRecordsCreateDTO : dtos ) {
            list.add( zhurongButNestingPartsSplitRecordsCreateDTOToZhurongButNestingPartsSplitRecords( zhurongButNestingPartsSplitRecordsCreateDTO ) );
        }

        return list;
    }

    @Override
    public ZhurongButNestingPartsSplitRecords toEntity(ZhurongButNestingPartsSplitRecordsPageQuery dto) {
        if ( dto == null ) {
            return null;
        }

        ZhurongButNestingPartsSplitRecords zhurongButNestingPartsSplitRecords = new ZhurongButNestingPartsSplitRecords();

        zhurongButNestingPartsSplitRecords.setIsDeleted( dto.getIsDeleted() );
        zhurongButNestingPartsSplitRecords.setVersion( dto.getVersion() );
        zhurongButNestingPartsSplitRecords.setCreatedBy( dto.getCreatedBy() );
        zhurongButNestingPartsSplitRecords.setCreatedAt( dto.getCreatedAt() );
        zhurongButNestingPartsSplitRecords.setUpdatedBy( dto.getUpdatedBy() );
        zhurongButNestingPartsSplitRecords.setUpdatedAt( dto.getUpdatedAt() );
        zhurongButNestingPartsSplitRecords.setIsRead( dto.getIsRead() );
        zhurongButNestingPartsSplitRecords.setIsReviewed( dto.getIsReviewed() );
        zhurongButNestingPartsSplitRecords.setNstRef( dto.getNstRef() );
        zhurongButNestingPartsSplitRecords.setMnoRef( dto.getMnoRef() );
        zhurongButNestingPartsSplitRecords.setOrgMnoRef( dto.getOrgMnoRef() );
        zhurongButNestingPartsSplitRecords.setOprId( dto.getOprId() );
        zhurongButNestingPartsSplitRecords.setQuantity( dto.getQuantity() );
        zhurongButNestingPartsSplitRecords.setRemark( dto.getRemark() );
        zhurongButNestingPartsSplitRecords.setOrdRef( dto.getOrdRef() );
        zhurongButNestingPartsSplitRecords.setRecId( dto.getRecId() );
        zhurongButNestingPartsSplitRecords.setPrdRef( dto.getPrdRef() );

        return zhurongButNestingPartsSplitRecords;
    }

    @Override
    public void updateFromDTO(ZhurongButNestingPartsSplitRecordsDTO dto, ZhurongButNestingPartsSplitRecords entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getId() != null ) {
            entity.setId( dto.getId() );
        }
        if ( dto.getIsDeleted() != null ) {
            entity.setIsDeleted( dto.getIsDeleted() );
        }
        if ( dto.getVersion() != null ) {
            entity.setVersion( dto.getVersion() );
        }
        if ( dto.getCreatedBy() != null ) {
            entity.setCreatedBy( dto.getCreatedBy() );
        }
        if ( dto.getCreatedAt() != null ) {
            entity.setCreatedAt( dto.getCreatedAt() );
        }
        if ( dto.getUpdatedBy() != null ) {
            entity.setUpdatedBy( dto.getUpdatedBy() );
        }
        if ( dto.getUpdatedAt() != null ) {
            entity.setUpdatedAt( dto.getUpdatedAt() );
        }
        if ( dto.getIsRead() != null ) {
            entity.setIsRead( dto.getIsRead() );
        }
        if ( dto.getIsReviewed() != null ) {
            entity.setIsReviewed( dto.getIsReviewed() );
        }
        if ( dto.getNstRef() != null ) {
            entity.setNstRef( dto.getNstRef() );
        }
        if ( dto.getMnoRef() != null ) {
            entity.setMnoRef( dto.getMnoRef() );
        }
        if ( dto.getOrgMnoRef() != null ) {
            entity.setOrgMnoRef( dto.getOrgMnoRef() );
        }
        if ( dto.getOprId() != null ) {
            entity.setOprId( dto.getOprId() );
        }
        if ( dto.getQuantity() != null ) {
            entity.setQuantity( dto.getQuantity() );
        }
        if ( dto.getRemark() != null ) {
            entity.setRemark( dto.getRemark() );
        }
        if ( dto.getOrdRef() != null ) {
            entity.setOrdRef( dto.getOrdRef() );
        }
        if ( dto.getRecId() != null ) {
            entity.setRecId( dto.getRecId() );
        }
        if ( dto.getPrdRef() != null ) {
            entity.setPrdRef( dto.getPrdRef() );
        }
    }

    protected ZhurongButNestingPartsSplitRecords zhurongButNestingPartsSplitRecordsCreateDTOToZhurongButNestingPartsSplitRecords(ZhurongButNestingPartsSplitRecordsCreateDTO zhurongButNestingPartsSplitRecordsCreateDTO) {
        if ( zhurongButNestingPartsSplitRecordsCreateDTO == null ) {
            return null;
        }

        ZhurongButNestingPartsSplitRecords zhurongButNestingPartsSplitRecords = new ZhurongButNestingPartsSplitRecords();

        zhurongButNestingPartsSplitRecords.setNstRef( zhurongButNestingPartsSplitRecordsCreateDTO.getNstRef() );
        zhurongButNestingPartsSplitRecords.setMnoRef( zhurongButNestingPartsSplitRecordsCreateDTO.getMnoRef() );
        zhurongButNestingPartsSplitRecords.setOrgMnoRef( zhurongButNestingPartsSplitRecordsCreateDTO.getOrgMnoRef() );
        zhurongButNestingPartsSplitRecords.setOprId( zhurongButNestingPartsSplitRecordsCreateDTO.getOprId() );
        zhurongButNestingPartsSplitRecords.setQuantity( zhurongButNestingPartsSplitRecordsCreateDTO.getQuantity() );
        zhurongButNestingPartsSplitRecords.setRemark( zhurongButNestingPartsSplitRecordsCreateDTO.getRemark() );
        zhurongButNestingPartsSplitRecords.setOrdRef( zhurongButNestingPartsSplitRecordsCreateDTO.getOrdRef() );
        zhurongButNestingPartsSplitRecords.setRecId( zhurongButNestingPartsSplitRecordsCreateDTO.getRecId() );
        zhurongButNestingPartsSplitRecords.setPrdRef( zhurongButNestingPartsSplitRecordsCreateDTO.getPrdRef() );

        return zhurongButNestingPartsSplitRecords;
    }
}
