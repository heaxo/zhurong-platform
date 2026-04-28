package com.zhurong.platform.custom.sap.convert;

import com.zhurong.platform.custom.sap.dto.AvaInventoryQtyDTO;
import com.zhurong.platform.custom.sap.entity.AvaInventoryQty;
import com.zhurong.platform.custom.sap.vo.AvaInventoryQtyVO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-04-28T21:32:54+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.10 (Eclipse Adoptium)"
)
@Component
public class AvaInventoryQtyConvertImpl implements AvaInventoryQtyConvert {

    @Override
    public AvaInventoryQty toEntity(AvaInventoryQtyDTO dto) {
        if ( dto == null ) {
            return null;
        }

        AvaInventoryQty avaInventoryQty = new AvaInventoryQty();

        avaInventoryQty.setItemCode( dto.getItemCode() );
        avaInventoryQty.setItemName( dto.getItemName() );
        avaInventoryQty.setUBeasZnr( dto.getUBeasZnr() );
        avaInventoryQty.setUHd( dto.getUHd() );
        avaInventoryQty.setBatchNum( dto.getBatchNum() );
        avaInventoryQty.setQuantity( dto.getQuantity() );
        avaInventoryQty.setWidth( dto.getWidth() );
        avaInventoryQty.setLength( dto.getLength() );
        avaInventoryQty.setWhsName( dto.getWhsName() );
        avaInventoryQty.setWeight( dto.getWeight() );
        avaInventoryQty.setLocName( dto.getLocName() );
        avaInventoryQty.setCompany( dto.getCompany() );

        return avaInventoryQty;
    }

    @Override
    public AvaInventoryQtyVO toVO(AvaInventoryQty entity) {
        if ( entity == null ) {
            return null;
        }

        AvaInventoryQtyVO avaInventoryQtyVO = new AvaInventoryQtyVO();

        avaInventoryQtyVO.setItemCode( entity.getItemCode() );
        avaInventoryQtyVO.setItemName( entity.getItemName() );
        avaInventoryQtyVO.setUBeasZnr( entity.getUBeasZnr() );
        avaInventoryQtyVO.setUHd( entity.getUHd() );
        avaInventoryQtyVO.setBatchNum( entity.getBatchNum() );
        avaInventoryQtyVO.setQuantity( entity.getQuantity() );
        avaInventoryQtyVO.setWidth( entity.getWidth() );
        avaInventoryQtyVO.setLength( entity.getLength() );
        avaInventoryQtyVO.setWhsName( entity.getWhsName() );
        avaInventoryQtyVO.setWeight( entity.getWeight() );
        avaInventoryQtyVO.setCompany( entity.getCompany() );

        return avaInventoryQtyVO;
    }

    @Override
    public List<AvaInventoryQtyVO> toVO(List<AvaInventoryQty> entity) {
        if ( entity == null ) {
            return null;
        }

        List<AvaInventoryQtyVO> list = new ArrayList<AvaInventoryQtyVO>( entity.size() );
        for ( AvaInventoryQty avaInventoryQty : entity ) {
            list.add( toVO( avaInventoryQty ) );
        }

        return list;
    }
}
