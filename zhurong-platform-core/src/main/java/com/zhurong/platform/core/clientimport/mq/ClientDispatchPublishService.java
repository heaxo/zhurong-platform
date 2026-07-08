package com.zhurong.platform.core.clientimport.mq;

public interface ClientDispatchPublishService {

    void publish(String taskId);

    void publish(ClientImportTaskMessage message);
}
