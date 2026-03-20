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
@TableName("DIS_NEST_NEST_00000500")
public class DisNestNest00000500 extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     *
     */
    @TableField("NstRef")
    private String NstRef;

    /**
     *
     */
    @TableField("MnORef")
    private String MnORef;

    /**
     *
     */
    @TableField("OprID")
    private Integer OprID;

    /**
     *
     */
    @TableField("PrdRefDst")
    private String PrdRefDst;

    /**
     *
     */
    @TableField("PIndex")
    private Integer PIndex;

    /**
     *
     */
    @TableField("Quantity")
    private Integer Quantity;

    /**
     *
     */
    @TableField("MQ")
    private Integer MQ;

    /**
     *
     */
    @TableField("CostMat")
    private Double CostMat;

    /**
     *
     */
    @TableField("CostMachTime")
    private Double CostMachTime;

    /**
     *
     */
    @TableField("CostConsum")
    private Double CostConsum;

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
    @TableId(value = "RecID", type = IdType.AUTO)
    private Integer RecID;
}
