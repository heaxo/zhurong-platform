package com.zhurong.platform.core.lantek.service;

import com.zhurong.platform.core.lantek.dto.ZhurongPlatformJobCursorDTO;
import com.zhurong.platform.core.lantek.entity.ZhurongPlatformJobCursor;
import com.zhurong.platform.core.lantek.vo.ZhurongPlatformJobCursorVO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 服务接口
 */
public interface IZhurongPlatformJobCursorService extends IService<ZhurongPlatformJobCursor> {
    ZhurongPlatformJobCursorVO getVOById(Long id);

    Long saveFromDTO(ZhurongPlatformJobCursorDTO dto);

    Boolean updateFromDTO(Long id, ZhurongPlatformJobCursorDTO dto);
}
