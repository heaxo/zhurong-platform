package com.ao.platform.core.lantek.dto;

import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
*  DTO
*/
@Data
public class DisShprPptt00000200DTO implements Serializable {

    private String partRef;
    private String wrkRef;
    private String oprRef;
    private String matRef;
    private Double thickness;
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
