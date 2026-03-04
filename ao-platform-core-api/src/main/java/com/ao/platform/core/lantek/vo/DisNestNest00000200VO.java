package com.ao.platform.core.lantek.vo;

import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
*  VO
*/
@Data
public class DisNestNest00000200VO implements Serializable {

        private String nstRef;
        private String mCode;
        private String msgDesc;
        private Integer msgNum;
        private Integer valType;
        private String uCtName;
        private String untName;
        private Double dValue;
        private Double cValue;
        private Integer recState;
        private LocalDateTime crtDate;
        private LocalDateTime lastDate;
        private String crtUser;
        private String lastUser;
        private String owner;
        private String recEnt;
        private String recOU;
        private Integer recSec;
        private Integer cntID;
        private Integer recID;
}
