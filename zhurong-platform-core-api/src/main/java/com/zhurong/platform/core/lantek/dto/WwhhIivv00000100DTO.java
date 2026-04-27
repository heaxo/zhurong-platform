package com.zhurong.platform.core.lantek.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
*  DTO
*/
@Data
public class WwhhIivv00000100DTO implements Serializable {

    private String BatchNo;

    private String WrhRef;
    private String PrdRef;
    private String PrdName;
    private String UCtName;
    private String UntName;
    private Double StockQ;
    private Double AllocatedQ;
    private Double OnOrderQ;
    private Double PendingQ;
    private Integer UpdMethod;
    private Byte IsDefault;
    private Double MinStock;
    private Double MinOrder;
    private Double MaxOrder;
    private Double MultOrder;
    private Integer Strategy;
    private String LocDefault;
    private Double Weight;
    private Double RWeight;
    private Byte IsSemifinished;
    private String GLS_Var1;
    private String GLS_Var2;
    private String GLS_Var3;
    private String GLS_Var4;
    private String GLS_Var5;
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
