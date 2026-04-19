package com.zhurong.platform.core.lantek.convert;

import com.zhurong.platform.core.lantek.dto.PpbbPpbb00000100DTO;
import com.zhurong.platform.core.lantek.dto.PpbbPpbb00000100PageQuery;
import com.zhurong.platform.core.lantek.entity.PpbbPpbb00000100;
import com.zhurong.platform.core.lantek.vo.PpbbPpbb00000100VO;
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
public class PpbbPpbb00000100ConvertImpl implements PpbbPpbb00000100Convert {

    @Override
    public PpbbPpbb00000100VO toVO(PpbbPpbb00000100 entity) {
        if ( entity == null ) {
            return null;
        }

        PpbbPpbb00000100VO ppbbPpbb00000100VO = new PpbbPpbb00000100VO();

        ppbbPpbb00000100VO.setMaterialReqID( entity.getMaterialReqID() );
        ppbbPpbb00000100VO.setWrhRef( entity.getWrhRef() );
        ppbbPpbb00000100VO.setPrdRef( entity.getPrdRef() );
        ppbbPpbb00000100VO.setPrdName( entity.getPrdName() );
        ppbbPpbb00000100VO.setElementStatus( entity.getElementStatus() );
        ppbbPpbb00000100VO.setConfirmed( entity.getConfirmed() );
        ppbbPpbb00000100VO.setType( entity.getType() );
        ppbbPpbb00000100VO.setReference( entity.getReference() );
        ppbbPpbb00000100VO.setActRef( entity.getActRef() );
        ppbbPpbb00000100VO.setLineNum( entity.getLineNum() );
        ppbbPpbb00000100VO.setUCtName( entity.getUCtName() );
        ppbbPpbb00000100VO.setUntName( entity.getUntName() );
        ppbbPpbb00000100VO.setRDate( entity.getRDate() );
        ppbbPpbb00000100VO.setPlannedDDate( entity.getPlannedDDate() );
        ppbbPpbb00000100VO.setRequiredQ( entity.getRequiredQ() );
        ppbbPpbb00000100VO.setOnOrderQ( entity.getOnOrderQ() );
        ppbbPpbb00000100VO.setAllocatedQ( entity.getAllocatedQ() );
        ppbbPpbb00000100VO.setPendingQ( entity.getPendingQ() );
        ppbbPpbb00000100VO.setReceivedQ( entity.getReceivedQ() );
        ppbbPpbb00000100VO.setLocRef( entity.getLocRef() );
        ppbbPpbb00000100VO.setRecordID( entity.getRecordID() );
        ppbbPpbb00000100VO.setOriginRef( entity.getOriginRef() );
        ppbbPpbb00000100VO.setOriginLineNum( entity.getOriginLineNum() );
        ppbbPpbb00000100VO.setOriginType( entity.getOriginType() );
        ppbbPpbb00000100VO.setMainOriginFilter( entity.getMainOriginFilter() );
        ppbbPpbb00000100VO.setMainOriginNameFilter( entity.getMainOriginNameFilter() );
        ppbbPpbb00000100VO.setWorkPackageFilter( entity.getWorkPackageFilter() );
        ppbbPpbb00000100VO.setWorkPackageNameFilter( entity.getWorkPackageNameFilter() );
        ppbbPpbb00000100VO.setWorkOrderFilter( entity.getWorkOrderFilter() );
        ppbbPpbb00000100VO.setWorkOrderNameFilter( entity.getWorkOrderNameFilter() );
        ppbbPpbb00000100VO.setMainOriginTypeFilter( entity.getMainOriginTypeFilter() );
        ppbbPpbb00000100VO.setGLS_BatchNo( entity.getGLS_BatchNo() );
        ppbbPpbb00000100VO.setGLS_Var1( entity.getGLS_Var1() );
        ppbbPpbb00000100VO.setGLS_Var2( entity.getGLS_Var2() );
        ppbbPpbb00000100VO.setGLS_Var3( entity.getGLS_Var3() );
        ppbbPpbb00000100VO.setGLS_Var4( entity.getGLS_Var4() );
        ppbbPpbb00000100VO.setGLS_Var5( entity.getGLS_Var5() );
        ppbbPpbb00000100VO.setDIS_Subclass( entity.getDIS_Subclass() );
        ppbbPpbb00000100VO.setDIS_MatRef( entity.getDIS_MatRef() );
        ppbbPpbb00000100VO.setDIS_Thickness( entity.getDIS_Thickness() );
        ppbbPpbb00000100VO.setDIS_FormatRef( entity.getDIS_FormatRef() );
        ppbbPpbb00000100VO.setRecState( entity.getRecState() );
        ppbbPpbb00000100VO.setCrtDate( entity.getCrtDate() );
        ppbbPpbb00000100VO.setLastDate( entity.getLastDate() );
        ppbbPpbb00000100VO.setCrtUser( entity.getCrtUser() );
        ppbbPpbb00000100VO.setLastUser( entity.getLastUser() );
        ppbbPpbb00000100VO.setOwner( entity.getOwner() );
        ppbbPpbb00000100VO.setRecEnt( entity.getRecEnt() );
        ppbbPpbb00000100VO.setRecOU( entity.getRecOU() );
        ppbbPpbb00000100VO.setRecSec( entity.getRecSec() );
        ppbbPpbb00000100VO.setCntID( entity.getCntID() );
        ppbbPpbb00000100VO.setRecID( entity.getRecID() );

        return ppbbPpbb00000100VO;
    }

