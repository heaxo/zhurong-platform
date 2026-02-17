package com.ao.platform.auth.service;

import com.ao.platform.auth.entity.SysUserRole;
import com.baomidou.mybatisplus.extension.service.IService;

/**
*  服务接口
*/
public interface ISysUserRoleService extends IService<SysUserRole> {
    SysUserRoleVO getVOById(Serializable id);

    void saveFromDTO(SysUserRoleDTO dto);

    void updateFromDTO(Serializable id, SysUserRoleDTO dto);
}
