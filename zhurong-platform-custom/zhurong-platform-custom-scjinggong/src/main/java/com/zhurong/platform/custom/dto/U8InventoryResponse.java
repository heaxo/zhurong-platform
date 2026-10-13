package com.zhurong.platform.custom.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class U8InventoryResponse {

    /**
     * itemid
     */
    private Long itemid;

    /**
     * AutoID
     */
    @JsonProperty("AutoID")
    private Long autoId;

    /**
     * 仓库编码
     */
    private String cWhCode;

    /**
     * 物料编码
     */
    private String cInvCode;

    /**
     * 在库数量
     */
    private BigDecimal iQuantity;

    /**
     * 需求跟踪方式-编码
     */
    private Integer iSoType;

    /**
     * 来源销售订单id
     */
    private String isodid;

    /**
     * 需求跟踪方式-名称
     */
    private String enumname;

    /**
     * 需求跟踪订单号
     */
    private String csocode;

    /**
     * 需求跟踪行号
     */
    private Integer isoseq;

    /**
     * 需求分类代码
     */
    private String cdemandmemo;
}
