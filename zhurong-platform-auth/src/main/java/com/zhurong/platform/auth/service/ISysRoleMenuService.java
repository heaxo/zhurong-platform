package com.zhurong.platform.auth.service;

import com.zhurong.platform.auth.dto.SysRoleMenuDTO;
import com.zhurong.platform.auth.entity.SysRoleMenu;
import com.zhurong.platform.auth.vo.SysRoleMenuVO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 服务接口
 */
public interface ISysRoleMenuService extends IService<SysRoleMenu> {
    SysRoleMenuVO getVOById(Long id);

    Long saveFromDTO(SysRoleMenuDTO dto);

    Boolean updateFromDTO(Long id, SysRoleMenuDTO dto);
}
