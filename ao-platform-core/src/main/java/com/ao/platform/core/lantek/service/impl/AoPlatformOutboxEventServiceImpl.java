package com.ao.platform.core.lantek.service.impl;

import com.ao.platform.core.lantek.convert.AoPlatformOutboxEventConvert;
import com.ao.platform.core.lantek.dto.AoPlatformOutboxEventDTO;
import com.ao.platform.core.lantek.entity.AoPlatformOutboxEvent;
import com.ao.platform.core.lantek.mapper.AoPlatformOutboxEventMapper;
import com.ao.platform.core.lantek.service.IAoPlatformOutboxEventService;
import com.ao.platform.core.lantek.vo.AoPlatformOutboxEventVO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 服务实现类
 */
@Service
@RequiredArgsConstructor
public class AoPlatformOutboxEventServiceImpl
        extends ServiceImpl<AoPlatformOutboxEventMapper, AoPlatformOutboxEvent>
        implements IAoPlatformOutboxEventService {

    private final AoPlatformOutboxEventConvert convert;


    @Override
    public AoPlatformOutboxEventVO getVOById(Long id) {
        AoPlatformOutboxEvent entity = this.getById(id);
        return convert.toVO(entity);
    }

    @Override
    public Long saveFromDTO(AoPlatformOutboxEventDTO dto) {
        AoPlatformOutboxEvent entity = convert.toEntity(dto);
        this.save(entity);
        return entity.getId();
    }

    @Override
    public Boolean updateFromDTO(Long id, AoPlatformOutboxEventDTO dto) {
        AoPlatformOutboxEvent entity = this.getById(id);
        convert.updateFromDTO(dto, entity);
        return this.updateById(entity);
    }
}
