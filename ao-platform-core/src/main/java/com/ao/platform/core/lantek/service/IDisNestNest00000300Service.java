package com.ao.platform.core.lantek.service;

import com.ao.platform.core.lantek.dto.DisNestNest00000300DTO;
import com.ao.platform.core.lantek.entity.DisNestNest00000300;
import com.ao.platform.core.lantek.vo.DisNestNest00000300VO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 服务接口
 */
public interface IDisNestNest00000300Service extends IService<DisNestNest00000300> {
    DisNestNest00000300VO getVOById(Long id);

    Long saveFromDTO(DisNestNest00000300DTO dto);

    Boolean updateFromDTO(Long id, DisNestNest00000300DTO dto);
}
