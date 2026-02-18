package com.ao.platform.auth.service.impl;

import com.ao.platform.auth.convert.SysRoleConvert;
import com.ao.platform.auth.dto.SysRoleDTO;
import com.ao.platform.auth.entity.SysRole;
import com.ao.platform.auth.mapper.SysRoleMapper;
import com.ao.platform.auth.service.ISysRoleService;
import com.ao.platform.auth.vo.SysRoleVO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * 服务实现类
 */
@Service
@RequiredArgsConstructor
public class SysRoleServiceImpl
        extends ServiceImpl<SysRoleMapper, SysRole>
        implements ISysRoleService {

    private final SysRoleConvert convert;


    @Override
    public SysRoleVO getVOById(Serializable id) {
        SysRole entity = this.getById(id);
        return convert.toVO(entity);
    }

    @Override
    public Long saveFromDTO(SysRoleDTO dto) {
        SysRole entity = convert.toEntity(dto);
        this.save(entity);
        return entity.getId();
    }

    @Override
    public Boolean updateFromDTO(Serializable id, SysRoleDTO dto) {
        SysRole entity = this.getById(id);
        convert.updateFromDTO(dto, entity);
        return this.updateById(entity);
    }
}
