package com.ao.platform.core.lantek.convert;

import com.ao.platform.core.lantek.dto.DisNestNest00000400DTO;
import com.ao.platform.core.lantek.dto.DisNestNest00000400PageQuery;
import com.ao.platform.core.lantek.entity.DisNestNest00000400;
import com.ao.platform.core.lantek.vo.DisNestNest00000400VO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-04T21:52:26+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.10 (Eclipse Adoptium)"
)
@Component
public class DisNestNest00000400ConvertImpl implements DisNestNest00000400Convert {

    @Override
    public DisNestNest00000400VO toVO(DisNestNest00000400 entity) {
        if ( entity == null ) {
            return null;
        }

        DisNestNest00000400VO disNestNest00000400VO = new DisNestNest00000400VO();

        disNestNest00000400VO.setNstRef( entity.getNstRef() );
        disNestNest00000400VO.setWrkRef( entity.getWrkRef() );
        disNestNest00000400VO.setTurrRef( entity.getTurrRef() );
        disNestNest00000400VO.setTPosition( entity.getTPosition() );
        disNestNest00000400VO.setMltRef( entity.getMltRef() );
        disNestNest00000400VO.setMPosition( entity.getMPosition() );
        disNestNest00000400VO.setPunchRef( entity.getPunchRef() );
        disNestNest00000400VO.setDieRef( entity.getDieRef() );
        disNestNest00000400VO.setPAngle( entity.getPAngle() );
        disNestNest00000400VO.setIsUsed( entity.getIsUsed() );
        disNestNest00000400VO.setNumOpN( entity.getNumOpN() );
        disNestNest00000400VO.setNumOpR( entity.getNumOpR() );
        disNestNest00000400VO.setToolClass( entity.getToolClass() );
        disNestNest00000400VO.setRecState( entity.getRecState() );
        disNestNest00000400VO.setCrtDate( entity.getCrtDate() );
        disNestNest00000400VO.setLastDate( entity.getLastDate() );
        disNestNest00000400VO.setCrtUser( entity.getCrtUser() );
        disNestNest00000400VO.setLastUser( entity.getLastUser() );
        disNestNest00000400VO.setOwner( entity.getOwner() );
        disNestNest00000400VO.setRecEnt( entity.getRecEnt() );
        disNestNest00000400VO.setRecOU( entity.getRecOU() );
        disNestNest00000400VO.setRecSec( entity.getRecSec() );
        disNestNest00000400VO.setCntID( entity.getCntID() );
        disNestNest00000400VO.setRecID( entity.getRecID() );

        return disNestNest00000400VO;
    }

    @Override
    public List<DisNestNest00000400VO> toVOList(List<DisNestNest00000400> list) {
        if ( list == null ) {
            return null;
        }

        List<DisNestNest00000400VO> list1 = new ArrayList<DisNestNest00000400VO>( list.size() );
        for ( DisNestNest00000400 disNestNest00000400 : list ) {
            list1.add( toVO( disNestNest00000400 ) );
        }

        return list1;
    }

    @Override
    public DisNestNest00000400 toEntity(DisNestNest00000400DTO dto) {
        if ( dto == null ) {
            return null;
        }

        DisNestNest00000400 disNestNest00000400 = new DisNestNest00000400();

        disNestNest00000400.setNstRef( dto.getNstRef() );
        disNestNest00000400.setWrkRef( dto.getWrkRef() );
        disNestNest00000400.setTurrRef( dto.getTurrRef() );
        disNestNest00000400.setTPosition( dto.getTPosition() );
        disNestNest00000400.setMltRef( dto.getMltRef() );
        disNestNest00000400.setMPosition( dto.getMPosition() );
        disNestNest00000400.setPunchRef( dto.getPunchRef() );
        disNestNest00000400.setDieRef( dto.getDieRef() );
        disNestNest00000400.setPAngle( dto.getPAngle() );
        disNestNest00000400.setIsUsed( dto.getIsUsed() );
        disNestNest00000400.setNumOpN( dto.getNumOpN() );
        disNestNest00000400.setNumOpR( dto.getNumOpR() );
        disNestNest00000400.setToolClass( dto.getToolClass() );
        disNestNest00000400.setRecState( dto.getRecState() );
        disNestNest00000400.setCrtDate( dto.getCrtDate() );
        disNestNest00000400.setLastDate( dto.getLastDate() );
        disNestNest00000400.setCrtUser( dto.getCrtUser() );
        disNestNest00000400.setLastUser( dto.getLastUser() );
        disNestNest00000400.setOwner( dto.getOwner() );
        disNestNest00000400.setRecEnt( dto.getRecEnt() );
        disNestNest00000400.setRecOU( dto.getRecOU() );
        disNestNest00000400.setRecSec( dto.getRecSec() );
        disNestNest00000400.setCntID( dto.getCntID() );
        disNestNest00000400.setRecID( dto.getRecID() );

        return disNestNest00000400;
    }

