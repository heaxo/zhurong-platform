package com.zhurong.platform.core.lantek.service.impl;

import com.zhurong.platform.core.lantek.convert.SystOwnd00000100Convert;
import com.zhurong.platform.core.lantek.dto.SystOwnd00000100DTO;
import com.zhurong.platform.core.lantek.entity.SystOwnd00000100;
import com.zhurong.platform.core.lantek.mapper.SystOwnd00000100Mapper;
import com.zhurong.platform.core.lantek.service.ISystOwnd00000100Service;
import com.zhurong.platform.core.lantek.vo.SystOwnd00000100VO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 服务实现类
 */
@Service
@RequiredArgsConstructor
public class SystOwnd00000100ServiceImpl
        extends ServiceImpl<SystOwnd00000100Mapper, SystOwnd00000100>
        implements ISystOwnd00000100Service {

    private final SystOwnd00000100Convert convert;


    @Override
    public SystOwnd00000100VO getVOById(Long id) {
        SystOwnd00000100 entity = this.getById(id);
        return convert.toVO(entity);
    }

    @Override
    public Long saveFromDTO(SystOwnd00000100DTO dto) {
        SystOwnd00000100 entity = convert.toEntity(dto);
        this.save(entity);
        return entity.getId();
    }

    @Override
    public Boolean updateFromDTO(Long id, SystOwnd00000100DTO dto) {
        SystOwnd00000100 entity = this.getById(id);
        convert.updateFromDTO(dto, entity);
        return this.updateById(entity);
    }
}
