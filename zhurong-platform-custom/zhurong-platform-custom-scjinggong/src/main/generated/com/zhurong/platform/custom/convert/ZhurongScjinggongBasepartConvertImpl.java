package com.zhurong.platform.custom.convert;

import com.zhurong.platform.custom.dto.ZhurongScjinggongBasepartDTO;
import com.zhurong.platform.custom.dto.ZhurongScjinggongBasepartPageQuery;
import com.zhurong.platform.custom.entity.ZhurongScjinggongBasepart;
import com.zhurong.platform.custom.vo.ZhurongScjinggongBasepartVO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-10-04T20:06:41+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.10 (Eclipse Adoptium)"
)
@Component
public class ZhurongScjinggongBasepartConvertImpl implements ZhurongScjinggongBasepartConvert {

    @Override
    public ZhurongScjinggongBasepartVO toVO(ZhurongScjinggongBasepart entity) {
        if ( entity == null ) {
            return null;
        }

        ZhurongScjinggongBasepartVO zhurongScjinggongBasepartVO = new ZhurongScjinggongBasepartVO();

        if ( entity.getId() != null ) {
            zhurongScjinggongBasepartVO.setId( String.valueOf( entity.getId() ) );
        }
        zhurongScjinggongBasepartVO.setIsDeleted( entity.getIsDeleted() );
        zhurongScjinggongBasepartVO.setVersion( entity.getVersion() );
        if ( entity.getCreatedBy() != null ) {
            zhurongScjinggongBasepartVO.setCreatedBy( String.valueOf( entity.getCreatedBy() ) );
        }
        zhurongScjinggongBasepartVO.setCreatedAt( entity.getCreatedAt() );
        if ( entity.getUpdatedBy() != null ) {
            zhurongScjinggongBasepartVO.setUpdatedBy( String.valueOf( entity.getUpdatedBy() ) );
        }
        zhurongScjinggongBasepartVO.setUpdatedAt( entity.getUpdatedAt() );
        zhurongScjinggongBasepartVO.setIsRead( entity.getIsRead() );
        zhurongScjinggongBasepartVO.setIsReviewed( entity.getIsReviewed() );
        zhurongScjinggongBasepartVO.setInvalidState( entity.getInvalidState() );
        zhurongScjinggongBasepartVO.setPrdRef( entity.getPrdRef() );
        zhurongScjinggongBasepartVO.setPrdName( entity.getPrdName() );
        zhurongScjinggongBasepartVO.setWrkRef( entity.getWrkRef() );
        zhurongScjinggongBasepartVO.setMatRef( entity.getMatRef() );
        if ( entity.getThickness() != null ) {
            zhurongScjinggongBasepartVO.setThickness( entity.getThickness().doubleValue() );
        }
        zhurongScjinggongBasepartVO.setQuantity( entity.getQuantity() );
        zhurongScjinggongBasepartVO.setUdata1( entity.getUdata1() );
        zhurongScjinggongBasepartVO.setUdata2( entity.getUdata2() );
        zhurongScjinggongBasepartVO.setUdata3( entity.getUdata3() );
        zhurongScjinggongBasepartVO.setUdata4( entity.getUdata4() );
        zhurongScjinggongBasepartVO.setUdata5( entity.getUdata5() );
        zhurongScjinggongBasepartVO.setUdata6( entity.getUdata6() );
        zhurongScjinggongBasepartVO.setUdata7( entity.getUdata7() );
        zhurongScjinggongBasepartVO.setUdata8( entity.getUdata8() );
        zhurongScjinggongBasepartVO.setDrawingPath( entity.getDrawingPath() );
        zhurongScjinggongBasepartVO.setRawDrawingPath( entity.getRawDrawingPath() );

        return zhurongScjinggongBasepartVO;
    }

    @Override
    public List<ZhurongScjinggongBasepartVO> toVOList(List<ZhurongScjinggongBasepart> list) {
        if ( list == null ) {
            return null;
        }

        List<ZhurongScjinggongBasepartVO> list1 = new ArrayList<ZhurongScjinggongBasepartVO>( list.size() );
        for ( ZhurongScjinggongBasepart zhurongScjinggongBasepart : list ) {
            list1.add( toVO( zhurongScjinggongBasepart ) );
        }

        return list1;
    }

