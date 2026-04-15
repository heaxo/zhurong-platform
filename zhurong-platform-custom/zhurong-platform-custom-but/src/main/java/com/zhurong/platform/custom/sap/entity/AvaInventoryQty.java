package com.zhurong.platform.custom.sap.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@TableName("SBO_BUT.AVA_INVENTOYR_QTY")
@Accessors(chain = true)
public class AvaInventoryQty {

    @TableField("\"ItemCode\"")
    private String itemCode;

    @TableField("\"ItemName\"")
    private String itemName;

    @TableField("\"U_beas_znr\"")
    private String uBeasZnr;

    @TableField("\"U_HD\"")
    private String uHd;

    @TableField("\"BatchNum\"")
    private String batchNum;

    /**
     * 数据库字段拼写是 QANTITY（不是 QUANTITY）
     */
    @TableField("\"Qantity\"")
    private Double quantity;

    @TableField("\"Width\"")
    private String width;

    @TableField("\"Length\"")
    private String length;

    @TableField("\"WhsName\"")
    private String whsName;

    @TableField("\"weight\"")
    private Double weight;

    @TableField("\"LocName\"")
    private String locName;
}