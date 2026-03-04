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
public class DisPpttTtrr00000800PageQuery extends BasePageQuery {


        /**
        * 
        */
        private String turrRef;


        /**
        * 
        */
        private Integer tPosition;


        /**
        * 
        */
        private Integer tNumber;


        /**
        * 
        */
        private String mltRef;


        /**
        * 
        */
        private String stTRef;


        /**
        * 
        */
        private Byte autoindex;


        /**
        * 
        */
        private Double dMin;


        /**
        * 
        */
        private Double dMax;


        /**
        * 
        */
        private Integer zClamp;


        /**
        * 
        */
        private Double zLeft;


        /**
        * 
        */
        private Double zRight;


        /**
        * 
        */
        private Double zTop;


        /**
        * 
        */
        private Double offsetXMin;


        /**
        * 
        */
        private Double offsetXMax;


        /**
        * 
        */
        private Double offsetYMin;


        /**
        * 
        */
        private Double offsetYMax;


        /**
        * 
        */
        private Integer plane;


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
