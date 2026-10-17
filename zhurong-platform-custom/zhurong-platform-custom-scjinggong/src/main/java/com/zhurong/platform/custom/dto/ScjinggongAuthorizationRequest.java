package com.zhurong.platform.custom.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 盛驰接口认证请求。
 */
@Data
public class ScjinggongAuthorizationRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private String accesstoken;
    private String secret;
    private String user;
    private String apiName;
    private String modulesub;
    private String appid;
}
