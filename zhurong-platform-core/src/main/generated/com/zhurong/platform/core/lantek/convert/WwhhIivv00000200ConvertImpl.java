package com.zhurong.platform.core.lantek.convert;

import com.zhurong.platform.core.lantek.dto.WwhhIivv00000200DTO;
import com.zhurong.platform.core.lantek.dto.WwhhIivv00000200PageQuery;
import com.zhurong.platform.core.lantek.entity.WwhhIivv00000200;
import com.zhurong.platform.core.lantek.vo.WwhhIivv00000200VO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-04-18T19:39:23+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.10 (Eclipse Adoptium)"
)
@Component
public class WwhhIivv00000200ConvertImpl implements WwhhIivv00000200Convert {

    @Override
    public WwhhIivv00000200VO toVO(WwhhIivv00000200 entity) {
        if ( entity == null ) {
            return null;
        }

        WwhhIivv00000200VO wwhhIivv00000200VO = new WwhhIivv00000200VO();

        wwhhIivv00000200VO.setWrhRef( entity.getWrhRef() );
        wwhhIivv00000200VO.setLocRef( entity.getLocRef() );
        wwhhIivv00000200VO.setPrdRef( entity.getPrdRef() );
        wwhhIivv00000200VO.setPrdName( entity.getPrdName() );
        wwhhIivv00000200VO.setStockID( entity.getStockID() );
        wwhhIivv00000200VO.setUCtName( entity.getUCtName() );
        wwhhIivv00000200VO.setUntName( entity.getUntName() );
        wwhhIivv00000200VO.setARCost( entity.getARCost() );
        wwhhIivv00000200VO.setStockQ( entity.getStockQ() );
        wwhhIivv00000200VO.setWeight( entity.getWeight() );
        wwhhIivv00000200VO.setRWeight( entity.getRWeight() );
        wwhhIivv00000200VO.setIsSemifinished( entity.getIsSemifinished() );
        wwhhIivv00000200VO.setMnORef( entity.getMnORef() );
        wwhhIivv00000200VO.setOprID( entity.getOprID() );
        wwhhIivv00000200VO.setGLS_Var1( entity.getGLS_Var1() );
        wwhhIivv00000200VO.setGLS_Var2( entity.getGLS_Var2() );
        wwhhIivv00000200VO.setGLS_Var3( entity.getGLS_Var3() );
        wwhhIivv00000200VO.setGLS_Var4( entity.getGLS_Var4() );
        wwhhIivv00000200VO.setGLS_Var5( entity.getGLS_Var5() );
        wwhhIivv00000200VO.setGLS_SerialNo( entity.getGLS_SerialNo() );
        wwhhIivv00000200VO.setGLS_BatchNo( entity.getGLS_BatchNo() );
        wwhhIivv00000200VO.setRecState( entity.getRecState() );
        wwhhIivv00000200VO.setCrtDate( entity.getCrtDate() );
        wwhhIivv00000200VO.setLastDate( entity.getLastDate() );
        wwhhIivv00000200VO.setCrtUser( entity.getCrtUser() );
        wwhhIivv00000200VO.setLastUser( entity.getLastUser() );
        wwhhIivv00000200VO.setOwner( entity.getOwner() );
        wwhhIivv00000200VO.setRecEnt( entity.getRecEnt() );
        wwhhIivv00000200VO.setRecOU( entity.getRecOU() );
        wwhhIivv00000200VO.setRecSec( entity.getRecSec() );
        wwhhIivv00000200VO.setCntID( entity.getCntID() );
        wwhhIivv00000200VO.setRecID( entity.getRecID() );

        return wwhhIivv00000200VO;
    }

    @Override
    public List<WwhhIivv00000200VO> toVOList(List<WwhhIivv00000200> list) {
        if ( list == null ) {
            return null;
        }

        List<WwhhIivv00000200VO> list1 = new ArrayList<WwhhIivv00000200VO>( list.size() );
        for ( WwhhIivv00000200 wwhhIivv00000200 : list ) {
            list1.add( toVO( wwhhIivv00000200 ) );
        }

        return list1;
    }

