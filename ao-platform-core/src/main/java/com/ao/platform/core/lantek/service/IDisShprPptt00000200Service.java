package com.ao.platform.core.lantek.service;

import com.ao.platform.core.lantek.dto.DisShprPptt00000200DTO;
import com.ao.platform.core.lantek.entity.DisShprPptt00000200;
import com.ao.platform.core.lantek.vo.DisShprPptt00000200VO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 服务接口
 */
public interface IDisShprPptt00000200Service extends IService<DisShprPptt00000200> {
    DisShprPptt00000200VO getVOById(Long id);

    Long saveFromDTO(DisShprPptt00000200DTO dto);

    Boolean updateFromDTO(Long id, DisShprPptt00000200DTO dto);
}
