package com.ao.platform.auth.service.impl;

import com.ao.platform.auth.convert.SysTenantConvert;
import com.ao.platform.auth.dto.SysTenantDTO;
import com.ao.platform.auth.entity.SysTenant;
import com.ao.platform.auth.mapper.SysTenantMapper;
import com.ao.platform.auth.service.ISysTenantService;
import com.ao.platform.auth.vo.SysTenantVO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * 服务实现类
 */
@Service
public class SysTenantServiceImpl
        extends ServiceImpl<SysTenantMapper, SysTenant>
        implements ISysTenantService {

    private final SysTenantConvert convert;

    public SysTenantServiceImpl(SysTenantConvert convert) {
        this.convert = convert;
    }

    @Override
    public SysTenantVO getVOById(Serializable id) {
        SysTenant entity = this.getById(id);
        return convert.toVO(entity);
    }

    @Override
    public Long saveFromDTO(SysTenantDTO dto) {
        SysTenant entity = convert.toEntity(dto);
        this.save(entity);
        return entity.getId();
    }

    @Override
    public Boolean updateFromDTO(Serializable id, SysTenantDTO dto) {
        SysTenant entity = this.getById(id);
        convert.updateFromDTO(dto, entity);
        return this.updateById(entity);
    }
}
