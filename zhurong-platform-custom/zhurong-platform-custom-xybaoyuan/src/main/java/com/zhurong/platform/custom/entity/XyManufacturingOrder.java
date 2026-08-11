package com.zhurong.platform.custom.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zhurong.platform.custom.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("Zhurong_Xybaoyuan_ManufacturingOrder")
public class XyManufacturingOrder extends BaseEntity {
    private String productionOrderNumber;
    private String productionOrderLineId;
    private String productionOrderErpInternalCode;
    private String prdRef;
    private String prdName;
    private String matRef;
    private String wrkRef;
    private Double thickness;
    private Double quantity;
    private LocalDateTime deliveryDate;
    private String rouRef;
    private String cusRef;
    private String cusName;
    private String udata1;
    private String udata2;
    private String workCenter;
    private String jobRef;
    private String jobName;
    private String productionWorkshopCode;
    private String productionWorkshopName;
    private Boolean readState;
    private LocalDateTime readTime;
    private Boolean sendState;
    private LocalDateTime sendTime;
    private Boolean invalidState;
    private Long lastTaskId;

    @TableField(exist = false)
    private String drawingCode;
    @TableField(exist = false)
    private Boolean partMaintenance;
    @TableField(exist = false)
    private XyImportTask task;
}
