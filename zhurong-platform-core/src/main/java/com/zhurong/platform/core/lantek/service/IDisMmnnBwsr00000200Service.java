package com.zhurong.platform.core.lantek.service;

import com.zhurong.platform.core.lantek.dto.DisMmnnBwsr00000200DTO;
import com.zhurong.platform.core.lantek.entity.DisMmnnBwsr00000200;
import com.zhurong.platform.core.lantek.vo.DisMmnnBwsr00000200VO;

/**
*  服务接口
*/
public interface IDisMmnnBwsr00000200Service extends BaseIService<DisMmnnBwsr00000200> {
DisMmnnBwsr00000200VO getVOById(Long id);

Long saveFromDTO(DisMmnnBwsr00000200DTO dto);

Boolean updateFromDTO(Long id, DisMmnnBwsr00000200DTO dto);
}
