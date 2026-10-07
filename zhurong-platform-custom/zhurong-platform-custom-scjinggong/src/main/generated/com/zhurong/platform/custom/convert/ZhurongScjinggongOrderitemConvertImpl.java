package com.zhurong.platform.custom.convert;

import com.zhurong.platform.custom.dto.OrderItemRequestDTO;
import com.zhurong.platform.custom.dto.ZhurongScjinggongOrderitemDTO;
import com.zhurong.platform.custom.dto.ZhurongScjinggongOrderitemPageQuery;
import com.zhurong.platform.custom.entity.ZhurongScjinggongOrderitem;
import com.zhurong.platform.custom.vo.ZhurongScjinggongOrderitemVO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-10-07T12:51:54+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.10 (Eclipse Adoptium)"
)
@Component
public class ZhurongScjinggongOrderitemConvertImpl implements ZhurongScjinggongOrderitemConvert {

    @Override
    public ZhurongScjinggongOrderitemVO toVO(ZhurongScjinggongOrderitem entity) {
        if ( entity == null ) {
            return null;
        }

        ZhurongScjinggongOrderitemVO zhurongScjinggongOrderitemVO = new ZhurongScjinggongOrderitemVO();

        if ( entity.getId() != null ) {
            zhurongScjinggongOrderitemVO.setId( String.valueOf( entity.getId() ) );
        }
        zhurongScjinggongOrderitemVO.setIsDeleted( entity.getIsDeleted() );
        zhurongScjinggongOrderitemVO.setVersion( entity.getVersion() );
        if ( entity.getCreatedBy() != null ) {
            zhurongScjinggongOrderitemVO.setCreatedBy( String.valueOf( entity.getCreatedBy() ) );
        }
        zhurongScjinggongOrderitemVO.setCreatedAt( entity.getCreatedAt() );
        if ( entity.getUpdatedBy() != null ) {
            zhurongScjinggongOrderitemVO.setUpdatedBy( String.valueOf( entity.getUpdatedBy() ) );
        }
        zhurongScjinggongOrderitemVO.setUpdatedAt( entity.getUpdatedAt() );
        zhurongScjinggongOrderitemVO.setIsRead( entity.getIsRead() );
        zhurongScjinggongOrderitemVO.setIsReviewed( entity.getIsReviewed() );
        zhurongScjinggongOrderitemVO.setInvalidState( entity.getInvalidState() );
        zhurongScjinggongOrderitemVO.setPrdRef( entity.getPrdRef() );
        zhurongScjinggongOrderitemVO.setWrkRef( entity.getWrkRef() );
        zhurongScjinggongOrderitemVO.setCusRef( entity.getCusRef() );
        zhurongScjinggongOrderitemVO.setOrdRef( entity.getOrdRef() );
        zhurongScjinggongOrderitemVO.setQuantity( entity.getQuantity() );
        zhurongScjinggongOrderitemVO.setRdate( entity.getRdate() );
        zhurongScjinggongOrderitemVO.setUdata1( entity.getUdata1() );
        zhurongScjinggongOrderitemVO.setUdata2( entity.getUdata2() );
        zhurongScjinggongOrderitemVO.setUdata3( entity.getUdata3() );
        zhurongScjinggongOrderitemVO.setUdata4( entity.getUdata4() );
        zhurongScjinggongOrderitemVO.setUdata5( entity.getUdata5() );
        zhurongScjinggongOrderitemVO.setUdata6( entity.getUdata6() );
        zhurongScjinggongOrderitemVO.setUdata7( entity.getUdata7() );
        zhurongScjinggongOrderitemVO.setUdata8( entity.getUdata8() );
        zhurongScjinggongOrderitemVO.setUdata9( entity.getUdata9() );
        zhurongScjinggongOrderitemVO.setUdata10( entity.getUdata10() );
        zhurongScjinggongOrderitemVO.setUdata11( entity.getUdata11() );
        zhurongScjinggongOrderitemVO.setUdata12( entity.getUdata12() );
        zhurongScjinggongOrderitemVO.setOrderId( entity.getOrderId() );

        return zhurongScjinggongOrderitemVO;
    }

    @Override
    public List<ZhurongScjinggongOrderitemVO> toVOList(List<ZhurongScjinggongOrderitem> list) {
        if ( list == null ) {
            return null;
        }

        List<ZhurongScjinggongOrderitemVO> list1 = new ArrayList<ZhurongScjinggongOrderitemVO>( list.size() );
        for ( ZhurongScjinggongOrderitem zhurongScjinggongOrderitem : list ) {
            list1.add( toVO( zhurongScjinggongOrderitem ) );
        }

        return list1;
    }

