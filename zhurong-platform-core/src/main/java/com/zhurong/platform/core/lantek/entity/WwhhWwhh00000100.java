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
@TableName("WWHH_WWHH_00000100")
public class WwhhWwhh00000100 extends BaseEntity implements Serializable {

private static final long serialVersionUID = 1L;


        /**
        * 
        */
            @TableField("WrhRef")
            private String WrhRef;

        /**
        * 
        */
            @TableField("WrhName")
            private String WrhName;

        /**
        * 
        */
            @TableField("IsOutsourcing")
            private Byte IsOutsourcing;

        /**
        * 
        */
            @TableField("Descrip")
            private String Descrip;

        /**
        * 
        */
            @TableField("DIS_Automatic")
            private Byte DIS_Automatic;

        /**
        * 
        */
            @TableField("DIS_WosWhDriver")
            private String DIS_WosWhDriver;

        /**
        * 
        */
            @TableField("DIS_IsWorkCenter")
            private Byte DIS_IsWorkCenter;

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
