package com.ao.platform.auth.service.impl;

import com.ao.platform.auth.convert.SysUserRoleConvert;
import com.ao.platform.auth.dto.SysUserRoleDTO;
import com.ao.platform.auth.entity.SysUserRole;
import com.ao.platform.auth.mapper.SysUserRoleMapper;
import com.ao.platform.auth.service.ISysUserRoleService;
import com.ao.platform.auth.vo.SysUserRoleVO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * 服务实现类
 */
@Service
@RequiredArgsConstructor
public class SysUserRoleServiceImpl
        extends ServiceImpl<SysUserRoleMapper, SysUserRole>
        implements ISysUserRoleService {

    private final SysUserRoleConvert convert;


    @Override
    public SysUserRoleVO getVOById(Serializable id) {
        SysUserRole entity = this.getById(id);
        return convert.toVO(entity);
    }

    @Override
    public Long saveFromDTO(SysUserRoleDTO dto) {
        SysUserRole entity = convert.toEntity(dto);
        this.save(entity);
        return entity.getId();
    }

    @Override
    public Boolean updateFromDTO(Serializable id, SysUserRoleDTO dto) {
        SysUserRole entity = this.getById(id);
        convert.updateFromDTO(dto, entity);
        return this.updateById(entity);
    }
}
