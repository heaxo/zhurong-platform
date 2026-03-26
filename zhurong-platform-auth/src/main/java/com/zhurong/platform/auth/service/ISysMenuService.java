package com.zhurong.platform.auth.service;

import com.zhurong.platform.auth.dto.SysMenuDTO;
import com.zhurong.platform.auth.entity.SysMenu;
import com.zhurong.platform.auth.vo.SysMenuVO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 服务接口
 */
public interface ISysMenuService extends IService<SysMenu> {
    SysMenuVO getVOById(Long id);

    Long saveFromDTO(SysMenuDTO dto);

    Boolean updateFromDTO(Long id, SysMenuDTO dto);
}
