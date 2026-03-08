package com.ao.platform.core.lantek.convert;

import com.ao.platform.core.lantek.dto.DisNestNest00000100DTO;
import com.ao.platform.core.lantek.dto.DisNestNest00000100PageQuery;
import com.ao.platform.core.lantek.entity.DisNestNest00000100;
import com.ao.platform.core.lantek.vo.DisNestNest00000100VO;
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
public class DisNestNest00000100ConvertImpl implements DisNestNest00000100Convert {

    @Override
    public DisNestNest00000100VO toVO(DisNestNest00000100 entity) {
        if ( entity == null ) {
            return null;
        }

        DisNestNest00000100VO disNestNest00000100VO = new DisNestNest00000100VO();

        disNestNest00000100VO.setNstRef( entity.getNstRef() );
        disNestNest00000100VO.setNestMainRef( entity.getNestMainRef() );
        disNestNest00000100VO.setNestPrevRef( entity.getNestPrevRef() );
        disNestNest00000100VO.setNstPRef( entity.getNstPRef() );
        disNestNest00000100VO.setCopyIndx( entity.getCopyIndx() );
        disNestNest00000100VO.setWrkRef( entity.getWrkRef() );
        disNestNest00000100VO.setOprRef( entity.getOprRef() );
        disNestNest00000100VO.setNOrder( entity.getNOrder() );
        disNestNest00000100VO.setCDate( entity.getCDate() );
        disNestNest00000100VO.setJobRef( entity.getJobRef() );
        disNestNest00000100VO.setNCategory( entity.getNCategory() );
        disNestNest00000100VO.setMState( entity.getMState() );
        disNestNest00000100VO.setWosStatus( entity.getWosStatus() );
        disNestNest00000100VO.setShtRef( entity.getShtRef() );
        disNestNest00000100VO.setShtRefOrg( entity.getShtRefOrg() );
        disNestNest00000100VO.setRealSht( entity.getRealSht() );
        disNestNest00000100VO.setMatRef( entity.getMatRef() );
        disNestNest00000100VO.setSLength( entity.getSLength() );
        disNestNest00000100VO.setSWidth( entity.getSWidth() );
        disNestNest00000100VO.setSThickness( entity.getSThickness() );
        disNestNest00000100VO.setSArea( entity.getSArea() );
        disNestNest00000100VO.setSUArea( entity.getSUArea() );
        disNestNest00000100VO.setSWeight( entity.getSWeight() );
        disNestNest00000100VO.setSUWeight( entity.getSUWeight() );
        disNestNest00000100VO.setSXMax( entity.getSXMax() );
        disNestNest00000100VO.setSYMax( entity.getSYMax() );
        disNestNest00000100VO.setSPriority( entity.getSPriority() );
        disNestNest00000100VO.setSProfit( entity.getSProfit() );
        disNestNest00000100VO.setSProfitS( entity.getSProfitS() );
        disNestNest00000100VO.setSMSQuant( entity.getSMSQuant() );
        disNestNest00000100VO.setETime( entity.getETime() );
        disNestNest00000100VO.setQuantity( entity.getQuantity() );
        disNestNest00000100VO.setRTime( entity.getRTime() );
        disNestNest00000100VO.setSDate( entity.getSDate() );
        disNestNest00000100VO.setEDate( entity.getEDate() );
        disNestNest00000100VO.setUData1( entity.getUData1() );
        disNestNest00000100VO.setUData2( entity.getUData2() );
        disNestNest00000100VO.setUData3( entity.getUData3() );
        disNestNest00000100VO.setPartialSDate( entity.getPartialSDate() );
        disNestNest00000100VO.setEDuration( entity.getEDuration() );
        disNestNest00000100VO.setIsCopy( entity.getIsCopy() );
        disNestNest00000100VO.setMltPrgRef( entity.getMltPrgRef() );
        disNestNest00000100VO.setMltPrgNstRef( entity.getMltPrgNstRef() );
        disNestNest00000100VO.setDescrip( entity.getDescrip() );
        disNestNest00000100VO.setToPallet( entity.getToPallet() );
        disNestNest00000100VO.setIsQuote( entity.getIsQuote() );
        disNestNest00000100VO.setRealTimeUpdated( entity.getRealTimeUpdated() );
        disNestNest00000100VO.setNstCpyRef( entity.getNstCpyRef() );
        disNestNest00000100VO.setUnitaryNest( entity.getUnitaryNest() );
        disNestNest00000100VO.setVar1( entity.getVar1() );
        disNestNest00000100VO.setVar2( entity.getVar2() );
        disNestNest00000100VO.setVar3( entity.getVar3() );
        disNestNest00000100VO.setVar4( entity.getVar4() );
        disNestNest00000100VO.setVar5( entity.getVar5() );
        disNestNest00000100VO.setCamLastDate( entity.getCamLastDate() );
        disNestNest00000100VO.setWrkCfg( entity.getWrkCfg() );
        disNestNest00000100VO.setRequiredDate( entity.getRequiredDate() );
        disNestNest00000100VO.setScheduledStart( entity.getScheduledStart() );
        disNestNest00000100VO.setPriority( entity.getPriority() );
        disNestNest00000100VO.setName( entity.getName() );
        disNestNest00000100VO.setMStateCloudStatus( entity.getMStateCloudStatus() );
        disNestNest00000100VO.setCuttingStatus( entity.getCuttingStatus() );
        disNestNest00000100VO.setCutQuantity( entity.getCutQuantity() );
        disNestNest00000100VO.setExternalIndex( entity.getExternalIndex() );
        disNestNest00000100VO.setAutomatic( entity.getAutomatic() );
        disNestNest00000100VO.setRecState( entity.getRecState() );
        disNestNest00000100VO.setCrtDate( entity.getCrtDate() );
        disNestNest00000100VO.setLastDate( entity.getLastDate() );
        disNestNest00000100VO.setCrtUser( entity.getCrtUser() );
        disNestNest00000100VO.setLastUser( entity.getLastUser() );
        disNestNest00000100VO.setOwner( entity.getOwner() );
        disNestNest00000100VO.setRecEnt( entity.getRecEnt() );
        disNestNest00000100VO.setRecOU( entity.getRecOU() );
        disNestNest00000100VO.setRecSec( entity.getRecSec() );
        disNestNest00000100VO.setCntID( entity.getCntID() );
        disNestNest00000100VO.setRecID( entity.getRecID() );

        return disNestNest00000100VO;
    }

