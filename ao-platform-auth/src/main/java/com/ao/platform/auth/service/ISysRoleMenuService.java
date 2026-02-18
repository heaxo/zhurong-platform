package com.ao.platform.auth.service;

import com.ao.platform.auth.dto.SysRoleMenuDTO;
import com.ao.platform.auth.entity.SysRoleMenu;
import com.ao.platform.auth.vo.SysRoleMenuVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.Serializable;

/**
 * 服务接口
 */
public interface ISysRoleMenuService extends IService<SysRoleMenu> {
    SysRoleMenuVO getVOById(Serializable id);

    Long saveFromDTO(SysRoleMenuDTO dto);

    Boolean updateFromDTO(Serializable id, SysRoleMenuDTO dto);
}
