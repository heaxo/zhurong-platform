package com.zhurong.platform.core.lantek.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.zhurong.platform.base.model.BasePageQuery;
import com.zhurong.platform.core.dto.SortInstructionDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

/**
*  分页查询对象
*/
@Getter
@Setter
public class DisNestNest00000100PageQuery extends BasePageQuery {

        /**
         * 动态多字段排序
         */
        private List<SortInstructionDTO> sortRules;
        private RelationLoadPlan loadPlan;
        private List<Integer> recIds;
        private List<Integer> nestPartRecIds;

        /**
        * 
        */
        private String nstRef;


        /**
        * 
        */
        private String nestMainRef;


        /**
        * 
        */
        private String nestPrevRef;


        /**
        * 
        */
        private String nstPRef;


        /**
        * 
        */
        private Integer copyIndx;


        /**
        * 
        */
        private String wrkRef;


        /**
        * 
        */
        private String oprRef;


        /**
        * 
        */
        @JsonProperty("nOrder")
        @JsonAlias("NOrder")
        private Integer nOrder;


        /**
        * 
        */
        @JsonProperty("cDate")
        @JsonAlias("CDate")
        private LocalDateTime cDate;


        /**
        * 
        */
        private String jobRef;


        /**
        * 
        */
        @JsonProperty("nCategory")
        @JsonAlias("NCategory")
        private Integer nCategory;


        /**
        * 
        */
        @JsonProperty("mState")
        @JsonAlias("MState")
        private Integer mState;


        /**
        * 
        */
        private Integer wosStatus;


        /**
        * 
        */
        private String cnc;


        /**
        * 
        */
        private String shtRef;


        /**
        * 
        */
        private String shtRefOrg;


        /**
        * 
        */
        private String realSht;


        /**
        * 
        */
        private String matRef;


        /**
        * 
        */
        @JsonProperty("sLength")
        @JsonAlias("SLength")
        private Double sLength;


        /**
        * 
        */
        @JsonProperty("sWidth")
        @JsonAlias("SWidth")
        private Double sWidth;


        /**
        * 
        */
        @JsonProperty("sThickness")
        @JsonAlias("SThickness")
        private Double sThickness;


        /**
        * 
        */
        @JsonProperty("sArea")
        @JsonAlias("SArea")
        private Double sArea;


        /**
        * 
        */
        @JsonProperty("sUArea")
        @JsonAlias("SUArea")
        private Double sUArea;


        /**
        * 
        */
        @JsonProperty("sWeight")
        @JsonAlias("SWeight")
        private Double sWeight;


        /**
        * 
        */
        @JsonProperty("sUWeight")
        @JsonAlias("SUWeight")
        private Double sUWeight;


        /**
        * 
        */
        @JsonProperty("sXMax")
        @JsonAlias("SXMax")
        private Double sXMax;


        /**
        * 
        */
        @JsonProperty("sYMax")
        @JsonAlias("SYMax")
        private Double sYMax;


        /**
        * 
        */
        @JsonProperty("sPriority")
        @JsonAlias("SPriority")
        private Double sPriority;


        /**
        * 
        */
        @JsonProperty("sProfit")
        @JsonAlias("SProfit")
        private Double sProfit;


        /**
        * 
        */
        @JsonProperty("sProfitS")
        @JsonAlias("SProfitS")
        private Double sProfitS;


        /**
        * 
        */
        @JsonProperty("sMSQuant")
        @JsonAlias("SMSQuant")
        private Integer sMSQuant;


        /**
        * 
        */
        @JsonProperty("eTime")
        @JsonAlias("ETime")
        private Double eTime;


        /**
        * 
        */
        private Integer quantity;


        /**
        * 
        */
        private Integer mq;


        /**
        * 
        */
        @JsonProperty("rTime")
        @JsonAlias("RTime")
        private Double rTime;


        /**
        * 
        */
        @JsonProperty("sDate")
        @JsonAlias("SDate")
        private LocalDateTime sDate;


        /**
        * 
        */
        @JsonProperty("eDate")
        @JsonAlias("EDate")
        private LocalDateTime eDate;


        /**
        * 
        */
        @JsonProperty("uData1")
        @JsonAlias("UData1")
        private String uData1;


        /**
        * 
        */
        @JsonProperty("uData2")
        @JsonAlias("UData2")
        private String uData2;


        /**
        * 
        */
        @JsonProperty("uData3")
        @JsonAlias("UData3")
        private String uData3;


        /**
        * 
        */
        private LocalDateTime partialSDate;


        /**
        * 
        */
        @JsonProperty("eDuration")
        @JsonAlias("EDuration")
        private Double eDuration;


        /**
        * 
        */
        private Byte isCopy;


        /**
        * 
        */
        private String mltPrgRef;


        /**
        * 
        */
        private String mltPrgNstRef;


        /**
        * 
        */
        private String descrip;


        /**
        * 
        */
        private Byte toPallet;


        /**
        * 
        */
        private Byte isQuote;


        /**
        * 
        */
        private Byte realTimeUpdated;


        /**
        * 
        */
        private String nstCpyRef;


        /**
        * 
        */
        private Byte unitaryNest;


        /**
        * 
        */
        private String var1;


        /**
        * 
        */
        private String var2;


        /**
        * 
        */
        private String var3;


        /**
        * 
        */
        private String var4;


        /**
        * 
        */
        private String var5;


        /**
        * 
        */
        private LocalDateTime camLastDate;


        /**
        * 
        */
        private String wrkCfg;


        /**
        * 
        */
        private LocalDateTime requiredDate;


        /**
        * 
        */
        private LocalDateTime scheduledStart;


        /**
        * 
        */
        private Integer priority;


        /**
        * 
        */
        private String name;


        /**
        * 
        */
        @JsonProperty("mStateCloudStatus")
        @JsonAlias("MStateCloudStatus")
        private Integer mStateCloudStatus;


        /**
        * 
        */
        private Integer cuttingStatus;


        /**
        * 
        */
        private Integer cutQuantity;


        /**
        * 
        */
        private String externalIndex;


        /**
        * 
        */
        private Byte automatic;


        /**
        * 
        */
        private Integer recState;


        /**
        * 
        */
        private LocalDateTime crtDate;


        /**
        * 
        */
        private LocalDateTime lastDate;


        /**
        * 
        */
        private String crtUser;


        /**
        * 
        */
        private String lastUser;


        /**
        * 
        */
        private String owner;


        /**
        * 
        */
        private String recEnt;


        /**
        * 
        */
        private String recOU;


        /**
        * 
        */
        private Integer recSec;


        /**
        * 
        */
        private Integer cntID;


        /**
        * 
        */
        private Integer recID;

/**
* 创建时间开始
*/
private LocalDateTime beginCreateTime;

/**
* 创建时间结束
*/
private LocalDateTime endCreateTime;
}
