package com.zhurong.platform.custom.convert;

import com.zhurong.platform.core.lantek.dto.PprrPprr00000100DTO;
import com.zhurong.platform.core.lantek.dto.PprrPprr00000100PageQuery;
import com.zhurong.platform.core.lantek.vo.PprrPprr00000100VO;
import com.zhurong.platform.custom.entity.PprrPprr00000100;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-06-14T12:06:49+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.10 (Eclipse Adoptium)"
)
@Component
public class PprrPprr00000100ConvertImpl implements PprrPprr00000100Convert {

    @Override
    public PprrPprr00000100VO toVO(PprrPprr00000100 entity) {
        if ( entity == null ) {
            return null;
        }

        PprrPprr00000100VO pprrPprr00000100VO = new PprrPprr00000100VO();

        pprrPprr00000100VO.setPrdRef( entity.getPrdRef() );
        pprrPprr00000100VO.setPrdName( entity.getPrdName() );
        pprrPprr00000100VO.setPrdIntName( entity.getPrdIntName() );
        pprrPprr00000100VO.setPGroup( entity.getPGroup() );
        pprrPprr00000100VO.setBarCode( entity.getBarCode() );
        pprrPprr00000100VO.setIsActive( entity.getIsActive() );
        pprrPprr00000100VO.setRealPrd( entity.getRealPrd() );
        pprrPprr00000100VO.setAssembly( entity.getAssembly() );
        pprrPprr00000100VO.setForSale( entity.getForSale() );
        pprrPprr00000100VO.setPType( entity.getPType() );
        pprrPprr00000100VO.setCstRanges( entity.getCstRanges() );
        pprrPprr00000100VO.setFixPrice( entity.getFixPrice() );
        pprrPprr00000100VO.setStdCost( entity.getStdCost() );
        pprrPprr00000100VO.setCurCost( entity.getCurCost() );
        pprrPprr00000100VO.setPrcMethod( entity.getPrcMethod() );
        pprrPprr00000100VO.setCstMethod( entity.getCstMethod() );
        pprrPprr00000100VO.setUCtName( entity.getUCtName() );
        pprrPprr00000100VO.setUntName( entity.getUntName() );
        pprrPprr00000100VO.setCurQuan( entity.getCurQuan() );
        pprrPprr00000100VO.setCDate( entity.getCDate() );
        pprrPprr00000100VO.setKeyWords( entity.getKeyWords() );
        pprrPprr00000100VO.setDescrip( entity.getDescrip() );
        pprrPprr00000100VO.setImage( entity.getImage() );
        pprrPprr00000100VO.setWeight( entity.getWeight() );
        pprrPprr00000100VO.setLeadTime( entity.getLeadTime() );
        pprrPprr00000100VO.setLeadUCtName( entity.getLeadUCtName() );
        pprrPprr00000100VO.setLeadUntName( entity.getLeadUntName() );
        pprrPprr00000100VO.setLeadUpdateMethod( entity.getLeadUpdateMethod() );
        pprrPprr00000100VO.setCommCode( entity.getCommCode() );
        pprrPprr00000100VO.setOCountry( entity.getOCountry() );
        pprrPprr00000100VO.setCGroup( entity.getCGroup() );
        pprrPprr00000100VO.setAccConItemRef( entity.getAccConItemRef() );
        pprrPprr00000100VO.setGLS_VarRef1( entity.getGLS_VarRef1() );
        pprrPprr00000100VO.setGLS_UntName1( entity.getGLS_UntName1() );
        pprrPprr00000100VO.setGLS_VarRef2( entity.getGLS_VarRef2() );
        pprrPprr00000100VO.setGLS_UntName2( entity.getGLS_UntName2() );
        pprrPprr00000100VO.setGLS_VarRef3( entity.getGLS_VarRef3() );
        pprrPprr00000100VO.setGLS_UntName3( entity.getGLS_UntName3() );
        pprrPprr00000100VO.setGLS_VarRef4( entity.getGLS_VarRef4() );
        pprrPprr00000100VO.setGLS_UntName4( entity.getGLS_UntName4() );
        pprrPprr00000100VO.setGLS_VarRef5( entity.getGLS_VarRef5() );
        pprrPprr00000100VO.setGLS_UntName5( entity.getGLS_UntName5() );
        pprrPprr00000100VO.setGLS_UCtName1( entity.getGLS_UCtName1() );
        pprrPprr00000100VO.setGLS_UCtName2( entity.getGLS_UCtName2() );
        pprrPprr00000100VO.setGLS_UCtName3( entity.getGLS_UCtName3() );
        pprrPprr00000100VO.setGLS_UCtName4( entity.getGLS_UCtName4() );
        pprrPprr00000100VO.setGLS_UCtName5( entity.getGLS_UCtName5() );
        pprrPprr00000100VO.setGLS_AtRNameSerial( entity.getGLS_AtRNameSerial() );
        pprrPprr00000100VO.setGLS_AtRNameBatch( entity.getGLS_AtRNameBatch() );
        pprrPprr00000100VO.setGLS_TblRef( entity.getGLS_TblRef() );
        pprrPprr00000100VO.setDIS_MatRef( entity.getDIS_MatRef() );
        pprrPprr00000100VO.setDIS_Length( entity.getDIS_Length() );
        pprrPprr00000100VO.setDIS_Width( entity.getDIS_Width() );
        pprrPprr00000100VO.setDIS_Thickness( entity.getDIS_Thickness() );
        pprrPprr00000100VO.setDIS_Area( entity.getDIS_Area() );
        pprrPprr00000100VO.setDIS_CreationM( entity.getDIS_CreationM() );
        pprrPprr00000100VO.setDIS_IsCanal( entity.getDIS_IsCanal() );
        pprrPprr00000100VO.setDIS_ShtRef( entity.getDIS_ShtRef() );
        pprrPprr00000100VO.setDIS_Rotations( entity.getDIS_Rotations() );
        pprrPprr00000100VO.setDIS_FillerPart( entity.getDIS_FillerPart() );
        pprrPprr00000100VO.setDIS_CanQuote( entity.getDIS_CanQuote() );
        pprrPprr00000100VO.setDIS_Side( entity.getDIS_Side() );
        pprrPprr00000100VO.setDIS_CutPerim( entity.getDIS_CutPerim() );
        pprrPprr00000100VO.setDIS_MrkPerim( entity.getDIS_MrkPerim() );
        pprrPprr00000100VO.setDIS_ExtArea( entity.getDIS_ExtArea() );
        pprrPprr00000100VO.setDIS_RectArea( entity.getDIS_RectArea() );
        pprrPprr00000100VO.setDIS_TypeArea( entity.getDIS_TypeArea() );
        pprrPprr00000100VO.setDIS_ExtWeight( entity.getDIS_ExtWeight() );
        pprrPprr00000100VO.setDIS_RectWeight( entity.getDIS_RectWeight() );
        pprrPprr00000100VO.setDIS_IsDuctPart( entity.getDIS_IsDuctPart() );
        pprrPprr00000100VO.setDIS_UData1_Prt( entity.getDIS_UData1_Prt() );
        pprrPprr00000100VO.setDIS_UData2_Prt( entity.getDIS_UData2_Prt() );
        pprrPprr00000100VO.setDIS_UData3_Prt( entity.getDIS_UData3_Prt() );
        pprrPprr00000100VO.setDIS_UData4_Prt( entity.getDIS_UData4_Prt() );
        pprrPprr00000100VO.setDIS_UData5_Prt( entity.getDIS_UData5_Prt() );
        pprrPprr00000100VO.setDIS_UData6_Prt( entity.getDIS_UData6_Prt() );
        pprrPprr00000100VO.setDIS_UData7_Prt( entity.getDIS_UData7_Prt() );
        pprrPprr00000100VO.setDIS_UData8_Prt( entity.getDIS_UData8_Prt() );
        pprrPprr00000100VO.setDIS_IsDraft( entity.getDIS_IsDraft() );
        pprrPprr00000100VO.setDIS_DuctDINCode( entity.getDIS_DuctDINCode() );
        pprrPprr00000100VO.setDIS_DuctDINRef( entity.getDIS_DuctDINRef() );
        pprrPprr00000100VO.setDIS_SeamL( entity.getDIS_SeamL() );
        pprrPprr00000100VO.setDIS_CnnL( entity.getDIS_CnnL() );
        pprrPprr00000100VO.setDIS_FPosition( entity.getDIS_FPosition() );
        pprrPprr00000100VO.setDIS_CArea( entity.getDIS_CArea() );
        pprrPprr00000100VO.setDIS_UMAX( entity.getDIS_UMAX() );
        pprrPprr00000100VO.setDIS_LMAX( entity.getDIS_LMAX() );
        pprrPprr00000100VO.setDIS_SMAX( entity.getDIS_SMAX() );
        pprrPprr00000100VO.setDIS_PrssIndex( entity.getDIS_PrssIndex() );
        pprrPprr00000100VO.setDIS_IsRemnant( entity.getDIS_IsRemnant() );
        pprrPprr00000100VO.setDIS_PrcRmntPrice( entity.getDIS_PrcRmntPrice() );
        pprrPprr00000100VO.setDIS_ScrpPrice( entity.getDIS_ScrpPrice() );
        pprrPprr00000100VO.setDIS_PrcScrpPrice( entity.getDIS_PrcScrpPrice() );
        pprrPprr00000100VO.setDIS_RPriority( entity.getDIS_RPriority() );
        pprrPprr00000100VO.setDIS_IsLocked( entity.getDIS_IsLocked() );
        pprrPprr00000100VO.setDIS_CamQuan( entity.getDIS_CamQuan() );
        pprrPprr00000100VO.setDIS_ShtRefOrg( entity.getDIS_ShtRefOrg() );
        pprrPprr00000100VO.setDIS_UData1_Sht( entity.getDIS_UData1_Sht() );
        pprrPprr00000100VO.setDIS_UData2_Sht( entity.getDIS_UData2_Sht() );
        pprrPprr00000100VO.setDIS_UData3_Sht( entity.getDIS_UData3_Sht() );
        pprrPprr00000100VO.setDIS_Price( entity.getDIS_Price() );
        pprrPprr00000100VO.setDIS_InProgress( entity.getDIS_InProgress() );
        pprrPprr00000100VO.setDIS_Factor( entity.getDIS_Factor() );
        pprrPprr00000100VO.setDIS_Vl( entity.getDIS_Vl() );
        pprrPprr00000100VO.setDIS_Vh( entity.getDIS_Vh() );
        pprrPprr00000100VO.setDIS_Vg( entity.getDIS_Vg() );
        pprrPprr00000100VO.setDIS_Vp( entity.getDIS_Vp() );
        pprrPprr00000100VO.setDIS_BPrice( entity.getDIS_BPrice() );
        pprrPprr00000100VO.setDIS_CPrice( entity.getDIS_CPrice() );
        pprrPprr00000100VO.setDIS_APrice( entity.getDIS_APrice() );
        pprrPprr00000100VO.setDIS_FormatRef( entity.getDIS_FormatRef() );
        pprrPprr00000100VO.setDIS_ProfileRef( entity.getDIS_ProfileRef() );
        pprrPprr00000100VO.setDIS_WSA( entity.getDIS_WSA() );
        pprrPprr00000100VO.setDIS_WEA( entity.getDIS_WEA() );
        pprrPprr00000100VO.setDIS_FSA( entity.getDIS_FSA() );
        pprrPprr00000100VO.setDIS_FEA( entity.getDIS_FEA() );
        pprrPprr00000100VO.setDIS_PClass( entity.getDIS_PClass() );
        pprrPprr00000100VO.setDIS_IsQuote( entity.getDIS_IsQuote() );
        pprrPprr00000100VO.setDIS_Volume( entity.getDIS_Volume() );
        pprrPprr00000100VO.setDIS_CommonPartIni( entity.getDIS_CommonPartIni() );
        pprrPprr00000100VO.setDIS_CommonPartEnd( entity.getDIS_CommonPartEnd() );
        pprrPprr00000100VO.setDIS_CommonProfileIni( entity.getDIS_CommonProfileIni() );
        pprrPprr00000100VO.setDIS_CommonProfileEnd( entity.getDIS_CommonProfileEnd() );
        pprrPprr00000100VO.setDIS_JobRef( entity.getDIS_JobRef() );
        pprrPprr00000100VO.setDIS_Checked( entity.getDIS_Checked() );
        pprrPprr00000100VO.setDIS_CanSplit( entity.getDIS_CanSplit() );
        pprrPprr00000100VO.setDIS_PriceDate( entity.getDIS_PriceDate() );
        pprrPprr00000100VO.setDIS_NestSeparation( entity.getDIS_NestSeparation() );
        pprrPprr00000100VO.setDIS_AreaByLength( entity.getDIS_AreaByLength() );
        pprrPprr00000100VO.setDIS_WeightByLength( entity.getDIS_WeightByLength() );
        pprrPprr00000100VO.setDIS_ModelingBy( entity.getDIS_ModelingBy() );
        pprrPprr00000100VO.setDIS_ModelingByID( entity.getDIS_ModelingByID() );
        pprrPprr00000100VO.setDIS_Strength( entity.getDIS_Strength() );
        pprrPprr00000100VO.setDIS_SimpleBends( entity.getDIS_SimpleBends() );
        pprrPprr00000100VO.setDIS_SpecialBends( entity.getDIS_SpecialBends() );
        pprrPprr00000100VO.setDIS_BendingToolChanges( entity.getDIS_BendingToolChanges() );
        pprrPprr00000100VO.setDIS_BendingRotations( entity.getDIS_BendingRotations() );
        pprrPprr00000100VO.setDIS_RouteAbbreviation( entity.getDIS_RouteAbbreviation() );
        pprrPprr00000100VO.setDIS_ExternalKey( entity.getDIS_ExternalKey() );
        pprrPprr00000100VO.setRecState( entity.getRecState() );
        pprrPprr00000100VO.setCrtDate( entity.getCrtDate() );
        pprrPprr00000100VO.setLastDate( entity.getLastDate() );
        pprrPprr00000100VO.setCrtUser( entity.getCrtUser() );
        pprrPprr00000100VO.setLastUser( entity.getLastUser() );
        pprrPprr00000100VO.setOwner( entity.getOwner() );
        pprrPprr00000100VO.setRecEnt( entity.getRecEnt() );
        pprrPprr00000100VO.setRecOU( entity.getRecOU() );
        pprrPprr00000100VO.setRecSec( entity.getRecSec() );
        pprrPprr00000100VO.setCntID( entity.getCntID() );
        pprrPprr00000100VO.setRecID( entity.getRecID() );

        return pprrPprr00000100VO;
    }

