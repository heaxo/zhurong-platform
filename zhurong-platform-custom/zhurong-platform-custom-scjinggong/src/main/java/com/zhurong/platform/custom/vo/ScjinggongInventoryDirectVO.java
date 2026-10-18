package com.zhurong.platform.custom.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * MES物料主数据视图展示对象。
 */
@Data
public class ScjinggongInventoryDirectVO {

    private String invClassCode;
    private String invClassName;
    private String invCode;
    private String invName;
    private String invSize;
    private String invMaterialCode;
    private BigDecimal invDensity;
    private BigDecimal invLong;
    private BigDecimal invThick;
    private BigDecimal invWide;
}
