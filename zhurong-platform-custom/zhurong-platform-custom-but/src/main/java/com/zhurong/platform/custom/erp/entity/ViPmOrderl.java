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

    /**
     * 订单编号
     */
    @TableField("BELPOS_ID")
    private String belposId;

    /**
     * 物料编号
     */
    @TableField("ITEMCODE")
    private String itemCode;

    /**
     * SAP分厂
     */
    @TableField("COUTKEY")
    private String coutKey;

    /**
     * 总成图号
     */
    @TableField("CFCCAD")
    private String cfccad;

    /**
     * 总成型号
     */
    @TableField("CFTYPE")
    private String cfType;

    /**
     * 零件编号
     */
    @TableField("CCAD")
    private String ccad;

    /**
     * 零件名称
     */
    @TableField("ITEMNAME")
    private String itemName;

    /**
     * 材质
     */
    @TableField("U_ZNR")
    private String uZnr;

    /**
     * 厚度
     */
    @TableField("UDF1")
    private Double udf1;

    /**
     * 机床
     */
    @TableField("APLATZ_ID")
    private String aplatzId;

    /**
     * 数量
     */
    @TableField("MENGE_VERBRAUCH")
    private Double mengeVerbrauch;

    /**
     * 开始时间
     */
    @TableField("ANFZEIT")
    private LocalDateTime anfzeit;

    /**
     *交货期
     */
    @TableField("LIEFERDATUM")
    private LocalDateTime lieferdatum;

    /**
     * 生产计划批注
     */
    @TableField("VVERSION")
    private String vversion;

    /**
     * 总成内码
     */
    @TableField("ICADPRODUCT")
    private String icadproduct;
}