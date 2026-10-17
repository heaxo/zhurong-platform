package com.zhurong.platform.custom.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/*
 * @Author zhurong
 * @Description OrderItemRequestDTO
 * @Date 2026/10/5 21:37
 **/
@Data
public class OrderItemRequestDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 零件编号
     */
    @NotBlank(message = "不能为空")
    @JsonProperty("PrdRef")
    private String prdRef;

    /**
     * 机床
     */
    @NotBlank(message = "不能为空")
    @JsonProperty("Machine")
    private String wrkRef;

    /**
     * MES工单号
     */
    @NotBlank(message = "不能为空")
    @JsonProperty("Cusref")
    private String cusRef;

    /**
     * 生产批次号
     */
    @NotBlank(message = "不能为空")
    @JsonProperty("OrdRef")
    private String ordRef;

    /**
     * 工单数量
     */
    @NotNull(message = "工单数量不能为空")
    @JsonProperty("Quantity")
    private Integer quantity;

    /**
     * 订单交货日期
     */
    @NotNull(message = "订单交货日期不能为空")
    @JsonProperty("ordDlvDt")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime rdate;

    /**
     * 加工中心编码
     */
    @JsonProperty("MachineCode")
    private String udata1;

    /**
     * U8生产订单号
     */
    @NotBlank(message = "不能为空")
    @JsonProperty("mesProdOrdNo")
    private String udata2;

    /**
     * 收料仓库编码
     */
    @NotBlank(message = "不能为空")
    @JsonProperty("rcvWareCd")
    private String udata3;

    /**
     * 收料仓库名称
     */
    @NotBlank(message = "收料仓库名称不能为空")
    @JsonProperty("rcvWareNm")
    private String udata4;

    /**
     * 工序行号
     */
    @NotBlank(message = "工序行号不能为空")
    @JsonProperty("opLineNo")
    private String udata5;

    /**
     * 工序编码
     */
    @NotBlank(message = "工序编码不能为空")
    @JsonProperty("opCd")
    private String udata6;

    /**
     * 工序名称
     */
    @NotBlank(message = "工序名称不能为空")
    @JsonProperty("opNm")
    private String udata7;

    /**
     * 班组编码
     */
    @NotBlank(message = "班组编码不能为空")
    @JsonProperty("tmCd")
    private String udata8;

    /**
     * 班组名称
     */
    @NotBlank(message = "班组名称不能为空")
    @JsonProperty("tmNm")
    private String udata9;

    /**
     * 计划完工时间
     */
    @NotBlank(message = "计划完工时间不能为空")
    @JsonProperty("planFinTm")
    private String udata10;

    /**
     * 订单变更后交期
     */
    @NotBlank(message = "订单变更后交期不能为空")
    @JsonProperty("chgDlvDt")
    private String udata11;

    /**
     * 工单状态
     */
    @NotBlank(message = "工单状态不能为空")
    @JsonProperty("woStatus")
    private String udata12;

    /**
     * 项次
     */
    @NotBlank(message = "项次不能为空")
    @JsonProperty("ItemNum")
    private String udata13;

    /**
     * 唯一序列号
     */
    @NotBlank(message = "唯一序列号不能为空")
    @JsonProperty("GuID")
    private String udata14;

    /**
     * BomList_id
     */
    @NotBlank(message = "BomList_id不能为空")
    @JsonProperty("BomList_id")
    private String udata15;

    /**
     * Prd_UData4
     */
    @NotBlank(message = "Prd_UData4不能为空")
    @JsonProperty("Prd_UData4")
    private String udata16;

    /**
     * Prd_UData5
     */
    @NotBlank(message = "Prd_UData5不能为空")
    @JsonProperty("Prd_UData5")
    private String udata17;

    /**
     * Prd_UData6
     */
    @NotBlank(message = "Prd_UData6不能为空")
    @JsonProperty("Prd_UData6")
    private String udata18;

    /**
     * Prd_UData7
     */
    @NotBlank(message = "Prd_UData7不能为空")
    @JsonProperty("Prd_UData7")
    private String udata19;

    /**
     * Prd_UData8
     */
    @NotBlank(message = "Prd_UData8不能为空")
    @JsonProperty("Prd_UData8")
    private String udata20;

    /**
     * 零件名称
     */
    @NotBlank(message = "零件名称不能为空")
    @JsonProperty("PrdName")
    private String prdName;

    /**
     * 加工材质
     */
    @NotBlank(message = "加工材质不能为空")
    @JsonProperty("MatRef")
    private String matRef;

    /**
     * 加工厚度
     */
    @NotBlank(message = "加工厚度不能为空")
    @JsonProperty("SThickness")
    private Float thickness;

}
