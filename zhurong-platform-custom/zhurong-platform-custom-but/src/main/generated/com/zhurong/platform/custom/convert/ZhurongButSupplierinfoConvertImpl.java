package com.zhurong.platform.custom.convert;

import com.zhurong.platform.custom.dto.ZhurongButSupplierinfoDTO;
import com.zhurong.platform.custom.dto.ZhurongButSupplierinfoPageQuery;
import com.zhurong.platform.custom.entity.ZhurongButSupplierinfo;
import com.zhurong.platform.custom.vo.ZhurongButSupplierinfoVO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-04-19T15:28:47+0800",
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
        zhurongButSupplierinfoVO.setNstRef( entity.getNstRef() );
        zhurongButSupplierinfoVO.setSupplierName( entity.getSupplierName() );
        zhurongButSupplierinfoVO.setWhsName( entity.getWhsName() );
        zhurongButSupplierinfoVO.setUdata1( entity.getUdata1() );
        zhurongButSupplierinfoVO.setUdata2( entity.getUdata2() );
        zhurongButSupplierinfoVO.setUdata3( entity.getUdata3() );

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
        zhurongButSupplierinfo.setNstRef( dto.getNstRef() );
        zhurongButSupplierinfo.setSupplierName( dto.getSupplierName() );
        zhurongButSupplierinfo.setWhsName( dto.getWhsName() );
        zhurongButSupplierinfo.setUdata1( dto.getUdata1() );
        zhurongButSupplierinfo.setUdata2( dto.getUdata2() );
        zhurongButSupplierinfo.setUdata3( dto.getUdata3() );

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
        zhurongButSupplierinfo.setNstRef( dto.getNstRef() );
        zhurongButSupplierinfo.setSupplierName( dto.getSupplierName() );
        zhurongButSupplierinfo.setWhsName( dto.getWhsName() );
        zhurongButSupplierinfo.setUdata1( dto.getUdata1() );
        zhurongButSupplierinfo.setUdata2( dto.getUdata2() );
        zhurongButSupplierinfo.setUdata3( dto.getUdata3() );

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
        if ( dto.getNstRef() != null ) {
            entity.setNstRef( dto.getNstRef() );
        }
        if ( dto.getSupplierName() != null ) {
            entity.setSupplierName( dto.getSupplierName() );
        }
        if ( dto.getWhsName() != null ) {
            entity.setWhsName( dto.getWhsName() );
        }
        if ( dto.getUdata1() != null ) {
            entity.setUdata1( dto.getUdata1() );
        }
        if ( dto.getUdata2() != null ) {
            entity.setUdata2( dto.getUdata2() );
        }
        if ( dto.getUdata3() != null ) {
            entity.setUdata3( dto.getUdata3() );
        }
    }
}
