package com.ao.platform.custom.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

/**
 * 订单零件明细
 */

@Data
@Builder
@Jacksonized
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail {

    /**
     * 零件编号
     */
    @JsonProperty("partCode")
    private String partCode;

    /**
     * 零件名称
     */
    @JsonProperty("partName")
    private String partName;

    /**
     * 零件数量（订单总数量）
     */
    @JsonProperty("partNum")
    private Integer partNum;

    /**
     * 加工机床类型
     * 1 = 激光
     * 2 = 冲床
     */
    @JsonProperty("craft")
    private Integer craft;

}