package com.zhurong.platform.auth.service;

import com.zhurong.platform.auth.dto.SysUserRoleDTO;
import com.zhurong.platform.auth.entity.SysUserRole;
import com.zhurong.platform.auth.vo.SysUserRoleVO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 服务接口
 */
public interface ISysUserRoleService extends IService<SysUserRole> {
    SysUserRoleVO getVOById(Long id);

    Long saveFromDTO(SysUserRoleDTO dto);

    Boolean updateFromDTO(Long id, SysUserRoleDTO dto);
}