    @Override
    public ZhurongScjinggongOrderitem toEntity(ZhurongScjinggongOrderitemDTO dto) {
        if ( dto == null ) {
            return null;
        }

        ZhurongScjinggongOrderitem zhurongScjinggongOrderitem = new ZhurongScjinggongOrderitem();

        zhurongScjinggongOrderitem.setId( dto.getId() );
        zhurongScjinggongOrderitem.setIsDeleted( dto.getIsDeleted() );
        zhurongScjinggongOrderitem.setVersion( dto.getVersion() );
        zhurongScjinggongOrderitem.setCreatedBy( dto.getCreatedBy() );
        zhurongScjinggongOrderitem.setCreatedAt( dto.getCreatedAt() );
        zhurongScjinggongOrderitem.setUpdatedBy( dto.getUpdatedBy() );
        zhurongScjinggongOrderitem.setUpdatedAt( dto.getUpdatedAt() );
        zhurongScjinggongOrderitem.setIsRead( dto.getIsRead() );
        zhurongScjinggongOrderitem.setIsReviewed( dto.getIsReviewed() );
        zhurongScjinggongOrderitem.setInvalidState( dto.getInvalidState() );
        zhurongScjinggongOrderitem.setPrdRef( dto.getPrdRef() );
        zhurongScjinggongOrderitem.setWrkRef( dto.getWrkRef() );
        zhurongScjinggongOrderitem.setCusRef( dto.getCusRef() );
        zhurongScjinggongOrderitem.setOrdRef( dto.getOrdRef() );
        zhurongScjinggongOrderitem.setQuantity( dto.getQuantity() );
        zhurongScjinggongOrderitem.setRdate( dto.getRdate() );
        zhurongScjinggongOrderitem.setUdata1( dto.getUdata1() );
        zhurongScjinggongOrderitem.setUdata2( dto.getUdata2() );
        zhurongScjinggongOrderitem.setUdata3( dto.getUdata3() );
        zhurongScjinggongOrderitem.setUdata4( dto.getUdata4() );
        zhurongScjinggongOrderitem.setUdata5( dto.getUdata5() );
        zhurongScjinggongOrderitem.setUdata6( dto.getUdata6() );
        zhurongScjinggongOrderitem.setUdata7( dto.getUdata7() );
        zhurongScjinggongOrderitem.setUdata8( dto.getUdata8() );
        zhurongScjinggongOrderitem.setUdata9( dto.getUdata9() );
        zhurongScjinggongOrderitem.setUdata10( dto.getUdata10() );
        zhurongScjinggongOrderitem.setUdata11( dto.getUdata11() );
        zhurongScjinggongOrderitem.setUdata12( dto.getUdata12() );
        zhurongScjinggongOrderitem.setOrderId( dto.getOrderId() );

        return zhurongScjinggongOrderitem;
    }

    @Override
    public ZhurongScjinggongOrderitem toEntity(ZhurongScjinggongOrderitemPageQuery dto) {
        if ( dto == null ) {
            return null;
        }

        ZhurongScjinggongOrderitem zhurongScjinggongOrderitem = new ZhurongScjinggongOrderitem();

        zhurongScjinggongOrderitem.setIsDeleted( dto.getIsDeleted() );
        zhurongScjinggongOrderitem.setVersion( dto.getVersion() );
        zhurongScjinggongOrderitem.setCreatedBy( dto.getCreatedBy() );
        zhurongScjinggongOrderitem.setCreatedAt( dto.getCreatedAt() );
        zhurongScjinggongOrderitem.setUpdatedBy( dto.getUpdatedBy() );
        zhurongScjinggongOrderitem.setUpdatedAt( dto.getUpdatedAt() );
        zhurongScjinggongOrderitem.setIsRead( dto.getIsRead() );
        zhurongScjinggongOrderitem.setIsReviewed( dto.getIsReviewed() );
        zhurongScjinggongOrderitem.setInvalidState( dto.getInvalidState() );
        zhurongScjinggongOrderitem.setPrdRef( dto.getPrdRef() );
        zhurongScjinggongOrderitem.setWrkRef( dto.getWrkRef() );
        zhurongScjinggongOrderitem.setCusRef( dto.getCusRef() );
        zhurongScjinggongOrderitem.setOrdRef( dto.getOrdRef() );
        zhurongScjinggongOrderitem.setQuantity( dto.getQuantity() );
        zhurongScjinggongOrderitem.setRdate( dto.getRdate() );
        zhurongScjinggongOrderitem.setUdata1( dto.getUdata1() );
        zhurongScjinggongOrderitem.setUdata2( dto.getUdata2() );
        zhurongScjinggongOrderitem.setUdata3( dto.getUdata3() );
        zhurongScjinggongOrderitem.setUdata4( dto.getUdata4() );
        zhurongScjinggongOrderitem.setUdata5( dto.getUdata5() );
        zhurongScjinggongOrderitem.setUdata6( dto.getUdata6() );
        zhurongScjinggongOrderitem.setUdata7( dto.getUdata7() );
        zhurongScjinggongOrderitem.setUdata8( dto.getUdata8() );
        zhurongScjinggongOrderitem.setUdata9( dto.getUdata9() );
        zhurongScjinggongOrderitem.setUdata10( dto.getUdata10() );
        zhurongScjinggongOrderitem.setUdata11( dto.getUdata11() );
        zhurongScjinggongOrderitem.setUdata12( dto.getUdata12() );
        zhurongScjinggongOrderitem.setOrderId( dto.getOrderId() );

        return zhurongScjinggongOrderitem;
    }

