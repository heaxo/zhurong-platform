package com.zhurong.platform.core.lantek.dto;

import lombok.Data;

/*
 * @Author zhurong
 * @Description ItemsDocument
 * @Date 2026/4/15 22:09
 **/
@Data
public class ItemsDocument {
    /**
     * 单项记录ID
     */
    private Integer recID;

    /**
     * 相对路径
     */
    private String BMP;
    private String IMGB;
    private String MEC;

    /**
     * 绝对路径
     */
    private String fullPathBMP;
    private String fullPathIMGB;
    private String fullPathMEC;
}
