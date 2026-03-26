package com.zhurong.platform.core.lantek.convert;

import com.zhurong.platform.core.lantek.dto.MmnnMmoo00000300DTO;
import com.zhurong.platform.core.lantek.dto.MmnnMmoo00000300PageQuery;
import com.zhurong.platform.core.lantek.entity.MmnnMmoo00000300;
import com.zhurong.platform.core.lantek.vo.MmnnMmoo00000300VO;
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
public interface MmnnMmoo00000300Convert {

    /**
     * Entity → VO
     */
    MmnnMmoo00000300VO toVO(MmnnMmoo00000300 entity);

    /**
     * Entity 列表 → VO 列表
     */
    List
            <MmnnMmoo00000300VO> toVOList(List<MmnnMmoo00000300> list);

    /**
     * DTO → Entity
     */
    MmnnMmoo00000300 toEntity(MmnnMmoo00000300DTO dto);

    MmnnMmoo00000300 toEntity(MmnnMmoo00000300PageQuery dto);

    /**
     * 更新时 DTO → Entity（忽略 null）
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDTO(MmnnMmoo00000300DTO dto, @MappingTarget MmnnMmoo00000300 entity);
}
