package com.zhurong.platform.core.clientimport.api;

import com.zhurong.platform.base.api.ApiResponse;
import com.zhurong.platform.core.clientimport.mq.ClientImportTaskMessage;
import com.zhurong.platform.core.clientimport.mq.ClientImportTaskStatusMessage;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 客户端导入任务内部契约。
 * MQ只通知taskId，业务数据必须通过该接口从core实时读取，确保SQL Server始终是事实来源。
 */
public interface IClientImportTaskApi {

    /**
     * 查询当前批次仍未导入的数据。
     */
    @GetMapping("/tasks/{taskId}/pending-data")
    ApiResponse<ClientImportTaskMessage> getPendingData(@PathVariable("taskId") String taskId);

    /**
     * 客户端回传导入执行状态和成功导入的core业务表主键集合。
     */
    @PostMapping("/tasks/{taskId}/status")
    ApiResponse<Boolean> updateStatus(
            @PathVariable("taskId") String taskId,
            @Valid @RequestBody ClientImportTaskStatusMessage statusMessage);
}
