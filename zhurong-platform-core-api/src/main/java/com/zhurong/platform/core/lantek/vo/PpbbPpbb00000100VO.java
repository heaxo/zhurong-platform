package com.zhurong.platform.core.lantek.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
*  VO
*/
@Data
public class PpbbPpbb00000100VO implements Serializable {

        private Integer MaterialReqID;
        private String WrhRef;
        private String PrdRef;
        private String PrdName;
        private Integer ElementStatus;
        private Byte Confirmed;
        private Integer Type;
        private String Reference;
        private String ActRef;
        private String LineNum;
        private String UCtName;
        private String UntName;
        private LocalDateTime RDate;
        private LocalDateTime PlannedDDate;
        private Double RequiredQ;
        private Double OnOrderQ;
        private Double AllocatedQ;
        private Double PendingQ;
        private Double ReceivedQ;
        private String LocRef;
        private Integer RecordID;
        private String OriginRef;
        private String OriginLineNum;
        private Integer OriginType;
        private String MainOriginFilter;
        private String MainOriginNameFilter;
        private String WorkPackageFilter;
        private String WorkPackageNameFilter;
        private String WorkOrderFilter;
        private String WorkOrderNameFilter;
        private Integer MainOriginTypeFilter;
        private String GLS_BatchNo;
        private String GLS_Var1;
        private String GLS_Var2;
        private String GLS_Var3;
        private String GLS_Var4;
        private String GLS_Var5;
        private Integer DIS_Subclass;
        private String DIS_MatRef;
        private Double DIS_Thickness;
        private String DIS_FormatRef;
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
