package com.ao.platform.auth.service;

import com.ao.platform.auth.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
*  服务接口
*/
public interface ISysUserService extends IService<SysUser> {
    SysUserVO getVOById(Serializable id);

    void saveFromDTO(SysUserDTO dto);

    void updateFromDTO(Serializable id, SysUserDTO dto);
}
