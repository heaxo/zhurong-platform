package com.zhurong.platform.core.lantek.convert;

import com.zhurong.platform.core.lantek.dto.MmnnMmoo00000300DTO;
import com.zhurong.platform.core.lantek.dto.MmnnMmoo00000300PageQuery;
import com.zhurong.platform.core.lantek.entity.MmnnMmoo00000300;
import com.zhurong.platform.core.lantek.vo.MmnnMmoo00000300VO;
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
public class MmnnMmoo00000300ConvertImpl implements MmnnMmoo00000300Convert {

    @Override
    public MmnnMmoo00000300VO toVO(MmnnMmoo00000300 entity) {
        if ( entity == null ) {
            return null;
        }

        MmnnMmoo00000300VO mmnnMmoo00000300VO = new MmnnMmoo00000300VO();

        mmnnMmoo00000300VO.setMnORef( entity.getMnORef() );
        mmnnMmoo00000300VO.setOprID( entity.getOprID() );
        mmnnMmoo00000300VO.setNextOprID( entity.getNextOprID() );
        mmnnMmoo00000300VO.setRouID( entity.getRouID() );
        mmnnMmoo00000300VO.setNextRouID( entity.getNextRouID() );
        mmnnMmoo00000300VO.setPrdRef( entity.getPrdRef() );
        mmnnMmoo00000300VO.setPrdRefOrg( entity.getPrdRefOrg() );
        mmnnMmoo00000300VO.setNComp( entity.getNComp() );
        mmnnMmoo00000300VO.setPrdRefDst( entity.getPrdRefDst() );
        mmnnMmoo00000300VO.setPrdNameDst( entity.getPrdNameDst() );
        mmnnMmoo00000300VO.setRouRef( entity.getRouRef() );
        mmnnMmoo00000300VO.setOOrder( entity.getOOrder() );
        mmnnMmoo00000300VO.setWrkRef( entity.getWrkRef() );
        mmnnMmoo00000300VO.setOprRef( entity.getOprRef() );
        mmnnMmoo00000300VO.setPPriority( entity.getPPriority() );
        mmnnMmoo00000300VO.setOrigin( entity.getOrigin() );
        mmnnMmoo00000300VO.setOrdRef( entity.getOrdRef() );
        mmnnMmoo00000300VO.setOLineNum( entity.getOLineNum() );
        mmnnMmoo00000300VO.setCusRef( entity.getCusRef() );
        mmnnMmoo00000300VO.setCusName( entity.getCusName() );
        mmnnMmoo00000300VO.setCDate( entity.getCDate() );
        mmnnMmoo00000300VO.setRDate( entity.getRDate() );
        mmnnMmoo00000300VO.setIsEnd( entity.getIsEnd() );
        mmnnMmoo00000300VO.setUCtName( entity.getUCtName() );
        mmnnMmoo00000300VO.setUntName( entity.getUntName() );
        mmnnMmoo00000300VO.setQuantity( entity.getQuantity() );
        mmnnMmoo00000300VO.setAQ( entity.getAQ() );
        mmnnMmoo00000300VO.setMQ( entity.getMQ() );
        mmnnMmoo00000300VO.setLQ( entity.getLQ() );
        mmnnMmoo00000300VO.setToOutQ( entity.getToOutQ() );
        mmnnMmoo00000300VO.setOutQ( entity.getOutQ() );
        mmnnMmoo00000300VO.setMState( entity.getMState() );
        mmnnMmoo00000300VO.setRQ( entity.getRQ() );
        mmnnMmoo00000300VO.setOState( entity.getOState() );
        mmnnMmoo00000300VO.setEDuration( entity.getEDuration() );
        mmnnMmoo00000300VO.setIOrder( entity.getIOrder() );
        mmnnMmoo00000300VO.setSDate( entity.getSDate() );
        mmnnMmoo00000300VO.setEDate( entity.getEDate() );
        mmnnMmoo00000300VO.setPartialSDate( entity.getPartialSDate() );
        mmnnMmoo00000300VO.setESDate( entity.getESDate() );
        mmnnMmoo00000300VO.setEEDate( entity.getEEDate() );
        mmnnMmoo00000300VO.setDescrip( entity.getDescrip() );
        mmnnMmoo00000300VO.setStdTime( entity.getStdTime() );
        mmnnMmoo00000300VO.setRunTime( entity.getRunTime() );
        mmnnMmoo00000300VO.setCurTime( entity.getCurTime() );
        mmnnMmoo00000300VO.setWrkTime( entity.getWrkTime() );
        mmnnMmoo00000300VO.setWrkCap( entity.getWrkCap() );
        mmnnMmoo00000300VO.setStdCostU( entity.getStdCostU() );
        mmnnMmoo00000300VO.setCurCostU( entity.getCurCostU() );
        mmnnMmoo00000300VO.setStdCostT( entity.getStdCostT() );
        mmnnMmoo00000300VO.setCurCostT( entity.getCurCostT() );
        mmnnMmoo00000300VO.setMainOpr( entity.getMainOpr() );
        mmnnMmoo00000300VO.setParallelOpr( entity.getParallelOpr() );
        mmnnMmoo00000300VO.setWrhRefSM( entity.getWrhRefSM() );
        mmnnMmoo00000300VO.setLineNumSM( entity.getLineNumSM() );
        mmnnMmoo00000300VO.setOutOrdRef( entity.getOutOrdRef() );
        mmnnMmoo00000300VO.setOutLineNum( entity.getOutLineNum() );
        mmnnMmoo00000300VO.setSuppRef( entity.getSuppRef() );
        mmnnMmoo00000300VO.setSuppName( entity.getSuppName() );
        mmnnMmoo00000300VO.setCurName( entity.getCurName() );
        mmnnMmoo00000300VO.setTarRef( entity.getTarRef() );
        mmnnMmoo00000300VO.setMinQuan( entity.getMinQuan() );
        mmnnMmoo00000300VO.setIsOutsourcing( entity.getIsOutsourcing() );
        mmnnMmoo00000300VO.setCurCostA( entity.getCurCostA() );
        mmnnMmoo00000300VO.setMainOriginFilter( entity.getMainOriginFilter() );
        mmnnMmoo00000300VO.setMainOriginNameFilter( entity.getMainOriginNameFilter() );
        mmnnMmoo00000300VO.setWorkPackageFilter( entity.getWorkPackageFilter() );
        mmnnMmoo00000300VO.setWorkPackageNameFilter( entity.getWorkPackageNameFilter() );
        mmnnMmoo00000300VO.setMainOriginTypeFilter( entity.getMainOriginTypeFilter() );
        mmnnMmoo00000300VO.setMainPackage( entity.getMainPackage() );
        mmnnMmoo00000300VO.setMainPackageName( entity.getMainPackageName() );
        mmnnMmoo00000300VO.setDIS_PGroup( entity.getDIS_PGroup() );
        mmnnMmoo00000300VO.setDIS_MatRef( entity.getDIS_MatRef() );
        mmnnMmoo00000300VO.setDIS_Thickness( entity.getDIS_Thickness() );
        mmnnMmoo00000300VO.setDIS_JobRef( entity.getDIS_JobRef() );
        mmnnMmoo00000300VO.setDIS_FPosition( entity.getDIS_FPosition() );
        mmnnMmoo00000300VO.setDIS_NQ( entity.getDIS_NQ() );
        mmnnMmoo00000300VO.setDIS_IsChanged( entity.getDIS_IsChanged() );
        mmnnMmoo00000300VO.setDIS_IsDuct( entity.getDIS_IsDuct() );
        mmnnMmoo00000300VO.setDIS_Is2DSOp( entity.getDIS_Is2DSOp() );
        mmnnMmoo00000300VO.setDIS_Is3DSOp( entity.getDIS_Is3DSOp() );
        mmnnMmoo00000300VO.setDIS_IsQuote( entity.getDIS_IsQuote() );
        mmnnMmoo00000300VO.setDIS_WrkCfg( entity.getDIS_WrkCfg() );
        mmnnMmoo00000300VO.setWosStatus( entity.getWosStatus() );
        mmnnMmoo00000300VO.setDIS_NonCuttingJob( entity.getDIS_NonCuttingJob() );
        mmnnMmoo00000300VO.setDIS_OrgOprID( entity.getDIS_OrgOprID() );
        mmnnMmoo00000300VO.setDIS_MStateCloudStatus( entity.getDIS_MStateCloudStatus() );
        mmnnMmoo00000300VO.setRecState( entity.getRecState() );
        mmnnMmoo00000300VO.setCrtDate( entity.getCrtDate() );
        mmnnMmoo00000300VO.setLastDate( entity.getLastDate() );
        mmnnMmoo00000300VO.setCrtUser( entity.getCrtUser() );
        mmnnMmoo00000300VO.setLastUser( entity.getLastUser() );
        mmnnMmoo00000300VO.setOwner( entity.getOwner() );
        mmnnMmoo00000300VO.setRecEnt( entity.getRecEnt() );
        mmnnMmoo00000300VO.setRecOU( entity.getRecOU() );
        mmnnMmoo00000300VO.setRecSec( entity.getRecSec() );
        mmnnMmoo00000300VO.setCntID( entity.getCntID() );
        mmnnMmoo00000300VO.setRecID( entity.getRecID() );

        return mmnnMmoo00000300VO;
    }

