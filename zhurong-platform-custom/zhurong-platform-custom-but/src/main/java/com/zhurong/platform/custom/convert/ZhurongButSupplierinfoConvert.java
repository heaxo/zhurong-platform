package com.zhurong.platform.custom.convert;

import com.zhurong.platform.custom.dto.ZhurongButSupplierinfoDTO;
import com.zhurong.platform.custom.dto.ZhurongButSupplierinfoPageQuery;
import com.zhurong.platform.custom.entity.ZhurongButSupplierinfo;
import com.zhurong.platform.custom.vo.ZhurongButSupplierinfoVO;
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
public interface ZhurongButSupplierinfoConvert {

    /**
     * Entity → VO
     */
    ZhurongButSupplierinfoVO toVO(ZhurongButSupplierinfo entity);

    /**
     * Entity 列表 → VO 列表
     */
    List
            <ZhurongButSupplierinfoVO> toVOList(List<ZhurongButSupplierinfo> list);

    /**
     * DTO → Entity
     */
    ZhurongButSupplierinfo toEntity(ZhurongButSupplierinfoDTO dto);

    ZhurongButSupplierinfo toEntity(ZhurongButSupplierinfoPageQuery dto);

    /**
     * 更新时 DTO → Entity（忽略 null）
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDTO(ZhurongButSupplierinfoDTO dto, @MappingTarget ZhurongButSupplierinfo entity);
}
