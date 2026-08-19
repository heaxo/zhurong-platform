package com.zhurong.platform.core.clientimport.dto;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;

import java.io.Serializable;

/**
 * 通用客户端命令的同步响应。
 */
@Data
public class ClientCommandResponse implements Serializable {

    private String commandId;

    private String targetClientId;

    private String commandType;

    private String status;

    private String message;

    private JsonNode data;

    public boolean isSuccess() {
        return "SUCCESS".equals(status);
    }
}