    @Override
    public List<ZhurongScjinggongOrderitem> toEntitys(List<OrderItemRequestDTO> dto) {
        if ( dto == null ) {
            return null;
        }

        List<ZhurongScjinggongOrderitem> list = new ArrayList<ZhurongScjinggongOrderitem>( dto.size() );
        for ( OrderItemRequestDTO orderItemRequestDTO : dto ) {
            list.add( orderItemRequestDTOToZhurongScjinggongOrderitem( orderItemRequestDTO ) );
        }

        return list;
    }

    @Override
    public ZhurongScjinggongOrderitemDTO toDTO(ZhurongScjinggongOrderitem entity) {
        if ( entity == null ) {
            return null;
        }

        ZhurongScjinggongOrderitemDTO zhurongScjinggongOrderitemDTO = new ZhurongScjinggongOrderitemDTO();

        zhurongScjinggongOrderitemDTO.setId( entity.getId() );
        zhurongScjinggongOrderitemDTO.setIsDeleted( entity.getIsDeleted() );
        zhurongScjinggongOrderitemDTO.setVersion( entity.getVersion() );
        zhurongScjinggongOrderitemDTO.setCreatedBy( entity.getCreatedBy() );
        zhurongScjinggongOrderitemDTO.setCreatedAt( entity.getCreatedAt() );
        zhurongScjinggongOrderitemDTO.setUpdatedBy( entity.getUpdatedBy() );
        zhurongScjinggongOrderitemDTO.setUpdatedAt( entity.getUpdatedAt() );
        zhurongScjinggongOrderitemDTO.setIsRead( entity.getIsRead() );
        zhurongScjinggongOrderitemDTO.setIsReviewed( entity.getIsReviewed() );
        zhurongScjinggongOrderitemDTO.setInvalidState( entity.getInvalidState() );
        zhurongScjinggongOrderitemDTO.setPrdRef( entity.getPrdRef() );
        zhurongScjinggongOrderitemDTO.setWrkRef( entity.getWrkRef() );
        zhurongScjinggongOrderitemDTO.setCusRef( entity.getCusRef() );
        zhurongScjinggongOrderitemDTO.setOrdRef( entity.getOrdRef() );
        zhurongScjinggongOrderitemDTO.setQuantity( entity.getQuantity() );
        zhurongScjinggongOrderitemDTO.setRdate( entity.getRdate() );
        zhurongScjinggongOrderitemDTO.setUdata1( entity.getUdata1() );
        zhurongScjinggongOrderitemDTO.setUdata2( entity.getUdata2() );
        zhurongScjinggongOrderitemDTO.setUdata3( entity.getUdata3() );
        zhurongScjinggongOrderitemDTO.setUdata4( entity.getUdata4() );
        zhurongScjinggongOrderitemDTO.setUdata5( entity.getUdata5() );
        zhurongScjinggongOrderitemDTO.setUdata6( entity.getUdata6() );
        zhurongScjinggongOrderitemDTO.setUdata7( entity.getUdata7() );
        zhurongScjinggongOrderitemDTO.setUdata8( entity.getUdata8() );
        zhurongScjinggongOrderitemDTO.setUdata9( entity.getUdata9() );
        zhurongScjinggongOrderitemDTO.setUdata10( entity.getUdata10() );
        zhurongScjinggongOrderitemDTO.setUdata11( entity.getUdata11() );
        zhurongScjinggongOrderitemDTO.setUdata12( entity.getUdata12() );
        zhurongScjinggongOrderitemDTO.setOrderId( entity.getOrderId() );

        return zhurongScjinggongOrderitemDTO;
    }

