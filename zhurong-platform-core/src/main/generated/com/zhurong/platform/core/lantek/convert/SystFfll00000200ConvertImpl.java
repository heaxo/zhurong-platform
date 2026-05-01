package com.zhurong.platform.core.lantek.convert;

import com.zhurong.platform.core.lantek.dto.SystFfll00000200DTO;
import com.zhurong.platform.core.lantek.dto.SystFfll00000200PageQuery;
import com.zhurong.platform.core.lantek.entity.SystFfll00000200;
import com.zhurong.platform.core.lantek.vo.SystFfll00000200VO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-01T18:49:30+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.10 (Eclipse Adoptium)"
)
@Component
public class SystFfll00000200ConvertImpl implements SystFfll00000200Convert {

    @Override
    public SystFfll00000200VO toVO(SystFfll00000200 entity) {
        if ( entity == null ) {
            return null;
        }

        SystFfll00000200VO systFfll00000200VO = new SystFfll00000200VO();

        systFfll00000200VO.setVltName( entity.getVltName() );
        systFfll00000200VO.setVltType( entity.getVltType() );
        systFfll00000200VO.setVltFld( entity.getVltFld() );
        systFfll00000200VO.setFileSave( entity.getFileSave() );
        systFfll00000200VO.setSvrName( entity.getSvrName() );
        systFfll00000200VO.setRecState( entity.getRecState() );
        systFfll00000200VO.setCrtDate( entity.getCrtDate() );
        systFfll00000200VO.setLastDate( entity.getLastDate() );
        systFfll00000200VO.setCrtUser( entity.getCrtUser() );
        systFfll00000200VO.setLastUser( entity.getLastUser() );
        systFfll00000200VO.setOwner( entity.getOwner() );
        systFfll00000200VO.setRecEnt( entity.getRecEnt() );
        systFfll00000200VO.setRecOU( entity.getRecOU() );
        systFfll00000200VO.setRecSec( entity.getRecSec() );
        systFfll00000200VO.setCntID( entity.getCntID() );
        systFfll00000200VO.setRecID( entity.getRecID() );

        return systFfll00000200VO;
    }

    @Override
    public List<SystFfll00000200VO> toVOList(List<SystFfll00000200> list) {
        if ( list == null ) {
            return null;
        }

        List<SystFfll00000200VO> list1 = new ArrayList<SystFfll00000200VO>( list.size() );
        for ( SystFfll00000200 systFfll00000200 : list ) {
            list1.add( toVO( systFfll00000200 ) );
        }

        return list1;
    }

    @Override
    public SystFfll00000200 toEntity(SystFfll00000200DTO dto) {
        if ( dto == null ) {
            return null;
        }

        SystFfll00000200 systFfll00000200 = new SystFfll00000200();

        systFfll00000200.setVltName( dto.getVltName() );
        systFfll00000200.setVltType( dto.getVltType() );
        systFfll00000200.setVltFld( dto.getVltFld() );
        systFfll00000200.setFileSave( dto.getFileSave() );
        systFfll00000200.setSvrName( dto.getSvrName() );
        systFfll00000200.setRecState( dto.getRecState() );
        systFfll00000200.setCrtDate( dto.getCrtDate() );
        systFfll00000200.setLastDate( dto.getLastDate() );
        systFfll00000200.setCrtUser( dto.getCrtUser() );
        systFfll00000200.setLastUser( dto.getLastUser() );
        systFfll00000200.setOwner( dto.getOwner() );
        systFfll00000200.setRecEnt( dto.getRecEnt() );
        systFfll00000200.setRecOU( dto.getRecOU() );
        systFfll00000200.setRecSec( dto.getRecSec() );
        systFfll00000200.setCntID( dto.getCntID() );
        systFfll00000200.setRecID( dto.getRecID() );

        return systFfll00000200;
    }

    @Override
    public SystFfll00000200 toEntity(SystFfll00000200PageQuery dto) {
        if ( dto == null ) {
            return null;
        }

        SystFfll00000200 systFfll00000200 = new SystFfll00000200();

        systFfll00000200.setVltName( dto.getVltName() );
        systFfll00000200.setVltType( dto.getVltType() );
        systFfll00000200.setVltFld( dto.getVltFld() );
        systFfll00000200.setFileSave( dto.getFileSave() );
        systFfll00000200.setSvrName( dto.getSvrName() );
        systFfll00000200.setRecState( dto.getRecState() );
        systFfll00000200.setCrtDate( dto.getCrtDate() );
        systFfll00000200.setLastDate( dto.getLastDate() );
        systFfll00000200.setCrtUser( dto.getCrtUser() );
        systFfll00000200.setLastUser( dto.getLastUser() );
        systFfll00000200.setOwner( dto.getOwner() );
        systFfll00000200.setRecEnt( dto.getRecEnt() );
        systFfll00000200.setRecOU( dto.getRecOU() );
        systFfll00000200.setRecSec( dto.getRecSec() );
        systFfll00000200.setCntID( dto.getCntID() );
        systFfll00000200.setRecID( dto.getRecID() );

        return systFfll00000200;
    }

    @Override
    public void updateFromDTO(SystFfll00000200DTO dto, SystFfll00000200 entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getVltName() != null ) {
            entity.setVltName( dto.getVltName() );
        }
        if ( dto.getVltType() != null ) {
            entity.setVltType( dto.getVltType() );
        }
        if ( dto.getVltFld() != null ) {
            entity.setVltFld( dto.getVltFld() );
        }
        if ( dto.getFileSave() != null ) {
            entity.setFileSave( dto.getFileSave() );
        }
        if ( dto.getSvrName() != null ) {
            entity.setSvrName( dto.getSvrName() );
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
