package com.ao.platform.core.lantek.dto;

import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
*  DTO
*/
@Data
public class DisPpttTtrr00000800DTO implements Serializable {

    private String TurrRef;
    private Integer TPosition;
    private Integer TNumber;
    private String MltRef;
    private String StTRef;
    private Byte Autoindex;
    private Double DMin;
    private Double DMax;
    private Integer ZClamp;
    private Double ZLeft;
    private Double ZRight;
    private Double ZTop;
    private Double OffsetXMin;
    private Double OffsetXMax;
    private Double OffsetYMin;
    private Double OffsetYMax;
    private Integer Plane;
    private Integer RecState;
    private LocalDateTime CrtDate;
    private LocalDateTime LastDate;
    private String CrtUser;
    private String LastUser;
    private String Owner;
    private String RecEnt;
    private String RecOU;
    private Integer RecSec;
    private Integer CntID;
    private Integer RecID;

}
