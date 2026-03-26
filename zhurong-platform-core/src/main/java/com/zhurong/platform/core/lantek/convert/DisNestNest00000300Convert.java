package com.zhurong.platform.core.lantek.convert;

import com.zhurong.platform.core.lantek.dto.DisNestNest00000300DTO;
import com.zhurong.platform.core.lantek.dto.DisNestNest00000300PageQuery;
import com.zhurong.platform.core.lantek.entity.DisNestNest00000300;
import com.zhurong.platform.core.lantek.vo.DisNestNest00000300VO;
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
public interface DisNestNest00000300Convert {

    /**
     * Entity → VO
     */
    DisNestNest00000300VO toVO(DisNestNest00000300 entity);

    /**
     * Entity 列表 → VO 列表
     */
    List
            <DisNestNest00000300VO> toVOList(List<DisNestNest00000300> list);

    /**
     * DTO → Entity
     */
    DisNestNest00000300 toEntity(DisNestNest00000300DTO dto);

    DisNestNest00000300 toEntity(DisNestNest00000300PageQuery dto);

    /**
     * 更新时 DTO → Entity（忽略 null）
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDTO(DisNestNest00000300DTO dto, @MappingTarget DisNestNest00000300 entity);
}
