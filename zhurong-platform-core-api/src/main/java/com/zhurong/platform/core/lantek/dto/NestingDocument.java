package com.zhurong.platform.core.lantek.dto;

import lombok.Data;

/*
 * @Author zhurong
 * @Description NestingDocument
 * @Date 2026/4/15 22:08
 **/
@Data
public class NestingDocument {
    /**
     * 套料记录ID
     */
    private Integer recID;

    /**
     * 相对路径
     */
    private String BMP;
    private String CHP;
    private String CNC;
    private String IMGB;
    private String JOBRPT;
    private String MEC;
    private String XML;

    /**
     * 绝对路径
     */
    private String fullPathBMP;
    private String fullPathCHP;
    private String fullPathIMGB;
    private String fullPathJOBRPT;
    private String fullPathMEC;
    private String fullPathXML;
}
