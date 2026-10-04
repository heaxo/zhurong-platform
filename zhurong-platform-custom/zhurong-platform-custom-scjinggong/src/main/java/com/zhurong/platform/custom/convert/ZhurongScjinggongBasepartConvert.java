package com.zhurong.platform.custom.convert;

import com.zhurong.platform.custom.dto.ZhurongScjinggongBasepartDTO;
import com.zhurong.platform.custom.dto.ZhurongScjinggongBasepartPageQuery;
import com.zhurong.platform.custom.entity.ZhurongScjinggongBasepart;
import com.zhurong.platform.custom.vo.ZhurongScjinggongBasepartVO;
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
public interface ZhurongScjinggongBasepartConvert {

/**
* Entity → VO
*/
ZhurongScjinggongBasepartVO toVO(ZhurongScjinggongBasepart entity);

/**
* Entity 列表 → VO 列表
*/
List
<ZhurongScjinggongBasepartVO> toVOList(List<ZhurongScjinggongBasepart> list);

    /**
    * DTO → Entity
    */
    ZhurongScjinggongBasepart toEntity(ZhurongScjinggongBasepartDTO dto);
    ZhurongScjinggongBasepart toEntity(ZhurongScjinggongBasepartPageQuery dto);

    /**
    * 更新时 DTO → Entity（忽略 null）
    */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDTO(ZhurongScjinggongBasepartDTO dto, @MappingTarget ZhurongScjinggongBasepart entity);
    }