    @Override
    public WwhhIivv00000200 toEntity(WwhhIivv00000200DTO dto) {
        if ( dto == null ) {
            return null;
        }

        WwhhIivv00000200 wwhhIivv00000200 = new WwhhIivv00000200();

        wwhhIivv00000200.setWrhRef( dto.getWrhRef() );
        wwhhIivv00000200.setLocRef( dto.getLocRef() );
        wwhhIivv00000200.setPrdRef( dto.getPrdRef() );
        wwhhIivv00000200.setPrdName( dto.getPrdName() );
        wwhhIivv00000200.setStockID( dto.getStockID() );
        wwhhIivv00000200.setUCtName( dto.getUCtName() );
        wwhhIivv00000200.setUntName( dto.getUntName() );
        wwhhIivv00000200.setARCost( dto.getARCost() );
        wwhhIivv00000200.setStockQ( dto.getStockQ() );
        wwhhIivv00000200.setWeight( dto.getWeight() );
        wwhhIivv00000200.setRWeight( dto.getRWeight() );
        wwhhIivv00000200.setIsSemifinished( dto.getIsSemifinished() );
        wwhhIivv00000200.setMnORef( dto.getMnORef() );
        wwhhIivv00000200.setOprID( dto.getOprID() );
        wwhhIivv00000200.setGLS_Var1( dto.getGLS_Var1() );
        wwhhIivv00000200.setGLS_Var2( dto.getGLS_Var2() );
        wwhhIivv00000200.setGLS_Var3( dto.getGLS_Var3() );
        wwhhIivv00000200.setGLS_Var4( dto.getGLS_Var4() );
        wwhhIivv00000200.setGLS_Var5( dto.getGLS_Var5() );
        wwhhIivv00000200.setGLS_SerialNo( dto.getGLS_SerialNo() );
        wwhhIivv00000200.setGLS_BatchNo( dto.getGLS_BatchNo() );
        wwhhIivv00000200.setRecState( dto.getRecState() );
        wwhhIivv00000200.setCrtDate( dto.getCrtDate() );
        wwhhIivv00000200.setLastDate( dto.getLastDate() );
        wwhhIivv00000200.setCrtUser( dto.getCrtUser() );
        wwhhIivv00000200.setLastUser( dto.getLastUser() );
        wwhhIivv00000200.setOwner( dto.getOwner() );
        wwhhIivv00000200.setRecEnt( dto.getRecEnt() );
        wwhhIivv00000200.setRecOU( dto.getRecOU() );
        wwhhIivv00000200.setRecSec( dto.getRecSec() );
        wwhhIivv00000200.setCntID( dto.getCntID() );
        wwhhIivv00000200.setRecID( dto.getRecID() );

        return wwhhIivv00000200;
    }

    @Override
    public WwhhIivv00000200 toEntity(WwhhIivv00000200PageQuery dto) {
        if ( dto == null ) {
            return null;
        }

        WwhhIivv00000200 wwhhIivv00000200 = new WwhhIivv00000200();

        wwhhIivv00000200.setWrhRef( dto.getWrhRef() );
        wwhhIivv00000200.setLocRef( dto.getLocRef() );
        wwhhIivv00000200.setPrdRef( dto.getPrdRef() );
        wwhhIivv00000200.setPrdName( dto.getPrdName() );
        wwhhIivv00000200.setStockID( dto.getStockID() );
        wwhhIivv00000200.setUCtName( dto.getUCtName() );
        wwhhIivv00000200.setUntName( dto.getUntName() );
        wwhhIivv00000200.setARCost( dto.getARCost() );
        wwhhIivv00000200.setStockQ( dto.getStockQ() );
        wwhhIivv00000200.setWeight( dto.getWeight() );
        wwhhIivv00000200.setRWeight( dto.getRWeight() );
        wwhhIivv00000200.setIsSemifinished( dto.getIsSemifinished() );
        wwhhIivv00000200.setMnORef( dto.getMnORef() );
        wwhhIivv00000200.setOprID( dto.getOprID() );
        wwhhIivv00000200.setRecState( dto.getRecState() );
        wwhhIivv00000200.setCrtDate( dto.getCrtDate() );
        wwhhIivv00000200.setLastDate( dto.getLastDate() );
        wwhhIivv00000200.setCrtUser( dto.getCrtUser() );
        wwhhIivv00000200.setLastUser( dto.getLastUser() );
        wwhhIivv00000200.setOwner( dto.getOwner() );
        wwhhIivv00000200.setRecEnt( dto.getRecEnt() );
        wwhhIivv00000200.setRecOU( dto.getRecOU() );
        wwhhIivv00000200.setRecSec( dto.getRecSec() );
        wwhhIivv00000200.setCntID( dto.getCntID() );
        wwhhIivv00000200.setRecID( dto.getRecID() );

        return wwhhIivv00000200;
    }

