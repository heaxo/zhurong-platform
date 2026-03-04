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
@TableName("DIS_SHPR_PPTT_00000200")
public class DisShprPptt00000200 extends BaseEntity implements Serializable {

private static final long serialVersionUID = 1L;


        /**
        * 
        */
        @TableField("PartRef")
            private String PartRef;

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
        @TableField("MatRef")
            private String MatRef;

        /**
        * 
        */
        @TableField("Thickness")
            private Double Thickness;

        /**
        * 
        */
        @TableField("MCode")
            private String MCode;

        /**
        * 
        */
        @TableField("MsgDesc")
            private String MsgDesc;

        /**
        * 
        */
        @TableField("MsgNum")
            private Integer MsgNum;

        /**
        * 
        */
        @TableField("ValType")
            private Integer ValType;

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
        @TableField("DValue")
            private Double DValue;

        /**
        * 
        */
        @TableField("CValue")
            private Double CValue;

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
