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
public class WwhhIivv00000100PageQuery extends BasePageQuery {


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
        private String uCtName;


        /**
        * 
        */
        private String untName;


        /**
        * 
        */
        private Double stockQ;


        /**
        * 
        */
        private Double allocatedQ;


        /**
        * 
        */
        private Double onOrderQ;


        /**
        * 
        */
        private Double pendingQ;


        /**
        * 
        */
        private Integer updMethod;


        /**
        * 
        */
        private Byte isDefault;


        /**
        * 
        */
        private Double minStock;


        /**
        * 
        */
        private Double minOrder;


        /**
        * 
        */
        private Double maxOrder;


        /**
        * 
        */
        private Double multOrder;


        /**
        * 
        */
        private Integer strategy;


        /**
        * 
        */
        private String locDefault;


        /**
        * 
        */
        private Double weight;


        /**
        * 
        */
        private Double rWeight;


        /**
        * 
        */
        private Byte isSemifinished;


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
