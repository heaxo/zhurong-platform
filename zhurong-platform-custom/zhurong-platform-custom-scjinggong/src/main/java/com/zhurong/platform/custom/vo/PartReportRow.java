package com.zhurong.platform.custom.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import java.math.BigDecimal;

/** 零件清单的 SQL 结果。Lantek Image 是本机路径，前端仅接收处理后的 data URI。 */
@Data
public class PartReportRow {
    private Integer partSequence;
    private String productCode;
    @JsonIgnore
    private String imagePath;
    private String imageDataUri;
    private BigDecimal quantity;
    private String customerPartNo;
    private String batchNo;
    private String material;
    private BigDecimal thickness;
    private String process;

}
