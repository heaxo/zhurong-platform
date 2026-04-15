package com.zhurong.platform.core.lantek.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhurong.platform.core.lantek.dto.DisNestNest00000500DTO;
import com.zhurong.platform.core.lantek.dto.RelationLoadPlan;
import com.zhurong.platform.core.lantek.entity.DisNestNest00000500;
import com.zhurong.platform.core.lantek.vo.DisNestNest00000500VO;

import java.util.List;

/**
 * 服务接口
 */
public interface IDisNestNest00000500Service extends IService<DisNestNest00000500> {
    DisNestNest00000500VO getVOById(Long id);

    Long saveFromDTO(DisNestNest00000500DTO dto);

    Boolean updateFromDTO(Long id, DisNestNest00000500DTO dto);

    /**
     * 根据套料号批量取零件行，并按需补充关联数据
     */
    List<DisNestNest00000500VO> findPartRowsByNestRefs(List<String> nestRefs, RelationLoadPlan loadPlan);

}
