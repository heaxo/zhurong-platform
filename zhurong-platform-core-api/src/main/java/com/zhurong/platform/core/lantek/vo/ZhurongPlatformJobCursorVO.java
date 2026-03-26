package com.zhurong.platform.core.lantek.vo;

import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
*  VO
*/
@Data
public class ZhurongPlatformJobCursorVO implements Serializable {

        private String jobName;
        private LocalDateTime cursorTime;
        private LocalDateTime updateTime;
}
