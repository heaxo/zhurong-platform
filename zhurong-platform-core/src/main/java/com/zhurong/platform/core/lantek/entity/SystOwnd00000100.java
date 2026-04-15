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
@TableName("SYST_OWND_00000100")
public class SystOwnd00000100 extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     *
     */
    @TableField("DocDsc")
    private String DocDsc;

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
    @TableField("SType")
    private Integer SType;

    /**
     *
     */
    @TableField("FFType")
    private String FFType;

    /**
     *
     */
    @TableField("FFName")
    private String FFName;

    /**
     *
     */
    @TableField("FFDate")
    private LocalDateTime FFDate;

    /**
     *
     */
    @TableField("FFSize")
    private Double FFSize;

    /**
     *
     */
    @TableField("FFVault")
    private String FFVault;

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
