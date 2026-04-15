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
public class SystCcpp00000100PageQuery extends BasePageQuery {


        /**
        * 
        */
        private Integer eType;


        /**
        * 
        */
        private String eName;


        /**
        * 
        */
        private String parName;


        /**
        * 
        */
        private String parValue;


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
