package com.zhurong.platform.core.clientimport.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@TableName("Zhurong_PartDrawingArchive")
public class PartDrawingArchive extends ClientImportRecordBase {

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
