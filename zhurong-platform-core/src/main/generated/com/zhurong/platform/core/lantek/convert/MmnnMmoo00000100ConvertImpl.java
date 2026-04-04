package com.zhurong.platform.core.lantek.convert;

import com.zhurong.platform.core.lantek.dto.MmnnMmoo00000100DTO;
import com.zhurong.platform.core.lantek.dto.MmnnMmoo00000100PageQuery;
import com.zhurong.platform.core.lantek.entity.MmnnMmoo00000100;
import com.zhurong.platform.core.lantek.vo.MmnnMmoo00000100VO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-04-04T11:40:49+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.10 (Eclipse Adoptium)"
)
@Component
public class MmnnMmoo00000100ConvertImpl implements MmnnMmoo00000100Convert {

    @Override
    public MmnnMmoo00000100VO toVO(MmnnMmoo00000100 entity) {
        if ( entity == null ) {
            return null;
        }

        MmnnMmoo00000100VO mmnnMmoo00000100VO = new MmnnMmoo00000100VO();

        mmnnMmoo00000100VO.setMnORef( entity.getMnORef() );
        mmnnMmoo00000100VO.setPrdRef( entity.getPrdRef() );
        mmnnMmoo00000100VO.setPrdName( entity.getPrdName() );
        mmnnMmoo00000100VO.setRouRef( entity.getRouRef() );
        mmnnMmoo00000100VO.setUCtName( entity.getUCtName() );
        mmnnMmoo00000100VO.setUntName( entity.getUntName() );
        mmnnMmoo00000100VO.setQuantity( entity.getQuantity() );
        mmnnMmoo00000100VO.setPriority( entity.getPriority() );
        mmnnMmoo00000100VO.setOrigin( entity.getOrigin() );
        mmnnMmoo00000100VO.setOrdRef( entity.getOrdRef() );
        mmnnMmoo00000100VO.setOLineNum( entity.getOLineNum() );
        mmnnMmoo00000100VO.setCusRef( entity.getCusRef() );
        mmnnMmoo00000100VO.setCusName( entity.getCusName() );
        mmnnMmoo00000100VO.setCDate( entity.getCDate() );
        mmnnMmoo00000100VO.setDDate( entity.getDDate() );
        mmnnMmoo00000100VO.setMState( entity.getMState() );
        mmnnMmoo00000100VO.setSDate( entity.getSDate() );
        mmnnMmoo00000100VO.setEDate( entity.getEDate() );
        mmnnMmoo00000100VO.setESDate( entity.getESDate() );
        mmnnMmoo00000100VO.setEEDate( entity.getEEDate() );
        mmnnMmoo00000100VO.setRDate( entity.getRDate() );
        mmnnMmoo00000100VO.setMadeQuan( entity.getMadeQuan() );
        mmnnMmoo00000100VO.setCost( entity.getCost() );
        mmnnMmoo00000100VO.setDescrip( entity.getDescrip() );
        mmnnMmoo00000100VO.setWrhRefSM( entity.getWrhRefSM() );
        mmnnMmoo00000100VO.setLineNumSM( entity.getLineNumSM() );
        mmnnMmoo00000100VO.setPlanned( entity.getPlanned() );
        mmnnMmoo00000100VO.setMnORefOrg( entity.getMnORefOrg() );
        mmnnMmoo00000100VO.setMnORoot( entity.getMnORoot() );
        mmnnMmoo00000100VO.setOrderType( entity.getOrderType() );
        mmnnMmoo00000100VO.setMainPackage( entity.getMainPackage() );
        mmnnMmoo00000100VO.setMainPackageName( entity.getMainPackageName() );
        mmnnMmoo00000100VO.setWorkPackage( entity.getWorkPackage() );
        mmnnMmoo00000100VO.setWorkPackageName( entity.getWorkPackageName() );
        mmnnMmoo00000100VO.setGLS_Var1( entity.getGLS_Var1() );
        mmnnMmoo00000100VO.setGLS_Var2( entity.getGLS_Var2() );
        mmnnMmoo00000100VO.setGLS_Var3( entity.getGLS_Var3() );
        mmnnMmoo00000100VO.setGLS_Var4( entity.getGLS_Var4() );
        mmnnMmoo00000100VO.setGLS_Var5( entity.getGLS_Var5() );
        mmnnMmoo00000100VO.setDIS_IsQuote( entity.getDIS_IsQuote() );
        mmnnMmoo00000100VO.setDIS_OutQ( entity.getDIS_OutQ() );
        mmnnMmoo00000100VO.setDIS_FillerPart( entity.getDIS_FillerPart() );
        mmnnMmoo00000100VO.setDIS_RouteAbbreviation( entity.getDIS_RouteAbbreviation() );
        mmnnMmoo00000100VO.setDIS_AutoNesting( entity.getDIS_AutoNesting() );
        mmnnMmoo00000100VO.setDIS_AutoNestingStrategy( entity.getDIS_AutoNestingStrategy() );
        mmnnMmoo00000100VO.setRecState( entity.getRecState() );
        mmnnMmoo00000100VO.setCrtDate( entity.getCrtDate() );
        mmnnMmoo00000100VO.setLastDate( entity.getLastDate() );
        mmnnMmoo00000100VO.setCrtUser( entity.getCrtUser() );
        mmnnMmoo00000100VO.setLastUser( entity.getLastUser() );
        mmnnMmoo00000100VO.setOwner( entity.getOwner() );
        mmnnMmoo00000100VO.setRecEnt( entity.getRecEnt() );
        mmnnMmoo00000100VO.setRecOU( entity.getRecOU() );
        mmnnMmoo00000100VO.setRecSec( entity.getRecSec() );
        mmnnMmoo00000100VO.setCntID( entity.getCntID() );
        mmnnMmoo00000100VO.setRecID( entity.getRecID() );

        return mmnnMmoo00000100VO;
    }

