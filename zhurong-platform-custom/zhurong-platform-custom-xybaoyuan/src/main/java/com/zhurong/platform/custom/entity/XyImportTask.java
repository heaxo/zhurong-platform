package com.zhurong.platform.custom.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zhurong.platform.custom.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("Zhurong_Xybaoyuan_ImportTask")
public class XyImportTask extends BaseEntity {
    private String businessType;
    private String status;
    private String recordIdsJson;
    private Integer attempts;
    private String message;
    private LocalDateTime executionTime;
}
