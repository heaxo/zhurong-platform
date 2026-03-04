package com.ao.platform.core.lantek.convert;

import com.ao.platform.core.lantek.dto.DisNestNest00000200DTO;
import com.ao.platform.core.lantek.dto.DisNestNest00000200PageQuery;
import com.ao.platform.core.lantek.entity.DisNestNest00000200;
import com.ao.platform.core.lantek.vo.DisNestNest00000200VO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-04T22:48:13+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.10 (Eclipse Adoptium)"
)
@Component
public class DisNestNest00000200ConvertImpl implements DisNestNest00000200Convert {

    @Override
    public DisNestNest00000200VO toVO(DisNestNest00000200 entity) {
        if ( entity == null ) {
            return null;
        }

        DisNestNest00000200VO disNestNest00000200VO = new DisNestNest00000200VO();

        disNestNest00000200VO.setNstRef( entity.getNstRef() );
        disNestNest00000200VO.setMCode( entity.getMCode() );
        disNestNest00000200VO.setMsgDesc( entity.getMsgDesc() );
        disNestNest00000200VO.setMsgNum( entity.getMsgNum() );
        disNestNest00000200VO.setValType( entity.getValType() );
        disNestNest00000200VO.setUCtName( entity.getUCtName() );
        disNestNest00000200VO.setUntName( entity.getUntName() );
        disNestNest00000200VO.setDValue( entity.getDValue() );
        disNestNest00000200VO.setCValue( entity.getCValue() );
        disNestNest00000200VO.setRecState( entity.getRecState() );
        disNestNest00000200VO.setCrtDate( entity.getCrtDate() );
        disNestNest00000200VO.setLastDate( entity.getLastDate() );
        disNestNest00000200VO.setCrtUser( entity.getCrtUser() );
        disNestNest00000200VO.setLastUser( entity.getLastUser() );
        disNestNest00000200VO.setOwner( entity.getOwner() );
        disNestNest00000200VO.setRecEnt( entity.getRecEnt() );
        disNestNest00000200VO.setRecOU( entity.getRecOU() );
        disNestNest00000200VO.setRecSec( entity.getRecSec() );
        disNestNest00000200VO.setCntID( entity.getCntID() );
        disNestNest00000200VO.setRecID( entity.getRecID() );

        return disNestNest00000200VO;
    }

    @Override
    public List<DisNestNest00000200VO> toVOList(List<DisNestNest00000200> list) {
        if ( list == null ) {
            return null;
        }

        List<DisNestNest00000200VO> list1 = new ArrayList<DisNestNest00000200VO>( list.size() );
        for ( DisNestNest00000200 disNestNest00000200 : list ) {
            list1.add( toVO( disNestNest00000200 ) );
        }

        return list1;
    }

    @Override
    public DisNestNest00000200 toEntity(DisNestNest00000200DTO dto) {
        if ( dto == null ) {
            return null;
        }

        DisNestNest00000200 disNestNest00000200 = new DisNestNest00000200();

        disNestNest00000200.setNstRef( dto.getNstRef() );
        disNestNest00000200.setMCode( dto.getMCode() );
        disNestNest00000200.setMsgDesc( dto.getMsgDesc() );
        disNestNest00000200.setMsgNum( dto.getMsgNum() );
        disNestNest00000200.setValType( dto.getValType() );
        disNestNest00000200.setUCtName( dto.getUCtName() );
        disNestNest00000200.setUntName( dto.getUntName() );
        disNestNest00000200.setDValue( dto.getDValue() );
        disNestNest00000200.setCValue( dto.getCValue() );
        disNestNest00000200.setRecState( dto.getRecState() );
        disNestNest00000200.setCrtDate( dto.getCrtDate() );
        disNestNest00000200.setLastDate( dto.getLastDate() );
        disNestNest00000200.setCrtUser( dto.getCrtUser() );
        disNestNest00000200.setLastUser( dto.getLastUser() );
        disNestNest00000200.setOwner( dto.getOwner() );
        disNestNest00000200.setRecEnt( dto.getRecEnt() );
        disNestNest00000200.setRecOU( dto.getRecOU() );
        disNestNest00000200.setRecSec( dto.getRecSec() );
        disNestNest00000200.setCntID( dto.getCntID() );
        disNestNest00000200.setRecID( dto.getRecID() );

        return disNestNest00000200;
    }

    @Override
    public DisNestNest00000200 toEntity(DisNestNest00000200PageQuery dto) {
        if ( dto == null ) {
            return null;
        }

        DisNestNest00000200 disNestNest00000200 = new DisNestNest00000200();

        disNestNest00000200.setNstRef( dto.getNstRef() );
        disNestNest00000200.setMCode( dto.getMCode() );
        disNestNest00000200.setMsgDesc( dto.getMsgDesc() );
        disNestNest00000200.setMsgNum( dto.getMsgNum() );
        disNestNest00000200.setValType( dto.getValType() );
        disNestNest00000200.setUCtName( dto.getUCtName() );
        disNestNest00000200.setUntName( dto.getUntName() );
        disNestNest00000200.setDValue( dto.getDValue() );
        disNestNest00000200.setCValue( dto.getCValue() );
        disNestNest00000200.setRecState( dto.getRecState() );
        disNestNest00000200.setCrtDate( dto.getCrtDate() );
        disNestNest00000200.setLastDate( dto.getLastDate() );
        disNestNest00000200.setCrtUser( dto.getCrtUser() );
        disNestNest00000200.setLastUser( dto.getLastUser() );
        disNestNest00000200.setOwner( dto.getOwner() );
        disNestNest00000200.setRecEnt( dto.getRecEnt() );
        disNestNest00000200.setRecOU( dto.getRecOU() );
        disNestNest00000200.setRecSec( dto.getRecSec() );
        disNestNest00000200.setCntID( dto.getCntID() );
        disNestNest00000200.setRecID( dto.getRecID() );

        return disNestNest00000200;
    }

    @Override
    public void updateFromDTO(DisNestNest00000200DTO dto, DisNestNest00000200 entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getNstRef() != null ) {
            entity.setNstRef( dto.getNstRef() );
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
