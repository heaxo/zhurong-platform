package com.zhurong.platform.custom.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zhurong.platform.custom.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("Zhurong_Xybaoyuan_SteelPlate")
public class XySteelPlate extends BaseEntity {
    private Integer erpMaterialId;
    private String prdRef;
    private String prdName;
    private String specification;
    private String matRef;
    private String stockName;
    private String stockNumber;
    private Double tons;
    private Double quantity;
    private Double thickness;
    private Double width;
    private Double length;
    private String lotNumber;
    private String remark;
    private Boolean readState;
    private LocalDateTime readTime;
    private Boolean sendState;
    private LocalDateTime sendTime;
    private Boolean invalidState;
    private Long lastTaskId;

    @TableField(exist = false)
    private XyImportTask task;
}
