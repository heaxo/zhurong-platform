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
@TableName("DIS_NEST_NEST_00000300")
public class DisNestNest00000300 extends BaseEntity implements Serializable {

private static final long serialVersionUID = 1L;


        /**
        * 
        */
        @TableField("NstRef")
            private String NstRef;

        /**
        * 
        */
        @TableField("ShtRef")
            private String ShtRef;

        /**
        * 
        */
        @TableField("RIndex")
            private Integer RIndex;

        /**
        * 
        */
        @TableField("Quantity")
            private Integer Quantity;

        /**
        * 
        */
        @TableField("Var1")
            private String Var1;

        /**
        * 
        */
        @TableField("Var2")
            private String Var2;

        /**
        * 
        */
        @TableField("Var3")
            private String Var3;

        /**
        * 
        */
        @TableField("Var4")
            private String Var4;

        /**
        * 
        */
        @TableField("Var5")
            private String Var5;

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
