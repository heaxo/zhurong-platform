package com.zhurong.platform.custom.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zhurong.platform.custom.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 *
 *
 * @author me
 * @since 2026-04-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("Zhurong_But_SupplierInfo")
public class ZhurongButSupplierinfo extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableField("cnc")
    private String cnc;

    /**
     *
     */
    @TableField("loc_name")
    private String locName;

    /**
     *
     */
    @TableField("sht_ref")
    private String shtRef;

    /**
     *
     */
    @TableField("sht_name")
    private String shtName;

    /**
     *
     */
    @TableField("quantity")
    private Integer quantity;

    /**
     *
     */
    @TableField("whs_name")
    private String whsName;

    /**
     *
     */
    @TableField("batch_number")
    private String batchNumber;

    /**
     *
     */
    @TableField("weight")
    private Float weight;

    /**
     *
     */
    @TableField("unit")
    private String unit;

    /**
     *
     */
    @TableField("business_type")
    private String businessType;
}
