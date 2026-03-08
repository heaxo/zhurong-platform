package com.ao.platform.core.lantek.convert;

import com.ao.platform.core.lantek.dto.DisNestNest00000300DTO;
import com.ao.platform.core.lantek.dto.DisNestNest00000300PageQuery;
import com.ao.platform.core.lantek.entity.DisNestNest00000300;
import com.ao.platform.core.lantek.vo.DisNestNest00000300VO;
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
public class DisNestNest00000300ConvertImpl implements DisNestNest00000300Convert {

    @Override
    public DisNestNest00000300VO toVO(DisNestNest00000300 entity) {
        if ( entity == null ) {
            return null;
        }

        DisNestNest00000300VO disNestNest00000300VO = new DisNestNest00000300VO();

        disNestNest00000300VO.setNstRef( entity.getNstRef() );
        disNestNest00000300VO.setShtRef( entity.getShtRef() );
        disNestNest00000300VO.setRIndex( entity.getRIndex() );
        disNestNest00000300VO.setQuantity( entity.getQuantity() );
        disNestNest00000300VO.setVar1( entity.getVar1() );
        disNestNest00000300VO.setVar2( entity.getVar2() );
        disNestNest00000300VO.setVar3( entity.getVar3() );
        disNestNest00000300VO.setVar4( entity.getVar4() );
        disNestNest00000300VO.setVar5( entity.getVar5() );
        disNestNest00000300VO.setRecState( entity.getRecState() );
        disNestNest00000300VO.setCrtDate( entity.getCrtDate() );
        disNestNest00000300VO.setLastDate( entity.getLastDate() );
        disNestNest00000300VO.setCrtUser( entity.getCrtUser() );
        disNestNest00000300VO.setLastUser( entity.getLastUser() );
        disNestNest00000300VO.setOwner( entity.getOwner() );
        disNestNest00000300VO.setRecEnt( entity.getRecEnt() );
        disNestNest00000300VO.setRecOU( entity.getRecOU() );
        disNestNest00000300VO.setRecSec( entity.getRecSec() );
        disNestNest00000300VO.setCntID( entity.getCntID() );
        disNestNest00000300VO.setRecID( entity.getRecID() );

        return disNestNest00000300VO;
    }

    @Override
    public List<DisNestNest00000300VO> toVOList(List<DisNestNest00000300> list) {
        if ( list == null ) {
            return null;
        }

        List<DisNestNest00000300VO> list1 = new ArrayList<DisNestNest00000300VO>( list.size() );
        for ( DisNestNest00000300 disNestNest00000300 : list ) {
            list1.add( toVO( disNestNest00000300 ) );
        }

        return list1;
    }

    @Override
    public DisNestNest00000300 toEntity(DisNestNest00000300DTO dto) {
        if ( dto == null ) {
            return null;
        }

        DisNestNest00000300 disNestNest00000300 = new DisNestNest00000300();

        disNestNest00000300.setNstRef( dto.getNstRef() );
        disNestNest00000300.setShtRef( dto.getShtRef() );
        disNestNest00000300.setRIndex( dto.getRIndex() );
        disNestNest00000300.setQuantity( dto.getQuantity() );
        disNestNest00000300.setVar1( dto.getVar1() );
        disNestNest00000300.setVar2( dto.getVar2() );
        disNestNest00000300.setVar3( dto.getVar3() );
        disNestNest00000300.setVar4( dto.getVar4() );
        disNestNest00000300.setVar5( dto.getVar5() );
        disNestNest00000300.setRecState( dto.getRecState() );
        disNestNest00000300.setCrtDate( dto.getCrtDate() );
        disNestNest00000300.setLastDate( dto.getLastDate() );
        disNestNest00000300.setCrtUser( dto.getCrtUser() );
        disNestNest00000300.setLastUser( dto.getLastUser() );
        disNestNest00000300.setOwner( dto.getOwner() );
        disNestNest00000300.setRecEnt( dto.getRecEnt() );
        disNestNest00000300.setRecOU( dto.getRecOU() );
        disNestNest00000300.setRecSec( dto.getRecSec() );
        disNestNest00000300.setCntID( dto.getCntID() );
        disNestNest00000300.setRecID( dto.getRecID() );

        return disNestNest00000300;
    }

    @Override
    public DisNestNest00000300 toEntity(DisNestNest00000300PageQuery dto) {
        if ( dto == null ) {
            return null;
        }

        DisNestNest00000300 disNestNest00000300 = new DisNestNest00000300();

        disNestNest00000300.setNstRef( dto.getNstRef() );
        disNestNest00000300.setShtRef( dto.getShtRef() );
        disNestNest00000300.setRIndex( dto.getRIndex() );
        disNestNest00000300.setQuantity( dto.getQuantity() );
        disNestNest00000300.setVar1( dto.getVar1() );
        disNestNest00000300.setVar2( dto.getVar2() );
        disNestNest00000300.setVar3( dto.getVar3() );
        disNestNest00000300.setVar4( dto.getVar4() );
        disNestNest00000300.setVar5( dto.getVar5() );
        disNestNest00000300.setRecState( dto.getRecState() );
        disNestNest00000300.setCrtDate( dto.getCrtDate() );
        disNestNest00000300.setLastDate( dto.getLastDate() );
        disNestNest00000300.setCrtUser( dto.getCrtUser() );
        disNestNest00000300.setLastUser( dto.getLastUser() );
        disNestNest00000300.setOwner( dto.getOwner() );
        disNestNest00000300.setRecEnt( dto.getRecEnt() );
        disNestNest00000300.setRecOU( dto.getRecOU() );
        disNestNest00000300.setRecSec( dto.getRecSec() );
        disNestNest00000300.setCntID( dto.getCntID() );
        disNestNest00000300.setRecID( dto.getRecID() );

        return disNestNest00000300;
    }

    @Override
    public void updateFromDTO(DisNestNest00000300DTO dto, DisNestNest00000300 entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getNstRef() != null ) {
            entity.setNstRef( dto.getNstRef() );
        }
        if ( dto.getShtRef() != null ) {
            entity.setShtRef( dto.getShtRef() );
        }
        if ( dto.getRIndex() != null ) {
            entity.setRIndex( dto.getRIndex() );
        }
        if ( dto.getQuantity() != null ) {
            entity.setQuantity( dto.getQuantity() );
        }
        if ( dto.getVar1() != null ) {
            entity.setVar1( dto.getVar1() );
        }
        if ( dto.getVar2() != null ) {
            entity.setVar2( dto.getVar2() );
        }
        if ( dto.getVar3() != null ) {
            entity.setVar3( dto.getVar3() );
        }
        if ( dto.getVar4() != null ) {
            entity.setVar4( dto.getVar4() );
        }
        if ( dto.getVar5() != null ) {
            entity.setVar5( dto.getVar5() );
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
