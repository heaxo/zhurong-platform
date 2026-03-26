package com.zhurong.platform.core.lantek.convert;

import com.zhurong.platform.core.lantek.dto.SystFfll00000200DTO;
import com.zhurong.platform.core.lantek.dto.SystFfll00000200PageQuery;
import com.zhurong.platform.core.lantek.entity.SystFfll00000200;
import com.zhurong.platform.core.lantek.vo.SystFfll00000200VO;
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
public interface SystFfll00000200Convert {

    /**
     * Entity → VO
     */
    SystFfll00000200VO toVO(SystFfll00000200 entity);

    /**
     * Entity 列表 → VO 列表
     */
    List
            <SystFfll00000200VO> toVOList(List<SystFfll00000200> list);

    /**
     * DTO → Entity
     */
    SystFfll00000200 toEntity(SystFfll00000200DTO dto);

    SystFfll00000200 toEntity(SystFfll00000200PageQuery dto);

    /**
     * 更新时 DTO → Entity（忽略 null）
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDTO(SystFfll00000200DTO dto, @MappingTarget SystFfll00000200 entity);
}
