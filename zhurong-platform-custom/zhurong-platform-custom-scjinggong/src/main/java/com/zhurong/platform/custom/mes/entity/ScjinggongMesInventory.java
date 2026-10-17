package com.zhurong.platform.custom.mes.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 亚威MES物料主数据视图。
 */
@Data
@TableName("V_BD_Inventory")
public class ScjinggongMesInventory {

    @TableField("Inv_ClassCode")
    private String invClassCode;

    @TableField("Inv_ClassName")
    private String invClassName;

    @TableField("INV_CODE")
    private String invCode;

    @TableField("INV_NAME")
    private String invName;

    @TableField("INV_SIZE")
    private String invSize;

    @TableField("Inv_MaterialCode")
    private String invMaterialCode;

    @TableField("Inv_Density")
    private BigDecimal invDensity;

    @TableField("INV_LONG")
    private BigDecimal invLong;

    @TableField("INV_THICK")
    private BigDecimal invThick;

    @TableField("INV_WIDE")
    private BigDecimal invWide;
}
