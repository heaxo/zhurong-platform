package com.zhurong.platform.core.lantek.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhurong.platform.core.lantek.dto.DisNestNest00000300DTO;
import com.zhurong.platform.core.lantek.dto.RelationLoadPlan;
import com.zhurong.platform.core.lantek.entity.DisNestNest00000300;
import com.zhurong.platform.core.lantek.vo.DisNestNest00000300VO;

import java.util.List;

/**
 * 服务接口
 */
public interface IDisNestNest00000300Service extends IService<DisNestNest00000300> {
    DisNestNest00000300VO getVOById(Long id);

    Long saveFromDTO(DisNestNest00000300DTO dto);

    Boolean updateFromDTO(Long id, DisNestNest00000300DTO dto);

    /**
     * 根据套料号批量取余料行，并按需补充关联数据
     */
    List<DisNestNest00000300VO> findRemnantsByNestRefs(List<String> nestRefs, RelationLoadPlan loadPlan);
}
