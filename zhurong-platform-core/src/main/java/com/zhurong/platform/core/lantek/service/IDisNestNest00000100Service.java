package com.zhurong.platform.core.lantek.service;

import com.zhurong.platform.core.lantek.dto.DisNestNest00000100DTO;
import com.zhurong.platform.core.lantek.entity.DisNestNest00000100;
import com.zhurong.platform.core.lantek.vo.DisNestNest00000100VO;

/**
 * 服务接口
 */
public interface IDisNestNest00000100Service extends BaseIService<DisNestNest00000100> {
    DisNestNest00000100VO getVOById(Long id);

    Long saveFromDTO(DisNestNest00000100DTO dto);

    Boolean updateFromDTO(Long id, DisNestNest00000100DTO dto);

    //明细
    DisNestNest00000100VO details(Integer recID);
}
