package com.zhurong.platform.core.lantek.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhurong.platform.core.lantek.dto.DisMmnnMmoo00000200DTO;
import com.zhurong.platform.core.lantek.dto.RelationLoadPlan;
import com.zhurong.platform.core.lantek.entity.DisMmnnMmoo00000200;
import com.zhurong.platform.core.lantek.vo.DisMmnnMmoo00000200VO;

import java.util.List;
import java.util.Map;

/**
 * 服务接口
 */
public interface IDisMmnnMmoo00000200Service extends IService<DisMmnnMmoo00000200> {
    DisMmnnMmoo00000200VO getVOById(Long id);

    Long saveFromDTO(DisMmnnMmoo00000200DTO dto);

    Boolean updateFromDTO(Long id, DisMmnnMmoo00000200DTO dto);

    List<DisMmnnMmoo00000200VO> findJobsByRefs(List<String> jobRefs, RelationLoadPlan loadPlan);

    Map<String, String> buildJobPathMap(List<String> jobRefs, List<DisMmnnMmoo00000200> seedJobs);

    String resolveJobPath(String jobRef);
}
