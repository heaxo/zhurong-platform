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
    @TableField("nst_ref")
    private String nstRef;

    /**
     *
     */
    @TableField("supplier_name")
    private String supplierName;

    /**
     *
     */
    @TableField("whs_name")
    private String whsName;

    /**
     *
     */
    @TableField("udata1")
    private String udata1;

    /**
     *
     */
    @TableField("udata2")
    private String udata2;

    /**
     *
     */
    @TableField("udata3")
    private String udata3;
}
