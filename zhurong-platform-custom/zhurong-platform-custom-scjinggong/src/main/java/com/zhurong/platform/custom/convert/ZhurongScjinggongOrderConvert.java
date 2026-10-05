package com.zhurong.platform.custom.convert;

import com.zhurong.platform.custom.dto.ZhurongScjinggongOrderDTO;
import com.zhurong.platform.custom.dto.ZhurongScjinggongOrderPageQuery;
import com.zhurong.platform.custom.entity.ZhurongScjinggongOrder;
import com.zhurong.platform.custom.vo.ZhurongScjinggongOrderVO;
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
public interface ZhurongScjinggongOrderConvert {

    /**
     * Entity → VO
     */
    ZhurongScjinggongOrderVO toVO(ZhurongScjinggongOrder entity);

    /**
     * Entity 列表 → VO 列表
     */
    List
            <ZhurongScjinggongOrderVO> toVOList(List<ZhurongScjinggongOrder> list);

    /**
     * DTO → Entity
     */
    ZhurongScjinggongOrder toEntity(ZhurongScjinggongOrderDTO dto);

    ZhurongScjinggongOrder toEntity(ZhurongScjinggongOrderPageQuery dto);

    /**
     * 更新时 DTO → Entity（忽略 null）
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDTO(ZhurongScjinggongOrderDTO dto, @MappingTarget ZhurongScjinggongOrder entity);
}
