package com.zhurong.platform.core.lantek.convert;

import com.zhurong.platform.core.lantek.dto.WwhhWwhh00000200DTO;
import com.zhurong.platform.core.lantek.dto.WwhhWwhh00000200PageQuery;
import com.zhurong.platform.core.lantek.entity.WwhhWwhh00000200;
import com.zhurong.platform.core.lantek.vo.WwhhWwhh00000200VO;
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
public interface WwhhWwhh00000200Convert {

/**
* Entity → VO
*/
WwhhWwhh00000200VO toVO(WwhhWwhh00000200 entity);

/**
* Entity 列表 → VO 列表
*/
List
<WwhhWwhh00000200VO> toVOList(List<WwhhWwhh00000200> list);

    /**
    * DTO → Entity
    */
    WwhhWwhh00000200 toEntity(WwhhWwhh00000200DTO dto);
    WwhhWwhh00000200 toEntity(WwhhWwhh00000200PageQuery dto);

    /**
    * 更新时 DTO → Entity（忽略 null）
    */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDTO(WwhhWwhh00000200DTO dto, @MappingTarget WwhhWwhh00000200 entity);
    }
