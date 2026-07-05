package com.zhurong.platform.custom.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/*
 * @Author zhurong
 * @Description LabelDataQueryDTO
 * @Date 2026/7/4 19:58
 **/
@Data
public class LabelDataQueryDTO {

    @Schema(description = "跨板合并，直接按零件、订单编码合并标签数据。不跨板合并，只在同一个套料结果中按零件、订单编码合并。")
    private Boolean crossBoardMerger;

    private List<Integer> nestRecIds;
    private List<Integer> nestPartRecIds;

}
