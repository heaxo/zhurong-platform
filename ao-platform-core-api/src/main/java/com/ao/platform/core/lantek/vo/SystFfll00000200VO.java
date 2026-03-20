package com.ao.platform.core.lantek.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
*  VO
*/
@Data
public class SystFfll00000200VO implements Serializable {

        private String VltName;
        private Integer VltType;
        private String VltFld;
        private Integer FileSave;
        private String SvrName;
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
