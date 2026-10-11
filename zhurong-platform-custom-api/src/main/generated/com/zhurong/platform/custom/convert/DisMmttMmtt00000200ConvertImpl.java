package com.zhurong.platform.custom.convert;

import com.zhurong.platform.core.lantek.dto.DisMmttMmtt00000200DTO;
import com.zhurong.platform.core.lantek.dto.DisMmttMmtt00000200PageQuery;
import com.zhurong.platform.core.lantek.vo.DisMmttMmtt00000200VO;
import com.zhurong.platform.custom.entity.DisMmttMmtt00000200;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-10-11T16:43:19+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.10 (Eclipse Adoptium)"
)
@Component
public class DisMmttMmtt00000200ConvertImpl implements DisMmttMmtt00000200Convert {

    @Override
    public DisMmttMmtt00000200VO toVO(DisMmttMmtt00000200 entity) {
        if ( entity == null ) {
            return null;
        }

        DisMmttMmtt00000200VO disMmttMmtt00000200VO = new DisMmttMmtt00000200VO();

        disMmttMmtt00000200VO.setMatRef( entity.getMatRef() );
        disMmttMmtt00000200VO.setMinThickness( entity.getMinThickness() );
        disMmttMmtt00000200VO.setMaxThickness( entity.getMaxThickness() );
        disMmttMmtt00000200VO.setPrice( entity.getPrice() );
        disMmttMmtt00000200VO.setPriceDate( entity.getPriceDate() );
        disMmttMmtt00000200VO.setDescrip( entity.getDescrip() );
        disMmttMmtt00000200VO.setRecState( entity.getRecState() );
        disMmttMmtt00000200VO.setCrtDate( entity.getCrtDate() );
        disMmttMmtt00000200VO.setLastDate( entity.getLastDate() );
        disMmttMmtt00000200VO.setCrtUser( entity.getCrtUser() );
        disMmttMmtt00000200VO.setLastUser( entity.getLastUser() );
        disMmttMmtt00000200VO.setOwner( entity.getOwner() );
        disMmttMmtt00000200VO.setRecEnt( entity.getRecEnt() );
        disMmttMmtt00000200VO.setRecOU( entity.getRecOU() );
        disMmttMmtt00000200VO.setRecSec( entity.getRecSec() );
        disMmttMmtt00000200VO.setCntID( entity.getCntID() );
        disMmttMmtt00000200VO.setRecID( entity.getRecID() );

        return disMmttMmtt00000200VO;
    }

    @Override
    public List<DisMmttMmtt00000200VO> toVOList(List<DisMmttMmtt00000200> list) {
        if ( list == null ) {
            return null;
        }

        List<DisMmttMmtt00000200VO> list1 = new ArrayList<DisMmttMmtt00000200VO>( list.size() );
        for ( DisMmttMmtt00000200 disMmttMmtt00000200 : list ) {
            list1.add( toVO( disMmttMmtt00000200 ) );
        }

        return list1;
    }

    @Override
    public DisMmttMmtt00000200 toEntity(DisMmttMmtt00000200DTO dto) {
        if ( dto == null ) {
            return null;
        }

        DisMmttMmtt00000200 disMmttMmtt00000200 = new DisMmttMmtt00000200();

        disMmttMmtt00000200.setMatRef( dto.getMatRef() );
        disMmttMmtt00000200.setMinThickness( dto.getMinThickness() );
        disMmttMmtt00000200.setMaxThickness( dto.getMaxThickness() );
        disMmttMmtt00000200.setPrice( dto.getPrice() );
        disMmttMmtt00000200.setPriceDate( dto.getPriceDate() );
        disMmttMmtt00000200.setDescrip( dto.getDescrip() );
        disMmttMmtt00000200.setRecState( dto.getRecState() );
        disMmttMmtt00000200.setCrtDate( dto.getCrtDate() );
        disMmttMmtt00000200.setLastDate( dto.getLastDate() );
        disMmttMmtt00000200.setCrtUser( dto.getCrtUser() );
        disMmttMmtt00000200.setLastUser( dto.getLastUser() );
        disMmttMmtt00000200.setOwner( dto.getOwner() );
        disMmttMmtt00000200.setRecEnt( dto.getRecEnt() );
        disMmttMmtt00000200.setRecOU( dto.getRecOU() );
        disMmttMmtt00000200.setRecSec( dto.getRecSec() );
        disMmttMmtt00000200.setCntID( dto.getCntID() );
        disMmttMmtt00000200.setRecID( dto.getRecID() );

        return disMmttMmtt00000200;
    }

    @Override
    public DisMmttMmtt00000200 toEntity(DisMmttMmtt00000200PageQuery dto) {
        if ( dto == null ) {
            return null;
        }

        DisMmttMmtt00000200 disMmttMmtt00000200 = new DisMmttMmtt00000200();

        disMmttMmtt00000200.setMatRef( dto.getMatRef() );
        disMmttMmtt00000200.setMinThickness( dto.getMinThickness() );
        disMmttMmtt00000200.setMaxThickness( dto.getMaxThickness() );
        disMmttMmtt00000200.setPrice( dto.getPrice() );
        disMmttMmtt00000200.setPriceDate( dto.getPriceDate() );
        disMmttMmtt00000200.setDescrip( dto.getDescrip() );
        disMmttMmtt00000200.setRecState( dto.getRecState() );
        disMmttMmtt00000200.setCrtDate( dto.getCrtDate() );
        disMmttMmtt00000200.setLastDate( dto.getLastDate() );
        disMmttMmtt00000200.setCrtUser( dto.getCrtUser() );
        disMmttMmtt00000200.setLastUser( dto.getLastUser() );
        disMmttMmtt00000200.setOwner( dto.getOwner() );
        disMmttMmtt00000200.setRecEnt( dto.getRecEnt() );
        disMmttMmtt00000200.setRecOU( dto.getRecOU() );
        disMmttMmtt00000200.setRecSec( dto.getRecSec() );
        disMmttMmtt00000200.setCntID( dto.getCntID() );
        disMmttMmtt00000200.setRecID( dto.getRecID() );

        return disMmttMmtt00000200;
    }

    @Override
    public void updateFromDTO(DisMmttMmtt00000200DTO dto, DisMmttMmtt00000200 entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getMatRef() != null ) {
            entity.setMatRef( dto.getMatRef() );
        }
        if ( dto.getMinThickness() != null ) {
            entity.setMinThickness( dto.getMinThickness() );
        }
        if ( dto.getMaxThickness() != null ) {
            entity.setMaxThickness( dto.getMaxThickness() );
        }
        if ( dto.getPrice() != null ) {
            entity.setPrice( dto.getPrice() );
        }
        if ( dto.getPriceDate() != null ) {
            entity.setPriceDate( dto.getPriceDate() );
        }
        if ( dto.getDescrip() != null ) {
            entity.setDescrip( dto.getDescrip() );
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
