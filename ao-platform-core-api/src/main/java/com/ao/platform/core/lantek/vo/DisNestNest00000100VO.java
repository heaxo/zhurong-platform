package com.ao.platform.core.lantek.vo;

import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
*  VO
*/
@Data
public class DisNestNest00000100VO implements Serializable {

        private String nstRef;
        private String nestMainRef;
        private String nestPrevRef;
        private String nstPRef;
        private Integer copyIndx;
        private String wrkRef;
        private String oprRef;
        private Integer nOrder;
        private LocalDateTime cDate;
        private String jobRef;
        private Integer nCategory;
        private Integer mState;
        private Integer wosStatus;
        private String cnc;
        private String shtRef;
        private String shtRefOrg;
        private String realSht;
        private String matRef;
        private Double sLength;
        private Double sWidth;
        private Double sThickness;
        private Double sArea;
        private Double sUArea;
        private Double sWeight;
        private Double sUWeight;
        private Double sXMax;
        private Double sYMax;
        private Double sPriority;
        private Double sProfit;
        private Double sProfitS;
        private Integer sMSQuant;
        private Double eTime;
        private Integer quantity;
        private Integer mq;
        private Double rTime;
        private LocalDateTime sDate;
        private LocalDateTime eDate;
        private String uData1;
        private String uData2;
        private String uData3;
        private LocalDateTime partialSDate;
        private Double eDuration;
        private Byte isCopy;
        private String mltPrgRef;
        private String mltPrgNstRef;
        private String descrip;
        private Byte toPallet;
        private Byte isQuote;
        private Byte realTimeUpdated;
        private String nstCpyRef;
        private Byte unitaryNest;
        private String var1;
        private String var2;
        private String var3;
        private String var4;
        private String var5;
        private LocalDateTime camLastDate;
        private String wrkCfg;
        private LocalDateTime requiredDate;
        private LocalDateTime scheduledStart;
        private Integer priority;
        private String name;
        private Integer mStateCloudStatus;
        private Integer cuttingStatus;
        private Integer cutQuantity;
        private String externalIndex;
        private Byte automatic;
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
