package com.zhurong.platform.custom.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 盛驰套料接收请求。
 */
@Data
public class ScjinggongNestFeedbackRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private String apiName;
    private String accesstoken;
    private String secret;
    private String modulesub;
    private String user;

    /** 按 JobRef/JobName 归组后的 NEST500 零件。 */
    @JsonProperty("PartList")
    private List<ScjinggongNestFeedbackPartDTO> partList;

    /** 本地生产批次号。 */
    @JsonProperty("JobRef")
    private String jobRef;

    /** 本地生产批次名。 */
    @JsonProperty("JobName")
    private String jobName;
}
