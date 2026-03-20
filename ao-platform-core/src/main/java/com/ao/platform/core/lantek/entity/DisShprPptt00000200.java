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
@TableName("DIS_SHPR_PPTT_00000200")
public class DisShprPptt00000200 extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     *
     */
    @TableField("PartRef")
    private String PartRef;

    /**
     *
     */
    @TableField("WrkRef")
    private String WrkRef;

    /**
     *
     */
    @TableField("OprRef")
    private String OprRef;

    /**
     *
     */
    @TableField("MatRef")
    private String MatRef;

    /**
     *
     */
    @TableField("Thickness")
    private Double Thickness;

    /**
     *
     */
    @TableField("MCode")
    private String MCode;

    /**
     *
     */
    @TableField("MsgDesc")
    private String MsgDesc;

    /**
     *
     */
    @TableField("MsgNum")
    private Integer MsgNum;

    /**
     *
     */
    @TableField("ValType")
    private Integer ValType;

    /**
     *
     */
    @TableField("UCtName")
    private String UCtName;

    /**
     *
     */
    @TableField("UntName")
    private String UntName;

    /**
     *
     */
    @TableField("DValue")
    private Double DValue;

    /**
     *
     */
    @TableField("CValue")
    private Double CValue;

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
