package com.zhurong.platform.core.clientimport.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 标准客户端导入任务提交结果。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientImportSubmission implements Serializable {

    private String taskId;

    private String requestId;

    private String businessType;

    private String targetClientId;
}