    @Override
    public List<PprrPprr00000100VO> toVOList(List<PprrPprr00000100> list) {
        if ( list == null ) {
            return null;
        }

        List<PprrPprr00000100VO> list1 = new ArrayList<PprrPprr00000100VO>( list.size() );
        for ( PprrPprr00000100 pprrPprr00000100 : list ) {
            list1.add( toVO( pprrPprr00000100 ) );
        }

        return list1;
    }

    @Override
    public PprrPprr00000100 toEntity(PprrPprr00000100DTO dto) {
        if ( dto == null ) {
            return null;
        }

        PprrPprr00000100 pprrPprr00000100 = new PprrPprr00000100();

        pprrPprr00000100.setPrdRef( dto.getPrdRef() );
        pprrPprr00000100.setPrdName( dto.getPrdName() );
        pprrPprr00000100.setPrdIntName( dto.getPrdIntName() );
        pprrPprr00000100.setPGroup( dto.getPGroup() );
        pprrPprr00000100.setBarCode( dto.getBarCode() );
        pprrPprr00000100.setIsActive( dto.getIsActive() );
        pprrPprr00000100.setRealPrd( dto.getRealPrd() );
        pprrPprr00000100.setAssembly( dto.getAssembly() );
        pprrPprr00000100.setForSale( dto.getForSale() );
        pprrPprr00000100.setPType( dto.getPType() );
        pprrPprr00000100.setCstRanges( dto.getCstRanges() );
        pprrPprr00000100.setFixPrice( dto.getFixPrice() );
        pprrPprr00000100.setStdCost( dto.getStdCost() );
        pprrPprr00000100.setCurCost( dto.getCurCost() );
        pprrPprr00000100.setPrcMethod( dto.getPrcMethod() );
        pprrPprr00000100.setCstMethod( dto.getCstMethod() );
        pprrPprr00000100.setUCtName( dto.getUCtName() );
        pprrPprr00000100.setUntName( dto.getUntName() );
        pprrPprr00000100.setCurQuan( dto.getCurQuan() );
        pprrPprr00000100.setCDate( dto.getCDate() );
        pprrPprr00000100.setKeyWords( dto.getKeyWords() );
        pprrPprr00000100.setDescrip( dto.getDescrip() );
        pprrPprr00000100.setImage( dto.getImage() );
        pprrPprr00000100.setWeight( dto.getWeight() );
        pprrPprr00000100.setLeadTime( dto.getLeadTime() );
        pprrPprr00000100.setLeadUCtName( dto.getLeadUCtName() );
        pprrPprr00000100.setLeadUntName( dto.getLeadUntName() );
        pprrPprr00000100.setLeadUpdateMethod( dto.getLeadUpdateMethod() );
        pprrPprr00000100.setCommCode( dto.getCommCode() );
        pprrPprr00000100.setOCountry( dto.getOCountry() );
        pprrPprr00000100.setCGroup( dto.getCGroup() );
        pprrPprr00000100.setAccConItemRef( dto.getAccConItemRef() );
        pprrPprr00000100.setGLS_VarRef1( dto.getGLS_VarRef1() );
        pprrPprr00000100.setGLS_UntName1( dto.getGLS_UntName1() );
        pprrPprr00000100.setGLS_VarRef2( dto.getGLS_VarRef2() );
        pprrPprr00000100.setGLS_UntName2( dto.getGLS_UntName2() );
        pprrPprr00000100.setGLS_VarRef3( dto.getGLS_VarRef3() );
        pprrPprr00000100.setGLS_UntName3( dto.getGLS_UntName3() );
        pprrPprr00000100.setGLS_VarRef4( dto.getGLS_VarRef4() );
        pprrPprr00000100.setGLS_UntName4( dto.getGLS_UntName4() );
        pprrPprr00000100.setGLS_VarRef5( dto.getGLS_VarRef5() );
        pprrPprr00000100.setGLS_UntName5( dto.getGLS_UntName5() );
        pprrPprr00000100.setGLS_UCtName1( dto.getGLS_UCtName1() );
        pprrPprr00000100.setGLS_UCtName2( dto.getGLS_UCtName2() );
        pprrPprr00000100.setGLS_UCtName3( dto.getGLS_UCtName3() );
        pprrPprr00000100.setGLS_UCtName4( dto.getGLS_UCtName4() );
        pprrPprr00000100.setGLS_UCtName5( dto.getGLS_UCtName5() );
        pprrPprr00000100.setGLS_AtRNameSerial( dto.getGLS_AtRNameSerial() );
        pprrPprr00000100.setGLS_AtRNameBatch( dto.getGLS_AtRNameBatch() );
        pprrPprr00000100.setGLS_TblRef( dto.getGLS_TblRef() );
        pprrPprr00000100.setDIS_MatRef( dto.getDIS_MatRef() );
        pprrPprr00000100.setDIS_Length( dto.getDIS_Length() );
        pprrPprr00000100.setDIS_Width( dto.getDIS_Width() );
        pprrPprr00000100.setDIS_Thickness( dto.getDIS_Thickness() );
        pprrPprr00000100.setDIS_Area( dto.getDIS_Area() );
        pprrPprr00000100.setDIS_CreationM( dto.getDIS_CreationM() );
        pprrPprr00000100.setDIS_IsCanal( dto.getDIS_IsCanal() );
        pprrPprr00000100.setDIS_ShtRef( dto.getDIS_ShtRef() );
        pprrPprr00000100.setDIS_Rotations( dto.getDIS_Rotations() );
        pprrPprr00000100.setDIS_FillerPart( dto.getDIS_FillerPart() );
        pprrPprr00000100.setDIS_CanQuote( dto.getDIS_CanQuote() );
        pprrPprr00000100.setDIS_Side( dto.getDIS_Side() );
        pprrPprr00000100.setDIS_CutPerim( dto.getDIS_CutPerim() );
        pprrPprr00000100.setDIS_MrkPerim( dto.getDIS_MrkPerim() );
        pprrPprr00000100.setDIS_ExtArea( dto.getDIS_ExtArea() );
        pprrPprr00000100.setDIS_RectArea( dto.getDIS_RectArea() );
        pprrPprr00000100.setDIS_TypeArea( dto.getDIS_TypeArea() );
        pprrPprr00000100.setDIS_ExtWeight( dto.getDIS_ExtWeight() );
        pprrPprr00000100.setDIS_RectWeight( dto.getDIS_RectWeight() );
        pprrPprr00000100.setDIS_IsDuctPart( dto.getDIS_IsDuctPart() );
        pprrPprr00000100.setDIS_UData1_Prt( dto.getDIS_UData1_Prt() );
        pprrPprr00000100.setDIS_UData2_Prt( dto.getDIS_UData2_Prt() );
        pprrPprr00000100.setDIS_UData3_Prt( dto.getDIS_UData3_Prt() );
        pprrPprr00000100.setDIS_UData4_Prt( dto.getDIS_UData4_Prt() );
        pprrPprr00000100.setDIS_UData5_Prt( dto.getDIS_UData5_Prt() );
        pprrPprr00000100.setDIS_UData6_Prt( dto.getDIS_UData6_Prt() );
        pprrPprr00000100.setDIS_UData7_Prt( dto.getDIS_UData7_Prt() );
        pprrPprr00000100.setDIS_UData8_Prt( dto.getDIS_UData8_Prt() );
        pprrPprr00000100.setDIS_IsDraft( dto.getDIS_IsDraft() );
        pprrPprr00000100.setDIS_DuctDINCode( dto.getDIS_DuctDINCode() );
        pprrPprr00000100.setDIS_DuctDINRef( dto.getDIS_DuctDINRef() );
        pprrPprr00000100.setDIS_SeamL( dto.getDIS_SeamL() );
        pprrPprr00000100.setDIS_CnnL( dto.getDIS_CnnL() );
        pprrPprr00000100.setDIS_FPosition( dto.getDIS_FPosition() );
        pprrPprr00000100.setDIS_CArea( dto.getDIS_CArea() );
        pprrPprr00000100.setDIS_UMAX( dto.getDIS_UMAX() );
        pprrPprr00000100.setDIS_LMAX( dto.getDIS_LMAX() );
        pprrPprr00000100.setDIS_SMAX( dto.getDIS_SMAX() );
        pprrPprr00000100.setDIS_PrssIndex( dto.getDIS_PrssIndex() );
        pprrPprr00000100.setDIS_IsRemnant( dto.getDIS_IsRemnant() );
        pprrPprr00000100.setDIS_PrcRmntPrice( dto.getDIS_PrcRmntPrice() );
        pprrPprr00000100.setDIS_ScrpPrice( dto.getDIS_ScrpPrice() );
        pprrPprr00000100.setDIS_PrcScrpPrice( dto.getDIS_PrcScrpPrice() );
        pprrPprr00000100.setDIS_RPriority( dto.getDIS_RPriority() );
        pprrPprr00000100.setDIS_IsLocked( dto.getDIS_IsLocked() );
        pprrPprr00000100.setDIS_CamQuan( dto.getDIS_CamQuan() );
        pprrPprr00000100.setDIS_ShtRefOrg( dto.getDIS_ShtRefOrg() );
        pprrPprr00000100.setDIS_UData1_Sht( dto.getDIS_UData1_Sht() );
        pprrPprr00000100.setDIS_UData2_Sht( dto.getDIS_UData2_Sht() );
        pprrPprr00000100.setDIS_UData3_Sht( dto.getDIS_UData3_Sht() );
        pprrPprr00000100.setDIS_Price( dto.getDIS_Price() );
        pprrPprr00000100.setDIS_InProgress( dto.getDIS_InProgress() );
        pprrPprr00000100.setDIS_Factor( dto.getDIS_Factor() );
        pprrPprr00000100.setDIS_Vl( dto.getDIS_Vl() );
        pprrPprr00000100.setDIS_Vh( dto.getDIS_Vh() );
        pprrPprr00000100.setDIS_Vg( dto.getDIS_Vg() );
        pprrPprr00000100.setDIS_Vp( dto.getDIS_Vp() );
        pprrPprr00000100.setDIS_BPrice( dto.getDIS_BPrice() );
        pprrPprr00000100.setDIS_CPrice( dto.getDIS_CPrice() );
        pprrPprr00000100.setDIS_APrice( dto.getDIS_APrice() );
        pprrPprr00000100.setDIS_FormatRef( dto.getDIS_FormatRef() );
        pprrPprr00000100.setDIS_ProfileRef( dto.getDIS_ProfileRef() );
        pprrPprr00000100.setDIS_WSA( dto.getDIS_WSA() );
        pprrPprr00000100.setDIS_WEA( dto.getDIS_WEA() );
        pprrPprr00000100.setDIS_FSA( dto.getDIS_FSA() );
        pprrPprr00000100.setDIS_FEA( dto.getDIS_FEA() );
        pprrPprr00000100.setDIS_PClass( dto.getDIS_PClass() );
        pprrPprr00000100.setDIS_IsQuote( dto.getDIS_IsQuote() );
        pprrPprr00000100.setDIS_Volume( dto.getDIS_Volume() );
        pprrPprr00000100.setDIS_CommonPartIni( dto.getDIS_CommonPartIni() );
        pprrPprr00000100.setDIS_CommonPartEnd( dto.getDIS_CommonPartEnd() );
        pprrPprr00000100.setDIS_CommonProfileIni( dto.getDIS_CommonProfileIni() );
        pprrPprr00000100.setDIS_CommonProfileEnd( dto.getDIS_CommonProfileEnd() );
        pprrPprr00000100.setDIS_JobRef( dto.getDIS_JobRef() );
        pprrPprr00000100.setDIS_Checked( dto.getDIS_Checked() );
        pprrPprr00000100.setDIS_CanSplit( dto.getDIS_CanSplit() );
        pprrPprr00000100.setDIS_PriceDate( dto.getDIS_PriceDate() );
        pprrPprr00000100.setDIS_NestSeparation( dto.getDIS_NestSeparation() );
        pprrPprr00000100.setDIS_AreaByLength( dto.getDIS_AreaByLength() );
        pprrPprr00000100.setDIS_WeightByLength( dto.getDIS_WeightByLength() );
        pprrPprr00000100.setDIS_ModelingBy( dto.getDIS_ModelingBy() );
        pprrPprr00000100.setDIS_ModelingByID( dto.getDIS_ModelingByID() );
        pprrPprr00000100.setDIS_Strength( dto.getDIS_Strength() );
        pprrPprr00000100.setDIS_SimpleBends( dto.getDIS_SimpleBends() );
        pprrPprr00000100.setDIS_SpecialBends( dto.getDIS_SpecialBends() );
        pprrPprr00000100.setDIS_BendingToolChanges( dto.getDIS_BendingToolChanges() );
        pprrPprr00000100.setDIS_BendingRotations( dto.getDIS_BendingRotations() );
        pprrPprr00000100.setDIS_RouteAbbreviation( dto.getDIS_RouteAbbreviation() );
        pprrPprr00000100.setDIS_ExternalKey( dto.getDIS_ExternalKey() );
        pprrPprr00000100.setRecState( dto.getRecState() );
        pprrPprr00000100.setCrtDate( dto.getCrtDate() );
        pprrPprr00000100.setLastDate( dto.getLastDate() );
        pprrPprr00000100.setCrtUser( dto.getCrtUser() );
        pprrPprr00000100.setLastUser( dto.getLastUser() );
        pprrPprr00000100.setOwner( dto.getOwner() );
        pprrPprr00000100.setRecEnt( dto.getRecEnt() );
        pprrPprr00000100.setRecOU( dto.getRecOU() );
        pprrPprr00000100.setRecSec( dto.getRecSec() );
        pprrPprr00000100.setCntID( dto.getCntID() );
        pprrPprr00000100.setRecID( dto.getRecID() );

        return pprrPprr00000100;
    }

