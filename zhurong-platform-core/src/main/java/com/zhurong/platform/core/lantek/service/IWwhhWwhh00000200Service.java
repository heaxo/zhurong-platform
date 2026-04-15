package com.zhurong.platform.core.lantek.service;

import com.zhurong.platform.core.lantek.dto.WwhhWwhh00000200DTO;
import com.zhurong.platform.core.lantek.entity.WwhhWwhh00000200;
import com.zhurong.platform.core.lantek.vo.WwhhWwhh00000200VO;

/**
 * 服务接口
 */
public interface IWwhhWwhh00000200Service extends BaseIService<WwhhWwhh00000200> {
    WwhhWwhh00000200VO getVOById(Long id);

    Long saveFromDTO(WwhhWwhh00000200DTO dto);

    Boolean updateFromDTO(Long id, WwhhWwhh00000200DTO dto);
}
