package com.zhurong.platform.core.clientimport.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhurong.platform.core.clientimport.entity.ClientDispatchTask;
import com.zhurong.platform.core.clientimport.enums.DispatchStatus;

import java.util.List;

public interface ClientDispatchTaskService extends IService<ClientDispatchTask> {

    ClientDispatchTask getByTaskId(String taskId);

    void updateStatus(String taskId, DispatchStatus status, String message);

    List<ClientDispatchTask> listTasksWaitingPublish(int limit);
}
