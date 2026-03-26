package com.zhurong.platform.core.lantek.service;

import com.zhurong.platform.core.lantek.dto.DisPpttTtrr00000800DTO;
import com.zhurong.platform.core.lantek.entity.DisPpttTtrr00000800;
import com.zhurong.platform.core.lantek.vo.DisPpttTtrr00000800VO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 服务接口
 */
public interface IDisPpttTtrr00000800Service extends IService<DisPpttTtrr00000800> {
    DisPpttTtrr00000800VO getVOById(Long id);

    Long saveFromDTO(DisPpttTtrr00000800DTO dto);

    Boolean updateFromDTO(Long id, DisPpttTtrr00000800DTO dto);
}
