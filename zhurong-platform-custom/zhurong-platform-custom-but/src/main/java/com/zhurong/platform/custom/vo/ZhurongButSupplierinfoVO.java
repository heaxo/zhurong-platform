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
        private String nstRef;
        private String supplierName;
        private String whsName;
        private String udata1;
        private String udata2;
        private String udata3;
}
