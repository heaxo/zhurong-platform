package com.zhurong.platform.core.lantek.convert;

import com.zhurong.platform.core.lantek.dto.ZhurongPlatformNest100StateSnapshotDTO;
import com.zhurong.platform.core.lantek.dto.ZhurongPlatformNest100StateSnapshotPageQuery;
import com.zhurong.platform.core.lantek.entity.ZhurongPlatformNest100StateSnapshot;
import com.zhurong.platform.core.lantek.vo.ZhurongPlatformNest100StateSnapshotVO;
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
public interface ZhurongPlatformNest100StateSnapshotConvert {

    /**
     * Entity → VO
     */
    ZhurongPlatformNest100StateSnapshotVO toVO(ZhurongPlatformNest100StateSnapshot entity);

    /**
     * Entity 列表 → VO 列表
     */
    List
            <ZhurongPlatformNest100StateSnapshotVO> toVOList(List<ZhurongPlatformNest100StateSnapshot> list);

    /**
     * DTO → Entity
     */
    ZhurongPlatformNest100StateSnapshot toEntity(ZhurongPlatformNest100StateSnapshotDTO dto);

    ZhurongPlatformNest100StateSnapshot toEntity(ZhurongPlatformNest100StateSnapshotPageQuery dto);

    /**
     * 更新时 DTO → Entity（忽略 null）
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDTO(ZhurongPlatformNest100StateSnapshotDTO dto, @MappingTarget ZhurongPlatformNest100StateSnapshot entity);
}
