package com.zhurong.platform.core.lantek.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *
 *
 * @author heao
 * @since 2026-03-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("PPRR_PPRR_00000100")
public class PprrPprr00000100 extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     *
     */
    @TableField("PrdRef")
    private String PrdRef;

    /**
     *
     */
    @TableField("PrdName")
    private String PrdName;

    /**
     *
     */
    @TableField("PrdIntName")
    private String PrdIntName;

    /**
     *
     */
    @TableField("PGroup")
    private String PGroup;

    /**
     *
     */
    @TableField("BarCode")
    private String BarCode;

    /**
     *
     */
    @TableField("IsActive")
    private Byte IsActive;

    /**
     *
     */
    @TableField("RealPrd")
    private Byte RealPrd;

    /**
     *
     */
    @TableField("Assembly")
    private Byte Assembly;

    /**
     *
     */
    @TableField("ForSale")
    private Byte ForSale;

    /**
     *
     */
    @TableField("PType")
    private Integer PType;

    /**
     *
     */
    @TableField("CstRanges")
    private Byte CstRanges;

    /**
     *
     */
    @TableField("FixPrice")
    private Byte FixPrice;

    /**
     *
     */
    @TableField("StdCost")
    private Double StdCost;

    /**
     *
     */
    @TableField("CurCost")
    private Double CurCost;

    /**
     *
     */
    @TableField("PrcMethod")
    private Integer PrcMethod;

    /**
     *
     */
    @TableField("CstMethod")
    private Integer CstMethod;

    /**
     *
     */
    @TableField("UCtName")
    private String UCtName;

    /**
     *
     */
    @TableField("UntName")
    private String UntName;

    /**
     *
     */
    @TableField("CurQuan")
    private Double CurQuan;

    /**
     *
     */
    @TableField("CDate")
    private LocalDateTime CDate;

    /**
     *
     */
    @TableField("KeyWords")
    private String KeyWords;

    /**
     *
     */
    @TableField("Descrip")
    private String Descrip;

    /**
     *
     */
    @TableField("Image")
    private String Image;

    /**
     *
     */
    @TableField("Weight")
    private Double Weight;

    /**
     *
     */
    @TableField("LeadTime")
    private Double LeadTime;

    /**
     *
     */
    @TableField("LeadUCtName")
    private String LeadUCtName;

    /**
     *
     */
    @TableField("LeadUntName")
    private String LeadUntName;

    /**
     *
     */
    @TableField("LeadUpdateMethod")
    private Integer LeadUpdateMethod;

    /**
     *
     */
    @TableField("CommCode")
    private String CommCode;

    /**
     *
     */
    @TableField("OCountry")
    private String OCountry;

    /**
     *
     */
    @TableField("CGroup")
    private String CGroup;

    /**
     *
     */
    @TableField("AccConItemRef")
    private String AccConItemRef;

    /**
     *
     */
    @TableField("GLS_VarRef1")
    private String GLS_VarRef1;

    /**
     *
     */
    @TableField("GLS_UntName1")
    private String GLS_UntName1;

    /**
     *
     */
    @TableField("GLS_VarRef2")
    private String GLS_VarRef2;

    /**
     *
     */
    @TableField("GLS_UntName2")
    private String GLS_UntName2;

    /**
     *
     */
    @TableField("GLS_VarRef3")
    private String GLS_VarRef3;

    /**
     *
     */
    @TableField("GLS_UntName3")
    private String GLS_UntName3;

    /**
     *
     */
    @TableField("GLS_VarRef4")
    private String GLS_VarRef4;

    /**
     *
     */
    @TableField("GLS_UntName4")
    private String GLS_UntName4;

    /**
     *
     */
    @TableField("GLS_VarRef5")
    private String GLS_VarRef5;

    /**
     *
     */
    @TableField("GLS_UntName5")
    private String GLS_UntName5;

    /**
     *
     */
    @TableField("GLS_UCtName1")
    private String GLS_UCtName1;

    /**
     *
     */
    @TableField("GLS_UCtName2")
    private String GLS_UCtName2;

    /**
     *
     */
    @TableField("GLS_UCtName3")
    private String GLS_UCtName3;

    /**
     *
     */
    @TableField("GLS_UCtName4")
    private String GLS_UCtName4;

    /**
     *
     */
    @TableField("GLS_UCtName5")
    private String GLS_UCtName5;

    /**
     *
     */
    @TableField("GLS_AtRNameSerial")
    private String GLS_AtRNameSerial;

    /**
     *
     */
    @TableField("GLS_AtRNameBatch")
    private String GLS_AtRNameBatch;

    /**
     *
     */
    @TableField("GLS_TblRef")
    private String GLS_TblRef;

    /**
     *
     */
    @TableField("DIS_MatRef")
    private String DIS_MatRef;

    /**
     *
     */
    @TableField("DIS_Length")
    private Double DIS_Length;

    /**
     *
     */
    @TableField("DIS_Width")
    private Double DIS_Width;

    /**
     *
     */
    @TableField("DIS_Thickness")
    private Double DIS_Thickness;

    /**
     *
     */
    @TableField("DIS_Area")
    private Double DIS_Area;

    /**
     *
     */
    @TableField("DIS_CreationM")
    private String DIS_CreationM;

    /**
     *
     */
    @TableField("DIS_IsCanal")
    private Byte DIS_IsCanal;

    /**
     *
     */
    @TableField("DIS_ShtRef")
    private String DIS_ShtRef;

    /**
     *
     */
    @TableField("DIS_Rotations")
    private String DIS_Rotations;

    /**
     *
     */
    @TableField("DIS_FillerPart")
    private Integer DIS_FillerPart;

    /**
     *
     */
    @TableField("DIS_CanQuote")
    private Byte DIS_CanQuote;

    /**
     *
     */
    @TableField("DIS_Side")
    private String DIS_Side;

    /**
     *
     */
    @TableField("DIS_CutPerim")
    private Double DIS_CutPerim;

    /**
     *
     */
    @TableField("DIS_MrkPerim")
    private Double DIS_MrkPerim;

    /**
     *
     */
    @TableField("DIS_ExtArea")
    private Double DIS_ExtArea;

    /**
     *
     */
    @TableField("DIS_RectArea")
    private Double DIS_RectArea;

    /**
     *
     */
    @TableField("DIS_TypeArea")
    private Integer DIS_TypeArea;

    /**
     *
     */
    @TableField("DIS_ExtWeight")
    private Double DIS_ExtWeight;

    /**
     *
     */
    @TableField("DIS_RectWeight")
    private Double DIS_RectWeight;

    /**
     *
     */
    @TableField("DIS_IsDuctPart")
    private Byte DIS_IsDuctPart;

    /**
     *
     */
    @TableField("DIS_UData1_Prt")
    private String DIS_UData1_Prt;

    /**
     *
     */
    @TableField("DIS_UData2_Prt")
    private String DIS_UData2_Prt;

    /**
     *
     */
    @TableField("DIS_UData3_Prt")
    private String DIS_UData3_Prt;

    /**
     *
     */
    @TableField("DIS_UData4_Prt")
    private String DIS_UData4_Prt;

    /**
     *
     */
    @TableField("DIS_UData5_Prt")
    private String DIS_UData5_Prt;

    /**
     *
     */
    @TableField("DIS_UData6_Prt")
    private String DIS_UData6_Prt;

    /**
     *
     */
    @TableField("DIS_UData7_Prt")
    private String DIS_UData7_Prt;

    /**
     *
     */
    @TableField("DIS_UData8_Prt")
    private String DIS_UData8_Prt;

    /**
     *
     */
    @TableField("DIS_IsDraft")
    private Byte DIS_IsDraft;

    /**
     *
     */
    @TableField("DIS_DuctDINCode")
    private Integer DIS_DuctDINCode;

    /**
     *
     */
    @TableField("DIS_DuctDINRef")
    private String DIS_DuctDINRef;

    /**
     *
     */
    @TableField("DIS_SeamL")
    private Double DIS_SeamL;

    /**
     *
     */
    @TableField("DIS_CnnL")
    private Double DIS_CnnL;

    /**
     *
     */
    @TableField("DIS_FPosition")
    private String DIS_FPosition;

    /**
     *
     */
    @TableField("DIS_CArea")
    private Double DIS_CArea;

    /**
     *
     */
    @TableField("DIS_UMAX")
    private Double DIS_UMAX;

    /**
     *
     */
    @TableField("DIS_LMAX")
    private Double DIS_LMAX;

    /**
     *
     */
    @TableField("DIS_SMAX")
    private Double DIS_SMAX;

    /**
     *
     */
    @TableField("DIS_PrssIndex")
    private Integer DIS_PrssIndex;

    /**
     *
     */
    @TableField("DIS_IsRemnant")
    private Byte DIS_IsRemnant;

    /**
     *
     */
    @TableField("DIS_PrcRmntPrice")
    private Double DIS_PrcRmntPrice;

    /**
     *
     */
    @TableField("DIS_ScrpPrice")
    private Double DIS_ScrpPrice;

    /**
     *
     */
    @TableField("DIS_PrcScrpPrice")
    private Double DIS_PrcScrpPrice;

    /**
     *
     */
    @TableField("DIS_RPriority")
    private Integer DIS_RPriority;

    /**
     *
     */
    @TableField("DIS_IsLocked")
    private Byte DIS_IsLocked;

    /**
     *
     */
    @TableField("DIS_CamQuan")
    private Integer DIS_CamQuan;

    /**
     *
     */
    @TableField("DIS_ShtRefOrg")
    private String DIS_ShtRefOrg;

    /**
     *
     */
    @TableField("DIS_UData1_Sht")
    private String DIS_UData1_Sht;

    /**
     *
     */
    @TableField("DIS_UData2_Sht")
    private String DIS_UData2_Sht;

    /**
     *
     */
    @TableField("DIS_UData3_Sht")
    private String DIS_UData3_Sht;

    /**
     *
     */
    @TableField("DIS_Price")
    private Double DIS_Price;

    /**
     *
     */
    @TableField("DIS_InProgress")
    private Integer DIS_InProgress;

    /**
     *
     */
    @TableField("DIS_Factor")
    private Double DIS_Factor;

    /**
     *
     */
    @TableField("DIS_Vl")
    private Double DIS_Vl;

    /**
     *
     */
    @TableField("DIS_Vh")
    private Double DIS_Vh;

    /**
     *
     */
    @TableField("DIS_Vg")
    private Double DIS_Vg;

    /**
     *
     */
    @TableField("DIS_Vp")
    private Double DIS_Vp;

    /**
     *
     */
    @TableField("DIS_BPrice")
    private Double DIS_BPrice;

    /**
     *
     */
    @TableField("DIS_CPrice")
    private Double DIS_CPrice;

    /**
     *
     */
    @TableField("DIS_APrice")
    private Double DIS_APrice;

    /**
     *
     */
    @TableField("DIS_FormatRef")
    private String DIS_FormatRef;

    /**
     *
     */
    @TableField("DIS_ProfileRef")
    private String DIS_ProfileRef;

    /**
     *
     */
    @TableField("DIS_WSA")
    private Double DIS_WSA;

    /**
     *
     */
    @TableField("DIS_WEA")
    private Double DIS_WEA;

    /**
     *
     */
    @TableField("DIS_FSA")
    private Double DIS_FSA;

    /**
     *
     */
    @TableField("DIS_FEA")
    private Double DIS_FEA;

    /**
     *
     */
    @TableField("DIS_PClass")
    private Integer DIS_PClass;

    /**
     *
     */
    @TableField("DIS_IsQuote")
    private Byte DIS_IsQuote;

    /**
     *
     */
    @TableField("DIS_Volume")
    private Double DIS_Volume;

    /**
     *
     */
    @TableField("DIS_CommonPartIni")
    private Byte DIS_CommonPartIni;

    /**
     *
     */
    @TableField("DIS_CommonPartEnd")
    private Byte DIS_CommonPartEnd;

    /**
     *
     */
    @TableField("DIS_CommonProfileIni")
    private Byte DIS_CommonProfileIni;

    /**
     *
     */
    @TableField("DIS_CommonProfileEnd")
    private Byte DIS_CommonProfileEnd;

    /**
     *
     */
    @TableField("DIS_JobRef")
    private String DIS_JobRef;

    /**
     *
     */
    @TableField("DIS_Checked")
    private Integer DIS_Checked;

    /**
     *
     */
    @TableField("DIS_CanSplit")
    private Byte DIS_CanSplit;

    /**
     *
     */
    @TableField("DIS_PriceDate")
    private LocalDateTime DIS_PriceDate;

    /**
     *
     */
    @TableField("DIS_NestSeparation")
    private Double DIS_NestSeparation;

    /**
     *
     */
    @TableField("DIS_AreaByLength")
    private Double DIS_AreaByLength;

    /**
     *
     */
    @TableField("DIS_WeightByLength")
    private Double DIS_WeightByLength;

    /**
     *
     */
    @TableField("DIS_ModelingBy")
    private Integer DIS_ModelingBy;

    /**
     *
     */
    @TableField("DIS_ModelingByID")
    private String DIS_ModelingByID;

    /**
     *
     */
    @TableField("DIS_Strength")
    private Double DIS_Strength;

    /**
     *
     */
    @TableField("DIS_SimpleBends")
    private Integer DIS_SimpleBends;

    /**
     *
     */
    @TableField("DIS_SpecialBends")
    private Integer DIS_SpecialBends;

    /**
     *
     */
    @TableField("DIS_BendingToolChanges")
    private Integer DIS_BendingToolChanges;

    /**
     *
     */
    @TableField("DIS_BendingRotations")
    private Integer DIS_BendingRotations;

    /**
     *
     */
    @TableField("DIS_RouteAbbreviation")
    private String DIS_RouteAbbreviation;

    /**
     *
     */
    @TableField("DIS_ExternalKey")
    private String DIS_ExternalKey;

    /**
     *
     */
    @TableField("RecState")
    private Integer RecState;

    /**
     *
     */
    @TableField("CrtDate")
    private LocalDateTime CrtDate;

    /**
     *
     */
    @TableField("LastDate")
    private LocalDateTime LastDate;

    /**
     *
     */
    @TableField("CrtUser")
    private String CrtUser;

    /**
     *
     */
    @TableField("LastUser")
    private String LastUser;

    /**
     *
     */
    @TableField("Owner")
    private String Owner;

    /**
     *
     */
    @TableField("RecEnt")
    private String RecEnt;

    /**
     *
     */
    @TableField("RecOU")
    private String RecOU;

    /**
     *
     */
    @TableField("RecSec")
    private Integer RecSec;

    /**
     *
     */
    @TableField("CntID")
    private Integer CntID;

    /**
     *
     */
    @TableId(value = "RecID", type = IdType.AUTO)
    private Integer RecID;
}
