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
    private String prdRef;
    private String prdName;
    private String drawingCode;
    private String matRef;
    private Double thickness;
    private String rouRef;
    private String cusRef;
    private String cusName;
    private String udata1;
    private String udata2;
    private String udata3;
    private Boolean invalidState;

    @TableField(exist = false)
    private Boolean partMaintenance;
    @TableField(exist = false)
    private String imageData;
}