    @Override
    public List<MmnnMmoo00000100VO> toVOList(List<MmnnMmoo00000100> list) {
        if ( list == null ) {
            return null;
        }

        List<MmnnMmoo00000100VO> list1 = new ArrayList<MmnnMmoo00000100VO>( list.size() );
        for ( MmnnMmoo00000100 mmnnMmoo00000100 : list ) {
            list1.add( toVO( mmnnMmoo00000100 ) );
        }

        return list1;
    }

    @Override
    public MmnnMmoo00000100 toEntity(MmnnMmoo00000100DTO dto) {
        if ( dto == null ) {
            return null;
        }

        MmnnMmoo00000100 mmnnMmoo00000100 = new MmnnMmoo00000100();

        mmnnMmoo00000100.setMnORef( dto.getMnORef() );
        mmnnMmoo00000100.setPrdRef( dto.getPrdRef() );
        mmnnMmoo00000100.setPrdName( dto.getPrdName() );
        mmnnMmoo00000100.setRouRef( dto.getRouRef() );
        mmnnMmoo00000100.setUCtName( dto.getUCtName() );
        mmnnMmoo00000100.setUntName( dto.getUntName() );
        mmnnMmoo00000100.setQuantity( dto.getQuantity() );
        mmnnMmoo00000100.setPriority( dto.getPriority() );
        mmnnMmoo00000100.setOrigin( dto.getOrigin() );
        mmnnMmoo00000100.setOrdRef( dto.getOrdRef() );
        mmnnMmoo00000100.setOLineNum( dto.getOLineNum() );
        mmnnMmoo00000100.setCusRef( dto.getCusRef() );
        mmnnMmoo00000100.setCusName( dto.getCusName() );
        mmnnMmoo00000100.setCDate( dto.getCDate() );
        mmnnMmoo00000100.setDDate( dto.getDDate() );
        mmnnMmoo00000100.setMState( dto.getMState() );
        mmnnMmoo00000100.setSDate( dto.getSDate() );
        mmnnMmoo00000100.setEDate( dto.getEDate() );
        mmnnMmoo00000100.setESDate( dto.getESDate() );
        mmnnMmoo00000100.setEEDate( dto.getEEDate() );
        mmnnMmoo00000100.setRDate( dto.getRDate() );
        mmnnMmoo00000100.setMadeQuan( dto.getMadeQuan() );
        mmnnMmoo00000100.setCost( dto.getCost() );
        mmnnMmoo00000100.setDescrip( dto.getDescrip() );
        mmnnMmoo00000100.setWrhRefSM( dto.getWrhRefSM() );
        mmnnMmoo00000100.setLineNumSM( dto.getLineNumSM() );
        mmnnMmoo00000100.setPlanned( dto.getPlanned() );
        mmnnMmoo00000100.setMnORefOrg( dto.getMnORefOrg() );
        mmnnMmoo00000100.setMnORoot( dto.getMnORoot() );
        mmnnMmoo00000100.setOrderType( dto.getOrderType() );
        mmnnMmoo00000100.setMainPackage( dto.getMainPackage() );
        mmnnMmoo00000100.setMainPackageName( dto.getMainPackageName() );
        mmnnMmoo00000100.setWorkPackage( dto.getWorkPackage() );
        mmnnMmoo00000100.setWorkPackageName( dto.getWorkPackageName() );
        mmnnMmoo00000100.setGLS_Var1( dto.getGLS_Var1() );
        mmnnMmoo00000100.setGLS_Var2( dto.getGLS_Var2() );
        mmnnMmoo00000100.setGLS_Var3( dto.getGLS_Var3() );
        mmnnMmoo00000100.setGLS_Var4( dto.getGLS_Var4() );
        mmnnMmoo00000100.setGLS_Var5( dto.getGLS_Var5() );
        mmnnMmoo00000100.setDIS_IsQuote( dto.getDIS_IsQuote() );
        mmnnMmoo00000100.setDIS_OutQ( dto.getDIS_OutQ() );
        mmnnMmoo00000100.setDIS_FillerPart( dto.getDIS_FillerPart() );
        mmnnMmoo00000100.setDIS_RouteAbbreviation( dto.getDIS_RouteAbbreviation() );
        mmnnMmoo00000100.setDIS_AutoNesting( dto.getDIS_AutoNesting() );
        mmnnMmoo00000100.setDIS_AutoNestingStrategy( dto.getDIS_AutoNestingStrategy() );
        mmnnMmoo00000100.setRecState( dto.getRecState() );
        mmnnMmoo00000100.setCrtDate( dto.getCrtDate() );
        mmnnMmoo00000100.setLastDate( dto.getLastDate() );
        mmnnMmoo00000100.setCrtUser( dto.getCrtUser() );
        mmnnMmoo00000100.setLastUser( dto.getLastUser() );
        mmnnMmoo00000100.setOwner( dto.getOwner() );
        mmnnMmoo00000100.setRecEnt( dto.getRecEnt() );
        mmnnMmoo00000100.setRecOU( dto.getRecOU() );
        mmnnMmoo00000100.setRecSec( dto.getRecSec() );
        mmnnMmoo00000100.setCntID( dto.getCntID() );
        mmnnMmoo00000100.setRecID( dto.getRecID() );

        return mmnnMmoo00000100;
    }

