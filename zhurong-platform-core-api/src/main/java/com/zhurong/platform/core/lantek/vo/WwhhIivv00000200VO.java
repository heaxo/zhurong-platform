package com.zhurong.platform.core.lantek.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
*  VO
*/
@Data
public class WwhhIivv00000200VO implements Serializable {

        private String WrhRef;
        private String LocRef;
        private String PrdRef;
        private String PrdName;
        private Integer StockID;
        private String UCtName;
        private String UntName;
        private Double ARCost;
        private Double StockQ;
        private Double Weight;
        private Double RWeight;
        private Byte IsSemifinished;
        private String MnORef;
        private Integer OprID;
        private String GLS_Var1;
        private String GLS_Var2;
        private String GLS_Var3;
        private String GLS_Var4;
        private String GLS_Var5;
        private String GLS_SerialNo;
        private String GLS_BatchNo;
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
