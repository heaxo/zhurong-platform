package com.zhurong.platform.custom.convert;

import com.zhurong.platform.core.lantek.dto.DisNestNest00000500DTO;
import com.zhurong.platform.core.lantek.dto.DisNestNest00000500PageQuery;
import com.zhurong.platform.core.lantek.vo.DisNestNest00000500VO;
import com.zhurong.platform.custom.entity.DisNestNest00000500;
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
public interface DisNestNest00000500Convert {

    /**
     * Entity → VO
     */
    DisNestNest00000500VO toVO(DisNestNest00000500 entity);

    /**
     * Entity 列表 → VO 列表
     */
    List
            <DisNestNest00000500VO> toVOList(List<DisNestNest00000500> list);

    /**
     * DTO → Entity
     */
    DisNestNest00000500 toEntity(DisNestNest00000500DTO dto);

    DisNestNest00000500 toEntity(DisNestNest00000500PageQuery dto);

    /**
     * 更新时 DTO → Entity（忽略 null）
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDTO(DisNestNest00000500DTO dto, @MappingTarget DisNestNest00000500 entity);
}
