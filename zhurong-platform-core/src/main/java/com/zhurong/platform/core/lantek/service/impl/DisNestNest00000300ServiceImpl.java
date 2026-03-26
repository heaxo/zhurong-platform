package com.zhurong.platform.core.lantek.service.impl;

import com.zhurong.platform.core.lantek.convert.DisNestNest00000300Convert;
import com.zhurong.platform.core.lantek.dto.DisNestNest00000300DTO;
import com.zhurong.platform.core.lantek.entity.DisNestNest00000300;
import com.zhurong.platform.core.lantek.mapper.DisNestNest00000300Mapper;
import com.zhurong.platform.core.lantek.service.IDisNestNest00000300Service;
import com.zhurong.platform.core.lantek.vo.DisNestNest00000300VO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 服务实现类
 */
@Service
@RequiredArgsConstructor
public class DisNestNest00000300ServiceImpl
        extends ServiceImpl<DisNestNest00000300Mapper, DisNestNest00000300>
        implements IDisNestNest00000300Service {

    private final DisNestNest00000300Convert convert;


    @Override
    public DisNestNest00000300VO getVOById(Long id) {
        DisNestNest00000300 entity = this.getById(id);
        return convert.toVO(entity);
    }

    @Override
    public Long saveFromDTO(DisNestNest00000300DTO dto) {
        DisNestNest00000300 entity = convert.toEntity(dto);
        this.save(entity);
        return entity.getId();
    }

    @Override
    public Boolean updateFromDTO(Long id, DisNestNest00000300DTO dto) {
        DisNestNest00000300 entity = this.getById(id);
        convert.updateFromDTO(dto, entity);
        return this.updateById(entity);
    }
}
