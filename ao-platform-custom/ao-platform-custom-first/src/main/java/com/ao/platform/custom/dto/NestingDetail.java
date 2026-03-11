package com.ao.platform.custom.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

/**
 * 套料零件明细
 */
@Data
@Builder
@Jacksonized
@NoArgsConstructor
@AllArgsConstructor
public class NestingDetail {

    /**
     * 订单编号
     */
    @JsonProperty("orderCode")
    private String orderCode;

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
     * 零件长度
     */
    @JsonProperty("partLength")
    private Double partLength;

    /**
     * 零件宽度
     */
    @JsonProperty("partWidth")
    private Double partWidth;

    /**
     * 零件厚度
     */
    @JsonProperty("partHeight")
    private Double partHeight;

    /**
     * 零件材质
     */
    @JsonProperty("partMaterial")
    private String partMaterial;

    /**
     * 零件数量
     */
    @JsonProperty("partNum")
    private Integer partNum;

    /**
     * 零件图片路径
     */
    @JsonProperty("partPath")
    private String partPath;

}