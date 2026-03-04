package com.ao.platform.core.lantek.service.impl;

import com.ao.platform.core.lantek.convert.DisPpttTtrr00000700Convert;
import com.ao.platform.core.lantek.dto.DisPpttTtrr00000700DTO;
import com.ao.platform.core.lantek.entity.DisPpttTtrr00000700;
import com.ao.platform.core.lantek.mapper.DisPpttTtrr00000700Mapper;
import com.ao.platform.core.lantek.service.IDisPpttTtrr00000700Service;
import com.ao.platform.core.lantek.vo.DisPpttTtrr00000700VO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 服务实现类
 */
@Service
@RequiredArgsConstructor
public class DisPpttTtrr00000700ServiceImpl
        extends ServiceImpl<DisPpttTtrr00000700Mapper, DisPpttTtrr00000700>
        implements IDisPpttTtrr00000700Service {

    private final DisPpttTtrr00000700Convert convert;


    @Override
    public DisPpttTtrr00000700VO getVOById(Long id) {
        DisPpttTtrr00000700 entity = this.getById(id);
        return convert.toVO(entity);
    }

    @Override
    public Long saveFromDTO(DisPpttTtrr00000700DTO dto) {
        DisPpttTtrr00000700 entity = convert.toEntity(dto);
        this.save(entity);
        return entity.getId();
    }

    @Override
    public Boolean updateFromDTO(Long id, DisPpttTtrr00000700DTO dto) {
        DisPpttTtrr00000700 entity = this.getById(id);
        convert.updateFromDTO(dto, entity);
        return this.updateById(entity);
    }
}
