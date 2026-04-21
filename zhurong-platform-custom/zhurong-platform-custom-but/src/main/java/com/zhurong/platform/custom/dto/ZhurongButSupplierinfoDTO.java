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

    private String cnc;
    private String locName;
    private String shtRef;
    private String shtName;
    private Integer quantity;
    private String whsName;
    private String batchNumber;
    private Float weight;
    private String unit;
    private String businessType;

}
