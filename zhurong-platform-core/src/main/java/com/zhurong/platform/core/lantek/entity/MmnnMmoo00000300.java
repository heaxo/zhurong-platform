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
 * @author me
 * @since 2026-03-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("MMNN_MMOO_00000300")
public class MmnnMmoo00000300 extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     *
     */
    @TableField("MnORef")
    private String MnORef;

    /**
     *
     */
    @TableField("OprID")
    private Integer OprID;

    /**
     *
     */
    @TableField("NextOprID")
    private Integer NextOprID;

    /**
     *
     */
    @TableField("RouID")
    private Integer RouID;

    /**
     *
     */
    @TableField("NextRouID")
    private Integer NextRouID;

    /**
     *
     */
    @TableField("PrdRef")
    private String PrdRef;

    /**
     *
     */
    @TableField("PrdRefOrg")
    private String PrdRefOrg;

    /**
     *
     */
    @TableField("NComp")
    private Integer NComp;

    /**
     *
     */
    @TableField("PrdRefDst")
    private String PrdRefDst;

    /**
     *
     */
    @TableField("PrdNameDst")
    private String PrdNameDst;

    /**
     *
     */
    @TableField("RouRef")
    private String RouRef;

    /**
     *
     */
    @TableField("OOrder")
    private Integer OOrder;

    /**
     *
     */
    @TableField("WrkRef")
    private String WrkRef;

    /**
     *
     */
    @TableField("OprRef")
    private String OprRef;

    /**
     *
     */
    @TableField("PPriority")
    private Integer PPriority;

    /**
     *
     */
    @TableField("Origin")
    private Integer Origin;

    /**
     *
     */
    @TableField("OrdRef")
    private String OrdRef;

    /**
     *
     */
    @TableField("OLineNum")
    private String OLineNum;

    /**
     *
     */
    @TableField("CusRef")
    private String CusRef;

    /**
     *
     */
    @TableField("CusName")
    private String CusName;

    /**
     *
     */
    @TableField("CDate")
    private LocalDateTime CDate;

    /**
     *
     */
    @TableField("RDate")
    private LocalDateTime RDate;

    /**
     *
     */
    @TableField("IsEnd")
    private Byte IsEnd;

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
    @TableField("Quantity")
    private Double Quantity;

    /**
     *
     */
    @TableField("AQ")
    private Double AQ;

    /**
     *
     */
    @TableField("MQ")
    private Double MQ;

    /**
     *
     */
    @TableField("LQ")
    private Double LQ;

    /**
     *
     */
    @TableField("ToOutQ")
    private Double ToOutQ;

    /**
     *
     */
    @TableField("OutQ")
    private Double OutQ;

    /**
     *
     */
    @TableField("MState")
    private Integer MState;

    /**
     *
     */
    @TableField("RQ")
    private Double RQ;

    /**
     *
     */
    @TableField("OState")
    private Integer OState;

    /**
     *
     */
    @TableField("EDuration")
    private Double EDuration;

    /**
     *
     */
    @TableField("IOrder")
    private Integer IOrder;

    /**
     *
     */
    @TableField("SDate")
    private LocalDateTime SDate;

    /**
     *
     */
    @TableField("EDate")
    private LocalDateTime EDate;

    /**
     *
     */
    @TableField("PartialSDate")
    private LocalDateTime PartialSDate;

    /**
     *
     */
    @TableField("ESDate")
    private LocalDateTime ESDate;

    /**
     *
     */
    @TableField("EEDate")
    private LocalDateTime EEDate;

    /**
     *
     */
    @TableField("Descrip")
    private String Descrip;

    /**
     *
     */
    @TableField("StdTime")
    private Double StdTime;

    /**
     *
     */
    @TableField("RunTime")
    private Double RunTime;

    /**
     *
     */
    @TableField("CurTime")
    private Double CurTime;

    /**
     *
     */
    @TableField("WrkTime")
    private Double WrkTime;

    /**
     *
     */
    @TableField("WrkCap")
    private Double WrkCap;

    /**
     *
     */
    @TableField("StdCostU")
    private Double StdCostU;

    /**
     *
     */
    @TableField("CurCostU")
    private Double CurCostU;

    /**
     *
     */
    @TableField("StdCostT")
    private Double StdCostT;

    /**
     *
     */
    @TableField("CurCostT")
    private Double CurCostT;

    /**
     *
     */
    @TableField("MainOpr")
    private Integer MainOpr;

    /**
     *
     */
    @TableField("ParallelOpr")
    private Byte ParallelOpr;

    /**
     *
     */
    @TableField("WrhRefSM")
    private String WrhRefSM;

    /**
     *
     */
    @TableField("LineNumSM")
    private String LineNumSM;

    /**
     *
     */
    @TableField("OutOrdRef")
    private String OutOrdRef;

    /**
     *
     */
    @TableField("OutLineNum")
    private String OutLineNum;

    /**
     *
     */
    @TableField("SuppRef")
    private String SuppRef;

    /**
     *
     */
    @TableField("SuppName")
    private String SuppName;

    /**
     *
     */
    @TableField("CurName")
    private String CurName;

    /**
     *
     */
    @TableField("TarRef")
    private String TarRef;

    /**
     *
     */
    @TableField("MinQuan")
    private Double MinQuan;

    /**
     *
     */
    @TableField("IsOutsourcing")
    private Byte IsOutsourcing;

    /**
     *
     */
    @TableField("CurCostA")
    private Double CurCostA;

    /**
     *
     */
    @TableField("MainOriginFilter")
    private String MainOriginFilter;

    /**
     *
     */
    @TableField("MainOriginNameFilter")
    private String MainOriginNameFilter;

    /**
     *
     */
    @TableField("WorkPackageFilter")
    private String WorkPackageFilter;

    /**
     *
     */
    @TableField("WorkPackageNameFilter")
    private String WorkPackageNameFilter;

    /**
     *
     */
    @TableField("MainOriginTypeFilter")
    private Integer MainOriginTypeFilter;

    /**
     *
     */
    @TableField("MainPackage")
    private String MainPackage;

    /**
     *
     */
    @TableField("MainPackageName")
    private String MainPackageName;

    /**
     *
     */
    @TableField("DIS_PGroup")
    private String DIS_PGroup;

    /**
     *
     */
    @TableField("DIS_MatRef")
    private String DIS_MatRef;

    /**
     *
     */
    @TableField("DIS_Thickness")
    private Double DIS_Thickness;

    /**
     *
     */
    @TableField("DIS_JobRef")
    private String DIS_JobRef;

    /**
     *
     */
    @TableField("DIS_FPosition")
    private String DIS_FPosition;

    /**
     *
     */
    @TableField("DIS_NQ")
    private Double DIS_NQ;

    /**
     *
     */
    @TableField("DIS_IsChanged")
    private Byte DIS_IsChanged;

    /**
     *
     */
    @TableField("DIS_IsDuct")
    private Byte DIS_IsDuct;

    /**
     *
     */
    @TableField("DIS_Is2DSOp")
    private Byte DIS_Is2DSOp;

    /**
     *
     */
    @TableField("DIS_Is3DSOp")
    private Byte DIS_Is3DSOp;

    /**
     *
     */
    @TableField("DIS_IsQuote")
    private Byte DIS_IsQuote;

    /**
     *
     */
    @TableField("DIS_WrkCfg")
    private String DIS_WrkCfg;

    /**
     *
     */
    @TableField("WosStatus")
    private Integer WosStatus;

    /**
     *
     */
    @TableField("DIS_NonCuttingJob")
    private String DIS_NonCuttingJob;

    /**
     *
     */
    @TableField("DIS_OrgOprID")
    private Integer DIS_OrgOprID;

    /**
     *
     */
    @TableField("DIS_MStateCloudStatus")
    private Integer DIS_MStateCloudStatus;

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
