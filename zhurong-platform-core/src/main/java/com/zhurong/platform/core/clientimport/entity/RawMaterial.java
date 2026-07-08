package com.zhurong.platform.core.clientimport.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@TableName("Zhurong_RawMaterial")
public class RawMaterial extends ClientImportRecordBase {

    @TableField("PrdRef")
    private String prdRef;

    @TableField("PrdName")
    private String prdName;

    @TableField("MatRef")
    private String matRef;

    @TableField("Thickness")
    private BigDecimal thickness;

    @TableField("Length")
    private BigDecimal length;

    @TableField("Width")
    private BigDecimal width;

    @TableField("Quantity")
    private Integer quantity;

    @TableField("UData1")
    private String udata1;

    @TableField("UData2")
    private String udata2;

    @TableField("UData3")
    private String udata3;

    @TableField("IsRemnant")
    private Boolean isRemnant;
}
