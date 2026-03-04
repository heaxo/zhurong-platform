package com.ao.platform.core.lantek.convert;

import com.ao.platform.core.lantek.dto.DisNestNest00000400DTO;
import com.ao.platform.core.lantek.dto.DisNestNest00000400PageQuery;
import com.ao.platform.core.lantek.entity.DisNestNest00000400;
import com.ao.platform.core.lantek.vo.DisNestNest00000400VO;
import org.mapstruct.*;

import java.util.List;

/**
 * 对象转换器
 * <p>
 * 说明：
 * 1. Entity ↔ DTO
 * 2. Entity ↔ VO
 * 3. 使用 MapStruct 自动生成实现
 */
@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface DisNestNest00000400Convert {

    /**
     * Entity → VO
     */
    DisNestNest00000400VO toVO(DisNestNest00000400 entity);

    /**
     * Entity 列表 → VO 列表
     */
    List
            <DisNestNest00000400VO> toVOList(List<DisNestNest00000400> list);

    /**
     * DTO → Entity
     */
    DisNestNest00000400 toEntity(DisNestNest00000400DTO dto);

    DisNestNest00000400 toEntity(DisNestNest00000400PageQuery dto);

    /**
     * 更新时 DTO → Entity（忽略 null）
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDTO(DisNestNest00000400DTO dto, @MappingTarget DisNestNest00000400 entity);
}
