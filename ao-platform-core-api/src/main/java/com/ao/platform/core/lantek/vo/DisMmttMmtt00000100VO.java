package com.ao.platform.core.lantek.vo;

import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
*  VO
*/
@Data
public class DisMmttMmtt00000100VO implements Serializable {

        private String matRef;
        private Double density;
        private Double strength;
        private Double mFactor;
        private Double price;
        private Byte isMain;
        private String intRef;
        private String descrip;
        private LocalDateTime priceDate;
        private String cGroup;
        private Double scrapPrice;
        private LocalDateTime scrapPriceDate;
        private Integer recState;
        private LocalDateTime crtDate;
        private LocalDateTime lastDate;
        private String crtUser;
        private String lastUser;
        private String owner;
        private String recEnt;
        private String recOU;
        private Integer recSec;
        private Integer cntID;
        private Integer recID;
}
