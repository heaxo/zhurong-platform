package com.zhurong.platform.core.lantek.convert;

import com.zhurong.platform.core.lantek.dto.DisMmnnBwsr00000200DTO;
import com.zhurong.platform.core.lantek.dto.DisMmnnBwsr00000200PageQuery;
import com.zhurong.platform.core.lantek.entity.DisMmnnBwsr00000200;
import com.zhurong.platform.core.lantek.vo.DisMmnnBwsr00000200VO;
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
public interface DisMmnnBwsr00000200Convert {

/**
* Entity → VO
*/
DisMmnnBwsr00000200VO toVO(DisMmnnBwsr00000200 entity);

/**
* Entity 列表 → VO 列表
*/
List
<DisMmnnBwsr00000200VO> toVOList(List<DisMmnnBwsr00000200> list);

    /**
    * DTO → Entity
    */
    DisMmnnBwsr00000200 toEntity(DisMmnnBwsr00000200DTO dto);
    DisMmnnBwsr00000200 toEntity(DisMmnnBwsr00000200PageQuery dto);

    /**
    * 更新时 DTO → Entity（忽略 null）
    */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDTO(DisMmnnBwsr00000200DTO dto, @MappingTarget DisMmnnBwsr00000200 entity);
    }
