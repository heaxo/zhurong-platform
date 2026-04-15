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
 * @since 2026-03-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("DIS_PPTT_TTRR_00000700")
public class DisPpttTtrr00000700 extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     *
     */
    @TableField("TurrRef")
    private String TurrRef;

    /**
     *
     */
    @TableField("TrType")
    private Integer TrType;

    /**
     *
     */
    @TableField("TrCategory")
    private Integer TrCategory;

    /**
     *
     */
    @TableField("Descrip")
    private String Descrip;

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
    @TableId(value = "RecID", type = IdType.ASSIGN_ID)
    private Integer RecID;
}
