package com.ao.platform.core.lantek.convert;

import com.ao.platform.core.lantek.dto.AoPlatformOutboxEventDTO;
import com.ao.platform.core.lantek.dto.AoPlatformOutboxEventPageQuery;
import com.ao.platform.core.lantek.entity.AoPlatformOutboxEvent;
import com.ao.platform.core.lantek.vo.AoPlatformOutboxEventVO;
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
public interface AoPlatformOutboxEventConvert {

    /**
     * Entity → VO
     */
    AoPlatformOutboxEventVO toVO(AoPlatformOutboxEvent entity);

    /**
     * Entity 列表 → VO 列表
     */
    List
            <AoPlatformOutboxEventVO> toVOList(List<AoPlatformOutboxEvent> list);

    /**
     * DTO → Entity
     */
    AoPlatformOutboxEvent toEntity(AoPlatformOutboxEventDTO dto);

    AoPlatformOutboxEvent toEntity(AoPlatformOutboxEventPageQuery dto);

    /**
     * 更新时 DTO → Entity（忽略 null）
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDTO(AoPlatformOutboxEventDTO dto, @MappingTarget AoPlatformOutboxEvent entity);
}
