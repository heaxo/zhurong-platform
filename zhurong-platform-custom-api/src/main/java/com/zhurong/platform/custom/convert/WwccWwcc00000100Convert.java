package com.zhurong.platform.custom.convert;

import com.zhurong.platform.core.lantek.dto.WwccWwcc00000100DTO;
import com.zhurong.platform.core.lantek.dto.WwccWwcc00000100PageQuery;
import com.zhurong.platform.core.lantek.vo.WwccWwcc00000100VO;
import com.zhurong.platform.custom.entity.WwccWwcc00000100;
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
public interface WwccWwcc00000100Convert {

    /**
     * Entity → VO
     */
    WwccWwcc00000100VO toVO(WwccWwcc00000100 entity);

    /**
     * Entity 列表 → VO 列表
     */
    List
            <WwccWwcc00000100VO> toVOList(List<WwccWwcc00000100> list);

    /**
     * DTO → Entity
     */
    WwccWwcc00000100 toEntity(WwccWwcc00000100DTO dto);

    WwccWwcc00000100 toEntity(WwccWwcc00000100PageQuery dto);

    /**
     * 更新时 DTO → Entity（忽略 null）
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDTO(WwccWwcc00000100DTO dto, @MappingTarget WwccWwcc00000100 entity);
}
