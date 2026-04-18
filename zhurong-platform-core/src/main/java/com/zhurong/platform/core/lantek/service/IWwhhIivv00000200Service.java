package com.zhurong.platform.core.lantek.service;

import com.zhurong.platform.core.lantek.dto.WwhhIivv00000200DTO;
import com.zhurong.platform.core.lantek.entity.WwhhIivv00000200;
import com.zhurong.platform.core.lantek.vo.WwhhIivv00000200VO;

/**
*  服务接口
*/
public interface IWwhhIivv00000200Service extends BaseIService<WwhhIivv00000200> {
WwhhIivv00000200VO getVOById(Long id);

Long saveFromDTO(WwhhIivv00000200DTO dto);

Boolean updateFromDTO(Long id, WwhhIivv00000200DTO dto);
}
