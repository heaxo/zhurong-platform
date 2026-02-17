package com.ao.platform.auth.service;

import com.ao.platform.auth.entity.SysDept;
import com.baomidou.mybatisplus.extension.service.IService;

/**
*  服务接口
*/
public interface ISysDeptService extends IService<SysDept> {
    SysDeptVO getVOById(Serializable id);

    void saveFromDTO(SysDeptDTO dto);

    void updateFromDTO(Serializable id, SysDeptDTO dto);
}
