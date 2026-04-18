package com.zhurong.platform.core.lantek.convert;

import com.zhurong.platform.core.lantek.dto.WwhhIivv00000200DTO;
import com.zhurong.platform.core.lantek.dto.WwhhIivv00000200PageQuery;
import com.zhurong.platform.core.lantek.entity.WwhhIivv00000200;
import com.zhurong.platform.core.lantek.vo.WwhhIivv00000200VO;
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
public interface WwhhIivv00000200Convert {

/**
* Entity → VO
*/
WwhhIivv00000200VO toVO(WwhhIivv00000200 entity);

/**
* Entity 列表 → VO 列表
*/
List
<WwhhIivv00000200VO> toVOList(List<WwhhIivv00000200> list);

    /**
    * DTO → Entity
    */
    WwhhIivv00000200 toEntity(WwhhIivv00000200DTO dto);
    WwhhIivv00000200 toEntity(WwhhIivv00000200PageQuery dto);

    /**
    * 更新时 DTO → Entity（忽略 null）
    */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDTO(WwhhIivv00000200DTO dto, @MappingTarget WwhhIivv00000200 entity);
    }
