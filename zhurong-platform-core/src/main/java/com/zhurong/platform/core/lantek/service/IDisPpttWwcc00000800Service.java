package com.zhurong.platform.core.lantek.service;

import com.zhurong.platform.core.lantek.dto.DisPpttWwcc00000800DTO;
import com.zhurong.platform.core.lantek.entity.DisPpttWwcc00000800;
import com.zhurong.platform.core.lantek.vo.DisPpttWwcc00000800VO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 服务接口
 */
public interface IDisPpttWwcc00000800Service extends IService<DisPpttWwcc00000800> {
    DisPpttWwcc00000800VO getVOById(Long id);

    Long saveFromDTO(DisPpttWwcc00000800DTO dto);

    Boolean updateFromDTO(Long id, DisPpttWwcc00000800DTO dto);
}