    @Override
    public ZhurongScjinggongBasepart toEntity(ZhurongScjinggongBasepartDTO dto) {
        if ( dto == null ) {
            return null;
        }

        ZhurongScjinggongBasepart zhurongScjinggongBasepart = new ZhurongScjinggongBasepart();

        zhurongScjinggongBasepart.setId( dto.getId() );
        zhurongScjinggongBasepart.setIsDeleted( dto.getIsDeleted() );
        zhurongScjinggongBasepart.setVersion( dto.getVersion() );
        zhurongScjinggongBasepart.setCreatedBy( dto.getCreatedBy() );
        zhurongScjinggongBasepart.setCreatedAt( dto.getCreatedAt() );
        zhurongScjinggongBasepart.setUpdatedBy( dto.getUpdatedBy() );
        zhurongScjinggongBasepart.setUpdatedAt( dto.getUpdatedAt() );
        zhurongScjinggongBasepart.setIsRead( dto.getIsRead() );
        zhurongScjinggongBasepart.setIsReviewed( dto.getIsReviewed() );
        zhurongScjinggongBasepart.setInvalidState( dto.getInvalidState() );
        zhurongScjinggongBasepart.setPrdRef( dto.getPrdRef() );
        zhurongScjinggongBasepart.setPrdName( dto.getPrdName() );
        zhurongScjinggongBasepart.setWrkRef( dto.getWrkRef() );
        zhurongScjinggongBasepart.setMatRef( dto.getMatRef() );
        if ( dto.getThickness() != null ) {
            zhurongScjinggongBasepart.setThickness( dto.getThickness().floatValue() );
        }
        zhurongScjinggongBasepart.setQuantity( dto.getQuantity() );
        zhurongScjinggongBasepart.setUdata1( dto.getUdata1() );
        zhurongScjinggongBasepart.setUdata2( dto.getUdata2() );
        zhurongScjinggongBasepart.setUdata3( dto.getUdata3() );
        zhurongScjinggongBasepart.setUdata4( dto.getUdata4() );
        zhurongScjinggongBasepart.setUdata5( dto.getUdata5() );
        zhurongScjinggongBasepart.setUdata6( dto.getUdata6() );
        zhurongScjinggongBasepart.setUdata7( dto.getUdata7() );
        zhurongScjinggongBasepart.setUdata8( dto.getUdata8() );
        zhurongScjinggongBasepart.setDrawingPath( dto.getDrawingPath() );
        zhurongScjinggongBasepart.setRawDrawingPath( dto.getRawDrawingPath() );

        return zhurongScjinggongBasepart;
    }

    @Override
    public ZhurongScjinggongBasepart toEntity(ZhurongScjinggongBasepartPageQuery dto) {
        if ( dto == null ) {
            return null;
        }

        ZhurongScjinggongBasepart zhurongScjinggongBasepart = new ZhurongScjinggongBasepart();

        zhurongScjinggongBasepart.setIsDeleted( dto.getIsDeleted() );
        zhurongScjinggongBasepart.setVersion( dto.getVersion() );
        zhurongScjinggongBasepart.setCreatedBy( dto.getCreatedBy() );
        zhurongScjinggongBasepart.setCreatedAt( dto.getCreatedAt() );
        zhurongScjinggongBasepart.setUpdatedBy( dto.getUpdatedBy() );
        zhurongScjinggongBasepart.setUpdatedAt( dto.getUpdatedAt() );
        zhurongScjinggongBasepart.setIsRead( dto.getIsRead() );
        zhurongScjinggongBasepart.setIsReviewed( dto.getIsReviewed() );
        zhurongScjinggongBasepart.setInvalidState( dto.getInvalidState() );
        zhurongScjinggongBasepart.setPrdRef( dto.getPrdRef() );
        zhurongScjinggongBasepart.setPrdName( dto.getPrdName() );
        zhurongScjinggongBasepart.setWrkRef( dto.getWrkRef() );
        zhurongScjinggongBasepart.setMatRef( dto.getMatRef() );
        if ( dto.getThickness() != null ) {
            zhurongScjinggongBasepart.setThickness( dto.getThickness().floatValue() );
        }
        zhurongScjinggongBasepart.setQuantity( dto.getQuantity() );
        zhurongScjinggongBasepart.setUdata1( dto.getUdata1() );
        zhurongScjinggongBasepart.setUdata2( dto.getUdata2() );
        zhurongScjinggongBasepart.setUdata3( dto.getUdata3() );
        zhurongScjinggongBasepart.setUdata4( dto.getUdata4() );
        zhurongScjinggongBasepart.setUdata5( dto.getUdata5() );
        zhurongScjinggongBasepart.setUdata6( dto.getUdata6() );
        zhurongScjinggongBasepart.setUdata7( dto.getUdata7() );
        zhurongScjinggongBasepart.setUdata8( dto.getUdata8() );
        zhurongScjinggongBasepart.setDrawingPath( dto.getDrawingPath() );
        zhurongScjinggongBasepart.setRawDrawingPath( dto.getRawDrawingPath() );

        return zhurongScjinggongBasepart;
    }

    @Override
    public void updateFromDTO(ZhurongScjinggongBasepartDTO dto, ZhurongScjinggongBasepart entity) {
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
        if ( dto.getInvalidState() != null ) {
            entity.setInvalidState( dto.getInvalidState() );
        }
        if ( dto.getPrdRef() != null ) {
            entity.setPrdRef( dto.getPrdRef() );
        }
        if ( dto.getPrdName() != null ) {
            entity.setPrdName( dto.getPrdName() );
        }
        if ( dto.getWrkRef() != null ) {
            entity.setWrkRef( dto.getWrkRef() );
        }
        if ( dto.getMatRef() != null ) {
            entity.setMatRef( dto.getMatRef() );
        }
        if ( dto.getThickness() != null ) {
            entity.setThickness( dto.getThickness().floatValue() );
        }
        if ( dto.getQuantity() != null ) {
            entity.setQuantity( dto.getQuantity() );
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
        if ( dto.getUdata4() != null ) {
            entity.setUdata4( dto.getUdata4() );
        }
        if ( dto.getUdata5() != null ) {
            entity.setUdata5( dto.getUdata5() );
        }
        if ( dto.getUdata6() != null ) {
            entity.setUdata6( dto.getUdata6() );
        }
        if ( dto.getUdata7() != null ) {
            entity.setUdata7( dto.getUdata7() );
        }
        if ( dto.getUdata8() != null ) {
            entity.setUdata8( dto.getUdata8() );
        }
        if ( dto.getDrawingPath() != null ) {
            entity.setDrawingPath( dto.getDrawingPath() );
        }
        if ( dto.getRawDrawingPath() != null ) {
            entity.setRawDrawingPath( dto.getRawDrawingPath() );
        }
    }
}
