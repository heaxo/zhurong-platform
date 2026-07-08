package com.zhurong.platform.core.clientimport.mq;

import lombok.Data;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

@Data
public class ClientImportTaskStatusMessage implements Serializable {

    private String taskId;

    private String requestId;

    private String clientId;

    private String businessType;

    private String status;

    private String message;

    /**
     * 客户端实际导入成功的core业务表主键集合。core按这些id把Imported标记为true。
     */
    private List<Long> importedRecordIds;

    private String userName;

    private String version;

    private Instant eventTime;
}
