package com.zhurong.platform.core.lantek.dto;

import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
*  DTO
*/
@Data
public class ZhurongPlatformJobCursorDTO implements Serializable {

    private String jobName;
    private LocalDateTime cursorTime;
    private LocalDateTime updateTime;

}
