package com.zhurong.platform.custom.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * DTO
 */
@Data
public class BasepartRequestDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 物料编码
     */
    @JsonProperty("PrdRefDst")
    private String prdRef;

    /**
     * 物料名称
     */
    @JsonProperty("PrdName")
    private String prdName;

    /**
     * 机床
     */
    @JsonProperty("Machine")
    private String wrkRef;

    /**
     * 材质
     */
    @JsonProperty("Material")
    private String matRef;

    /**
     * 厚度
     */
    @JsonProperty("Thickness")
    private Float thickness;

    /**
     * 数量
     */
    @JsonProperty("Quantity")
    private Integer quantity;

    /**
     * 层级
     */
    @JsonProperty("UserData1")
    private String udata1;

    /**
     * 客户件号
     */
    @JsonProperty("UserData1")
    private String udata2;

    /**
     * 物料参数
     */
    @JsonProperty("UserData3")
    private String udata3;

    /**
     * 工艺路线集合
     */
    @JsonProperty("UserData4")
    private String udata4;

    /**
     * 子件物料编码
     */
    @JsonProperty("UserData5")
    private String udata5;

    /**
     * 子件物料名称
     */
    @JsonProperty("UserData6")
    private String udata6;

    /**
     * 子件物料规格
     */
    @JsonProperty("UserData7")
    private String udata7;

    /**
     * 子件物料材质
     */
    @JsonProperty("UserData8")
    private String udata8;

    /**
     * 原始图纸路径
     */
    @JsonProperty("DrawPath")
    private String rawDrawingPath;

}


