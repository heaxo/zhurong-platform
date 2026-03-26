package com.zhurong.platform.auth.service;

import com.zhurong.platform.auth.dto.SysRoleDTO;
import com.zhurong.platform.auth.entity.SysRole;
import com.zhurong.platform.auth.vo.SysRoleVO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 服务接口
 */
public interface ISysRoleService extends IService<SysRole> {
    SysRoleVO getVOById(Long id);

    Long saveFromDTO(SysRoleDTO dto);

    Boolean updateFromDTO(Long id, SysRoleDTO dto);
}
