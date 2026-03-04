package com.ao.platform.core.lantek.dto;

import com.ao.platform.base.model.BasePageQuery;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
*  分页查询对象
*/
@Getter
@Setter
public class DisMmttMmtt00000100PageQuery extends BasePageQuery {


        /**
        * 
        */
        private String matRef;


        /**
        * 
        */
        private Double density;


        /**
        * 
        */
        private Double strength;


        /**
        * 
        */
        private Double mFactor;


        /**
        * 
        */
        private Double price;


        /**
        * 
        */
        private Byte isMain;


        /**
        * 
        */
        private String intRef;


        /**
        * 
        */
        private String descrip;


        /**
        * 
        */
        private LocalDateTime priceDate;


        /**
        * 
        */
        private String cGroup;


        /**
        * 
        */
        private Double scrapPrice;


        /**
        * 
        */
        private LocalDateTime scrapPriceDate;


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
