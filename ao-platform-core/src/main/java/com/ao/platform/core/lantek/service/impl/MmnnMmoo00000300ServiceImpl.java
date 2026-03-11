package com.ao.platform.core.lantek.service.impl;

import com.ao.platform.core.lantek.convert.MmnnMmoo00000300Convert;
import com.ao.platform.core.lantek.dto.MmnnMmoo00000300DTO;
import com.ao.platform.core.lantek.entity.MmnnMmoo00000300;
import com.ao.platform.core.lantek.mapper.MmnnMmoo00000300Mapper;
import com.ao.platform.core.lantek.service.IMmnnMmoo00000300Service;
import com.ao.platform.core.lantek.vo.MmnnMmoo00000300VO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 服务实现类
 */
@Service
@RequiredArgsConstructor
public class MmnnMmoo00000300ServiceImpl
        extends ServiceImpl<MmnnMmoo00000300Mapper, MmnnMmoo00000300>
        implements IMmnnMmoo00000300Service {

    private final MmnnMmoo00000300Convert convert;


    @Override
    public MmnnMmoo00000300Convert getConvert() {
        return convert;
    }

    @Override
    public MmnnMmoo00000300VO getVOById(Long id) {
        MmnnMmoo00000300 entity = this.getById(id);
        return convert.toVO(entity);
    }

    @Override
    public Long saveFromDTO(MmnnMmoo00000300DTO dto) {
        MmnnMmoo00000300 entity = convert.toEntity(dto);
        this.save(entity);
        return entity.getId();
    }

    @Override
    public Boolean updateFromDTO(Long id, MmnnMmoo00000300DTO dto) {
        MmnnMmoo00000300 entity = this.getById(id);
        convert.updateFromDTO(dto, entity);
        return this.updateById(entity);
    }
}
