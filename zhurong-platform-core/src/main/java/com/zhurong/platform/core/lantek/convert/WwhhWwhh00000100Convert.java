package com.zhurong.platform.core.lantek.convert;

import com.zhurong.platform.core.lantek.dto.WwhhWwhh00000100DTO;
import com.zhurong.platform.core.lantek.dto.WwhhWwhh00000100PageQuery;
import com.zhurong.platform.core.lantek.entity.WwhhWwhh00000100;
import com.zhurong.platform.core.lantek.vo.WwhhWwhh00000100VO;
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
public interface WwhhWwhh00000100Convert {

/**
* Entity → VO
*/
WwhhWwhh00000100VO toVO(WwhhWwhh00000100 entity);

/**
* Entity 列表 → VO 列表
*/
List
<WwhhWwhh00000100VO> toVOList(List<WwhhWwhh00000100> list);

    /**
    * DTO → Entity
    */
    WwhhWwhh00000100 toEntity(WwhhWwhh00000100DTO dto);
    WwhhWwhh00000100 toEntity(WwhhWwhh00000100PageQuery dto);

    /**
    * 更新时 DTO → Entity（忽略 null）
    */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDTO(WwhhWwhh00000100DTO dto, @MappingTarget WwhhWwhh00000100 entity);
    }
