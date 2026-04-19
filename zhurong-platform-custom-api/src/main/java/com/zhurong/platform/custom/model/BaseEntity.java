package com.zhurong.platform.custom.model;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public abstract class BaseEntity {

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    @TableField("is_deleted")
    @TableLogic(value = "0", delval = "1")
    private Boolean isDeleted;
    @Version
    private Integer version;
    @TableField(fill = FieldFill.INSERT, value = "created_by")
    private Long createdBy;
    @TableField(fill = FieldFill.INSERT, value = "created_at")
    private LocalDateTime createdAt;
    @TableField(fill = FieldFill.INSERT_UPDATE, value = "updated_by")
    private Long updatedBy;
    @TableField(fill = FieldFill.INSERT_UPDATE, value = "updated_at")
    private LocalDateTime updatedAt;
    @TableField("is_read")
    private Boolean isRead;
    @TableField("is_reviewed")
    private Boolean isReviewed;
}

