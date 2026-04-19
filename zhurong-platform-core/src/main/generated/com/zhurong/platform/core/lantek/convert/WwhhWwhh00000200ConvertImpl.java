package com.zhurong.platform.core.lantek.convert;

import com.zhurong.platform.core.lantek.dto.WwhhWwhh00000200DTO;
import com.zhurong.platform.core.lantek.dto.WwhhWwhh00000200PageQuery;
import com.zhurong.platform.core.lantek.entity.WwhhWwhh00000200;
import com.zhurong.platform.core.lantek.vo.WwhhWwhh00000200VO;
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
public class WwhhWwhh00000200ConvertImpl implements WwhhWwhh00000200Convert {

    @Override
    public WwhhWwhh00000200VO toVO(WwhhWwhh00000200 entity) {
        if ( entity == null ) {
            return null;
        }

        WwhhWwhh00000200VO wwhhWwhh00000200VO = new WwhhWwhh00000200VO();

        wwhhWwhh00000200VO.setWrhRef( entity.getWrhRef() );
        wwhhWwhh00000200VO.setLocRef( entity.getLocRef() );
        wwhhWwhh00000200VO.setLOrder( entity.getLOrder() );
        wwhhWwhh00000200VO.setLocName( entity.getLocName() );
        wwhhWwhh00000200VO.setLocParent( entity.getLocParent() );
        wwhhWwhh00000200VO.setIsEnd( entity.getIsEnd() );
        wwhhWwhh00000200VO.setWidth( entity.getWidth() );
        wwhhWwhh00000200VO.setHeight( entity.getHeight() );
        wwhhWwhh00000200VO.setLength( entity.getLength() );
        wwhhWwhh00000200VO.setWeight( entity.getWeight() );
        wwhhWwhh00000200VO.setDIS_IsWorkCenter( entity.getDIS_IsWorkCenter() );
        wwhhWwhh00000200VO.setRecState( entity.getRecState() );
        wwhhWwhh00000200VO.setCrtDate( entity.getCrtDate() );
        wwhhWwhh00000200VO.setLastDate( entity.getLastDate() );
        wwhhWwhh00000200VO.setCrtUser( entity.getCrtUser() );
        wwhhWwhh00000200VO.setLastUser( entity.getLastUser() );
        wwhhWwhh00000200VO.setOwner( entity.getOwner() );
        wwhhWwhh00000200VO.setRecEnt( entity.getRecEnt() );
        wwhhWwhh00000200VO.setRecOU( entity.getRecOU() );
        wwhhWwhh00000200VO.setRecSec( entity.getRecSec() );
        wwhhWwhh00000200VO.setCntID( entity.getCntID() );
        wwhhWwhh00000200VO.setRecID( entity.getRecID() );

        return wwhhWwhh00000200VO;
    }

    @Override
    public List<WwhhWwhh00000200VO> toVOList(List<WwhhWwhh00000200> list) {
        if ( list == null ) {
            return null;
        }

        List<WwhhWwhh00000200VO> list1 = new ArrayList<WwhhWwhh00000200VO>( list.size() );
        for ( WwhhWwhh00000200 wwhhWwhh00000200 : list ) {
            list1.add( toVO( wwhhWwhh00000200 ) );
        }

        return list1;
    }

