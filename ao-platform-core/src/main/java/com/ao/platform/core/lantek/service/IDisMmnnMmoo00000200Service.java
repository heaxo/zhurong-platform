package com.ao.platform.core.lantek.service;

import com.ao.platform.core.lantek.dto.DisMmnnMmoo00000200DTO;
import com.ao.platform.core.lantek.entity.DisMmnnMmoo00000200;
import com.ao.platform.core.lantek.vo.DisMmnnMmoo00000200VO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 服务接口
 */
public interface IDisMmnnMmoo00000200Service extends IService<DisMmnnMmoo00000200> {
    DisMmnnMmoo00000200VO getVOById(Long id);

    Long saveFromDTO(DisMmnnMmoo00000200DTO dto);

    Boolean updateFromDTO(Long id, DisMmnnMmoo00000200DTO dto);
}
