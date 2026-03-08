package com.ao.platform.core.lantek.convert;

import com.ao.platform.core.lantek.dto.DisMmnnMmoo00000200DTO;
import com.ao.platform.core.lantek.dto.DisMmnnMmoo00000200PageQuery;
import com.ao.platform.core.lantek.entity.DisMmnnMmoo00000200;
import com.ao.platform.core.lantek.vo.DisMmnnMmoo00000200VO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-06T15:33:07+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.10 (Eclipse Adoptium)"
)
@Component
public class DisMmnnMmoo00000200ConvertImpl implements DisMmnnMmoo00000200Convert {

    @Override
    public DisMmnnMmoo00000200VO toVO(DisMmnnMmoo00000200 entity) {
        if ( entity == null ) {
            return null;
        }

        DisMmnnMmoo00000200VO disMmnnMmoo00000200VO = new DisMmnnMmoo00000200VO();

        disMmnnMmoo00000200VO.setJobRef( entity.getJobRef() );
        disMmnnMmoo00000200VO.setMState( entity.getMState() );
        disMmnnMmoo00000200VO.setCDate( entity.getCDate() );
        disMmnnMmoo00000200VO.setJGroup( entity.getJGroup() );
        disMmnnMmoo00000200VO.setUData1( entity.getUData1() );
        disMmnnMmoo00000200VO.setUData2( entity.getUData2() );
        disMmnnMmoo00000200VO.setUData3( entity.getUData3() );
        disMmnnMmoo00000200VO.setUData4( entity.getUData4() );
        disMmnnMmoo00000200VO.setUData5( entity.getUData5() );
        disMmnnMmoo00000200VO.setWrkRef( entity.getWrkRef() );
        disMmnnMmoo00000200VO.setMatRef( entity.getMatRef() );
        disMmnnMmoo00000200VO.setThickness( entity.getThickness() );
        disMmnnMmoo00000200VO.setDescrip( entity.getDescrip() );
        disMmnnMmoo00000200VO.setIsQuote( entity.getIsQuote() );
        disMmnnMmoo00000200VO.setCusRef( entity.getCusRef() );
        disMmnnMmoo00000200VO.setCusName( entity.getCusName() );
        disMmnnMmoo00000200VO.setQutRef( entity.getQutRef() );
        disMmnnMmoo00000200VO.setJobName( entity.getJobName() );
        disMmnnMmoo00000200VO.setJobOrder( entity.getJobOrder() );
        disMmnnMmoo00000200VO.setRDate( entity.getRDate() );
        disMmnnMmoo00000200VO.setCamLastDate( entity.getCamLastDate() );
        disMmnnMmoo00000200VO.setLastQuoteModification( entity.getLastQuoteModification() );
        disMmnnMmoo00000200VO.setJobElementLastDate( entity.getJobElementLastDate() );
        disMmnnMmoo00000200VO.setExternalKey( entity.getExternalKey() );
        disMmnnMmoo00000200VO.setRecState( entity.getRecState() );
        disMmnnMmoo00000200VO.setCrtDate( entity.getCrtDate() );
        disMmnnMmoo00000200VO.setLastDate( entity.getLastDate() );
        disMmnnMmoo00000200VO.setCrtUser( entity.getCrtUser() );
        disMmnnMmoo00000200VO.setLastUser( entity.getLastUser() );
        disMmnnMmoo00000200VO.setOwner( entity.getOwner() );
        disMmnnMmoo00000200VO.setRecEnt( entity.getRecEnt() );
        disMmnnMmoo00000200VO.setRecOU( entity.getRecOU() );
        disMmnnMmoo00000200VO.setRecSec( entity.getRecSec() );
        disMmnnMmoo00000200VO.setCntID( entity.getCntID() );
        disMmnnMmoo00000200VO.setRecID( entity.getRecID() );

        return disMmnnMmoo00000200VO;
    }

    @Override
    public List<DisMmnnMmoo00000200VO> toVOList(List<DisMmnnMmoo00000200> list) {
        if ( list == null ) {
            return null;
        }

        List<DisMmnnMmoo00000200VO> list1 = new ArrayList<DisMmnnMmoo00000200VO>( list.size() );
        for ( DisMmnnMmoo00000200 disMmnnMmoo00000200 : list ) {
            list1.add( toVO( disMmnnMmoo00000200 ) );
        }

        return list1;
    }

