package com.zhurong.platform.custom.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 盛驰套料接收接口的零件明细。
 */
@Data
public class ScjinggongNestFeedbackPartDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("PrdRef")
    private String prdRef;
    @JsonProperty("Machine")
    private String machine;
    @JsonProperty("MachineCode")
    private String machineCode;
    @JsonProperty("Quantity")
    private Integer quantity;
    @JsonProperty("OrdRef")
    private String ordRef;
    @JsonProperty("Cusref")
    private String cusRef;
    private String mesProdOrdNo;
    private String rcvWareCd;
    private String rcvWareNm;
    private String opLineNo;
    private String opCd;
    private String opNm;
    private String tmCd;
    private String tmNm;
    private String ordDlvDt;
    private String planFinTm;
    private String chgDlvDt;
    private String woStatus;
    @JsonProperty("ItemNum")
    private String itemNum;
    @JsonProperty("GuID")
    private String guId;
    @JsonProperty("PrdName")
    private String prdName;
    @JsonProperty("MatRef")
    private String matRef;
    @JsonProperty("SThickness")
    private String thickness;
    @JsonProperty("Qty")
    private String qty;
    @JsonProperty("PartNo")
    private String partNo;
    @JsonProperty("BomList_id")
    private String bomListId;
    @JsonProperty("Prd_UData3")
    private String prdUData3;
    @JsonProperty("Prd_UData4")
    private String prdUData4;
    @JsonProperty("Prd_UData5")
    private String prdUData5;
    @JsonProperty("Prd_UData6")
    private String prdUData6;
    @JsonProperty("Prd_UData7")
    private String prdUData7;
    @JsonProperty("Prd_UData8")
    private String prdUData8;
    @JsonProperty("PrdPerimeter")
    private String prdPerimeter;
}
