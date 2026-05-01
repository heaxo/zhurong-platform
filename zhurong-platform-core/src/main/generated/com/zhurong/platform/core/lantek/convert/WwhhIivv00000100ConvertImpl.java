package com.zhurong.platform.core.lantek.convert;

import com.zhurong.platform.core.lantek.dto.WwhhIivv00000100DTO;
import com.zhurong.platform.core.lantek.dto.WwhhIivv00000100PageQuery;
import com.zhurong.platform.core.lantek.entity.WwhhIivv00000100;
import com.zhurong.platform.core.lantek.vo.WwhhIivv00000100VO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-01T18:49:29+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.10 (Eclipse Adoptium)"
)
@Component
public class WwhhIivv00000100ConvertImpl implements WwhhIivv00000100Convert {

    @Override
    public WwhhIivv00000100VO toVO(WwhhIivv00000100 entity) {
        if ( entity == null ) {
            return null;
        }

        WwhhIivv00000100VO wwhhIivv00000100VO = new WwhhIivv00000100VO();

        wwhhIivv00000100VO.setWrhRef( entity.getWrhRef() );
        wwhhIivv00000100VO.setPrdRef( entity.getPrdRef() );
        wwhhIivv00000100VO.setPrdName( entity.getPrdName() );
        wwhhIivv00000100VO.setUCtName( entity.getUCtName() );
        wwhhIivv00000100VO.setUntName( entity.getUntName() );
        wwhhIivv00000100VO.setStockQ( entity.getStockQ() );
        wwhhIivv00000100VO.setAllocatedQ( entity.getAllocatedQ() );
        wwhhIivv00000100VO.setOnOrderQ( entity.getOnOrderQ() );
        wwhhIivv00000100VO.setPendingQ( entity.getPendingQ() );
        wwhhIivv00000100VO.setUpdMethod( entity.getUpdMethod() );
        wwhhIivv00000100VO.setIsDefault( entity.getIsDefault() );
        wwhhIivv00000100VO.setMinStock( entity.getMinStock() );
        wwhhIivv00000100VO.setMinOrder( entity.getMinOrder() );
        wwhhIivv00000100VO.setMaxOrder( entity.getMaxOrder() );
        wwhhIivv00000100VO.setMultOrder( entity.getMultOrder() );
        wwhhIivv00000100VO.setStrategy( entity.getStrategy() );
        wwhhIivv00000100VO.setLocDefault( entity.getLocDefault() );
        wwhhIivv00000100VO.setWeight( entity.getWeight() );
        wwhhIivv00000100VO.setRWeight( entity.getRWeight() );
        wwhhIivv00000100VO.setIsSemifinished( entity.getIsSemifinished() );
        wwhhIivv00000100VO.setGLS_Var1( entity.getGLS_Var1() );
        wwhhIivv00000100VO.setGLS_Var2( entity.getGLS_Var2() );
        wwhhIivv00000100VO.setGLS_Var3( entity.getGLS_Var3() );
        wwhhIivv00000100VO.setGLS_Var4( entity.getGLS_Var4() );
        wwhhIivv00000100VO.setGLS_Var5( entity.getGLS_Var5() );
        wwhhIivv00000100VO.setRecState( entity.getRecState() );
        wwhhIivv00000100VO.setCrtDate( entity.getCrtDate() );
        wwhhIivv00000100VO.setLastDate( entity.getLastDate() );
        wwhhIivv00000100VO.setCrtUser( entity.getCrtUser() );
        wwhhIivv00000100VO.setLastUser( entity.getLastUser() );
        wwhhIivv00000100VO.setOwner( entity.getOwner() );
        wwhhIivv00000100VO.setRecEnt( entity.getRecEnt() );
        wwhhIivv00000100VO.setRecOU( entity.getRecOU() );
        wwhhIivv00000100VO.setRecSec( entity.getRecSec() );
        wwhhIivv00000100VO.setCntID( entity.getCntID() );
        wwhhIivv00000100VO.setRecID( entity.getRecID() );

        return wwhhIivv00000100VO;
    }

