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
@TableName("DIS_PPTT_TTRR_00000800")
public class DisPpttTtrr00000800 extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;


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
    @TableField("TNumber")
    private Integer TNumber;

    /**
     *
     */
    @TableField("MltRef")
    private String MltRef;

    /**
     *
     */
    @TableField("StTRef")
    private String StTRef;

    /**
     *
     */
    @TableField("Autoindex")
    private Byte Autoindex;

    /**
     *
     */
    @TableField("DMin")
    private Double DMin;

    /**
     *
     */
    @TableField("DMax")
    private Double DMax;

    /**
     *
     */
    @TableField("ZClamp")
    private Integer ZClamp;

    /**
     *
     */
    @TableField("ZLeft")
    private Double ZLeft;

    /**
     *
     */
    @TableField("ZRight")
    private Double ZRight;

    /**
     *
     */
    @TableField("ZTop")
    private Double ZTop;

    /**
     *
     */
    @TableField("OffsetXMin")
    private Double OffsetXMin;

    /**
     *
     */
    @TableField("OffsetXMax")
    private Double OffsetXMax;

    /**
     *
     */
    @TableField("OffsetYMin")
    private Double OffsetYMin;

    /**
     *
     */
    @TableField("OffsetYMax")
    private Double OffsetYMax;

    /**
     *
     */
    @TableField("Plane")
    private Integer Plane;

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
