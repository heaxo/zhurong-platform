package com.ao.platform.auth.service;

import com.ao.platform.auth.dto.SysMenuDTO;
import com.ao.platform.auth.entity.SysMenu;
import com.ao.platform.auth.vo.SysMenuVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.Serializable;

/**
 * 服务接口
 */
public interface ISysMenuService extends IService<SysMenu> {
    SysMenuVO getVOById(Serializable id);

    Long saveFromDTO(SysMenuDTO dto);

    Boolean updateFromDTO(Serializable id, SysMenuDTO dto);
}
