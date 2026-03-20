package com.ao.platform.core.lantek.convert;

import com.ao.platform.core.lantek.dto.AoPlatformJobCursorDTO;
import com.ao.platform.core.lantek.dto.AoPlatformJobCursorPageQuery;
import com.ao.platform.core.lantek.entity.AoPlatformJobCursor;
import com.ao.platform.core.lantek.vo.AoPlatformJobCursorVO;
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
public interface AoPlatformJobCursorConvert {

    /**
     * Entity → VO
     */
    AoPlatformJobCursorVO toVO(AoPlatformJobCursor entity);

    /**
     * Entity 列表 → VO 列表
     */
    List
            <AoPlatformJobCursorVO> toVOList(List<AoPlatformJobCursor> list);

    /**
     * DTO → Entity
     */
    AoPlatformJobCursor toEntity(AoPlatformJobCursorDTO dto);

    AoPlatformJobCursor toEntity(AoPlatformJobCursorPageQuery dto);

    /**
     * 更新时 DTO → Entity（忽略 null）
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDTO(AoPlatformJobCursorDTO dto, @MappingTarget AoPlatformJobCursor entity);
}