    @Override
    public List<DisNestNest00000100VO> toVOList(List<DisNestNest00000100> list) {
        if ( list == null ) {
            return null;
        }

        List<DisNestNest00000100VO> list1 = new ArrayList<DisNestNest00000100VO>( list.size() );
        for ( DisNestNest00000100 disNestNest00000100 : list ) {
            list1.add( toVO( disNestNest00000100 ) );
        }

        return list1;
    }

    @Override
    public DisNestNest00000100 toEntity(DisNestNest00000100DTO dto) {
        if ( dto == null ) {
            return null;
        }

        DisNestNest00000100 disNestNest00000100 = new DisNestNest00000100();

        disNestNest00000100.setNstRef( dto.getNstRef() );
        disNestNest00000100.setNestMainRef( dto.getNestMainRef() );
        disNestNest00000100.setNestPrevRef( dto.getNestPrevRef() );
        disNestNest00000100.setNstPRef( dto.getNstPRef() );
        disNestNest00000100.setCopyIndx( dto.getCopyIndx() );
        disNestNest00000100.setWrkRef( dto.getWrkRef() );
        disNestNest00000100.setOprRef( dto.getOprRef() );
        disNestNest00000100.setNOrder( dto.getNOrder() );
        disNestNest00000100.setCDate( dto.getCDate() );
        disNestNest00000100.setJobRef( dto.getJobRef() );
        disNestNest00000100.setNCategory( dto.getNCategory() );
        disNestNest00000100.setMState( dto.getMState() );
        disNestNest00000100.setWosStatus( dto.getWosStatus() );
        disNestNest00000100.setShtRef( dto.getShtRef() );
        disNestNest00000100.setShtRefOrg( dto.getShtRefOrg() );
        disNestNest00000100.setRealSht( dto.getRealSht() );
        disNestNest00000100.setMatRef( dto.getMatRef() );
        disNestNest00000100.setSLength( dto.getSLength() );
        disNestNest00000100.setSWidth( dto.getSWidth() );
        disNestNest00000100.setSThickness( dto.getSThickness() );
        disNestNest00000100.setSArea( dto.getSArea() );
        disNestNest00000100.setSUArea( dto.getSUArea() );
        disNestNest00000100.setSWeight( dto.getSWeight() );
        disNestNest00000100.setSUWeight( dto.getSUWeight() );
        disNestNest00000100.setSXMax( dto.getSXMax() );
        disNestNest00000100.setSYMax( dto.getSYMax() );
        disNestNest00000100.setSPriority( dto.getSPriority() );
        disNestNest00000100.setSProfit( dto.getSProfit() );
        disNestNest00000100.setSProfitS( dto.getSProfitS() );
        disNestNest00000100.setSMSQuant( dto.getSMSQuant() );
        disNestNest00000100.setETime( dto.getETime() );
        disNestNest00000100.setQuantity( dto.getQuantity() );
        disNestNest00000100.setRTime( dto.getRTime() );
        disNestNest00000100.setSDate( dto.getSDate() );
        disNestNest00000100.setEDate( dto.getEDate() );
        disNestNest00000100.setUData1( dto.getUData1() );
        disNestNest00000100.setUData2( dto.getUData2() );
        disNestNest00000100.setUData3( dto.getUData3() );
        disNestNest00000100.setPartialSDate( dto.getPartialSDate() );
        disNestNest00000100.setEDuration( dto.getEDuration() );
        disNestNest00000100.setIsCopy( dto.getIsCopy() );
        disNestNest00000100.setMltPrgRef( dto.getMltPrgRef() );
        disNestNest00000100.setMltPrgNstRef( dto.getMltPrgNstRef() );
        disNestNest00000100.setDescrip( dto.getDescrip() );
        disNestNest00000100.setToPallet( dto.getToPallet() );
        disNestNest00000100.setIsQuote( dto.getIsQuote() );
        disNestNest00000100.setRealTimeUpdated( dto.getRealTimeUpdated() );
        disNestNest00000100.setNstCpyRef( dto.getNstCpyRef() );
        disNestNest00000100.setUnitaryNest( dto.getUnitaryNest() );
        disNestNest00000100.setVar1( dto.getVar1() );
        disNestNest00000100.setVar2( dto.getVar2() );
        disNestNest00000100.setVar3( dto.getVar3() );
        disNestNest00000100.setVar4( dto.getVar4() );
        disNestNest00000100.setVar5( dto.getVar5() );
        disNestNest00000100.setCamLastDate( dto.getCamLastDate() );
        disNestNest00000100.setWrkCfg( dto.getWrkCfg() );
        disNestNest00000100.setRequiredDate( dto.getRequiredDate() );
        disNestNest00000100.setScheduledStart( dto.getScheduledStart() );
        disNestNest00000100.setPriority( dto.getPriority() );
        disNestNest00000100.setName( dto.getName() );
        disNestNest00000100.setMStateCloudStatus( dto.getMStateCloudStatus() );
        disNestNest00000100.setCuttingStatus( dto.getCuttingStatus() );
        disNestNest00000100.setCutQuantity( dto.getCutQuantity() );
        disNestNest00000100.setExternalIndex( dto.getExternalIndex() );
        disNestNest00000100.setAutomatic( dto.getAutomatic() );
        disNestNest00000100.setRecState( dto.getRecState() );
        disNestNest00000100.setCrtDate( dto.getCrtDate() );
        disNestNest00000100.setLastDate( dto.getLastDate() );
        disNestNest00000100.setCrtUser( dto.getCrtUser() );
        disNestNest00000100.setLastUser( dto.getLastUser() );
        disNestNest00000100.setOwner( dto.getOwner() );
        disNestNest00000100.setRecEnt( dto.getRecEnt() );
        disNestNest00000100.setRecOU( dto.getRecOU() );
        disNestNest00000100.setRecSec( dto.getRecSec() );
        disNestNest00000100.setCntID( dto.getCntID() );
        disNestNest00000100.setRecID( dto.getRecID() );

        return disNestNest00000100;
    }

