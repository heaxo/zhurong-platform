package com.ao.platform.core.lantek.entity;

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
@TableName("DIS_NEST_NEST_00000400")
public class DisNestNest00000400 extends BaseEntity implements Serializable {

private static final long serialVersionUID = 1L;


        /**
        * 
        */
            @TableField("NstRef")
            private String NstRef;

        /**
        * 
        */
            @TableField("WrkRef")
            private String WrkRef;

        /**
        * 
        */
            @TableField("TurrRef")
            private String TurrRef;

        /**
        * 
        */
            @TableField("TPosition")
            private Integer TPosition;

        /**
        * 
        */
            @TableField("MltRef")
            private String MltRef;

        /**
        * 
        */
            @TableField("MPosition")
            private Integer MPosition;

        /**
        * 
        */
            @TableField("PunchRef")
            private String PunchRef;

        /**
        * 
        */
            @TableField("DieRef")
            private String DieRef;

        /**
        * 
        */
            @TableField("PAngle")
            private Double PAngle;

        /**
        * 
        */
            @TableField("IsUsed")
            private Byte IsUsed;

        /**
        * 
        */
            @TableField("NumOpN")
            private Integer NumOpN;

        /**
        * 
        */
            @TableField("NumOpR")
            private Integer NumOpR;

        /**
        * 
        */
            @TableField("ToolClass")
            private Integer ToolClass;

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
            @TableId(value = "RecID",type = IdType.AUTO)
            private Integer RecID;
}
