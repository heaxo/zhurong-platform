package com.ao.platform.core.lantek.service.impl;

import com.ao.platform.core.lantek.convert.DisNestNest00000100Convert;
import com.ao.platform.core.lantek.dto.DisNestNest00000100DTO;
import com.ao.platform.core.lantek.entity.DisNestNest00000100;
import com.ao.platform.core.lantek.mapper.DisNestNest00000100Mapper;
import com.ao.platform.core.lantek.service.IDisNestNest00000100Service;
import com.ao.platform.core.lantek.vo.DisNestNest00000100VO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 服务实现类
 */
@Service
@RequiredArgsConstructor
public class DisNestNest00000100ServiceImpl
        extends ServiceImpl<DisNestNest00000100Mapper, DisNestNest00000100>
        implements IDisNestNest00000100Service {

    private final DisNestNest00000100Convert convert;


    @Override
    public DisNestNest00000100VO getVOById(Long id) {
        DisNestNest00000100 entity = this.getById(id);
        return convert.toVO(entity);
    }

    @Override
    public Long saveFromDTO(DisNestNest00000100DTO dto) {
        DisNestNest00000100 entity = convert.toEntity(dto);
        this.save(entity);
        return entity.getId();
    }

    @Override
    public Boolean updateFromDTO(Long id, DisNestNest00000100DTO dto) {
        DisNestNest00000100 entity = this.getById(id);
        convert.updateFromDTO(dto, entity);
        return this.updateById(entity);
    }
}
