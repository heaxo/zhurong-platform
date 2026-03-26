package com.zhurong.platform.core.lantek.convert;

import com.zhurong.platform.core.lantek.dto.ZhurongPlatformOutboxEventDTO;
import com.zhurong.platform.core.lantek.dto.ZhurongPlatformOutboxEventPageQuery;
import com.zhurong.platform.core.lantek.entity.ZhurongPlatformOutboxEvent;
import com.zhurong.platform.core.lantek.vo.ZhurongPlatformOutboxEventVO;
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
public interface ZhurongPlatformOutboxEventConvert {

    /**
     * Entity → VO
     */
    ZhurongPlatformOutboxEventVO toVO(ZhurongPlatformOutboxEvent entity);

    /**
     * Entity 列表 → VO 列表
     */
    List
            <ZhurongPlatformOutboxEventVO> toVOList(List<ZhurongPlatformOutboxEvent> list);

    /**
     * DTO → Entity
     */
    ZhurongPlatformOutboxEvent toEntity(ZhurongPlatformOutboxEventDTO dto);

    ZhurongPlatformOutboxEvent toEntity(ZhurongPlatformOutboxEventPageQuery dto);

    /**
     * 更新时 DTO → Entity（忽略 null）
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDTO(ZhurongPlatformOutboxEventDTO dto, @MappingTarget ZhurongPlatformOutboxEvent entity);
}
