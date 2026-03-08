package com.ao.platform.core.lantek.convert;

import com.ao.platform.core.lantek.dto.PprrPprr00000100DTO;
import com.ao.platform.core.lantek.dto.PprrPprr00000100PageQuery;
import com.ao.platform.core.lantek.entity.PprrPprr00000100;
import com.ao.platform.core.lantek.vo.PprrPprr00000100VO;
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
public class PprrPprr00000100ConvertImpl implements PprrPprr00000100Convert {

    @Override
    public PprrPprr00000100VO toVO(PprrPprr00000100 entity) {
        if ( entity == null ) {
            return null;
        }

        PprrPprr00000100VO pprrPprr00000100VO = new PprrPprr00000100VO();

        pprrPprr00000100VO.setPrdRef( entity.getPrdRef() );
        pprrPprr00000100VO.setPrdName( entity.getPrdName() );
        pprrPprr00000100VO.setPrdIntName( entity.getPrdIntName() );
        pprrPprr00000100VO.setPGroup( entity.getPGroup() );
        pprrPprr00000100VO.setBarCode( entity.getBarCode() );
        pprrPprr00000100VO.setIsActive( entity.getIsActive() );
        pprrPprr00000100VO.setRealPrd( entity.getRealPrd() );
        pprrPprr00000100VO.setAssembly( entity.getAssembly() );
        pprrPprr00000100VO.setForSale( entity.getForSale() );
        pprrPprr00000100VO.setPType( entity.getPType() );
        pprrPprr00000100VO.setCstRanges( entity.getCstRanges() );
        pprrPprr00000100VO.setFixPrice( entity.getFixPrice() );
        pprrPprr00000100VO.setStdCost( entity.getStdCost() );
        pprrPprr00000100VO.setCurCost( entity.getCurCost() );
        pprrPprr00000100VO.setPrcMethod( entity.getPrcMethod() );
        pprrPprr00000100VO.setCstMethod( entity.getCstMethod() );
        pprrPprr00000100VO.setUCtName( entity.getUCtName() );
        pprrPprr00000100VO.setUntName( entity.getUntName() );
        pprrPprr00000100VO.setCurQuan( entity.getCurQuan() );
        pprrPprr00000100VO.setCDate( entity.getCDate() );
        pprrPprr00000100VO.setKeyWords( entity.getKeyWords() );
        pprrPprr00000100VO.setDescrip( entity.getDescrip() );
        pprrPprr00000100VO.setImage( entity.getImage() );
        pprrPprr00000100VO.setWeight( entity.getWeight() );
        pprrPprr00000100VO.setLeadTime( entity.getLeadTime() );
        pprrPprr00000100VO.setLeadUCtName( entity.getLeadUCtName() );
        pprrPprr00000100VO.setLeadUntName( entity.getLeadUntName() );
        pprrPprr00000100VO.setLeadUpdateMethod( entity.getLeadUpdateMethod() );
        pprrPprr00000100VO.setCommCode( entity.getCommCode() );
        pprrPprr00000100VO.setOCountry( entity.getOCountry() );
        pprrPprr00000100VO.setCGroup( entity.getCGroup() );
        pprrPprr00000100VO.setAccConItemRef( entity.getAccConItemRef() );
        pprrPprr00000100VO.setRecState( entity.getRecState() );
        pprrPprr00000100VO.setCrtDate( entity.getCrtDate() );
        pprrPprr00000100VO.setLastDate( entity.getLastDate() );
        pprrPprr00000100VO.setCrtUser( entity.getCrtUser() );
        pprrPprr00000100VO.setLastUser( entity.getLastUser() );
        pprrPprr00000100VO.setOwner( entity.getOwner() );
        pprrPprr00000100VO.setRecEnt( entity.getRecEnt() );
        pprrPprr00000100VO.setRecOU( entity.getRecOU() );
        pprrPprr00000100VO.setRecSec( entity.getRecSec() );
        pprrPprr00000100VO.setCntID( entity.getCntID() );
        pprrPprr00000100VO.setRecID( entity.getRecID() );

        return pprrPprr00000100VO;
    }

    @Override
    public List<PprrPprr00000100VO> toVOList(List<PprrPprr00000100> list) {
        if ( list == null ) {
            return null;
        }

        List<PprrPprr00000100VO> list1 = new ArrayList<PprrPprr00000100VO>( list.size() );
        for ( PprrPprr00000100 pprrPprr00000100 : list ) {
            list1.add( toVO( pprrPprr00000100 ) );
        }

        return list1;
    }

