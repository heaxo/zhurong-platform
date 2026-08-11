package com.zhurong.platform.custom.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zhurong.platform.custom.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("Zhurong_Xybaoyuan_BasePart")
public class XyBasePart extends BaseEntity {
    @TableField("prd_ref")
    private String prdRef;
    @TableField("prd_name")
    private String prdName;
    @TableField("drawing_code")
    private String drawingCode;
    @TableField("mat_ref")
    private String matRef;
    @TableField("thickness")
    private Double thickness;
    @TableField("rou_ref")
    private String rouRef;
    @TableField("cus_ref")
    private String cusRef;
    @TableField("cus_name")
    private String cusName;
    @TableField("udata1")
    private String udata1;
    @TableField("udata2")
    private String udata2;
    @TableField("udata3")
    private String udata3;
    @TableField("invalid_state")
    private Boolean invalidState;

    @TableField(exist = false)
    private Boolean partMaintenance;
    @TableField(exist = false)
    private String imageData;
}
