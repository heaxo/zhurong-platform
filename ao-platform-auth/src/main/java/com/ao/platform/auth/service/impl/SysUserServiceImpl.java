package com.ao.platform.auth.service.impl;

import com.ao.platform.auth.convert.SysUserConvert;
import com.ao.platform.auth.dto.SysUserDTO;
import com.ao.platform.auth.entity.SysUser;
import com.ao.platform.auth.mapper.SysUserMapper;
import com.ao.platform.auth.service.ISysUserService;
import com.ao.platform.auth.vo.SysUserVO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * 服务实现类
 */
@Service
public class SysUserServiceImpl
        extends ServiceImpl<SysUserMapper, SysUser>
        implements ISysUserService {

    private final SysUserConvert convert;

    public SysUserServiceImpl(SysUserConvert convert) {
        this.convert = convert;
    }

    @Override
    public SysUserVO getVOById(Serializable id) {
        SysUser entity = this.getById(id);
        return convert.toVO(entity);
    }

    @Override
    public Long saveFromDTO(SysUserDTO dto) {
        SysUser entity = convert.toEntity(dto);
        this.save(entity);
        return entity.getId();
    }

    @Override
    public Boolean updateFromDTO(Serializable id, SysUserDTO dto) {
        SysUser entity = this.getById(id);
        convert.updateFromDTO(dto, entity);
        return this.updateById(entity);
    }
}
