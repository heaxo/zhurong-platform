package com.zhurong.platform.custom.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * NEST500 套料零件反馈状态 DTO。
 */
@Data
public class ZhurongScjinggongNestPartFeedbackStateDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Boolean isDeleted;
    private Integer version;
    private Long createdBy;
    private LocalDateTime createdAt;
    private Long updatedBy;
    private LocalDateTime updatedAt;
    private Boolean isRead;
    private Boolean isReviewed;
    private Integer nestRecId;
    private Integer nestPartRecId;
    private String nstRef;
    private Long orderId;
    private String orderCode;
    private String orderName;
    private Boolean sent;
    private Integer attemptCount;
    private LocalDateTime sentAt;
    private String remark;
}
