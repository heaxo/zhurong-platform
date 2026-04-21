package com.zhurong.platform.custom.convert;

import com.zhurong.platform.custom.dto.ZhurongButSupplierinfoDTO;
import com.zhurong.platform.custom.dto.ZhurongButSupplierinfoPageQuery;
import com.zhurong.platform.custom.entity.ZhurongButSupplierinfo;
import com.zhurong.platform.custom.erp.entity.ViPmPrjplanLantek;
import com.zhurong.platform.custom.vo.ZhurongButSupplierinfoVO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-04-21T22:19:18+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.10 (Eclipse Adoptium)"
)
@Component
public class ZhurongButSupplierinfoConvertImpl implements ZhurongButSupplierinfoConvert {

    @Override
    public ZhurongButSupplierinfoVO toVO(ZhurongButSupplierinfo entity) {
        if ( entity == null ) {
            return null;
        }

        ZhurongButSupplierinfoVO zhurongButSupplierinfoVO = new ZhurongButSupplierinfoVO();

        if ( entity.getId() != null ) {
            zhurongButSupplierinfoVO.setId( String.valueOf( entity.getId() ) );
        }
        zhurongButSupplierinfoVO.setCreatedAt( entity.getCreatedAt() );
        zhurongButSupplierinfoVO.setUpdatedAt( entity.getUpdatedAt() );
        if ( entity.getCreatedBy() != null ) {
            zhurongButSupplierinfoVO.setCreatedBy( String.valueOf( entity.getCreatedBy() ) );
        }
        if ( entity.getUpdatedBy() != null ) {
            zhurongButSupplierinfoVO.setUpdatedBy( String.valueOf( entity.getUpdatedBy() ) );
        }
        zhurongButSupplierinfoVO.setVersion( entity.getVersion() );
        zhurongButSupplierinfoVO.setIsDeleted( entity.getIsDeleted() );
        zhurongButSupplierinfoVO.setIsRead( entity.getIsRead() );
        zhurongButSupplierinfoVO.setIsReviewed( entity.getIsReviewed() );
        zhurongButSupplierinfoVO.setWhsName( entity.getWhsName() );

        return zhurongButSupplierinfoVO;
    }

    @Override
    public List<ZhurongButSupplierinfoVO> toVOList(List<ZhurongButSupplierinfo> list) {
        if ( list == null ) {
            return null;
        }

        List<ZhurongButSupplierinfoVO> list1 = new ArrayList<ZhurongButSupplierinfoVO>( list.size() );
        for ( ZhurongButSupplierinfo zhurongButSupplierinfo : list ) {
            list1.add( toVO( zhurongButSupplierinfo ) );
        }

        return list1;
    }

    @Override
    public ZhurongButSupplierinfo toEntity(ZhurongButSupplierinfoDTO dto) {
        if ( dto == null ) {
            return null;
        }

        ZhurongButSupplierinfo zhurongButSupplierinfo = new ZhurongButSupplierinfo();

        zhurongButSupplierinfo.setId( dto.getId() );
        zhurongButSupplierinfo.setIsDeleted( dto.getIsDeleted() );
        zhurongButSupplierinfo.setVersion( dto.getVersion() );
        zhurongButSupplierinfo.setCreatedBy( dto.getCreatedBy() );
        zhurongButSupplierinfo.setCreatedAt( dto.getCreatedAt() );
        zhurongButSupplierinfo.setUpdatedBy( dto.getUpdatedBy() );
        zhurongButSupplierinfo.setUpdatedAt( dto.getUpdatedAt() );
        zhurongButSupplierinfo.setIsRead( dto.getIsRead() );
        zhurongButSupplierinfo.setIsReviewed( dto.getIsReviewed() );
        zhurongButSupplierinfo.setCnc( dto.getCnc() );
        zhurongButSupplierinfo.setLocName( dto.getLocName() );
        zhurongButSupplierinfo.setShtRef( dto.getShtRef() );
        zhurongButSupplierinfo.setShtName( dto.getShtName() );
        zhurongButSupplierinfo.setQuantity( dto.getQuantity() );
        zhurongButSupplierinfo.setWhsName( dto.getWhsName() );
        zhurongButSupplierinfo.setBatchNumber( dto.getBatchNumber() );
        zhurongButSupplierinfo.setWeight( dto.getWeight() );
        zhurongButSupplierinfo.setUnit( dto.getUnit() );
        zhurongButSupplierinfo.setBusinessType( dto.getBusinessType() );

        return zhurongButSupplierinfo;
    }

