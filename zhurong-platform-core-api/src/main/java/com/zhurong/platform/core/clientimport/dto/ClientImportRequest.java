package com.zhurong.platform.core.clientimport.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(description = "客户端导入统一请求外层")
public class ClientImportRequest<T> implements Serializable {

    @Schema(description = "指定任务发送到哪台客户端；可不传，但最终解析结果不能为空", example = "CLIENT-01")
    private String targetClientId;

    @Valid
    @NotNull(message = "data不能为空")
    @Schema(description = "标准业务数据", requiredMode = Schema.RequiredMode.REQUIRED)
    private T data;
}
