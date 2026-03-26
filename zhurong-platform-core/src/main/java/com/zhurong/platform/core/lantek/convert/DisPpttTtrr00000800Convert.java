package com.zhurong.platform.core.lantek.convert;

import com.zhurong.platform.core.lantek.dto.DisPpttTtrr00000800DTO;
import com.zhurong.platform.core.lantek.dto.DisPpttTtrr00000800PageQuery;
import com.zhurong.platform.core.lantek.entity.DisPpttTtrr00000800;
import com.zhurong.platform.core.lantek.vo.DisPpttTtrr00000800VO;
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
public interface DisPpttTtrr00000800Convert {

    /**
     * Entity → VO
     */
    DisPpttTtrr00000800VO toVO(DisPpttTtrr00000800 entity);

    /**
     * Entity 列表 → VO 列表
     */
    List
            <DisPpttTtrr00000800VO> toVOList(List<DisPpttTtrr00000800> list);

    /**
     * DTO → Entity
     */
    DisPpttTtrr00000800 toEntity(DisPpttTtrr00000800DTO dto);

    DisPpttTtrr00000800 toEntity(DisPpttTtrr00000800PageQuery dto);

    /**
     * 更新时 DTO → Entity（忽略 null）
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDTO(DisPpttTtrr00000800DTO dto, @MappingTarget DisPpttTtrr00000800 entity);
}
