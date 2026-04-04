package com.zhurong.platform.core.lantek.convert;

import com.zhurong.platform.core.lantek.dto.DisMmnnBwsr00000100DTO;
import com.zhurong.platform.core.lantek.dto.DisMmnnBwsr00000100PageQuery;
import com.zhurong.platform.core.lantek.entity.DisMmnnBwsr00000100;
import com.zhurong.platform.core.lantek.vo.DisMmnnBwsr00000100VO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-04-04T17:31:42+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.10 (Eclipse Adoptium)"
)
@Component
public class DisMmnnBwsr00000100ConvertImpl implements DisMmnnBwsr00000100Convert {

    @Override
    public DisMmnnBwsr00000100VO toVO(DisMmnnBwsr00000100 entity) {
        if ( entity == null ) {
            return null;
        }

        DisMmnnBwsr00000100VO disMmnnBwsr00000100VO = new DisMmnnBwsr00000100VO();

        disMmnnBwsr00000100VO.setNodeName( entity.getNodeName() );
        if ( entity.getNodeID() != null ) {
            disMmnnBwsr00000100VO.setNodeID( String.valueOf( entity.getNodeID() ) );
        }
        disMmnnBwsr00000100VO.setParentID( entity.getParentID() );
        disMmnnBwsr00000100VO.setBwsrType( entity.getBwsrType() );
        disMmnnBwsr00000100VO.setRecState( entity.getRecState() );
        disMmnnBwsr00000100VO.setCrtDate( entity.getCrtDate() );
        disMmnnBwsr00000100VO.setLastDate( entity.getLastDate() );
        disMmnnBwsr00000100VO.setCrtUser( entity.getCrtUser() );
        disMmnnBwsr00000100VO.setLastUser( entity.getLastUser() );
        disMmnnBwsr00000100VO.setOwner( entity.getOwner() );
        disMmnnBwsr00000100VO.setRecEnt( entity.getRecEnt() );
        disMmnnBwsr00000100VO.setRecOU( entity.getRecOU() );
        disMmnnBwsr00000100VO.setRecSec( entity.getRecSec() );
        disMmnnBwsr00000100VO.setCntID( entity.getCntID() );
        disMmnnBwsr00000100VO.setRecID( entity.getRecID() );

        return disMmnnBwsr00000100VO;
    }

    @Override
    public List<DisMmnnBwsr00000100VO> toVOList(List<DisMmnnBwsr00000100> list) {
        if ( list == null ) {
            return null;
        }

        List<DisMmnnBwsr00000100VO> list1 = new ArrayList<DisMmnnBwsr00000100VO>( list.size() );
        for ( DisMmnnBwsr00000100 disMmnnBwsr00000100 : list ) {
            list1.add( toVO( disMmnnBwsr00000100 ) );
        }

        return list1;
    }

    @Override
    public DisMmnnBwsr00000100 toEntity(DisMmnnBwsr00000100DTO dto) {
        if ( dto == null ) {
            return null;
        }

        DisMmnnBwsr00000100 disMmnnBwsr00000100 = new DisMmnnBwsr00000100();

        disMmnnBwsr00000100.setNodeName( dto.getNodeName() );
        disMmnnBwsr00000100.setNodeID( dto.getNodeID() );
        disMmnnBwsr00000100.setParentID( dto.getParentID() );
        disMmnnBwsr00000100.setBwsrType( dto.getBwsrType() );
        disMmnnBwsr00000100.setRecState( dto.getRecState() );
        disMmnnBwsr00000100.setCrtDate( dto.getCrtDate() );
        disMmnnBwsr00000100.setLastDate( dto.getLastDate() );
        disMmnnBwsr00000100.setCrtUser( dto.getCrtUser() );
        disMmnnBwsr00000100.setLastUser( dto.getLastUser() );
        disMmnnBwsr00000100.setOwner( dto.getOwner() );
        disMmnnBwsr00000100.setRecEnt( dto.getRecEnt() );
        disMmnnBwsr00000100.setRecOU( dto.getRecOU() );
        disMmnnBwsr00000100.setRecSec( dto.getRecSec() );
        disMmnnBwsr00000100.setCntID( dto.getCntID() );
        disMmnnBwsr00000100.setRecID( dto.getRecID() );

        return disMmnnBwsr00000100;
    }

    @Override
    public DisMmnnBwsr00000100 toEntity(DisMmnnBwsr00000100PageQuery dto) {
        if ( dto == null ) {
            return null;
        }

        DisMmnnBwsr00000100 disMmnnBwsr00000100 = new DisMmnnBwsr00000100();

        disMmnnBwsr00000100.setNodeName( dto.getNodeName() );
        disMmnnBwsr00000100.setNodeID( dto.getNodeID() );
        disMmnnBwsr00000100.setParentID( dto.getParentID() );
        disMmnnBwsr00000100.setBwsrType( dto.getBwsrType() );
        disMmnnBwsr00000100.setRecState( dto.getRecState() );
        disMmnnBwsr00000100.setCrtDate( dto.getCrtDate() );
        disMmnnBwsr00000100.setLastDate( dto.getLastDate() );
        disMmnnBwsr00000100.setCrtUser( dto.getCrtUser() );
        disMmnnBwsr00000100.setLastUser( dto.getLastUser() );
        disMmnnBwsr00000100.setOwner( dto.getOwner() );
        disMmnnBwsr00000100.setRecEnt( dto.getRecEnt() );
        disMmnnBwsr00000100.setRecOU( dto.getRecOU() );
        disMmnnBwsr00000100.setRecSec( dto.getRecSec() );
        disMmnnBwsr00000100.setCntID( dto.getCntID() );
        disMmnnBwsr00000100.setRecID( dto.getRecID() );

        return disMmnnBwsr00000100;
    }

    @Override
    public void updateFromDTO(DisMmnnBwsr00000100DTO dto, DisMmnnBwsr00000100 entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getNodeName() != null ) {
            entity.setNodeName( dto.getNodeName() );
        }
        if ( dto.getNodeID() != null ) {
            entity.setNodeID( dto.getNodeID() );
        }
        if ( dto.getParentID() != null ) {
            entity.setParentID( dto.getParentID() );
        }
        if ( dto.getBwsrType() != null ) {
            entity.setBwsrType( dto.getBwsrType() );
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
