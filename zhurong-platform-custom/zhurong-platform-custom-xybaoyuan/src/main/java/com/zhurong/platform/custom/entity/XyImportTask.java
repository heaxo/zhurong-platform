package com.zhurong.platform.custom.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zhurong.platform.custom.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("Zhurong_Xybaoyuan_ImportTask")
public class XyImportTask extends BaseEntity {
    @TableField("business_type")
    private String businessType;
    @TableField("status")
    private String status;
    @TableField("record_ids_json")
    private String recordIdsJson;
    @TableField("attempts")
    private Integer attempts;
    @TableField("message")
    private String message;
    @TableField("execution_time")
    private LocalDateTime executionTime;
}
