package com.ao.platform.core.lantek.dto;

import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
*  DTO
*/
@Data
public class DisNestNest00000500DTO implements Serializable {

    private String nstRef;
    private String mnORef;
    private Integer oprID;
    private String prdRefDst;
    private Integer pIndex;
    private Integer quantity;
    private Integer mq;
    private Double costMat;
    private Double costMachTime;
    private Double costConsum;
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
