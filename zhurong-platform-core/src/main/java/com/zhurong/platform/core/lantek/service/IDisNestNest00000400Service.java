package com.zhurong.platform.core.lantek.service;

import com.zhurong.platform.core.lantek.dto.DisNestNest00000400DTO;
import com.zhurong.platform.core.lantek.entity.DisNestNest00000400;
import com.zhurong.platform.core.lantek.vo.DisNestNest00000400VO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 服务接口
 */
public interface IDisNestNest00000400Service extends IService<DisNestNest00000400> {
    DisNestNest00000400VO getVOById(Long id);

    Long saveFromDTO(DisNestNest00000400DTO dto);

    Boolean updateFromDTO(Long id, DisNestNest00000400DTO dto);
}