    @Override
    public List<PpbbPpbb00000100VO> toVOList(List<PpbbPpbb00000100> list) {
        if ( list == null ) {
            return null;
        }

        List<PpbbPpbb00000100VO> list1 = new ArrayList<PpbbPpbb00000100VO>( list.size() );
        for ( PpbbPpbb00000100 ppbbPpbb00000100 : list ) {
            list1.add( toVO( ppbbPpbb00000100 ) );
        }

        return list1;
    }

    @Override
    public PpbbPpbb00000100 toEntity(PpbbPpbb00000100DTO dto) {
        if ( dto == null ) {
            return null;
        }

        PpbbPpbb00000100 ppbbPpbb00000100 = new PpbbPpbb00000100();

        ppbbPpbb00000100.setMaterialReqID( dto.getMaterialReqID() );
        ppbbPpbb00000100.setWrhRef( dto.getWrhRef() );
        ppbbPpbb00000100.setPrdRef( dto.getPrdRef() );
        ppbbPpbb00000100.setPrdName( dto.getPrdName() );
        ppbbPpbb00000100.setElementStatus( dto.getElementStatus() );
        ppbbPpbb00000100.setConfirmed( dto.getConfirmed() );
        ppbbPpbb00000100.setType( dto.getType() );
        ppbbPpbb00000100.setReference( dto.getReference() );
        ppbbPpbb00000100.setActRef( dto.getActRef() );
        ppbbPpbb00000100.setLineNum( dto.getLineNum() );
        ppbbPpbb00000100.setUCtName( dto.getUCtName() );
        ppbbPpbb00000100.setUntName( dto.getUntName() );
        ppbbPpbb00000100.setRDate( dto.getRDate() );
        ppbbPpbb00000100.setPlannedDDate( dto.getPlannedDDate() );
        ppbbPpbb00000100.setRequiredQ( dto.getRequiredQ() );
        ppbbPpbb00000100.setOnOrderQ( dto.getOnOrderQ() );
        ppbbPpbb00000100.setAllocatedQ( dto.getAllocatedQ() );
        ppbbPpbb00000100.setPendingQ( dto.getPendingQ() );
        ppbbPpbb00000100.setReceivedQ( dto.getReceivedQ() );
        ppbbPpbb00000100.setLocRef( dto.getLocRef() );
        ppbbPpbb00000100.setRecordID( dto.getRecordID() );
        ppbbPpbb00000100.setOriginRef( dto.getOriginRef() );
        ppbbPpbb00000100.setOriginLineNum( dto.getOriginLineNum() );
        ppbbPpbb00000100.setOriginType( dto.getOriginType() );
        ppbbPpbb00000100.setMainOriginFilter( dto.getMainOriginFilter() );
        ppbbPpbb00000100.setMainOriginNameFilter( dto.getMainOriginNameFilter() );
        ppbbPpbb00000100.setWorkPackageFilter( dto.getWorkPackageFilter() );
        ppbbPpbb00000100.setWorkPackageNameFilter( dto.getWorkPackageNameFilter() );
        ppbbPpbb00000100.setWorkOrderFilter( dto.getWorkOrderFilter() );
        ppbbPpbb00000100.setWorkOrderNameFilter( dto.getWorkOrderNameFilter() );
        ppbbPpbb00000100.setMainOriginTypeFilter( dto.getMainOriginTypeFilter() );
        ppbbPpbb00000100.setGLS_BatchNo( dto.getGLS_BatchNo() );
        ppbbPpbb00000100.setGLS_Var1( dto.getGLS_Var1() );
        ppbbPpbb00000100.setGLS_Var2( dto.getGLS_Var2() );
        ppbbPpbb00000100.setGLS_Var3( dto.getGLS_Var3() );
        ppbbPpbb00000100.setGLS_Var4( dto.getGLS_Var4() );
        ppbbPpbb00000100.setGLS_Var5( dto.getGLS_Var5() );
        ppbbPpbb00000100.setDIS_Subclass( dto.getDIS_Subclass() );
        ppbbPpbb00000100.setDIS_MatRef( dto.getDIS_MatRef() );
        ppbbPpbb00000100.setDIS_Thickness( dto.getDIS_Thickness() );
        ppbbPpbb00000100.setDIS_FormatRef( dto.getDIS_FormatRef() );
        ppbbPpbb00000100.setRecState( dto.getRecState() );
        ppbbPpbb00000100.setCrtDate( dto.getCrtDate() );
        ppbbPpbb00000100.setLastDate( dto.getLastDate() );
        ppbbPpbb00000100.setCrtUser( dto.getCrtUser() );
        ppbbPpbb00000100.setLastUser( dto.getLastUser() );
        ppbbPpbb00000100.setOwner( dto.getOwner() );
        ppbbPpbb00000100.setRecEnt( dto.getRecEnt() );
        ppbbPpbb00000100.setRecOU( dto.getRecOU() );
        ppbbPpbb00000100.setRecSec( dto.getRecSec() );
        ppbbPpbb00000100.setCntID( dto.getCntID() );
        ppbbPpbb00000100.setRecID( dto.getRecID() );

        return ppbbPpbb00000100;
    }

