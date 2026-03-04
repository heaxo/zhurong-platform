package com.ao.platform.core.lantek.service;

import com.ao.platform.core.lantek.dto.SystOwnd00000100DTO;
import com.ao.platform.core.lantek.entity.SystOwnd00000100;
import com.ao.platform.core.lantek.vo.SystOwnd00000100VO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 服务接口
 */
public interface ISystOwnd00000100Service extends IService<SystOwnd00000100> {
    SystOwnd00000100VO getVOById(Long id);

    Long saveFromDTO(SystOwnd00000100DTO dto);

    Boolean updateFromDTO(Long id, SystOwnd00000100DTO dto);
}
