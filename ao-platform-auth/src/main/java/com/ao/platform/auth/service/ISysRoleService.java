package com.ao.platform.auth.service;

import com.ao.platform.auth.entity.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;

/**
*  服务接口
*/
public interface ISysRoleService extends IService<SysRole> {
    SysRoleVO getVOById(Serializable id);

    void saveFromDTO(SysRoleDTO dto);

    void updateFromDTO(Serializable id, SysRoleDTO dto);
}
