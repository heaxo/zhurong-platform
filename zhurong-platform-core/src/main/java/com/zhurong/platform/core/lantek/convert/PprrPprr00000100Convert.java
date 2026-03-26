package com.zhurong.platform.core.lantek.convert;

import com.zhurong.platform.core.lantek.dto.PprrPprr00000100DTO;
import com.zhurong.platform.core.lantek.dto.PprrPprr00000100PageQuery;
import com.zhurong.platform.core.lantek.entity.PprrPprr00000100;
import com.zhurong.platform.core.lantek.vo.PprrPprr00000100VO;
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
public interface PprrPprr00000100Convert {

    /**
     * Entity → VO
     */
    PprrPprr00000100VO toVO(PprrPprr00000100 entity);

    /**
     * Entity 列表 → VO 列表
     */
    List
            <PprrPprr00000100VO> toVOList(List<PprrPprr00000100> list);

    /**
     * DTO → Entity
     */
    PprrPprr00000100 toEntity(PprrPprr00000100DTO dto);

    PprrPprr00000100 toEntity(PprrPprr00000100PageQuery dto);

    /**
     * 更新时 DTO → Entity（忽略 null）
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDTO(PprrPprr00000100DTO dto, @MappingTarget PprrPprr00000100 entity);
}
