package com.zhurong.platform.core.clientimport.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

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
    private BigDecimal thickness;

    @TableField("WrkRef")
    private String wrkRef;

    @TableField("MnORef")
    private String mnORef;

    @TableField("OrdRef")
    private String ordRef;

    @TableField("CusRef")
    private String cusRef;

    @TableField("Quantity")
    private Integer quantity;
}