    @Override
    public List<MmnnMmoo00000300VO> toVOList(List<MmnnMmoo00000300> list) {
        if ( list == null ) {
            return null;
        }

        List<MmnnMmoo00000300VO> list1 = new ArrayList<MmnnMmoo00000300VO>( list.size() );
        for ( MmnnMmoo00000300 mmnnMmoo00000300 : list ) {
            list1.add( toVO( mmnnMmoo00000300 ) );
        }

        return list1;
    }

    @Override
    public MmnnMmoo00000300 toEntity(MmnnMmoo00000300DTO dto) {
        if ( dto == null ) {
            return null;
        }

        MmnnMmoo00000300 mmnnMmoo00000300 = new MmnnMmoo00000300();

        mmnnMmoo00000300.setMnORef( dto.getMnORef() );
        mmnnMmoo00000300.setOprID( dto.getOprID() );
        mmnnMmoo00000300.setNextOprID( dto.getNextOprID() );
        mmnnMmoo00000300.setRouID( dto.getRouID() );
        mmnnMmoo00000300.setNextRouID( dto.getNextRouID() );
        mmnnMmoo00000300.setPrdRef( dto.getPrdRef() );
        mmnnMmoo00000300.setPrdRefOrg( dto.getPrdRefOrg() );
        mmnnMmoo00000300.setNComp( dto.getNComp() );
        mmnnMmoo00000300.setPrdRefDst( dto.getPrdRefDst() );
        mmnnMmoo00000300.setPrdNameDst( dto.getPrdNameDst() );
        mmnnMmoo00000300.setRouRef( dto.getRouRef() );
        mmnnMmoo00000300.setOOrder( dto.getOOrder() );
        mmnnMmoo00000300.setWrkRef( dto.getWrkRef() );
        mmnnMmoo00000300.setOprRef( dto.getOprRef() );
        mmnnMmoo00000300.setPPriority( dto.getPPriority() );
        mmnnMmoo00000300.setOrigin( dto.getOrigin() );
        mmnnMmoo00000300.setOrdRef( dto.getOrdRef() );
        mmnnMmoo00000300.setOLineNum( dto.getOLineNum() );
        mmnnMmoo00000300.setCusRef( dto.getCusRef() );
        mmnnMmoo00000300.setCusName( dto.getCusName() );
        mmnnMmoo00000300.setCDate( dto.getCDate() );
        mmnnMmoo00000300.setRDate( dto.getRDate() );
        mmnnMmoo00000300.setIsEnd( dto.getIsEnd() );
        mmnnMmoo00000300.setUCtName( dto.getUCtName() );
        mmnnMmoo00000300.setUntName( dto.getUntName() );
        mmnnMmoo00000300.setQuantity( dto.getQuantity() );
        mmnnMmoo00000300.setAQ( dto.getAQ() );
        mmnnMmoo00000300.setMQ( dto.getMQ() );
        mmnnMmoo00000300.setLQ( dto.getLQ() );
        mmnnMmoo00000300.setToOutQ( dto.getToOutQ() );
        mmnnMmoo00000300.setOutQ( dto.getOutQ() );
        mmnnMmoo00000300.setMState( dto.getMState() );
        mmnnMmoo00000300.setRQ( dto.getRQ() );
        mmnnMmoo00000300.setOState( dto.getOState() );
        mmnnMmoo00000300.setEDuration( dto.getEDuration() );
        mmnnMmoo00000300.setIOrder( dto.getIOrder() );
        mmnnMmoo00000300.setSDate( dto.getSDate() );
        mmnnMmoo00000300.setEDate( dto.getEDate() );
        mmnnMmoo00000300.setPartialSDate( dto.getPartialSDate() );
        mmnnMmoo00000300.setESDate( dto.getESDate() );
        mmnnMmoo00000300.setEEDate( dto.getEEDate() );
        mmnnMmoo00000300.setDescrip( dto.getDescrip() );
        mmnnMmoo00000300.setStdTime( dto.getStdTime() );
        mmnnMmoo00000300.setRunTime( dto.getRunTime() );
        mmnnMmoo00000300.setCurTime( dto.getCurTime() );
        mmnnMmoo00000300.setWrkTime( dto.getWrkTime() );
        mmnnMmoo00000300.setWrkCap( dto.getWrkCap() );
        mmnnMmoo00000300.setStdCostU( dto.getStdCostU() );
        mmnnMmoo00000300.setCurCostU( dto.getCurCostU() );
        mmnnMmoo00000300.setStdCostT( dto.getStdCostT() );
        mmnnMmoo00000300.setCurCostT( dto.getCurCostT() );
        mmnnMmoo00000300.setMainOpr( dto.getMainOpr() );
        mmnnMmoo00000300.setParallelOpr( dto.getParallelOpr() );
        mmnnMmoo00000300.setWrhRefSM( dto.getWrhRefSM() );
        mmnnMmoo00000300.setLineNumSM( dto.getLineNumSM() );
        mmnnMmoo00000300.setOutOrdRef( dto.getOutOrdRef() );
        mmnnMmoo00000300.setOutLineNum( dto.getOutLineNum() );
        mmnnMmoo00000300.setSuppRef( dto.getSuppRef() );
        mmnnMmoo00000300.setSuppName( dto.getSuppName() );
        mmnnMmoo00000300.setCurName( dto.getCurName() );
        mmnnMmoo00000300.setTarRef( dto.getTarRef() );
        mmnnMmoo00000300.setMinQuan( dto.getMinQuan() );
        mmnnMmoo00000300.setIsOutsourcing( dto.getIsOutsourcing() );
        mmnnMmoo00000300.setCurCostA( dto.getCurCostA() );
        mmnnMmoo00000300.setMainOriginFilter( dto.getMainOriginFilter() );
        mmnnMmoo00000300.setMainOriginNameFilter( dto.getMainOriginNameFilter() );
        mmnnMmoo00000300.setWorkPackageFilter( dto.getWorkPackageFilter() );
        mmnnMmoo00000300.setWorkPackageNameFilter( dto.getWorkPackageNameFilter() );
        mmnnMmoo00000300.setMainOriginTypeFilter( dto.getMainOriginTypeFilter() );
        mmnnMmoo00000300.setMainPackage( dto.getMainPackage() );
        mmnnMmoo00000300.setMainPackageName( dto.getMainPackageName() );
        mmnnMmoo00000300.setDIS_PGroup( dto.getDIS_PGroup() );
        mmnnMmoo00000300.setDIS_MatRef( dto.getDIS_MatRef() );
        mmnnMmoo00000300.setDIS_Thickness( dto.getDIS_Thickness() );
        mmnnMmoo00000300.setDIS_JobRef( dto.getDIS_JobRef() );
        mmnnMmoo00000300.setDIS_FPosition( dto.getDIS_FPosition() );
        mmnnMmoo00000300.setDIS_NQ( dto.getDIS_NQ() );
        mmnnMmoo00000300.setDIS_IsChanged( dto.getDIS_IsChanged() );
        mmnnMmoo00000300.setDIS_IsDuct( dto.getDIS_IsDuct() );
        mmnnMmoo00000300.setDIS_Is2DSOp( dto.getDIS_Is2DSOp() );
        mmnnMmoo00000300.setDIS_Is3DSOp( dto.getDIS_Is3DSOp() );
        mmnnMmoo00000300.setDIS_IsQuote( dto.getDIS_IsQuote() );
        mmnnMmoo00000300.setDIS_WrkCfg( dto.getDIS_WrkCfg() );
        mmnnMmoo00000300.setWosStatus( dto.getWosStatus() );
        mmnnMmoo00000300.setDIS_NonCuttingJob( dto.getDIS_NonCuttingJob() );
        mmnnMmoo00000300.setDIS_OrgOprID( dto.getDIS_OrgOprID() );
        mmnnMmoo00000300.setDIS_MStateCloudStatus( dto.getDIS_MStateCloudStatus() );
        mmnnMmoo00000300.setRecState( dto.getRecState() );
        mmnnMmoo00000300.setCrtDate( dto.getCrtDate() );
        mmnnMmoo00000300.setLastDate( dto.getLastDate() );
        mmnnMmoo00000300.setCrtUser( dto.getCrtUser() );
        mmnnMmoo00000300.setLastUser( dto.getLastUser() );
        mmnnMmoo00000300.setOwner( dto.getOwner() );
        mmnnMmoo00000300.setRecEnt( dto.getRecEnt() );
        mmnnMmoo00000300.setRecOU( dto.getRecOU() );
        mmnnMmoo00000300.setRecSec( dto.getRecSec() );
        mmnnMmoo00000300.setCntID( dto.getCntID() );
        mmnnMmoo00000300.setRecID( dto.getRecID() );

        return mmnnMmoo00000300;
    }

