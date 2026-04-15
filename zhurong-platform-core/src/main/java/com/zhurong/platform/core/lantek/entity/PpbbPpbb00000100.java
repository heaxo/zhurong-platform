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
* @since 2026-04-15
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("PPBB_PPBB_00000100")
public class PpbbPpbb00000100 extends BaseEntity implements Serializable {

private static final long serialVersionUID = 1L;


        /**
        * 
        */
            @TableField("MaterialReqID")
            private Integer MaterialReqID;

        /**
        * 
        */
            @TableField("WrhRef")
            private String WrhRef;

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
            @TableField("ElementStatus")
            private Integer ElementStatus;

        /**
        * 
        */
            @TableField("Confirmed")
            private Byte Confirmed;

        /**
        * 
        */
            @TableField("Type")
            private Integer Type;

        /**
        * 
        */
            @TableField("Reference")
            private String Reference;

        /**
        * 
        */
            @TableField("ActRef")
            private String ActRef;

        /**
        * 
        */
            @TableField("LineNum")
            private String LineNum;

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
            @TableField("RDate")
            private LocalDateTime RDate;

        /**
        * 
        */
            @TableField("PlannedDDate")
            private LocalDateTime PlannedDDate;

        /**
        * 
        */
            @TableField("RequiredQ")
            private Double RequiredQ;

        /**
        * 
        */
            @TableField("OnOrderQ")
            private Double OnOrderQ;

        /**
        * 
        */
            @TableField("AllocatedQ")
            private Double AllocatedQ;

        /**
        * 
        */
            @TableField("PendingQ")
            private Double PendingQ;

        /**
        * 
        */
            @TableField("ReceivedQ")
            private Double ReceivedQ;

        /**
        * 
        */
            @TableField("LocRef")
            private String LocRef;

        /**
        * 
        */
            @TableField("RecordID")
            private Integer RecordID;

        /**
        * 
        */
            @TableField("OriginRef")
            private String OriginRef;

        /**
        * 
        */
            @TableField("OriginLineNum")
            private String OriginLineNum;

        /**
        * 
        */
            @TableField("OriginType")
            private Integer OriginType;

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
            @TableField("WorkOrderFilter")
            private String WorkOrderFilter;

        /**
        * 
        */
            @TableField("WorkOrderNameFilter")
            private String WorkOrderNameFilter;

        /**
        * 
        */
            @TableField("MainOriginTypeFilter")
            private Integer MainOriginTypeFilter;

        /**
        * 
        */
            @TableField("GLS_BatchNo")
            private String GLS_BatchNo;

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
            @TableField("DIS_Subclass")
            private Integer DIS_Subclass;

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
            @TableField("DIS_FormatRef")
            private String DIS_FormatRef;

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
            @TableId(value = "RecID",type = IdType.ASSIGN_ID)
            private Integer RecID;
}
