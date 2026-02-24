package com.ao.platform.auth.service;

import com.ao.platform.auth.dto.SysTenantDTO;
import com.ao.platform.auth.entity.SysTenant;
import com.ao.platform.auth.vo.SysTenantVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.Serializable;

/**
 * 服务接口
 */
public interface ISysTenantService extends IService<SysTenant> {
    SysTenantVO getVOById(Long id);

    Long saveFromDTO(SysTenantDTO dto);

    Boolean updateFromDTO(Long id, SysTenantDTO dto);
}
