package com.ao.platform.auth.service;

import com.ao.platform.auth.dto.SysDeptDTO;
import com.ao.platform.auth.entity.SysDept;
import com.ao.platform.auth.vo.SysDeptVO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 服务接口
 */
public interface ISysDeptService extends IService<SysDept> {
    SysDeptVO getVOById(Long id);

    Long saveFromDTO(SysDeptDTO dto);

    Boolean updateFromDTO(Long id, SysDeptDTO dto);
}
