package com.zhurong.platform.core.lantek.vo;

import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
*  VO
*/
@Data
public class DisMmnnMmoo00000200VO implements Serializable {

        private String jobFullPath;

        private String JobRef;
        private Integer MState;
        private LocalDateTime CDate;
        private String JGroup;
        private String UData1;
        private String UData2;
        private String UData3;
        private String UData4;
        private String UData5;
        private String WrkRef;
        private String MatRef;
        private Double Thickness;
        private String Descrip;
        private Byte IsQuote;
        private String CusRef;
        private String CusName;
        private String QutRef;
        private String JobName;
        private Integer JobOrder;
        private LocalDateTime RDate;
        private LocalDateTime CamLastDate;
        private LocalDateTime LastQuoteModification;
        private LocalDateTime JobElementLastDate;
        private String ExternalKey;
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
