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
public class AoPlatformOutboxEventPageQuery extends BasePageQuery {


        /**
        * 
        */
        private String eventId;


        /**
        * 
        */
        private String eventType;


        /**
        * 
        */
        private Integer aggregateId;


        /**
        * 
        */
        private Integer oldState;


        /**
        * 
        */
        private Integer newState;


        /**
        * 
        */
        private LocalDateTime sourceLastDate;


        /**
        * 
        */
        private String payload;


        /**
        * 
        */
        private Integer status;


        /**
        * 
        */
        private Integer retryCount;


        /**
        * 
        */
        private LocalDateTime nextRetryTime;

/**
* 创建时间开始
*/
private LocalDateTime beginCreateTime;

/**
* 创建时间结束
*/
private LocalDateTime endCreateTime;
}