    @Override
    public MmnnMmoo00000100 toEntity(MmnnMmoo00000100PageQuery dto) {
        if ( dto == null ) {
            return null;
        }

        MmnnMmoo00000100 mmnnMmoo00000100 = new MmnnMmoo00000100();

        mmnnMmoo00000100.setMnORef( dto.getMnORef() );
        mmnnMmoo00000100.setPrdRef( dto.getPrdRef() );
        mmnnMmoo00000100.setPrdName( dto.getPrdName() );
        mmnnMmoo00000100.setRouRef( dto.getRouRef() );
        mmnnMmoo00000100.setUCtName( dto.getUCtName() );
        mmnnMmoo00000100.setUntName( dto.getUntName() );
        mmnnMmoo00000100.setQuantity( dto.getQuantity() );
        mmnnMmoo00000100.setPriority( dto.getPriority() );
        mmnnMmoo00000100.setOrigin( dto.getOrigin() );
        mmnnMmoo00000100.setOrdRef( dto.getOrdRef() );
        mmnnMmoo00000100.setOLineNum( dto.getOLineNum() );
        mmnnMmoo00000100.setCusRef( dto.getCusRef() );
        mmnnMmoo00000100.setCusName( dto.getCusName() );
        mmnnMmoo00000100.setCDate( dto.getCDate() );
        mmnnMmoo00000100.setDDate( dto.getDDate() );
        mmnnMmoo00000100.setMState( dto.getMState() );
        mmnnMmoo00000100.setSDate( dto.getSDate() );
        mmnnMmoo00000100.setEDate( dto.getEDate() );
        mmnnMmoo00000100.setESDate( dto.getESDate() );
        mmnnMmoo00000100.setEEDate( dto.getEEDate() );
        mmnnMmoo00000100.setRDate( dto.getRDate() );
        mmnnMmoo00000100.setMadeQuan( dto.getMadeQuan() );
        mmnnMmoo00000100.setCost( dto.getCost() );
        mmnnMmoo00000100.setDescrip( dto.getDescrip() );
        mmnnMmoo00000100.setWrhRefSM( dto.getWrhRefSM() );
        mmnnMmoo00000100.setLineNumSM( dto.getLineNumSM() );
        mmnnMmoo00000100.setPlanned( dto.getPlanned() );
        mmnnMmoo00000100.setMnORefOrg( dto.getMnORefOrg() );
        mmnnMmoo00000100.setMnORoot( dto.getMnORoot() );
        mmnnMmoo00000100.setOrderType( dto.getOrderType() );
        mmnnMmoo00000100.setMainPackage( dto.getMainPackage() );
        mmnnMmoo00000100.setMainPackageName( dto.getMainPackageName() );
        mmnnMmoo00000100.setWorkPackage( dto.getWorkPackage() );
        mmnnMmoo00000100.setWorkPackageName( dto.getWorkPackageName() );
        mmnnMmoo00000100.setRecState( dto.getRecState() );
        mmnnMmoo00000100.setCrtDate( dto.getCrtDate() );
        mmnnMmoo00000100.setLastDate( dto.getLastDate() );
        mmnnMmoo00000100.setCrtUser( dto.getCrtUser() );
        mmnnMmoo00000100.setLastUser( dto.getLastUser() );
        mmnnMmoo00000100.setOwner( dto.getOwner() );
        mmnnMmoo00000100.setRecEnt( dto.getRecEnt() );
        mmnnMmoo00000100.setRecOU( dto.getRecOU() );
        mmnnMmoo00000100.setRecSec( dto.getRecSec() );
        mmnnMmoo00000100.setCntID( dto.getCntID() );
        mmnnMmoo00000100.setRecID( dto.getRecID() );

        return mmnnMmoo00000100;
    }

