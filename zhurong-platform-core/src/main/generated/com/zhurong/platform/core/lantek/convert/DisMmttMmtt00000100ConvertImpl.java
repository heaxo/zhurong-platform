package com.zhurong.platform.core.lantek.convert;

import com.zhurong.platform.core.lantek.dto.DisMmttMmtt00000100DTO;
import com.zhurong.platform.core.lantek.dto.DisMmttMmtt00000100PageQuery;
import com.zhurong.platform.core.lantek.entity.DisMmttMmtt00000100;
import com.zhurong.platform.core.lantek.vo.DisMmttMmtt00000100VO;
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
public class DisMmttMmtt00000100ConvertImpl implements DisMmttMmtt00000100Convert {

    @Override
    public DisMmttMmtt00000100VO toVO(DisMmttMmtt00000100 entity) {
        if ( entity == null ) {
            return null;
        }

        DisMmttMmtt00000100VO disMmttMmtt00000100VO = new DisMmttMmtt00000100VO();

        disMmttMmtt00000100VO.setMatRef( entity.getMatRef() );
        disMmttMmtt00000100VO.setDensity( entity.getDensity() );
        disMmttMmtt00000100VO.setStrength( entity.getStrength() );
        disMmttMmtt00000100VO.setMFactor( entity.getMFactor() );
        disMmttMmtt00000100VO.setPrice( entity.getPrice() );
        disMmttMmtt00000100VO.setIsMain( entity.getIsMain() );
        disMmttMmtt00000100VO.setIntRef( entity.getIntRef() );
        disMmttMmtt00000100VO.setDescrip( entity.getDescrip() );
        disMmttMmtt00000100VO.setPriceDate( entity.getPriceDate() );
        disMmttMmtt00000100VO.setCGroup( entity.getCGroup() );
        disMmttMmtt00000100VO.setScrapPrice( entity.getScrapPrice() );
        disMmttMmtt00000100VO.setScrapPriceDate( entity.getScrapPriceDate() );
        disMmttMmtt00000100VO.setRecState( entity.getRecState() );
        disMmttMmtt00000100VO.setCrtDate( entity.getCrtDate() );
        disMmttMmtt00000100VO.setLastDate( entity.getLastDate() );
        disMmttMmtt00000100VO.setCrtUser( entity.getCrtUser() );
        disMmttMmtt00000100VO.setLastUser( entity.getLastUser() );
        disMmttMmtt00000100VO.setOwner( entity.getOwner() );
        disMmttMmtt00000100VO.setRecEnt( entity.getRecEnt() );
        disMmttMmtt00000100VO.setRecOU( entity.getRecOU() );
        disMmttMmtt00000100VO.setRecSec( entity.getRecSec() );
        disMmttMmtt00000100VO.setCntID( entity.getCntID() );
        disMmttMmtt00000100VO.setRecID( entity.getRecID() );

        return disMmttMmtt00000100VO;
    }

    @Override
    public List<DisMmttMmtt00000100VO> toVOList(List<DisMmttMmtt00000100> list) {
        if ( list == null ) {
            return null;
        }

        List<DisMmttMmtt00000100VO> list1 = new ArrayList<DisMmttMmtt00000100VO>( list.size() );
        for ( DisMmttMmtt00000100 disMmttMmtt00000100 : list ) {
            list1.add( toVO( disMmttMmtt00000100 ) );
        }

        return list1;
    }

    @Override
    public DisMmttMmtt00000100 toEntity(DisMmttMmtt00000100DTO dto) {
        if ( dto == null ) {
            return null;
        }

        DisMmttMmtt00000100 disMmttMmtt00000100 = new DisMmttMmtt00000100();

        disMmttMmtt00000100.setMatRef( dto.getMatRef() );
        disMmttMmtt00000100.setDensity( dto.getDensity() );
        disMmttMmtt00000100.setStrength( dto.getStrength() );
        disMmttMmtt00000100.setMFactor( dto.getMFactor() );
        disMmttMmtt00000100.setPrice( dto.getPrice() );
        disMmttMmtt00000100.setIsMain( dto.getIsMain() );
        disMmttMmtt00000100.setIntRef( dto.getIntRef() );
        disMmttMmtt00000100.setDescrip( dto.getDescrip() );
        disMmttMmtt00000100.setPriceDate( dto.getPriceDate() );
        disMmttMmtt00000100.setCGroup( dto.getCGroup() );
        disMmttMmtt00000100.setScrapPrice( dto.getScrapPrice() );
        disMmttMmtt00000100.setScrapPriceDate( dto.getScrapPriceDate() );
        disMmttMmtt00000100.setRecState( dto.getRecState() );
        disMmttMmtt00000100.setCrtDate( dto.getCrtDate() );
        disMmttMmtt00000100.setLastDate( dto.getLastDate() );
        disMmttMmtt00000100.setCrtUser( dto.getCrtUser() );
        disMmttMmtt00000100.setLastUser( dto.getLastUser() );
        disMmttMmtt00000100.setOwner( dto.getOwner() );
        disMmttMmtt00000100.setRecEnt( dto.getRecEnt() );
        disMmttMmtt00000100.setRecOU( dto.getRecOU() );
        disMmttMmtt00000100.setRecSec( dto.getRecSec() );
        disMmttMmtt00000100.setCntID( dto.getCntID() );
        disMmttMmtt00000100.setRecID( dto.getRecID() );

        return disMmttMmtt00000100;
    }

