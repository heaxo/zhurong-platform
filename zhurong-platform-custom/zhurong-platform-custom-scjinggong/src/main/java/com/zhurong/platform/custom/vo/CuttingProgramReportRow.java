package com.zhurong.platform.custom.vo;

import lombok.Data;
import java.math.BigDecimal;

/** 切割程序清单的 SQL 结果；列标题与顺序由前端组件负责。 */
@Data
public class CuttingProgramReportRow {
    private String cnc;
    private String plateSize;
    private String material;
    private BigDecimal quantity;
    private BigDecimal totalWeight;
    private String utilization;
    private String utilizationWithoutRemnant;
}
