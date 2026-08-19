package com.zhurong.platform.core.clientimport.dto;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;

/**
 * 客户端完成通用命令后的回执。
 */
@Data
public class ClientCommandResultReport implements Serializable {

    @NotBlank(message = "clientId不能为空")
    private String clientId;

    @NotBlank(message = "commandType不能为空")
    private String commandType;

    private boolean success;

    private String message;

    private JsonNode data;
}
