package com.ao.platform.core.lantek.dto;

import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
*  DTO
*/
@Data
public class WwccWwcc00000100DTO implements Serializable {

    private String WrkRef;
    private String WrkOEM;
    private String WrkCNC;
    private Byte IsOutsourcing;
    private String WrkCapUCtName;
    private String WrkCapUntName;
    private Double WrkCap;
    private String Descrip;
    private Byte IsActive;
    private Integer DIS_WrkType;
    private Integer DIS_OTechn;
    private String DIS_CfgFile;
    private String DIS_PsfFile;
    private String DIS_SawRef;
    private String DIS_Group;
    private String WosMessages;
    private Integer WosState;
    private LocalDateTime WosStateLastDate;
    private String WosPauseReason;
    private String WosOperatorComment;
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
