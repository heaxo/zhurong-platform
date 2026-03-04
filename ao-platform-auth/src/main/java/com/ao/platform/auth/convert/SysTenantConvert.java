package com.ao.platform.auth.convert;

import com.ao.platform.auth.dto.SysTenantDTO;
import com.ao.platform.auth.dto.SysTenantPageQuery;
import com.ao.platform.auth.entity.SysTenant;
import com.ao.platform.auth.vo.SysTenantVO;
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
public interface SysTenantConvert {

    /**
     * Entity → VO
     */
    SysTenantVO toVO(SysTenant entity);

    /**
     * Entity 列表 → VO 列表
     */
    List
            <SysTenantVO> toVOList(List<SysTenant> list);

    /**
     * DTO → Entity
     */
    SysTenant toEntity(SysTenantDTO dto);

    SysTenant toEntity(SysTenantPageQuery dto);

    /**
     * 更新时 DTO → Entity（忽略 null）
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDTO(SysTenantDTO dto, @MappingTarget SysTenant entity);
}
