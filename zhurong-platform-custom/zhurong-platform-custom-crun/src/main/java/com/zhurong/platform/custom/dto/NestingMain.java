package com.zhurong.platform.custom.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

/**
 * 套料主信息
 */
@Data
@Builder
@Jacksonized
@NoArgsConstructor
@AllArgsConstructor
public class NestingMain {

    /**
     * 套料唯一ID
     */
    @JsonProperty("NestingReference")
    private String nestingReference;

    /**
     * 套料对应订单号（多个用 / 分隔）
     */
    @JsonProperty("orderCode")
    private String orderCode;

    /**
     * 材料编号
     */
    @JsonProperty("materialCode")
    private String materialCode;

    /**
     * 程序编号（带后缀）
     */
    @JsonProperty("program")
    private String program;

    /**
     * 钢板长度
     */
    @JsonProperty("length")
    private Double length;

    /**
     * 钢板宽度
     */
    @JsonProperty("width")
    private Double width;

    /**
     * 钢板厚度
     */
    @JsonProperty("height")
    private Double height;

    /**
     * 使用机床（多个用 / 分隔）
     */
    @JsonProperty("machine")
    private String machine;

    /**
     * 材质
     */
    @JsonProperty("material")
    private String material;

    /**
     * 加工机床类型
     * 1 = 激光
     * 2 = 冲床
     */
    @JsonProperty("craft")
    private Integer craft;

    /**
     * 运行次数（钢板数量）
     */
    @JsonProperty("runCount")
    private Integer runCount;

    /**
     * 切割参数
     */
    @JsonProperty("parameter")
    private String parameter;

    /**
     * CNC程序绝对路径
     */
    @JsonProperty("programPath")
    private String programPath;

    /**
     * 套料排版图路径
     */
    @JsonProperty("pickPath")
    private String pickPath;

    /**
     * 套料零件明细
     */
    @JsonProperty("nestingDetails")
    private List<NestingDetail> nestingDetails;

}