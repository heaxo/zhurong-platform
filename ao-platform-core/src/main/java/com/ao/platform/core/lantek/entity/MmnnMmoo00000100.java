package com.ao.platform.core.lantek.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.time.LocalDateTime;
import com.ao.platform.core.lantek.entity.BaseEntity;

/**
* 
*
* @author heao
* @since 2026-03-04
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("MMNN_MMOO_00000100")
public class MmnnMmoo00000100 extends BaseEntity implements Serializable {

private static final long serialVersionUID = 1L;


        /**
        * 
        */
        @TableField("MnORef")
            private String MnORef;

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
        @TableField("RouRef")
            private String RouRef;

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
        @TableField("Priority")
            private Integer Priority;

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
        @TableField("DDate")
            private LocalDateTime DDate;

        /**
        * 
        */
        @TableField("MState")
            private Integer MState;

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
        @TableField("RDate")
            private LocalDateTime RDate;

        /**
        * 
        */
        @TableField("MadeQuan")
            private Double MadeQuan;

        /**
        * 
        */
        @TableField("Cost")
            private Double Cost;

        /**
        * 
        */
        @TableField("Descrip")
            private String Descrip;

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
        @TableField("Planned")
            private Byte Planned;

        /**
        * 
        */
        @TableField("MnORefOrg")
            private String MnORefOrg;

        /**
        * 
        */
        @TableField("MnORoot")
            private String MnORoot;

        /**
        * 
        */
        @TableField("OrderType")
            private Integer OrderType;

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
        @TableField("WorkPackage")
            private String WorkPackage;

        /**
        * 
        */
        @TableField("WorkPackageName")
            private String WorkPackageName;

        /**
        * 
        */
        @TableField("GLS_Var1")
            private String GLS_Var1;

        /**
        * 
        */
        @TableField("GLS_Var2")
            private String GLS_Var2;

        /**
        * 
        */
        @TableField("GLS_Var3")
            private String GLS_Var3;

        /**
        * 
        */
        @TableField("GLS_Var4")
            private String GLS_Var4;

        /**
        * 
        */
        @TableField("GLS_Var5")
            private String GLS_Var5;

        /**
        * 
        */
        @TableField("DIS_IsQuote")
            private Byte DIS_IsQuote;

        /**
        * 
        */
        @TableField("DIS_OutQ")
            private Double DIS_OutQ;

        /**
        * 
        */
        @TableField("DIS_FillerPart")
            private Integer DIS_FillerPart;

        /**
        * 
        */
        @TableField("DIS_RouteAbbreviation")
            private String DIS_RouteAbbreviation;

        /**
        * 
        */
        @TableField("DIS_AutoNesting")
            private Byte DIS_AutoNesting;

        /**
        * 
        */
        @TableField("DIS_AutoNestingStrategy")
            private Integer DIS_AutoNestingStrategy;

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
        @TableField("RecID")
            private Integer RecID;
}
