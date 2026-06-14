package com.zhurong.platform.custom.convert;

import com.zhurong.platform.core.lantek.dto.DisNestNest00000500DTO;
import com.zhurong.platform.core.lantek.dto.DisNestNest00000500PageQuery;
import com.zhurong.platform.core.lantek.vo.DisNestNest00000500VO;
import com.zhurong.platform.custom.entity.DisNestNest00000500;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-06-14T12:06:48+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.10 (Eclipse Adoptium)"
)
@Component
public class DisNestNest00000500ConvertImpl implements DisNestNest00000500Convert {

    @Override
    public DisNestNest00000500VO toVO(DisNestNest00000500 entity) {
        if ( entity == null ) {
            return null;
        }

        DisNestNest00000500VO disNestNest00000500VO = new DisNestNest00000500VO();

        disNestNest00000500VO.setNstRef( entity.getNstRef() );
        disNestNest00000500VO.setMnORef( entity.getMnORef() );
        disNestNest00000500VO.setOprID( entity.getOprID() );
        disNestNest00000500VO.setPrdRefDst( entity.getPrdRefDst() );
        disNestNest00000500VO.setPIndex( entity.getPIndex() );
        disNestNest00000500VO.setQuantity( entity.getQuantity() );
        disNestNest00000500VO.setMQ( entity.getMQ() );
        disNestNest00000500VO.setCostMat( entity.getCostMat() );
        disNestNest00000500VO.setCostMachTime( entity.getCostMachTime() );
        disNestNest00000500VO.setCostConsum( entity.getCostConsum() );
        disNestNest00000500VO.setRecState( entity.getRecState() );
        disNestNest00000500VO.setCrtDate( entity.getCrtDate() );
        disNestNest00000500VO.setLastDate( entity.getLastDate() );
        disNestNest00000500VO.setCrtUser( entity.getCrtUser() );
        disNestNest00000500VO.setLastUser( entity.getLastUser() );
        disNestNest00000500VO.setOwner( entity.getOwner() );
        disNestNest00000500VO.setRecEnt( entity.getRecEnt() );
        disNestNest00000500VO.setRecOU( entity.getRecOU() );
        disNestNest00000500VO.setRecSec( entity.getRecSec() );
        disNestNest00000500VO.setCntID( entity.getCntID() );
        disNestNest00000500VO.setRecID( entity.getRecID() );

        return disNestNest00000500VO;
    }

    @Override
    public List<DisNestNest00000500VO> toVOList(List<DisNestNest00000500> list) {
        if ( list == null ) {
            return null;
        }

        List<DisNestNest00000500VO> list1 = new ArrayList<DisNestNest00000500VO>( list.size() );
        for ( DisNestNest00000500 disNestNest00000500 : list ) {
            list1.add( toVO( disNestNest00000500 ) );
        }

        return list1;
    }

    @Override
    public DisNestNest00000500 toEntity(DisNestNest00000500DTO dto) {
        if ( dto == null ) {
            return null;
        }

        DisNestNest00000500 disNestNest00000500 = new DisNestNest00000500();

        disNestNest00000500.setNstRef( dto.getNstRef() );
        disNestNest00000500.setMnORef( dto.getMnORef() );
        disNestNest00000500.setOprID( dto.getOprID() );
        disNestNest00000500.setPrdRefDst( dto.getPrdRefDst() );
        disNestNest00000500.setPIndex( dto.getPIndex() );
        disNestNest00000500.setQuantity( dto.getQuantity() );
        disNestNest00000500.setMQ( dto.getMQ() );
        disNestNest00000500.setCostMat( dto.getCostMat() );
        disNestNest00000500.setCostMachTime( dto.getCostMachTime() );
        disNestNest00000500.setCostConsum( dto.getCostConsum() );
        disNestNest00000500.setRecState( dto.getRecState() );
        disNestNest00000500.setCrtDate( dto.getCrtDate() );
        disNestNest00000500.setLastDate( dto.getLastDate() );
        disNestNest00000500.setCrtUser( dto.getCrtUser() );
        disNestNest00000500.setLastUser( dto.getLastUser() );
        disNestNest00000500.setOwner( dto.getOwner() );
        disNestNest00000500.setRecEnt( dto.getRecEnt() );
        disNestNest00000500.setRecOU( dto.getRecOU() );
        disNestNest00000500.setRecSec( dto.getRecSec() );
        disNestNest00000500.setCntID( dto.getCntID() );
        disNestNest00000500.setRecID( dto.getRecID() );

        return disNestNest00000500;
    }

    @Override
    public DisNestNest00000500 toEntity(DisNestNest00000500PageQuery dto) {
        if ( dto == null ) {
            return null;
        }

        DisNestNest00000500 disNestNest00000500 = new DisNestNest00000500();

        disNestNest00000500.setNstRef( dto.getNstRef() );
        disNestNest00000500.setMnORef( dto.getMnORef() );
        disNestNest00000500.setOprID( dto.getOprID() );
        disNestNest00000500.setPrdRefDst( dto.getPrdRefDst() );
        disNestNest00000500.setPIndex( dto.getPIndex() );
        disNestNest00000500.setQuantity( dto.getQuantity() );
        disNestNest00000500.setCostMat( dto.getCostMat() );
        disNestNest00000500.setCostMachTime( dto.getCostMachTime() );
        disNestNest00000500.setCostConsum( dto.getCostConsum() );
        disNestNest00000500.setRecState( dto.getRecState() );
        disNestNest00000500.setCrtDate( dto.getCrtDate() );
        disNestNest00000500.setLastDate( dto.getLastDate() );
        disNestNest00000500.setCrtUser( dto.getCrtUser() );
        disNestNest00000500.setLastUser( dto.getLastUser() );
        disNestNest00000500.setOwner( dto.getOwner() );
        disNestNest00000500.setRecEnt( dto.getRecEnt() );
        disNestNest00000500.setRecOU( dto.getRecOU() );
        disNestNest00000500.setRecSec( dto.getRecSec() );
        disNestNest00000500.setCntID( dto.getCntID() );
        disNestNest00000500.setRecID( dto.getRecID() );

        return disNestNest00000500;
    }

    @Override
    public void updateFromDTO(DisNestNest00000500DTO dto, DisNestNest00000500 entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getNstRef() != null ) {
            entity.setNstRef( dto.getNstRef() );
        }
        if ( dto.getMnORef() != null ) {
            entity.setMnORef( dto.getMnORef() );
        }
        if ( dto.getOprID() != null ) {
            entity.setOprID( dto.getOprID() );
        }
        if ( dto.getPrdRefDst() != null ) {
            entity.setPrdRefDst( dto.getPrdRefDst() );
        }
        if ( dto.getPIndex() != null ) {
            entity.setPIndex( dto.getPIndex() );
        }
        if ( dto.getQuantity() != null ) {
            entity.setQuantity( dto.getQuantity() );
        }
        if ( dto.getMQ() != null ) {
            entity.setMQ( dto.getMQ() );
        }
        if ( dto.getCostMat() != null ) {
            entity.setCostMat( dto.getCostMat() );
        }
        if ( dto.getCostMachTime() != null ) {
            entity.setCostMachTime( dto.getCostMachTime() );
        }
        if ( dto.getCostConsum() != null ) {
            entity.setCostConsum( dto.getCostConsum() );
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
