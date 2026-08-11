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
    @TableField("erp_material_id")
    private Integer erpMaterialId;
    @TableField("prd_ref")
    private String prdRef;
    @TableField("prd_name")
    private String prdName;
    @TableField("specification")
    private String specification;
    @TableField("mat_ref")
    private String matRef;
    @TableField("stock_name")
    private String stockName;
    @TableField("stock_number")
    private String stockNumber;
    @TableField("tons")
    private Double tons;
    @TableField("quantity")
    private Double quantity;
    @TableField("thickness")
    private Double thickness;
    @TableField("width")
    private Double width;
    @TableField("length")
    private Double length;
    @TableField("lot_number")
    private String lotNumber;
    @TableField("remark")
    private String remark;
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
    private XyImportTask task;
}
