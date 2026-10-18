package com.zhurong.platform.custom.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 直接导入Expert的库存行。
 * <p>
 * 物料主数据字段来自MES视图，库存字段来自U8接口；该对象不经过钢板库存中间表。
 */
@Data
public class ScjinggongInventoryDirectDTO {

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

    // 保留U8接口当前支持的全部字段，便于后续直接扩展前端展示。
    private Long itemid;
    private Long autoId;
    private String cWhCode;
    private String cInvCode;
    private BigDecimal iQuantity;
    private Integer iSoType;
    private String isodid;
    private String enumname;
    private String csocode;
    private Integer isoseq;
    private String cdemandmemo;
}
