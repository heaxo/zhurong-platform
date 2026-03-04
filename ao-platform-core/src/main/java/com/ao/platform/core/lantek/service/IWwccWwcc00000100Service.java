package com.ao.platform.core.lantek.service;

import com.ao.platform.core.lantek.dto.WwccWwcc00000100DTO;
import com.ao.platform.core.lantek.entity.WwccWwcc00000100;
import com.ao.platform.core.lantek.vo.WwccWwcc00000100VO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 服务接口
 */
public interface IWwccWwcc00000100Service extends IService<WwccWwcc00000100> {
    WwccWwcc00000100VO getVOById(Long id);

    Long saveFromDTO(WwccWwcc00000100DTO dto);

    Boolean updateFromDTO(Long id, WwccWwcc00000100DTO dto);
}
