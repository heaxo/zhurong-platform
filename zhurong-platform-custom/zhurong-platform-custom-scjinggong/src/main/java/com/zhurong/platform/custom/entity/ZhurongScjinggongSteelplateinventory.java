package com.zhurong.platform.custom.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zhurong.platform.custom.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 *
 *
 * @author me
 * @since 2026-10-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("Zhurong_Scjinggong_SteelPlateInventory")
public class ZhurongScjinggongSteelplateinventory extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     *
     */
    @TableField("invalid_state")
    private Boolean invalidState;

    /**
     * 物料编码 cInvCode
     */
    @TableField("sht_ref")
    private String shtRef;

    /**
     * 存货材质 Inv_MaterialCode
     */
    @TableField("mat_ref")
    private String matRef;

    /**
     * 厚度 INV_THICK
     */
    @TableField("thickness")
    private Float thickness;

    /**
     * 长度 INV_LONG
     */
    @TableField("length")
    private Float length;

    /**
     * 宽度 INV_WIDE
     */
    @TableField("width")
    private Float width;

    /**
     * iQuantity 在库数量
     */
    @TableField("quantity")
    private Integer quantity;

    /**
     * cWhCode 仓库编码
     */
    @TableField("udata1")
    private String udata1;

    /**
     *
     */
    @TableField("udata2")
    private String udata2;

    /**
     *
     */
    @TableField("udata3")
    private String udata3;

    /**
     * itemid
     */
    @TableField("udata4")
    private String udata4;

    /**
     * AutoID
     */
    @TableField("udata5")
    private String udata5;

    /**
     * iSoType 需求跟踪方式-编码
     */
    @TableField("udata6")
    private String udata6;

    /**
     * isodid 来源销售订单id
     */
    @TableField("udata7")
    private String udata7;

    /**
     * csocode 需求跟踪订单号
     */
    @TableField("udata8")
    private String udata8;

    /**
     * enumname 需求跟踪方式-名称
     */
    @TableField("udata9")
    private String udata9;

    /**
     * cdemandmemo 需求分类代码
     */
    @TableField("udata10")
    private String udata10;

    /**
     * isoseq 需求跟踪行号
     */
    @TableField("udata11")
    private String udata11;

    /**
     * INV_NAME 物料名称
     */
    @TableField("udata12")
    private String udata12;

    /**
     * Inv_ClassName 存货分类名称
     */
    @TableField("udata13")
    private String udata13;

    /**
     * Inv_ClassCode 存货分类编码
     */
    @TableField("udata14")
    private String udata14;

    /**
     * INV_SIZE 规格尺寸
     */
    @TableField("udata15")
    private String udata15;

    /**
     * Inv_Density 物料密度g/cm^3
     */
    @TableField("udata16")
    private String udata16;

    /**
     *
     */
    @TableField("udata17")
    private String udata17;

    /**
     *
     */
    @TableField("udata18")
    private String udata18;

    /**
     *
     */
    @TableField("udata19")
    private String udata19;

    /**
     *
     */
    @TableField("udata20")
    private String udata20;
}
