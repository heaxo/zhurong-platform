package com.ao.platform.auth.service.impl;

import com.ao.platform.auth.convert.SysRoleMenuConvert;
import com.ao.platform.auth.dto.SysRoleMenuDTO;
import com.ao.platform.auth.entity.SysRoleMenu;
import com.ao.platform.auth.mapper.SysRoleMenuMapper;
import com.ao.platform.auth.service.ISysRoleMenuService;
import com.ao.platform.auth.vo.SysRoleMenuVO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * 服务实现类
 */
@Service
public class SysRoleMenuServiceImpl
        extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu>
        implements ISysRoleMenuService {

    private final SysRoleMenuConvert convert;

    public SysRoleMenuServiceImpl(SysRoleMenuConvert convert) {
        this.convert = convert;
    }

    @Override
    public SysRoleMenuVO getVOById(Serializable id) {
        SysRoleMenu entity = this.getById(id);
        return convert.toVO(entity);
    }

    @Override
    public Long saveFromDTO(SysRoleMenuDTO dto) {
        SysRoleMenu entity = convert.toEntity(dto);
        this.save(entity);
        return entity.getId();
    }

    @Override
    public Boolean updateFromDTO(Serializable id, SysRoleMenuDTO dto) {
        SysRoleMenu entity = this.getById(id);
        convert.updateFromDTO(dto, entity);
        return this.updateById(entity);
    }
}
