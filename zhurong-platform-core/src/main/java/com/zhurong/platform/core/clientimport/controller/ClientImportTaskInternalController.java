package com.zhurong.platform.core.clientimport.controller;

import com.zhurong.platform.base.api.ApiResponse;
import com.zhurong.platform.core.clientimport.api.IClientImportTaskApi;
import com.zhurong.platform.core.clientimport.configuration.ConditionalOnClientCommunicationEnabled;
import com.zhurong.platform.core.clientimport.mq.ClientImportTaskMessage;
import com.zhurong.platform.core.clientimport.mq.ClientImportTaskStatusMessage;
import com.zhurong.platform.core.clientimport.dto.ClientCommandRequest;
import com.zhurong.platform.core.clientimport.dto.ClientCommandResponse;
import com.zhurong.platform.core.clientimport.dto.ClientCommandResultReport;
import com.zhurong.platform.core.clientimport.dto.ClientImportRequest;
import com.zhurong.platform.core.clientimport.dto.ClientImportSubmission;
import com.zhurong.platform.core.clientimport.dto.ClientImportTaskResult;
import com.zhurong.platform.base.clientimport.dto.ProductionOrderRequest;
import com.zhurong.platform.base.clientimport.dto.RawMaterialRequest;
import com.zhurong.platform.core.clientimport.service.ClientCommandService;
import com.zhurong.platform.core.clientimport.service.ClientImportApplicationService;
import com.zhurong.platform.core.clientimport.service.ClientImportTaskRuntimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/client-import/internal")
@ConditionalOnClientCommunicationEnabled
@RequiredArgsConstructor
public class ClientImportTaskInternalController implements IClientImportTaskApi {

    private final ClientImportTaskRuntimeService taskRuntimeService;
    private final ClientImportApplicationService applicationService;
    private final ClientCommandService commandService;

    @Override
    public ApiResponse<ClientImportSubmission> submitProductionOrder(
            ClientImportRequest<List<ProductionOrderRequest>> request
    ) {
        return ApiResponse.success(applicationService.submitProductionOrder(request));
    }

    @Override
    public ApiResponse<ClientImportSubmission> submitRawMaterial(
            ClientImportRequest<List<RawMaterialRequest>> request
    ) {
        return ApiResponse.success(applicationService.submitRawMaterial(request));
    }

    @Override
    public ApiResponse<ClientImportTaskResult> getResult(String taskId) {
        return ApiResponse.success(taskRuntimeService.getResult(taskId));
    }

    @Override
    public ApiResponse<ClientCommandResponse> executeCommand(ClientCommandRequest request) {
        return ApiResponse.success(commandService.execute(request));
    }

    @Override
    public ApiResponse<Boolean> updateCommandResult(String commandId, ClientCommandResultReport report) {
        return ApiResponse.success(commandService.complete(commandId, report));
    }

    @Override
    public ApiResponse<ClientImportTaskMessage> getPendingData(String taskId) {
        return ApiResponse.success(taskRuntimeService.getPendingData(taskId));
    }

    @Override
    public ApiResponse<Boolean> updateStatus(String taskId, ClientImportTaskStatusMessage statusMessage) {
        statusMessage.setTaskId(taskId);
        return ApiResponse.success(taskRuntimeService.handleStatus(statusMessage));
    }
}
