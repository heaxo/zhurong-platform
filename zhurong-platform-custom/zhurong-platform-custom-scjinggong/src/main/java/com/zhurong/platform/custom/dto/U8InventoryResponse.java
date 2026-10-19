package com.zhurong.platform.custom.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Data
public class U8InventoryResponse {

    /**
     * itemid
     */
    @JsonProperty("itemid")
    private Long itemid;

    /**
     * AutoID
     */
    @JsonAlias("AutoID")
    private Long autoId;

    /**
     * 仓库编码
     */
    @Getter(onMethod_ = @JsonProperty("cWhCode"))
    @Setter(onMethod_ = @JsonProperty("cWhCode"))
    private String cWhCode;

    /**
     * 物料编码
     */
    @Getter(onMethod_ = @JsonProperty("cInvCode"))
    @Setter(onMethod_ = @JsonProperty("cInvCode"))
    private String cInvCode;

    /**
     * 在库数量
     */
    @Getter(onMethod_ = @JsonProperty("iQuantity"))
    @Setter(onMethod_ = @JsonProperty("iQuantity"))
    private BigDecimal iQuantity;

    /**
     * 需求跟踪方式-编码
     */
    @Getter(onMethod_ = @JsonProperty("iSoType"))
    @Setter(onMethod_ = @JsonProperty("iSoType"))
    private Integer iSoType;

    /**
     * 来源销售订单id
     */
    @JsonProperty("isodid")
    private String isodid;

    /**
     * 需求跟踪方式-名称
     */
    @JsonProperty("enumname")
    private String enumname;

    /**
     * 需求跟踪订单号
     */
    @JsonProperty("csocode")
    private String csocode;

    /**
     * 需求跟踪行号
     */
    @JsonProperty("isoseq")
    private Integer isoseq;

    /**
     * 需求分类代码
     */
    @JsonProperty("cdemandmemo")
    private String cdemandmemo;
}