    @Override
    public DisMmnnMmoo00000200 toEntity(DisMmnnMmoo00000200DTO dto) {
        if ( dto == null ) {
            return null;
        }

        DisMmnnMmoo00000200 disMmnnMmoo00000200 = new DisMmnnMmoo00000200();

        disMmnnMmoo00000200.setJobRef( dto.getJobRef() );
        disMmnnMmoo00000200.setMState( dto.getMState() );
        disMmnnMmoo00000200.setCDate( dto.getCDate() );
        disMmnnMmoo00000200.setJGroup( dto.getJGroup() );
        disMmnnMmoo00000200.setUData1( dto.getUData1() );
        disMmnnMmoo00000200.setUData2( dto.getUData2() );
        disMmnnMmoo00000200.setUData3( dto.getUData3() );
        disMmnnMmoo00000200.setUData4( dto.getUData4() );
        disMmnnMmoo00000200.setUData5( dto.getUData5() );
        disMmnnMmoo00000200.setWrkRef( dto.getWrkRef() );
        disMmnnMmoo00000200.setMatRef( dto.getMatRef() );
        disMmnnMmoo00000200.setThickness( dto.getThickness() );
        disMmnnMmoo00000200.setDescrip( dto.getDescrip() );
        disMmnnMmoo00000200.setIsQuote( dto.getIsQuote() );
        disMmnnMmoo00000200.setCusRef( dto.getCusRef() );
        disMmnnMmoo00000200.setCusName( dto.getCusName() );
        disMmnnMmoo00000200.setQutRef( dto.getQutRef() );
        disMmnnMmoo00000200.setJobName( dto.getJobName() );
        disMmnnMmoo00000200.setJobOrder( dto.getJobOrder() );
        disMmnnMmoo00000200.setRDate( dto.getRDate() );
        disMmnnMmoo00000200.setCamLastDate( dto.getCamLastDate() );
        disMmnnMmoo00000200.setLastQuoteModification( dto.getLastQuoteModification() );
        disMmnnMmoo00000200.setJobElementLastDate( dto.getJobElementLastDate() );
        disMmnnMmoo00000200.setExternalKey( dto.getExternalKey() );
        disMmnnMmoo00000200.setRecState( dto.getRecState() );
        disMmnnMmoo00000200.setCrtDate( dto.getCrtDate() );
        disMmnnMmoo00000200.setLastDate( dto.getLastDate() );
        disMmnnMmoo00000200.setCrtUser( dto.getCrtUser() );
        disMmnnMmoo00000200.setLastUser( dto.getLastUser() );
        disMmnnMmoo00000200.setOwner( dto.getOwner() );
        disMmnnMmoo00000200.setRecEnt( dto.getRecEnt() );
        disMmnnMmoo00000200.setRecOU( dto.getRecOU() );
        disMmnnMmoo00000200.setRecSec( dto.getRecSec() );
        disMmnnMmoo00000200.setCntID( dto.getCntID() );
        disMmnnMmoo00000200.setRecID( dto.getRecID() );

        return disMmnnMmoo00000200;
    }

