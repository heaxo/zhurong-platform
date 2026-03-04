package com.ao.platform.core.lantek.dto;

import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
*  DTO
*/
@Data
public class DisNestNest00000300DTO implements Serializable {

    private String nstRef;
    private String shtRef;
    private Integer rIndex;
    private Integer quantity;
    private String var1;
    private String var2;
    private String var3;
    private String var4;
    private String var5;
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