    @Override
    public MmnnMmoo00000300 toEntity(MmnnMmoo00000300PageQuery dto) {
        if ( dto == null ) {
            return null;
        }

        MmnnMmoo00000300 mmnnMmoo00000300 = new MmnnMmoo00000300();

        mmnnMmoo00000300.setMnORef( dto.getMnORef() );
        mmnnMmoo00000300.setOprID( dto.getOprID() );
        mmnnMmoo00000300.setNextOprID( dto.getNextOprID() );
        mmnnMmoo00000300.setRouID( dto.getRouID() );
        mmnnMmoo00000300.setNextRouID( dto.getNextRouID() );
        mmnnMmoo00000300.setPrdRef( dto.getPrdRef() );
        mmnnMmoo00000300.setPrdRefOrg( dto.getPrdRefOrg() );
        mmnnMmoo00000300.setNComp( dto.getNComp() );
        mmnnMmoo00000300.setPrdRefDst( dto.getPrdRefDst() );
        mmnnMmoo00000300.setPrdNameDst( dto.getPrdNameDst() );
        mmnnMmoo00000300.setRouRef( dto.getRouRef() );
        mmnnMmoo00000300.setOOrder( dto.getOOrder() );
        mmnnMmoo00000300.setWrkRef( dto.getWrkRef() );
        mmnnMmoo00000300.setOprRef( dto.getOprRef() );
        mmnnMmoo00000300.setPPriority( dto.getPPriority() );
        mmnnMmoo00000300.setOrigin( dto.getOrigin() );
        mmnnMmoo00000300.setOrdRef( dto.getOrdRef() );
        mmnnMmoo00000300.setOLineNum( dto.getOLineNum() );
        mmnnMmoo00000300.setCusRef( dto.getCusRef() );
        mmnnMmoo00000300.setCusName( dto.getCusName() );
        mmnnMmoo00000300.setCDate( dto.getCDate() );
        mmnnMmoo00000300.setRDate( dto.getRDate() );
        mmnnMmoo00000300.setIsEnd( dto.getIsEnd() );
        mmnnMmoo00000300.setUCtName( dto.getUCtName() );
        mmnnMmoo00000300.setUntName( dto.getUntName() );
        mmnnMmoo00000300.setQuantity( dto.getQuantity() );
        mmnnMmoo00000300.setToOutQ( dto.getToOutQ() );
        mmnnMmoo00000300.setOutQ( dto.getOutQ() );
        mmnnMmoo00000300.setMState( dto.getMState() );
        mmnnMmoo00000300.setOState( dto.getOState() );
        mmnnMmoo00000300.setEDuration( dto.getEDuration() );
        mmnnMmoo00000300.setIOrder( dto.getIOrder() );
        mmnnMmoo00000300.setSDate( dto.getSDate() );
        mmnnMmoo00000300.setEDate( dto.getEDate() );
        mmnnMmoo00000300.setPartialSDate( dto.getPartialSDate() );
        mmnnMmoo00000300.setESDate( dto.getESDate() );
        mmnnMmoo00000300.setEEDate( dto.getEEDate() );
        mmnnMmoo00000300.setDescrip( dto.getDescrip() );
        mmnnMmoo00000300.setStdTime( dto.getStdTime() );
        mmnnMmoo00000300.setRunTime( dto.getRunTime() );
        mmnnMmoo00000300.setCurTime( dto.getCurTime() );
        mmnnMmoo00000300.setWrkTime( dto.getWrkTime() );
        mmnnMmoo00000300.setWrkCap( dto.getWrkCap() );
        mmnnMmoo00000300.setStdCostU( dto.getStdCostU() );
        mmnnMmoo00000300.setCurCostU( dto.getCurCostU() );
        mmnnMmoo00000300.setStdCostT( dto.getStdCostT() );
        mmnnMmoo00000300.setCurCostT( dto.getCurCostT() );
        mmnnMmoo00000300.setMainOpr( dto.getMainOpr() );
        mmnnMmoo00000300.setParallelOpr( dto.getParallelOpr() );
        mmnnMmoo00000300.setWrhRefSM( dto.getWrhRefSM() );
        mmnnMmoo00000300.setLineNumSM( dto.getLineNumSM() );
        mmnnMmoo00000300.setOutOrdRef( dto.getOutOrdRef() );
        mmnnMmoo00000300.setOutLineNum( dto.getOutLineNum() );
        mmnnMmoo00000300.setSuppRef( dto.getSuppRef() );
        mmnnMmoo00000300.setSuppName( dto.getSuppName() );
        mmnnMmoo00000300.setCurName( dto.getCurName() );
        mmnnMmoo00000300.setTarRef( dto.getTarRef() );
        mmnnMmoo00000300.setMinQuan( dto.getMinQuan() );
        mmnnMmoo00000300.setIsOutsourcing( dto.getIsOutsourcing() );
        mmnnMmoo00000300.setCurCostA( dto.getCurCostA() );
        mmnnMmoo00000300.setMainOriginFilter( dto.getMainOriginFilter() );
        mmnnMmoo00000300.setMainOriginNameFilter( dto.getMainOriginNameFilter() );
        mmnnMmoo00000300.setWorkPackageFilter( dto.getWorkPackageFilter() );
        mmnnMmoo00000300.setWorkPackageNameFilter( dto.getWorkPackageNameFilter() );
        mmnnMmoo00000300.setMainOriginTypeFilter( dto.getMainOriginTypeFilter() );
        mmnnMmoo00000300.setMainPackage( dto.getMainPackage() );
        mmnnMmoo00000300.setMainPackageName( dto.getMainPackageName() );
        mmnnMmoo00000300.setWosStatus( dto.getWosStatus() );
        mmnnMmoo00000300.setRecState( dto.getRecState() );
        mmnnMmoo00000300.setCrtDate( dto.getCrtDate() );
        mmnnMmoo00000300.setLastDate( dto.getLastDate() );
        mmnnMmoo00000300.setCrtUser( dto.getCrtUser() );
        mmnnMmoo00000300.setLastUser( dto.getLastUser() );
        mmnnMmoo00000300.setOwner( dto.getOwner() );
        mmnnMmoo00000300.setRecEnt( dto.getRecEnt() );
        mmnnMmoo00000300.setRecOU( dto.getRecOU() );
        mmnnMmoo00000300.setRecSec( dto.getRecSec() );
        mmnnMmoo00000300.setCntID( dto.getCntID() );
        mmnnMmoo00000300.setRecID( dto.getRecID() );

        return mmnnMmoo00000300;
    }

