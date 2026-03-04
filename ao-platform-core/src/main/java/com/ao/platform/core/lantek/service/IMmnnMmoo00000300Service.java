package com.ao.platform.core.lantek.service;

import com.ao.platform.core.lantek.dto.MmnnMmoo00000300DTO;
import com.ao.platform.core.lantek.entity.MmnnMmoo00000300;
import com.ao.platform.core.lantek.vo.MmnnMmoo00000300VO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 服务接口
 */
public interface IMmnnMmoo00000300Service extends IService<MmnnMmoo00000300> {
    MmnnMmoo00000300VO getVOById(Long id);

    Long saveFromDTO(MmnnMmoo00000300DTO dto);

    Boolean updateFromDTO(Long id, MmnnMmoo00000300DTO dto);
}
