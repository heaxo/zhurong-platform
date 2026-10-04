package com.zhurong.platform.custom.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
*  VO
*/
@Data
public class ZhurongScjinggongOrderVO implements Serializable {

        private String id;
        private Boolean isDeleted;
        private Integer version;
        private String createdBy;
        private LocalDateTime createdAt;
        private String updatedBy;
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
