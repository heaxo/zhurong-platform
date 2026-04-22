package com.zhurong.platform.custom.convert;

import com.zhurong.platform.custom.dto.ZhurongButNestingPartsSplitRecordsCreateDTO;
import com.zhurong.platform.custom.dto.ZhurongButNestingPartsSplitRecordsDTO;
import com.zhurong.platform.custom.dto.ZhurongButNestingPartsSplitRecordsPageQuery;
import com.zhurong.platform.custom.entity.ZhurongButNestingPartsSplitRecords;
import com.zhurong.platform.custom.vo.ZhurongButNestingPartsSplitRecordsVO;
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
public interface ZhurongButNestingPartsSplitRecordsConvert {

    /**
     * Entity → VO
     */
    ZhurongButNestingPartsSplitRecordsVO toVO(ZhurongButNestingPartsSplitRecords entity);

    /**
     * Entity 列表 → VO 列表
     */
    List
            <ZhurongButNestingPartsSplitRecordsVO> toVOList(List<ZhurongButNestingPartsSplitRecords> list);

    /**
     * DTO → Entity
     */
    ZhurongButNestingPartsSplitRecords toEntity(ZhurongButNestingPartsSplitRecordsDTO dto);
    List<ZhurongButNestingPartsSplitRecords> toEntity(List<ZhurongButNestingPartsSplitRecordsCreateDTO> dtos);

    ZhurongButNestingPartsSplitRecords toEntity(ZhurongButNestingPartsSplitRecordsPageQuery dto);

    /**
     * 更新时 DTO → Entity（忽略 null）
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDTO(ZhurongButNestingPartsSplitRecordsDTO dto, @MappingTarget ZhurongButNestingPartsSplitRecords entity);
}
