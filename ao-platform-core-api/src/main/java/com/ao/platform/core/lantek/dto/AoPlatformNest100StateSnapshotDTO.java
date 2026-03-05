package com.ao.platform.core.lantek.dto;

import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
*  DTO
*/
@Data
public class AoPlatformNest100StateSnapshotDTO implements Serializable {

    private Integer recID;
    private Integer mState;
    private LocalDateTime lastDate;
    private LocalDateTime syncTime;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

}
