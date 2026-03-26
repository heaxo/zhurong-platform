package com.zhurong.platform.core.lantek.service;

import com.zhurong.platform.core.lantek.dto.DisNestNest00000500DTO;
import com.zhurong.platform.core.lantek.entity.DisNestNest00000500;
import com.zhurong.platform.core.lantek.vo.DisNestNest00000500VO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 服务接口
 */
public interface IDisNestNest00000500Service extends IService<DisNestNest00000500> {
    DisNestNest00000500VO getVOById(Long id);

    Long saveFromDTO(DisNestNest00000500DTO dto);

    Boolean updateFromDTO(Long id, DisNestNest00000500DTO dto);
}
