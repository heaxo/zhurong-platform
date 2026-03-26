package com.zhurong.platform.core.lantek.convert;

import com.zhurong.platform.core.lantek.dto.ZhurongPlatformJobCursorDTO;
import com.zhurong.platform.core.lantek.dto.ZhurongPlatformJobCursorPageQuery;
import com.zhurong.platform.core.lantek.entity.ZhurongPlatformJobCursor;
import com.zhurong.platform.core.lantek.vo.ZhurongPlatformJobCursorVO;
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
public interface ZhurongPlatformJobCursorConvert {

    /**
     * Entity → VO
     */
    ZhurongPlatformJobCursorVO toVO(ZhurongPlatformJobCursor entity);

    /**
     * Entity 列表 → VO 列表
     */
    List
            <ZhurongPlatformJobCursorVO> toVOList(List<ZhurongPlatformJobCursor> list);

    /**
     * DTO → Entity
     */
    ZhurongPlatformJobCursor toEntity(ZhurongPlatformJobCursorDTO dto);

    ZhurongPlatformJobCursor toEntity(ZhurongPlatformJobCursorPageQuery dto);

    /**
     * 更新时 DTO → Entity（忽略 null）
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDTO(ZhurongPlatformJobCursorDTO dto, @MappingTarget ZhurongPlatformJobCursor entity);
}
