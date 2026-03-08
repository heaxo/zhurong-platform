package com.ao.platform.core.lantek.dto;

import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
*  DTO
*/
@Data
public class DisShprPptt00000200DTO implements Serializable {

    private String PartRef;
    private String WrkRef;
    private String OprRef;
    private String MatRef;
    private Double Thickness;
    private String MCode;
    private String MsgDesc;
    private Integer MsgNum;
    private Integer ValType;
    private String UCtName;
    private String UntName;
    private Double DValue;
    private Double CValue;
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