    @Override
    public void updateFromDTO(MmnnMmoo00000300DTO dto, MmnnMmoo00000300 entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getMnORef() != null ) {
            entity.setMnORef( dto.getMnORef() );
        }
        if ( dto.getOprID() != null ) {
            entity.setOprID( dto.getOprID() );
        }
        if ( dto.getNextOprID() != null ) {
            entity.setNextOprID( dto.getNextOprID() );
        }
        if ( dto.getRouID() != null ) {
            entity.setRouID( dto.getRouID() );
        }
        if ( dto.getNextRouID() != null ) {
            entity.setNextRouID( dto.getNextRouID() );
        }
        if ( dto.getPrdRef() != null ) {
            entity.setPrdRef( dto.getPrdRef() );
        }
        if ( dto.getPrdRefOrg() != null ) {
            entity.setPrdRefOrg( dto.getPrdRefOrg() );
        }
        if ( dto.getNComp() != null ) {
            entity.setNComp( dto.getNComp() );
        }
        if ( dto.getPrdRefDst() != null ) {
            entity.setPrdRefDst( dto.getPrdRefDst() );
        }
        if ( dto.getPrdNameDst() != null ) {
            entity.setPrdNameDst( dto.getPrdNameDst() );
        }
        if ( dto.getRouRef() != null ) {
            entity.setRouRef( dto.getRouRef() );
        }
        if ( dto.getOOrder() != null ) {
            entity.setOOrder( dto.getOOrder() );
        }
        if ( dto.getWrkRef() != null ) {
            entity.setWrkRef( dto.getWrkRef() );
        }
        if ( dto.getOprRef() != null ) {
            entity.setOprRef( dto.getOprRef() );
        }
        if ( dto.getPPriority() != null ) {
            entity.setPPriority( dto.getPPriority() );
        }
        if ( dto.getOrigin() != null ) {
            entity.setOrigin( dto.getOrigin() );
        }
        if ( dto.getOrdRef() != null ) {
            entity.setOrdRef( dto.getOrdRef() );
        }
        if ( dto.getOLineNum() != null ) {
            entity.setOLineNum( dto.getOLineNum() );
        }
        if ( dto.getCusRef() != null ) {
            entity.setCusRef( dto.getCusRef() );
        }
        if ( dto.getCusName() != null ) {
            entity.setCusName( dto.getCusName() );
        }
        if ( dto.getCDate() != null ) {
            entity.setCDate( dto.getCDate() );
        }
        if ( dto.getRDate() != null ) {
            entity.setRDate( dto.getRDate() );
        }
        if ( dto.getIsEnd() != null ) {
            entity.setIsEnd( dto.getIsEnd() );
        }
        if ( dto.getUCtName() != null ) {
            entity.setUCtName( dto.getUCtName() );
        }
        if ( dto.getUntName() != null ) {
            entity.setUntName( dto.getUntName() );
        }
        if ( dto.getQuantity() != null ) {
            entity.setQuantity( dto.getQuantity() );
        }
        if ( dto.getAQ() != null ) {
            entity.setAQ( dto.getAQ() );
        }
        if ( dto.getMQ() != null ) {
            entity.setMQ( dto.getMQ() );
        }
        if ( dto.getLQ() != null ) {
            entity.setLQ( dto.getLQ() );
        }
        if ( dto.getToOutQ() != null ) {
            entity.setToOutQ( dto.getToOutQ() );
        }
        if ( dto.getOutQ() != null ) {
            entity.setOutQ( dto.getOutQ() );
        }
        if ( dto.getMState() != null ) {
            entity.setMState( dto.getMState() );
        }
        if ( dto.getRQ() != null ) {
            entity.setRQ( dto.getRQ() );
        }
        if ( dto.getOState() != null ) {
            entity.setOState( dto.getOState() );
        }
        if ( dto.getEDuration() != null ) {
            entity.setEDuration( dto.getEDuration() );
        }
        if ( dto.getIOrder() != null ) {
            entity.setIOrder( dto.getIOrder() );
        }
        if ( dto.getSDate() != null ) {
            entity.setSDate( dto.getSDate() );
        }
        if ( dto.getEDate() != null ) {
            entity.setEDate( dto.getEDate() );
        }
        if ( dto.getPartialSDate() != null ) {
            entity.setPartialSDate( dto.getPartialSDate() );
        }
        if ( dto.getESDate() != null ) {
            entity.setESDate( dto.getESDate() );
        }
        if ( dto.getEEDate() != null ) {
            entity.setEEDate( dto.getEEDate() );
        }
        if ( dto.getDescrip() != null ) {
            entity.setDescrip( dto.getDescrip() );
        }
        if ( dto.getStdTime() != null ) {
            entity.setStdTime( dto.getStdTime() );
        }
        if ( dto.getRunTime() != null ) {
            entity.setRunTime( dto.getRunTime() );
        }
        if ( dto.getCurTime() != null ) {
            entity.setCurTime( dto.getCurTime() );
        }
        if ( dto.getWrkTime() != null ) {
            entity.setWrkTime( dto.getWrkTime() );
        }
        if ( dto.getWrkCap() != null ) {
            entity.setWrkCap( dto.getWrkCap() );
        }
        if ( dto.getStdCostU() != null ) {
            entity.setStdCostU( dto.getStdCostU() );
        }
        if ( dto.getCurCostU() != null ) {
            entity.setCurCostU( dto.getCurCostU() );
        }
        if ( dto.getStdCostT() != null ) {
            entity.setStdCostT( dto.getStdCostT() );
        }
        if ( dto.getCurCostT() != null ) {
            entity.setCurCostT( dto.getCurCostT() );
        }
        if ( dto.getMainOpr() != null ) {
            entity.setMainOpr( dto.getMainOpr() );
        }
        if ( dto.getParallelOpr() != null ) {
            entity.setParallelOpr( dto.getParallelOpr() );
        }
        if ( dto.getWrhRefSM() != null ) {
            entity.setWrhRefSM( dto.getWrhRefSM() );
        }
        if ( dto.getLineNumSM() != null ) {
            entity.setLineNumSM( dto.getLineNumSM() );
        }
        if ( dto.getOutOrdRef() != null ) {
            entity.setOutOrdRef( dto.getOutOrdRef() );
        }
        if ( dto.getOutLineNum() != null ) {
            entity.setOutLineNum( dto.getOutLineNum() );
        }
        if ( dto.getSuppRef() != null ) {
            entity.setSuppRef( dto.getSuppRef() );
        }
        if ( dto.getSuppName() != null ) {
            entity.setSuppName( dto.getSuppName() );
        }
        if ( dto.getCurName() != null ) {
            entity.setCurName( dto.getCurName() );
        }
        if ( dto.getTarRef() != null ) {
            entity.setTarRef( dto.getTarRef() );
        }
        if ( dto.getMinQuan() != null ) {
            entity.setMinQuan( dto.getMinQuan() );
        }
        if ( dto.getIsOutsourcing() != null ) {
            entity.setIsOutsourcing( dto.getIsOutsourcing() );
        }
        if ( dto.getCurCostA() != null ) {
            entity.setCurCostA( dto.getCurCostA() );
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
        if ( dto.getMainOriginTypeFilter() != null ) {
            entity.setMainOriginTypeFilter( dto.getMainOriginTypeFilter() );
        }
        if ( dto.getMainPackage() != null ) {
            entity.setMainPackage( dto.getMainPackage() );
        }
        if ( dto.getMainPackageName() != null ) {
            entity.setMainPackageName( dto.getMainPackageName() );
        }
        if ( dto.getDIS_PGroup() != null ) {
            entity.setDIS_PGroup( dto.getDIS_PGroup() );
        }
        if ( dto.getDIS_MatRef() != null ) {
            entity.setDIS_MatRef( dto.getDIS_MatRef() );
        }
        if ( dto.getDIS_Thickness() != null ) {
            entity.setDIS_Thickness( dto.getDIS_Thickness() );
        }
        if ( dto.getDIS_JobRef() != null ) {
            entity.setDIS_JobRef( dto.getDIS_JobRef() );
        }
        if ( dto.getDIS_FPosition() != null ) {
            entity.setDIS_FPosition( dto.getDIS_FPosition() );
        }
        if ( dto.getDIS_NQ() != null ) {
            entity.setDIS_NQ( dto.getDIS_NQ() );
        }
        if ( dto.getDIS_IsChanged() != null ) {
            entity.setDIS_IsChanged( dto.getDIS_IsChanged() );
        }
        if ( dto.getDIS_IsDuct() != null ) {
            entity.setDIS_IsDuct( dto.getDIS_IsDuct() );
        }
        if ( dto.getDIS_Is2DSOp() != null ) {
            entity.setDIS_Is2DSOp( dto.getDIS_Is2DSOp() );
        }
        if ( dto.getDIS_Is3DSOp() != null ) {
            entity.setDIS_Is3DSOp( dto.getDIS_Is3DSOp() );
        }
        if ( dto.getDIS_IsQuote() != null ) {
            entity.setDIS_IsQuote( dto.getDIS_IsQuote() );
        }
        if ( dto.getDIS_WrkCfg() != null ) {
            entity.setDIS_WrkCfg( dto.getDIS_WrkCfg() );
        }
        if ( dto.getWosStatus() != null ) {
            entity.setWosStatus( dto.getWosStatus() );
        }
        if ( dto.getDIS_NonCuttingJob() != null ) {
            entity.setDIS_NonCuttingJob( dto.getDIS_NonCuttingJob() );
        }
        if ( dto.getDIS_OrgOprID() != null ) {
            entity.setDIS_OrgOprID( dto.getDIS_OrgOprID() );
        }
        if ( dto.getDIS_MStateCloudStatus() != null ) {
            entity.setDIS_MStateCloudStatus( dto.getDIS_MStateCloudStatus() );
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
