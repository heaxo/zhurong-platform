package com.ao.platform.auth.service.impl;

import com.ao.platform.auth.convert.SysDeptConvert;
import com.ao.platform.auth.dto.SysDeptDTO;
import com.ao.platform.auth.entity.SysDept;
import com.ao.platform.auth.mapper.SysDeptMapper;
import com.ao.platform.auth.service.ISysDeptService;
import com.ao.platform.auth.vo.SysDeptVO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * 服务实现类
 */
@Service
public class SysDeptServiceImpl
        extends ServiceImpl<SysDeptMapper, SysDept>
        implements ISysDeptService {

    private final SysDeptConvert convert;

    public SysDeptServiceImpl(SysDeptConvert convert) {
        this.convert = convert;
    }

    @Override
    public SysDeptVO getVOById(Serializable id) {
        SysDept entity = this.getById(id);
        return convert.toVO(entity);
    }

    @Override
    public Long saveFromDTO(SysDeptDTO dto) {
        SysDept entity = convert.toEntity(dto);
        this.save(entity);
        return entity.getId();
    }

    @Override
    public Boolean updateFromDTO(Serializable id, SysDeptDTO dto) {
        SysDept entity = this.getById(id);
        convert.updateFromDTO(dto, entity);
        return this.updateById(entity);
    }
}
