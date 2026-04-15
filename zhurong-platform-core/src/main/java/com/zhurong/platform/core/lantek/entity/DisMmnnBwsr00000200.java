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
* @since 2026-04-04
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("DIS_MMNN_BWSR_00000200")
public class DisMmnnBwsr00000200 extends BaseEntity implements Serializable {

private static final long serialVersionUID = 1L;


        /**
        * 
        */
            @TableField("BwsrID")
            private Integer BwsrID;

        /**
        * 
        */
            @TableField("TblRef")
            private String TblRef;

        /**
        * 
        */
            @TableField("RecordID")
            private Integer RecordID;

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
