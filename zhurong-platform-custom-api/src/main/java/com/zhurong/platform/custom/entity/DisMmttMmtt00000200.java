package com.zhurong.platform.custom.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zhurong.platform.base.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
* 
*
* @author me
* @since 2026-10-11
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("DIS_MMTT_MMTT_00000200")
public class DisMmttMmtt00000200 extends BaseEntity implements Serializable {

private static final long serialVersionUID = 1L;


        /**
        * 
        */
            @TableField("MatRef")
            private String matRef;

        /**
        * 
        */
            @TableField("MinThickness")
            private Float minThickness;

        /**
        * 
        */
            @TableField("MaxThickness")
            private Float maxThickness;

        /**
        * 
        */
            @TableField("Price")
            private Float price;

        /**
        * 
        */
            @TableField("PriceDate")
            private LocalDateTime priceDate;

        /**
        * 
        */
            @TableField("Descrip")
            private String descrip;

        /**
        * 
        */
            @TableField("RecState")
            private Integer recState;

        /**
        * 
        */
            @TableField("CrtDate")
            private LocalDateTime crtDate;

        /**
        * 
        */
            @TableField("LastDate")
            private LocalDateTime lastDate;

        /**
        * 
        */
            @TableField("CrtUser")
            private String crtUser;

        /**
        * 
        */
            @TableField("LastUser")
            private String lastUser;

        /**
        * 
        */
            @TableField("Owner")
            private String owner;

        /**
        * 
        */
            @TableField("RecEnt")
            private String recEnt;

        /**
        * 
        */
            @TableField("RecOU")
            private String recOU;

        /**
        * 
        */
            @TableField("RecSec")
            private Integer recSec;

        /**
        * 
        */
            @TableField("CntID")
            private Integer cntID;

        /**
        * 
        */
            @TableField("RecID")
            private Integer recID;
}
