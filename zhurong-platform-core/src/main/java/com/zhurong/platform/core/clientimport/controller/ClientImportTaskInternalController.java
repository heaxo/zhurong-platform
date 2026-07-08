package com.zhurong.platform.core.clientimport.controller;

import com.zhurong.platform.base.api.ApiResponse;
import com.zhurong.platform.core.clientimport.api.IClientImportTaskApi;
import com.zhurong.platform.core.clientimport.configuration.ConditionalOnClientCommunicationEnabled;
import com.zhurong.platform.core.clientimport.mq.ClientImportTaskMessage;
import com.zhurong.platform.core.clientimport.mq.ClientImportTaskStatusMessage;
import com.zhurong.platform.core.clientimport.service.ClientImportTaskRuntimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client-import/internal")
@ConditionalOnClientCommunicationEnabled
@RequiredArgsConstructor
public class ClientImportTaskInternalController implements IClientImportTaskApi {

    private final ClientImportTaskRuntimeService taskRuntimeService;

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
