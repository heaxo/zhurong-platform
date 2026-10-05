package com.zhurong.platform.custom.convert;

import com.zhurong.platform.custom.dto.ZhurongScjinggongOrderitemDTO;
import com.zhurong.platform.custom.dto.ZhurongScjinggongOrderitemPageQuery;
import com.zhurong.platform.custom.entity.ZhurongScjinggongOrderitem;
import com.zhurong.platform.custom.vo.ZhurongScjinggongOrderitemVO;
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
public interface ZhurongScjinggongOrderitemConvert {

    /**
     * Entity → VO
     */
    ZhurongScjinggongOrderitemVO toVO(ZhurongScjinggongOrderitem entity);

    /**
     * Entity 列表 → VO 列表
     */
    List
            <ZhurongScjinggongOrderitemVO> toVOList(List<ZhurongScjinggongOrderitem> list);

    /**
     * DTO → Entity
     */
    ZhurongScjinggongOrderitem toEntity(ZhurongScjinggongOrderitemDTO dto);

    ZhurongScjinggongOrderitem toEntity(ZhurongScjinggongOrderitemPageQuery dto);

    /**
     * 更新时 DTO → Entity（忽略 null）
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDTO(ZhurongScjinggongOrderitemDTO dto, @MappingTarget ZhurongScjinggongOrderitem entity);
}
