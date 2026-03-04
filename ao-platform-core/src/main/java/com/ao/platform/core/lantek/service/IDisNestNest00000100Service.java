package com.ao.platform.core.lantek.service;

import com.ao.platform.core.lantek.dto.DisNestNest00000100DTO;
import com.ao.platform.core.lantek.entity.DisNestNest00000100;
import com.ao.platform.core.lantek.vo.DisNestNest00000100VO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 服务接口
 */
public interface IDisNestNest00000100Service extends IService<DisNestNest00000100> {
    DisNestNest00000100VO getVOById(Long id);

    Long saveFromDTO(DisNestNest00000100DTO dto);

    Boolean updateFromDTO(Long id, DisNestNest00000100DTO dto);
}
