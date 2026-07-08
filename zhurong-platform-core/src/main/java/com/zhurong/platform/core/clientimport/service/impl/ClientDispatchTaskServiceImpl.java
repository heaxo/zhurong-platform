package com.zhurong.platform.core.clientimport.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhurong.platform.core.clientimport.configuration.ConditionalOnClientCommunicationEnabled;
import com.zhurong.platform.core.clientimport.entity.ClientDispatchTask;
import com.zhurong.platform.core.clientimport.enums.DispatchStatus;
import com.zhurong.platform.core.clientimport.mapper.ClientDispatchTaskMapper;
import com.zhurong.platform.core.clientimport.service.ClientDispatchTaskService;
import com.zhurong.platform.core.clientimport.service.EntityAuditHelper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@ConditionalOnClientCommunicationEnabled
public class ClientDispatchTaskServiceImpl
        extends ServiceImpl<ClientDispatchTaskMapper, ClientDispatchTask>
        implements ClientDispatchTaskService {

    @Override
    public ClientDispatchTask getByTaskId(String taskId) {
        return getOne(Wrappers.lambdaQuery(ClientDispatchTask.class)
                .eq(ClientDispatchTask::getTaskId, taskId), false);
    }

    @Override
    public void updateStatus(String taskId, DispatchStatus status, String message) {
        ClientDispatchTask task = getByTaskId(taskId);
        if (task == null) {
            return;
        }
        task.setStatus(status.name());
        task.setErrorMessage(message);
        if (status == DispatchStatus.PUBLISHED) {
            task.setPublishTime(EntityAuditHelper.now());
        }
        if (status == DispatchStatus.RECEIVED) {
            task.setReceiveTime(EntityAuditHelper.now());
        }
        if (status == DispatchStatus.SUCCESS || status == DispatchStatus.FAILED || status == DispatchStatus.TIMEOUT) {
            task.setFinishTime(EntityAuditHelper.now());
        }
        EntityAuditHelper.prepareUpdate(task);
        updateById(task);
    }

    @Override
    public List<ClientDispatchTask> listTasksWaitingPublish(int limit) {
        return list(Wrappers.lambdaQuery(ClientDispatchTask.class)
                .in(ClientDispatchTask::getStatus,
                        DispatchStatus.PENDING_DISPATCH.name(),
                        DispatchStatus.PUBLISH_FAILED.name())
                .orderByAsc(ClientDispatchTask::getCreateTime)
                .last("OFFSET 0 ROWS FETCH NEXT " + limit + " ROWS ONLY"));
    }
}
