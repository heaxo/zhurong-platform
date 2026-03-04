package com.ao.platform.core.lantek.dto;

import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
*  DTO
*/
@Data
public class DisNestNest00000400DTO implements Serializable {

    private String nstRef;
    private String wrkRef;
    private String turrRef;
    private Integer tPosition;
    private String mltRef;
    private Integer mPosition;
    private String punchRef;
    private String dieRef;
    private Double pAngle;
    private Byte isUsed;
    private Integer numOpN;
    private Integer numOpR;
    private Integer toolClass;
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
