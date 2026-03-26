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
public class ZhurongPlatformJobCursorPageQuery extends BasePageQuery {


        /**
        * 
        */
        private String jobName;


        /**
        * 
        */
        private LocalDateTime cursorTime;

/**
* 创建时间开始
*/
private LocalDateTime beginCreateTime;

/**
* 创建时间结束
*/
private LocalDateTime endCreateTime;
}
