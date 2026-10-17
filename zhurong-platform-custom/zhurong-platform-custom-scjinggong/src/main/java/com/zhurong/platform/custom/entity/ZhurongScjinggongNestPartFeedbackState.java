package com.zhurong.platform.custom.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zhurong.platform.custom.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * NEST500 套料零件反馈状态。
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("Zhurong_Scjinggong_NestPartFeedbackState")
public class ZhurongScjinggongNestPartFeedbackState extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 所属 NEST100 RecID。 */
    @TableField("nest_rec_id")
    private Integer nestRecId;

    /** NEST500 RecID，同时是反馈幂等键。 */
    @TableField("nest_part_rec_id")
    private Integer nestPartRecId;

    /** 套料程序编号。 */
    @TableField("nst_ref")
    private String nstRef;

    /** 本地生产批次主键。 */
    @TableField("order_id")
    private Long orderId;

    /** 对方接口的 JobRef。 */
    @TableField("order_code")
    private String orderCode;

    /** 对方接口的 JobName。 */
    @TableField("order_name")
    private String orderName;

    /** 最近一次反馈是否成功；成功记录不会再次发送。 */
    @TableField("sent")
    private Boolean sent;

    /** 累计反馈尝试次数。 */
    @TableField("attempt_count")
    private Integer attemptCount;

    /** 最近一次成功反馈时间。 */
    @TableField("sent_at")
    private LocalDateTime sentAt;

    /** 最近一次反馈结果或错误摘要。 */
    @TableField("remark")
    private String remark;
}