    @Override
    public void updateFromDTO(MmnnMmoo00000100DTO dto, MmnnMmoo00000100 entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getMnORef() != null ) {
            entity.setMnORef( dto.getMnORef() );
        }
        if ( dto.getPrdRef() != null ) {
            entity.setPrdRef( dto.getPrdRef() );
        }
        if ( dto.getPrdName() != null ) {
            entity.setPrdName( dto.getPrdName() );
        }
        if ( dto.getRouRef() != null ) {
            entity.setRouRef( dto.getRouRef() );
        }
        if ( dto.getUCtName() != null ) {
            entity.setUCtName( dto.getUCtName() );
        }
        if ( dto.getUntName() != null ) {
            entity.setUntName( dto.getUntName() );
        }
        if ( dto.getQuantity() != null ) {
            entity.setQuantity( dto.getQuantity() );
        }
        if ( dto.getPriority() != null ) {
            entity.setPriority( dto.getPriority() );
        }
        if ( dto.getOrigin() != null ) {
            entity.setOrigin( dto.getOrigin() );
        }
        if ( dto.getOrdRef() != null ) {
            entity.setOrdRef( dto.getOrdRef() );
        }
        if ( dto.getOLineNum() != null ) {
            entity.setOLineNum( dto.getOLineNum() );
        }
        if ( dto.getCusRef() != null ) {
            entity.setCusRef( dto.getCusRef() );
        }
        if ( dto.getCusName() != null ) {
            entity.setCusName( dto.getCusName() );
        }
        if ( dto.getCDate() != null ) {
            entity.setCDate( dto.getCDate() );
        }
        if ( dto.getDDate() != null ) {
            entity.setDDate( dto.getDDate() );
        }
        if ( dto.getMState() != null ) {
            entity.setMState( dto.getMState() );
        }
        if ( dto.getSDate() != null ) {
            entity.setSDate( dto.getSDate() );
        }
        if ( dto.getEDate() != null ) {
            entity.setEDate( dto.getEDate() );
        }
        if ( dto.getESDate() != null ) {
            entity.setESDate( dto.getESDate() );
        }
        if ( dto.getEEDate() != null ) {
            entity.setEEDate( dto.getEEDate() );
        }
        if ( dto.getRDate() != null ) {
            entity.setRDate( dto.getRDate() );
        }
        if ( dto.getMadeQuan() != null ) {
            entity.setMadeQuan( dto.getMadeQuan() );
        }
        if ( dto.getCost() != null ) {
            entity.setCost( dto.getCost() );
        }
        if ( dto.getDescrip() != null ) {
            entity.setDescrip( dto.getDescrip() );
        }
        if ( dto.getWrhRefSM() != null ) {
            entity.setWrhRefSM( dto.getWrhRefSM() );
        }
        if ( dto.getLineNumSM() != null ) {
            entity.setLineNumSM( dto.getLineNumSM() );
        }
        if ( dto.getPlanned() != null ) {
            entity.setPlanned( dto.getPlanned() );
        }
        if ( dto.getMnORefOrg() != null ) {
            entity.setMnORefOrg( dto.getMnORefOrg() );
        }
        if ( dto.getMnORoot() != null ) {
            entity.setMnORoot( dto.getMnORoot() );
        }
        if ( dto.getOrderType() != null ) {
            entity.setOrderType( dto.getOrderType() );
        }
        if ( dto.getMainPackage() != null ) {
            entity.setMainPackage( dto.getMainPackage() );
        }
        if ( dto.getMainPackageName() != null ) {
            entity.setMainPackageName( dto.getMainPackageName() );
        }
        if ( dto.getWorkPackage() != null ) {
            entity.setWorkPackage( dto.getWorkPackage() );
        }
        if ( dto.getWorkPackageName() != null ) {
            entity.setWorkPackageName( dto.getWorkPackageName() );
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
        if ( dto.getDIS_IsQuote() != null ) {
            entity.setDIS_IsQuote( dto.getDIS_IsQuote() );
        }
        if ( dto.getDIS_OutQ() != null ) {
            entity.setDIS_OutQ( dto.getDIS_OutQ() );
        }
        if ( dto.getDIS_FillerPart() != null ) {
            entity.setDIS_FillerPart( dto.getDIS_FillerPart() );
        }
        if ( dto.getDIS_RouteAbbreviation() != null ) {
            entity.setDIS_RouteAbbreviation( dto.getDIS_RouteAbbreviation() );
        }
        if ( dto.getDIS_AutoNesting() != null ) {
            entity.setDIS_AutoNesting( dto.getDIS_AutoNesting() );
        }
        if ( dto.getDIS_AutoNestingStrategy() != null ) {
            entity.setDIS_AutoNestingStrategy( dto.getDIS_AutoNestingStrategy() );
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