    @Override
    public List<WwhhIivv00000100VO> toVOList(List<WwhhIivv00000100> list) {
        if ( list == null ) {
            return null;
        }

        List<WwhhIivv00000100VO> list1 = new ArrayList<WwhhIivv00000100VO>( list.size() );
        for ( WwhhIivv00000100 wwhhIivv00000100 : list ) {
            list1.add( toVO( wwhhIivv00000100 ) );
        }

        return list1;
    }

    @Override
    public WwhhIivv00000100 toEntity(WwhhIivv00000100DTO dto) {
        if ( dto == null ) {
            return null;
        }

        WwhhIivv00000100 wwhhIivv00000100 = new WwhhIivv00000100();

        wwhhIivv00000100.setWrhRef( dto.getWrhRef() );
        wwhhIivv00000100.setPrdRef( dto.getPrdRef() );
        wwhhIivv00000100.setPrdName( dto.getPrdName() );
        wwhhIivv00000100.setUCtName( dto.getUCtName() );
        wwhhIivv00000100.setUntName( dto.getUntName() );
        wwhhIivv00000100.setStockQ( dto.getStockQ() );
        wwhhIivv00000100.setAllocatedQ( dto.getAllocatedQ() );
        wwhhIivv00000100.setOnOrderQ( dto.getOnOrderQ() );
        wwhhIivv00000100.setPendingQ( dto.getPendingQ() );
        wwhhIivv00000100.setUpdMethod( dto.getUpdMethod() );
        wwhhIivv00000100.setIsDefault( dto.getIsDefault() );
        wwhhIivv00000100.setMinStock( dto.getMinStock() );
        wwhhIivv00000100.setMinOrder( dto.getMinOrder() );
        wwhhIivv00000100.setMaxOrder( dto.getMaxOrder() );
        wwhhIivv00000100.setMultOrder( dto.getMultOrder() );
        wwhhIivv00000100.setStrategy( dto.getStrategy() );
        wwhhIivv00000100.setLocDefault( dto.getLocDefault() );
        wwhhIivv00000100.setWeight( dto.getWeight() );
        wwhhIivv00000100.setRWeight( dto.getRWeight() );
        wwhhIivv00000100.setIsSemifinished( dto.getIsSemifinished() );
        wwhhIivv00000100.setGLS_Var1( dto.getGLS_Var1() );
        wwhhIivv00000100.setGLS_Var2( dto.getGLS_Var2() );
        wwhhIivv00000100.setGLS_Var3( dto.getGLS_Var3() );
        wwhhIivv00000100.setGLS_Var4( dto.getGLS_Var4() );
        wwhhIivv00000100.setGLS_Var5( dto.getGLS_Var5() );
        wwhhIivv00000100.setRecState( dto.getRecState() );
        wwhhIivv00000100.setCrtDate( dto.getCrtDate() );
        wwhhIivv00000100.setLastDate( dto.getLastDate() );
        wwhhIivv00000100.setCrtUser( dto.getCrtUser() );
        wwhhIivv00000100.setLastUser( dto.getLastUser() );
        wwhhIivv00000100.setOwner( dto.getOwner() );
        wwhhIivv00000100.setRecEnt( dto.getRecEnt() );
        wwhhIivv00000100.setRecOU( dto.getRecOU() );
        wwhhIivv00000100.setRecSec( dto.getRecSec() );
        wwhhIivv00000100.setCntID( dto.getCntID() );
        wwhhIivv00000100.setRecID( dto.getRecID() );

        return wwhhIivv00000100;
    }

