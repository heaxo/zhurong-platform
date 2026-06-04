package com.zhurong.platform.custom.sap.convert;

import com.zhurong.platform.custom.sap.dto.AvaInventoryQtyDTO;
import com.zhurong.platform.custom.sap.entity.AvaInventoryQty;
import com.zhurong.platform.custom.sap.vo.AvaInventoryQtyVO;
import com.zhurong.platform.custom.sbut.entity.SbutAvaInventoryQty;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-06-04T19:43:09+0800",
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
    public SbutAvaInventoryQty toEntity(AvaInventoryQty entity) {
        if ( entity == null ) {
            return null;
        }

        SbutAvaInventoryQty sbutAvaInventoryQty = new SbutAvaInventoryQty();

        sbutAvaInventoryQty.setItemCode( entity.getItemCode() );
        sbutAvaInventoryQty.setItemName( entity.getItemName() );
        sbutAvaInventoryQty.setUBeasZnr( entity.getUBeasZnr() );
        sbutAvaInventoryQty.setUHd( entity.getUHd() );
        sbutAvaInventoryQty.setBatchNum( entity.getBatchNum() );
        sbutAvaInventoryQty.setQuantity( entity.getQuantity() );
        sbutAvaInventoryQty.setWidth( entity.getWidth() );
        sbutAvaInventoryQty.setLength( entity.getLength() );
        sbutAvaInventoryQty.setWhsName( entity.getWhsName() );
        sbutAvaInventoryQty.setWeight( entity.getWeight() );
        sbutAvaInventoryQty.setLocName( entity.getLocName() );
        sbutAvaInventoryQty.setCompany( entity.getCompany() );

        return sbutAvaInventoryQty;
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

    @Override
    public List<AvaInventoryQtyVO> toVOFromSbut(List<SbutAvaInventoryQty> entity) {
        if ( entity == null ) {
            return null;
        }

        List<AvaInventoryQtyVO> list = new ArrayList<AvaInventoryQtyVO>( entity.size() );
        for ( SbutAvaInventoryQty sbutAvaInventoryQty : entity ) {
            list.add( sbutAvaInventoryQtyToAvaInventoryQtyVO( sbutAvaInventoryQty ) );
        }

        return list;
    }

    @Override
    public List<AvaInventoryQty> toEntityFromSbut(List<SbutAvaInventoryQty> entity) {
        if ( entity == null ) {
            return null;
        }

        List<AvaInventoryQty> list = new ArrayList<AvaInventoryQty>( entity.size() );
        for ( SbutAvaInventoryQty sbutAvaInventoryQty : entity ) {
            list.add( sbutAvaInventoryQtyToAvaInventoryQty( sbutAvaInventoryQty ) );
        }

        return list;
    }

    protected AvaInventoryQtyVO sbutAvaInventoryQtyToAvaInventoryQtyVO(SbutAvaInventoryQty sbutAvaInventoryQty) {
        if ( sbutAvaInventoryQty == null ) {
            return null;
        }

        AvaInventoryQtyVO avaInventoryQtyVO = new AvaInventoryQtyVO();

        avaInventoryQtyVO.setItemCode( sbutAvaInventoryQty.getItemCode() );
        avaInventoryQtyVO.setItemName( sbutAvaInventoryQty.getItemName() );
        avaInventoryQtyVO.setUBeasZnr( sbutAvaInventoryQty.getUBeasZnr() );
        avaInventoryQtyVO.setUHd( sbutAvaInventoryQty.getUHd() );
        avaInventoryQtyVO.setBatchNum( sbutAvaInventoryQty.getBatchNum() );
        avaInventoryQtyVO.setQuantity( sbutAvaInventoryQty.getQuantity() );
        avaInventoryQtyVO.setWidth( sbutAvaInventoryQty.getWidth() );
        avaInventoryQtyVO.setLength( sbutAvaInventoryQty.getLength() );
        avaInventoryQtyVO.setWhsName( sbutAvaInventoryQty.getWhsName() );
        avaInventoryQtyVO.setWeight( sbutAvaInventoryQty.getWeight() );
        avaInventoryQtyVO.setCompany( sbutAvaInventoryQty.getCompany() );

        return avaInventoryQtyVO;
    }

    protected AvaInventoryQty sbutAvaInventoryQtyToAvaInventoryQty(SbutAvaInventoryQty sbutAvaInventoryQty) {
        if ( sbutAvaInventoryQty == null ) {
            return null;
        }

        AvaInventoryQty avaInventoryQty = new AvaInventoryQty();

        avaInventoryQty.setItemCode( sbutAvaInventoryQty.getItemCode() );
        avaInventoryQty.setItemName( sbutAvaInventoryQty.getItemName() );
        avaInventoryQty.setUBeasZnr( sbutAvaInventoryQty.getUBeasZnr() );
        avaInventoryQty.setUHd( sbutAvaInventoryQty.getUHd() );
        avaInventoryQty.setBatchNum( sbutAvaInventoryQty.getBatchNum() );
        avaInventoryQty.setQuantity( sbutAvaInventoryQty.getQuantity() );
        avaInventoryQty.setWidth( sbutAvaInventoryQty.getWidth() );
        avaInventoryQty.setLength( sbutAvaInventoryQty.getLength() );
        avaInventoryQty.setWhsName( sbutAvaInventoryQty.getWhsName() );
        avaInventoryQty.setWeight( sbutAvaInventoryQty.getWeight() );
        avaInventoryQty.setLocName( sbutAvaInventoryQty.getLocName() );
        avaInventoryQty.setCompany( sbutAvaInventoryQty.getCompany() );

        return avaInventoryQty;
    }
}
