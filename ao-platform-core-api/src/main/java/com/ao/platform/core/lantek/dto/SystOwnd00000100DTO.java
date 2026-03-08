package com.ao.platform.core.lantek.dto;

import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
*  DTO
*/
@Data
public class SystOwnd00000100DTO implements Serializable {

    private String DocDsc;
    private String TblRef;
    private Integer RecordID;
    private Integer SType;
    private String FFType;
    private String FFName;
    private LocalDateTime FFDate;
    private Double FFSize;
    private String FFVault;
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
