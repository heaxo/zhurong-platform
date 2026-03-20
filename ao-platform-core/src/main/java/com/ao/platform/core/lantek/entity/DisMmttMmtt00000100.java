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
@TableName("DIS_MMTT_MMTT_00000100")
public class DisMmttMmtt00000100 extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     *
     */
    @TableField("MatRef")
    private String MatRef;

    /**
     *
     */
    @TableField("Density")
    private Double Density;

    /**
     *
     */
    @TableField("Strength")
    private Double Strength;

    /**
     *
     */
    @TableField("MFactor")
    private Double MFactor;

    /**
     *
     */
    @TableField("Price")
    private Double Price;

    /**
     *
     */
    @TableField("IsMain")
    private Byte IsMain;

    /**
     *
     */
    @TableField("IntRef")
    private String IntRef;

    /**
     *
     */
    @TableField("Descrip")
    private String Descrip;

    /**
     *
     */
    @TableField("PriceDate")
    private LocalDateTime PriceDate;

    /**
     *
     */
    @TableField("CGroup")
    private String CGroup;

    /**
     *
     */
    @TableField("ScrapPrice")
    private Double ScrapPrice;

    /**
     *
     */
    @TableField("ScrapPriceDate")
    private LocalDateTime ScrapPriceDate;

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
