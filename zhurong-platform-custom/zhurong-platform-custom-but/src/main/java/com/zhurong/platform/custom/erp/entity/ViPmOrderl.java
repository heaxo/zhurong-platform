package com.zhurong.platform.custom.erp.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("VI_PM_ORDERL")
public class ViPmOrderl implements Serializable  {

    private static final long serialVersionUID = 1L;

    @TableField("BELPOS_ID")
    private String belposId;

    @TableField("ITEMCODE")
    private String itemCode;

    @TableField("COUTKEY")
    private String coutKey;

    @TableField("CFCCAD")
    private String cfccad;

    @TableField("CFTYPE")
    private String cfType;

    @TableField("CCAD")
    private String ccad;

    @TableField("ITEMNAME")
    private String itemName;

    @TableField("U_ZNR")
    private String uZnr;

    @TableField("UDF1")
    private Double udf1;

    @TableField("APLATZ_ID")
    private String aplatzId;

    @TableField("MENGE_VERBRAUCH")
    private Double mengeVerbrauch;

    @TableField("ANFZEIT")
    private LocalDateTime anfzeit;

    @TableField("LIEFERDATUM")
    private LocalDateTime lieferdatum;

    @TableField("VVERSION")
    private String vversion;

    @TableField("ICADPRODUCT")
    private String icadproduct;
}