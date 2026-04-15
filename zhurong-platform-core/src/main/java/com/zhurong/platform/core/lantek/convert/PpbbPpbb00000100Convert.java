package com.zhurong.platform.core.lantek.convert;

import com.zhurong.platform.core.lantek.dto.PpbbPpbb00000100DTO;
import com.zhurong.platform.core.lantek.dto.PpbbPpbb00000100PageQuery;
import com.zhurong.platform.core.lantek.entity.PpbbPpbb00000100;
import com.zhurong.platform.core.lantek.vo.PpbbPpbb00000100VO;
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
public interface PpbbPpbb00000100Convert {

/**
* Entity → VO
*/
PpbbPpbb00000100VO toVO(PpbbPpbb00000100 entity);

/**
* Entity 列表 → VO 列表
*/
List
<PpbbPpbb00000100VO> toVOList(List<PpbbPpbb00000100> list);

    /**
    * DTO → Entity
    */
    PpbbPpbb00000100 toEntity(PpbbPpbb00000100DTO dto);
    PpbbPpbb00000100 toEntity(PpbbPpbb00000100PageQuery dto);

    /**
    * 更新时 DTO → Entity（忽略 null）
    */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDTO(PpbbPpbb00000100DTO dto, @MappingTarget PpbbPpbb00000100 entity);
    }
