package com.zhurong.platform.custom.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
*  DTO
*/
@Data
public class ZhurongButSupplierinfoDTO implements Serializable {

    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long createdBy;
    private Long updatedBy;
    private Integer version;
    private Boolean isDeleted;
    private Boolean isRead;
    private Boolean isReviewed;
    private String nstRef;
    private String supplierName;
    private String whsName;
    private String udata1;
    private String udata2;
    private String udata3;

}
