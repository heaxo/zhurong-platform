package com.ao.platform.core.lantek.dto;

import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
*  DTO
*/
@Data
public class DisMmttMmtt00000100DTO implements Serializable {

    private String MatRef;
    private Double Density;
    private Double Strength;
    private Double MFactor;
    private Double Price;
    private Byte IsMain;
    private String IntRef;
    private String Descrip;
    private LocalDateTime PriceDate;
    private String CGroup;
    private Double ScrapPrice;
    private LocalDateTime ScrapPriceDate;
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
