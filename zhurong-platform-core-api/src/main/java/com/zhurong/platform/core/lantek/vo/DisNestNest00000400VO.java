package com.zhurong.platform.core.lantek.vo;

import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
*  VO
*/
@Data
public class DisNestNest00000400VO implements Serializable {

        private String NstRef;
        private String WrkRef;
        private String TurrRef;
        private Integer TPosition;
        private String MltRef;
        private Integer MPosition;
        private String PunchRef;
        private String DieRef;
        private Double PAngle;
        private Byte IsUsed;
        private Integer NumOpN;
        private Integer NumOpR;
        private Integer ToolClass;
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
