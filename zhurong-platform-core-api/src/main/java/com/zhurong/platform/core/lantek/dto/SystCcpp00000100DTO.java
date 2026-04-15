package com.zhurong.platform.core.lantek.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
*  DTO
*/
@Data
public class SystCcpp00000100DTO implements Serializable {

    private Integer EType;
    private String EName;
    private String ParName;
    private String ParValue;
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
