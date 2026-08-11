package com.zhurong.platform.custom.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zhurong.platform.custom.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("Zhurong_Xybaoyuan_CodeSequence")
public class XyCodeSequence extends BaseEntity {
    @TableField("sequence_key")
    private String sequenceKey;
    @TableField("current_value")
    private Long currentValue;
    @TableField("increment_by")
    private Integer incrementBy;
    @TableField("last_allocated_at")
    private LocalDateTime lastAllocatedAt;
}
