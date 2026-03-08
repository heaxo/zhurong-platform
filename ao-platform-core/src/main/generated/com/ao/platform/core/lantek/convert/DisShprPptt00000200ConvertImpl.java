package com.ao.platform.core.lantek.convert;

import com.ao.platform.core.lantek.dto.DisShprPptt00000200DTO;
import com.ao.platform.core.lantek.dto.DisShprPptt00000200PageQuery;
import com.ao.platform.core.lantek.entity.DisShprPptt00000200;
import com.ao.platform.core.lantek.vo.DisShprPptt00000200VO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-06T15:33:06+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.10 (Eclipse Adoptium)"
)
@Component
public class DisShprPptt00000200ConvertImpl implements DisShprPptt00000200Convert {

    @Override
    public DisShprPptt00000200VO toVO(DisShprPptt00000200 entity) {
        if ( entity == null ) {
            return null;
        }

        DisShprPptt00000200VO disShprPptt00000200VO = new DisShprPptt00000200VO();

        disShprPptt00000200VO.setPartRef( entity.getPartRef() );
        disShprPptt00000200VO.setWrkRef( entity.getWrkRef() );
        disShprPptt00000200VO.setOprRef( entity.getOprRef() );
        disShprPptt00000200VO.setMatRef( entity.getMatRef() );
        disShprPptt00000200VO.setThickness( entity.getThickness() );
        disShprPptt00000200VO.setMCode( entity.getMCode() );
        disShprPptt00000200VO.setMsgDesc( entity.getMsgDesc() );
        disShprPptt00000200VO.setMsgNum( entity.getMsgNum() );
        disShprPptt00000200VO.setValType( entity.getValType() );
        disShprPptt00000200VO.setUCtName( entity.getUCtName() );
        disShprPptt00000200VO.setUntName( entity.getUntName() );
        disShprPptt00000200VO.setDValue( entity.getDValue() );
        disShprPptt00000200VO.setCValue( entity.getCValue() );
        disShprPptt00000200VO.setRecState( entity.getRecState() );
        disShprPptt00000200VO.setCrtDate( entity.getCrtDate() );
        disShprPptt00000200VO.setLastDate( entity.getLastDate() );
        disShprPptt00000200VO.setCrtUser( entity.getCrtUser() );
        disShprPptt00000200VO.setLastUser( entity.getLastUser() );
        disShprPptt00000200VO.setOwner( entity.getOwner() );
        disShprPptt00000200VO.setRecEnt( entity.getRecEnt() );
        disShprPptt00000200VO.setRecOU( entity.getRecOU() );
        disShprPptt00000200VO.setRecSec( entity.getRecSec() );
        disShprPptt00000200VO.setCntID( entity.getCntID() );
        disShprPptt00000200VO.setRecID( entity.getRecID() );

        return disShprPptt00000200VO;
    }

    @Override
    public List<DisShprPptt00000200VO> toVOList(List<DisShprPptt00000200> list) {
        if ( list == null ) {
            return null;
        }

        List<DisShprPptt00000200VO> list1 = new ArrayList<DisShprPptt00000200VO>( list.size() );
        for ( DisShprPptt00000200 disShprPptt00000200 : list ) {
            list1.add( toVO( disShprPptt00000200 ) );
        }

        return list1;
    }

    @Override
    public DisShprPptt00000200 toEntity(DisShprPptt00000200DTO dto) {
        if ( dto == null ) {
            return null;
        }

        DisShprPptt00000200 disShprPptt00000200 = new DisShprPptt00000200();

        disShprPptt00000200.setPartRef( dto.getPartRef() );
        disShprPptt00000200.setWrkRef( dto.getWrkRef() );
        disShprPptt00000200.setOprRef( dto.getOprRef() );
        disShprPptt00000200.setMatRef( dto.getMatRef() );
        disShprPptt00000200.setThickness( dto.getThickness() );
        disShprPptt00000200.setMCode( dto.getMCode() );
        disShprPptt00000200.setMsgDesc( dto.getMsgDesc() );
        disShprPptt00000200.setMsgNum( dto.getMsgNum() );
        disShprPptt00000200.setValType( dto.getValType() );
        disShprPptt00000200.setUCtName( dto.getUCtName() );
        disShprPptt00000200.setUntName( dto.getUntName() );
        disShprPptt00000200.setDValue( dto.getDValue() );
        disShprPptt00000200.setCValue( dto.getCValue() );
        disShprPptt00000200.setRecState( dto.getRecState() );
        disShprPptt00000200.setCrtDate( dto.getCrtDate() );
        disShprPptt00000200.setLastDate( dto.getLastDate() );
        disShprPptt00000200.setCrtUser( dto.getCrtUser() );
        disShprPptt00000200.setLastUser( dto.getLastUser() );
        disShprPptt00000200.setOwner( dto.getOwner() );
        disShprPptt00000200.setRecEnt( dto.getRecEnt() );
        disShprPptt00000200.setRecOU( dto.getRecOU() );
        disShprPptt00000200.setRecSec( dto.getRecSec() );
        disShprPptt00000200.setCntID( dto.getCntID() );
        disShprPptt00000200.setRecID( dto.getRecID() );

        return disShprPptt00000200;
    }

