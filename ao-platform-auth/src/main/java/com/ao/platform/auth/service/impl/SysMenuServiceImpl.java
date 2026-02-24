package com.ao.platform.auth.service.impl;

import com.ao.platform.auth.convert.SysMenuConvert;
import com.ao.platform.auth.dto.SysMenuDTO;
import com.ao.platform.auth.entity.SysMenu;
import com.ao.platform.auth.mapper.SysMenuMapper;
import com.ao.platform.auth.service.ISysMenuService;
import com.ao.platform.auth.vo.SysMenuVO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * 服务实现类
 */
@Service
@RequiredArgsConstructor
public class SysMenuServiceImpl
        extends ServiceImpl<SysMenuMapper, SysMenu>
        implements ISysMenuService {

    private final SysMenuConvert convert;


    @Override
    public SysMenuVO getVOById(Long id) {
        SysMenu entity = this.getById(id);
        return convert.toVO(entity);
    }

    @Override
    public Long saveFromDTO(SysMenuDTO dto) {
        SysMenuDTO sysMenuDTO = dto.getMeta().to(dto);
        SysMenu entity = convert.toEntity(sysMenuDTO);
        if (entity.getTenantId() == null){
            entity.setTenantId(0L);
        }
        this.save(entity);
        return entity.getId();
    }

    @Override
    public Boolean updateFromDTO(Long id, SysMenuDTO dto) {
        SysMenuDTO sysMenuDTO = dto.getMeta().to(dto);
        SysMenu entity = this.getById(id);
        convert.updateFromDTO(sysMenuDTO, entity);
        return this.updateById(entity);
    }
}