    @Override
    public DisMmnnMmoo00000200 toEntity(DisMmnnMmoo00000200PageQuery dto) {
        if ( dto == null ) {
            return null;
        }

        DisMmnnMmoo00000200 disMmnnMmoo00000200 = new DisMmnnMmoo00000200();

        disMmnnMmoo00000200.setJobRef( dto.getJobRef() );
        disMmnnMmoo00000200.setMState( dto.getMState() );
        disMmnnMmoo00000200.setCDate( dto.getCDate() );
        disMmnnMmoo00000200.setJGroup( dto.getJGroup() );
        disMmnnMmoo00000200.setUData1( dto.getUData1() );
        disMmnnMmoo00000200.setUData2( dto.getUData2() );
        disMmnnMmoo00000200.setUData3( dto.getUData3() );
        disMmnnMmoo00000200.setUData4( dto.getUData4() );
        disMmnnMmoo00000200.setUData5( dto.getUData5() );
        disMmnnMmoo00000200.setWrkRef( dto.getWrkRef() );
        disMmnnMmoo00000200.setMatRef( dto.getMatRef() );
        disMmnnMmoo00000200.setThickness( dto.getThickness() );
        disMmnnMmoo00000200.setDescrip( dto.getDescrip() );
        disMmnnMmoo00000200.setIsQuote( dto.getIsQuote() );
        disMmnnMmoo00000200.setCusRef( dto.getCusRef() );
        disMmnnMmoo00000200.setCusName( dto.getCusName() );
        disMmnnMmoo00000200.setQutRef( dto.getQutRef() );
        disMmnnMmoo00000200.setJobName( dto.getJobName() );
        disMmnnMmoo00000200.setJobOrder( dto.getJobOrder() );
        disMmnnMmoo00000200.setRDate( dto.getRDate() );
        disMmnnMmoo00000200.setCamLastDate( dto.getCamLastDate() );
        disMmnnMmoo00000200.setLastQuoteModification( dto.getLastQuoteModification() );
        disMmnnMmoo00000200.setJobElementLastDate( dto.getJobElementLastDate() );
        disMmnnMmoo00000200.setExternalKey( dto.getExternalKey() );
        disMmnnMmoo00000200.setRecState( dto.getRecState() );
        disMmnnMmoo00000200.setCrtDate( dto.getCrtDate() );
        disMmnnMmoo00000200.setLastDate( dto.getLastDate() );
        disMmnnMmoo00000200.setCrtUser( dto.getCrtUser() );
        disMmnnMmoo00000200.setLastUser( dto.getLastUser() );
        disMmnnMmoo00000200.setOwner( dto.getOwner() );
        disMmnnMmoo00000200.setRecEnt( dto.getRecEnt() );
        disMmnnMmoo00000200.setRecOU( dto.getRecOU() );
        disMmnnMmoo00000200.setRecSec( dto.getRecSec() );
        disMmnnMmoo00000200.setCntID( dto.getCntID() );
        disMmnnMmoo00000200.setRecID( dto.getRecID() );

        return disMmnnMmoo00000200;
    }

    @Override
    public void updateFromDTO(DisMmnnMmoo00000200DTO dto, DisMmnnMmoo00000200 entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getJobRef() != null ) {
            entity.setJobRef( dto.getJobRef() );
        }
        if ( dto.getMState() != null ) {
            entity.setMState( dto.getMState() );
        }
        if ( dto.getCDate() != null ) {
            entity.setCDate( dto.getCDate() );
        }
        if ( dto.getJGroup() != null ) {
            entity.setJGroup( dto.getJGroup() );
        }
        if ( dto.getUData1() != null ) {
            entity.setUData1( dto.getUData1() );
        }
        if ( dto.getUData2() != null ) {
            entity.setUData2( dto.getUData2() );
        }
        if ( dto.getUData3() != null ) {
            entity.setUData3( dto.getUData3() );
        }
        if ( dto.getUData4() != null ) {
            entity.setUData4( dto.getUData4() );
        }
        if ( dto.getUData5() != null ) {
            entity.setUData5( dto.getUData5() );
        }
        if ( dto.getWrkRef() != null ) {
            entity.setWrkRef( dto.getWrkRef() );
        }
        if ( dto.getMatRef() != null ) {
            entity.setMatRef( dto.getMatRef() );
        }
        if ( dto.getThickness() != null ) {
            entity.setThickness( dto.getThickness() );
        }
        if ( dto.getDescrip() != null ) {
            entity.setDescrip( dto.getDescrip() );
        }
        if ( dto.getIsQuote() != null ) {
            entity.setIsQuote( dto.getIsQuote() );
        }
        if ( dto.getCusRef() != null ) {
            entity.setCusRef( dto.getCusRef() );
        }
        if ( dto.getCusName() != null ) {
            entity.setCusName( dto.getCusName() );
        }
        if ( dto.getQutRef() != null ) {
            entity.setQutRef( dto.getQutRef() );
        }
        if ( dto.getJobName() != null ) {
            entity.setJobName( dto.getJobName() );
        }
        if ( dto.getJobOrder() != null ) {
            entity.setJobOrder( dto.getJobOrder() );
        }
        if ( dto.getRDate() != null ) {
            entity.setRDate( dto.getRDate() );
        }
        if ( dto.getCamLastDate() != null ) {
            entity.setCamLastDate( dto.getCamLastDate() );
        }
        if ( dto.getLastQuoteModification() != null ) {
            entity.setLastQuoteModification( dto.getLastQuoteModification() );
        }
        if ( dto.getJobElementLastDate() != null ) {
            entity.setJobElementLastDate( dto.getJobElementLastDate() );
        }
        if ( dto.getExternalKey() != null ) {
            entity.setExternalKey( dto.getExternalKey() );
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
