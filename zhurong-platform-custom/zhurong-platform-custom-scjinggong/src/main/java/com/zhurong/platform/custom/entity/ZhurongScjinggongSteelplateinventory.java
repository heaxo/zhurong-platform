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
     * 钢板编码
     */
    @TableField("sht_ref")
    private String shtRef;

    /**
     * 材质
     */
    @TableField("mat_ref")
    private String matRef;

    /**
     * 厚度
     */
    @TableField("thickness")
    private Float thickness;

    /**
     * 长度
     */
    @TableField("length")
    private Float length;

    /**
     * 宽度
     */
    @TableField("width")
    private Float width;

    /**
     * 数量
     */
    @TableField("quantity")
    private Integer quantity;

    /**
     *
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
     *
     */
    @TableField("udata4")
    private String udata4;

    /**
     *
     */
    @TableField("udata5")
    private String udata5;

    /**
     *
     */
    @TableField("udata6")
    private String udata6;

    /**
     *
     */
    @TableField("udata7")
    private String udata7;

    /**
     *
     */
    @TableField("udata8")
    private String udata8;

    /**
     *
     */
    @TableField("udata9")
    private String udata9;

    /**
     *
     */
    @TableField("udata10")
    private String udata10;

    /**
     *
     */
    @TableField("udata11")
    private String udata11;

    /**
     *
     */
    @TableField("udata12")
    private String udata12;

    /**
     *
     */
    @TableField("udata13")
    private String udata13;

    /**
     *
     */
    @TableField("udata14")
    private String udata14;

    /**
     *
     */
    @TableField("udata15")
    private String udata15;

    /**
     *
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
