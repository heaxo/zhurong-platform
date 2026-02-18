package com.ao.platform.auth.service;

import com.ao.platform.auth.dto.SysDeptDTO;
import com.ao.platform.auth.entity.SysDept;
import com.ao.platform.auth.vo.SysDeptVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.Serializable;

/**
 * 服务接口
 */
public interface ISysDeptService extends IService<SysDept> {
    SysDeptVO getVOById(Serializable id);

    Long saveFromDTO(SysDeptDTO dto);

    Boolean updateFromDTO(Serializable id, SysDeptDTO dto);
}
