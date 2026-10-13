package com.zhurong.platform.custom.convert;

import com.zhurong.platform.custom.dto.ZhurongScjinggongSteelplateinventoryDTO;
import com.zhurong.platform.custom.dto.ZhurongScjinggongSteelplateinventoryPageQuery;
import com.zhurong.platform.custom.entity.ZhurongScjinggongSteelplateinventory;
import com.zhurong.platform.custom.vo.ZhurongScjinggongSteelplateinventoryVO;
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
public interface ZhurongScjinggongSteelplateinventoryConvert {

    /**
     * Entity → VO
     */
    ZhurongScjinggongSteelplateinventoryVO toVO(ZhurongScjinggongSteelplateinventory entity);

    /**
     * Entity 列表 → VO 列表
     */
    List
            <ZhurongScjinggongSteelplateinventoryVO> toVOList(List<ZhurongScjinggongSteelplateinventory> list);

    /**
     * DTO → Entity
     */
    ZhurongScjinggongSteelplateinventory toEntity(ZhurongScjinggongSteelplateinventoryDTO dto);

    ZhurongScjinggongSteelplateinventory toEntity(ZhurongScjinggongSteelplateinventoryPageQuery dto);

    /**
     * 更新时 DTO → Entity（忽略 null）
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDTO(ZhurongScjinggongSteelplateinventoryDTO dto, @MappingTarget ZhurongScjinggongSteelplateinventory entity);
}
