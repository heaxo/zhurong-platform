package com.zhurong.platform.core.clientimport.api;

import com.zhurong.platform.base.api.ApiResponse;
import com.zhurong.platform.base.clientimport.dto.ProductionOrderRequest;
import com.zhurong.platform.base.clientimport.dto.RawMaterialRequest;
import com.zhurong.platform.core.clientimport.dto.ClientCommandRequest;
import com.zhurong.platform.core.clientimport.dto.ClientCommandResponse;
import com.zhurong.platform.core.clientimport.dto.ClientCommandResultReport;
import com.zhurong.platform.core.clientimport.dto.ClientImportRequest;
import com.zhurong.platform.core.clientimport.dto.ClientImportSubmission;
import com.zhurong.platform.core.clientimport.dto.ClientImportTaskResult;
import com.zhurong.platform.core.clientimport.mq.ClientImportTaskMessage;
import com.zhurong.platform.core.clientimport.mq.ClientImportTaskStatusMessage;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 客户端导入任务内部契约。
 * MQ只通知taskId，业务数据必须通过该接口从core实时读取，确保SQL Server始终是事实来源。
 */
public interface IClientImportTaskApi {

    /**
     * 供其他平台模块提交标准生产订单，并取得可跟踪的任务号。
     */
    @PostMapping("/dispatch/production-order")
    ApiResponse<ClientImportSubmission> submitProductionOrder(
            @Valid @RequestBody ClientImportRequest<List<ProductionOrderRequest>> request);

    /**
     * 供其他平台模块提交标准原材料，并取得可跟踪的任务号。
     */
    @PostMapping("/dispatch/raw-material")
    ApiResponse<ClientImportSubmission> submitRawMaterial(
            @Valid @RequestBody ClientImportRequest<List<RawMaterialRequest>> request);

    @GetMapping("/tasks/{taskId}/result")
    ApiResponse<ClientImportTaskResult> getResult(@PathVariable("taskId") String taskId);

    /**
     * 同步执行一个 core 不解释载荷的定向客户端命令。
     */
    @PostMapping("/commands/execute")
    ApiResponse<ClientCommandResponse> executeCommand(@Valid @RequestBody ClientCommandRequest request);

    /**
     * 客户端回写通用命令结果。
     */
    @PostMapping("/commands/{commandId}/result")
    ApiResponse<Boolean> updateCommandResult(
            @PathVariable("commandId") String commandId,
            @Valid @RequestBody ClientCommandResultReport report);

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
