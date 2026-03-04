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
public class DisShprPptt00000200PageQuery extends BasePageQuery {


        /**
        * 
        */
        private String partRef;


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
        private String matRef;


        /**
        * 
        */
        private Double thickness;


        /**
        * 
        */
        private String mCode;


        /**
        * 
        */
        private String msgDesc;


        /**
        * 
        */
        private Integer msgNum;


        /**
        * 
        */
        private Integer valType;


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
        private Double dValue;


        /**
        * 
        */
        private Double cValue;


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
