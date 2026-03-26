package com.zhurong.platform.core.lantek.service;

import com.zhurong.platform.core.lantek.dto.MmnnMmoo00000100DTO;
import com.zhurong.platform.core.lantek.entity.MmnnMmoo00000100;
import com.zhurong.platform.core.lantek.vo.MmnnMmoo00000100VO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 服务接口
 */
public interface IMmnnMmoo00000100Service extends IService<MmnnMmoo00000100> {
    MmnnMmoo00000100VO getVOById(Long id);

    Long saveFromDTO(MmnnMmoo00000100DTO dto);

    Boolean updateFromDTO(Long id, MmnnMmoo00000100DTO dto);
}
