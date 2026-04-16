package com.zhurong.platform.core.dto;

import lombok.Data;

@Data
public class SortInstructionDTO {

    /**
     * 前端传入的实体属性名
     * 例如：recID、jobRef、nstRef、nOrder
     */
    private String property;

    /**
     * asc / desc
     */
    private String direction;
}