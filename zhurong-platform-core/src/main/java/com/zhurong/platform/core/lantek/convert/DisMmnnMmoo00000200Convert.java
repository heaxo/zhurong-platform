package com.zhurong.platform.core.lantek.convert;

import com.zhurong.platform.core.lantek.dto.DisMmnnMmoo00000200DTO;
import com.zhurong.platform.core.lantek.dto.DisMmnnMmoo00000200PageQuery;
import com.zhurong.platform.core.lantek.entity.DisMmnnMmoo00000200;
import com.zhurong.platform.core.lantek.vo.DisMmnnMmoo00000200VO;
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
public interface DisMmnnMmoo00000200Convert {

    /**
     * Entity → VO
     */
    DisMmnnMmoo00000200VO toVO(DisMmnnMmoo00000200 entity);

    /**
     * Entity 列表 → VO 列表
     */
    List
            <DisMmnnMmoo00000200VO> toVOList(List<DisMmnnMmoo00000200> list);

    /**
     * DTO → Entity
     */
    DisMmnnMmoo00000200 toEntity(DisMmnnMmoo00000200DTO dto);

    DisMmnnMmoo00000200 toEntity(DisMmnnMmoo00000200PageQuery dto);

    /**
     * 更新时 DTO → Entity（忽略 null）
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDTO(DisMmnnMmoo00000200DTO dto, @MappingTarget DisMmnnMmoo00000200 entity);
}
