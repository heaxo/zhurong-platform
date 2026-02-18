package com.ao.platform.auth.convert;

import com.ao.platform.auth.dto.SysUserRoleDTO;
import com.ao.platform.auth.dto.SysUserRolePageQuery;
import com.ao.platform.auth.entity.SysUserRole;
import com.ao.platform.auth.vo.SysUserRoleVO;
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
public interface SysUserRoleConvert {

    /**
     * Entity → VO
     */
    SysUserRoleVO toVO(SysUserRole entity);

    /**
     * Entity 列表 → VO 列表
     */
    List
            <SysUserRoleVO> toVOList(List<SysUserRole> list);

    /**
     * DTO → Entity
     */
    SysUserRole toEntity(SysUserRoleDTO dto);

    SysUserRole toEntity(SysUserRolePageQuery dto);

    /**
     * 更新时 DTO → Entity（忽略 null）
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDTO(SysUserRoleDTO dto, @MappingTarget SysUserRole entity);
}
