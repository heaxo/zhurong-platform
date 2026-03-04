package com.ao.platform.core.lantek.service.impl;

import com.ao.platform.core.lantek.convert.MmnnMmoo00000100Convert;
import com.ao.platform.core.lantek.dto.MmnnMmoo00000100DTO;
import com.ao.platform.core.lantek.entity.MmnnMmoo00000100;
import com.ao.platform.core.lantek.mapper.MmnnMmoo00000100Mapper;
import com.ao.platform.core.lantek.service.IMmnnMmoo00000100Service;
import com.ao.platform.core.lantek.vo.MmnnMmoo00000100VO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 服务实现类
 */
@Service
@RequiredArgsConstructor
public class MmnnMmoo00000100ServiceImpl
        extends ServiceImpl<MmnnMmoo00000100Mapper, MmnnMmoo00000100>
        implements IMmnnMmoo00000100Service {

    private final MmnnMmoo00000100Convert convert;


    @Override
    public MmnnMmoo00000100VO getVOById(Long id) {
        MmnnMmoo00000100 entity = this.getById(id);
        return convert.toVO(entity);
    }

    @Override
    public Long saveFromDTO(MmnnMmoo00000100DTO dto) {
        MmnnMmoo00000100 entity = convert.toEntity(dto);
        this.save(entity);
        return entity.getId();
    }

    @Override
    public Boolean updateFromDTO(Long id, MmnnMmoo00000100DTO dto) {
        MmnnMmoo00000100 entity = this.getById(id);
        convert.updateFromDTO(dto, entity);
        return this.updateById(entity);
    }
}
