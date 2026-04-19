package com.zhurong.platform.core.lantek.convert;

import com.zhurong.platform.core.lantek.dto.WwhhWwhh00000100DTO;
import com.zhurong.platform.core.lantek.dto.WwhhWwhh00000100PageQuery;
import com.zhurong.platform.core.lantek.entity.WwhhWwhh00000100;
import com.zhurong.platform.core.lantek.vo.WwhhWwhh00000100VO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-04-18T12:26:16+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.10 (Eclipse Adoptium)"
)
@Component
public class WwhhWwhh00000100ConvertImpl implements WwhhWwhh00000100Convert {

    @Override
    public WwhhWwhh00000100VO toVO(WwhhWwhh00000100 entity) {
        if ( entity == null ) {
            return null;
        }

        WwhhWwhh00000100VO wwhhWwhh00000100VO = new WwhhWwhh00000100VO();

        wwhhWwhh00000100VO.setWrhRef( entity.getWrhRef() );
        wwhhWwhh00000100VO.setWrhName( entity.getWrhName() );
        wwhhWwhh00000100VO.setIsOutsourcing( entity.getIsOutsourcing() );
        wwhhWwhh00000100VO.setDescrip( entity.getDescrip() );
        wwhhWwhh00000100VO.setDIS_Automatic( entity.getDIS_Automatic() );
        wwhhWwhh00000100VO.setDIS_WosWhDriver( entity.getDIS_WosWhDriver() );
        wwhhWwhh00000100VO.setDIS_IsWorkCenter( entity.getDIS_IsWorkCenter() );
        wwhhWwhh00000100VO.setRecState( entity.getRecState() );
        wwhhWwhh00000100VO.setCrtDate( entity.getCrtDate() );
        wwhhWwhh00000100VO.setLastDate( entity.getLastDate() );
        wwhhWwhh00000100VO.setCrtUser( entity.getCrtUser() );
        wwhhWwhh00000100VO.setLastUser( entity.getLastUser() );
        wwhhWwhh00000100VO.setOwner( entity.getOwner() );
        wwhhWwhh00000100VO.setRecEnt( entity.getRecEnt() );
        wwhhWwhh00000100VO.setRecOU( entity.getRecOU() );
        wwhhWwhh00000100VO.setRecSec( entity.getRecSec() );
        wwhhWwhh00000100VO.setCntID( entity.getCntID() );
        wwhhWwhh00000100VO.setRecID( entity.getRecID() );

        return wwhhWwhh00000100VO;
    }

    @Override
    public List<WwhhWwhh00000100VO> toVOList(List<WwhhWwhh00000100> list) {
        if ( list == null ) {
            return null;
        }

        List<WwhhWwhh00000100VO> list1 = new ArrayList<WwhhWwhh00000100VO>( list.size() );
        for ( WwhhWwhh00000100 wwhhWwhh00000100 : list ) {
            list1.add( toVO( wwhhWwhh00000100 ) );
        }

        return list1;
    }

    @Override
    public WwhhWwhh00000100 toEntity(WwhhWwhh00000100DTO dto) {
        if ( dto == null ) {
            return null;
        }

        WwhhWwhh00000100 wwhhWwhh00000100 = new WwhhWwhh00000100();

        wwhhWwhh00000100.setWrhRef( dto.getWrhRef() );
        wwhhWwhh00000100.setWrhName( dto.getWrhName() );
        wwhhWwhh00000100.setIsOutsourcing( dto.getIsOutsourcing() );
        wwhhWwhh00000100.setDescrip( dto.getDescrip() );
        wwhhWwhh00000100.setDIS_Automatic( dto.getDIS_Automatic() );
        wwhhWwhh00000100.setDIS_WosWhDriver( dto.getDIS_WosWhDriver() );
        wwhhWwhh00000100.setDIS_IsWorkCenter( dto.getDIS_IsWorkCenter() );
        wwhhWwhh00000100.setRecState( dto.getRecState() );
        wwhhWwhh00000100.setCrtDate( dto.getCrtDate() );
        wwhhWwhh00000100.setLastDate( dto.getLastDate() );
        wwhhWwhh00000100.setCrtUser( dto.getCrtUser() );
        wwhhWwhh00000100.setLastUser( dto.getLastUser() );
        wwhhWwhh00000100.setOwner( dto.getOwner() );
        wwhhWwhh00000100.setRecEnt( dto.getRecEnt() );
        wwhhWwhh00000100.setRecOU( dto.getRecOU() );
        wwhhWwhh00000100.setRecSec( dto.getRecSec() );
        wwhhWwhh00000100.setCntID( dto.getCntID() );
        wwhhWwhh00000100.setRecID( dto.getRecID() );

        return wwhhWwhh00000100;
    }

    @Override
    public WwhhWwhh00000100 toEntity(WwhhWwhh00000100PageQuery dto) {
        if ( dto == null ) {
            return null;
        }

        WwhhWwhh00000100 wwhhWwhh00000100 = new WwhhWwhh00000100();

        wwhhWwhh00000100.setWrhRef( dto.getWrhRef() );
        wwhhWwhh00000100.setWrhName( dto.getWrhName() );
        wwhhWwhh00000100.setIsOutsourcing( dto.getIsOutsourcing() );
        wwhhWwhh00000100.setDescrip( dto.getDescrip() );
        wwhhWwhh00000100.setRecState( dto.getRecState() );
        wwhhWwhh00000100.setCrtDate( dto.getCrtDate() );
        wwhhWwhh00000100.setLastDate( dto.getLastDate() );
        wwhhWwhh00000100.setCrtUser( dto.getCrtUser() );
        wwhhWwhh00000100.setLastUser( dto.getLastUser() );
        wwhhWwhh00000100.setOwner( dto.getOwner() );
        wwhhWwhh00000100.setRecEnt( dto.getRecEnt() );
        wwhhWwhh00000100.setRecOU( dto.getRecOU() );
        wwhhWwhh00000100.setRecSec( dto.getRecSec() );
        wwhhWwhh00000100.setCntID( dto.getCntID() );
        wwhhWwhh00000100.setRecID( dto.getRecID() );

        return wwhhWwhh00000100;
    }

    @Override
    public void updateFromDTO(WwhhWwhh00000100DTO dto, WwhhWwhh00000100 entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getWrhRef() != null ) {
            entity.setWrhRef( dto.getWrhRef() );
        }
        if ( dto.getWrhName() != null ) {
            entity.setWrhName( dto.getWrhName() );
        }
        if ( dto.getIsOutsourcing() != null ) {
            entity.setIsOutsourcing( dto.getIsOutsourcing() );
        }
        if ( dto.getDescrip() != null ) {
            entity.setDescrip( dto.getDescrip() );
        }
        if ( dto.getDIS_Automatic() != null ) {
            entity.setDIS_Automatic( dto.getDIS_Automatic() );
        }
        if ( dto.getDIS_WosWhDriver() != null ) {
            entity.setDIS_WosWhDriver( dto.getDIS_WosWhDriver() );
        }
        if ( dto.getDIS_IsWorkCenter() != null ) {
            entity.setDIS_IsWorkCenter( dto.getDIS_IsWorkCenter() );
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
