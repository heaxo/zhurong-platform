package com.zhurong.platform.core.lantek.service;

import com.zhurong.platform.core.lantek.dto.ZhurongPlatformOutboxEventDTO;
import com.zhurong.platform.core.lantek.entity.ZhurongPlatformOutboxEvent;
import com.zhurong.platform.core.lantek.vo.ZhurongPlatformOutboxEventVO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 服务接口
 */
public interface IZhurongPlatformOutboxEventService extends IService<ZhurongPlatformOutboxEvent> {
    ZhurongPlatformOutboxEventVO getVOById(Long id);

    Long saveFromDTO(ZhurongPlatformOutboxEventDTO dto);

    Boolean updateFromDTO(Long id, ZhurongPlatformOutboxEventDTO dto);
}
