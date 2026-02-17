package com.ao.platform.auth.service;

import com.ao.platform.auth.entity.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;

/**
*  服务接口
*/
public interface ISysMenuService extends IService<SysMenu> {
    SysMenuVO getVOById(Serializable id);

    void saveFromDTO(SysMenuDTO dto);

    void updateFromDTO(Serializable id, SysMenuDTO dto);
}
