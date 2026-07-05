package com.zhurong.platform.custom.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/*
 * @Author zhurong
 * @Description LabelDataVO
 * @Date 2026/7/4 19:56
 **/
@Data
@Schema(description = "标签打印数据")
public class LabelDataVO {
    @Schema(description = "订单号", example = "DC-2204-2606090001")
    private String ordRef;

    @Schema(description = "品号", example = "B31113-X0-2604240004-017-01")
    private String prdRef;

    @Schema(description = "品名", example = "网带固定张紧座")
    private String prdName;

    @Schema(description = "颜色", example = "黑")
    private String color;

    @Schema(description = "数量", example = "2")
    private Integer quantity;

    @Schema(description = "日期", example = "2026-06-12")
    private LocalDateTime date;

    @Schema(description = "二维码内容")
    private String qrCodeContent;
}
