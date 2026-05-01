package com.zhurong.platform.core.lantek.convert;

import com.zhurong.platform.core.lantek.dto.DisMmnnBwsr00000200DTO;
import com.zhurong.platform.core.lantek.dto.DisMmnnBwsr00000200PageQuery;
import com.zhurong.platform.core.lantek.entity.DisMmnnBwsr00000200;
import com.zhurong.platform.core.lantek.vo.DisMmnnBwsr00000200VO;
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
public class DisMmnnBwsr00000200ConvertImpl implements DisMmnnBwsr00000200Convert {

    @Override
    public DisMmnnBwsr00000200VO toVO(DisMmnnBwsr00000200 entity) {
        if ( entity == null ) {
            return null;
        }

        DisMmnnBwsr00000200VO disMmnnBwsr00000200VO = new DisMmnnBwsr00000200VO();

        disMmnnBwsr00000200VO.setBwsrID( entity.getBwsrID() );
        disMmnnBwsr00000200VO.setTblRef( entity.getTblRef() );
        disMmnnBwsr00000200VO.setRecordID( entity.getRecordID() );
        disMmnnBwsr00000200VO.setRecState( entity.getRecState() );
        disMmnnBwsr00000200VO.setCrtDate( entity.getCrtDate() );
        disMmnnBwsr00000200VO.setLastDate( entity.getLastDate() );
        disMmnnBwsr00000200VO.setCrtUser( entity.getCrtUser() );
        disMmnnBwsr00000200VO.setLastUser( entity.getLastUser() );
        disMmnnBwsr00000200VO.setOwner( entity.getOwner() );
        disMmnnBwsr00000200VO.setRecEnt( entity.getRecEnt() );
        disMmnnBwsr00000200VO.setRecOU( entity.getRecOU() );
        disMmnnBwsr00000200VO.setRecSec( entity.getRecSec() );
        disMmnnBwsr00000200VO.setCntID( entity.getCntID() );
        disMmnnBwsr00000200VO.setRecID( entity.getRecID() );

        return disMmnnBwsr00000200VO;
    }

    @Override
    public List<DisMmnnBwsr00000200VO> toVOList(List<DisMmnnBwsr00000200> list) {
        if ( list == null ) {
            return null;
        }

        List<DisMmnnBwsr00000200VO> list1 = new ArrayList<DisMmnnBwsr00000200VO>( list.size() );
        for ( DisMmnnBwsr00000200 disMmnnBwsr00000200 : list ) {
            list1.add( toVO( disMmnnBwsr00000200 ) );
        }

        return list1;
    }

    @Override
    public DisMmnnBwsr00000200 toEntity(DisMmnnBwsr00000200DTO dto) {
        if ( dto == null ) {
            return null;
        }

        DisMmnnBwsr00000200 disMmnnBwsr00000200 = new DisMmnnBwsr00000200();

        disMmnnBwsr00000200.setBwsrID( dto.getBwsrID() );
        disMmnnBwsr00000200.setTblRef( dto.getTblRef() );
        disMmnnBwsr00000200.setRecordID( dto.getRecordID() );
        disMmnnBwsr00000200.setRecState( dto.getRecState() );
        disMmnnBwsr00000200.setCrtDate( dto.getCrtDate() );
        disMmnnBwsr00000200.setLastDate( dto.getLastDate() );
        disMmnnBwsr00000200.setCrtUser( dto.getCrtUser() );
        disMmnnBwsr00000200.setLastUser( dto.getLastUser() );
        disMmnnBwsr00000200.setOwner( dto.getOwner() );
        disMmnnBwsr00000200.setRecEnt( dto.getRecEnt() );
        disMmnnBwsr00000200.setRecOU( dto.getRecOU() );
        disMmnnBwsr00000200.setRecSec( dto.getRecSec() );
        disMmnnBwsr00000200.setCntID( dto.getCntID() );
        disMmnnBwsr00000200.setRecID( dto.getRecID() );

        return disMmnnBwsr00000200;
    }

    @Override
    public DisMmnnBwsr00000200 toEntity(DisMmnnBwsr00000200PageQuery dto) {
        if ( dto == null ) {
            return null;
        }

        DisMmnnBwsr00000200 disMmnnBwsr00000200 = new DisMmnnBwsr00000200();

        disMmnnBwsr00000200.setBwsrID( dto.getBwsrID() );
        disMmnnBwsr00000200.setTblRef( dto.getTblRef() );
        disMmnnBwsr00000200.setRecordID( dto.getRecordID() );
        disMmnnBwsr00000200.setRecState( dto.getRecState() );
        disMmnnBwsr00000200.setCrtDate( dto.getCrtDate() );
        disMmnnBwsr00000200.setLastDate( dto.getLastDate() );
        disMmnnBwsr00000200.setCrtUser( dto.getCrtUser() );
        disMmnnBwsr00000200.setLastUser( dto.getLastUser() );
        disMmnnBwsr00000200.setOwner( dto.getOwner() );
        disMmnnBwsr00000200.setRecEnt( dto.getRecEnt() );
        disMmnnBwsr00000200.setRecOU( dto.getRecOU() );
        disMmnnBwsr00000200.setRecSec( dto.getRecSec() );
        disMmnnBwsr00000200.setCntID( dto.getCntID() );
        disMmnnBwsr00000200.setRecID( dto.getRecID() );

        return disMmnnBwsr00000200;
    }

    @Override
    public void updateFromDTO(DisMmnnBwsr00000200DTO dto, DisMmnnBwsr00000200 entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getBwsrID() != null ) {
            entity.setBwsrID( dto.getBwsrID() );
        }
        if ( dto.getTblRef() != null ) {
            entity.setTblRef( dto.getTblRef() );
        }
        if ( dto.getRecordID() != null ) {
            entity.setRecordID( dto.getRecordID() );
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
