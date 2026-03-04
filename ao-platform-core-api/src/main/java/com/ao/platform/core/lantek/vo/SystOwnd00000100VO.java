package com.ao.platform.core.lantek.vo;

import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
*  VO
*/
@Data
public class SystOwnd00000100VO implements Serializable {

        private String docDsc;
        private String tblRef;
        private Integer recordID;
        private Integer sType;
        private String fFType;
        private String fFName;
        private LocalDateTime fFDate;
        private Double fFSize;
        private String fFVault;
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
