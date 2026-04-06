package com.zhurong.platform.custom.erp.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ViPmOrderlDTO {

    private String belposId;

    private String itemCode;

    private String coutKey;

    private String cfccad;

    private String cfType;

    private String ccad;

    private String itemName;

    private String uZnr;

    private Double udf1;

    private String aplatzId;

    private Double mengeVerbrauch;

    private LocalDateTime anfzeit;

    private LocalDateTime lieferdatum;

    private String vversion;

    private String icadproduct;

    private List<String> belposIds;
    private String jobRef;
    private String matRef;
    private String wrkRef;
}