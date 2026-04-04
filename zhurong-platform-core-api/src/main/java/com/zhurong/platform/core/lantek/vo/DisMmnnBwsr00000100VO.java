package com.zhurong.platform.core.lantek.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
*  VO
*/
@Data
public class DisMmnnBwsr00000100VO implements Serializable {

        private String NodeName;
        private String NodeID;
        private Integer ParentID;
        private Integer BwsrType;
        private Integer RecState;
        private LocalDateTime CrtDate;
        private LocalDateTime LastDate;
        private String CrtUser;
        private String LastUser;
        private String Owner;
        private String RecEnt;
        private String RecOU;
        private Integer RecSec;
        private Integer CntID;
        private Integer RecID;
}
