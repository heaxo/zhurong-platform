package com.ao.platform.core.lantek.service.impl;

import com.ao.platform.core.lantek.convert.DisNestNest00000200Convert;
import com.ao.platform.core.lantek.dto.DisNestNest00000200DTO;
import com.ao.platform.core.lantek.entity.DisNestNest00000200;
import com.ao.platform.core.lantek.mapper.DisNestNest00000200Mapper;
import com.ao.platform.core.lantek.service.IDisNestNest00000200Service;
import com.ao.platform.core.lantek.vo.DisNestNest00000200VO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 服务实现类
 */
@Service
@RequiredArgsConstructor
public class DisNestNest00000200ServiceImpl
        extends ServiceImpl<DisNestNest00000200Mapper, DisNestNest00000200>
        implements IDisNestNest00000200Service {

    private final DisNestNest00000200Convert convert;


    @Override
    public DisNestNest00000200VO getVOById(Long id) {
        DisNestNest00000200 entity = this.getById(id);
        return convert.toVO(entity);
    }

    @Override
    public Long saveFromDTO(DisNestNest00000200DTO dto) {
        DisNestNest00000200 entity = convert.toEntity(dto);
        this.save(entity);
        return entity.getId();
    }

    @Override
    public Boolean updateFromDTO(Long id, DisNestNest00000200DTO dto) {
        DisNestNest00000200 entity = this.getById(id);
        convert.updateFromDTO(dto, entity);
        return this.updateById(entity);
    }
}
