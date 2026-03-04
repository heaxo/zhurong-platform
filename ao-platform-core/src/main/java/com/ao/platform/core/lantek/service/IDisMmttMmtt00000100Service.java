package com.ao.platform.core.lantek.service;

import com.ao.platform.core.lantek.dto.DisMmttMmtt00000100DTO;
import com.ao.platform.core.lantek.entity.DisMmttMmtt00000100;
import com.ao.platform.core.lantek.vo.DisMmttMmtt00000100VO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 服务接口
 */
public interface IDisMmttMmtt00000100Service extends IService<DisMmttMmtt00000100> {
    DisMmttMmtt00000100VO getVOById(Long id);

    Long saveFromDTO(DisMmttMmtt00000100DTO dto);

    Boolean updateFromDTO(Long id, DisMmttMmtt00000100DTO dto);
}
