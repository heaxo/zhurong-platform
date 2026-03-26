package com.zhurong.platform.auth.service;

import com.zhurong.platform.auth.dto.SysTenantDTO;
import com.zhurong.platform.auth.entity.SysTenant;
import com.zhurong.platform.auth.vo.SysTenantVO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 服务接口
 */
public interface ISysTenantService extends IService<SysTenant> {
    SysTenantVO getVOById(Long id);

    Long saveFromDTO(SysTenantDTO dto);

    Boolean updateFromDTO(Long id, SysTenantDTO dto);
}