    @Override
    public void updateFromDTO(WwhhIivv00000200DTO dto, WwhhIivv00000200 entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getWrhRef() != null ) {
            entity.setWrhRef( dto.getWrhRef() );
        }
        if ( dto.getLocRef() != null ) {
            entity.setLocRef( dto.getLocRef() );
        }
        if ( dto.getPrdRef() != null ) {
            entity.setPrdRef( dto.getPrdRef() );
        }
        if ( dto.getPrdName() != null ) {
            entity.setPrdName( dto.getPrdName() );
        }
        if ( dto.getStockID() != null ) {
            entity.setStockID( dto.getStockID() );
        }
        if ( dto.getUCtName() != null ) {
            entity.setUCtName( dto.getUCtName() );
        }
        if ( dto.getUntName() != null ) {
            entity.setUntName( dto.getUntName() );
        }
        if ( dto.getARCost() != null ) {
            entity.setARCost( dto.getARCost() );
        }
        if ( dto.getStockQ() != null ) {
            entity.setStockQ( dto.getStockQ() );
        }
        if ( dto.getWeight() != null ) {
            entity.setWeight( dto.getWeight() );
        }
        if ( dto.getRWeight() != null ) {
            entity.setRWeight( dto.getRWeight() );
        }
        if ( dto.getIsSemifinished() != null ) {
            entity.setIsSemifinished( dto.getIsSemifinished() );
        }
        if ( dto.getMnORef() != null ) {
            entity.setMnORef( dto.getMnORef() );
        }
        if ( dto.getOprID() != null ) {
            entity.setOprID( dto.getOprID() );
        }
        if ( dto.getGLS_Var1() != null ) {
            entity.setGLS_Var1( dto.getGLS_Var1() );
        }
        if ( dto.getGLS_Var2() != null ) {
            entity.setGLS_Var2( dto.getGLS_Var2() );
        }
        if ( dto.getGLS_Var3() != null ) {
            entity.setGLS_Var3( dto.getGLS_Var3() );
        }
        if ( dto.getGLS_Var4() != null ) {
            entity.setGLS_Var4( dto.getGLS_Var4() );
        }
        if ( dto.getGLS_Var5() != null ) {
            entity.setGLS_Var5( dto.getGLS_Var5() );
        }
        if ( dto.getGLS_SerialNo() != null ) {
            entity.setGLS_SerialNo( dto.getGLS_SerialNo() );
        }
        if ( dto.getGLS_BatchNo() != null ) {
            entity.setGLS_BatchNo( dto.getGLS_BatchNo() );
        }
        if ( dto.getRecState() != null ) {
            entity.setRecState( dto.getRecState() );
        }
        if ( dto.getCrtDate() != null ) {
            entity.setCrtDate( dto.getCrtDate() );
        }
        if ( dto.getLastDate() != null ) {
            entity.setLastDate( dto.getLastDate() );
        }
        if ( dto.getCrtUser() != null ) {
            entity.setCrtUser( dto.getCrtUser() );
        }
        if ( dto.getLastUser() != null ) {
            entity.setLastUser( dto.getLastUser() );
        }
        if ( dto.getOwner() != null ) {
            entity.setOwner( dto.getOwner() );
        }
        if ( dto.getRecEnt() != null ) {
            entity.setRecEnt( dto.getRecEnt() );
        }
        if ( dto.getRecOU() != null ) {
            entity.setRecOU( dto.getRecOU() );
        }
        if ( dto.getRecSec() != null ) {
            entity.setRecSec( dto.getRecSec() );
        }
        if ( dto.getCntID() != null ) {
            entity.setCntID( dto.getCntID() );
        }
        if ( dto.getRecID() != null ) {
            entity.setRecID( dto.getRecID() );
        }
    }
}
