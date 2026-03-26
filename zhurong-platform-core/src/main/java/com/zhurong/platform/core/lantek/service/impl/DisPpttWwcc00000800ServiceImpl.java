package com.zhurong.platform.core.lantek.service.impl;

import com.zhurong.platform.core.lantek.convert.DisPpttWwcc00000800Convert;
import com.zhurong.platform.core.lantek.dto.DisPpttWwcc00000800DTO;
import com.zhurong.platform.core.lantek.entity.DisPpttWwcc00000800;
import com.zhurong.platform.core.lantek.mapper.DisPpttWwcc00000800Mapper;
import com.zhurong.platform.core.lantek.service.IDisPpttWwcc00000800Service;
import com.zhurong.platform.core.lantek.vo.DisPpttWwcc00000800VO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 服务实现类
 */
@Service
@RequiredArgsConstructor
public class DisPpttWwcc00000800ServiceImpl
        extends ServiceImpl<DisPpttWwcc00000800Mapper, DisPpttWwcc00000800>
        implements IDisPpttWwcc00000800Service {

    private final DisPpttWwcc00000800Convert convert;


    @Override
    public DisPpttWwcc00000800VO getVOById(Long id) {
        DisPpttWwcc00000800 entity = this.getById(id);
        return convert.toVO(entity);
    }

    @Override
    public Long saveFromDTO(DisPpttWwcc00000800DTO dto) {
        DisPpttWwcc00000800 entity = convert.toEntity(dto);
        this.save(entity);
        return entity.getId();
    }

    @Override
    public Boolean updateFromDTO(Long id, DisPpttWwcc00000800DTO dto) {
        DisPpttWwcc00000800 entity = this.getById(id);
        convert.updateFromDTO(dto, entity);
        return this.updateById(entity);
    }
}