    @Override
    public PpbbPpbb00000100 toEntity(PpbbPpbb00000100PageQuery dto) {
        if ( dto == null ) {
            return null;
        }

        PpbbPpbb00000100 ppbbPpbb00000100 = new PpbbPpbb00000100();

        ppbbPpbb00000100.setMaterialReqID( dto.getMaterialReqID() );
        ppbbPpbb00000100.setWrhRef( dto.getWrhRef() );
        ppbbPpbb00000100.setPrdRef( dto.getPrdRef() );
        ppbbPpbb00000100.setPrdName( dto.getPrdName() );
        ppbbPpbb00000100.setElementStatus( dto.getElementStatus() );
        ppbbPpbb00000100.setConfirmed( dto.getConfirmed() );
        ppbbPpbb00000100.setType( dto.getType() );
        ppbbPpbb00000100.setReference( dto.getReference() );
        ppbbPpbb00000100.setActRef( dto.getActRef() );
        ppbbPpbb00000100.setLineNum( dto.getLineNum() );
        ppbbPpbb00000100.setUCtName( dto.getUCtName() );
        ppbbPpbb00000100.setUntName( dto.getUntName() );
        ppbbPpbb00000100.setRDate( dto.getRDate() );
        ppbbPpbb00000100.setPlannedDDate( dto.getPlannedDDate() );
        ppbbPpbb00000100.setRequiredQ( dto.getRequiredQ() );
        ppbbPpbb00000100.setOnOrderQ( dto.getOnOrderQ() );
        ppbbPpbb00000100.setAllocatedQ( dto.getAllocatedQ() );
        ppbbPpbb00000100.setPendingQ( dto.getPendingQ() );
        ppbbPpbb00000100.setReceivedQ( dto.getReceivedQ() );
        ppbbPpbb00000100.setLocRef( dto.getLocRef() );
        ppbbPpbb00000100.setRecordID( dto.getRecordID() );
        ppbbPpbb00000100.setOriginRef( dto.getOriginRef() );
        ppbbPpbb00000100.setOriginLineNum( dto.getOriginLineNum() );
        ppbbPpbb00000100.setOriginType( dto.getOriginType() );
        ppbbPpbb00000100.setMainOriginFilter( dto.getMainOriginFilter() );
        ppbbPpbb00000100.setMainOriginNameFilter( dto.getMainOriginNameFilter() );
        ppbbPpbb00000100.setWorkPackageFilter( dto.getWorkPackageFilter() );
        ppbbPpbb00000100.setWorkPackageNameFilter( dto.getWorkPackageNameFilter() );
        ppbbPpbb00000100.setWorkOrderFilter( dto.getWorkOrderFilter() );
        ppbbPpbb00000100.setWorkOrderNameFilter( dto.getWorkOrderNameFilter() );
        ppbbPpbb00000100.setMainOriginTypeFilter( dto.getMainOriginTypeFilter() );
        ppbbPpbb00000100.setRecState( dto.getRecState() );
        ppbbPpbb00000100.setCrtDate( dto.getCrtDate() );
        ppbbPpbb00000100.setLastDate( dto.getLastDate() );
        ppbbPpbb00000100.setCrtUser( dto.getCrtUser() );
        ppbbPpbb00000100.setLastUser( dto.getLastUser() );
        ppbbPpbb00000100.setOwner( dto.getOwner() );
        ppbbPpbb00000100.setRecEnt( dto.getRecEnt() );
        ppbbPpbb00000100.setRecOU( dto.getRecOU() );
        ppbbPpbb00000100.setRecSec( dto.getRecSec() );
        ppbbPpbb00000100.setCntID( dto.getCntID() );
        ppbbPpbb00000100.setRecID( dto.getRecID() );

        return ppbbPpbb00000100;
    }