    @Override
    public PprrPprr00000100 toEntity(PprrPprr00000100DTO dto) {
        if ( dto == null ) {
            return null;
        }

        PprrPprr00000100 pprrPprr00000100 = new PprrPprr00000100();

        pprrPprr00000100.setPrdRef( dto.getPrdRef() );
        pprrPprr00000100.setPrdName( dto.getPrdName() );
        pprrPprr00000100.setPrdIntName( dto.getPrdIntName() );
        pprrPprr00000100.setPGroup( dto.getPGroup() );
        pprrPprr00000100.setBarCode( dto.getBarCode() );
        pprrPprr00000100.setIsActive( dto.getIsActive() );
        pprrPprr00000100.setRealPrd( dto.getRealPrd() );
        pprrPprr00000100.setAssembly( dto.getAssembly() );
        pprrPprr00000100.setForSale( dto.getForSale() );
        pprrPprr00000100.setPType( dto.getPType() );
        pprrPprr00000100.setCstRanges( dto.getCstRanges() );
        pprrPprr00000100.setFixPrice( dto.getFixPrice() );
        pprrPprr00000100.setStdCost( dto.getStdCost() );
        pprrPprr00000100.setCurCost( dto.getCurCost() );
        pprrPprr00000100.setPrcMethod( dto.getPrcMethod() );
        pprrPprr00000100.setCstMethod( dto.getCstMethod() );
        pprrPprr00000100.setUCtName( dto.getUCtName() );
        pprrPprr00000100.setUntName( dto.getUntName() );
        pprrPprr00000100.setCurQuan( dto.getCurQuan() );
        pprrPprr00000100.setCDate( dto.getCDate() );
        pprrPprr00000100.setKeyWords( dto.getKeyWords() );
        pprrPprr00000100.setDescrip( dto.getDescrip() );
        pprrPprr00000100.setImage( dto.getImage() );
        pprrPprr00000100.setWeight( dto.getWeight() );
        pprrPprr00000100.setLeadTime( dto.getLeadTime() );
        pprrPprr00000100.setLeadUCtName( dto.getLeadUCtName() );
        pprrPprr00000100.setLeadUntName( dto.getLeadUntName() );
        pprrPprr00000100.setLeadUpdateMethod( dto.getLeadUpdateMethod() );
        pprrPprr00000100.setCommCode( dto.getCommCode() );
        pprrPprr00000100.setOCountry( dto.getOCountry() );
        pprrPprr00000100.setCGroup( dto.getCGroup() );
        pprrPprr00000100.setAccConItemRef( dto.getAccConItemRef() );
        pprrPprr00000100.setRecState( dto.getRecState() );
        pprrPprr00000100.setCrtDate( dto.getCrtDate() );
        pprrPprr00000100.setLastDate( dto.getLastDate() );
        pprrPprr00000100.setCrtUser( dto.getCrtUser() );
        pprrPprr00000100.setLastUser( dto.getLastUser() );
        pprrPprr00000100.setOwner( dto.getOwner() );
        pprrPprr00000100.setRecEnt( dto.getRecEnt() );
        pprrPprr00000100.setRecOU( dto.getRecOU() );
        pprrPprr00000100.setRecSec( dto.getRecSec() );
        pprrPprr00000100.setCntID( dto.getCntID() );
        pprrPprr00000100.setRecID( dto.getRecID() );

        return pprrPprr00000100;
    }

    @Override
    public PprrPprr00000100 toEntity(PprrPprr00000100PageQuery dto) {
        if ( dto == null ) {
            return null;
        }

        PprrPprr00000100 pprrPprr00000100 = new PprrPprr00000100();

        pprrPprr00000100.setPrdRef( dto.getPrdRef() );
        pprrPprr00000100.setPrdName( dto.getPrdName() );
        pprrPprr00000100.setPrdIntName( dto.getPrdIntName() );
        pprrPprr00000100.setPGroup( dto.getPGroup() );
        pprrPprr00000100.setBarCode( dto.getBarCode() );
        pprrPprr00000100.setIsActive( dto.getIsActive() );
        pprrPprr00000100.setRealPrd( dto.getRealPrd() );
        pprrPprr00000100.setAssembly( dto.getAssembly() );
        pprrPprr00000100.setForSale( dto.getForSale() );
        pprrPprr00000100.setPType( dto.getPType() );
        pprrPprr00000100.setCstRanges( dto.getCstRanges() );
        pprrPprr00000100.setFixPrice( dto.getFixPrice() );
        pprrPprr00000100.setStdCost( dto.getStdCost() );
        pprrPprr00000100.setCurCost( dto.getCurCost() );
        pprrPprr00000100.setPrcMethod( dto.getPrcMethod() );
        pprrPprr00000100.setCstMethod( dto.getCstMethod() );
        pprrPprr00000100.setUCtName( dto.getUCtName() );
        pprrPprr00000100.setUntName( dto.getUntName() );
        pprrPprr00000100.setCurQuan( dto.getCurQuan() );
        pprrPprr00000100.setCDate( dto.getCDate() );
        pprrPprr00000100.setKeyWords( dto.getKeyWords() );
        pprrPprr00000100.setDescrip( dto.getDescrip() );
        pprrPprr00000100.setImage( dto.getImage() );
        pprrPprr00000100.setWeight( dto.getWeight() );
        pprrPprr00000100.setLeadTime( dto.getLeadTime() );
        pprrPprr00000100.setLeadUCtName( dto.getLeadUCtName() );
        pprrPprr00000100.setLeadUntName( dto.getLeadUntName() );
        pprrPprr00000100.setLeadUpdateMethod( dto.getLeadUpdateMethod() );
        pprrPprr00000100.setCommCode( dto.getCommCode() );
        pprrPprr00000100.setOCountry( dto.getOCountry() );
        pprrPprr00000100.setCGroup( dto.getCGroup() );
        pprrPprr00000100.setAccConItemRef( dto.getAccConItemRef() );
        pprrPprr00000100.setRecState( dto.getRecState() );
        pprrPprr00000100.setCrtDate( dto.getCrtDate() );
        pprrPprr00000100.setLastDate( dto.getLastDate() );
        pprrPprr00000100.setCrtUser( dto.getCrtUser() );
        pprrPprr00000100.setLastUser( dto.getLastUser() );
        pprrPprr00000100.setOwner( dto.getOwner() );
        pprrPprr00000100.setRecEnt( dto.getRecEnt() );
        pprrPprr00000100.setRecOU( dto.getRecOU() );
        pprrPprr00000100.setRecSec( dto.getRecSec() );
        pprrPprr00000100.setCntID( dto.getCntID() );
        pprrPprr00000100.setRecID( dto.getRecID() );

        return pprrPprr00000100;
    }

