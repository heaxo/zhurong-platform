package com.zhurong.platform.core.clientimport.mq;

import com.fasterxml.jackson.databind.JsonNode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.Instant;

@Data
@Schema(description = "客户端导入任务MQ消息")
public class ClientImportTaskMessage implements Serializable {

    private String taskId;

    private String requestId;

    private String targetClientId;

    @Schema(description = "PART_DRAWING_ARCHIVE、PRODUCTION_ORDER、RAW_MATERIAL，也可为PING")
    private String businessType;

    private String schemaVersion;

    private Instant createTime;

    @Schema(description = "MQ通知场景为空；客户端通过Feign查询任务数据时，此处为当前未导入数据列表")
    private JsonNode payload;
}
