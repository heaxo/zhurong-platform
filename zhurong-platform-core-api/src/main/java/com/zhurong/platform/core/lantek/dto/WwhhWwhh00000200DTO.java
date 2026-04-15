package com.zhurong.platform.core.lantek.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
*  DTO
*/
@Data
public class WwhhWwhh00000200DTO implements Serializable {

    private String WrhRef;
    private String LocRef;
    private Integer LOrder;
    private String LocName;
    private String LocParent;
    private Byte IsEnd;
    private Double Width;
    private Double Height;
    private Double Length;
    private Double Weight;
    private Byte DIS_IsWorkCenter;
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
