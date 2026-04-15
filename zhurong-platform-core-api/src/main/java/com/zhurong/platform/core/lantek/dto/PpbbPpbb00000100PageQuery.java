package com.zhurong.platform.core.lantek.dto;

import lombok.Getter;
import lombok.Setter;
import com.zhurong.platform.base.model.BasePageQuery;

import java.time.LocalDateTime;

/**
*  分页查询对象
*/
@Getter
@Setter
public class PpbbPpbb00000100PageQuery extends BasePageQuery {


        /**
        * 
        */
        private Integer materialReqID;


        /**
        * 
        */
        private String wrhRef;


        /**
        * 
        */
        private String prdRef;


        /**
        * 
        */
        private String prdName;


        /**
        * 
        */
        private Integer elementStatus;


        /**
        * 
        */
        private Byte confirmed;


        /**
        * 
        */
        private Integer type;


        /**
        * 
        */
        private String reference;


        /**
        * 
        */
        private String actRef;


        /**
        * 
        */
        private String lineNum;


        /**
        * 
        */
        private String uCtName;


        /**
        * 
        */
        private String untName;


        /**
        * 
        */
        private LocalDateTime rDate;


        /**
        * 
        */
        private LocalDateTime plannedDDate;


        /**
        * 
        */
        private Double requiredQ;


        /**
        * 
        */
        private Double onOrderQ;


        /**
        * 
        */
        private Double allocatedQ;


        /**
        * 
        */
        private Double pendingQ;


        /**
        * 
        */
        private Double receivedQ;


        /**
        * 
        */
        private String locRef;


        /**
        * 
        */
        private Integer recordID;


        /**
        * 
        */
        private String originRef;


        /**
        * 
        */
        private String originLineNum;


        /**
        * 
        */
        private Integer originType;


        /**
        * 
        */
        private String mainOriginFilter;


        /**
        * 
        */
        private String mainOriginNameFilter;


        /**
        * 
        */
        private String workPackageFilter;


        /**
        * 
        */
        private String workPackageNameFilter;


        /**
        * 
        */
        private String workOrderFilter;


        /**
        * 
        */
        private String workOrderNameFilter;


        /**
        * 
        */
        private Integer mainOriginTypeFilter;


        /**
        * 
        */
        private String glsBatchno;


        /**
        * 
        */
        private String glsVar1;


        /**
        * 
        */
        private String glsVar2;


        /**
        * 
        */
        private String glsVar3;


        /**
        * 
        */
        private String glsVar4;


        /**
        * 
        */
        private String glsVar5;


        /**
        * 
        */
        private Integer disSubclass;


        /**
        * 
        */
        private String disMatref;


        /**
        * 
        */
        private Double disThickness;


        /**
        * 
        */
        private String disFormatref;


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
