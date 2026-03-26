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
public class SystOwnd00000100PageQuery extends BasePageQuery {


        /**
        * 
        */
        private String docDsc;


        /**
        * 
        */
        private String tblRef;


        /**
        * 
        */
        private Integer recordID;


        /**
        * 
        */
        private Integer sType;


        /**
        * 
        */
        private String fFType;


        /**
        * 
        */
        private String fFName;


        /**
        * 
        */
        private LocalDateTime fFDate;


        /**
        * 
        */
        private Double fFSize;


        /**
        * 
        */
        private String fFVault;


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
