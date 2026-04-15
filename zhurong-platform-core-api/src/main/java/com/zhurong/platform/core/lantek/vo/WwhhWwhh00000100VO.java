package com.zhurong.platform.core.lantek.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
*  VO
*/
@Data
public class WwhhWwhh00000100VO implements Serializable {

        private String WrhRef;
        private String WrhName;
        private Byte IsOutsourcing;
        private String Descrip;
        private Byte DIS_Automatic;
        private String DIS_WosWhDriver;
        private Byte DIS_IsWorkCenter;
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
