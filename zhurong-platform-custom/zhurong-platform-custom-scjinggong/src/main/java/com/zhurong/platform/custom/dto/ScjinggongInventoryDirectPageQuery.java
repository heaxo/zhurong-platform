package com.zhurong.platform.custom.dto;

import com.zhurong.platform.base.model.BasePageQuery;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * MES物料主数据视图分页查询条件。
 */
@Getter
@Setter
public class ScjinggongInventoryDirectPageQuery extends BasePageQuery {

    private String invCode;
    private String invName;
    private String invSize;
    private String invMaterialCode;
    private BigDecimal invLongMin;
    private BigDecimal invLongMax;
    private BigDecimal invWideMin;
    private BigDecimal invWideMax;
    private BigDecimal invThickMin;
    private BigDecimal invThickMax;

    public ScjinggongInventoryDirectPageQuery() {
        setPageSize(50L);
    }
}
