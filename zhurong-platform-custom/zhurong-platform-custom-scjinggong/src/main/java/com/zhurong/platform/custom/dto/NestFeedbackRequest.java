package com.zhurong.platform.custom.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/*
 * @Author zhurong
 * @Description NestFeedbackRequest
 * @Date 2026/10/11 12:03
 **/
@Data
public class NestFeedbackRequest {
    @JsonProperty("data")
    private List<NestFeedbackRequestDTO> data;
}
