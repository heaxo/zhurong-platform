package com.ao.platform.core.lantek.dto;

import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
*  DTO
*/
@Data
public class MmnnMmoo00000100DTO implements Serializable {

    private String MnORef;
    private String PrdRef;
    private String PrdName;
    private String RouRef;
    private String UCtName;
    private String UntName;
    private Double Quantity;
    private Integer Priority;
    private Integer Origin;
    private String OrdRef;
    private String OLineNum;
    private String CusRef;
    private String CusName;
    private LocalDateTime CDate;
    private LocalDateTime DDate;
    private Integer MState;
    private LocalDateTime SDate;
    private LocalDateTime EDate;
    private LocalDateTime ESDate;
    private LocalDateTime EEDate;
    private LocalDateTime RDate;
    private Double MadeQuan;
    private Double Cost;
    private String Descrip;
    private String WrhRefSM;
    private String LineNumSM;
    private Byte Planned;
    private String MnORefOrg;
    private String MnORoot;
    private Integer OrderType;
    private String MainPackage;
    private String MainPackageName;
    private String WorkPackage;
    private String WorkPackageName;
    private String GLS_Var1;
    private String GLS_Var2;
    private String GLS_Var3;
    private String GLS_Var4;
    private String GLS_Var5;
    private Byte DIS_IsQuote;
    private Double DIS_OutQ;
    private Integer DIS_FillerPart;
    private String DIS_RouteAbbreviation;
    private Byte DIS_AutoNesting;
    private Integer DIS_AutoNestingStrategy;
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
