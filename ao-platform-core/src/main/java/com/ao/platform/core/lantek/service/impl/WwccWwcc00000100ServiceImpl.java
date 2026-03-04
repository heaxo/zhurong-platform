package com.ao.platform.core.lantek.service.impl;

import com.ao.platform.core.lantek.convert.WwccWwcc00000100Convert;
import com.ao.platform.core.lantek.dto.WwccWwcc00000100DTO;
import com.ao.platform.core.lantek.entity.WwccWwcc00000100;
import com.ao.platform.core.lantek.mapper.WwccWwcc00000100Mapper;
import com.ao.platform.core.lantek.service.IWwccWwcc00000100Service;
import com.ao.platform.core.lantek.vo.WwccWwcc00000100VO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 服务实现类
 */
@Service
@RequiredArgsConstructor
public class WwccWwcc00000100ServiceImpl
        extends ServiceImpl<WwccWwcc00000100Mapper, WwccWwcc00000100>
        implements IWwccWwcc00000100Service {

    private final WwccWwcc00000100Convert convert;


    @Override
    public WwccWwcc00000100VO getVOById(Long id) {
        WwccWwcc00000100 entity = this.getById(id);
        return convert.toVO(entity);
    }

    @Override
    public Long saveFromDTO(WwccWwcc00000100DTO dto) {
        WwccWwcc00000100 entity = convert.toEntity(dto);
        this.save(entity);
        return entity.getId();
    }

    @Override
    public Boolean updateFromDTO(Long id, WwccWwcc00000100DTO dto) {
        WwccWwcc00000100 entity = this.getById(id);
        convert.updateFromDTO(dto, entity);
        return this.updateById(entity);
    }
}
