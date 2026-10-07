package com.zhurong.platform.custom.convert;

import com.zhurong.platform.custom.dto.ZhurongScjinggongOrderDTO;
import com.zhurong.platform.custom.dto.ZhurongScjinggongOrderPageQuery;
import com.zhurong.platform.custom.entity.ZhurongScjinggongOrder;
import com.zhurong.platform.custom.vo.ZhurongScjinggongOrderVO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-10-07T12:51:53+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.10 (Eclipse Adoptium)"
)
@Component
public class ZhurongScjinggongOrderConvertImpl implements ZhurongScjinggongOrderConvert {

    @Override
    public ZhurongScjinggongOrderVO toVO(ZhurongScjinggongOrder entity) {
        if ( entity == null ) {
            return null;
        }

        ZhurongScjinggongOrderVO zhurongScjinggongOrderVO = new ZhurongScjinggongOrderVO();

        if ( entity.getId() != null ) {
            zhurongScjinggongOrderVO.setId( String.valueOf( entity.getId() ) );
        }
        zhurongScjinggongOrderVO.setIsDeleted( entity.getIsDeleted() );
        zhurongScjinggongOrderVO.setVersion( entity.getVersion() );
        if ( entity.getCreatedBy() != null ) {
            zhurongScjinggongOrderVO.setCreatedBy( String.valueOf( entity.getCreatedBy() ) );
        }
        zhurongScjinggongOrderVO.setCreatedAt( entity.getCreatedAt() );
        if ( entity.getUpdatedBy() != null ) {
            zhurongScjinggongOrderVO.setUpdatedBy( String.valueOf( entity.getUpdatedBy() ) );
        }
        zhurongScjinggongOrderVO.setUpdatedAt( entity.getUpdatedAt() );
        zhurongScjinggongOrderVO.setIsRead( entity.getIsRead() );
        zhurongScjinggongOrderVO.setIsReviewed( entity.getIsReviewed() );
        zhurongScjinggongOrderVO.setInvalidState( entity.getInvalidState() );
        zhurongScjinggongOrderVO.setOrderCode( entity.getOrderCode() );
        zhurongScjinggongOrderVO.setOrderName( entity.getOrderName() );
        zhurongScjinggongOrderVO.setUdata1( entity.getUdata1() );
        zhurongScjinggongOrderVO.setUdata2( entity.getUdata2() );
        zhurongScjinggongOrderVO.setUdata3( entity.getUdata3() );
        zhurongScjinggongOrderVO.setUdata4( entity.getUdata4() );
        zhurongScjinggongOrderVO.setUdata5( entity.getUdata5() );

        return zhurongScjinggongOrderVO;
    }

    @Override
    public List<ZhurongScjinggongOrderVO> toVOList(List<ZhurongScjinggongOrder> list) {
        if ( list == null ) {
            return null;
        }

        List<ZhurongScjinggongOrderVO> list1 = new ArrayList<ZhurongScjinggongOrderVO>( list.size() );
        for ( ZhurongScjinggongOrder zhurongScjinggongOrder : list ) {
            list1.add( toVO( zhurongScjinggongOrder ) );
        }

        return list1;
    }

    @Override
    public ZhurongScjinggongOrder toEntity(ZhurongScjinggongOrderDTO dto) {
        if ( dto == null ) {
            return null;
        }

        ZhurongScjinggongOrder zhurongScjinggongOrder = new ZhurongScjinggongOrder();

        zhurongScjinggongOrder.setId( dto.getId() );
        zhurongScjinggongOrder.setIsDeleted( dto.getIsDeleted() );
        zhurongScjinggongOrder.setVersion( dto.getVersion() );
        zhurongScjinggongOrder.setCreatedBy( dto.getCreatedBy() );
        zhurongScjinggongOrder.setCreatedAt( dto.getCreatedAt() );
        zhurongScjinggongOrder.setUpdatedBy( dto.getUpdatedBy() );
        zhurongScjinggongOrder.setUpdatedAt( dto.getUpdatedAt() );
        zhurongScjinggongOrder.setIsRead( dto.getIsRead() );
        zhurongScjinggongOrder.setIsReviewed( dto.getIsReviewed() );
        zhurongScjinggongOrder.setInvalidState( dto.getInvalidState() );
        zhurongScjinggongOrder.setOrderCode( dto.getOrderCode() );
        zhurongScjinggongOrder.setOrderName( dto.getOrderName() );
        zhurongScjinggongOrder.setUdata1( dto.getUdata1() );
        zhurongScjinggongOrder.setUdata2( dto.getUdata2() );
        zhurongScjinggongOrder.setUdata3( dto.getUdata3() );
        zhurongScjinggongOrder.setUdata4( dto.getUdata4() );
        zhurongScjinggongOrder.setUdata5( dto.getUdata5() );

        return zhurongScjinggongOrder;
    }

    @Override
    public ZhurongScjinggongOrder toEntity(ZhurongScjinggongOrderPageQuery dto) {
        if ( dto == null ) {
            return null;
        }

        ZhurongScjinggongOrder zhurongScjinggongOrder = new ZhurongScjinggongOrder();

        zhurongScjinggongOrder.setIsDeleted( dto.getIsDeleted() );
        zhurongScjinggongOrder.setVersion( dto.getVersion() );
        zhurongScjinggongOrder.setCreatedBy( dto.getCreatedBy() );
        zhurongScjinggongOrder.setCreatedAt( dto.getCreatedAt() );
        zhurongScjinggongOrder.setUpdatedBy( dto.getUpdatedBy() );
        zhurongScjinggongOrder.setUpdatedAt( dto.getUpdatedAt() );
        zhurongScjinggongOrder.setIsRead( dto.getIsRead() );
        zhurongScjinggongOrder.setIsReviewed( dto.getIsReviewed() );
        zhurongScjinggongOrder.setInvalidState( dto.getInvalidState() );
        zhurongScjinggongOrder.setOrderCode( dto.getOrderCode() );
        zhurongScjinggongOrder.setOrderName( dto.getOrderName() );
        zhurongScjinggongOrder.setUdata1( dto.getUdata1() );
        zhurongScjinggongOrder.setUdata2( dto.getUdata2() );
        zhurongScjinggongOrder.setUdata3( dto.getUdata3() );
        zhurongScjinggongOrder.setUdata4( dto.getUdata4() );
        zhurongScjinggongOrder.setUdata5( dto.getUdata5() );

        return zhurongScjinggongOrder;
    }

    @Override
    public void updateFromDTO(ZhurongScjinggongOrderDTO dto, ZhurongScjinggongOrder entity) {
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
        if ( dto.getOrderCode() != null ) {
            entity.setOrderCode( dto.getOrderCode() );
        }
        if ( dto.getOrderName() != null ) {
            entity.setOrderName( dto.getOrderName() );
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
    }
}
