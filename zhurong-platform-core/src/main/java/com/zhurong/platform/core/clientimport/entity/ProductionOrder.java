package com.zhurong.platform.core.clientimport.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName("Zhurong_ProductionOrder")
public class ProductionOrder extends ClientImportRecordBase {

    @TableField("PrdRef")
    private String prdRef;

    @TableField("PrdName")
    private String prdName;

    @TableField("MatRef")
    private String matRef;

    @TableField("Thickness")
    private Float thickness;

    @TableField("WrkRef")
    private String wrkRef;

    @TableField("Image")
    private String image;

    @TableField("MnORef")
    private String mnORef;

    @TableField("OrdRef")
    private String ordRef;

    @TableField("CusRef")
    private String cusRef;

    @TableField("Quantity")
    private Integer quantity;

    @TableField("UData1")
    private String udata1;

    @TableField("UData2")
    private String udata2;

    @TableField("UData3")
    private String udata3;

    @TableField("UData4")
    private String udata4;

    @TableField("UData5")
    private String udata5;

    @TableField("UData6")
    private String udata6;

    @TableField("UData7")
    private String udata7;

    @TableField("UData8")
    private String udata8;
}
