package com.zhurong.platform.custom.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * 套料任务请求对象
 */
@Data
public class OrderNestingRequest {

    /**
     * 订单列表
     */
    @JsonProperty("orderMains")
    private List<OrderMain> orderMains;

    /**
     * 套料主信息列表
     */
    @JsonProperty("nestingMains")
    private List<NestingMain> nestingMains;

}