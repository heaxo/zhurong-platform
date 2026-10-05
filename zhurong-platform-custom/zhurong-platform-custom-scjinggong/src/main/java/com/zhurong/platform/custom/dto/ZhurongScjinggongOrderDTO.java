package com.zhurong.platform.custom.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO
 */
@Data
public class ZhurongScjinggongOrderDTO implements Serializable {

    private Long id;
    private Boolean isDeleted;
    private Integer version;
    private Long createdBy;
    private LocalDateTime createdAt;
    private Long updatedBy;
    private LocalDateTime updatedAt;
    private Boolean isRead;
    private Boolean isReviewed;
    private Boolean invalidState;
    private String orderCode;
    private String orderName;
    private String udata1;
    private String udata2;
    private String udata3;
    private String udata4;
    private String udata5;

}