    @Override
    public PprrPprr00000100 toEntity(PprrPprr00000100PageQuery dto) {
        if ( dto == null ) {
            return null;
        }

        PprrPprr00000100 pprrPprr00000100 = new PprrPprr00000100();

        pprrPprr00000100.setPrdRef( dto.getPrdRef() );
        pprrPprr00000100.setPrdName( dto.getPrdName() );
        pprrPprr00000100.setPrdIntName( dto.getPrdIntName() );
        pprrPprr00000100.setPGroup( dto.getPGroup() );
        pprrPprr00000100.setBarCode( dto.getBarCode() );
        pprrPprr00000100.setIsActive( dto.getIsActive() );
        pprrPprr00000100.setRealPrd( dto.getRealPrd() );
        pprrPprr00000100.setAssembly( dto.getAssembly() );
        pprrPprr00000100.setForSale( dto.getForSale() );
        pprrPprr00000100.setPType( dto.getPType() );
        pprrPprr00000100.setCstRanges( dto.getCstRanges() );
        pprrPprr00000100.setFixPrice( dto.getFixPrice() );
        pprrPprr00000100.setStdCost( dto.getStdCost() );
        pprrPprr00000100.setCurCost( dto.getCurCost() );
        pprrPprr00000100.setPrcMethod( dto.getPrcMethod() );
        pprrPprr00000100.setCstMethod( dto.getCstMethod() );
        pprrPprr00000100.setUCtName( dto.getUCtName() );
        pprrPprr00000100.setUntName( dto.getUntName() );
        pprrPprr00000100.setCurQuan( dto.getCurQuan() );
        pprrPprr00000100.setCDate( dto.getCDate() );
        pprrPprr00000100.setKeyWords( dto.getKeyWords() );
        pprrPprr00000100.setDescrip( dto.getDescrip() );
        pprrPprr00000100.setImage( dto.getImage() );
        pprrPprr00000100.setWeight( dto.getWeight() );
        pprrPprr00000100.setLeadTime( dto.getLeadTime() );
        pprrPprr00000100.setLeadUCtName( dto.getLeadUCtName() );
        pprrPprr00000100.setLeadUntName( dto.getLeadUntName() );
        pprrPprr00000100.setLeadUpdateMethod( dto.getLeadUpdateMethod() );
        pprrPprr00000100.setCommCode( dto.getCommCode() );
        pprrPprr00000100.setOCountry( dto.getOCountry() );
        pprrPprr00000100.setCGroup( dto.getCGroup() );
        pprrPprr00000100.setAccConItemRef( dto.getAccConItemRef() );
        pprrPprr00000100.setRecState( dto.getRecState() );
        pprrPprr00000100.setCrtDate( dto.getCrtDate() );
        pprrPprr00000100.setLastDate( dto.getLastDate() );
        pprrPprr00000100.setCrtUser( dto.getCrtUser() );
        pprrPprr00000100.setLastUser( dto.getLastUser() );
        pprrPprr00000100.setOwner( dto.getOwner() );
        pprrPprr00000100.setRecEnt( dto.getRecEnt() );
        pprrPprr00000100.setRecOU( dto.getRecOU() );
        pprrPprr00000100.setRecSec( dto.getRecSec() );
        pprrPprr00000100.setCntID( dto.getCntID() );
        pprrPprr00000100.setRecID( dto.getRecID() );

        return pprrPprr00000100;
    }

