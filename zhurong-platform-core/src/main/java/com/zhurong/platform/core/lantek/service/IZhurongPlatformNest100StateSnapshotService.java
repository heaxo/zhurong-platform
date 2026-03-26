package com.zhurong.platform.core.lantek.service;

import com.zhurong.platform.core.lantek.dto.ZhurongPlatformNest100StateSnapshotDTO;
import com.zhurong.platform.core.lantek.entity.ZhurongPlatformNest100StateSnapshot;
import com.zhurong.platform.core.lantek.vo.ZhurongPlatformNest100StateSnapshotVO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 服务接口
 */
public interface IZhurongPlatformNest100StateSnapshotService extends IService<ZhurongPlatformNest100StateSnapshot> {
    ZhurongPlatformNest100StateSnapshotVO getVOById(Long id);

    Long saveFromDTO(ZhurongPlatformNest100StateSnapshotDTO dto);

    Boolean updateFromDTO(Long id, ZhurongPlatformNest100StateSnapshotDTO dto);
}
