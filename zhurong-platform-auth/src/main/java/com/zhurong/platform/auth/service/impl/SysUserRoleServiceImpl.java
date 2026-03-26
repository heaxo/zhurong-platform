package com.zhurong.platform.auth.service.impl;

import com.zhurong.platform.auth.convert.SysUserRoleConvert;
import com.zhurong.platform.auth.dto.SysUserRoleDTO;
import com.zhurong.platform.auth.entity.SysUserRole;
import com.zhurong.platform.auth.mapper.SysUserRoleMapper;
import com.zhurong.platform.auth.service.ISysUserRoleService;
import com.zhurong.platform.auth.vo.SysUserRoleVO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
    public SysUserRoleVO getVOById(Long id) {
        SysUserRole entity = this.getById(id);
        if (entity.getTenantId() == null){
            entity.setTenantId(0L);
        }
        return convert.toVO(entity);
    }

    @Override
    public Long saveFromDTO(SysUserRoleDTO dto) {
        SysUserRole entity = convert.toEntity(dto);
        this.save(entity);
        return entity.getId();
    }

    @Override
    public Boolean updateFromDTO(Long id, SysUserRoleDTO dto) {
        SysUserRole entity = this.getById(id);
        convert.updateFromDTO(dto, entity);
        return this.updateById(entity);
    }
}
