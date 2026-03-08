package com.ao.platform.custom.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AcceptOrderRequestDTO {

    @JsonProperty("createTime")
    private String createTime;

    @JsonProperty("orderCode")
    private String orderCode;

    @JsonProperty("projectCode")
    private String projectCode;

    @JsonProperty("isBack")
    private Integer isBack;

    @JsonProperty("details")
    private List<Details> details;

    @JsonProperty("nestingMains")
    private List<NestingMains> nestingMains;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Details {

        //零件编号
        @JsonProperty("partCode")
        private String partCode;

        //零件名称
        @JsonProperty("partName")
        private String partName;

        //零件数量
        @JsonProperty("partNum")
        private Integer partNum;

        //工艺类型 1激光 2冲床
        @JsonProperty("craft")
        private Integer craft;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class NestingMains {

        //物料编号
        @JsonProperty("materialCode")
        private String materialCode;

        //程序
        @JsonProperty("program")
        private String program;

        //板材长度
        @JsonProperty("length")
        private Double length;

        //板材宽度
        @JsonProperty("width")
        private Double width;

        //板材厚度
        @JsonProperty("height")
        private Double height;

        //加工设备(激光M1/M2或者冲床PC1/PC2)
        @JsonProperty("machine")
        private String machine;

        //物料材质
        @JsonProperty("material")
        private String material;

        //工艺类型 1激光 2冲床
        @JsonProperty("craft")
        private Integer craft;

        //程序执行次数
        @JsonProperty("runCount")
        private Integer runCount;

        //加工参数
        @JsonProperty("parameter")
        private String parameter;

        //加工程序地址
        @JsonProperty("programPath")
        private String programPath;

        //整版图片地址
        @JsonProperty("pickPath")
        private String pickPath;

        //套料程序零件详情
        @JsonProperty("nestingDetails")
        private List<NestingDetails> nestingDetails;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class NestingDetails {

        //零件编号
        @JsonProperty("partCode")
        private String partCode;

        //零件名称
        @JsonProperty("partName")
        private String partName;

        //零件长度
        @JsonProperty("partLength")
        private Double partLength;

        //零件宽度
        @JsonProperty("partWidth")
        private Double partWidth;

        //零件厚度
        @JsonProperty("partHeight")
        private Double partHeight;

        //零件材质
        @JsonProperty("partMaterial")
        private String partMaterial;

        //零件数量
        @JsonProperty("partNum")
        private Integer partNum;

        //零件图片地址
        @JsonProperty("partPath")
        private String partPath;
    }
}