    @Override
    public DisNestNest00000100 toEntity(DisNestNest00000100PageQuery dto) {
        if ( dto == null ) {
            return null;
        }

        DisNestNest00000100 disNestNest00000100 = new DisNestNest00000100();

        disNestNest00000100.setNstRef( dto.getNstRef() );
        disNestNest00000100.setNestMainRef( dto.getNestMainRef() );
        disNestNest00000100.setNestPrevRef( dto.getNestPrevRef() );
        disNestNest00000100.setNstPRef( dto.getNstPRef() );
        disNestNest00000100.setCopyIndx( dto.getCopyIndx() );
        disNestNest00000100.setWrkRef( dto.getWrkRef() );
        disNestNest00000100.setOprRef( dto.getOprRef() );
        disNestNest00000100.setNOrder( dto.getNOrder() );
        disNestNest00000100.setCDate( dto.getCDate() );
        disNestNest00000100.setJobRef( dto.getJobRef() );
        disNestNest00000100.setNCategory( dto.getNCategory() );
        disNestNest00000100.setMState( dto.getMState() );
        disNestNest00000100.setWosStatus( dto.getWosStatus() );
        disNestNest00000100.setShtRef( dto.getShtRef() );
        disNestNest00000100.setShtRefOrg( dto.getShtRefOrg() );
        disNestNest00000100.setRealSht( dto.getRealSht() );
        disNestNest00000100.setMatRef( dto.getMatRef() );
        disNestNest00000100.setSLength( dto.getSLength() );
        disNestNest00000100.setSWidth( dto.getSWidth() );
        disNestNest00000100.setSThickness( dto.getSThickness() );
        disNestNest00000100.setSArea( dto.getSArea() );
        disNestNest00000100.setSUArea( dto.getSUArea() );
        disNestNest00000100.setSWeight( dto.getSWeight() );
        disNestNest00000100.setSUWeight( dto.getSUWeight() );
        disNestNest00000100.setSXMax( dto.getSXMax() );
        disNestNest00000100.setSYMax( dto.getSYMax() );
        disNestNest00000100.setSPriority( dto.getSPriority() );
        disNestNest00000100.setSProfit( dto.getSProfit() );
        disNestNest00000100.setSProfitS( dto.getSProfitS() );
        disNestNest00000100.setSMSQuant( dto.getSMSQuant() );
        disNestNest00000100.setETime( dto.getETime() );
        disNestNest00000100.setQuantity( dto.getQuantity() );
        disNestNest00000100.setRTime( dto.getRTime() );
        disNestNest00000100.setSDate( dto.getSDate() );
        disNestNest00000100.setEDate( dto.getEDate() );
        disNestNest00000100.setUData1( dto.getUData1() );
        disNestNest00000100.setUData2( dto.getUData2() );
        disNestNest00000100.setUData3( dto.getUData3() );
        disNestNest00000100.setPartialSDate( dto.getPartialSDate() );
        disNestNest00000100.setEDuration( dto.getEDuration() );
        disNestNest00000100.setIsCopy( dto.getIsCopy() );
        disNestNest00000100.setMltPrgRef( dto.getMltPrgRef() );
        disNestNest00000100.setMltPrgNstRef( dto.getMltPrgNstRef() );
        disNestNest00000100.setDescrip( dto.getDescrip() );
        disNestNest00000100.setToPallet( dto.getToPallet() );
        disNestNest00000100.setIsQuote( dto.getIsQuote() );
        disNestNest00000100.setRealTimeUpdated( dto.getRealTimeUpdated() );
        disNestNest00000100.setNstCpyRef( dto.getNstCpyRef() );
        disNestNest00000100.setUnitaryNest( dto.getUnitaryNest() );
        disNestNest00000100.setVar1( dto.getVar1() );
        disNestNest00000100.setVar2( dto.getVar2() );
        disNestNest00000100.setVar3( dto.getVar3() );
        disNestNest00000100.setVar4( dto.getVar4() );
        disNestNest00000100.setVar5( dto.getVar5() );
        disNestNest00000100.setCamLastDate( dto.getCamLastDate() );
        disNestNest00000100.setWrkCfg( dto.getWrkCfg() );
        disNestNest00000100.setRequiredDate( dto.getRequiredDate() );
        disNestNest00000100.setScheduledStart( dto.getScheduledStart() );
        disNestNest00000100.setPriority( dto.getPriority() );
        disNestNest00000100.setName( dto.getName() );
        disNestNest00000100.setMStateCloudStatus( dto.getMStateCloudStatus() );
        disNestNest00000100.setCuttingStatus( dto.getCuttingStatus() );
        disNestNest00000100.setCutQuantity( dto.getCutQuantity() );
        disNestNest00000100.setExternalIndex( dto.getExternalIndex() );
        disNestNest00000100.setAutomatic( dto.getAutomatic() );
        disNestNest00000100.setRecState( dto.getRecState() );
        disNestNest00000100.setCrtDate( dto.getCrtDate() );
        disNestNest00000100.setLastDate( dto.getLastDate() );
        disNestNest00000100.setCrtUser( dto.getCrtUser() );
        disNestNest00000100.setLastUser( dto.getLastUser() );
        disNestNest00000100.setOwner( dto.getOwner() );
        disNestNest00000100.setRecEnt( dto.getRecEnt() );
        disNestNest00000100.setRecOU( dto.getRecOU() );
        disNestNest00000100.setRecSec( dto.getRecSec() );
        disNestNest00000100.setCntID( dto.getCntID() );
        disNestNest00000100.setRecID( dto.getRecID() );

        return disNestNest00000100;
    }

