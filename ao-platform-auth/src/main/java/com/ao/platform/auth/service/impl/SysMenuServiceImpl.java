package com.ao.platform.auth.service.impl;

import com.ao.platform.auth.entity.SysMenu;
import com.ao.platform.auth.mapper.SysMenuMapper;
import com.ao.platform.auth.service.ISysMenuService;
import com.ao.platform.auth.convert.SysMenuConvert;
import com.ao.platform.auth.dto.SysMenuDTO;
import com.ao.platform.auth.vo.SysMenuVO;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
*  服务实现类
*/
@Service
public class SysMenuServiceImpl
extends ServiceImpl<SysMenuMapper, SysMenu>
implements ISysMenuService {

    private final SysMenuConvert convert;

    public SysMenuServiceImpl(SysMenuConvert convert) {
    this.convert = convert;
    }

    @Override
    public SysMenuVO getVOById(Serializable id) {
    SysMenu entity = this.getById(id);
    return convert.toVO(entity);
    }

    @Override
    public void saveFromDTO(SysMenuDTO dto) {
    SysMenu entity = convert.toEntity(dto);
    this.save(entity);
    }

    @Override
    public void updateFromDTO(Serializable id, SysMenuDTO dto) {
    SysMenu entity = this.getById(id);
    convert.updateFromDTO(dto, entity);
    this.updateById(entity);
    }
}
