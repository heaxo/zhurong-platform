package com.zhurong.platform.custom.dto;

import com.zhurong.platform.base.model.BasePageQuery;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
*  分页查询对象
*/
@Getter
@Setter
public class ZhurongButSupplierinfoPageQuery extends BasePageQuery {


        /**
        * 
        */
        private LocalDateTime createdAt;


        /**
        * 
        */
        private LocalDateTime updatedAt;


        /**
        * 
        */
        private Long createdBy;


        /**
        * 
        */
        private Long updatedBy;


        /**
        * 
        */
        private Integer version;


        /**
        * 
        */
        private Boolean isDeleted;


        /**
        * 
        */
        private Boolean isRead;


        /**
        * 
        */
        private Boolean isReviewed;


        /**
        * 
        */
        private String nstRef;


        /**
        * 
        */
        private String supplierName;


        /**
        * 
        */
        private String whsName;


        /**
        * 
        */
        private String udata1;


        /**
        * 
        */
        private String udata2;


        /**
        * 
        */
        private String udata3;

/**
* 创建时间开始
*/
private LocalDateTime beginCreateTime;

/**
* 创建时间结束
*/
private LocalDateTime endCreateTime;
}
