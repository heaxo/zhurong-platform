package com.ao.platform.core.lantek.vo;

import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
*  VO
*/
@Data
public class DisPpttTtrr00000800VO implements Serializable {

        private String turrRef;
        private Integer tPosition;
        private Integer tNumber;
        private String mltRef;
        private String stTRef;
        private Byte autoindex;
        private Double dMin;
        private Double dMax;
        private Integer zClamp;
        private Double zLeft;
        private Double zRight;
        private Double zTop;
        private Double offsetXMin;
        private Double offsetXMax;
        private Double offsetYMin;
        private Double offsetYMax;
        private Integer plane;
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
