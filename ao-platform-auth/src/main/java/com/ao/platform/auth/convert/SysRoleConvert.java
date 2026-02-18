package com.ao.platform.auth.convert;

import com.ao.platform.auth.dto.SysRoleDTO;
import com.ao.platform.auth.dto.SysRolePageQuery;
import com.ao.platform.auth.entity.SysRole;
import com.ao.platform.auth.vo.SysRoleVO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

/**
 * 对象转换器
 * <p>
 * 说明：
 * 1. Entity ↔ DTO
 * 2. Entity ↔ VO
 * 3. 使用 MapStruct 自动生成实现
 */
@Mapper(componentModel = "spring")
public interface SysRoleConvert {

    /**
     * Entity → VO
     */
    SysRoleVO toVO(SysRole entity);

    /**
     * Entity 列表 → VO 列表
     */
    List
            <SysRoleVO> toVOList(List<SysRole> list);

    /**
     * DTO → Entity
     */
    SysRole toEntity(SysRoleDTO dto);

    SysRole toEntity(SysRolePageQuery dto);

    /**
     * 更新时 DTO → Entity（忽略 null）
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDTO(SysRoleDTO dto, @MappingTarget SysRole entity);
}
