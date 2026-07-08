package com.zhurong.platform.core.clientimport.service;

import com.zhurong.platform.core.clientimport.mq.ClientImportTaskMessage;
import com.zhurong.platform.core.clientimport.mq.ClientImportTaskStatusMessage;

public interface ClientImportTaskRuntimeService {

    ClientImportTaskMessage getPendingData(String taskId);

    boolean handleStatus(ClientImportTaskStatusMessage statusMessage);
}
