package com.zhurong.platform.core.clientimport.dto;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;

/**
 * 通用客户端命令。core 只负责定向路由和结果关联，不解释 payload 中的客户业务字段。
 */
@Data
public class ClientCommandRequest implements Serializable {

    @NotBlank(message = "targetClientId不能为空")
    private String targetClientId;

    @NotBlank(message = "commandType不能为空")
    private String commandType;

    private JsonNode payload;

    @Min(value = 1, message = "timeoutSeconds不能小于1")
    @Max(value = 600, message = "timeoutSeconds不能大于600")
    private Integer timeoutSeconds = 120;
}
