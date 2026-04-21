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

        private String cnc;
        private String locName;
        private String shtRef;
        private String shtName;
        private Integer quantity;
        private String whsName;
        private String batchNumber;
        private Float weight;
        private String unit;
        private String businessType;

/**
* 创建时间开始
*/
private LocalDateTime beginCreateTime;

/**
* 创建时间结束
*/
private LocalDateTime endCreateTime;
}
