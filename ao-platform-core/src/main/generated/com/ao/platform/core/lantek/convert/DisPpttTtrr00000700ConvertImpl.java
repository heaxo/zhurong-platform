package com.ao.platform.core.lantek.convert;

import com.ao.platform.core.lantek.dto.DisPpttTtrr00000700DTO;
import com.ao.platform.core.lantek.dto.DisPpttTtrr00000700PageQuery;
import com.ao.platform.core.lantek.entity.DisPpttTtrr00000700;
import com.ao.platform.core.lantek.vo.DisPpttTtrr00000700VO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-04T22:48:14+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.10 (Eclipse Adoptium)"
)
@Component
public class DisPpttTtrr00000700ConvertImpl implements DisPpttTtrr00000700Convert {

    @Override
    public DisPpttTtrr00000700VO toVO(DisPpttTtrr00000700 entity) {
        if ( entity == null ) {
            return null;
        }

        DisPpttTtrr00000700VO disPpttTtrr00000700VO = new DisPpttTtrr00000700VO();

        disPpttTtrr00000700VO.setTurrRef( entity.getTurrRef() );
        disPpttTtrr00000700VO.setTrType( entity.getTrType() );
        disPpttTtrr00000700VO.setTrCategory( entity.getTrCategory() );
        disPpttTtrr00000700VO.setDescrip( entity.getDescrip() );
        disPpttTtrr00000700VO.setToolClass( entity.getToolClass() );
        disPpttTtrr00000700VO.setRecState( entity.getRecState() );
        disPpttTtrr00000700VO.setCrtDate( entity.getCrtDate() );
        disPpttTtrr00000700VO.setLastDate( entity.getLastDate() );
        disPpttTtrr00000700VO.setCrtUser( entity.getCrtUser() );
        disPpttTtrr00000700VO.setLastUser( entity.getLastUser() );
        disPpttTtrr00000700VO.setOwner( entity.getOwner() );
        disPpttTtrr00000700VO.setRecEnt( entity.getRecEnt() );
        disPpttTtrr00000700VO.setRecOU( entity.getRecOU() );
        disPpttTtrr00000700VO.setRecSec( entity.getRecSec() );
        disPpttTtrr00000700VO.setCntID( entity.getCntID() );
        disPpttTtrr00000700VO.setRecID( entity.getRecID() );

        return disPpttTtrr00000700VO;
    }

    @Override
    public List<DisPpttTtrr00000700VO> toVOList(List<DisPpttTtrr00000700> list) {
        if ( list == null ) {
            return null;
        }

        List<DisPpttTtrr00000700VO> list1 = new ArrayList<DisPpttTtrr00000700VO>( list.size() );
        for ( DisPpttTtrr00000700 disPpttTtrr00000700 : list ) {
            list1.add( toVO( disPpttTtrr00000700 ) );
        }

        return list1;
    }

    @Override
    public DisPpttTtrr00000700 toEntity(DisPpttTtrr00000700DTO dto) {
        if ( dto == null ) {
            return null;
        }

        DisPpttTtrr00000700 disPpttTtrr00000700 = new DisPpttTtrr00000700();

        disPpttTtrr00000700.setTurrRef( dto.getTurrRef() );
        disPpttTtrr00000700.setTrType( dto.getTrType() );
        disPpttTtrr00000700.setTrCategory( dto.getTrCategory() );
        disPpttTtrr00000700.setDescrip( dto.getDescrip() );
        disPpttTtrr00000700.setToolClass( dto.getToolClass() );
        disPpttTtrr00000700.setRecState( dto.getRecState() );
        disPpttTtrr00000700.setCrtDate( dto.getCrtDate() );
        disPpttTtrr00000700.setLastDate( dto.getLastDate() );
        disPpttTtrr00000700.setCrtUser( dto.getCrtUser() );
        disPpttTtrr00000700.setLastUser( dto.getLastUser() );
        disPpttTtrr00000700.setOwner( dto.getOwner() );
        disPpttTtrr00000700.setRecEnt( dto.getRecEnt() );
        disPpttTtrr00000700.setRecOU( dto.getRecOU() );
        disPpttTtrr00000700.setRecSec( dto.getRecSec() );
        disPpttTtrr00000700.setCntID( dto.getCntID() );
        disPpttTtrr00000700.setRecID( dto.getRecID() );

        return disPpttTtrr00000700;
    }

    @Override
    public DisPpttTtrr00000700 toEntity(DisPpttTtrr00000700PageQuery dto) {
        if ( dto == null ) {
            return null;
        }

        DisPpttTtrr00000700 disPpttTtrr00000700 = new DisPpttTtrr00000700();

        disPpttTtrr00000700.setTurrRef( dto.getTurrRef() );
        disPpttTtrr00000700.setTrType( dto.getTrType() );
        disPpttTtrr00000700.setTrCategory( dto.getTrCategory() );
        disPpttTtrr00000700.setDescrip( dto.getDescrip() );
        disPpttTtrr00000700.setToolClass( dto.getToolClass() );
        disPpttTtrr00000700.setRecState( dto.getRecState() );
        disPpttTtrr00000700.setCrtDate( dto.getCrtDate() );
        disPpttTtrr00000700.setLastDate( dto.getLastDate() );
        disPpttTtrr00000700.setCrtUser( dto.getCrtUser() );
        disPpttTtrr00000700.setLastUser( dto.getLastUser() );
        disPpttTtrr00000700.setOwner( dto.getOwner() );
        disPpttTtrr00000700.setRecEnt( dto.getRecEnt() );
        disPpttTtrr00000700.setRecOU( dto.getRecOU() );
        disPpttTtrr00000700.setRecSec( dto.getRecSec() );
        disPpttTtrr00000700.setCntID( dto.getCntID() );
        disPpttTtrr00000700.setRecID( dto.getRecID() );

        return disPpttTtrr00000700;
    }

    @Override
    public void updateFromDTO(DisPpttTtrr00000700DTO dto, DisPpttTtrr00000700 entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getTurrRef() != null ) {
            entity.setTurrRef( dto.getTurrRef() );
        }
        if ( dto.getTrType() != null ) {
            entity.setTrType( dto.getTrType() );
        }
        if ( dto.getTrCategory() != null ) {
            entity.setTrCategory( dto.getTrCategory() );
        }
        if ( dto.getDescrip() != null ) {
            entity.setDescrip( dto.getDescrip() );
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
