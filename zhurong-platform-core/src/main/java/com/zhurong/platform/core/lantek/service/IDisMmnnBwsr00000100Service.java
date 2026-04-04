package com.zhurong.platform.core.lantek.service;

import com.zhurong.platform.core.lantek.dto.DisMmnnBwsr00000100DTO;
import com.zhurong.platform.core.lantek.entity.DisMmnnBwsr00000100;
import com.zhurong.platform.core.lantek.vo.DisMmnnBwsr00000100VO;
import com.zhurong.platform.core.lantek.vo.JobBrowserTreeVO;

import java.util.List;

/**
 * 服务接口
 */
public interface IDisMmnnBwsr00000100Service extends BaseIService<DisMmnnBwsr00000100> {
    DisMmnnBwsr00000100VO getVOById(Long id);

    Long saveFromDTO(DisMmnnBwsr00000100DTO dto);

    Boolean updateFromDTO(Long id, DisMmnnBwsr00000100DTO dto);

    List<JobBrowserTreeVO> getJobBrowserTree();

}
