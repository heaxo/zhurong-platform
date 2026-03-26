package com.zhurong.platform.core.lantek.service.impl;

import com.zhurong.platform.core.lantek.convert.ZhurongPlatformNest100StateSnapshotConvert;
import com.zhurong.platform.core.lantek.dto.ZhurongPlatformNest100StateSnapshotDTO;
import com.zhurong.platform.core.lantek.entity.ZhurongPlatformNest100StateSnapshot;
import com.zhurong.platform.core.lantek.entity.DisNestNest00000100;
import com.zhurong.platform.core.lantek.event.NestEventPublisher;
import com.zhurong.platform.core.lantek.mapper.ZhurongPlatformNest100StateSnapshotMapper;
import com.zhurong.platform.core.lantek.service.IZhurongPlatformNest100StateSnapshotService;
import com.zhurong.platform.core.lantek.service.IDisNestNest00000100Service;
import com.zhurong.platform.core.lantek.vo.ZhurongPlatformNest100StateSnapshotVO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * 服务实现类
 */
@Service
@RequiredArgsConstructor
public class ZhurongPlatformNest100StateSnapshotServiceImpl
        extends ServiceImpl<ZhurongPlatformNest100StateSnapshotMapper, ZhurongPlatformNest100StateSnapshot>
        implements IZhurongPlatformNest100StateSnapshotService {

    private final ZhurongPlatformNest100StateSnapshotConvert convert;
    private final ZhurongPlatformNest100StateSnapshotMapper nest100StateSnapshotMapper;
    private final IDisNestNest00000100Service disNestNest00000100Service;
    private final NestEventPublisher eventPublisher;

    @Override
    public ZhurongPlatformNest100StateSnapshotVO getVOById(Long id) {
        ZhurongPlatformNest100StateSnapshot entity = this.getById(id);
        return convert.toVO(entity);
    }

    @Override
    public Long saveFromDTO(ZhurongPlatformNest100StateSnapshotDTO dto) {
        ZhurongPlatformNest100StateSnapshot entity = convert.toEntity(dto);
        this.save(entity);
        return entity.getId();
    }

    @Override
    public Boolean updateFromDTO(Long id, ZhurongPlatformNest100StateSnapshotDTO dto) {
        ZhurongPlatformNest100StateSnapshot entity = this.getById(id);
        convert.updateFromDTO(dto, entity);
        return this.updateById(entity);
    }

    @Transactional
    public void syncState() {

        //查询最近3天的数据
        List<DisNestNest00000100> records =
                disNestNest00000100Service.lambdaQuery()
                        .ge(DisNestNest00000100::getLastDate,
                                LocalDateTime.now().minusDays(3))
                        .list();

        if (records.isEmpty()) {
            return;
        }

        for (DisNestNest00000100 record : records) {

            Integer recId = record.getRecID();
            Integer newState = record.getMState();

            // 查询 snapshot
            ZhurongPlatformNest100StateSnapshot snapshot =
                    nest100StateSnapshotMapper.selectById(recId);

            // 如果 snapshot 不存在 -> 初始化
            if (snapshot == null) {

                ZhurongPlatformNest100StateSnapshot newSnapshot =
                        new ZhurongPlatformNest100StateSnapshot();

                newSnapshot.setRecID(recId);
                newSnapshot.setMState(newState);
                newSnapshot.setLastDate(record.getLastDate());
                newSnapshot.setSyncTime(LocalDateTime.now());

                nest100StateSnapshotMapper.insert(newSnapshot);

                continue;
            }

            Integer oldState = snapshot.getMState();

            // 状态发生变化
            if (!Objects.equals(oldState, newState)) {

                // 发布事件
                eventPublisher.publish(
                        recId,
                        oldState,
                        newState,
                        record.getLastDate()
                );

                // 更新 snapshot
                snapshot.setMState(newState);
                snapshot.setLastDate(record.getLastDate());
                snapshot.setSyncTime(LocalDateTime.now());

                nest100StateSnapshotMapper.updateById(snapshot);
            }
        }
    }
}