    @Override
    public void updateFromDTO(PpbbPpbb00000100DTO dto, PpbbPpbb00000100 entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getMaterialReqID() != null ) {
            entity.setMaterialReqID( dto.getMaterialReqID() );
        }
        if ( dto.getWrhRef() != null ) {
            entity.setWrhRef( dto.getWrhRef() );
        }
        if ( dto.getPrdRef() != null ) {
            entity.setPrdRef( dto.getPrdRef() );
        }
        if ( dto.getPrdName() != null ) {
            entity.setPrdName( dto.getPrdName() );
        }
        if ( dto.getElementStatus() != null ) {
            entity.setElementStatus( dto.getElementStatus() );
        }
        if ( dto.getConfirmed() != null ) {
            entity.setConfirmed( dto.getConfirmed() );
        }
        if ( dto.getType() != null ) {
            entity.setType( dto.getType() );
        }
        if ( dto.getReference() != null ) {
            entity.setReference( dto.getReference() );
        }
        if ( dto.getActRef() != null ) {
            entity.setActRef( dto.getActRef() );
        }
        if ( dto.getLineNum() != null ) {
            entity.setLineNum( dto.getLineNum() );
        }
        if ( dto.getUCtName() != null ) {
            entity.setUCtName( dto.getUCtName() );
        }
        if ( dto.getUntName() != null ) {
            entity.setUntName( dto.getUntName() );
        }
        if ( dto.getRDate() != null ) {
            entity.setRDate( dto.getRDate() );
        }
        if ( dto.getPlannedDDate() != null ) {
            entity.setPlannedDDate( dto.getPlannedDDate() );
        }
        if ( dto.getRequiredQ() != null ) {
            entity.setRequiredQ( dto.getRequiredQ() );
        }
        if ( dto.getOnOrderQ() != null ) {
            entity.setOnOrderQ( dto.getOnOrderQ() );
        }
        if ( dto.getAllocatedQ() != null ) {
            entity.setAllocatedQ( dto.getAllocatedQ() );
        }
        if ( dto.getPendingQ() != null ) {
            entity.setPendingQ( dto.getPendingQ() );
        }
        if ( dto.getReceivedQ() != null ) {
            entity.setReceivedQ( dto.getReceivedQ() );
        }
        if ( dto.getLocRef() != null ) {
            entity.setLocRef( dto.getLocRef() );
        }
        if ( dto.getRecordID() != null ) {
            entity.setRecordID( dto.getRecordID() );
        }
        if ( dto.getOriginRef() != null ) {
            entity.setOriginRef( dto.getOriginRef() );
        }
        if ( dto.getOriginLineNum() != null ) {
            entity.setOriginLineNum( dto.getOriginLineNum() );
        }
        if ( dto.getOriginType() != null ) {
            entity.setOriginType( dto.getOriginType() );
        }
        if ( dto.getMainOriginFilter() != null ) {
            entity.setMainOriginFilter( dto.getMainOriginFilter() );
        }
        if ( dto.getMainOriginNameFilter() != null ) {
            entity.setMainOriginNameFilter( dto.getMainOriginNameFilter() );
        }
        if ( dto.getWorkPackageFilter() != null ) {
            entity.setWorkPackageFilter( dto.getWorkPackageFilter() );
        }
        if ( dto.getWorkPackageNameFilter() != null ) {
            entity.setWorkPackageNameFilter( dto.getWorkPackageNameFilter() );
        }
        if ( dto.getWorkOrderFilter() != null ) {
            entity.setWorkOrderFilter( dto.getWorkOrderFilter() );
        }
        if ( dto.getWorkOrderNameFilter() != null ) {
            entity.setWorkOrderNameFilter( dto.getWorkOrderNameFilter() );
        }
        if ( dto.getMainOriginTypeFilter() != null ) {
            entity.setMainOriginTypeFilter( dto.getMainOriginTypeFilter() );
        }
        if ( dto.getGLS_BatchNo() != null ) {
            entity.setGLS_BatchNo( dto.getGLS_BatchNo() );
        }
        if ( dto.getGLS_Var1() != null ) {
            entity.setGLS_Var1( dto.getGLS_Var1() );
        }
        if ( dto.getGLS_Var2() != null ) {
            entity.setGLS_Var2( dto.getGLS_Var2() );
        }
        if ( dto.getGLS_Var3() != null ) {
            entity.setGLS_Var3( dto.getGLS_Var3() );
        }
        if ( dto.getGLS_Var4() != null ) {
            entity.setGLS_Var4( dto.getGLS_Var4() );
        }
        if ( dto.getGLS_Var5() != null ) {
            entity.setGLS_Var5( dto.getGLS_Var5() );
        }
        if ( dto.getDIS_Subclass() != null ) {
            entity.setDIS_Subclass( dto.getDIS_Subclass() );
        }
        if ( dto.getDIS_MatRef() != null ) {
            entity.setDIS_MatRef( dto.getDIS_MatRef() );
        }
        if ( dto.getDIS_Thickness() != null ) {
            entity.setDIS_Thickness( dto.getDIS_Thickness() );
        }
        if ( dto.getDIS_FormatRef() != null ) {
            entity.setDIS_FormatRef( dto.getDIS_FormatRef() );
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
