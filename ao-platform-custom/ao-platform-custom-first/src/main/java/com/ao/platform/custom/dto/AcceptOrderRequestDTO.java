package com.ao.platform.custom.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AcceptOrderRequestDTO {

    private String createTime;
    private String orderCode;
    private String projectCode;
    private Integer isBack;
    private List<Details> details;
    private List<NestingMains> nestingMains;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Details {

        private String partCode;
        private String partName;
        private Integer partNum;
        private Integer craft;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class NestingMains {

        private String materialCode;
        private String program;
        private Integer length;
        private Integer width;
        private Integer height;
        private String machine;
        private String material;
        private Integer craft;
        private Integer runCount;
        private String parameter;
        private String programPath;
        private String pickPath;
        private List<NestingDetails> nestingDetails;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class NestingDetails {

        private String partCode;
        private String partName;
        private Integer partLength;
        private Integer partWidth;
        private Integer partHeight;
        private String partMaterial;
        private Integer partNum;
        private String partPath;
    }
}