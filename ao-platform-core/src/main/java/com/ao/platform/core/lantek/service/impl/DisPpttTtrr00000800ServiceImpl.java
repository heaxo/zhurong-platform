package com.ao.platform.core.lantek.service.impl;

import com.ao.platform.core.lantek.convert.DisPpttTtrr00000800Convert;
import com.ao.platform.core.lantek.dto.DisPpttTtrr00000800DTO;
import com.ao.platform.core.lantek.entity.DisPpttTtrr00000800;
import com.ao.platform.core.lantek.mapper.DisPpttTtrr00000800Mapper;
import com.ao.platform.core.lantek.service.IDisPpttTtrr00000800Service;
import com.ao.platform.core.lantek.vo.DisPpttTtrr00000800VO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 服务实现类
 */
@Service
@RequiredArgsConstructor
public class DisPpttTtrr00000800ServiceImpl
        extends ServiceImpl<DisPpttTtrr00000800Mapper, DisPpttTtrr00000800>
        implements IDisPpttTtrr00000800Service {

    private final DisPpttTtrr00000800Convert convert;


    @Override
    public DisPpttTtrr00000800VO getVOById(Long id) {
        DisPpttTtrr00000800 entity = this.getById(id);
        return convert.toVO(entity);
    }

    @Override
    public Long saveFromDTO(DisPpttTtrr00000800DTO dto) {
        DisPpttTtrr00000800 entity = convert.toEntity(dto);
        this.save(entity);
        return entity.getId();
    }

    @Override
    public Boolean updateFromDTO(Long id, DisPpttTtrr00000800DTO dto) {
        DisPpttTtrr00000800 entity = this.getById(id);
        convert.updateFromDTO(dto, entity);
        return this.updateById(entity);
    }
}
