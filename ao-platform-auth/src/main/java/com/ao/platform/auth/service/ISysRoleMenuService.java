package com.ao.platform.auth.service;

import com.ao.platform.auth.entity.SysRoleMenu;
import com.baomidou.mybatisplus.extension.service.IService;

/**
*  服务接口
*/
public interface ISysRoleMenuService extends IService<SysRoleMenu> {
    SysRoleMenuVO getVOById(Serializable id);

    void saveFromDTO(SysRoleMenuDTO dto);

    void updateFromDTO(Serializable id, SysRoleMenuDTO dto);
}
