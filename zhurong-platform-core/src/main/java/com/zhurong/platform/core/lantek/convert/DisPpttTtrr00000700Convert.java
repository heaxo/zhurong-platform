package com.zhurong.platform.core.lantek.convert;

import com.zhurong.platform.core.lantek.dto.DisPpttTtrr00000700DTO;
import com.zhurong.platform.core.lantek.dto.DisPpttTtrr00000700PageQuery;
import com.zhurong.platform.core.lantek.entity.DisPpttTtrr00000700;
import com.zhurong.platform.core.lantek.vo.DisPpttTtrr00000700VO;
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
public interface DisPpttTtrr00000700Convert {

    /**
     * Entity → VO
     */
    DisPpttTtrr00000700VO toVO(DisPpttTtrr00000700 entity);

    /**
     * Entity 列表 → VO 列表
     */
    List
            <DisPpttTtrr00000700VO> toVOList(List<DisPpttTtrr00000700> list);

    /**
     * DTO → Entity
     */
    DisPpttTtrr00000700 toEntity(DisPpttTtrr00000700DTO dto);

    DisPpttTtrr00000700 toEntity(DisPpttTtrr00000700PageQuery dto);

    /**
     * 更新时 DTO → Entity（忽略 null）
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDTO(DisPpttTtrr00000700DTO dto, @MappingTarget DisPpttTtrr00000700 entity);
}
