package com.ao.platform.core.lantek.service;

import com.ao.platform.core.lantek.entity.AoPlatformOutboxEvent;
import com.ao.platform.core.lantek.vo.AoPlatformOutboxEventVO;
import com.ao.platform.core.lantek.dto.AoPlatformOutboxEventDTO;
import com.baomidou.mybatisplus.extension.service.IService;
import java.io.Serializable;

/**
*  服务接口
*/
public interface IAoPlatformOutboxEventService extends IService<AoPlatformOutboxEvent> {
AoPlatformOutboxEventVO getVOById(Long id);

Long saveFromDTO(AoPlatformOutboxEventDTO dto);

Boolean updateFromDTO(Long id, AoPlatformOutboxEventDTO dto);
}
