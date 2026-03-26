package com.zhurong.platform.auth.convert;

import com.zhurong.platform.auth.dto.SysDeptDTO;
import com.zhurong.platform.auth.dto.SysDeptPageQuery;
import com.zhurong.platform.auth.entity.SysDept;
import com.zhurong.platform.auth.vo.SysDeptVO;
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
public interface SysDeptConvert {

    /**
     * Entity → VO
     */
    SysDeptVO toVO(SysDept entity);

    /**
     * Entity 列表 → VO 列表
     */
    List
            <SysDeptVO> toVOList(List<SysDept> list);

    /**
     * DTO → Entity
     */
    SysDept toEntity(SysDeptDTO dto);

    SysDept toEntity(SysDeptPageQuery dto);

    /**
     * 更新时 DTO → Entity（忽略 null）
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDTO(SysDeptDTO dto, @MappingTarget SysDept entity);
}
