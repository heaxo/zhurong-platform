package com.zhurong.platform.custom.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

/**
 * 订单主信息
 */
@Data
@Builder
@Jacksonized
@NoArgsConstructor
@AllArgsConstructor
public class OrderMain {

    /**
     * 创建时间
     * 格式：yyyy-MM-dd HH:mm:ss
     */
    @JsonProperty("createTime")
    private String createTime;

    /**
     * 订单编号
     */
    @JsonProperty("orderCode")
    private String orderCode;

    /**
     * 项目编号（目前为空）
     */
    @JsonProperty("projectCode")
    private String projectCode;

    /**
     * 是否回料库
     * 0 不回料库
     * 1 回料库
     */
    @JsonProperty("isBack")
    private Integer isBack;

    /**
     * 订单零件明细
     */
    @JsonProperty("details")
    private List<OrderDetail> details;

}