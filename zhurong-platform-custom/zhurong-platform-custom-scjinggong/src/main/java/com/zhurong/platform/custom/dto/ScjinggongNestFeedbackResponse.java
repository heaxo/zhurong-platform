package com.zhurong.platform.custom.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

/**
 * 盛驰套料接收响应。
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ScjinggongNestFeedbackResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer code;
    private String message;
    private Boolean data;
}
