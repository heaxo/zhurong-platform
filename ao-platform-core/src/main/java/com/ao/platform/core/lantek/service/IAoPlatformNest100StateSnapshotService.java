package com.ao.platform.core.lantek.service;

import com.ao.platform.core.lantek.dto.AoPlatformNest100StateSnapshotDTO;
import com.ao.platform.core.lantek.entity.AoPlatformNest100StateSnapshot;
import com.ao.platform.core.lantek.vo.AoPlatformNest100StateSnapshotVO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
*  服务接口
*/
public interface IAoPlatformNest100StateSnapshotService extends IService<AoPlatformNest100StateSnapshot> {
AoPlatformNest100StateSnapshotVO getVOById(Long id);

Long saveFromDTO(AoPlatformNest100StateSnapshotDTO dto);

Boolean updateFromDTO(Long id, AoPlatformNest100StateSnapshotDTO dto);
}