    @Override
    public DisShprPptt00000200 toEntity(DisShprPptt00000200PageQuery dto) {
        if ( dto == null ) {
            return null;
        }

        DisShprPptt00000200 disShprPptt00000200 = new DisShprPptt00000200();

        disShprPptt00000200.setPartRef( dto.getPartRef() );
        disShprPptt00000200.setWrkRef( dto.getWrkRef() );
        disShprPptt00000200.setOprRef( dto.getOprRef() );
        disShprPptt00000200.setMatRef( dto.getMatRef() );
        disShprPptt00000200.setThickness( dto.getThickness() );
        disShprPptt00000200.setMCode( dto.getMCode() );
        disShprPptt00000200.setMsgDesc( dto.getMsgDesc() );
        disShprPptt00000200.setMsgNum( dto.getMsgNum() );
        disShprPptt00000200.setValType( dto.getValType() );
        disShprPptt00000200.setUCtName( dto.getUCtName() );
        disShprPptt00000200.setUntName( dto.getUntName() );
        disShprPptt00000200.setDValue( dto.getDValue() );
        disShprPptt00000200.setCValue( dto.getCValue() );
        disShprPptt00000200.setRecState( dto.getRecState() );
        disShprPptt00000200.setCrtDate( dto.getCrtDate() );
        disShprPptt00000200.setLastDate( dto.getLastDate() );
        disShprPptt00000200.setCrtUser( dto.getCrtUser() );
        disShprPptt00000200.setLastUser( dto.getLastUser() );
        disShprPptt00000200.setOwner( dto.getOwner() );
        disShprPptt00000200.setRecEnt( dto.getRecEnt() );
        disShprPptt00000200.setRecOU( dto.getRecOU() );
        disShprPptt00000200.setRecSec( dto.getRecSec() );
        disShprPptt00000200.setCntID( dto.getCntID() );
        disShprPptt00000200.setRecID( dto.getRecID() );

        return disShprPptt00000200;
    }

    @Override
    public void updateFromDTO(DisShprPptt00000200DTO dto, DisShprPptt00000200 entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getPartRef() != null ) {
            entity.setPartRef( dto.getPartRef() );
        }
        if ( dto.getWrkRef() != null ) {
            entity.setWrkRef( dto.getWrkRef() );
        }
        if ( dto.getOprRef() != null ) {
            entity.setOprRef( dto.getOprRef() );
        }
        if ( dto.getMatRef() != null ) {
            entity.setMatRef( dto.getMatRef() );
        }
        if ( dto.getThickness() != null ) {
            entity.setThickness( dto.getThickness() );
        }
        if ( dto.getMCode() != null ) {
            entity.setMCode( dto.getMCode() );
        }
        if ( dto.getMsgDesc() != null ) {
            entity.setMsgDesc( dto.getMsgDesc() );
        }
        if ( dto.getMsgNum() != null ) {
            entity.setMsgNum( dto.getMsgNum() );
        }
        if ( dto.getValType() != null ) {
            entity.setValType( dto.getValType() );
        }
        if ( dto.getUCtName() != null ) {
            entity.setUCtName( dto.getUCtName() );
        }
        if ( dto.getUntName() != null ) {
            entity.setUntName( dto.getUntName() );
        }
        if ( dto.getDValue() != null ) {
            entity.setDValue( dto.getDValue() );
        }
        if ( dto.getCValue() != null ) {
            entity.setCValue( dto.getCValue() );
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
