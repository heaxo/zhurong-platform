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
@TableName("WWCC_WWCC_00000100")
public class WwccWwcc00000100 extends BaseEntity implements Serializable {

private static final long serialVersionUID = 1L;


        /**
        * 
        */
        @TableField("WrkRef")
            private String WrkRef;

        /**
        * 
        */
        @TableField("WrkOEM")
            private String WrkOEM;

        /**
        * 
        */
        @TableField("WrkCNC")
            private String WrkCNC;

        /**
        * 
        */
        @TableField("IsOutsourcing")
            private Byte IsOutsourcing;

        /**
        * 
        */
        @TableField("WrkCapUCtName")
            private String WrkCapUCtName;

        /**
        * 
        */
        @TableField("WrkCapUntName")
            private String WrkCapUntName;

        /**
        * 
        */
        @TableField("WrkCap")
            private Double WrkCap;

        /**
        * 
        */
        @TableField("Descrip")
            private String Descrip;

        /**
        * 
        */
        @TableField("IsActive")
            private Byte IsActive;

        /**
        * 
        */
        @TableField("DIS_WrkType")
            private Integer DIS_WrkType;

        /**
        * 
        */
        @TableField("DIS_OTechn")
            private Integer DIS_OTechn;

        /**
        * 
        */
        @TableField("DIS_CfgFile")
            private String DIS_CfgFile;

        /**
        * 
        */
        @TableField("DIS_PsfFile")
            private String DIS_PsfFile;

        /**
        * 
        */
        @TableField("DIS_SawRef")
            private String DIS_SawRef;

        /**
        * 
        */
        @TableField("DIS_Group")
            private String DIS_Group;

        /**
        * 
        */
        @TableField("WosMessages")
            private String WosMessages;

        /**
        * 
        */
        @TableField("WosState")
            private Integer WosState;

        /**
        * 
        */
        @TableField("WosStateLastDate")
            private LocalDateTime WosStateLastDate;

        /**
        * 
        */
        @TableField("WosPauseReason")
            private String WosPauseReason;

        /**
        * 
        */
        @TableField("WosOperatorComment")
            private String WosOperatorComment;

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
