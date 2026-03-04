package com.ao.platform.core.lantek.convert;

import com.ao.platform.core.lantek.dto.DisPpttWwcc00000800DTO;
import com.ao.platform.core.lantek.dto.DisPpttWwcc00000800PageQuery;
import com.ao.platform.core.lantek.entity.DisPpttWwcc00000800;
import com.ao.platform.core.lantek.vo.DisPpttWwcc00000800VO;
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
public interface DisPpttWwcc00000800Convert {

    /**
     * Entity → VO
     */
    DisPpttWwcc00000800VO toVO(DisPpttWwcc00000800 entity);

    /**
     * Entity 列表 → VO 列表
     */
    List
            <DisPpttWwcc00000800VO> toVOList(List<DisPpttWwcc00000800> list);

    /**
     * DTO → Entity
     */
    DisPpttWwcc00000800 toEntity(DisPpttWwcc00000800DTO dto);

    DisPpttWwcc00000800 toEntity(DisPpttWwcc00000800PageQuery dto);

    /**
     * 更新时 DTO → Entity（忽略 null）
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDTO(DisPpttWwcc00000800DTO dto, @MappingTarget DisPpttWwcc00000800 entity);
}
