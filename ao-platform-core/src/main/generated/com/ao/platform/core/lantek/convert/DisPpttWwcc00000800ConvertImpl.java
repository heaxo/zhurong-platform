package com.ao.platform.core.lantek.convert;

import com.ao.platform.core.lantek.dto.DisPpttWwcc00000800DTO;
import com.ao.platform.core.lantek.dto.DisPpttWwcc00000800PageQuery;
import com.ao.platform.core.lantek.entity.DisPpttWwcc00000800;
import com.ao.platform.core.lantek.vo.DisPpttWwcc00000800VO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-06T15:33:07+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.10 (Eclipse Adoptium)"
)
@Component
public class DisPpttWwcc00000800ConvertImpl implements DisPpttWwcc00000800Convert {

    @Override
    public DisPpttWwcc00000800VO toVO(DisPpttWwcc00000800 entity) {
        if ( entity == null ) {
            return null;
        }

        DisPpttWwcc00000800VO disPpttWwcc00000800VO = new DisPpttWwcc00000800VO();

        disPpttWwcc00000800VO.setWrkRef( entity.getWrkRef() );
        disPpttWwcc00000800VO.setTurrRef( entity.getTurrRef() );
        disPpttWwcc00000800VO.setRecState( entity.getRecState() );
        disPpttWwcc00000800VO.setCrtDate( entity.getCrtDate() );
        disPpttWwcc00000800VO.setLastDate( entity.getLastDate() );
        disPpttWwcc00000800VO.setCrtUser( entity.getCrtUser() );
        disPpttWwcc00000800VO.setLastUser( entity.getLastUser() );
        disPpttWwcc00000800VO.setOwner( entity.getOwner() );
        disPpttWwcc00000800VO.setRecEnt( entity.getRecEnt() );
        disPpttWwcc00000800VO.setRecOU( entity.getRecOU() );
        disPpttWwcc00000800VO.setRecSec( entity.getRecSec() );
        disPpttWwcc00000800VO.setCntID( entity.getCntID() );
        disPpttWwcc00000800VO.setRecID( entity.getRecID() );

        return disPpttWwcc00000800VO;
    }

    @Override
    public List<DisPpttWwcc00000800VO> toVOList(List<DisPpttWwcc00000800> list) {
        if ( list == null ) {
            return null;
        }

        List<DisPpttWwcc00000800VO> list1 = new ArrayList<DisPpttWwcc00000800VO>( list.size() );
        for ( DisPpttWwcc00000800 disPpttWwcc00000800 : list ) {
            list1.add( toVO( disPpttWwcc00000800 ) );
        }

        return list1;
    }

    @Override
    public DisPpttWwcc00000800 toEntity(DisPpttWwcc00000800DTO dto) {
        if ( dto == null ) {
            return null;
        }

        DisPpttWwcc00000800 disPpttWwcc00000800 = new DisPpttWwcc00000800();

        disPpttWwcc00000800.setWrkRef( dto.getWrkRef() );
        disPpttWwcc00000800.setTurrRef( dto.getTurrRef() );
        disPpttWwcc00000800.setRecState( dto.getRecState() );
        disPpttWwcc00000800.setCrtDate( dto.getCrtDate() );
        disPpttWwcc00000800.setLastDate( dto.getLastDate() );
        disPpttWwcc00000800.setCrtUser( dto.getCrtUser() );
        disPpttWwcc00000800.setLastUser( dto.getLastUser() );
        disPpttWwcc00000800.setOwner( dto.getOwner() );
        disPpttWwcc00000800.setRecEnt( dto.getRecEnt() );
        disPpttWwcc00000800.setRecOU( dto.getRecOU() );
        disPpttWwcc00000800.setRecSec( dto.getRecSec() );
        disPpttWwcc00000800.setCntID( dto.getCntID() );
        disPpttWwcc00000800.setRecID( dto.getRecID() );

        return disPpttWwcc00000800;
    }

    @Override
    public DisPpttWwcc00000800 toEntity(DisPpttWwcc00000800PageQuery dto) {
        if ( dto == null ) {
            return null;
        }

        DisPpttWwcc00000800 disPpttWwcc00000800 = new DisPpttWwcc00000800();

        disPpttWwcc00000800.setWrkRef( dto.getWrkRef() );
        disPpttWwcc00000800.setTurrRef( dto.getTurrRef() );
        disPpttWwcc00000800.setRecState( dto.getRecState() );
        disPpttWwcc00000800.setCrtDate( dto.getCrtDate() );
        disPpttWwcc00000800.setLastDate( dto.getLastDate() );
        disPpttWwcc00000800.setCrtUser( dto.getCrtUser() );
        disPpttWwcc00000800.setLastUser( dto.getLastUser() );
        disPpttWwcc00000800.setOwner( dto.getOwner() );
        disPpttWwcc00000800.setRecEnt( dto.getRecEnt() );
        disPpttWwcc00000800.setRecOU( dto.getRecOU() );
        disPpttWwcc00000800.setRecSec( dto.getRecSec() );
        disPpttWwcc00000800.setCntID( dto.getCntID() );
        disPpttWwcc00000800.setRecID( dto.getRecID() );

        return disPpttWwcc00000800;
    }

    @Override
    public void updateFromDTO(DisPpttWwcc00000800DTO dto, DisPpttWwcc00000800 entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getWrkRef() != null ) {
            entity.setWrkRef( dto.getWrkRef() );
        }
        if ( dto.getTurrRef() != null ) {
            entity.setTurrRef( dto.getTurrRef() );
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
