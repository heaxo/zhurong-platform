package com.ao.platform.core.lantek.service;

import com.ao.platform.core.lantek.dto.DisPpttTtrr00000700DTO;
import com.ao.platform.core.lantek.entity.DisPpttTtrr00000700;
import com.ao.platform.core.lantek.vo.DisPpttTtrr00000700VO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 服务接口
 */
public interface IDisPpttTtrr00000700Service extends IService<DisPpttTtrr00000700> {
    DisPpttTtrr00000700VO getVOById(Long id);

    Long saveFromDTO(DisPpttTtrr00000700DTO dto);

    Boolean updateFromDTO(Long id, DisPpttTtrr00000700DTO dto);
}
