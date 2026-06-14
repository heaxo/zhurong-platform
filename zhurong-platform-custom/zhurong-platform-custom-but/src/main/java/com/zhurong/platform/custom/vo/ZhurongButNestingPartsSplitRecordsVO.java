package com.zhurong.platform.custom.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
*  VO
*/
@Data
public class ZhurongButNestingPartsSplitRecordsVO implements Serializable {

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
        private String mnoRef;
        private String orgMnoRef;
        private Integer oprId;
        private Integer quantity;
        private String remark;
        private String ordRef;
        private Integer recId;
        private String prdRef;
        private String cnc;
}
