package com.zhurong.platform.core.lantek.service.impl;

import com.zhurong.platform.core.lantek.convert.DisNestNest00000500Convert;
import com.zhurong.platform.core.lantek.dto.DisNestNest00000500DTO;
import com.zhurong.platform.core.lantek.entity.DisNestNest00000500;
import com.zhurong.platform.core.lantek.mapper.DisNestNest00000500Mapper;
import com.zhurong.platform.core.lantek.service.IDisNestNest00000500Service;
import com.zhurong.platform.core.lantek.vo.DisNestNest00000500VO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 服务实现类
 */
@Service
@RequiredArgsConstructor
public class DisNestNest00000500ServiceImpl
        extends ServiceImpl<DisNestNest00000500Mapper, DisNestNest00000500>
        implements IDisNestNest00000500Service {

    private final DisNestNest00000500Convert convert;


    @Override
    public DisNestNest00000500VO getVOById(Long id) {
        DisNestNest00000500 entity = this.getById(id);
        return convert.toVO(entity);
    }

    @Override
    public Long saveFromDTO(DisNestNest00000500DTO dto) {
        DisNestNest00000500 entity = convert.toEntity(dto);
        this.save(entity);
        return entity.getId();
    }

    @Override
    public Boolean updateFromDTO(Long id, DisNestNest00000500DTO dto) {
        DisNestNest00000500 entity = this.getById(id);
        convert.updateFromDTO(dto, entity);
        return this.updateById(entity);
    }
}
