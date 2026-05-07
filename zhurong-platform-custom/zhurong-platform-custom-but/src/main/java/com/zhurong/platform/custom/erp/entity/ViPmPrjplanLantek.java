package com.zhurong.platform.custom.erp.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/*
 * @Author zhurong
 * @Description 下料程序名位置视图
 * @Date 2026/4/21 12:06
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("vi_pm_prjplan_lantek")
public class ViPmPrjplanLantek {

    @TableField("下料程序名")
    private String cnc;
    @TableField("位置")
    private String locName;
    @TableField("钢板编码")
    private String shtRef;
    @TableField("钢板名称")
    private String shtName;
    @TableField("钢板张数")
    private Integer quantity;
    @TableField("供应商编号")
    private String whsName;
    @TableField("钢板批号")
    private String batchNumber;
    @TableField("钢板总重")
    private Double weight;
    @TableField("单位")
    private String unit;
    @TableField("业务类型")
    private String businessType;
    @TableField("使用余料编码")
    private String remShtRef;

}
