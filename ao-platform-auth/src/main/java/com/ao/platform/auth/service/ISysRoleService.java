package com.ao.platform.auth.service;

import com.ao.platform.auth.dto.SysRoleDTO;
import com.ao.platform.auth.entity.SysRole;
import com.ao.platform.auth.vo.SysRoleVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.Serializable;

/**
 * 服务接口
 */
public interface ISysRoleService extends IService<SysRole> {
    SysRoleVO getVOById(Serializable id);

    Long saveFromDTO(SysRoleDTO dto);

    Boolean updateFromDTO(Serializable id, SysRoleDTO dto);
}
