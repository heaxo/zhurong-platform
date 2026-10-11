package com.zhurong.platform.core.lantek.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
*  DTO
*/
@Data
public class DisMmttMmtt00000200DTO implements Serializable {

    private String matRef;
    private Float minThickness;
    private Float maxThickness;
    private Float price;
    private LocalDateTime priceDate;
    private String descrip;
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