    @Override
    public DisNestNest00000400 toEntity(DisNestNest00000400PageQuery dto) {
        if ( dto == null ) {
            return null;
        }

        DisNestNest00000400 disNestNest00000400 = new DisNestNest00000400();

        disNestNest00000400.setNstRef( dto.getNstRef() );
        disNestNest00000400.setWrkRef( dto.getWrkRef() );
        disNestNest00000400.setTurrRef( dto.getTurrRef() );
        disNestNest00000400.setTPosition( dto.getTPosition() );
        disNestNest00000400.setMltRef( dto.getMltRef() );
        disNestNest00000400.setMPosition( dto.getMPosition() );
        disNestNest00000400.setPunchRef( dto.getPunchRef() );
        disNestNest00000400.setDieRef( dto.getDieRef() );
        disNestNest00000400.setPAngle( dto.getPAngle() );
        disNestNest00000400.setIsUsed( dto.getIsUsed() );
        disNestNest00000400.setNumOpN( dto.getNumOpN() );
        disNestNest00000400.setNumOpR( dto.getNumOpR() );
        disNestNest00000400.setToolClass( dto.getToolClass() );
        disNestNest00000400.setRecState( dto.getRecState() );
        disNestNest00000400.setCrtDate( dto.getCrtDate() );
        disNestNest00000400.setLastDate( dto.getLastDate() );
        disNestNest00000400.setCrtUser( dto.getCrtUser() );
        disNestNest00000400.setLastUser( dto.getLastUser() );
        disNestNest00000400.setOwner( dto.getOwner() );
        disNestNest00000400.setRecEnt( dto.getRecEnt() );
        disNestNest00000400.setRecOU( dto.getRecOU() );
        disNestNest00000400.setRecSec( dto.getRecSec() );
        disNestNest00000400.setCntID( dto.getCntID() );
        disNestNest00000400.setRecID( dto.getRecID() );

        return disNestNest00000400;
    }

    @Override
    public void updateFromDTO(DisNestNest00000400DTO dto, DisNestNest00000400 entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getNstRef() != null ) {
            entity.setNstRef( dto.getNstRef() );
        }
        if ( dto.getWrkRef() != null ) {
            entity.setWrkRef( dto.getWrkRef() );
        }
        if ( dto.getTurrRef() != null ) {
            entity.setTurrRef( dto.getTurrRef() );
        }
        if ( dto.getTPosition() != null ) {
            entity.setTPosition( dto.getTPosition() );
        }
        if ( dto.getMltRef() != null ) {
            entity.setMltRef( dto.getMltRef() );
        }
        if ( dto.getMPosition() != null ) {
            entity.setMPosition( dto.getMPosition() );
        }
        if ( dto.getPunchRef() != null ) {
            entity.setPunchRef( dto.getPunchRef() );
        }
        if ( dto.getDieRef() != null ) {
            entity.setDieRef( dto.getDieRef() );
        }
        if ( dto.getPAngle() != null ) {
            entity.setPAngle( dto.getPAngle() );
        }
        if ( dto.getIsUsed() != null ) {
            entity.setIsUsed( dto.getIsUsed() );
        }
        if ( dto.getNumOpN() != null ) {
            entity.setNumOpN( dto.getNumOpN() );
        }
        if ( dto.getNumOpR() != null ) {
            entity.setNumOpR( dto.getNumOpR() );
        }
        if ( dto.getToolClass() != null ) {
            entity.setToolClass( dto.getToolClass() );
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
