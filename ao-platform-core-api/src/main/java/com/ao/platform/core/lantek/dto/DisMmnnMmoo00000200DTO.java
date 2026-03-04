package com.ao.platform.core.lantek.dto;

import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
*  DTO
*/
@Data
public class DisMmnnMmoo00000200DTO implements Serializable {

    private String jobRef;
    private Integer mState;
    private LocalDateTime cDate;
    private String jGroup;
    private String uData1;
    private String uData2;
    private String uData3;
    private String uData4;
    private String uData5;
    private String wrkRef;
    private String matRef;
    private Double thickness;
    private String descrip;
    private Byte isQuote;
    private String cusRef;
    private String cusName;
    private String qutRef;
    private String jobName;
    private Integer jobOrder;
    private LocalDateTime rDate;
    private LocalDateTime camLastDate;
    private LocalDateTime lastQuoteModification;
    private LocalDateTime jobElementLastDate;
    private String externalKey;
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
