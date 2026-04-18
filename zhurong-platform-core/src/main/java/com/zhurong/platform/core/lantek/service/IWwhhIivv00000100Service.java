package com.zhurong.platform.core.lantek.service;

import com.zhurong.platform.core.lantek.dto.WwhhIivv00000100DTO;
import com.zhurong.platform.core.lantek.entity.WwhhIivv00000100;
import com.zhurong.platform.core.lantek.vo.WwhhIivv00000100VO;

/**
 * 服务接口
 */
public interface IWwhhIivv00000100Service extends BaseIService<WwhhIivv00000100> {
    WwhhIivv00000100VO getVOById(Long id);

    Long saveFromDTO(WwhhIivv00000100DTO dto);

    Boolean updateFromDTO(Long id, WwhhIivv00000100DTO dto);

    Boolean forceSheetMetalBinding(WwhhIivv00000100DTO dto);
    Boolean forceSheetMetalBindingUpdate(WwhhIivv00000100DTO dto);
}
