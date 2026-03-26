package com.zhurong.platform.core.lantek.convert;

import com.zhurong.platform.core.lantek.dto.DisPpttTtrr00000800DTO;
import com.zhurong.platform.core.lantek.dto.DisPpttTtrr00000800PageQuery;
import com.zhurong.platform.core.lantek.entity.DisPpttTtrr00000800;
import com.zhurong.platform.core.lantek.vo.DisPpttTtrr00000800VO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-08T17:13:33+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.10 (Eclipse Adoptium)"
)
@Component
public class DisPpttTtrr00000800ConvertImpl implements DisPpttTtrr00000800Convert {

    @Override
    public DisPpttTtrr00000800VO toVO(DisPpttTtrr00000800 entity) {
        if ( entity == null ) {
            return null;
        }

        DisPpttTtrr00000800VO disPpttTtrr00000800VO = new DisPpttTtrr00000800VO();

        disPpttTtrr00000800VO.setTurrRef( entity.getTurrRef() );
        disPpttTtrr00000800VO.setTPosition( entity.getTPosition() );
        disPpttTtrr00000800VO.setTNumber( entity.getTNumber() );
        disPpttTtrr00000800VO.setMltRef( entity.getMltRef() );
        disPpttTtrr00000800VO.setStTRef( entity.getStTRef() );
        disPpttTtrr00000800VO.setAutoindex( entity.getAutoindex() );
        disPpttTtrr00000800VO.setDMin( entity.getDMin() );
        disPpttTtrr00000800VO.setDMax( entity.getDMax() );
        disPpttTtrr00000800VO.setZClamp( entity.getZClamp() );
        disPpttTtrr00000800VO.setZLeft( entity.getZLeft() );
        disPpttTtrr00000800VO.setZRight( entity.getZRight() );
        disPpttTtrr00000800VO.setZTop( entity.getZTop() );
        disPpttTtrr00000800VO.setOffsetXMin( entity.getOffsetXMin() );
        disPpttTtrr00000800VO.setOffsetXMax( entity.getOffsetXMax() );
        disPpttTtrr00000800VO.setOffsetYMin( entity.getOffsetYMin() );
        disPpttTtrr00000800VO.setOffsetYMax( entity.getOffsetYMax() );
        disPpttTtrr00000800VO.setPlane( entity.getPlane() );
        disPpttTtrr00000800VO.setRecState( entity.getRecState() );
        disPpttTtrr00000800VO.setCrtDate( entity.getCrtDate() );
        disPpttTtrr00000800VO.setLastDate( entity.getLastDate() );
        disPpttTtrr00000800VO.setCrtUser( entity.getCrtUser() );
        disPpttTtrr00000800VO.setLastUser( entity.getLastUser() );
        disPpttTtrr00000800VO.setOwner( entity.getOwner() );
        disPpttTtrr00000800VO.setRecEnt( entity.getRecEnt() );
        disPpttTtrr00000800VO.setRecOU( entity.getRecOU() );
        disPpttTtrr00000800VO.setRecSec( entity.getRecSec() );
        disPpttTtrr00000800VO.setCntID( entity.getCntID() );
        disPpttTtrr00000800VO.setRecID( entity.getRecID() );

        return disPpttTtrr00000800VO;
    }

    @Override
    public List<DisPpttTtrr00000800VO> toVOList(List<DisPpttTtrr00000800> list) {
        if ( list == null ) {
            return null;
        }

        List<DisPpttTtrr00000800VO> list1 = new ArrayList<DisPpttTtrr00000800VO>( list.size() );
        for ( DisPpttTtrr00000800 disPpttTtrr00000800 : list ) {
            list1.add( toVO( disPpttTtrr00000800 ) );
        }

        return list1;
    }

    @Override
    public DisPpttTtrr00000800 toEntity(DisPpttTtrr00000800DTO dto) {
        if ( dto == null ) {
            return null;
        }

        DisPpttTtrr00000800 disPpttTtrr00000800 = new DisPpttTtrr00000800();

        disPpttTtrr00000800.setTurrRef( dto.getTurrRef() );
        disPpttTtrr00000800.setTPosition( dto.getTPosition() );
        disPpttTtrr00000800.setTNumber( dto.getTNumber() );
        disPpttTtrr00000800.setMltRef( dto.getMltRef() );
        disPpttTtrr00000800.setStTRef( dto.getStTRef() );
        disPpttTtrr00000800.setAutoindex( dto.getAutoindex() );
        disPpttTtrr00000800.setDMin( dto.getDMin() );
        disPpttTtrr00000800.setDMax( dto.getDMax() );
        disPpttTtrr00000800.setZClamp( dto.getZClamp() );
        disPpttTtrr00000800.setZLeft( dto.getZLeft() );
        disPpttTtrr00000800.setZRight( dto.getZRight() );
        disPpttTtrr00000800.setZTop( dto.getZTop() );
        disPpttTtrr00000800.setOffsetXMin( dto.getOffsetXMin() );
        disPpttTtrr00000800.setOffsetXMax( dto.getOffsetXMax() );
        disPpttTtrr00000800.setOffsetYMin( dto.getOffsetYMin() );
        disPpttTtrr00000800.setOffsetYMax( dto.getOffsetYMax() );
        disPpttTtrr00000800.setPlane( dto.getPlane() );
        disPpttTtrr00000800.setRecState( dto.getRecState() );
        disPpttTtrr00000800.setCrtDate( dto.getCrtDate() );
        disPpttTtrr00000800.setLastDate( dto.getLastDate() );
        disPpttTtrr00000800.setCrtUser( dto.getCrtUser() );
        disPpttTtrr00000800.setLastUser( dto.getLastUser() );
        disPpttTtrr00000800.setOwner( dto.getOwner() );
        disPpttTtrr00000800.setRecEnt( dto.getRecEnt() );
        disPpttTtrr00000800.setRecOU( dto.getRecOU() );
        disPpttTtrr00000800.setRecSec( dto.getRecSec() );
        disPpttTtrr00000800.setCntID( dto.getCntID() );
        disPpttTtrr00000800.setRecID( dto.getRecID() );

        return disPpttTtrr00000800;
    }

