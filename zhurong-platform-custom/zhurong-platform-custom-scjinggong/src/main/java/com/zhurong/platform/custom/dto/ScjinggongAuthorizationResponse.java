package com.zhurong.platform.custom.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 盛驰接口认证响应。
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ScjinggongAuthorizationResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer code;
    private String msg;
    private Integer errCode;

    /** 认证成功时返回的 Bearer Token 信息。 */
    private TokenData data;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class TokenData implements Serializable {

        private static final long serialVersionUID = 1L;

        private String token;

        /** Token 过期时间戳，由接口以秒为单位返回。 */
        @JsonProperty("expiration_time")
        private Long expirationTime;
    }
}