    @Override
    public void updateFromDTO(PprrPprr00000100DTO dto, PprrPprr00000100 entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getPrdRef() != null ) {
            entity.setPrdRef( dto.getPrdRef() );
        }
        if ( dto.getPrdName() != null ) {
            entity.setPrdName( dto.getPrdName() );
        }
        if ( dto.getPrdIntName() != null ) {
            entity.setPrdIntName( dto.getPrdIntName() );
        }
        if ( dto.getPGroup() != null ) {
            entity.setPGroup( dto.getPGroup() );
        }
        if ( dto.getBarCode() != null ) {
            entity.setBarCode( dto.getBarCode() );
        }
        if ( dto.getIsActive() != null ) {
            entity.setIsActive( dto.getIsActive() );
        }
        if ( dto.getRealPrd() != null ) {
            entity.setRealPrd( dto.getRealPrd() );
        }
        if ( dto.getAssembly() != null ) {
            entity.setAssembly( dto.getAssembly() );
        }
        if ( dto.getForSale() != null ) {
            entity.setForSale( dto.getForSale() );
        }
        if ( dto.getPType() != null ) {
            entity.setPType( dto.getPType() );
        }
        if ( dto.getCstRanges() != null ) {
            entity.setCstRanges( dto.getCstRanges() );
        }
        if ( dto.getFixPrice() != null ) {
            entity.setFixPrice( dto.getFixPrice() );
        }
        if ( dto.getStdCost() != null ) {
            entity.setStdCost( dto.getStdCost() );
        }
        if ( dto.getCurCost() != null ) {
            entity.setCurCost( dto.getCurCost() );
        }
        if ( dto.getPrcMethod() != null ) {
            entity.setPrcMethod( dto.getPrcMethod() );
        }
        if ( dto.getCstMethod() != null ) {
            entity.setCstMethod( dto.getCstMethod() );
        }
        if ( dto.getUCtName() != null ) {
            entity.setUCtName( dto.getUCtName() );
        }
        if ( dto.getUntName() != null ) {
            entity.setUntName( dto.getUntName() );
        }
        if ( dto.getCurQuan() != null ) {
            entity.setCurQuan( dto.getCurQuan() );
        }
        if ( dto.getCDate() != null ) {
            entity.setCDate( dto.getCDate() );
        }
        if ( dto.getKeyWords() != null ) {
            entity.setKeyWords( dto.getKeyWords() );
        }
        if ( dto.getDescrip() != null ) {
            entity.setDescrip( dto.getDescrip() );
        }
        if ( dto.getImage() != null ) {
            entity.setImage( dto.getImage() );
        }
        if ( dto.getWeight() != null ) {
            entity.setWeight( dto.getWeight() );
        }
        if ( dto.getLeadTime() != null ) {
            entity.setLeadTime( dto.getLeadTime() );
        }
        if ( dto.getLeadUCtName() != null ) {
            entity.setLeadUCtName( dto.getLeadUCtName() );
        }
        if ( dto.getLeadUntName() != null ) {
            entity.setLeadUntName( dto.getLeadUntName() );
        }
        if ( dto.getLeadUpdateMethod() != null ) {
            entity.setLeadUpdateMethod( dto.getLeadUpdateMethod() );
        }
        if ( dto.getCommCode() != null ) {
            entity.setCommCode( dto.getCommCode() );
        }
        if ( dto.getOCountry() != null ) {
            entity.setOCountry( dto.getOCountry() );
        }
        if ( dto.getCGroup() != null ) {
            entity.setCGroup( dto.getCGroup() );
        }
        if ( dto.getAccConItemRef() != null ) {
            entity.setAccConItemRef( dto.getAccConItemRef() );
        }
        if ( dto.getGLS_VarRef1() != null ) {
            entity.setGLS_VarRef1( dto.getGLS_VarRef1() );
        }
        if ( dto.getGLS_UntName1() != null ) {
            entity.setGLS_UntName1( dto.getGLS_UntName1() );
        }
        if ( dto.getGLS_VarRef2() != null ) {
            entity.setGLS_VarRef2( dto.getGLS_VarRef2() );
        }
        if ( dto.getGLS_UntName2() != null ) {
            entity.setGLS_UntName2( dto.getGLS_UntName2() );
        }
        if ( dto.getGLS_VarRef3() != null ) {
            entity.setGLS_VarRef3( dto.getGLS_VarRef3() );
        }
        if ( dto.getGLS_UntName3() != null ) {
            entity.setGLS_UntName3( dto.getGLS_UntName3() );
        }
        if ( dto.getGLS_VarRef4() != null ) {
            entity.setGLS_VarRef4( dto.getGLS_VarRef4() );
        }
        if ( dto.getGLS_UntName4() != null ) {
            entity.setGLS_UntName4( dto.getGLS_UntName4() );
        }
        if ( dto.getGLS_VarRef5() != null ) {
            entity.setGLS_VarRef5( dto.getGLS_VarRef5() );
        }
        if ( dto.getGLS_UntName5() != null ) {
            entity.setGLS_UntName5( dto.getGLS_UntName5() );
        }
        if ( dto.getGLS_UCtName1() != null ) {
            entity.setGLS_UCtName1( dto.getGLS_UCtName1() );
        }
        if ( dto.getGLS_UCtName2() != null ) {
            entity.setGLS_UCtName2( dto.getGLS_UCtName2() );
        }
        if ( dto.getGLS_UCtName3() != null ) {
            entity.setGLS_UCtName3( dto.getGLS_UCtName3() );
        }
        if ( dto.getGLS_UCtName4() != null ) {
            entity.setGLS_UCtName4( dto.getGLS_UCtName4() );
        }
        if ( dto.getGLS_UCtName5() != null ) {
            entity.setGLS_UCtName5( dto.getGLS_UCtName5() );
        }
        if ( dto.getGLS_AtRNameSerial() != null ) {
            entity.setGLS_AtRNameSerial( dto.getGLS_AtRNameSerial() );
        }
        if ( dto.getGLS_AtRNameBatch() != null ) {
            entity.setGLS_AtRNameBatch( dto.getGLS_AtRNameBatch() );
        }
        if ( dto.getGLS_TblRef() != null ) {
            entity.setGLS_TblRef( dto.getGLS_TblRef() );
        }
        if ( dto.getDIS_MatRef() != null ) {
            entity.setDIS_MatRef( dto.getDIS_MatRef() );
        }
        if ( dto.getDIS_Length() != null ) {
            entity.setDIS_Length( dto.getDIS_Length() );
        }
        if ( dto.getDIS_Width() != null ) {
            entity.setDIS_Width( dto.getDIS_Width() );
        }
        if ( dto.getDIS_Thickness() != null ) {
            entity.setDIS_Thickness( dto.getDIS_Thickness() );
        }
        if ( dto.getDIS_Area() != null ) {
            entity.setDIS_Area( dto.getDIS_Area() );
        }
        if ( dto.getDIS_CreationM() != null ) {
            entity.setDIS_CreationM( dto.getDIS_CreationM() );
        }
        if ( dto.getDIS_IsCanal() != null ) {
            entity.setDIS_IsCanal( dto.getDIS_IsCanal() );
        }
        if ( dto.getDIS_ShtRef() != null ) {
            entity.setDIS_ShtRef( dto.getDIS_ShtRef() );
        }
        if ( dto.getDIS_Rotations() != null ) {
            entity.setDIS_Rotations( dto.getDIS_Rotations() );
        }
        if ( dto.getDIS_FillerPart() != null ) {
            entity.setDIS_FillerPart( dto.getDIS_FillerPart() );
        }
        if ( dto.getDIS_CanQuote() != null ) {
            entity.setDIS_CanQuote( dto.getDIS_CanQuote() );
        }
        if ( dto.getDIS_Side() != null ) {
            entity.setDIS_Side( dto.getDIS_Side() );
        }
        if ( dto.getDIS_CutPerim() != null ) {
            entity.setDIS_CutPerim( dto.getDIS_CutPerim() );
        }
        if ( dto.getDIS_MrkPerim() != null ) {
            entity.setDIS_MrkPerim( dto.getDIS_MrkPerim() );
        }
        if ( dto.getDIS_ExtArea() != null ) {
            entity.setDIS_ExtArea( dto.getDIS_ExtArea() );
        }
        if ( dto.getDIS_RectArea() != null ) {
            entity.setDIS_RectArea( dto.getDIS_RectArea() );
        }
        if ( dto.getDIS_TypeArea() != null ) {
            entity.setDIS_TypeArea( dto.getDIS_TypeArea() );
        }
        if ( dto.getDIS_ExtWeight() != null ) {
            entity.setDIS_ExtWeight( dto.getDIS_ExtWeight() );
        }
        if ( dto.getDIS_RectWeight() != null ) {
            entity.setDIS_RectWeight( dto.getDIS_RectWeight() );
        }
        if ( dto.getDIS_IsDuctPart() != null ) {
            entity.setDIS_IsDuctPart( dto.getDIS_IsDuctPart() );
        }
        if ( dto.getDIS_UData1_Prt() != null ) {
            entity.setDIS_UData1_Prt( dto.getDIS_UData1_Prt() );
        }
        if ( dto.getDIS_UData2_Prt() != null ) {
            entity.setDIS_UData2_Prt( dto.getDIS_UData2_Prt() );
        }
        if ( dto.getDIS_UData3_Prt() != null ) {
            entity.setDIS_UData3_Prt( dto.getDIS_UData3_Prt() );
        }
        if ( dto.getDIS_UData4_Prt() != null ) {
            entity.setDIS_UData4_Prt( dto.getDIS_UData4_Prt() );
        }
        if ( dto.getDIS_UData5_Prt() != null ) {
            entity.setDIS_UData5_Prt( dto.getDIS_UData5_Prt() );
        }
        if ( dto.getDIS_UData6_Prt() != null ) {
            entity.setDIS_UData6_Prt( dto.getDIS_UData6_Prt() );
        }
        if ( dto.getDIS_UData7_Prt() != null ) {
            entity.setDIS_UData7_Prt( dto.getDIS_UData7_Prt() );
        }
        if ( dto.getDIS_UData8_Prt() != null ) {
            entity.setDIS_UData8_Prt( dto.getDIS_UData8_Prt() );
        }
        if ( dto.getDIS_IsDraft() != null ) {
            entity.setDIS_IsDraft( dto.getDIS_IsDraft() );
        }
        if ( dto.getDIS_DuctDINCode() != null ) {
            entity.setDIS_DuctDINCode( dto.getDIS_DuctDINCode() );
        }
        if ( dto.getDIS_DuctDINRef() != null ) {
            entity.setDIS_DuctDINRef( dto.getDIS_DuctDINRef() );
        }
        if ( dto.getDIS_SeamL() != null ) {
            entity.setDIS_SeamL( dto.getDIS_SeamL() );
        }
        if ( dto.getDIS_CnnL() != null ) {
            entity.setDIS_CnnL( dto.getDIS_CnnL() );
        }
        if ( dto.getDIS_FPosition() != null ) {
            entity.setDIS_FPosition( dto.getDIS_FPosition() );
        }
        if ( dto.getDIS_CArea() != null ) {
            entity.setDIS_CArea( dto.getDIS_CArea() );
        }
        if ( dto.getDIS_UMAX() != null ) {
            entity.setDIS_UMAX( dto.getDIS_UMAX() );
        }
        if ( dto.getDIS_LMAX() != null ) {
            entity.setDIS_LMAX( dto.getDIS_LMAX() );
        }
        if ( dto.getDIS_SMAX() != null ) {
            entity.setDIS_SMAX( dto.getDIS_SMAX() );
        }
        if ( dto.getDIS_PrssIndex() != null ) {
            entity.setDIS_PrssIndex( dto.getDIS_PrssIndex() );
        }
        if ( dto.getDIS_IsRemnant() != null ) {
            entity.setDIS_IsRemnant( dto.getDIS_IsRemnant() );
        }
        if ( dto.getDIS_PrcRmntPrice() != null ) {
            entity.setDIS_PrcRmntPrice( dto.getDIS_PrcRmntPrice() );
        }
        if ( dto.getDIS_ScrpPrice() != null ) {
            entity.setDIS_ScrpPrice( dto.getDIS_ScrpPrice() );
        }
        if ( dto.getDIS_PrcScrpPrice() != null ) {
            entity.setDIS_PrcScrpPrice( dto.getDIS_PrcScrpPrice() );
        }
        if ( dto.getDIS_RPriority() != null ) {
            entity.setDIS_RPriority( dto.getDIS_RPriority() );
        }
        if ( dto.getDIS_IsLocked() != null ) {
            entity.setDIS_IsLocked( dto.getDIS_IsLocked() );
        }
        if ( dto.getDIS_CamQuan() != null ) {
            entity.setDIS_CamQuan( dto.getDIS_CamQuan() );
        }
        if ( dto.getDIS_ShtRefOrg() != null ) {
            entity.setDIS_ShtRefOrg( dto.getDIS_ShtRefOrg() );
        }
        if ( dto.getDIS_UData1_Sht() != null ) {
            entity.setDIS_UData1_Sht( dto.getDIS_UData1_Sht() );
        }
        if ( dto.getDIS_UData2_Sht() != null ) {
            entity.setDIS_UData2_Sht( dto.getDIS_UData2_Sht() );
        }
        if ( dto.getDIS_UData3_Sht() != null ) {
            entity.setDIS_UData3_Sht( dto.getDIS_UData3_Sht() );
        }
        if ( dto.getDIS_Price() != null ) {
            entity.setDIS_Price( dto.getDIS_Price() );
        }
        if ( dto.getDIS_InProgress() != null ) {
            entity.setDIS_InProgress( dto.getDIS_InProgress() );
        }
        if ( dto.getDIS_Factor() != null ) {
            entity.setDIS_Factor( dto.getDIS_Factor() );
        }
        if ( dto.getDIS_Vl() != null ) {
            entity.setDIS_Vl( dto.getDIS_Vl() );
        }
        if ( dto.getDIS_Vh() != null ) {
            entity.setDIS_Vh( dto.getDIS_Vh() );
        }
        if ( dto.getDIS_Vg() != null ) {
            entity.setDIS_Vg( dto.getDIS_Vg() );
        }
        if ( dto.getDIS_Vp() != null ) {
            entity.setDIS_Vp( dto.getDIS_Vp() );
        }
        if ( dto.getDIS_BPrice() != null ) {
            entity.setDIS_BPrice( dto.getDIS_BPrice() );
        }
        if ( dto.getDIS_CPrice() != null ) {
            entity.setDIS_CPrice( dto.getDIS_CPrice() );
        }
        if ( dto.getDIS_APrice() != null ) {
            entity.setDIS_APrice( dto.getDIS_APrice() );
        }
        if ( dto.getDIS_FormatRef() != null ) {
            entity.setDIS_FormatRef( dto.getDIS_FormatRef() );
        }
        if ( dto.getDIS_ProfileRef() != null ) {
            entity.setDIS_ProfileRef( dto.getDIS_ProfileRef() );
        }
        if ( dto.getDIS_WSA() != null ) {
            entity.setDIS_WSA( dto.getDIS_WSA() );
        }
        if ( dto.getDIS_WEA() != null ) {
            entity.setDIS_WEA( dto.getDIS_WEA() );
        }
        if ( dto.getDIS_FSA() != null ) {
            entity.setDIS_FSA( dto.getDIS_FSA() );
        }
        if ( dto.getDIS_FEA() != null ) {
            entity.setDIS_FEA( dto.getDIS_FEA() );
        }
        if ( dto.getDIS_PClass() != null ) {
            entity.setDIS_PClass( dto.getDIS_PClass() );
        }
        if ( dto.getDIS_IsQuote() != null ) {
            entity.setDIS_IsQuote( dto.getDIS_IsQuote() );
        }
        if ( dto.getDIS_Volume() != null ) {
            entity.setDIS_Volume( dto.getDIS_Volume() );
        }
        if ( dto.getDIS_CommonPartIni() != null ) {
            entity.setDIS_CommonPartIni( dto.getDIS_CommonPartIni() );
        }
        if ( dto.getDIS_CommonPartEnd() != null ) {
            entity.setDIS_CommonPartEnd( dto.getDIS_CommonPartEnd() );
        }
        if ( dto.getDIS_CommonProfileIni() != null ) {
            entity.setDIS_CommonProfileIni( dto.getDIS_CommonProfileIni() );
        }
        if ( dto.getDIS_CommonProfileEnd() != null ) {
            entity.setDIS_CommonProfileEnd( dto.getDIS_CommonProfileEnd() );
        }
        if ( dto.getDIS_JobRef() != null ) {
            entity.setDIS_JobRef( dto.getDIS_JobRef() );
        }
        if ( dto.getDIS_Checked() != null ) {
            entity.setDIS_Checked( dto.getDIS_Checked() );
        }
        if ( dto.getDIS_CanSplit() != null ) {
            entity.setDIS_CanSplit( dto.getDIS_CanSplit() );
        }
        if ( dto.getDIS_PriceDate() != null ) {
            entity.setDIS_PriceDate( dto.getDIS_PriceDate() );
        }
        if ( dto.getDIS_NestSeparation() != null ) {
            entity.setDIS_NestSeparation( dto.getDIS_NestSeparation() );
        }
        if ( dto.getDIS_AreaByLength() != null ) {
            entity.setDIS_AreaByLength( dto.getDIS_AreaByLength() );
        }
        if ( dto.getDIS_WeightByLength() != null ) {
            entity.setDIS_WeightByLength( dto.getDIS_WeightByLength() );
        }
        if ( dto.getDIS_ModelingBy() != null ) {
            entity.setDIS_ModelingBy( dto.getDIS_ModelingBy() );
        }
        if ( dto.getDIS_ModelingByID() != null ) {
            entity.setDIS_ModelingByID( dto.getDIS_ModelingByID() );
        }
        if ( dto.getDIS_Strength() != null ) {
            entity.setDIS_Strength( dto.getDIS_Strength() );
        }
        if ( dto.getDIS_SimpleBends() != null ) {
            entity.setDIS_SimpleBends( dto.getDIS_SimpleBends() );
        }
        if ( dto.getDIS_SpecialBends() != null ) {
            entity.setDIS_SpecialBends( dto.getDIS_SpecialBends() );
        }
        if ( dto.getDIS_BendingToolChanges() != null ) {
            entity.setDIS_BendingToolChanges( dto.getDIS_BendingToolChanges() );
        }
        if ( dto.getDIS_BendingRotations() != null ) {
            entity.setDIS_BendingRotations( dto.getDIS_BendingRotations() );
        }
        if ( dto.getDIS_RouteAbbreviation() != null ) {
            entity.setDIS_RouteAbbreviation( dto.getDIS_RouteAbbreviation() );
        }
        if ( dto.getDIS_ExternalKey() != null ) {
            entity.setDIS_ExternalKey( dto.getDIS_ExternalKey() );
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
