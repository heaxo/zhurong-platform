package com.ao.platform.core.lantek.service.impl;

import com.ao.platform.core.lantek.convert.DisMmnnMmoo00000200Convert;
import com.ao.platform.core.lantek.dto.DisMmnnMmoo00000200DTO;
import com.ao.platform.core.lantek.entity.DisMmnnMmoo00000200;
import com.ao.platform.core.lantek.mapper.DisMmnnMmoo00000200Mapper;
import com.ao.platform.core.lantek.service.IDisMmnnMmoo00000200Service;
import com.ao.platform.core.lantek.vo.DisMmnnMmoo00000200VO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 服务实现类
 */
@Service
@RequiredArgsConstructor
public class DisMmnnMmoo00000200ServiceImpl
        extends ServiceImpl<DisMmnnMmoo00000200Mapper, DisMmnnMmoo00000200>
        implements IDisMmnnMmoo00000200Service {

    private final DisMmnnMmoo00000200Convert convert;


    @Override
    public DisMmnnMmoo00000200VO getVOById(Long id) {
        DisMmnnMmoo00000200 entity = this.getById(id);
        return convert.toVO(entity);
    }

    @Override
    public Long saveFromDTO(DisMmnnMmoo00000200DTO dto) {
        DisMmnnMmoo00000200 entity = convert.toEntity(dto);
        this.save(entity);
        return entity.getId();
    }

    @Override
    public Boolean updateFromDTO(Long id, DisMmnnMmoo00000200DTO dto) {
        DisMmnnMmoo00000200 entity = this.getById(id);
        convert.updateFromDTO(dto, entity);
        return this.updateById(entity);
    }
}
