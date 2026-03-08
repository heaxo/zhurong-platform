package com.ao.platform.core.lantek.convert;

import com.ao.platform.core.lantek.dto.WwccWwcc00000100DTO;
import com.ao.platform.core.lantek.dto.WwccWwcc00000100PageQuery;
import com.ao.platform.core.lantek.entity.WwccWwcc00000100;
import com.ao.platform.core.lantek.vo.WwccWwcc00000100VO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-08T17:13:32+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.10 (Eclipse Adoptium)"
)
@Component
public class WwccWwcc00000100ConvertImpl implements WwccWwcc00000100Convert {

    @Override
    public WwccWwcc00000100VO toVO(WwccWwcc00000100 entity) {
        if ( entity == null ) {
            return null;
        }

        WwccWwcc00000100VO wwccWwcc00000100VO = new WwccWwcc00000100VO();

        wwccWwcc00000100VO.setWrkRef( entity.getWrkRef() );
        wwccWwcc00000100VO.setWrkOEM( entity.getWrkOEM() );
        wwccWwcc00000100VO.setWrkCNC( entity.getWrkCNC() );
        wwccWwcc00000100VO.setIsOutsourcing( entity.getIsOutsourcing() );
        wwccWwcc00000100VO.setWrkCapUCtName( entity.getWrkCapUCtName() );
        wwccWwcc00000100VO.setWrkCapUntName( entity.getWrkCapUntName() );
        wwccWwcc00000100VO.setWrkCap( entity.getWrkCap() );
        wwccWwcc00000100VO.setDescrip( entity.getDescrip() );
        wwccWwcc00000100VO.setIsActive( entity.getIsActive() );
        wwccWwcc00000100VO.setDIS_WrkType( entity.getDIS_WrkType() );
        wwccWwcc00000100VO.setDIS_OTechn( entity.getDIS_OTechn() );
        wwccWwcc00000100VO.setDIS_CfgFile( entity.getDIS_CfgFile() );
        wwccWwcc00000100VO.setDIS_PsfFile( entity.getDIS_PsfFile() );
        wwccWwcc00000100VO.setDIS_SawRef( entity.getDIS_SawRef() );
        wwccWwcc00000100VO.setDIS_Group( entity.getDIS_Group() );
        wwccWwcc00000100VO.setWosMessages( entity.getWosMessages() );
        wwccWwcc00000100VO.setWosState( entity.getWosState() );
        wwccWwcc00000100VO.setWosStateLastDate( entity.getWosStateLastDate() );
        wwccWwcc00000100VO.setWosPauseReason( entity.getWosPauseReason() );
        wwccWwcc00000100VO.setWosOperatorComment( entity.getWosOperatorComment() );
        wwccWwcc00000100VO.setRecState( entity.getRecState() );
        wwccWwcc00000100VO.setCrtDate( entity.getCrtDate() );
        wwccWwcc00000100VO.setLastDate( entity.getLastDate() );
        wwccWwcc00000100VO.setCrtUser( entity.getCrtUser() );
        wwccWwcc00000100VO.setLastUser( entity.getLastUser() );
        wwccWwcc00000100VO.setOwner( entity.getOwner() );
        wwccWwcc00000100VO.setRecEnt( entity.getRecEnt() );
        wwccWwcc00000100VO.setRecOU( entity.getRecOU() );
        wwccWwcc00000100VO.setRecSec( entity.getRecSec() );
        wwccWwcc00000100VO.setCntID( entity.getCntID() );
        wwccWwcc00000100VO.setRecID( entity.getRecID() );

        return wwccWwcc00000100VO;
    }

    @Override
    public List<WwccWwcc00000100VO> toVOList(List<WwccWwcc00000100> list) {
        if ( list == null ) {
            return null;
        }

        List<WwccWwcc00000100VO> list1 = new ArrayList<WwccWwcc00000100VO>( list.size() );
        for ( WwccWwcc00000100 wwccWwcc00000100 : list ) {
            list1.add( toVO( wwccWwcc00000100 ) );
        }

        return list1;
    }

