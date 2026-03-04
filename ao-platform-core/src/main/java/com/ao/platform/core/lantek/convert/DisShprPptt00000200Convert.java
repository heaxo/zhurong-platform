package com.ao.platform.core.lantek.convert;

import com.ao.platform.core.lantek.dto.DisShprPptt00000200DTO;
import com.ao.platform.core.lantek.dto.DisShprPptt00000200PageQuery;
import com.ao.platform.core.lantek.entity.DisShprPptt00000200;
import com.ao.platform.core.lantek.vo.DisShprPptt00000200VO;
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
public interface DisShprPptt00000200Convert {

    /**
     * Entity → VO
     */
    DisShprPptt00000200VO toVO(DisShprPptt00000200 entity);

    /**
     * Entity 列表 → VO 列表
     */
    List
            <DisShprPptt00000200VO> toVOList(List<DisShprPptt00000200> list);

    /**
     * DTO → Entity
     */
    DisShprPptt00000200 toEntity(DisShprPptt00000200DTO dto);

    DisShprPptt00000200 toEntity(DisShprPptt00000200PageQuery dto);

    /**
     * 更新时 DTO → Entity（忽略 null）
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDTO(DisShprPptt00000200DTO dto, @MappingTarget DisShprPptt00000200 entity);
}