    @Override
    public DisPpttTtrr00000800 toEntity(DisPpttTtrr00000800PageQuery dto) {
        if ( dto == null ) {
            return null;
        }

        DisPpttTtrr00000800 disPpttTtrr00000800 = new DisPpttTtrr00000800();

        disPpttTtrr00000800.setTurrRef( dto.getTurrRef() );
        disPpttTtrr00000800.setTPosition( dto.getTPosition() );
        disPpttTtrr00000800.setTNumber( dto.getTNumber() );
        disPpttTtrr00000800.setMltRef( dto.getMltRef() );
        disPpttTtrr00000800.setStTRef( dto.getStTRef() );
        disPpttTtrr00000800.setAutoindex( dto.getAutoindex() );
        disPpttTtrr00000800.setDMin( dto.getDMin() );
        disPpttTtrr00000800.setDMax( dto.getDMax() );
        disPpttTtrr00000800.setZClamp( dto.getZClamp() );
        disPpttTtrr00000800.setZLeft( dto.getZLeft() );
        disPpttTtrr00000800.setZRight( dto.getZRight() );
        disPpttTtrr00000800.setZTop( dto.getZTop() );
        disPpttTtrr00000800.setOffsetXMin( dto.getOffsetXMin() );
        disPpttTtrr00000800.setOffsetXMax( dto.getOffsetXMax() );
        disPpttTtrr00000800.setOffsetYMin( dto.getOffsetYMin() );
        disPpttTtrr00000800.setOffsetYMax( dto.getOffsetYMax() );
        disPpttTtrr00000800.setPlane( dto.getPlane() );
        disPpttTtrr00000800.setRecState( dto.getRecState() );
        disPpttTtrr00000800.setCrtDate( dto.getCrtDate() );
        disPpttTtrr00000800.setLastDate( dto.getLastDate() );
        disPpttTtrr00000800.setCrtUser( dto.getCrtUser() );
        disPpttTtrr00000800.setLastUser( dto.getLastUser() );
        disPpttTtrr00000800.setOwner( dto.getOwner() );
        disPpttTtrr00000800.setRecEnt( dto.getRecEnt() );
        disPpttTtrr00000800.setRecOU( dto.getRecOU() );
        disPpttTtrr00000800.setRecSec( dto.getRecSec() );
        disPpttTtrr00000800.setCntID( dto.getCntID() );
        disPpttTtrr00000800.setRecID( dto.getRecID() );

        return disPpttTtrr00000800;
    }

    @Override
    public void updateFromDTO(DisPpttTtrr00000800DTO dto, DisPpttTtrr00000800 entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getTurrRef() != null ) {
            entity.setTurrRef( dto.getTurrRef() );
        }
        if ( dto.getTPosition() != null ) {
            entity.setTPosition( dto.getTPosition() );
        }
        if ( dto.getTNumber() != null ) {
            entity.setTNumber( dto.getTNumber() );
        }
        if ( dto.getMltRef() != null ) {
            entity.setMltRef( dto.getMltRef() );
        }
        if ( dto.getStTRef() != null ) {
            entity.setStTRef( dto.getStTRef() );
        }
        if ( dto.getAutoindex() != null ) {
            entity.setAutoindex( dto.getAutoindex() );
        }
        if ( dto.getDMin() != null ) {
            entity.setDMin( dto.getDMin() );
        }
        if ( dto.getDMax() != null ) {
            entity.setDMax( dto.getDMax() );
        }
        if ( dto.getZClamp() != null ) {
            entity.setZClamp( dto.getZClamp() );
        }
        if ( dto.getZLeft() != null ) {
            entity.setZLeft( dto.getZLeft() );
        }
        if ( dto.getZRight() != null ) {
            entity.setZRight( dto.getZRight() );
        }
        if ( dto.getZTop() != null ) {
            entity.setZTop( dto.getZTop() );
        }
        if ( dto.getOffsetXMin() != null ) {
            entity.setOffsetXMin( dto.getOffsetXMin() );
        }
        if ( dto.getOffsetXMax() != null ) {
            entity.setOffsetXMax( dto.getOffsetXMax() );
        }
        if ( dto.getOffsetYMin() != null ) {
            entity.setOffsetYMin( dto.getOffsetYMin() );
        }
        if ( dto.getOffsetYMax() != null ) {
            entity.setOffsetYMax( dto.getOffsetYMax() );
        }
        if ( dto.getPlane() != null ) {
            entity.setPlane( dto.getPlane() );
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
