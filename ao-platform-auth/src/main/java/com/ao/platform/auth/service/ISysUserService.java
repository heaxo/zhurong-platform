package com.ao.platform.auth.service;

import com.ao.platform.auth.dto.SysUserDTO;
import com.ao.platform.auth.entity.SysUser;
import com.ao.platform.auth.vo.SysUserVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.Serializable;

/**
 * 服务接口
 */
public interface ISysUserService extends IService<SysUser> {
    SysUserVO getVOById(Serializable id);

    Long saveFromDTO(SysUserDTO dto);

    Boolean updateFromDTO(Serializable id, SysUserDTO dto);
}
