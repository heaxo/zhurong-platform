package com.zhurong.platform.custom.erp.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/*
 * @Author zhurong
 * @Description 下料报工钢板视图
 * @Date 2026/4/21 12:05
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("vi_pm_prjreports_lantek")
public class ViPmPrjreportsLantek {

    @TableField("IID")
    private String id;
    @TableField("业务类型")
    private String businessType;
    @TableField("下料程序名")
    private String CNC;
    @TableField("钢板编码")
    private String shtRef;
    @TableField("钢板名称")
    private String shtName;
    @TableField("钢板张数")
    private Integer quantity;
    @TableField("钢板批号")
    private String batchNumber;
    @TableField("钢板总重")
    private Double weight;
    @TableField("单位")
    private String unit;
    @TableField("生成余料编码")
    private String remnantRef;
    @TableField("生成余料尺寸")
    private String remnantSpecification;
    @TableField("生成余料重量")
    private String remnantWeight;
    @TableField("下料方式")
    private String feedingMethod;
    @TableField("下料设备")
    private String equipment;
    @TableField("外协厂商")
    private String outsourcedManufacturers;
    @TableField("制单时间")
    private String preparationTime;
}
