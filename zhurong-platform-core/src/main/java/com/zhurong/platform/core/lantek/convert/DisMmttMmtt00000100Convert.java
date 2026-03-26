package com.zhurong.platform.core.lantek.convert;

import com.zhurong.platform.core.lantek.dto.DisMmttMmtt00000100DTO;
import com.zhurong.platform.core.lantek.dto.DisMmttMmtt00000100PageQuery;
import com.zhurong.platform.core.lantek.entity.DisMmttMmtt00000100;
import com.zhurong.platform.core.lantek.vo.DisMmttMmtt00000100VO;
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
public interface DisMmttMmtt00000100Convert {

    /**
     * Entity → VO
     */
    DisMmttMmtt00000100VO toVO(DisMmttMmtt00000100 entity);

    /**
     * Entity 列表 → VO 列表
     */
    List
            <DisMmttMmtt00000100VO> toVOList(List<DisMmttMmtt00000100> list);

    /**
     * DTO → Entity
     */
    DisMmttMmtt00000100 toEntity(DisMmttMmtt00000100DTO dto);

    DisMmttMmtt00000100 toEntity(DisMmttMmtt00000100PageQuery dto);

    /**
     * 更新时 DTO → Entity（忽略 null）
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDTO(DisMmttMmtt00000100DTO dto, @MappingTarget DisMmttMmtt00000100 entity);
}
