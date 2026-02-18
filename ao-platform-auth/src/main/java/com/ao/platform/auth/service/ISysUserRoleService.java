package com.ao.platform.auth.service;

import com.ao.platform.auth.dto.SysUserRoleDTO;
import com.ao.platform.auth.entity.SysUserRole;
import com.ao.platform.auth.vo.SysUserRoleVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.Serializable;

/**
 * 服务接口
 */
public interface ISysUserRoleService extends IService<SysUserRole> {
    SysUserRoleVO getVOById(Serializable id);

    Long saveFromDTO(SysUserRoleDTO dto);

    Boolean updateFromDTO(Serializable id, SysUserRoleDTO dto);
}
