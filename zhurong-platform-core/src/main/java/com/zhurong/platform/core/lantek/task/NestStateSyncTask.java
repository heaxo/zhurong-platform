package com.zhurong.platform.core.lantek.task;

import com.zhurong.platform.core.lantek.service.INestStateService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(
        prefix = "zhurong.rabbitmq",
        name = "enabled",
        havingValue = "true",
        matchIfMissing = false
)
public class NestStateSyncTask {

    private final INestStateService nestStateService;

    @Scheduled(fixedDelay = 30000)
    public void syncNestState() {
        nestStateService.syncState();
    }
}