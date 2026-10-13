package com.zhurong.platform.custom.convert;

import com.zhurong.platform.custom.dto.ZhurongScjinggongSteelplateinventoryDTO;
import com.zhurong.platform.custom.dto.ZhurongScjinggongSteelplateinventoryPageQuery;
import com.zhurong.platform.custom.entity.ZhurongScjinggongSteelplateinventory;
import com.zhurong.platform.custom.vo.ZhurongScjinggongSteelplateinventoryVO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-10-13T21:10:07+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.10 (Eclipse Adoptium)"
)
@Component
public class ZhurongScjinggongSteelplateinventoryConvertImpl implements ZhurongScjinggongSteelplateinventoryConvert {

    @Override
    public ZhurongScjinggongSteelplateinventoryVO toVO(ZhurongScjinggongSteelplateinventory entity) {
        if ( entity == null ) {
            return null;
        }

        ZhurongScjinggongSteelplateinventoryVO zhurongScjinggongSteelplateinventoryVO = new ZhurongScjinggongSteelplateinventoryVO();

        if ( entity.getId() != null ) {
            zhurongScjinggongSteelplateinventoryVO.setId( String.valueOf( entity.getId() ) );
        }
        zhurongScjinggongSteelplateinventoryVO.setIsDeleted( entity.getIsDeleted() );
        zhurongScjinggongSteelplateinventoryVO.setVersion( entity.getVersion() );
        if ( entity.getCreatedBy() != null ) {
            zhurongScjinggongSteelplateinventoryVO.setCreatedBy( String.valueOf( entity.getCreatedBy() ) );
        }
        zhurongScjinggongSteelplateinventoryVO.setCreatedAt( entity.getCreatedAt() );
        if ( entity.getUpdatedBy() != null ) {
            zhurongScjinggongSteelplateinventoryVO.setUpdatedBy( String.valueOf( entity.getUpdatedBy() ) );
        }
        zhurongScjinggongSteelplateinventoryVO.setUpdatedAt( entity.getUpdatedAt() );
        zhurongScjinggongSteelplateinventoryVO.setIsRead( entity.getIsRead() );
        zhurongScjinggongSteelplateinventoryVO.setIsReviewed( entity.getIsReviewed() );
        zhurongScjinggongSteelplateinventoryVO.setInvalidState( entity.getInvalidState() );
        zhurongScjinggongSteelplateinventoryVO.setShtRef( entity.getShtRef() );
        zhurongScjinggongSteelplateinventoryVO.setMatRef( entity.getMatRef() );
        zhurongScjinggongSteelplateinventoryVO.setThickness( entity.getThickness() );
        zhurongScjinggongSteelplateinventoryVO.setLength( entity.getLength() );
        zhurongScjinggongSteelplateinventoryVO.setWidth( entity.getWidth() );
        zhurongScjinggongSteelplateinventoryVO.setQuantity( entity.getQuantity() );
        zhurongScjinggongSteelplateinventoryVO.setUdata1( entity.getUdata1() );
        zhurongScjinggongSteelplateinventoryVO.setUdata2( entity.getUdata2() );
        zhurongScjinggongSteelplateinventoryVO.setUdata3( entity.getUdata3() );
        zhurongScjinggongSteelplateinventoryVO.setUdata4( entity.getUdata4() );
        zhurongScjinggongSteelplateinventoryVO.setUdata5( entity.getUdata5() );
        zhurongScjinggongSteelplateinventoryVO.setUdata6( entity.getUdata6() );
        zhurongScjinggongSteelplateinventoryVO.setUdata7( entity.getUdata7() );
        zhurongScjinggongSteelplateinventoryVO.setUdata8( entity.getUdata8() );
        zhurongScjinggongSteelplateinventoryVO.setUdata9( entity.getUdata9() );
        zhurongScjinggongSteelplateinventoryVO.setUdata10( entity.getUdata10() );
        zhurongScjinggongSteelplateinventoryVO.setUdata11( entity.getUdata11() );
        zhurongScjinggongSteelplateinventoryVO.setUdata12( entity.getUdata12() );
        zhurongScjinggongSteelplateinventoryVO.setUdata13( entity.getUdata13() );
        zhurongScjinggongSteelplateinventoryVO.setUdata14( entity.getUdata14() );
        zhurongScjinggongSteelplateinventoryVO.setUdata15( entity.getUdata15() );
        zhurongScjinggongSteelplateinventoryVO.setUdata16( entity.getUdata16() );
        zhurongScjinggongSteelplateinventoryVO.setUdata17( entity.getUdata17() );
        zhurongScjinggongSteelplateinventoryVO.setUdata18( entity.getUdata18() );
        zhurongScjinggongSteelplateinventoryVO.setUdata19( entity.getUdata19() );
        zhurongScjinggongSteelplateinventoryVO.setUdata20( entity.getUdata20() );

        return zhurongScjinggongSteelplateinventoryVO;
    }