    @Override
    public void updateFromDTO(DisNestNest00000100DTO dto, DisNestNest00000100 entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getNstRef() != null ) {
            entity.setNstRef( dto.getNstRef() );
        }
        if ( dto.getNestMainRef() != null ) {
            entity.setNestMainRef( dto.getNestMainRef() );
        }
        if ( dto.getNestPrevRef() != null ) {
            entity.setNestPrevRef( dto.getNestPrevRef() );
        }
        if ( dto.getNstPRef() != null ) {
            entity.setNstPRef( dto.getNstPRef() );
        }
        if ( dto.getCopyIndx() != null ) {
            entity.setCopyIndx( dto.getCopyIndx() );
        }
        if ( dto.getWrkRef() != null ) {
            entity.setWrkRef( dto.getWrkRef() );
        }
        if ( dto.getOprRef() != null ) {
            entity.setOprRef( dto.getOprRef() );
        }
        if ( dto.getNOrder() != null ) {
            entity.setNOrder( dto.getNOrder() );
        }
        if ( dto.getCDate() != null ) {
            entity.setCDate( dto.getCDate() );
        }
        if ( dto.getJobRef() != null ) {
            entity.setJobRef( dto.getJobRef() );
        }
        if ( dto.getNCategory() != null ) {
            entity.setNCategory( dto.getNCategory() );
        }
        if ( dto.getMState() != null ) {
            entity.setMState( dto.getMState() );
        }
        if ( dto.getWosStatus() != null ) {
            entity.setWosStatus( dto.getWosStatus() );
        }
        if ( dto.getShtRef() != null ) {
            entity.setShtRef( dto.getShtRef() );
        }
        if ( dto.getShtRefOrg() != null ) {
            entity.setShtRefOrg( dto.getShtRefOrg() );
        }
        if ( dto.getRealSht() != null ) {
            entity.setRealSht( dto.getRealSht() );
        }
        if ( dto.getMatRef() != null ) {
            entity.setMatRef( dto.getMatRef() );
        }
        if ( dto.getSLength() != null ) {
            entity.setSLength( dto.getSLength() );
        }
        if ( dto.getSWidth() != null ) {
            entity.setSWidth( dto.getSWidth() );
        }
        if ( dto.getSThickness() != null ) {
            entity.setSThickness( dto.getSThickness() );
        }
        if ( dto.getSArea() != null ) {
            entity.setSArea( dto.getSArea() );
        }
        if ( dto.getSUArea() != null ) {
            entity.setSUArea( dto.getSUArea() );
        }
        if ( dto.getSWeight() != null ) {
            entity.setSWeight( dto.getSWeight() );
        }
        if ( dto.getSUWeight() != null ) {
            entity.setSUWeight( dto.getSUWeight() );
        }
        if ( dto.getSXMax() != null ) {
            entity.setSXMax( dto.getSXMax() );
        }
        if ( dto.getSYMax() != null ) {
            entity.setSYMax( dto.getSYMax() );
        }
        if ( dto.getSPriority() != null ) {
            entity.setSPriority( dto.getSPriority() );
        }
        if ( dto.getSProfit() != null ) {
            entity.setSProfit( dto.getSProfit() );
        }
        if ( dto.getSProfitS() != null ) {
            entity.setSProfitS( dto.getSProfitS() );
        }
        if ( dto.getSMSQuant() != null ) {
            entity.setSMSQuant( dto.getSMSQuant() );
        }
        if ( dto.getETime() != null ) {
            entity.setETime( dto.getETime() );
        }
        if ( dto.getQuantity() != null ) {
            entity.setQuantity( dto.getQuantity() );
        }
        if ( dto.getRTime() != null ) {
            entity.setRTime( dto.getRTime() );
        }
        if ( dto.getSDate() != null ) {
            entity.setSDate( dto.getSDate() );
        }
        if ( dto.getEDate() != null ) {
            entity.setEDate( dto.getEDate() );
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
        if ( dto.getPartialSDate() != null ) {
            entity.setPartialSDate( dto.getPartialSDate() );
        }
        if ( dto.getEDuration() != null ) {
            entity.setEDuration( dto.getEDuration() );
        }
        if ( dto.getIsCopy() != null ) {
            entity.setIsCopy( dto.getIsCopy() );
        }
        if ( dto.getMltPrgRef() != null ) {
            entity.setMltPrgRef( dto.getMltPrgRef() );
        }
        if ( dto.getMltPrgNstRef() != null ) {
            entity.setMltPrgNstRef( dto.getMltPrgNstRef() );
        }
        if ( dto.getDescrip() != null ) {
            entity.setDescrip( dto.getDescrip() );
        }
        if ( dto.getToPallet() != null ) {
            entity.setToPallet( dto.getToPallet() );
        }
        if ( dto.getIsQuote() != null ) {
            entity.setIsQuote( dto.getIsQuote() );
        }
        if ( dto.getRealTimeUpdated() != null ) {
            entity.setRealTimeUpdated( dto.getRealTimeUpdated() );
        }
        if ( dto.getNstCpyRef() != null ) {
            entity.setNstCpyRef( dto.getNstCpyRef() );
        }
        if ( dto.getUnitaryNest() != null ) {
            entity.setUnitaryNest( dto.getUnitaryNest() );
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
        if ( dto.getCamLastDate() != null ) {
            entity.setCamLastDate( dto.getCamLastDate() );
        }
        if ( dto.getWrkCfg() != null ) {
            entity.setWrkCfg( dto.getWrkCfg() );
        }
        if ( dto.getRequiredDate() != null ) {
            entity.setRequiredDate( dto.getRequiredDate() );
        }
        if ( dto.getScheduledStart() != null ) {
            entity.setScheduledStart( dto.getScheduledStart() );
        }
        if ( dto.getPriority() != null ) {
            entity.setPriority( dto.getPriority() );
        }
        if ( dto.getName() != null ) {
            entity.setName( dto.getName() );
        }
        if ( dto.getMStateCloudStatus() != null ) {
            entity.setMStateCloudStatus( dto.getMStateCloudStatus() );
        }
        if ( dto.getCuttingStatus() != null ) {
            entity.setCuttingStatus( dto.getCuttingStatus() );
        }
        if ( dto.getCutQuantity() != null ) {
            entity.setCutQuantity( dto.getCutQuantity() );
        }
        if ( dto.getExternalIndex() != null ) {
            entity.setExternalIndex( dto.getExternalIndex() );
        }
        if ( dto.getAutomatic() != null ) {
            entity.setAutomatic( dto.getAutomatic() );
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
