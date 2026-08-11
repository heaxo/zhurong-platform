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
    @TableField("production_order_number")
    private String productionOrderNumber;
    @TableField("production_order_line_id")
    private String productionOrderLineId;
    @TableField("production_order_erp_internal_code")
    private String productionOrderErpInternalCode;
    @TableField("prd_ref")
    private String prdRef;
    @TableField("prd_name")
    private String prdName;
    @TableField("mat_ref")
    private String matRef;
    @TableField("wrk_ref")
    private String wrkRef;
    @TableField("thickness")
    private Double thickness;
    @TableField("quantity")
    private Double quantity;
    @TableField("delivery_date")
    private LocalDateTime deliveryDate;
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
    @TableField("work_center")
    private String workCenter;
    @TableField("job_ref")
    private String jobRef;
    @TableField("job_name")
    private String jobName;
    @TableField("production_workshop_code")
    private String productionWorkshopCode;
    @TableField("production_workshop_name")
    private String productionWorkshopName;
    @TableField("read_state")
    private Boolean readState;
    @TableField("read_time")
    private LocalDateTime readTime;
    @TableField("send_state")
    private Boolean sendState;
    @TableField("send_time")
    private LocalDateTime sendTime;
    @TableField("invalid_state")
    private Boolean invalidState;
    @TableField("last_task_id")
    private Long lastTaskId;

    @TableField(exist = false)
    private String drawingCode;
    @TableField(exist = false)
    private Boolean partMaintenance;
    @TableField(exist = false)
    private XyImportTask task;
}