    @Override
    public List<ZhurongScjinggongOrderitemDTO> toDTO(List<ZhurongScjinggongOrderitem> entity) {
        if ( entity == null ) {
            return null;
        }

        List<ZhurongScjinggongOrderitemDTO> list = new ArrayList<ZhurongScjinggongOrderitemDTO>( entity.size() );
        for ( ZhurongScjinggongOrderitem zhurongScjinggongOrderitem : entity ) {
            list.add( toDTO( zhurongScjinggongOrderitem ) );
        }

        return list;
    }

    @Override
    public void updateFromDTO(ZhurongScjinggongOrderitemDTO dto, ZhurongScjinggongOrderitem entity) {
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
        if ( dto.getWrkRef() != null ) {
            entity.setWrkRef( dto.getWrkRef() );
        }
        if ( dto.getCusRef() != null ) {
            entity.setCusRef( dto.getCusRef() );
        }
        if ( dto.getOrdRef() != null ) {
            entity.setOrdRef( dto.getOrdRef() );
        }
        if ( dto.getQuantity() != null ) {
            entity.setQuantity( dto.getQuantity() );
        }
        if ( dto.getRdate() != null ) {
            entity.setRdate( dto.getRdate() );
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
        if ( dto.getUdata9() != null ) {
            entity.setUdata9( dto.getUdata9() );
        }
        if ( dto.getUdata10() != null ) {
            entity.setUdata10( dto.getUdata10() );
        }
        if ( dto.getUdata11() != null ) {
            entity.setUdata11( dto.getUdata11() );
        }
        if ( dto.getUdata12() != null ) {
            entity.setUdata12( dto.getUdata12() );
        }
        if ( dto.getOrderId() != null ) {
            entity.setOrderId( dto.getOrderId() );
        }
    }

    protected ZhurongScjinggongOrderitem orderItemRequestDTOToZhurongScjinggongOrderitem(OrderItemRequestDTO orderItemRequestDTO) {
        if ( orderItemRequestDTO == null ) {
            return null;
        }

        ZhurongScjinggongOrderitem zhurongScjinggongOrderitem = new ZhurongScjinggongOrderitem();

        zhurongScjinggongOrderitem.setPrdRef( orderItemRequestDTO.getPrdRef() );
        zhurongScjinggongOrderitem.setWrkRef( orderItemRequestDTO.getWrkRef() );
        zhurongScjinggongOrderitem.setCusRef( orderItemRequestDTO.getCusRef() );
        zhurongScjinggongOrderitem.setOrdRef( orderItemRequestDTO.getOrdRef() );
        zhurongScjinggongOrderitem.setQuantity( orderItemRequestDTO.getQuantity() );
        zhurongScjinggongOrderitem.setRdate( orderItemRequestDTO.getRdate() );
        zhurongScjinggongOrderitem.setUdata1( orderItemRequestDTO.getUdata1() );
        zhurongScjinggongOrderitem.setUdata2( orderItemRequestDTO.getUdata2() );
        zhurongScjinggongOrderitem.setUdata3( orderItemRequestDTO.getUdata3() );
        zhurongScjinggongOrderitem.setUdata4( orderItemRequestDTO.getUdata4() );
        zhurongScjinggongOrderitem.setUdata5( orderItemRequestDTO.getUdata5() );
        zhurongScjinggongOrderitem.setUdata6( orderItemRequestDTO.getUdata6() );
        zhurongScjinggongOrderitem.setUdata7( orderItemRequestDTO.getUdata7() );
        zhurongScjinggongOrderitem.setUdata8( orderItemRequestDTO.getUdata8() );
        zhurongScjinggongOrderitem.setUdata9( orderItemRequestDTO.getUdata9() );
        zhurongScjinggongOrderitem.setUdata10( orderItemRequestDTO.getUdata10() );
        zhurongScjinggongOrderitem.setUdata11( orderItemRequestDTO.getUdata11() );
        zhurongScjinggongOrderitem.setUdata12( orderItemRequestDTO.getUdata12() );

        return zhurongScjinggongOrderitem;
    }
}
