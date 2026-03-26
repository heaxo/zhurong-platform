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
public class DisNestNest00000500PageQuery extends BasePageQuery {


        /**
        * 
        */
        private String nstRef;


        /**
        * 
        */
        private String mnORef;


        /**
        * 
        */
        private Integer oprID;


        /**
        * 
        */
        private String prdRefDst;


        /**
        * 
        */
        private Integer pIndex;


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
        private Double costMat;


        /**
        * 
        */
        private Double costMachTime;


        /**
        * 
        */
        private Double costConsum;


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