    @Override
    public WwhhWwhh00000200 toEntity(WwhhWwhh00000200DTO dto) {
        if ( dto == null ) {
            return null;
        }

        WwhhWwhh00000200 wwhhWwhh00000200 = new WwhhWwhh00000200();

        wwhhWwhh00000200.setWrhRef( dto.getWrhRef() );
        wwhhWwhh00000200.setLocRef( dto.getLocRef() );
        wwhhWwhh00000200.setLOrder( dto.getLOrder() );
        wwhhWwhh00000200.setLocName( dto.getLocName() );
        wwhhWwhh00000200.setLocParent( dto.getLocParent() );
        wwhhWwhh00000200.setIsEnd( dto.getIsEnd() );
        wwhhWwhh00000200.setWidth( dto.getWidth() );
        wwhhWwhh00000200.setHeight( dto.getHeight() );
        wwhhWwhh00000200.setLength( dto.getLength() );
        wwhhWwhh00000200.setWeight( dto.getWeight() );
        wwhhWwhh00000200.setDIS_IsWorkCenter( dto.getDIS_IsWorkCenter() );
        wwhhWwhh00000200.setRecState( dto.getRecState() );
        wwhhWwhh00000200.setCrtDate( dto.getCrtDate() );
        wwhhWwhh00000200.setLastDate( dto.getLastDate() );
        wwhhWwhh00000200.setCrtUser( dto.getCrtUser() );
        wwhhWwhh00000200.setLastUser( dto.getLastUser() );
        wwhhWwhh00000200.setOwner( dto.getOwner() );
        wwhhWwhh00000200.setRecEnt( dto.getRecEnt() );
        wwhhWwhh00000200.setRecOU( dto.getRecOU() );
        wwhhWwhh00000200.setRecSec( dto.getRecSec() );
        wwhhWwhh00000200.setCntID( dto.getCntID() );
        wwhhWwhh00000200.setRecID( dto.getRecID() );

        return wwhhWwhh00000200;
    }

    @Override
    public WwhhWwhh00000200 toEntity(WwhhWwhh00000200PageQuery dto) {
        if ( dto == null ) {
            return null;
        }

        WwhhWwhh00000200 wwhhWwhh00000200 = new WwhhWwhh00000200();

        wwhhWwhh00000200.setWrhRef( dto.getWrhRef() );
        wwhhWwhh00000200.setLocRef( dto.getLocRef() );
        wwhhWwhh00000200.setLOrder( dto.getLOrder() );
        wwhhWwhh00000200.setLocName( dto.getLocName() );
        wwhhWwhh00000200.setLocParent( dto.getLocParent() );
        wwhhWwhh00000200.setIsEnd( dto.getIsEnd() );
        wwhhWwhh00000200.setWidth( dto.getWidth() );
        wwhhWwhh00000200.setHeight( dto.getHeight() );
        wwhhWwhh00000200.setLength( dto.getLength() );
        wwhhWwhh00000200.setWeight( dto.getWeight() );
        wwhhWwhh00000200.setRecState( dto.getRecState() );
        wwhhWwhh00000200.setCrtDate( dto.getCrtDate() );
        wwhhWwhh00000200.setLastDate( dto.getLastDate() );
        wwhhWwhh00000200.setCrtUser( dto.getCrtUser() );
        wwhhWwhh00000200.setLastUser( dto.getLastUser() );
        wwhhWwhh00000200.setOwner( dto.getOwner() );
        wwhhWwhh00000200.setRecEnt( dto.getRecEnt() );
        wwhhWwhh00000200.setRecOU( dto.getRecOU() );
        wwhhWwhh00000200.setRecSec( dto.getRecSec() );
        wwhhWwhh00000200.setCntID( dto.getCntID() );
        wwhhWwhh00000200.setRecID( dto.getRecID() );

        return wwhhWwhh00000200;
    }

    @Override
    public void updateFromDTO(WwhhWwhh00000200DTO dto, WwhhWwhh00000200 entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getWrhRef() != null ) {
            entity.setWrhRef( dto.getWrhRef() );
        }
        if ( dto.getLocRef() != null ) {
            entity.setLocRef( dto.getLocRef() );
        }
        if ( dto.getLOrder() != null ) {
            entity.setLOrder( dto.getLOrder() );
        }
        if ( dto.getLocName() != null ) {
            entity.setLocName( dto.getLocName() );
        }
        if ( dto.getLocParent() != null ) {
            entity.setLocParent( dto.getLocParent() );
        }
        if ( dto.getIsEnd() != null ) {
            entity.setIsEnd( dto.getIsEnd() );
        }
        if ( dto.getWidth() != null ) {
            entity.setWidth( dto.getWidth() );
        }
        if ( dto.getHeight() != null ) {
            entity.setHeight( dto.getHeight() );
        }
        if ( dto.getLength() != null ) {
            entity.setLength( dto.getLength() );
        }
        if ( dto.getWeight() != null ) {
            entity.setWeight( dto.getWeight() );
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
