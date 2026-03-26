package com.zhurong.platform.core.lantek.dto;

import com.zhurong.platform.base.model.BasePageQuery;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
*  分页查询对象
*/
@Getter
@Setter
public class DisMmnnMmoo00000200PageQuery extends BasePageQuery {


        /**
        * 
        */
        private String jobRef;


        /**
        * 
        */
        private Integer mState;


        /**
        * 
        */
        private LocalDateTime cDate;


        /**
        * 
        */
        private String jGroup;


        /**
        * 
        */
        private String uData1;


        /**
        * 
        */
        private String uData2;


        /**
        * 
        */
        private String uData3;


        /**
        * 
        */
        private String uData4;


        /**
        * 
        */
        private String uData5;


        /**
        * 
        */
        private String wrkRef;


        /**
        * 
        */
        private String matRef;


        /**
        * 
        */
        private Double thickness;


        /**
        * 
        */
        private String descrip;


        /**
        * 
        */
        private Byte isQuote;


        /**
        * 
        */
        private String cusRef;


        /**
        * 
        */
        private String cusName;


        /**
        * 
        */
        private String qutRef;


        /**
        * 
        */
        private String jobName;


        /**
        * 
        */
        private Integer jobOrder;


        /**
        * 
        */
        private LocalDateTime rDate;


        /**
        * 
        */
        private LocalDateTime camLastDate;


        /**
        * 
        */
        private LocalDateTime lastQuoteModification;


        /**
        * 
        */
        private LocalDateTime jobElementLastDate;


        /**
        * 
        */
        private String externalKey;


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