    @Override
    public List<ZhurongScjinggongSteelplateinventoryVO> toVOList(List<ZhurongScjinggongSteelplateinventory> list) {
        if ( list == null ) {
            return null;
        }

        List<ZhurongScjinggongSteelplateinventoryVO> list1 = new ArrayList<ZhurongScjinggongSteelplateinventoryVO>( list.size() );
        for ( ZhurongScjinggongSteelplateinventory zhurongScjinggongSteelplateinventory : list ) {
            list1.add( toVO( zhurongScjinggongSteelplateinventory ) );
        }

        return list1;
    }

    @Override
    public ZhurongScjinggongSteelplateinventory toEntity(ZhurongScjinggongSteelplateinventoryDTO dto) {
        if ( dto == null ) {
            return null;
        }

        ZhurongScjinggongSteelplateinventory zhurongScjinggongSteelplateinventory = new ZhurongScjinggongSteelplateinventory();

        zhurongScjinggongSteelplateinventory.setId( dto.getId() );
        zhurongScjinggongSteelplateinventory.setIsDeleted( dto.getIsDeleted() );
        zhurongScjinggongSteelplateinventory.setVersion( dto.getVersion() );
        zhurongScjinggongSteelplateinventory.setCreatedBy( dto.getCreatedBy() );
        zhurongScjinggongSteelplateinventory.setCreatedAt( dto.getCreatedAt() );
        zhurongScjinggongSteelplateinventory.setUpdatedBy( dto.getUpdatedBy() );
        zhurongScjinggongSteelplateinventory.setUpdatedAt( dto.getUpdatedAt() );
        zhurongScjinggongSteelplateinventory.setIsRead( dto.getIsRead() );
        zhurongScjinggongSteelplateinventory.setIsReviewed( dto.getIsReviewed() );
        zhurongScjinggongSteelplateinventory.setInvalidState( dto.getInvalidState() );
        zhurongScjinggongSteelplateinventory.setShtRef( dto.getShtRef() );
        zhurongScjinggongSteelplateinventory.setMatRef( dto.getMatRef() );
        zhurongScjinggongSteelplateinventory.setThickness( dto.getThickness() );
        zhurongScjinggongSteelplateinventory.setLength( dto.getLength() );
        zhurongScjinggongSteelplateinventory.setWidth( dto.getWidth() );
        zhurongScjinggongSteelplateinventory.setQuantity( dto.getQuantity() );
        zhurongScjinggongSteelplateinventory.setUdata1( dto.getUdata1() );
        zhurongScjinggongSteelplateinventory.setUdata2( dto.getUdata2() );
        zhurongScjinggongSteelplateinventory.setUdata3( dto.getUdata3() );
        zhurongScjinggongSteelplateinventory.setUdata4( dto.getUdata4() );
        zhurongScjinggongSteelplateinventory.setUdata5( dto.getUdata5() );
        zhurongScjinggongSteelplateinventory.setUdata6( dto.getUdata6() );
        zhurongScjinggongSteelplateinventory.setUdata7( dto.getUdata7() );
        zhurongScjinggongSteelplateinventory.setUdata8( dto.getUdata8() );
        zhurongScjinggongSteelplateinventory.setUdata9( dto.getUdata9() );
        zhurongScjinggongSteelplateinventory.setUdata10( dto.getUdata10() );
        zhurongScjinggongSteelplateinventory.setUdata11( dto.getUdata11() );
        zhurongScjinggongSteelplateinventory.setUdata12( dto.getUdata12() );
        zhurongScjinggongSteelplateinventory.setUdata13( dto.getUdata13() );
        zhurongScjinggongSteelplateinventory.setUdata14( dto.getUdata14() );
        zhurongScjinggongSteelplateinventory.setUdata15( dto.getUdata15() );
        zhurongScjinggongSteelplateinventory.setUdata16( dto.getUdata16() );
        zhurongScjinggongSteelplateinventory.setUdata17( dto.getUdata17() );
        zhurongScjinggongSteelplateinventory.setUdata18( dto.getUdata18() );
        zhurongScjinggongSteelplateinventory.setUdata19( dto.getUdata19() );
        zhurongScjinggongSteelplateinventory.setUdata20( dto.getUdata20() );

        return zhurongScjinggongSteelplateinventory;
    }