    @Override
    public ZhurongButSupplierinfo toEntity(ZhurongButSupplierinfoPageQuery dto) {
        if ( dto == null ) {
            return null;
        }

        ZhurongButSupplierinfo zhurongButSupplierinfo = new ZhurongButSupplierinfo();

        zhurongButSupplierinfo.setIsDeleted( dto.getIsDeleted() );
        zhurongButSupplierinfo.setVersion( dto.getVersion() );
        zhurongButSupplierinfo.setCreatedBy( dto.getCreatedBy() );
        zhurongButSupplierinfo.setCreatedAt( dto.getCreatedAt() );
        zhurongButSupplierinfo.setUpdatedBy( dto.getUpdatedBy() );
        zhurongButSupplierinfo.setUpdatedAt( dto.getUpdatedAt() );
        zhurongButSupplierinfo.setIsRead( dto.getIsRead() );
        zhurongButSupplierinfo.setIsReviewed( dto.getIsReviewed() );
        zhurongButSupplierinfo.setCnc( dto.getCnc() );
        zhurongButSupplierinfo.setLocName( dto.getLocName() );
        zhurongButSupplierinfo.setShtRef( dto.getShtRef() );
        zhurongButSupplierinfo.setShtName( dto.getShtName() );
        zhurongButSupplierinfo.setQuantity( dto.getQuantity() );
        zhurongButSupplierinfo.setWhsName( dto.getWhsName() );
        zhurongButSupplierinfo.setBatchNumber( dto.getBatchNumber() );
        zhurongButSupplierinfo.setWeight( dto.getWeight() );
        zhurongButSupplierinfo.setUnit( dto.getUnit() );
        zhurongButSupplierinfo.setBusinessType( dto.getBusinessType() );

        return zhurongButSupplierinfo;
    }

    @Override
    public ZhurongButSupplierinfo toEntity(ViPmPrjplanLantek entity) {
        if ( entity == null ) {
            return null;
        }

        ZhurongButSupplierinfo zhurongButSupplierinfo = new ZhurongButSupplierinfo();

        zhurongButSupplierinfo.setCnc( entity.getCnc() );
        zhurongButSupplierinfo.setLocName( entity.getLocName() );
        zhurongButSupplierinfo.setShtRef( entity.getShtRef() );
        zhurongButSupplierinfo.setShtName( entity.getShtName() );
        zhurongButSupplierinfo.setQuantity( entity.getQuantity() );
        zhurongButSupplierinfo.setWhsName( entity.getWhsName() );
        zhurongButSupplierinfo.setBatchNumber( entity.getBatchNumber() );
        if ( entity.getWeight() != null ) {
            zhurongButSupplierinfo.setWeight( entity.getWeight().floatValue() );
        }
        zhurongButSupplierinfo.setUnit( entity.getUnit() );
        zhurongButSupplierinfo.setBusinessType( entity.getBusinessType() );

        return zhurongButSupplierinfo;
    }

    @Override
    public void updateFromDTO(ZhurongButSupplierinfoDTO dto, ZhurongButSupplierinfo entity) {
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
        if ( dto.getCnc() != null ) {
            entity.setCnc( dto.getCnc() );
        }
        if ( dto.getLocName() != null ) {
            entity.setLocName( dto.getLocName() );
        }
        if ( dto.getShtRef() != null ) {
            entity.setShtRef( dto.getShtRef() );
        }
        if ( dto.getShtName() != null ) {
            entity.setShtName( dto.getShtName() );
        }
        if ( dto.getQuantity() != null ) {
            entity.setQuantity( dto.getQuantity() );
        }
        if ( dto.getWhsName() != null ) {
            entity.setWhsName( dto.getWhsName() );
        }
        if ( dto.getBatchNumber() != null ) {
            entity.setBatchNumber( dto.getBatchNumber() );
        }
        if ( dto.getWeight() != null ) {
            entity.setWeight( dto.getWeight() );
        }
        if ( dto.getUnit() != null ) {
            entity.setUnit( dto.getUnit() );
        }
        if ( dto.getBusinessType() != null ) {
            entity.setBusinessType( dto.getBusinessType() );
        }
    }
}
