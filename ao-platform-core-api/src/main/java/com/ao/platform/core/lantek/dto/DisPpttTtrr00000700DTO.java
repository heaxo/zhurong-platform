package com.ao.platform.core.lantek.dto;

import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
*  DTO
*/
@Data
public class DisPpttTtrr00000700DTO implements Serializable {

    private String turrRef;
    private Integer trType;
    private Integer trCategory;
    private String descrip;
    private Integer toolClass;
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
