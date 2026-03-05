package com.ao.platform.core.lantek.dto;

import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
*  DTO
*/
@Data
public class AoPlatformJobCursorDTO implements Serializable {

    private String jobName;
    private LocalDateTime cursorTime;
    private LocalDateTime updateTime;

}