    @Override
    public WwhhIivv00000100 toEntity(WwhhIivv00000100PageQuery dto) {
        if ( dto == null ) {
            return null;
        }

        WwhhIivv00000100 wwhhIivv00000100 = new WwhhIivv00000100();

        wwhhIivv00000100.setWrhRef( dto.getWrhRef() );
        wwhhIivv00000100.setPrdRef( dto.getPrdRef() );
        wwhhIivv00000100.setPrdName( dto.getPrdName() );
        wwhhIivv00000100.setUCtName( dto.getUCtName() );
        wwhhIivv00000100.setUntName( dto.getUntName() );
        wwhhIivv00000100.setStockQ( dto.getStockQ() );
        wwhhIivv00000100.setAllocatedQ( dto.getAllocatedQ() );
        wwhhIivv00000100.setOnOrderQ( dto.getOnOrderQ() );
        wwhhIivv00000100.setPendingQ( dto.getPendingQ() );
        wwhhIivv00000100.setUpdMethod( dto.getUpdMethod() );
        wwhhIivv00000100.setIsDefault( dto.getIsDefault() );
        wwhhIivv00000100.setMinStock( dto.getMinStock() );
        wwhhIivv00000100.setMinOrder( dto.getMinOrder() );
        wwhhIivv00000100.setMaxOrder( dto.getMaxOrder() );
        wwhhIivv00000100.setMultOrder( dto.getMultOrder() );
        wwhhIivv00000100.setStrategy( dto.getStrategy() );
        wwhhIivv00000100.setLocDefault( dto.getLocDefault() );
        wwhhIivv00000100.setWeight( dto.getWeight() );
        wwhhIivv00000100.setRWeight( dto.getRWeight() );
        wwhhIivv00000100.setIsSemifinished( dto.getIsSemifinished() );
        wwhhIivv00000100.setRecState( dto.getRecState() );
        wwhhIivv00000100.setCrtDate( dto.getCrtDate() );
        wwhhIivv00000100.setLastDate( dto.getLastDate() );
        wwhhIivv00000100.setCrtUser( dto.getCrtUser() );
        wwhhIivv00000100.setLastUser( dto.getLastUser() );
        wwhhIivv00000100.setOwner( dto.getOwner() );
        wwhhIivv00000100.setRecEnt( dto.getRecEnt() );
        wwhhIivv00000100.setRecOU( dto.getRecOU() );
        wwhhIivv00000100.setRecSec( dto.getRecSec() );
        wwhhIivv00000100.setCntID( dto.getCntID() );
        wwhhIivv00000100.setRecID( dto.getRecID() );

        return wwhhIivv00000100;
    }

    @Override
    public void updateFromDTO(WwhhIivv00000100DTO dto, WwhhIivv00000100 entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getWrhRef() != null ) {
            entity.setWrhRef( dto.getWrhRef() );
        }
        if ( dto.getPrdRef() != null ) {
            entity.setPrdRef( dto.getPrdRef() );
        }
        if ( dto.getPrdName() != null ) {
            entity.setPrdName( dto.getPrdName() );
        }
        if ( dto.getUCtName() != null ) {
            entity.setUCtName( dto.getUCtName() );
        }
        if ( dto.getUntName() != null ) {
            entity.setUntName( dto.getUntName() );
        }
        if ( dto.getStockQ() != null ) {
            entity.setStockQ( dto.getStockQ() );
        }
        if ( dto.getAllocatedQ() != null ) {
            entity.setAllocatedQ( dto.getAllocatedQ() );
        }
        if ( dto.getOnOrderQ() != null ) {
            entity.setOnOrderQ( dto.getOnOrderQ() );
        }
        if ( dto.getPendingQ() != null ) {
            entity.setPendingQ( dto.getPendingQ() );
        }
        if ( dto.getUpdMethod() != null ) {
            entity.setUpdMethod( dto.getUpdMethod() );
        }
        if ( dto.getIsDefault() != null ) {
            entity.setIsDefault( dto.getIsDefault() );
        }
        if ( dto.getMinStock() != null ) {
            entity.setMinStock( dto.getMinStock() );
        }
        if ( dto.getMinOrder() != null ) {
            entity.setMinOrder( dto.getMinOrder() );
        }
        if ( dto.getMaxOrder() != null ) {
            entity.setMaxOrder( dto.getMaxOrder() );
        }
        if ( dto.getMultOrder() != null ) {
            entity.setMultOrder( dto.getMultOrder() );
        }
        if ( dto.getStrategy() != null ) {
            entity.setStrategy( dto.getStrategy() );
        }
        if ( dto.getLocDefault() != null ) {
            entity.setLocDefault( dto.getLocDefault() );
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
