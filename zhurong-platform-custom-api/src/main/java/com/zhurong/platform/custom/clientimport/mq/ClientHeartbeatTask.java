package com.zhurong.platform.custom.clientimport.mq;

import com.zhurong.platform.custom.clientimport.configuration.ConditionalOnClientCommunicationEnabled;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@ConditionalOnClientCommunicationEnabled
@RequiredArgsConstructor
public class ClientHeartbeatTask {

    private final ClientStatusReporter statusReporter;

    @Scheduled(fixedDelayString = "#{${zhurong.client-communication.heartbeat-seconds:30} * 1000}")
    public void heartbeat() {
        try {
            statusReporter.heartbeat();
        } catch (Exception ex) {
            log.warn("客户端心跳上报失败 message={}", ex.getMessage());
        }
    }
}
