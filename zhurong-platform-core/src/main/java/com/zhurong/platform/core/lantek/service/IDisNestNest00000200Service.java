package com.zhurong.platform.core.lantek.service;

import com.zhurong.platform.core.lantek.dto.DisNestNest00000200DTO;
import com.zhurong.platform.core.lantek.entity.DisNestNest00000200;
import com.zhurong.platform.core.lantek.vo.DisNestNest00000200VO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 服务接口
 */
public interface IDisNestNest00000200Service extends IService<DisNestNest00000200> {
    DisNestNest00000200VO getVOById(Long id);

    Long saveFromDTO(DisNestNest00000200DTO dto);

    Boolean updateFromDTO(Long id, DisNestNest00000200DTO dto);
}
