package com.zhurong.platform.core.lantek.service.impl;

import com.zhurong.platform.core.lantek.convert.ZhurongPlatformOutboxEventConvert;
import com.zhurong.platform.core.lantek.dto.ZhurongPlatformOutboxEventDTO;
import com.zhurong.platform.core.lantek.entity.ZhurongPlatformOutboxEvent;
import com.zhurong.platform.core.lantek.mapper.ZhurongPlatformOutboxEventMapper;
import com.zhurong.platform.core.lantek.service.IZhurongPlatformOutboxEventService;
import com.zhurong.platform.core.lantek.vo.ZhurongPlatformOutboxEventVO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 服务实现类
 */
@Service
@RequiredArgsConstructor
public class ZhurongPlatformOutboxEventServiceImpl
        extends ServiceImpl<ZhurongPlatformOutboxEventMapper, ZhurongPlatformOutboxEvent>
        implements IZhurongPlatformOutboxEventService {

    private final ZhurongPlatformOutboxEventConvert convert;


    @Override
    public ZhurongPlatformOutboxEventVO getVOById(Long id) {
        ZhurongPlatformOutboxEvent entity = this.getById(id);
        return convert.toVO(entity);
    }

    @Override
    public Long saveFromDTO(ZhurongPlatformOutboxEventDTO dto) {
        ZhurongPlatformOutboxEvent entity = convert.toEntity(dto);
        this.save(entity);
        return entity.getId();
    }

    @Override
    public Boolean updateFromDTO(Long id, ZhurongPlatformOutboxEventDTO dto) {
        ZhurongPlatformOutboxEvent entity = this.getById(id);
        convert.updateFromDTO(dto, entity);
        return this.updateById(entity);
    }
}
