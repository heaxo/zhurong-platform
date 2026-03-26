package com.zhurong.platform.core.lantek.service;

import com.zhurong.platform.core.lantek.convert.MmnnMmoo00000300Convert;
import com.zhurong.platform.core.lantek.dto.MmnnMmoo00000300DTO;
import com.zhurong.platform.core.lantek.entity.MmnnMmoo00000300;
import com.zhurong.platform.core.lantek.vo.MmnnMmoo00000300VO;

/**
 * 服务接口
 */
public interface IMmnnMmoo00000300Service extends BaseIService<MmnnMmoo00000300> {

    MmnnMmoo00000300Convert getConvert();

    MmnnMmoo00000300VO getVOById(Long id);

    Long saveFromDTO(MmnnMmoo00000300DTO dto);

    Boolean updateFromDTO(Long id, MmnnMmoo00000300DTO dto);
}
