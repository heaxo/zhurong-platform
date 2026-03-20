package com.ao.platform.core.lantek.service.impl;

import com.ao.platform.core.lantek.convert.AoPlatformJobCursorConvert;
import com.ao.platform.core.lantek.dto.AoPlatformJobCursorDTO;
import com.ao.platform.core.lantek.entity.AoPlatformJobCursor;
import com.ao.platform.core.lantek.mapper.AoPlatformJobCursorMapper;
import com.ao.platform.core.lantek.service.IAoPlatformJobCursorService;
import com.ao.platform.core.lantek.vo.AoPlatformJobCursorVO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 服务实现类
 */
@Service
@RequiredArgsConstructor
public class AoPlatformJobCursorServiceImpl
        extends ServiceImpl<AoPlatformJobCursorMapper, AoPlatformJobCursor>
        implements IAoPlatformJobCursorService {

    private final AoPlatformJobCursorConvert convert;


    @Override
    public AoPlatformJobCursorVO getVOById(Long id) {
        AoPlatformJobCursor entity = this.getById(id);
        return convert.toVO(entity);
    }

    @Override
    public Long saveFromDTO(AoPlatformJobCursorDTO dto) {
        AoPlatformJobCursor entity = convert.toEntity(dto);
        this.save(entity);
        return entity.getId();
    }

    @Override
    public Boolean updateFromDTO(Long id, AoPlatformJobCursorDTO dto) {
        AoPlatformJobCursor entity = this.getById(id);
        convert.updateFromDTO(dto, entity);
        return this.updateById(entity);
    }
}
