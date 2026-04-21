package com.zhurong.platform.custom.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
*  VO
*/
@Data
public class ZhurongButSupplierinfoVO implements Serializable {

        private String id;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private String createdBy;
        private String updatedBy;
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
