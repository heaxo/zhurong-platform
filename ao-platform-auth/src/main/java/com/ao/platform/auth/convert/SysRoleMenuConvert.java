package com.ao.platform.auth.convert;

import com.ao.platform.auth.entity.SysRoleMenu;
import com.ao.platform.auth.dto.SysRoleMenuDTO;
import com.ao.platform.auth.vo.SysRoleMenuVO;

import org.mapstruct.Mapper;
import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

/**
*  对象转换器
*
* 说明：
* 1. Entity ↔ DTO
* 2. Entity ↔ VO
* 3. 使用 MapStruct 自动生成实现
*/
@Mapper(componentModel = "spring")
public interface SysRoleMenuConvert {

/**
* Entity → VO
*/
SysRoleMenuVO toVO(SysRoleMenu entity);

/**
* Entity 列表 → VO 列表
*/
List<SysRoleMenuVO> toVOList(List<SysRoleMenu> list);

    /**
    * DTO → Entity
    */
    SysRoleMenu toEntity(SysRoleMenuDTO dto);

    /**
    * 更新时 DTO → Entity（忽略 null）
    */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDTO(SysRoleMenuDTO dto, @MappingTarget SysRoleMenu entity);
    }
