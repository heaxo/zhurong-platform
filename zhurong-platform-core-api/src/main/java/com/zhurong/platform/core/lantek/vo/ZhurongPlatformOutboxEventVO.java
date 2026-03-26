package com.zhurong.platform.core.lantek.vo;

import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
*  VO
*/
@Data
public class ZhurongPlatformOutboxEventVO implements Serializable {

        private String id;
        private String eventId;
        private String eventType;
        private Integer aggregateId;
        private Integer oldState;
        private Integer newState;
        private LocalDateTime sourceLastDate;
        private String payload;
        private Integer status;
        private Integer retryCount;
        private LocalDateTime nextRetryTime;
        private LocalDateTime createTime;
        private LocalDateTime updateTime;
}