    @Override
    public DisMmttMmtt00000100 toEntity(DisMmttMmtt00000100PageQuery dto) {
        if ( dto == null ) {
            return null;
        }

        DisMmttMmtt00000100 disMmttMmtt00000100 = new DisMmttMmtt00000100();

        disMmttMmtt00000100.setMatRef( dto.getMatRef() );
        disMmttMmtt00000100.setDensity( dto.getDensity() );
        disMmttMmtt00000100.setStrength( dto.getStrength() );
        disMmttMmtt00000100.setMFactor( dto.getMFactor() );
        disMmttMmtt00000100.setPrice( dto.getPrice() );
        disMmttMmtt00000100.setIsMain( dto.getIsMain() );
        disMmttMmtt00000100.setIntRef( dto.getIntRef() );
        disMmttMmtt00000100.setDescrip( dto.getDescrip() );
        disMmttMmtt00000100.setPriceDate( dto.getPriceDate() );
        disMmttMmtt00000100.setCGroup( dto.getCGroup() );
        disMmttMmtt00000100.setScrapPrice( dto.getScrapPrice() );
        disMmttMmtt00000100.setScrapPriceDate( dto.getScrapPriceDate() );
        disMmttMmtt00000100.setRecState( dto.getRecState() );
        disMmttMmtt00000100.setCrtDate( dto.getCrtDate() );
        disMmttMmtt00000100.setLastDate( dto.getLastDate() );
        disMmttMmtt00000100.setCrtUser( dto.getCrtUser() );
        disMmttMmtt00000100.setLastUser( dto.getLastUser() );
        disMmttMmtt00000100.setOwner( dto.getOwner() );
        disMmttMmtt00000100.setRecEnt( dto.getRecEnt() );
        disMmttMmtt00000100.setRecOU( dto.getRecOU() );
        disMmttMmtt00000100.setRecSec( dto.getRecSec() );
        disMmttMmtt00000100.setCntID( dto.getCntID() );
        disMmttMmtt00000100.setRecID( dto.getRecID() );

        return disMmttMmtt00000100;
    }

    @Override
    public void updateFromDTO(DisMmttMmtt00000100DTO dto, DisMmttMmtt00000100 entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getMatRef() != null ) {
            entity.setMatRef( dto.getMatRef() );
        }
        if ( dto.getDensity() != null ) {
            entity.setDensity( dto.getDensity() );
        }
        if ( dto.getStrength() != null ) {
            entity.setStrength( dto.getStrength() );
        }
        if ( dto.getMFactor() != null ) {
            entity.setMFactor( dto.getMFactor() );
        }
        if ( dto.getPrice() != null ) {
            entity.setPrice( dto.getPrice() );
        }
        if ( dto.getIsMain() != null ) {
            entity.setIsMain( dto.getIsMain() );
        }
        if ( dto.getIntRef() != null ) {
            entity.setIntRef( dto.getIntRef() );
        }
        if ( dto.getDescrip() != null ) {
            entity.setDescrip( dto.getDescrip() );
        }
        if ( dto.getPriceDate() != null ) {
            entity.setPriceDate( dto.getPriceDate() );
        }
        if ( dto.getCGroup() != null ) {
            entity.setCGroup( dto.getCGroup() );
        }
        if ( dto.getScrapPrice() != null ) {
            entity.setScrapPrice( dto.getScrapPrice() );
        }
        if ( dto.getScrapPriceDate() != null ) {
            entity.setScrapPriceDate( dto.getScrapPriceDate() );
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
