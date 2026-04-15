package com.zhurong.platform.core.lantek.service;

import com.zhurong.platform.core.lantek.dto.SystCcpp00000100DTO;
import com.zhurong.platform.core.lantek.entity.SystCcpp00000100;
import com.zhurong.platform.core.lantek.vo.SystCcpp00000100VO;

/**
 * 服务接口
 */
public interface ISystCcpp00000100Service extends BaseIService<SystCcpp00000100> {
    SystCcpp00000100VO getVOById(Long id);

    Long saveFromDTO(SystCcpp00000100DTO dto);

    Boolean updateFromDTO(Long id, SystCcpp00000100DTO dto);
}
