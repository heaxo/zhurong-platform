package com.ao.platform.core.lantek.service.impl;

import com.ao.platform.core.lantek.entity.AoPlatformNest100StateSnapshot;
import com.ao.platform.core.lantek.entity.DisNestNest00000100;
import com.ao.platform.core.lantek.event.NestEventPublisher;
import com.ao.platform.core.lantek.mapper.AoPlatformJobCursorMapper;
import com.ao.platform.core.lantek.mapper.AoPlatformNest100StateSnapshotMapper;
import com.ao.platform.core.lantek.mapper.AoPlatformOutboxEventMapper;
import com.ao.platform.core.lantek.service.IDisNestNest00000100Service;
import com.ao.platform.core.lantek.service.INestStateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/*
 * @Author a.he@lantek.com
 * @Description NestStateServiceImpl
 * @Date 2026/3/5 23:16
 **/
@Service
@RequiredArgsConstructor
public class NestStateServiceImpl implements INestStateService {
    private final AoPlatformJobCursorMapper cursorMapper;
    private final AoPlatformNest100StateSnapshotMapper nest100StateSnapshotMapper;
    private final AoPlatformOutboxEventMapper outboxMapper;
    private final IDisNestNest00000100Service disNestNest00000100Service;
    private final NestEventPublisher eventPublisher;

    @Transactional
    public void syncState() {

        final String jobName = "nest100_state_sync";

        // 读取游标；没有则初始化为 3 天前
        LocalDateTime cursorTime = cursorMapper.getCursorTime(jobName);
        if (cursorTime == null) {
            cursorTime = LocalDateTime.now().minusDays(3);
            // 可能并发插入失败，这里允许失败
            try {
                cursorMapper.initCursor(jobName, cursorTime);
            } catch (Exception ignore) {
            }
        }

        //增量扫描：只扫 LastDate > cursorTime 的数据（按时间升序）
        List<DisNestNest00000100> records =
                disNestNest00000100Service.lambdaQuery()
                        .gt(DisNestNest00000100::getLastDate, cursorTime)
                        .orderByAsc(DisNestNest00000100::getLastDate)
                        .list();

        if (records.isEmpty()) {
            return;
        }

        LocalDateTime maxSeenLastDate = cursorTime;

        for (DisNestNest00000100 record : records) {

            Integer RecID = record.getRecID();
            Integer NewState = record.getMState();
            LocalDateTime LastDate = record.getLastDate();

            if (LastDate != null && LastDate.isAfter(maxSeenLastDate)) {
                maxSeenLastDate = LastDate;
            }

            // 只关心 10、40
            if (NewState == null || (NewState != 10 && NewState != 40)) {
                continue;
            }

            // 查快照
            AoPlatformNest100StateSnapshot snapshot =
                    nest100StateSnapshotMapper.selectById(RecID);

            // 快照不存在
            if (snapshot == null) {
                AoPlatformNest100StateSnapshot newSnapshot = new AoPlatformNest100StateSnapshot();
                newSnapshot.setRecID(RecID);
                newSnapshot.setMState(NewState);
                newSnapshot.setLastDate(LastDate);
                newSnapshot.setSyncTime(LocalDateTime.now());
                nest100StateSnapshotMapper.insert(newSnapshot);
                eventPublisher.publish(RecID, null, NewState, LastDate);
                continue;
            }

            Integer OldState = snapshot.getMState();

            // 状态变化：发事件 + 更新快照
            if (!Objects.equals(OldState, NewState)) {

                //发布事件（建议包含 LastDate / 事件ID，后面我可以再帮你补强成 outbox）
                eventPublisher.publish(RecID, OldState, NewState, LastDate);

                // 更新快照
                snapshot.setMState(NewState);
                snapshot.setLastDate(LastDate);
                snapshot.setSyncTime(LocalDateTime.now());
                nest100StateSnapshotMapper.updateById(snapshot);
            }
        }

        // 推进游标（到本批次最大 LastDate）
        cursorMapper.updateCursorTime(jobName, maxSeenLastDate);
    }
}