    @Override
    public WwccWwcc00000100 toEntity(WwccWwcc00000100DTO dto) {
        if ( dto == null ) {
            return null;
        }

        WwccWwcc00000100 wwccWwcc00000100 = new WwccWwcc00000100();

        wwccWwcc00000100.setWrkRef( dto.getWrkRef() );
        wwccWwcc00000100.setWrkOEM( dto.getWrkOEM() );
        wwccWwcc00000100.setWrkCNC( dto.getWrkCNC() );
        wwccWwcc00000100.setIsOutsourcing( dto.getIsOutsourcing() );
        wwccWwcc00000100.setWrkCapUCtName( dto.getWrkCapUCtName() );
        wwccWwcc00000100.setWrkCapUntName( dto.getWrkCapUntName() );
        wwccWwcc00000100.setWrkCap( dto.getWrkCap() );
        wwccWwcc00000100.setDescrip( dto.getDescrip() );
        wwccWwcc00000100.setIsActive( dto.getIsActive() );
        wwccWwcc00000100.setDIS_WrkType( dto.getDIS_WrkType() );
        wwccWwcc00000100.setDIS_OTechn( dto.getDIS_OTechn() );
        wwccWwcc00000100.setDIS_CfgFile( dto.getDIS_CfgFile() );
        wwccWwcc00000100.setDIS_PsfFile( dto.getDIS_PsfFile() );
        wwccWwcc00000100.setDIS_SawRef( dto.getDIS_SawRef() );
        wwccWwcc00000100.setDIS_Group( dto.getDIS_Group() );
        wwccWwcc00000100.setWosMessages( dto.getWosMessages() );
        wwccWwcc00000100.setWosState( dto.getWosState() );
        wwccWwcc00000100.setWosStateLastDate( dto.getWosStateLastDate() );
        wwccWwcc00000100.setWosPauseReason( dto.getWosPauseReason() );
        wwccWwcc00000100.setWosOperatorComment( dto.getWosOperatorComment() );
        wwccWwcc00000100.setRecState( dto.getRecState() );
        wwccWwcc00000100.setCrtDate( dto.getCrtDate() );
        wwccWwcc00000100.setLastDate( dto.getLastDate() );
        wwccWwcc00000100.setCrtUser( dto.getCrtUser() );
        wwccWwcc00000100.setLastUser( dto.getLastUser() );
        wwccWwcc00000100.setOwner( dto.getOwner() );
        wwccWwcc00000100.setRecEnt( dto.getRecEnt() );
        wwccWwcc00000100.setRecOU( dto.getRecOU() );
        wwccWwcc00000100.setRecSec( dto.getRecSec() );
        wwccWwcc00000100.setCntID( dto.getCntID() );
        wwccWwcc00000100.setRecID( dto.getRecID() );

        return wwccWwcc00000100;
    }

