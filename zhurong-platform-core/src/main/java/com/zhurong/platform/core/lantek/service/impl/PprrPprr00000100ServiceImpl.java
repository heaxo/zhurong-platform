package com.zhurong.platform.core.lantek.service.impl;

import com.zhurong.platform.core.lantek.convert.PprrPprr00000100Convert;
import com.zhurong.platform.core.lantek.dto.PprrPprr00000100DTO;
import com.zhurong.platform.core.lantek.entity.PprrPprr00000100;
import com.zhurong.platform.core.lantek.mapper.PprrPprr00000100Mapper;
import com.zhurong.platform.core.lantek.service.IPprrPprr00000100Service;
import com.zhurong.platform.core.lantek.vo.PprrPprr00000100VO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 服务实现类
 */
@Service
@RequiredArgsConstructor
public class PprrPprr00000100ServiceImpl
        extends ServiceImpl<PprrPprr00000100Mapper, PprrPprr00000100>
        implements IPprrPprr00000100Service {

    private final PprrPprr00000100Convert convert;


    @Override
    public PprrPprr00000100VO getVOById(Long id) {
        PprrPprr00000100 entity = this.getById(id);
        return convert.toVO(entity);
    }

    @Override
    public Long saveFromDTO(PprrPprr00000100DTO dto) {
        PprrPprr00000100 entity = convert.toEntity(dto);
        this.save(entity);
        return entity.getId();
    }

    @Override
    public Boolean updateFromDTO(Long id, PprrPprr00000100DTO dto) {
        PprrPprr00000100 entity = this.getById(id);
        convert.updateFromDTO(dto, entity);
        return this.updateById(entity);
    }
}
