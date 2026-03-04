package com.ao.platform.core.lantek.service.impl;

import com.ao.platform.core.lantek.convert.DisShprPptt00000200Convert;
import com.ao.platform.core.lantek.dto.DisShprPptt00000200DTO;
import com.ao.platform.core.lantek.entity.DisShprPptt00000200;
import com.ao.platform.core.lantek.mapper.DisShprPptt00000200Mapper;
import com.ao.platform.core.lantek.service.IDisShprPptt00000200Service;
import com.ao.platform.core.lantek.vo.DisShprPptt00000200VO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 服务实现类
 */
@Service
@RequiredArgsConstructor
public class DisShprPptt00000200ServiceImpl
        extends ServiceImpl<DisShprPptt00000200Mapper, DisShprPptt00000200>
        implements IDisShprPptt00000200Service {

    private final DisShprPptt00000200Convert convert;


    @Override
    public DisShprPptt00000200VO getVOById(Long id) {
        DisShprPptt00000200 entity = this.getById(id);
        return convert.toVO(entity);
    }

    @Override
    public Long saveFromDTO(DisShprPptt00000200DTO dto) {
        DisShprPptt00000200 entity = convert.toEntity(dto);
        this.save(entity);
        return entity.getId();
    }

    @Override
    public Boolean updateFromDTO(Long id, DisShprPptt00000200DTO dto) {
        DisShprPptt00000200 entity = this.getById(id);
        convert.updateFromDTO(dto, entity);
        return this.updateById(entity);
    }
}
