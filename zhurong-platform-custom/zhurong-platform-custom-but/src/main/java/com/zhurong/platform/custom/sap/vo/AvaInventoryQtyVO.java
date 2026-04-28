package com.zhurong.platform.custom.sap.vo;

import lombok.Data;

/*
 * @Author zhurong
 * @Description AvaInventoyrQtyDTO
 * @Date 2026/3/29 15:29
 **/
@Data
public class AvaInventoryQtyVO {
    private String itemCode;
    private String itemName;
    private String uBeasZnr;
    private String uHd;
    private String batchNum;
    private Double quantity;
    private String width;
    private String length;
    private String whsName;
    private Double weight;
    private String company;
}
