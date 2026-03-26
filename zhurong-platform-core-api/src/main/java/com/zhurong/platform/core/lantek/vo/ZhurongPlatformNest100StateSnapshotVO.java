package com.zhurong.platform.core.lantek.vo;

import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
*  VO
*/
@Data
public class ZhurongPlatformNest100StateSnapshotVO implements Serializable {

        private Integer recID;
        private Integer mState;
        private LocalDateTime lastDate;
        private LocalDateTime syncTime;
        private LocalDateTime createTime;
        private LocalDateTime updateTime;
}
