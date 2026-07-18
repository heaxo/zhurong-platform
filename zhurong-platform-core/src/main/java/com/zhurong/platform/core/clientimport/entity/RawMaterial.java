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
    private Float thickness;

    @TableField("Length")
    private Float length;

    @TableField("Width")
    private Float width;

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
