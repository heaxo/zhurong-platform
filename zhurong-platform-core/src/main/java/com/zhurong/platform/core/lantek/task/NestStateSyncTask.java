package com.zhurong.platform.core.lantek.task;

import com.zhurong.platform.core.lantek.service.INestStateService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NestStateSyncTask {

    private final INestStateService nestStateService;

    @Scheduled(fixedDelay = 30000)
    public void syncNestState() {
        nestStateService.syncState();
    }
}