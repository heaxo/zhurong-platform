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
public class AoPlatformNest100StateSnapshotPageQuery extends BasePageQuery {


        /**
        * 
        */
        private Integer recID;


        /**
        * 
        */
        private Integer mState;


        /**
        * 
        */
        private LocalDateTime lastDate;


        /**
        * 
        */
        private LocalDateTime syncTime;

/**
* 创建时间开始
*/
private LocalDateTime beginCreateTime;

/**
* 创建时间结束
*/
private LocalDateTime endCreateTime;
}
