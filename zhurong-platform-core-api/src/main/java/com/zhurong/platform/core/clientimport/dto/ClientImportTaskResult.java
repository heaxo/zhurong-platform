package com.zhurong.platform.core.clientimport.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 标准客户端导入任务的当前执行结果。
 */
@Data
public class ClientImportTaskResult implements Serializable {

    private String taskId;

    private String requestId;

    private String businessType;

    private String targetClientId;

    private String status;

    private String message;

    public boolean isTerminal() {
        return "SUCCESS".equals(status)
                || "FAILED".equals(status)
                || "TIMEOUT".equals(status)
                || "PUBLISH_FAILED".equals(status);
    }

    public boolean isSuccess() {
        return "SUCCESS".equals(status);
    }
}
