package com.zhurong.platform.custom.sbut.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@TableName("SBO_SBUT.AVA_INVENTOYR_QTY")
@Accessors(chain = true)
public class SbutAvaInventoryQty {

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

    @TableField("\"Length\"")
    private String width;

    @TableField("\"Width\"")
    private String length;

    @TableField("\"WhsName\"")
    private String whsName;

    @TableField("\"weight\"")
    private Double weight;

    @TableField("\"WhsName\"")
    private String locName;

    @TableField("\"Company\"")
    private String company;
}