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
@TableName("DIS_MMNN_MMOO_00000200")
public class DisMmnnMmoo00000200 extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     *
     */
    @TableField("JobRef")
    private String JobRef;

    /**
     *
     */
    @TableField("MState")
    private Integer MState;

    /**
     *
     */
    @TableField("CDate")
    private LocalDateTime CDate;

    /**
     *
     */
    @TableField("JGroup")
    private String JGroup;

    /**
     *
     */
    @TableField("UData1")
    private String UData1;

    /**
     *
     */
    @TableField("UData2")
    private String UData2;

    /**
     *
     */
    @TableField("UData3")
    private String UData3;

    /**
     *
     */
    @TableField("UData4")
    private String UData4;

    /**
     *
     */
    @TableField("UData5")
    private String UData5;

    /**
     *
     */
    @TableField("WrkRef")
    private String WrkRef;

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
    @TableField("Descrip")
    private String Descrip;

    /**
     *
     */
    @TableField("IsQuote")
    private Byte IsQuote;

    /**
     *
     */
    @TableField("CusRef")
    private String CusRef;

    /**
     *
     */
    @TableField("CusName")
    private String CusName;

    /**
     *
     */
    @TableField("QutRef")
    private String QutRef;

    /**
     *
     */
    @TableField("JobName")
    private String JobName;

    /**
     *
     */
    @TableField("JobOrder")
    private Integer JobOrder;

    /**
     *
     */
    @TableField("RDate")
    private LocalDateTime RDate;

    /**
     *
     */
    @TableField("CamLastDate")
    private LocalDateTime CamLastDate;

    /**
     *
     */
    @TableField("LastQuoteModification")
    private LocalDateTime LastQuoteModification;

    /**
     *
     */
    @TableField("JobElementLastDate")
    private LocalDateTime JobElementLastDate;

    /**
     *
     */
    @TableField("ExternalKey")
    private String ExternalKey;

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
