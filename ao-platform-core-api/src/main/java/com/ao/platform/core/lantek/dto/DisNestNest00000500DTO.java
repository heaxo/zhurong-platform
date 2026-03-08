package com.ao.platform.core.lantek.dto;

import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
*  DTO
*/
@Data
public class DisNestNest00000500DTO implements Serializable {

    private String NstRef;
    private String MnORef;
    private Integer OprID;
    private String PrdRefDst;
    private Integer PIndex;
    private Integer Quantity;
    private Integer MQ;
    private Double CostMat;
    private Double CostMachTime;
    private Double CostConsum;
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
