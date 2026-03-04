package com.ao.platform.core.lantek.dto;

import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
*  DTO
*/
@Data
public class MmnnMmoo00000100DTO implements Serializable {

    private String mnORef;
    private String prdRef;
    private String prdName;
    private String rouRef;
    private String uCtName;
    private String untName;
    private Double quantity;
    private Integer priority;
    private Integer origin;
    private String ordRef;
    private String oLineNum;
    private String cusRef;
    private String cusName;
    private LocalDateTime cDate;
    private LocalDateTime dDate;
    private Integer mState;
    private LocalDateTime sDate;
    private LocalDateTime eDate;
    private LocalDateTime eSDate;
    private LocalDateTime eEDate;
    private LocalDateTime rDate;
    private Double madeQuan;
    private Double cost;
    private String descrip;
    private String wrhRefSM;
    private String lineNumSM;
    private Byte planned;
    private String mnORefOrg;
    private String mnORoot;
    private Integer orderType;
    private String mainPackage;
    private String mainPackageName;
    private String workPackage;
    private String workPackageName;
    private String glsVar1;
    private String glsVar2;
    private String glsVar3;
    private String glsVar4;
    private String glsVar5;
    private Byte disIsquote;
    private Double disOutq;
    private Integer disFillerpart;
    private String disRouteabbreviation;
    private Byte disAutonesting;
    private Integer disAutonestingstrategy;
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
