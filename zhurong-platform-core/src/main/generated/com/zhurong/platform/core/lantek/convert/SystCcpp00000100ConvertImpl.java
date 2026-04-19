package com.zhurong.platform.core.lantek.convert;

import com.zhurong.platform.core.lantek.dto.SystCcpp00000100DTO;
import com.zhurong.platform.core.lantek.dto.SystCcpp00000100PageQuery;
import com.zhurong.platform.core.lantek.entity.SystCcpp00000100;
import com.zhurong.platform.core.lantek.vo.SystCcpp00000100VO;
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
public class SystCcpp00000100ConvertImpl implements SystCcpp00000100Convert {

    @Override
    public SystCcpp00000100VO toVO(SystCcpp00000100 entity) {
        if ( entity == null ) {
            return null;
        }

        SystCcpp00000100VO systCcpp00000100VO = new SystCcpp00000100VO();

        systCcpp00000100VO.setEType( entity.getEType() );
        systCcpp00000100VO.setEName( entity.getEName() );
        systCcpp00000100VO.setParName( entity.getParName() );
        systCcpp00000100VO.setParValue( entity.getParValue() );
        systCcpp00000100VO.setRecState( entity.getRecState() );
        systCcpp00000100VO.setCrtDate( entity.getCrtDate() );
        systCcpp00000100VO.setLastDate( entity.getLastDate() );
        systCcpp00000100VO.setCrtUser( entity.getCrtUser() );
        systCcpp00000100VO.setLastUser( entity.getLastUser() );
        systCcpp00000100VO.setOwner( entity.getOwner() );
        systCcpp00000100VO.setRecEnt( entity.getRecEnt() );
        systCcpp00000100VO.setRecOU( entity.getRecOU() );
        systCcpp00000100VO.setRecSec( entity.getRecSec() );
        systCcpp00000100VO.setCntID( entity.getCntID() );
        systCcpp00000100VO.setRecID( entity.getRecID() );

        return systCcpp00000100VO;
    }

    @Override
    public List<SystCcpp00000100VO> toVOList(List<SystCcpp00000100> list) {
        if ( list == null ) {
            return null;
        }

        List<SystCcpp00000100VO> list1 = new ArrayList<SystCcpp00000100VO>( list.size() );
        for ( SystCcpp00000100 systCcpp00000100 : list ) {
            list1.add( toVO( systCcpp00000100 ) );
        }

        return list1;
    }

    @Override
    public SystCcpp00000100 toEntity(SystCcpp00000100DTO dto) {
        if ( dto == null ) {
            return null;
        }

        SystCcpp00000100 systCcpp00000100 = new SystCcpp00000100();

        systCcpp00000100.setEType( dto.getEType() );
        systCcpp00000100.setEName( dto.getEName() );
        systCcpp00000100.setParName( dto.getParName() );
        systCcpp00000100.setParValue( dto.getParValue() );
        systCcpp00000100.setRecState( dto.getRecState() );
        systCcpp00000100.setCrtDate( dto.getCrtDate() );
        systCcpp00000100.setLastDate( dto.getLastDate() );
        systCcpp00000100.setCrtUser( dto.getCrtUser() );
        systCcpp00000100.setLastUser( dto.getLastUser() );
        systCcpp00000100.setOwner( dto.getOwner() );
        systCcpp00000100.setRecEnt( dto.getRecEnt() );
        systCcpp00000100.setRecOU( dto.getRecOU() );
        systCcpp00000100.setRecSec( dto.getRecSec() );
        systCcpp00000100.setCntID( dto.getCntID() );
        systCcpp00000100.setRecID( dto.getRecID() );

        return systCcpp00000100;
    }

    @Override
    public SystCcpp00000100 toEntity(SystCcpp00000100PageQuery dto) {
        if ( dto == null ) {
            return null;
        }

        SystCcpp00000100 systCcpp00000100 = new SystCcpp00000100();

        systCcpp00000100.setEType( dto.getEType() );
        systCcpp00000100.setEName( dto.getEName() );
        systCcpp00000100.setParName( dto.getParName() );
        systCcpp00000100.setParValue( dto.getParValue() );
        systCcpp00000100.setRecState( dto.getRecState() );
        systCcpp00000100.setCrtDate( dto.getCrtDate() );
        systCcpp00000100.setLastDate( dto.getLastDate() );
        systCcpp00000100.setCrtUser( dto.getCrtUser() );
        systCcpp00000100.setLastUser( dto.getLastUser() );
        systCcpp00000100.setOwner( dto.getOwner() );
        systCcpp00000100.setRecEnt( dto.getRecEnt() );
        systCcpp00000100.setRecOU( dto.getRecOU() );
        systCcpp00000100.setRecSec( dto.getRecSec() );
        systCcpp00000100.setCntID( dto.getCntID() );
        systCcpp00000100.setRecID( dto.getRecID() );

        return systCcpp00000100;
    }

    @Override
    public void updateFromDTO(SystCcpp00000100DTO dto, SystCcpp00000100 entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getEType() != null ) {
            entity.setEType( dto.getEType() );
        }
        if ( dto.getEName() != null ) {
            entity.setEName( dto.getEName() );
        }
        if ( dto.getParName() != null ) {
            entity.setParName( dto.getParName() );
        }
        if ( dto.getParValue() != null ) {
            entity.setParValue( dto.getParValue() );
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