    @Override
    public void updateFromDTO(PprrPprr00000100DTO dto, PprrPprr00000100 entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getPrdRef() != null ) {
            entity.setPrdRef( dto.getPrdRef() );
        }
        if ( dto.getPrdName() != null ) {
            entity.setPrdName( dto.getPrdName() );
        }
        if ( dto.getPrdIntName() != null ) {
            entity.setPrdIntName( dto.getPrdIntName() );
        }
        if ( dto.getPGroup() != null ) {
            entity.setPGroup( dto.getPGroup() );
        }
        if ( dto.getBarCode() != null ) {
            entity.setBarCode( dto.getBarCode() );
        }
        if ( dto.getIsActive() != null ) {
            entity.setIsActive( dto.getIsActive() );
        }
        if ( dto.getRealPrd() != null ) {
            entity.setRealPrd( dto.getRealPrd() );
        }
        if ( dto.getAssembly() != null ) {
            entity.setAssembly( dto.getAssembly() );
        }
        if ( dto.getForSale() != null ) {
            entity.setForSale( dto.getForSale() );
        }
        if ( dto.getPType() != null ) {
            entity.setPType( dto.getPType() );
        }
        if ( dto.getCstRanges() != null ) {
            entity.setCstRanges( dto.getCstRanges() );
        }
        if ( dto.getFixPrice() != null ) {
            entity.setFixPrice( dto.getFixPrice() );
        }
        if ( dto.getStdCost() != null ) {
            entity.setStdCost( dto.getStdCost() );
        }
        if ( dto.getCurCost() != null ) {
            entity.setCurCost( dto.getCurCost() );
        }
        if ( dto.getPrcMethod() != null ) {
            entity.setPrcMethod( dto.getPrcMethod() );
        }
        if ( dto.getCstMethod() != null ) {
            entity.setCstMethod( dto.getCstMethod() );
        }
        if ( dto.getUCtName() != null ) {
            entity.setUCtName( dto.getUCtName() );
        }
        if ( dto.getUntName() != null ) {
            entity.setUntName( dto.getUntName() );
        }
        if ( dto.getCurQuan() != null ) {
            entity.setCurQuan( dto.getCurQuan() );
        }
        if ( dto.getCDate() != null ) {
            entity.setCDate( dto.getCDate() );
        }
        if ( dto.getKeyWords() != null ) {
            entity.setKeyWords( dto.getKeyWords() );
        }
        if ( dto.getDescrip() != null ) {
            entity.setDescrip( dto.getDescrip() );
        }
        if ( dto.getImage() != null ) {
            entity.setImage( dto.getImage() );
        }
        if ( dto.getWeight() != null ) {
            entity.setWeight( dto.getWeight() );
        }
        if ( dto.getLeadTime() != null ) {
            entity.setLeadTime( dto.getLeadTime() );
        }
        if ( dto.getLeadUCtName() != null ) {
            entity.setLeadUCtName( dto.getLeadUCtName() );
        }
        if ( dto.getLeadUntName() != null ) {
            entity.setLeadUntName( dto.getLeadUntName() );
        }
        if ( dto.getLeadUpdateMethod() != null ) {
            entity.setLeadUpdateMethod( dto.getLeadUpdateMethod() );
        }
        if ( dto.getCommCode() != null ) {
            entity.setCommCode( dto.getCommCode() );
        }
        if ( dto.getOCountry() != null ) {
            entity.setOCountry( dto.getOCountry() );
        }
        if ( dto.getCGroup() != null ) {
            entity.setCGroup( dto.getCGroup() );
        }
        if ( dto.getAccConItemRef() != null ) {
            entity.setAccConItemRef( dto.getAccConItemRef() );
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
