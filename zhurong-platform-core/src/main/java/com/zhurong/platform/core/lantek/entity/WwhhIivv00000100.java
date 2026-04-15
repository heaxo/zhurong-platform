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
@TableName("WWHH_IIVV_00000100")
public class WwhhIivv00000100 extends BaseEntity implements Serializable {

private static final long serialVersionUID = 1L;


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
            @TableField("StockQ")
            private Double StockQ;

        /**
        * 
        */
            @TableField("AllocatedQ")
            private Double AllocatedQ;

        /**
        * 
        */
            @TableField("OnOrderQ")
            private Double OnOrderQ;

        /**
        * 
        */
            @TableField("PendingQ")
            private Double PendingQ;

        /**
        * 
        */
            @TableField("UpdMethod")
            private Integer UpdMethod;

        /**
        * 
        */
            @TableField("IsDefault")
            private Byte IsDefault;

        /**
        * 
        */
            @TableField("MinStock")
            private Double MinStock;

        /**
        * 
        */
            @TableField("MinOrder")
            private Double MinOrder;

        /**
        * 
        */
            @TableField("MaxOrder")
            private Double MaxOrder;

        /**
        * 
        */
            @TableField("MultOrder")
            private Double MultOrder;

        /**
        * 
        */
            @TableField("Strategy")
            private Integer Strategy;

        /**
        * 
        */
            @TableField("LocDefault")
            private String LocDefault;

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
