package com.ao.platform.core.lantek.service;

import com.ao.platform.core.lantek.dto.PprrPprr00000100DTO;
import com.ao.platform.core.lantek.entity.PprrPprr00000100;
import com.ao.platform.core.lantek.vo.PprrPprr00000100VO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 服务接口
 */
public interface IPprrPprr00000100Service extends IService<PprrPprr00000100> {
    PprrPprr00000100VO getVOById(Long id);

    Long saveFromDTO(PprrPprr00000100DTO dto);

    Boolean updateFromDTO(Long id, PprrPprr00000100DTO dto);
}
