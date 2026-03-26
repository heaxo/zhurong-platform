package com.zhurong.platform.auth.service.impl;

import com.zhurong.platform.auth.convert.SysRoleMenuConvert;
import com.zhurong.platform.auth.dto.SysRoleMenuDTO;
import com.zhurong.platform.auth.entity.SysRoleMenu;
import com.zhurong.platform.auth.mapper.SysRoleMenuMapper;
import com.zhurong.platform.auth.service.ISysRoleMenuService;
import com.zhurong.platform.auth.vo.SysRoleMenuVO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 服务实现类
 */
@Service
@RequiredArgsConstructor
public class SysRoleMenuServiceImpl
        extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu>
        implements ISysRoleMenuService {

    private final SysRoleMenuConvert convert;


    @Override
    public SysRoleMenuVO getVOById(Long id) {
        SysRoleMenu entity = this.getById(id);
        return convert.toVO(entity);
    }

    @Override
    public Long saveFromDTO(SysRoleMenuDTO dto) {
        SysRoleMenu entity = convert.toEntity(dto);
        if (entity.getTenantId() == null){
            entity.setTenantId(0L);
        }
        this.save(entity);
        return entity.getId();
    }

    @Override
    public Boolean updateFromDTO(Long id, SysRoleMenuDTO dto) {
        SysRoleMenu entity = this.getById(id);
        convert.updateFromDTO(dto, entity);
        return this.updateById(entity);
    }
}
