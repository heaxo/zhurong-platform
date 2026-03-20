package com.ao.platform.core.lantek.service;

import com.ao.platform.core.lantek.dto.AoPlatformJobCursorDTO;
import com.ao.platform.core.lantek.entity.AoPlatformJobCursor;
import com.ao.platform.core.lantek.vo.AoPlatformJobCursorVO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 服务接口
 */
public interface IAoPlatformJobCursorService extends IService<AoPlatformJobCursor> {
    AoPlatformJobCursorVO getVOById(Long id);

    Long saveFromDTO(AoPlatformJobCursorDTO dto);

    Boolean updateFromDTO(Long id, AoPlatformJobCursorDTO dto);
}
