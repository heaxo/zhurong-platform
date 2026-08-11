package com.zhurong.platform.custom.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zhurong.platform.custom.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("Zhurong_Xybaoyuan_NestFeedbackState")
public class XyNestFeedbackState extends BaseEntity {
    @TableField("nest_rec_id")
    private Integer nestRecId;
    @TableField("nst_ref")
    private String nstRef;
    @TableField("sent")
    private Boolean sent;
    @TableField("sent_at")
    private LocalDateTime sentAt;
    @TableField("remark")
    private String remark;
}
