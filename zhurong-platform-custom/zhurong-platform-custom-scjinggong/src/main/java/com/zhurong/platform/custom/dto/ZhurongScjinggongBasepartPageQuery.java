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
public class ZhurongScjinggongBasepartPageQuery extends BasePageQuery {


        /**
        * 
        */
        private Boolean isDeleted;


        /**
        * 
        */
        private Integer version;


        /**
        * 
        */
        private Long createdBy;


        /**
        * 
        */
        private LocalDateTime createdAt;


        /**
        * 
        */
        private Long updatedBy;


        /**
        * 
        */
        private LocalDateTime updatedAt;


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
        private Boolean invalidState;


        /**
        * 
        */
        private String prdRef;


        /**
        * 
        */
        private String prdName;


        /**
        * 
        */
        private String wrkRef;


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
        private Integer quantity;


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
        * 
        */
        private String udata4;


        /**
        * 
        */
        private String udata5;


        /**
        * 
        */
        private String udata6;


        /**
        * 
        */
        private String udata7;


        /**
        * 
        */
        private String udata8;


        /**
        * 
        */
        private String drawingPath;


        /**
        * 
        */
        private String rawDrawingPath;

/**
* 创建时间开始
*/
private LocalDateTime beginCreateTime;

/**
* 创建时间结束
*/
private LocalDateTime endCreateTime;
}
