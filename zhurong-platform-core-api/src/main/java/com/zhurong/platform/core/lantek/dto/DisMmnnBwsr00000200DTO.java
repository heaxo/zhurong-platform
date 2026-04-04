package com.zhurong.platform.core.lantek.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
*  DTO
*/
@Data
public class DisMmnnBwsr00000200DTO implements Serializable {

    private Integer BwsrID;
    private String TblRef;
    private Integer RecordID;
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
