package com.zhurong.platform.core.lantek.convert;

import com.zhurong.platform.core.lantek.dto.SystOwnd00000100DTO;
import com.zhurong.platform.core.lantek.dto.SystOwnd00000100PageQuery;
import com.zhurong.platform.core.lantek.entity.SystOwnd00000100;
import com.zhurong.platform.core.lantek.vo.SystOwnd00000100VO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-04-04T11:40:49+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.10 (Eclipse Adoptium)"
)
@Component
public class SystOwnd00000100ConvertImpl implements SystOwnd00000100Convert {

    @Override
    public SystOwnd00000100VO toVO(SystOwnd00000100 entity) {
        if ( entity == null ) {
            return null;
        }

        SystOwnd00000100VO systOwnd00000100VO = new SystOwnd00000100VO();

        systOwnd00000100VO.setDocDsc( entity.getDocDsc() );
        systOwnd00000100VO.setTblRef( entity.getTblRef() );
        systOwnd00000100VO.setRecordID( entity.getRecordID() );
        systOwnd00000100VO.setSType( entity.getSType() );
        systOwnd00000100VO.setFFType( entity.getFFType() );
        systOwnd00000100VO.setFFName( entity.getFFName() );
        systOwnd00000100VO.setFFDate( entity.getFFDate() );
        systOwnd00000100VO.setFFSize( entity.getFFSize() );
        systOwnd00000100VO.setFFVault( entity.getFFVault() );
        systOwnd00000100VO.setRecState( entity.getRecState() );
        systOwnd00000100VO.setCrtDate( entity.getCrtDate() );
        systOwnd00000100VO.setLastDate( entity.getLastDate() );
        systOwnd00000100VO.setCrtUser( entity.getCrtUser() );
        systOwnd00000100VO.setLastUser( entity.getLastUser() );
        systOwnd00000100VO.setOwner( entity.getOwner() );
        systOwnd00000100VO.setRecEnt( entity.getRecEnt() );
        systOwnd00000100VO.setRecOU( entity.getRecOU() );
        systOwnd00000100VO.setRecSec( entity.getRecSec() );
        systOwnd00000100VO.setCntID( entity.getCntID() );
        systOwnd00000100VO.setRecID( entity.getRecID() );

        return systOwnd00000100VO;
    }

    @Override
    public List<SystOwnd00000100VO> toVOList(List<SystOwnd00000100> list) {
        if ( list == null ) {
            return null;
        }

        List<SystOwnd00000100VO> list1 = new ArrayList<SystOwnd00000100VO>( list.size() );
        for ( SystOwnd00000100 systOwnd00000100 : list ) {
            list1.add( toVO( systOwnd00000100 ) );
        }

        return list1;
    }

    @Override
    public SystOwnd00000100 toEntity(SystOwnd00000100DTO dto) {
        if ( dto == null ) {
            return null;
        }

        SystOwnd00000100 systOwnd00000100 = new SystOwnd00000100();

        systOwnd00000100.setDocDsc( dto.getDocDsc() );
        systOwnd00000100.setTblRef( dto.getTblRef() );
        systOwnd00000100.setRecordID( dto.getRecordID() );
        systOwnd00000100.setSType( dto.getSType() );
        systOwnd00000100.setFFType( dto.getFFType() );
        systOwnd00000100.setFFName( dto.getFFName() );
        systOwnd00000100.setFFDate( dto.getFFDate() );
        systOwnd00000100.setFFSize( dto.getFFSize() );
        systOwnd00000100.setFFVault( dto.getFFVault() );
        systOwnd00000100.setRecState( dto.getRecState() );
        systOwnd00000100.setCrtDate( dto.getCrtDate() );
        systOwnd00000100.setLastDate( dto.getLastDate() );
        systOwnd00000100.setCrtUser( dto.getCrtUser() );
        systOwnd00000100.setLastUser( dto.getLastUser() );
        systOwnd00000100.setOwner( dto.getOwner() );
        systOwnd00000100.setRecEnt( dto.getRecEnt() );
        systOwnd00000100.setRecOU( dto.getRecOU() );
        systOwnd00000100.setRecSec( dto.getRecSec() );
        systOwnd00000100.setCntID( dto.getCntID() );
        systOwnd00000100.setRecID( dto.getRecID() );

        return systOwnd00000100;
    }

    @Override
    public SystOwnd00000100 toEntity(SystOwnd00000100PageQuery dto) {
        if ( dto == null ) {
            return null;
        }

        SystOwnd00000100 systOwnd00000100 = new SystOwnd00000100();

        systOwnd00000100.setDocDsc( dto.getDocDsc() );
        systOwnd00000100.setTblRef( dto.getTblRef() );
        systOwnd00000100.setRecordID( dto.getRecordID() );
        systOwnd00000100.setSType( dto.getSType() );
        systOwnd00000100.setFFType( dto.getFFType() );
        systOwnd00000100.setFFName( dto.getFFName() );
        systOwnd00000100.setFFDate( dto.getFFDate() );
        systOwnd00000100.setFFSize( dto.getFFSize() );
        systOwnd00000100.setFFVault( dto.getFFVault() );
        systOwnd00000100.setRecState( dto.getRecState() );
        systOwnd00000100.setCrtDate( dto.getCrtDate() );
        systOwnd00000100.setLastDate( dto.getLastDate() );
        systOwnd00000100.setCrtUser( dto.getCrtUser() );
        systOwnd00000100.setLastUser( dto.getLastUser() );
        systOwnd00000100.setOwner( dto.getOwner() );
        systOwnd00000100.setRecEnt( dto.getRecEnt() );
        systOwnd00000100.setRecOU( dto.getRecOU() );
        systOwnd00000100.setRecSec( dto.getRecSec() );
        systOwnd00000100.setCntID( dto.getCntID() );
        systOwnd00000100.setRecID( dto.getRecID() );

        return systOwnd00000100;
    }

    @Override
    public void updateFromDTO(SystOwnd00000100DTO dto, SystOwnd00000100 entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getDocDsc() != null ) {
            entity.setDocDsc( dto.getDocDsc() );
        }
        if ( dto.getTblRef() != null ) {
            entity.setTblRef( dto.getTblRef() );
        }
        if ( dto.getRecordID() != null ) {
            entity.setRecordID( dto.getRecordID() );
        }
        if ( dto.getSType() != null ) {
            entity.setSType( dto.getSType() );
        }
        if ( dto.getFFType() != null ) {
            entity.setFFType( dto.getFFType() );
        }
        if ( dto.getFFName() != null ) {
            entity.setFFName( dto.getFFName() );
        }
        if ( dto.getFFDate() != null ) {
            entity.setFFDate( dto.getFFDate() );
        }
        if ( dto.getFFSize() != null ) {
            entity.setFFSize( dto.getFFSize() );
        }
        if ( dto.getFFVault() != null ) {
            entity.setFFVault( dto.getFFVault() );
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
