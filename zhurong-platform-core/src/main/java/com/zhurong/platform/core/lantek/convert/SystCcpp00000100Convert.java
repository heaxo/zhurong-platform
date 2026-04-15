package com.zhurong.platform.core.lantek.convert;

import com.zhurong.platform.core.lantek.dto.SystCcpp00000100DTO;
import com.zhurong.platform.core.lantek.dto.SystCcpp00000100PageQuery;
import com.zhurong.platform.core.lantek.entity.SystCcpp00000100;
import com.zhurong.platform.core.lantek.vo.SystCcpp00000100VO;
import org.mapstruct.*;

import java.util.List;

/**
*  对象转换器
*
* 说明：
* 1. Entity ↔ DTO
* 2. Entity ↔ VO
* 3. 使用 MapStruct 自动生成实现
*/
@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface SystCcpp00000100Convert {

/**
* Entity → VO
*/
SystCcpp00000100VO toVO(SystCcpp00000100 entity);

/**
* Entity 列表 → VO 列表
*/
List
<SystCcpp00000100VO> toVOList(List<SystCcpp00000100> list);

    /**
    * DTO → Entity
    */
    SystCcpp00000100 toEntity(SystCcpp00000100DTO dto);
    SystCcpp00000100 toEntity(SystCcpp00000100PageQuery dto);

    /**
    * 更新时 DTO → Entity（忽略 null）
    */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDTO(SystCcpp00000100DTO dto, @MappingTarget SystCcpp00000100 entity);
    }
