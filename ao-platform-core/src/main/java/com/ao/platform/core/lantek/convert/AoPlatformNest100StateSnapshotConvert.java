package com.ao.platform.core.lantek.convert;

import com.ao.platform.core.lantek.dto.AoPlatformNest100StateSnapshotDTO;
import com.ao.platform.core.lantek.dto.AoPlatformNest100StateSnapshotPageQuery;
import com.ao.platform.core.lantek.entity.AoPlatformNest100StateSnapshot;
import com.ao.platform.core.lantek.vo.AoPlatformNest100StateSnapshotVO;
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
public interface AoPlatformNest100StateSnapshotConvert {

    /**
     * Entity → VO
     */
    AoPlatformNest100StateSnapshotVO toVO(AoPlatformNest100StateSnapshot entity);

    /**
     * Entity 列表 → VO 列表
     */
    List
            <AoPlatformNest100StateSnapshotVO> toVOList(List<AoPlatformNest100StateSnapshot> list);

    /**
     * DTO → Entity
     */
    AoPlatformNest100StateSnapshot toEntity(AoPlatformNest100StateSnapshotDTO dto);

    AoPlatformNest100StateSnapshot toEntity(AoPlatformNest100StateSnapshotPageQuery dto);

    /**
     * 更新时 DTO → Entity（忽略 null）
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDTO(AoPlatformNest100StateSnapshotDTO dto, @MappingTarget AoPlatformNest100StateSnapshot entity);
}
