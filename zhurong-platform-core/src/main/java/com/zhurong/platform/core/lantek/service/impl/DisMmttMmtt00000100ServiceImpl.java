package com.zhurong.platform.core.lantek.service.impl;

import com.zhurong.platform.core.lantek.convert.DisMmttMmtt00000100Convert;
import com.zhurong.platform.core.lantek.dto.DisMmttMmtt00000100DTO;
import com.zhurong.platform.core.lantek.entity.DisMmttMmtt00000100;
import com.zhurong.platform.core.lantek.mapper.DisMmttMmtt00000100Mapper;
import com.zhurong.platform.core.lantek.service.IDisMmttMmtt00000100Service;
import com.zhurong.platform.core.lantek.vo.DisMmttMmtt00000100VO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 服务实现类
 */
@Service
@RequiredArgsConstructor
public class DisMmttMmtt00000100ServiceImpl
        extends ServiceImpl<DisMmttMmtt00000100Mapper, DisMmttMmtt00000100>
        implements IDisMmttMmtt00000100Service {

    private final DisMmttMmtt00000100Convert convert;


    @Override
    public DisMmttMmtt00000100VO getVOById(Long id) {
        DisMmttMmtt00000100 entity = this.getById(id);
        return convert.toVO(entity);
    }

    @Override
    public Long saveFromDTO(DisMmttMmtt00000100DTO dto) {
        DisMmttMmtt00000100 entity = convert.toEntity(dto);
        this.save(entity);
        return entity.getId();
    }

    @Override
    public Boolean updateFromDTO(Long id, DisMmttMmtt00000100DTO dto) {
        DisMmttMmtt00000100 entity = this.getById(id);
        convert.updateFromDTO(dto, entity);
        return this.updateById(entity);
    }
}
