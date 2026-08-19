package com.zhurong.platform.core.clientimport.mq;

import com.fasterxml.jackson.databind.JsonNode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.Instant;

@Data
@Schema(description = "客户端导入任务MQ消息")
public class ClientImportTaskMessage implements Serializable {

    /**
     * STANDARD_IMPORT 表示客户端收到通知后再从 core 拉取数据；
     * COMMAND 表示 payload 是一个由客户模块解释的轻量命令参数。
     */
    private String messageKind = ClientMessageKinds.STANDARD_IMPORT;

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
