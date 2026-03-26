package com.zhurong.platform.core.lantek.service;

import com.zhurong.platform.core.lantek.dto.SystFfll00000200DTO;
import com.zhurong.platform.core.lantek.entity.SystFfll00000200;
import com.zhurong.platform.core.lantek.vo.SystFfll00000200VO;

/**
 * 服务接口
 */
public interface ISystFfll00000200Service extends BaseIService<SystFfll00000200> {
    SystFfll00000200VO getVOById(Long id);

    Long saveFromDTO(SystFfll00000200DTO dto);

    Boolean updateFromDTO(Long id, SystFfll00000200DTO dto);

    String getSystemVaultPath();
}
