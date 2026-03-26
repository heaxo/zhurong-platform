package com.zhurong.platform.auth.service;

import com.zhurong.platform.auth.dto.SysUserDTO;
import com.zhurong.platform.auth.entity.SysUser;
import com.zhurong.platform.auth.vo.SysUserVO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 服务接口
 */
public interface ISysUserService extends IService<SysUser> {
    SysUserVO getVOById(Long id);

    Long saveFromDTO(SysUserDTO dto);

    Boolean updateFromDTO(Long id, SysUserDTO dto);

    boolean createUser(SysUserDTO request);
}
