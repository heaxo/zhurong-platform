package com.zhurong.platform.custom.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zhurong.platform.custom.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("Zhurong_Xybaoyuan_NestFeedbackState")
public class XyNestFeedbackState extends BaseEntity {
    private Integer nestRecId;
    private String nstRef;
    private Boolean sent;
    private LocalDateTime sentAt;
    private String remark;
}
