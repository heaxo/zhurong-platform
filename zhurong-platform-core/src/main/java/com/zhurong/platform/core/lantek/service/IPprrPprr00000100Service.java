package com.zhurong.platform.core.lantek.service;

import com.zhurong.platform.core.lantek.dto.PprrPprr00000100DTO;
import com.zhurong.platform.core.lantek.dto.RelationLoadPlan;
import com.zhurong.platform.core.lantek.entity.PprrPprr00000100;
import com.zhurong.platform.core.lantek.vo.PprrPprr00000100VO;

import java.util.List;

/**
 * 服务接口
 */
public interface IPprrPprr00000100Service extends BaseIService<PprrPprr00000100> {
    PprrPprr00000100VO getVOById(Long id);

    Long saveFromDTO(PprrPprr00000100DTO dto);

    Boolean updateFromDTO(Long id, PprrPprr00000100DTO dto);

    /**
     * 按零件引用批量加载单项资料，并根据装配方案补扩展字段
     */
    List<PprrPprr00000100VO> fetchPartProfiles(List<String> productRefs, RelationLoadPlan plan);
}
