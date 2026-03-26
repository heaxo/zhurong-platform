package com.zhurong.platform.core.lantek.dto;

import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
*  DTO
*/
@Data
public class ZhurongPlatformOutboxEventDTO implements Serializable {

    private Long id;
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
