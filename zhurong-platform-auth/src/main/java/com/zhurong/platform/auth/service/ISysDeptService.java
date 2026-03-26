package com.zhurong.platform.auth.service;

import com.zhurong.platform.auth.dto.SysDeptDTO;
import com.zhurong.platform.auth.entity.SysDept;
import com.zhurong.platform.auth.vo.SysDeptVO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 服务接口
 */
public interface ISysDeptService extends IService<SysDept> {
    SysDeptVO getVOById(Long id);

    Long saveFromDTO(SysDeptDTO dto);

    Boolean updateFromDTO(Long id, SysDeptDTO dto);
}
