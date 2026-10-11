package com.zhurong.platform.custom.convert;

import com.zhurong.platform.core.lantek.dto.DisMmttMmtt00000200DTO;
import com.zhurong.platform.core.lantek.dto.DisMmttMmtt00000200PageQuery;
import com.zhurong.platform.core.lantek.vo.DisMmttMmtt00000200VO;
import com.zhurong.platform.custom.entity.DisMmttMmtt00000200;
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
public interface DisMmttMmtt00000200Convert {

    /**
     * Entity → VO
     */
    DisMmttMmtt00000200VO toVO(DisMmttMmtt00000200 entity);

    /**
     * Entity 列表 → VO 列表
     */
    List
            <DisMmttMmtt00000200VO> toVOList(List<DisMmttMmtt00000200> list);

    /**
     * DTO → Entity
     */
    DisMmttMmtt00000200 toEntity(DisMmttMmtt00000200DTO dto);

    DisMmttMmtt00000200 toEntity(DisMmttMmtt00000200PageQuery dto);

    /**
     * 更新时 DTO → Entity（忽略 null）
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDTO(DisMmttMmtt00000200DTO dto, @MappingTarget DisMmttMmtt00000200 entity);
}
