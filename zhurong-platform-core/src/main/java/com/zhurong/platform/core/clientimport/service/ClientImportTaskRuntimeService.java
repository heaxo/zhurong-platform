package com.zhurong.platform.core.clientimport.service;

import com.zhurong.platform.core.clientimport.mq.ClientImportTaskMessage;
import com.zhurong.platform.core.clientimport.mq.ClientImportTaskStatusMessage;
import com.zhurong.platform.core.clientimport.dto.ClientImportTaskResult;

public interface ClientImportTaskRuntimeService {

    ClientImportTaskMessage getPendingData(String taskId);

    ClientImportTaskResult getResult(String taskId);

    boolean handleStatus(ClientImportTaskStatusMessage statusMessage);
}
