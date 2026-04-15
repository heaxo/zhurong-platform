package com.zhurong.platform.core.lantek.service;

import com.zhurong.platform.core.lantek.dto.PpbbPpbb00000100DTO;
import com.zhurong.platform.core.lantek.entity.PpbbPpbb00000100;
import com.zhurong.platform.core.lantek.vo.PpbbPpbb00000100VO;

/**
 * 服务接口
 */
public interface IPpbbPpbb00000100Service extends BaseIService<PpbbPpbb00000100> {
    PpbbPpbb00000100VO getVOById(Long id);

    Long saveFromDTO(PpbbPpbb00000100DTO dto);

    Boolean updateFromDTO(Long id, PpbbPpbb00000100DTO dto);
}
