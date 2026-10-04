package com.zhurong.platform.custom.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
*  VO
*/
@Data
public class ZhurongScjinggongOrderitemVO implements Serializable {

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
        private String prdRef;
        private String wrkRef;
        private String cusRef;
        private String ordRef;
        private Integer quantity;
        private LocalDateTime rdate;
        private String udata1;
        private String udata2;
        private String udata3;
        private String udata4;
        private String udata5;
        private String udata6;
        private String udata7;
        private String udata8;
        private String udata9;
        private String udata10;
        private String udata11;
        private String udata12;
}
