package com.zhurong.platform.core.lantek.service.impl;

import com.zhurong.platform.core.lantek.convert.DisNestNest00000400Convert;
import com.zhurong.platform.core.lantek.dto.DisNestNest00000400DTO;
import com.zhurong.platform.core.lantek.entity.DisNestNest00000400;
import com.zhurong.platform.core.lantek.mapper.DisNestNest00000400Mapper;
import com.zhurong.platform.core.lantek.service.IDisNestNest00000400Service;
import com.zhurong.platform.core.lantek.vo.DisNestNest00000400VO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 服务实现类
 */
@Service
@RequiredArgsConstructor
public class DisNestNest00000400ServiceImpl
        extends ServiceImpl<DisNestNest00000400Mapper, DisNestNest00000400>
        implements IDisNestNest00000400Service {

    private final DisNestNest00000400Convert convert;


    @Override
    public DisNestNest00000400VO getVOById(Long id) {
        DisNestNest00000400 entity = this.getById(id);
        return convert.toVO(entity);
    }

    @Override
    public Long saveFromDTO(DisNestNest00000400DTO dto) {
        DisNestNest00000400 entity = convert.toEntity(dto);
        this.save(entity);
        return entity.getId();
    }

    @Override
    public Boolean updateFromDTO(Long id, DisNestNest00000400DTO dto) {
        DisNestNest00000400 entity = this.getById(id);
        convert.updateFromDTO(dto, entity);
        return this.updateById(entity);
    }
}
