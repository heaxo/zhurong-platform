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
public class WwccWwcc00000100PageQuery extends BasePageQuery {


        /**
        * 
        */
        private String wrkRef;


        /**
        * 
        */
        private String wrkOEM;


        /**
        * 
        */
        private String wrkCNC;


        /**
        * 
        */
        private Byte isOutsourcing;


        /**
        * 
        */
        private String wrkCapUCtName;


        /**
        * 
        */
        private String wrkCapUntName;


        /**
        * 
        */
        private Double wrkCap;


        /**
        * 
        */
        private String descrip;


        /**
        * 
        */
        private Byte isActive;


        /**
        * 
        */
        private Integer disWrktype;


        /**
        * 
        */
        private Integer disOtechn;


        /**
        * 
        */
        private String disCfgfile;


        /**
        * 
        */
        private String disPsffile;


        /**
        * 
        */
        private String disSawref;


        /**
        * 
        */
        private String disGroup;


        /**
        * 
        */
        private String wosMessages;


        /**
        * 
        */
        private Integer wosState;


        /**
        * 
        */
        private LocalDateTime wosStateLastDate;


        /**
        * 
        */
        private String wosPauseReason;


        /**
        * 
        */
        private String wosOperatorComment;


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
