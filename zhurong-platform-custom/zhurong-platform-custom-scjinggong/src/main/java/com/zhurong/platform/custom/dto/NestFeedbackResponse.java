package com.zhurong.platform.custom.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/*
 * @Author zhurong
 * @Description NestFeedbackRequest
 * @Date 2026/10/11 12:03
 **/
@Data
public class NestFeedbackResponse {
    @JsonProperty("success")
    private String success;
    @JsonProperty("message")
    private String message;
}