    @Override
    public WwccWwcc00000100 toEntity(WwccWwcc00000100PageQuery dto) {
        if ( dto == null ) {
            return null;
        }

        WwccWwcc00000100 wwccWwcc00000100 = new WwccWwcc00000100();

        wwccWwcc00000100.setWrkRef( dto.getWrkRef() );
        wwccWwcc00000100.setWrkOEM( dto.getWrkOEM() );
        wwccWwcc00000100.setWrkCNC( dto.getWrkCNC() );
        wwccWwcc00000100.setIsOutsourcing( dto.getIsOutsourcing() );
        wwccWwcc00000100.setWrkCapUCtName( dto.getWrkCapUCtName() );
        wwccWwcc00000100.setWrkCapUntName( dto.getWrkCapUntName() );
        wwccWwcc00000100.setWrkCap( dto.getWrkCap() );
        wwccWwcc00000100.setDescrip( dto.getDescrip() );
        wwccWwcc00000100.setIsActive( dto.getIsActive() );
        wwccWwcc00000100.setWosMessages( dto.getWosMessages() );
        wwccWwcc00000100.setWosState( dto.getWosState() );
        wwccWwcc00000100.setWosStateLastDate( dto.getWosStateLastDate() );
        wwccWwcc00000100.setWosPauseReason( dto.getWosPauseReason() );
        wwccWwcc00000100.setWosOperatorComment( dto.getWosOperatorComment() );
        wwccWwcc00000100.setRecState( dto.getRecState() );
        wwccWwcc00000100.setCrtDate( dto.getCrtDate() );
        wwccWwcc00000100.setLastDate( dto.getLastDate() );
        wwccWwcc00000100.setCrtUser( dto.getCrtUser() );
        wwccWwcc00000100.setLastUser( dto.getLastUser() );
        wwccWwcc00000100.setOwner( dto.getOwner() );
        wwccWwcc00000100.setRecEnt( dto.getRecEnt() );
        wwccWwcc00000100.setRecOU( dto.getRecOU() );
        wwccWwcc00000100.setRecSec( dto.getRecSec() );
        wwccWwcc00000100.setCntID( dto.getCntID() );
        wwccWwcc00000100.setRecID( dto.getRecID() );

        return wwccWwcc00000100;
    }

    @Override
    public void updateFromDTO(WwccWwcc00000100DTO dto, WwccWwcc00000100 entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getWrkRef() != null ) {
            entity.setWrkRef( dto.getWrkRef() );
        }
        if ( dto.getWrkOEM() != null ) {
            entity.setWrkOEM( dto.getWrkOEM() );
        }
        if ( dto.getWrkCNC() != null ) {
            entity.setWrkCNC( dto.getWrkCNC() );
        }
        if ( dto.getIsOutsourcing() != null ) {
            entity.setIsOutsourcing( dto.getIsOutsourcing() );
        }
        if ( dto.getWrkCapUCtName() != null ) {
            entity.setWrkCapUCtName( dto.getWrkCapUCtName() );
        }
        if ( dto.getWrkCapUntName() != null ) {
            entity.setWrkCapUntName( dto.getWrkCapUntName() );
        }
        if ( dto.getWrkCap() != null ) {
            entity.setWrkCap( dto.getWrkCap() );
        }
        if ( dto.getDescrip() != null ) {
            entity.setDescrip( dto.getDescrip() );
        }
        if ( dto.getIsActive() != null ) {
            entity.setIsActive( dto.getIsActive() );
        }
        if ( dto.getDIS_WrkType() != null ) {
            entity.setDIS_WrkType( dto.getDIS_WrkType() );
        }
        if ( dto.getDIS_OTechn() != null ) {
            entity.setDIS_OTechn( dto.getDIS_OTechn() );
        }
        if ( dto.getDIS_CfgFile() != null ) {
            entity.setDIS_CfgFile( dto.getDIS_CfgFile() );
        }
        if ( dto.getDIS_PsfFile() != null ) {
            entity.setDIS_PsfFile( dto.getDIS_PsfFile() );
        }
        if ( dto.getDIS_SawRef() != null ) {
            entity.setDIS_SawRef( dto.getDIS_SawRef() );
        }
        if ( dto.getDIS_Group() != null ) {
            entity.setDIS_Group( dto.getDIS_Group() );
        }
        if ( dto.getWosMessages() != null ) {
            entity.setWosMessages( dto.getWosMessages() );
        }
        if ( dto.getWosState() != null ) {
            entity.setWosState( dto.getWosState() );
        }
        if ( dto.getWosStateLastDate() != null ) {
            entity.setWosStateLastDate( dto.getWosStateLastDate() );
        }
        if ( dto.getWosPauseReason() != null ) {
            entity.setWosPauseReason( dto.getWosPauseReason() );
        }
        if ( dto.getWosOperatorComment() != null ) {
            entity.setWosOperatorComment( dto.getWosOperatorComment() );
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
