package com.zhurong.platform.custom.entity;

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
* @since 2026-04-18
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("WWHH_IIVV_00000200")
public class WwhhIivv00000200 implements Serializable {

private static final long serialVersionUID = 1L;


        /**
        * 
        */
            @TableField("WrhRef")
            private String WrhRef;

        /**
        * 
        */
            @TableField("LocRef")
            private String LocRef;

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
            @TableField("StockID")
            private Integer StockID;

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
            @TableField("ARCost")
            private Double ARCost;

        /**
        * 
        */
            @TableField("StockQ")
            private Double StockQ;

        /**
        * 
        */
            @TableField("Weight")
            private Double Weight;

        /**
        * 
        */
            @TableField("RWeight")
            private Double RWeight;

        /**
        * 
        */
            @TableField("IsSemifinished")
            private Byte IsSemifinished;

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
            @TableField("GLS_SerialNo")
            private String GLS_SerialNo;

        /**
        * 
        */
            @TableField("GLS_BatchNo")
            private String GLS_BatchNo;

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