    @Override
    public ZhurongScjinggongSteelplateinventory toEntity(ZhurongScjinggongSteelplateinventoryPageQuery dto) {
        if ( dto == null ) {
            return null;
        }

        ZhurongScjinggongSteelplateinventory zhurongScjinggongSteelplateinventory = new ZhurongScjinggongSteelplateinventory();

        zhurongScjinggongSteelplateinventory.setIsDeleted( dto.getIsDeleted() );
        zhurongScjinggongSteelplateinventory.setVersion( dto.getVersion() );
        zhurongScjinggongSteelplateinventory.setCreatedBy( dto.getCreatedBy() );
        zhurongScjinggongSteelplateinventory.setCreatedAt( dto.getCreatedAt() );
        zhurongScjinggongSteelplateinventory.setUpdatedBy( dto.getUpdatedBy() );
        zhurongScjinggongSteelplateinventory.setUpdatedAt( dto.getUpdatedAt() );
        zhurongScjinggongSteelplateinventory.setIsRead( dto.getIsRead() );
        zhurongScjinggongSteelplateinventory.setIsReviewed( dto.getIsReviewed() );
        zhurongScjinggongSteelplateinventory.setInvalidState( dto.getInvalidState() );
        zhurongScjinggongSteelplateinventory.setShtRef( dto.getShtRef() );
        zhurongScjinggongSteelplateinventory.setMatRef( dto.getMatRef() );
        zhurongScjinggongSteelplateinventory.setThickness( dto.getThickness() );
        zhurongScjinggongSteelplateinventory.setLength( dto.getLength() );
        zhurongScjinggongSteelplateinventory.setWidth( dto.getWidth() );
        zhurongScjinggongSteelplateinventory.setQuantity( dto.getQuantity() );
        zhurongScjinggongSteelplateinventory.setUdata1( dto.getUdata1() );
        zhurongScjinggongSteelplateinventory.setUdata2( dto.getUdata2() );
        zhurongScjinggongSteelplateinventory.setUdata3( dto.getUdata3() );
        zhurongScjinggongSteelplateinventory.setUdata4( dto.getUdata4() );
        zhurongScjinggongSteelplateinventory.setUdata5( dto.getUdata5() );
        zhurongScjinggongSteelplateinventory.setUdata6( dto.getUdata6() );
        zhurongScjinggongSteelplateinventory.setUdata7( dto.getUdata7() );
        zhurongScjinggongSteelplateinventory.setUdata8( dto.getUdata8() );
        zhurongScjinggongSteelplateinventory.setUdata9( dto.getUdata9() );
        zhurongScjinggongSteelplateinventory.setUdata10( dto.getUdata10() );
        zhurongScjinggongSteelplateinventory.setUdata11( dto.getUdata11() );
        zhurongScjinggongSteelplateinventory.setUdata12( dto.getUdata12() );
        zhurongScjinggongSteelplateinventory.setUdata13( dto.getUdata13() );
        zhurongScjinggongSteelplateinventory.setUdata14( dto.getUdata14() );
        zhurongScjinggongSteelplateinventory.setUdata15( dto.getUdata15() );
        zhurongScjinggongSteelplateinventory.setUdata16( dto.getUdata16() );
        zhurongScjinggongSteelplateinventory.setUdata17( dto.getUdata17() );
        zhurongScjinggongSteelplateinventory.setUdata18( dto.getUdata18() );
        zhurongScjinggongSteelplateinventory.setUdata19( dto.getUdata19() );
        zhurongScjinggongSteelplateinventory.setUdata20( dto.getUdata20() );

        return zhurongScjinggongSteelplateinventory;
    }

    @Override
    public void updateFromDTO(ZhurongScjinggongSteelplateinventoryDTO dto, ZhurongScjinggongSteelplateinventory entity) {
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
        if ( dto.getShtRef() != null ) {
            entity.setShtRef( dto.getShtRef() );
        }
        if ( dto.getMatRef() != null ) {
            entity.setMatRef( dto.getMatRef() );
        }
        if ( dto.getThickness() != null ) {
            entity.setThickness( dto.getThickness() );
        }
        if ( dto.getLength() != null ) {
            entity.setLength( dto.getLength() );
        }
        if ( dto.getWidth() != null ) {
            entity.setWidth( dto.getWidth() );
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
        if ( dto.getUdata13() != null ) {
            entity.setUdata13( dto.getUdata13() );
        }
        if ( dto.getUdata14() != null ) {
            entity.setUdata14( dto.getUdata14() );
        }
        if ( dto.getUdata15() != null ) {
            entity.setUdata15( dto.getUdata15() );
        }
        if ( dto.getUdata16() != null ) {
            entity.setUdata16( dto.getUdata16() );
        }
        if ( dto.getUdata17() != null ) {
            entity.setUdata17( dto.getUdata17() );
        }
        if ( dto.getUdata18() != null ) {
            entity.setUdata18( dto.getUdata18() );
        }
        if ( dto.getUdata19() != null ) {
            entity.setUdata19( dto.getUdata19() );
        }
        if ( dto.getUdata20() != null ) {
            entity.setUdata20( dto.getUdata20() );
        }
    }
}
