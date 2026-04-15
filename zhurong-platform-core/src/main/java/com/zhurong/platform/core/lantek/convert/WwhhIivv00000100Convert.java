package com.zhurong.platform.core.lantek.convert;

import com.zhurong.platform.core.lantek.dto.WwhhIivv00000100DTO;
import com.zhurong.platform.core.lantek.dto.WwhhIivv00000100PageQuery;
import com.zhurong.platform.core.lantek.entity.WwhhIivv00000100;
import com.zhurong.platform.core.lantek.vo.WwhhIivv00000100VO;
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
public interface WwhhIivv00000100Convert {

/**
* Entity → VO
*/
WwhhIivv00000100VO toVO(WwhhIivv00000100 entity);

/**
* Entity 列表 → VO 列表
*/
List
<WwhhIivv00000100VO> toVOList(List<WwhhIivv00000100> list);

    /**
    * DTO → Entity
    */
    WwhhIivv00000100 toEntity(WwhhIivv00000100DTO dto);
    WwhhIivv00000100 toEntity(WwhhIivv00000100PageQuery dto);

    /**
    * 更新时 DTO → Entity（忽略 null）
    */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDTO(WwhhIivv00000100DTO dto, @MappingTarget WwhhIivv00000100 entity);
    }
