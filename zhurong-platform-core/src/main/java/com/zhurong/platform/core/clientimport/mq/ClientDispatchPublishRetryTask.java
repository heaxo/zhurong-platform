package com.zhurong.platform.core.clientimport.mq;

import com.zhurong.platform.core.clientimport.configuration.ClientCommunicationProperties;
import com.zhurong.platform.core.clientimport.configuration.ConditionalOnClientCommunicationEnabled;
import com.zhurong.platform.core.clientimport.entity.ClientDispatchTask;
import com.zhurong.platform.core.clientimport.service.ClientDispatchTaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@ConditionalOnClientCommunicationEnabled
@RequiredArgsConstructor
public class ClientDispatchPublishRetryTask {

    private final ClientCommunicationProperties properties;
    private final ClientDispatchTaskService dispatchTaskService;
    private final ClientDispatchPublishService dispatchPublishService;

    @Scheduled(fixedDelayString = "${zhurong.client-communication.publish-retry-delay-millis:60000}")
    public void retryWaitingPublishTasks() {
        if (properties.getPublishRetryDelayMillis() <= 0) {
            return;
        }
        // 补偿入口只处理待发布或发布失败的任务，不重新创建业务记录；业务去重由三类业务唯一键保证。
        for (ClientDispatchTask task : dispatchTaskService.listTasksWaitingPublish(50)) {
            try {
                dispatchPublishService.publish(task.getTaskId());
            } catch (Exception ex) {
                log.warn("客户端派发任务补偿发布失败 taskId={}, message={}", task.getTaskId(), ex.getMessage());
            }
        }
    }
}
